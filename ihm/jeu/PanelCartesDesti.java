package ihm.jeu;

import metier.CarteDestination;
import ihm.FrameJeu;
import metier.Noeud;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;

public class PanelCartesDesti extends JPanel {

	private JScrollPane scrollPane;
	private ArrayList<CarteDestination> alCartesDesti;

	private FrameJeu jeu;

	public PanelCartesDesti(FrameJeu jeu) {
		this.jeu = jeu;

		this.setLayout(new BorderLayout());
		//this.jeu.majIHM();

		this.alCartesDesti = new ArrayList<CarteDestination>();
		for(int i=0; i < 5; i++)
			this.alCartesDesti.add(new CarteDestination(new Noeud("", 1, 1, 1), new Noeud("", 1, 1, 1), 1));

		this.scrollPane = new JScrollPane(new JList(this.alCartesDesti.toArray()));
		this.scrollPane.setFocusable(false);
		JLabel lblNom = new JLabel("Cartes Destination");



		this.add(this.scrollPane, BorderLayout.CENTER);
		this.add(lblNom, BorderLayout.NORTH);
	}


	public void majCartesDesti(ArrayList<CarteDestination> alCartesDesti) {
		this.alCartesDesti = alCartesDesti;
	}

	public void majIHM() { this.jeu.majIHM(); }
}