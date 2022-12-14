package ihm.conception;

import ihm.Frame;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.awt.event.*;

public class FrameParametre extends Frame
{
	public String getName() 
	{
		return "Définition des Paramètres";
	}

	private PanelMappe panelMappe;
	private PanelParam panelParam;

	public FrameParametre(File imagePath) 
	{
		// Définition des deux panels principaux
		this.panelMappe = new PanelMappe(imagePath, this.LARGEUR, this.HAUTEUR);

		this.panelParam = new PanelParam(this.LARGEUR, this.HAUTEUR);
		
		// Ajout des panels à la frame
		this.add(this.panelMappe, BorderLayout.WEST);
		this.add(this.panelParam, BorderLayout.EAST);
	}
}