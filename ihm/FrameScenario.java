package ihm;

import application.Application;
import ihm.scenario.MenuBarScenario;
import metier.Joueur;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


/**
 * FrameScenario
 */
public class FrameScenario extends JFrame implements ActionListener
{
	private Application appli;

	private JPanel panelPrincipal;
	private JButton btnChargerScenario;


	private File mappeXML;
	private ArrayList<Joueur> alJoueur;

	public FrameScenario(Application appli) 
	{
		this.appli = appli;
		this.alJoueur = new ArrayList<Joueur>();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception exception) {exception.printStackTrace();}
		
		// Construction de la Frame

		this.setTitle("Scénario");
		this.setResizable(true);
		this.setBackground(Color.BLACK);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 250,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 250));
		this.setJMenuBar(new MenuBarScenario(this));
		

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 50);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints layoutCons = new GridBagConstraints();
		layoutCons.fill = GridBagConstraints.HORIZONTAL;
		layoutCons.insets = new Insets(0, 150, 0, 150);
		layout.setConstraints(this, layoutCons);

		// Bouton charger scenario
		this.btnChargerScenario = new JButton("Charger scénario");
		this.btnChargerScenario.setPreferredSize(new Dimension(240, 80));
		this.btnChargerScenario.addActionListener(this);
		this.btnChargerScenario.setBackground(Color.LIGHT_GRAY);
		this.btnChargerScenario.setBorderPainted(false);
		this.btnChargerScenario.setFocusPainted(false);

		// Contenu de la Frame
		// Panel principale
		this.panelPrincipal = new JPanel(layout);
		this.panelPrincipal.setBackground(Color.GRAY);

		// Ajout composant
		layout.addLayoutComponent(this.btnChargerScenario, layoutCons);
		panelPrincipal.add(this.btnChargerScenario);

		this.add(this.panelPrincipal);

		this.setVisible(true);
	}

	public Application getAppli() {return this.appli;}

	public void executerAction(String ligneAction) {
		String[] action = ligneAction.split(";");

		// Creation de la FrameJeu -> Jeu
		if (action[0].equals("Jeu"))
			new FrameJeu(this.appli, this.mappeXML, this.alJoueur.size(), this.alJoueur);

		// Creation d'un joueur -> Joueur;nom;r;g;b
		if (action[0].equals("Joueur"))
			this.alJoueur.add(new Joueur(action[1], this.appli.getMetier().getAlCartesWagon(), new Color(Integer.parseInt(action[2]), Integer.parseInt(action[3]), Integer.parseInt(action[4]))));
		
		// Set la pioche d'un joueur -> SetMainJoueur;idJoueur;nbJoker;nbBlanc;nbBleu;nbJaune;nbNoir;nbOrange;nbRouge;nbVert;nbViolet
		if (action[0].equals("SetMainJoueur"))
		{
			Integer[] main = new Integer[9];
			Joueur joueur = this.alJoueur.get(Integer.parseInt(action[1] ));
			main[0] = Integer.parseInt(action[2] );
			main[1] = Integer.parseInt(action[3] );
			main[2] = Integer.parseInt(action[4] );
			main[3] = Integer.parseInt(action[5] );
			main[4] = Integer.parseInt(action[6] );
			main[5] = Integer.parseInt(action[7] );
			main[6] = Integer.parseInt(action[8] );
			main[7] = Integer.parseInt(action[9] );
			main[8] = Integer.parseInt(action[10]);
			joueur.setMain(main);
		}
		
		// Charger un xml -> ChargerXML;chemin
		if (action[0].equals("ChargerXML"))
			this.mappeXML = new File("." + action[1]);
	}

	private void lireDonnee(String chemin)
	{
		try
		{
			Scanner sc = new Scanner ( new FileReader (chemin) );

			while ( sc.hasNextLine() )
			{
				String ligne = sc.nextLine();
				executerAction(ligne);
			}
			sc.close();
		}
		catch (Exception e) { e.printStackTrace();}
	}

	public String ouvrirExploreur() {
		String path = System.getProperty("user.dir");
		String pathTest = path + "/scenario/";

		if(new File(pathTest).exists() && new File(pathTest).isDirectory() && 
										  new File(pathTest).list().length > 0) {
			path = pathTest;
		}

		JFileChooser fileChooser = new JFileChooser(new File(path));
		fileChooser.setDialogTitle("Choisissez un scénario");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("DATA", "data");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setApproveButtonText("Ouvrir");

		int retour = fileChooser.showOpenDialog(this);

		if(retour == JFileChooser.APPROVE_OPTION) {
			if(fileChooser.getSelectedFile().exists()) {
				File scenario = fileChooser.getSelectedFile();
				return scenario.getPath();
			}
		}

		return null;
	}

	public void quitter() {
		new FrameManager(this.appli);
		this.appli.reinitialiserMetier();
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnChargerScenario)
		{
			String chemin = this.ouvrirExploreur();
			if(chemin != null)
				this.lireDonnee(chemin);
		}
	}	
}