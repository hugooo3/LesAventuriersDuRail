package ihm;

import javax.swing.*;

import application.Application;
import metier.Joueur;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class FrameManager extends JFrame implements ActionListener
{
	private Application app;
	private FrameChoixJoueur frameChoixJoueur;
	private JButton btnConcepteur;
	private JButton btnJeu;

	public FrameManager(Application app) {
		this.app = app;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception exception) {exception.printStackTrace();}
		
		// Construction de la Frame

		this.setTitle("Les Aventuriers Du Rail");
		this.setResizable(false);

		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 250,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 250));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 50);		

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints layoutCons = new GridBagConstraints();
		layoutCons.fill = GridBagConstraints.HORIZONTAL;
		layoutCons.insets = new Insets(0, 150, 0, 150);
		layout.setConstraints(this, layoutCons);

		// Contenu de la Frame

		this.btnConcepteur = new JButton("Concepteur");
		this.btnJeu = new JButton("Jouer");

		this.btnConcepteur.setPreferredSize(new Dimension(240, 80));
		this.btnJeu.setPreferredSize(new Dimension(240, 80));

		this.btnConcepteur.setBackground(Color.GRAY);
		this.btnJeu.setBackground(Color.GRAY);

		this.btnConcepteur.setBorderPainted(false);
		this.btnConcepteur.setFocusPainted(false);

		this.btnJeu.setBorderPainted(false);
		this.btnJeu.setFocusPainted(false);

		// Ajout des composants
		layout.addLayoutComponent(this.btnConcepteur, layoutCons);
		layout.addLayoutComponent(this.btnJeu, layoutCons);

		this.btnConcepteur.addActionListener(this);
		this.btnJeu.addActionListener(this);

		this.add(this.btnConcepteur);
		this.add(this.btnJeu);

		// Options pour la fermeture/apparence de la Frame
		this.setLayout(layout);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public ArrayList<Joueur> getLstJoueur () {return this.frameChoixJoueur.getLstJoueur();}
	public File getMappeXML() { return this.frameChoixJoueur.getMappeXML();}
	public boolean preparationJeu() {return this.app.preparationJeu();}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnConcepteur)
		{
			this.app.reinitialiserDossierSortie();
			new FrameConcepteur(this.app);
			this.dispose();
		}

		if (e.getSource() == this.btnJeu)
		{
			this.frameChoixJoueur = new FrameChoixJoueur(this.app);
			this.dispose();
		}
	}
}