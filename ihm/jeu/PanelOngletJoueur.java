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
	private ArrayList<String> lstNomJoueur;

	public PanelOngletJoueur(FrameJeu jeu, int largeur, int hauteur, int nbJoueur,  ArrayList<Joueur> alJoueurs) {
		this.jeu = jeu;
		this.lstJoueur = jeu.getAlJoueur();
		this.lstNomJoueur = new ArrayList<String>();
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.nbJoueur = nbJoueur;

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (this.largeur), this.hauteur));

		// Contenu du panel
		// ----------------

		// Init des panels Joueur
		for(Joueur j : alJoueurs) 
			this.lstNomJoueur.add(j.getNomJoueur());

		this.listePanelJoueur = new ArrayList<PanelJoueur>();
		for(int i = 0; i < this.nbJoueur; i++)
			this.listePanelJoueur.add(new PanelJoueur(this.jeu, this.lstJoueur.get(i), this.largeur, this.hauteur));

		// Creation des onglets de joueur
		this.panelOnglets = new JTabbedPane();
		for(int i=0; i < this.listePanelJoueur.size(); i++)
			this.panelOnglets.addTab(this.lstNomJoueur.get(i), this.listePanelJoueur.get(i));

		Joueur joueurCourant = this.jeu.getMetier().getJoueurEnJeu();
		for(Joueur j : this.lstJoueur) {
			if(!j.equals(joueurCourant)) { this.panelOnglets.setEnabledAt(this.lstJoueur.indexOf(j), false); } 
			else { 
				this.panelOnglets.setEnabledAt(this.lstJoueur.indexOf(j), true); 
				this.panelOnglets.setSelectedIndex(this.lstJoueur.indexOf(j));
			}
		}
	

		this.add(this.panelOnglets, BorderLayout.CENTER);
	}


	public void majLstJoueurs(ArrayList<Joueur> lstJoueur) { this.lstJoueur = lstJoueur; }

	public void majCartesWagon() {
		for(PanelJoueur panel : this.listePanelJoueur)
			panel.majCartesWagon();
	}

	public void majCartesDesti() {
		for(PanelJoueur panel : this.listePanelJoueur)
			panel.majCartesDesti();
	}

	public void majJoueur() {
		Joueur joueurCourant = this.jeu.getMetier().getJoueurEnJeu();
		for(Joueur j : this.lstJoueur) {
			if(!j.equals(joueurCourant)) { this.panelOnglets.setEnabledAt(this.lstJoueur.indexOf(j), false); } 
			else {
				this.panelOnglets.setEnabledAt(this.lstJoueur.indexOf(j), true);
				this.panelOnglets.setSelectedIndex(this.lstJoueur.indexOf(j));
			}
		}
	}
}