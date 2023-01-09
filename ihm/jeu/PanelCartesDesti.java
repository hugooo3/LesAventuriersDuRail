package ihm.jeu;

import metier.CarteDestination;
import ihm.FrameJeu;
import metier.Noeud;
import metier.Joueur;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

public class PanelCartesDesti extends JPanel {

	private JScrollPane scrollPane;
	private ArrayList<CarteDestination> alCartesDesti;
	private JList<String> lstAffichage;
	private DefaultListModel<String> lstModel;

	private FrameJeu jeu;
	private Joueur joueur;

	private JPanel panelListe;

	public PanelCartesDesti(FrameJeu jeu, Joueur joueur) {
		this.jeu = jeu;
		this.joueur = joueur;

		this.setLayout(new BorderLayout());
		//this.jeu.majIHM();

		this.alCartesDesti = this.joueur.getAlCarteDestination();
		this.panelListe = new JPanel();
		this.panelListe.setLayout(new BorderLayout());
		this.lstModel = new DefaultListModel<String>();
		this.lstAffichage = new JList<>(this.lstModel);

		for(CarteDestination carte : this.alCartesDesti) {
			String noeud1 = carte.getNoeud1().getNom();
			String noeud2 = carte.getNoeud2().getNom();
			String nbPoint = carte.getPoints() + "";

			this.lstModel.addElement(String.format("%3s | %-12s | %-12s", nbPoint, noeud1, noeud2));
		}

		this.panelListe.add(this.lstAffichage, BorderLayout.CENTER);


		this.scrollPane = new JScrollPane(this.panelListe);
		this.scrollPane.createVerticalScrollBar();

		JLabel lblNom = new JLabel("Cartes Destination");



		this.add(this.scrollPane, BorderLayout.CENTER);
		this.add(lblNom, BorderLayout.NORTH);
	}


	public void majCartesDesti(ArrayList<CarteDestination> alCartesDesti) {
		this.alCartesDesti = alCartesDesti;
	}

	public void majIHM() { this.jeu.majIHM(); }

}