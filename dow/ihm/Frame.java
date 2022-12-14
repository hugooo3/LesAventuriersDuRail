package dow.ihm;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Frame extends JFrame implements IFrame {
	
	public String getNom() {
		return null;
	}

	public final int HAUTEUR;
	public final int LARGEUR;
	public final int CENTRE_V;
	public final int CENTRE_H;

	public Frame() {
		
		this.setTitle(this.getName());
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-450,
										  (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-350));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2 - 50);

		this.HAUTEUR = (int) this.getSize().getHeight();
		this.LARGEUR = (int) this.getSize().getWidth();
		this.CENTRE_V = (int) this.getSize().getWidth() / 2;
		this.CENTRE_H = (int) this.getSize().getHeight() / 2;
	}


	public int getHauteur() { return this.HAUTEUR;  }
	public int getLargeur() { return this.LARGEUR;  }
	public int getCentreV() { return this.CENTRE_V; }
	public int getCentreH() { return this.CENTRE_H; }
}
