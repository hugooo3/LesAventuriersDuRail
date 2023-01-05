package ihm.jeu;

import ihm.FrameJeu;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class PanelJoueur extends JPanel {

	private FrameJeu jeu;
	private int largeur;
	private int hauteur;

	private JTabbedPane panelOngletCartes;

	private PanelCartesWagon panelCartesWagon;
	private PanelCartesDesti panelCartesDestination;

	public PanelJoueur(FrameJeu jeu, int largeur, int hauteur) {
		this.jeu = jeu;

		this.largeur = largeur;
		this.hauteur = hauteur;

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (this.largeur * 0.7), this.hauteur));


		// Contenu du panel

		this.panelOngletCartes = new JTabbedPane();




	}
}