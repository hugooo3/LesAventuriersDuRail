package ihm.jeu;

import metier.CarteWagon;

import java.util.ArrayList;
import javax.swing.*;

public class PanelCartesWagon extends JPanel {

	private JScrollPane scrollPane;
	private ArrayList<CarteWagon> alCartesWagon;

	
	public PanelCartesWagon() {

	}


	public void majCartesWagon(ArrayList<CarteWagon> alCartesWagon) {
		this.alCartesWagon = alCartesWagon;
	}
}