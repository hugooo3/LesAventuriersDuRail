package ihm.jeu;

import metier.CarteWagon;
import ihm.FrameJeu;
import metier.Joueur;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Image.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelCartesWagon extends JPanel {

	private JScrollPane scrollPane;
	private ArrayList<CarteWagon> alCartesWagon;
	private ArrayList<Integer> alNbCarte;

	private JPanel panelListe;

	private FrameJeu jeu;
	private Joueur joueur;

	
	public PanelCartesWagon(FrameJeu jeu, Joueur joueur) {
		this.jeu = jeu;
		this.joueur = joueur;

		this.setLayout(new BorderLayout());
		//this.jeu.majIHM();


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
			JLabel lblTemp = new JLabel(imgRectoResized);

			JLabel lblNb = new JLabel("" + this.alNbCarte.get(i), JLabel.CENTER);
			lblNb.setSize(new Dimension(100, 20));

			this.panelListe.add(lblTemp);
			this.panelListe.add(lblNb);
		}

		this.scrollPane = new JScrollPane(this.panelListe);
		this.scrollPane.createVerticalScrollBar();

		JLabel lblNom = new JLabel("Cartes Wagon");

		this.add(this.scrollPane, BorderLayout.CENTER);
		this.add(lblNom, BorderLayout.NORTH);
	}





	public void majCartesWagon(ArrayList<CarteWagon> alCartesWagon) {
		this.alCartesWagon = alCartesWagon;
	}

	public void majIHM() { this.jeu.majIHM(); }
}