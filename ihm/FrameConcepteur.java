package ihm;

import application.Application;
import ihm.conception.*;
import metier.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;


public class FrameConcepteur extends JFrame 
{
	private Application appli;

	private int hauteur, largeur;

	private PanelMappe panelMappe;
	private JTabbedPane tabbedPane;
	private PanelParam panelParam;
	private PanelLstNoeud panelLstNoeud;

	public FrameConcepteur(Application app) 
	{
		this.setTitle("Concepteur");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-450,
										  (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-350));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2 - 50);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.hauteur = (int)this.getSize().getHeight();
		this.largeur = (int)this.getSize().getWidth();

		this.appli = app;
		this.setJMenuBar(new MenuBar(this));

		this.panelMappe = new PanelMappe(this, this.largeur, this.hauteur);
		this.tabbedPane = new JTabbedPane();
		this.panelParam = new PanelParam(this, this.largeur, this.hauteur);
		this.panelLstNoeud = new PanelLstNoeud(this, largeur, hauteur);

		this.tabbedPane.addTab("Paramètres", this.panelParam);
		this.tabbedPane.addTab("Liste noeud", this.panelLstNoeud);
		this.tabbedPane.setVisible(false);

		this.add(this.panelMappe, BorderLayout.CENTER);
		this.add(this.tabbedPane, BorderLayout.EAST);

		this.setVisible(true);
	}

	public void setImgMappe(File imagePath) 
	{
		this.appli.setImgMappe(imagePath);
		this.panelMappe.setImg(imagePath);
		this.panelMappe.changeCliquable();
		this.tabbedPane.setVisible(true);
	}

	public boolean setNbJoueurMin(int nbJoueurMin) {return this.appli.setNbJoueurMin(nbJoueurMin);}
	public boolean setNbJoueurMax(int nbJoueurMax) {return this.appli.setNbJoueurMax(nbJoueurMax);}
	public boolean setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) {return this.appli.setNbJoueurDoubleVoies(nbJoueurDoubleVoies);}
	public boolean setNbWagonJoueur(int nbWagonJoueur) {return this.appli.setNbWagonJoueur(nbWagonJoueur);}

	public Metier getMetier() {return this.appli.getMetier();}
	
	public void majIHM() 
	{
		this.panelMappe.majIHM();
		this.panelLstNoeud.majLstNoeuds();
	}
}