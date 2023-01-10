package ihm.jeu;

import metier.CarteWagon;
import ihm.FrameJeu;
import metier.Joueur;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.*;

public class PanelCartesWagon extends JPanel {

	private JScrollPane scrollPane;
	private HashMap<CarteWagon, Integer> hmWagon;
	private ArrayList<CarteWagon> alCartesWagon;
	private ArrayList<Integer> alNbCarte;

	private ArrayList<JLabel> alLblCartesWagon;
	private ArrayList<JLabel> alLblNbCartesWagon;

	private JPanel panelListe;

	private FrameJeu jeu;
	private Joueur joueur;

	
	public PanelCartesWagon(FrameJeu jeu, Joueur joueur) {
		this.jeu = jeu;
		this.joueur = joueur;

		this.setLayout(new BorderLayout());
		//this.jeu.majIHM();


		this.alLblCartesWagon = new ArrayList<JLabel>();
		this.alLblNbCartesWagon = new ArrayList<JLabel>();
		this.alCartesWagon = new ArrayList<CarteWagon>();
		this.alNbCarte = new ArrayList<Integer>();
		this.joueur.getHmWagon().forEach((k, v) -> {
			this.alCartesWagon.add(k);
			this.alNbCarte.add(v);
		});

		this.panelListe = new JPanel();
		this.panelListe.setLayout(new GridLayout(this.alCartesWagon.size(), 2));

		for(int i=0; i < this.alCartesWagon.size(); i++) {
			Image imgRecto = null;
			imgRecto = this.alCartesWagon.get(i).getImgRecto().getImage();
			imgRecto = imgRecto.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

			ImageIcon imgRectoResized = new ImageIcon(imgRecto);
			this.alLblCartesWagon.add(new JLabel(imgRectoResized));

			this.alLblNbCartesWagon.add(new JLabel("" + this.alNbCarte.get(i), JLabel.CENTER));
			this.alLblNbCartesWagon.get(i).setSize(new Dimension(100, 20));

			this.panelListe.add(this.alLblCartesWagon.get(i));
			this.panelListe.add(this.alLblNbCartesWagon.get(i));
		}

		this.scrollPane = new JScrollPane(this.panelListe);
		this.scrollPane.createVerticalScrollBar();

		JLabel lblNom = new JLabel("Cartes Wagon");

		this.add(this.scrollPane, BorderLayout.CENTER);
		this.add(lblNom, BorderLayout.NORTH);
	}





	public void majCartesWagon() {
		this.hmWagon = this.jeu.getMetier().getJoueurEnJeu().getHmWagon();
		this.alCartesWagon.clear();
		this.alNbCarte.clear();
		this.hmWagon.forEach((k, v) -> {
			this.alCartesWagon.add(k);
			this.alNbCarte.add(v);
		});

		for(int i=0; i < this.alLblNbCartesWagon.size(); i++) {
			this.alLblNbCartesWagon.get(i).setText("" + this.alNbCarte.get(i));
			this.alLblNbCartesWagon.get(i).updateUI();
		}
	}

	public void majIHM() { this.jeu.majIHM(); }
}