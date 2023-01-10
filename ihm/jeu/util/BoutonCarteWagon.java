package ihm.jeu.util;

import javax.swing.*;
import metier.*;

public class BoutonCarteWagon extends JButton {

	private CarteWagon carte;

	public BoutonCarteWagon(CarteWagon carte, String texte) {
		super(texte == null ? "" : texte);
		this.carte = carte;
	}

	public void setCarteWagon(CarteWagon carte) { this.carte = carte; }
	public CarteWagon getCarteWagon() { return this.carte; }
}