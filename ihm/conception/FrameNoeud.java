package ihm.conception;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.BorderLayout;

import ihm.Frame;

public class FrameNoeud extends Frame {

	@Override
	public String getName() {
		return "Définition des Noeuds";
	}

	private PanelMappe panelMappeNoeud;
	private PanelLstNoeud panelLstNoeud;

	public FrameNoeud(File imagePath) {
		// Définition des deux panels principaux
		this.panelMappeNoeud = new PanelMappe(this, imagePath, this.LARGEUR, this.HAUTEUR, true);

		this.panelLstNoeud = new PanelLstNoeud(this.LARGEUR, this.HAUTEUR);

		// Ajout des panels à la frame
		this.add(this.panelMappeNoeud, BorderLayout.WEST);
		this.add(this.panelLstNoeud, BorderLayout.EAST);
	}

}
