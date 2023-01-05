package ihm;

import application.Application;
import metier.*;
import ihm.jeu.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameJeu extends JFrame
{
	private Application appli;

	private int hauteur;
	private int largeur;

	private PanelMappeJeu panelMappeJeu;

	public FrameJeu(Application appli) 
	{
		this.appli = appli;
		this.setJMenuBar(new MenuBarJeu(this));

		this.setTitle("Jeu : Les Aventuriers du Rail (USA)");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 450,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 350));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 50);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.hauteur = (int) this.getSize().getHeight();
		this.largeur = (int) this.getSize().getWidth();

		this.panelMappeJeu = new PanelMappeJeu(this, largeur, hauteur);

		this.add(this.panelMappeJeu, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	public Metier getMetier() {return this.appli.getMetier();}

	public void majIHM() {}

	public void quitter() 
	{
		new FrameManager(this.appli);
		this.appli.reinitialiserMetier();
		this.dispose();
	}
}