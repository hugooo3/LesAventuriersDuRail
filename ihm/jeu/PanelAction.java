package ihm.jeu;

import ihm.FrameJeu;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

public class PanelAction extends JPanel 
{
	private FrameJeu frameJeu;

	private int largeur;
	private int hauteur;
	
	public PanelAction(FrameJeu frameJeu, int largeur, int hauteur) 
	{
		this.frameJeu = frameJeu;

		this.largeur = largeur;
		this.hauteur = hauteur;
		
		this.setLayout(new GridLayout(1, 1));
		this.setPreferredSize(new Dimension(this.largeur, this.hauteur));

		this.add(new JLabel("testaeaeeaaeea")); // Temporaire
	}
}