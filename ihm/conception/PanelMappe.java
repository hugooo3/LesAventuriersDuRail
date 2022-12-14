package ihm.conception;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelMappe extends JPanel 
{
	private Image img;
	private int largeur;
	private int hauteur;

	public PanelMappe(File imagePath, int largeur, int hauteur) 
	{
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		this.setLayout(null);
		this.img = getToolkit().getImage(imagePath.getAbsolutePath());
		this.setPreferredSize(new Dimension((int) (largeur * 0.7), hauteur));
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(img,1, 1, (int) this.getSize().getWidth() - 2, (int) this.getSize().getHeight() - 2, this);
	}
}