package ihm.jeu;

import ihm.FrameJeu;
import metier.Joueur;
import metier.CarteDestination;
import metier.CarteWagon;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class PanelJoueur extends JPanel {

	private FrameJeu jeu;
	private Joueur joueur;

	private int largeur;
	private int hauteur;

	private JTabbedPane panelOngletCartes;

	private PanelCartesWagon panelCartesWagon;
	private PanelCartesDesti panelCartesDestination;

	public PanelJoueur(FrameJeu jeu, Joueur joueur, int largeur, int hauteur) {
		this.jeu = jeu;
		this.joueur = joueur;
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (this.largeur * 0.7), this.hauteur));


		// Contenu du panel

		this.panelOngletCartes = new JTabbedPane();




	}

	public void majCartesWagon(ArrayList<CarteWagon> alCartesWagon) {
		this.panelCartesWagon.majCartesWagon(alCartesWagon);
	}

	public void majCartesDesti(ArrayList<CarteDestination> alCartesDesti) {
		this.panelCartesDestination.majCartesDesti(alCartesDesti);
	}
}