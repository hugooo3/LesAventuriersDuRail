package ihm.jeu;

import metier.CarteDestination;

import java.util.ArrayList;
import javax.swing.*;

public class PanelCartesDesti extends JPanel {

	private ArrayList<CarteDestination> alCartesDesti;

	public PanelCartesDesti() {

	}


	public void majCartesDesti(ArrayList<CarteDestination> alCartesDesti) {
		this.alCartesDesti = alCartesDesti;
	}
}