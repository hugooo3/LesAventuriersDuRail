package ihm.jeu;

import metier.CarteWagon;
import ihm.FrameJeu;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;

public class PanelCartesWagon extends JPanel {

	private JScrollPane scrollPane;
	private ArrayList<CarteWagon> alCartesWagon;

	private FrameJeu jeu;

	
	public PanelCartesWagon(FrameJeu jeu) {
		this.jeu = jeu;

		this.setLayout(new BorderLayout());
		//this.jeu.majIHM();

		this.alCartesWagon = new ArrayList<CarteWagon>();
		for(int i=0; i < 5; i++)
			this.alCartesWagon.add(new CarteWagon("test" + i, null, null, null, 1));

		this.scrollPane = new JScrollPane(new JList(this.alCartesWagon.toArray()));
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