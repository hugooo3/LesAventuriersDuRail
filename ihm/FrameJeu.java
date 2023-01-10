package ihm;

import application.Application;
import metier.*;
import ihm.jeu.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

public class FrameJeu extends JFrame {
	private Application appli;

	private int hauteur;
	private int largeur;
	private int nbJoueurs;

	private File mappeXML;

	private ArrayList<Joueur> alJoueur;
	private ArrayList<Joueur> alJoueursMetier;

	private PanelMappeJeu panelMappeJeu;
	private PanelOngletJoueur panelOngletJoueur;
	private PanelAction panelAction;
	

	private Pioche pioche;

	public FrameJeu(Application appli, File mappeXML, int nbJoueurs, ArrayList<Joueur> alJoueurs) {
		this.appli = appli;
		this.mappeXML = mappeXML;
		this.nbJoueurs = nbJoueurs;
		this.alJoueur = alJoueurs;


		// Construction de la Frame
		this.setTitle("Jeu : Les Aventuriers du Rail (USA)");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 450,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 350));

		this.setJMenuBar(new MenuBarJeu(this)); // Obliger de mettre la barre de menu pour avoir la meme taille de mappe que dans le concepteur (sinon decalage y d'une dizaine de pixel)

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 25);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.hauteur = (int) this.getSize().getHeight();
		this.largeur = (int) this.getSize().getWidth();

		// Construction des panels

		this.preparationJeu(this.alJoueur);
		this.alJoueursMetier = this.getLstJoueurMetier();
		this.pioche = this.appli.getMetier().getPioche();
		

		this.panelMappeJeu = new PanelMappeJeu(this, (int) (largeur*0.7), hauteur);
		this.setImgMappe(this.appli.getMetier().getImgMappe());
		this.panelOngletJoueur = new PanelOngletJoueur(this, (int) (largeur*0.15), hauteur, this.nbJoueurs, this.alJoueursMetier);
		this.panelAction = new PanelAction(this, (int) (largeur*0.15), hauteur, this.alJoueursMetier, this.pioche);

		this.add(this.panelOngletJoueur, BorderLayout.WEST);
		this.add(this.panelMappeJeu, BorderLayout.CENTER);
		this.add(this.panelAction, BorderLayout.EAST);

		this.setVisible(true);

		/*TEMP */
		//ArrayList<Arete> alArete = this.appli.getMetier().getAlAretes();
		//alArete.get(0).setJoueurVoieSimple(this.alJoueur.get(1));
		//alArete.get(0).setJoueurVoieDouble(this.alJoueur.get(0));
		//alArete.get(1).setJoueurVoieSimple(this.alJoueur.get(1));
		//alArete.get(2).setJoueurVoieSimple(this.alJoueur.get(1));
		//alArete.get(3).setJoueurVoieSimple(this.alJoueur.get(1));
		//alArete.get(4).setJoueurVoieDouble(this.alJoueur.get(0));
		//alArete.get(5).setJoueurVoieSimple(this.alJoueur.get(1));
		//alArete.get(6).setJoueurVoieSimple(this.alJoueur.get(1));
	}

	public Metier getMetier() { return this.appli.getMetier(); }
	public File getMappeXML() { return this.mappeXML; }
	public ArrayList<Joueur> getAlJoueur() { return this.alJoueur; }


	public void majIHM() {
		this.panelMappeJeu.majIHM();
		this.panelOngletJoueur.majCartesWagon();
		this.panelOngletJoueur.majCartesDesti();
		this.panelAction.majIHM();
		this.majJoueur();
	}

	public void jeu(String action) {
		this.appli.jeu(action);
	}

	public void quitter() {
		new FrameManager(this.appli);
		this.appli.reinitialiserMetier();
		this.dispose();
	}

	public void setImgMappe(Image imgMappe) {
		if (imgMappe != null) {
			this.appli.setImgMappe(imgMappe);
			this.panelMappeJeu.setImg(imgMappe);
		}
	}

	public void majJoueur() { this.panelOngletJoueur.majJoueur(); }

	public ArrayList<Joueur> getLstJoueurMetier() {return this.appli.getLstJoueurMetier();}
	public boolean preparationJeu(ArrayList<Joueur> lstJoueur) { return this.appli.preparationJeu(lstJoueur); }
}