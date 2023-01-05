package ihm.jeu;

import ihm.FrameJeu;

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

	public PanelOngletJoueur(FrameJeu jeu, int largeur, int hauteur, int nbJoueur) {
		this.jeu = jeu;

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
			this.listePanelJoueur.add(new PanelJoueur(this.jeu, this.largeur, this.hauteur));

		// Creation des onglets de joueur
		this.panelOnglets = new JTabbedPane();
		for(PanelJoueur panel : this.listePanelJoueur)
			this.panelOnglets.addTab("Joueur " + (this.listePanelJoueur.indexOf(panel) + 1), panel);


		this.add(this.panelOnglets, BorderLayout.CENTER);
	}
}