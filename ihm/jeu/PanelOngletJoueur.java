package ihm.jeu;

import ihm.FrameJeu;
import metier.Joueur;
import metier.CarteWagon;
import metier.CarteDestination;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class PanelOngletJoueur extends JPanel {

	private FrameJeu jeu;
	private int largeur;
	private int hauteur;
	private int nbJoueur;

	private JTabbedPane panelOnglets;

	private ArrayList<PanelJoueur> listePanelJoueur;
	private ArrayList<Joueur> lstJoueur;

	public PanelOngletJoueur(FrameJeu jeu, int largeur, int hauteur, int nbJoueur) {
		this.jeu = jeu;
		this.lstJoueur = jeu.getLstJoueur();
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.nbJoueur = nbJoueur;

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (this.largeur * 0.7), this.hauteur));


		// Contenu du panel
		// ----------------

		// Init des panels Joueur
		this.listePanelJoueur = new ArrayList<PanelJoueur>();
		for(int i = 0; i < this.nbJoueur; i++)
			this.listePanelJoueur.add(new PanelJoueur(this.jeu, this.lstJoueur.get(i), this.largeur, this.hauteur));

		// Creation des onglets de joueur
		this.panelOnglets = new JTabbedPane();
		for(PanelJoueur panel : this.listePanelJoueur)
			this.panelOnglets.addTab("Joueur " + (this.listePanelJoueur.indexOf(panel) + 1), panel);


		this.add(this.panelOnglets, BorderLayout.CENTER);
	}


	public void majLstJoueurs(ArrayList<Joueur> lstJoueur) {
		this.lstJoueur = lstJoueur;
	}

	public void majCartesWagon(ArrayList<CarteWagon> alCartesWagon, Joueur joueur) {
		for(Joueur j : this.lstJoueur)
			if(joueur.equals(j))
				this.listePanelJoueur.get(this.lstJoueur.indexOf(j)).majCartesWagon(alCartesWagon);
	}

	public void majCartesDesti(ArrayList<CarteDestination> alCartesDesti, Joueur joueur) {
		for(Joueur j : this.lstJoueur)
			if(joueur.equals(j))
				this.listePanelJoueur.get(this.lstJoueur.indexOf(j)).majCartesDesti(alCartesDesti);
	}
}