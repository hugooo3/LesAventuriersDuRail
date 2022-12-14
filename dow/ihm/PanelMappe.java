package dow.ihm;

import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import java.io.File;

public class PanelMappe extends JPanel 
{

	private Image img;

	public PanelMappe(File imagePath) 
	{
		this.setLayout(null);
		this.img = getToolkit().getImage(imagePath.getAbsolutePath());
		/* TO DO
		 * Taille de l'image a bien ajuster, si elle est trop grande elle passe au dessus des boutons
		 */
		this.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-750,
												(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-350));
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(img, 0, 0, this);
	}

	// Classe interne pour la gestion des événements Souris
	/*
	 * private class GestionSouris extends MouseAdapter
	 * {
	 * public void mousePressed(MouseEvent event)
	 * {
	 * ctrl.cliquer ( event.getX(), event.getY() );
	 * }
	 * }
	 */
}
