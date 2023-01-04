package ihm;

import application.Application;
import ihm.conception.*;
import metier.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Image;

import java.io.File;

public class FrameConcepteur extends JFrame implements ActionListener {
	private Application appli;

	private int hauteur, largeur;

	private PanelMappe panelMappe;
	private JTabbedPane tabbedPane;
	private PanelParam panelParam;
	private PanelLstNoeud panelLstNoeud;
	private PanelLstArete panelLstArete;
	private PanelLstCarteDestination panelLstCarteDestination;
	private PanelLstCarteWagon panelLstCarteWagon;

	private JButton btnXML;

	public FrameConcepteur(Application app) {
		this.setTitle("Concepteur");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 450,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 350));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 50);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.hauteur = (int) this.getSize().getHeight();
		this.largeur = (int) this.getSize().getWidth();

		this.appli = app;
		this.setJMenuBar(new MenuBar(this));

		this.btnXML = new JButton("Exporter en XML");
		this.btnXML.addActionListener(this);
		this.btnXML.setVisible(false);

		// Def des panels
		this.panelMappe = new PanelMappe(this, this.largeur, this.hauteur);
		JPanel panelDroit = new JPanel(new BorderLayout());
		this.tabbedPane = new JTabbedPane();
		this.panelParam = new PanelParam(this, this.largeur, this.hauteur);
		this.panelLstNoeud = new PanelLstNoeud(this, this.largeur, this.hauteur);
		this.panelLstArete = new PanelLstArete(this, this.largeur, this.hauteur);
		this.panelLstCarteDestination = new PanelLstCarteDestination(this, largeur, hauteur);
		this.panelLstCarteWagon = new PanelLstCarteWagon(this, largeur, hauteur);

		// Ajout des panels au tabbedPane
		this.tabbedPane.addTab("Paramètres", this.panelParam);
		this.tabbedPane.addTab("Liste noeud", this.panelLstNoeud);
		this.tabbedPane.addTab("Liste arête", this.panelLstArete);
		this.tabbedPane.addTab("Liste carte destination", this.panelLstCarteDestination);
		this.tabbedPane.addTab("Liste carte wagon", this.panelLstCarteWagon);
		this.tabbedPane.setVisible(false);

		this.add(this.panelMappe, BorderLayout.CENTER);
		panelDroit.add(this.tabbedPane, BorderLayout.CENTER);
		panelDroit.add(this.btnXML, BorderLayout.SOUTH);
		this.add(panelDroit, BorderLayout.EAST);

		this.setVisible(true);
	}

	public void setImgMappe(Image image) {
		if (image != null) {
			this.appli.setImgMappe(image);
			this.panelMappe.setImg(image);
			this.panelMappe.setCliquable(true);
			this.tabbedPane.setVisible(true);
			this.btnXML.setVisible(true);
		}
	}

	public void setNbJoueurMin(int nbJoueurMin) {this.appli.setNbJoueurMin(nbJoueurMin);}
	public void setNbJoueurMax(int nbJoueurMax) {this.appli.setNbJoueurMax(nbJoueurMax);}
	public void setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) {this.appli.setNbJoueurDoubleVoies(nbJoueurDoubleVoies);}
	public void setNbWagonJoueur(int nbWagonJoueur) {this.appli.setNbWagonJoueur(nbWagonJoueur);}
	public void setNbFin(int nbFin) {this.appli.setNbFin(nbFin);}

	public Metier getMetier() {return this.appli.getMetier();}
	public void modifierLstNoeud(int selectedIndex) {this.panelMappe.modifierLstNoeud(selectedIndex);}
	public void removeLstAreteDeNoeud(Noeud noeud) {this.panelLstArete.removeLstAreteDeNoeud(noeud);}

	public boolean importMappe(File xmlPath) {return this.appli.importMappe(xmlPath);}
	
	public void majIHM()
	{
		this.panelMappe.majIHM();
		this.panelLstNoeud.majLstNoeuds();
		this.panelLstArete.majLstArete();
		this.panelLstCarteDestination.majLstCarteDestination();
		this.panelLstCarteWagon.majLstCarteWagon();
	}

	public void quitter() 
	{
		new FrameManager(this.appli);
		this.appli.reinitialiserMetier();
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnXML)
		{
			this.panelParam.setParam();
			this.appli.ecrireXML();
		}
	}
}