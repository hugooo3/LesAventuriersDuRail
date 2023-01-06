package ihm;

import application.Application;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import java.io.File;


public class FrameChoixJoueur extends JFrame implements ActionListener {
	
	private Application appli;

	private final int largeur;
	private final int hauteur;

	private File mappeXML;

	private JPanel panelImportXML;
	private JPanel panelCreationJoueurs;

	private JButton btnImport;
	private JButton btnConfirmer;
	private JButton btnMenu;

	public FrameChoixJoueur(Application appli) {
		this.appli = appli;

		// Paramètres Frame
		this.setTitle("Import Mappe XML");
		this.setLayout(new BorderLayout());

		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 150,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 150));


		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 25);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.hauteur = (int) this.getSize().getHeight();
		this.largeur = (int) this.getSize().getWidth();


		// Paramètres panels
		this.panelImportXML = new JPanel();
		this.panelImportXML.setLayout(null);
		this.panelImportXML.setMinimumSize(this.getSize());
		

		this.panelCreationJoueurs = new JPanel();
		this.panelCreationJoueurs.setEnabled(false);


		// Contenu Panel Import XML
		this.btnMenu = new JButton("Retour menu");
		this.btnMenu.addActionListener(this);
		this.panelImportXML.add(this.btnMenu);
		this.btnMenu.setBounds(20, 20, 150, 30);

		this.btnImport = new JButton("Ouvrir l'explorateur ...");
		this.btnImport.addActionListener(this);
		this.panelImportXML.add(this.btnImport);
		this.btnImport.setBounds(this.largeur/2 - 75, this.hauteur/2, 150, 30);

		this.btnConfirmer = new JButton("Confirmer");
		this.btnConfirmer.addActionListener(this);
		this.panelImportXML.add(this.btnConfirmer);
		this.btnConfirmer.setBounds(this.largeur/2 - 75, this.hauteur/2 + 100, 150, 30);

		this.add(this.panelImportXML, BorderLayout.CENTER);
		this.add(this.panelCreationJoueurs, BorderLayout.SOUTH);
		this.setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnMenu) {
			new FrameManager(appli);
			this.dispose();
		}

		if(e.getSource() == this.btnImport) {
			this.ouvrirExploreur();
		}

		if(e.getSource() == this.btnConfirmer && this.mappeXML != null) {
			this.panelImportXML.setEnabled(false);
			this.panelCreationJoueurs.setEnabled(true);
			this.setTitle("Création des joueurs");
		}
	}


	public void ouvrirExploreur() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);

		int retour = fileChooser.showOpenDialog(this);

		if(retour == JFileChooser.APPROVE_OPTION) {
			if(fileChooser.getSelectedFile().getName().endsWith(".xml") && fileChooser.getSelectedFile().exists()) 
				this.mappeXML = fileChooser.getSelectedFile();
		}
	}

}
