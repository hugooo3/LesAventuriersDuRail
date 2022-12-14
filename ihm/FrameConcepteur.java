package ihm;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;


public class FrameConcepteur extends JFrame implements ActionListener {

	public final int HAUTEUR;
	public final int LARGEUR;

	private File image;

	private JPanel panelContenu;

	private JButton btnFichier;
	private JButton btnEnregistrer;
	private JButton btnPasser;

	private JLabel lblPath;


	public FrameConcepteur() 
	{
		// Construction de la Frame

		this.setTitle("Concepteur de mappe");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-450,
										  (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-350));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2 - 50);



		// Contenu de la Frame


		// Contenu du Panel principal
		this.panelContenu = new JPanel();
		this.panelContenu.setSize(this.getSize());

		this.panelContenu.setBackground(Color.GRAY);
		this.panelContenu.setLayout(null);

		this.HAUTEUR = (int) this.panelContenu.getSize().getHeight();
		this.LARGEUR = (int) this.panelContenu.getSize().getWidth();
		int centreH = (int) this.panelContenu.getSize().getWidth() / 2; // Ces deux paramètres serviront à placer proprement les éléments sur notre panel
		int centreV = (int) this.panelContenu.getSize().getHeight() / 2;


		this.btnFichier = new JButton("Parcourir les fichiers");
		this.btnFichier.setBackground(Color.LIGHT_GRAY);
		this.btnFichier.setBorderPainted(false);
		this.btnFichier.setFocusPainted(false);

		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnEnregistrer.setBackground(Color.LIGHT_GRAY);
		this.btnEnregistrer.setBorderPainted(false);
		this.btnEnregistrer.setFocusPainted(false);

		this.btnPasser = new JButton("Passer (fond d'écran par défaut)");
		this.btnPasser.setBackground(Color.LIGHT_GRAY);
		this.btnPasser.setBorderPainted(false);
		this.btnPasser.setFocusPainted(false);


		this.lblPath = new JLabel("(Fichier : \"...\")", JLabel.CENTER);

		this.panelContenu.add(this.btnFichier);
		this.btnFichier.setBounds(centreH - 125, (centreV / 4), 250, 65);
		this.btnFichier.addActionListener(this);

		this.panelContenu.add(this.btnEnregistrer);
		this.btnEnregistrer.setBounds(centreH - 100, centreV - 60, 200, 50);
		this.btnEnregistrer.addActionListener(this);

		this.panelContenu.add(this.btnPasser);
		this.btnPasser.setBounds(this.LARGEUR - 500, this.HAUTEUR - 120, 400, 60);
		this.btnPasser.addActionListener(this);

		this.panelContenu.add(this.lblPath);
		this.lblPath.setBounds(centreH - this.LARGEUR/2, (centreV / 4)+65, this.LARGEUR, 40);

		this.add(this.panelContenu, BorderLayout.CENTER);

		// Options pour la fermeture/apparence de la Frame

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void ouvrirExploreur(String path) 
	{
		// Ici on ouvre l'explorateur de fichiers et on lui applique des paramètres

		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch(Exception exception) { exception.printStackTrace(); }

		JFileChooser parcourirFichier = new JFileChooser(path);
		parcourirFichier.setDialogTitle("Choisissez un fond d'écran");
		parcourirFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		parcourirFichier.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png", "jpeg");
		parcourirFichier.addChoosableFileFilter(filter);
		parcourirFichier.setApproveButtonText("Ouvrir");


		// On lui applique l'UI du pc de l'utilisateur
		try 
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch(Exception exception) { exception.printStackTrace(); }


		// Après que l'utilisateur ait cliqué sur "ouvrir"

		try 
		{
			int reponse = parcourirFichier.showOpenDialog(null);
			if(reponse == JFileChooser.APPROVE_OPTION && parcourirFichier.getSelectedFile() != null) 
			{
				this.lblPath.setText(parcourirFichier.getSelectedFile().getAbsolutePath());
				this.image = parcourirFichier.getSelectedFile();
			}
		} 
		catch(Exception exception) { exception.printStackTrace(); }
	}


	public void actionPerformed(ActionEvent e) 
	{
		
		// Clic sur le bouton Enregistrer
		if(e.getSource() == this.btnEnregistrer) 
		{
			new FrameParam();
			this.dispose();

		}

		// Clic sur le bouton Passer
		if(e.getSource() == this.btnPasser) 
		{
			this.image = new File("../images/carteUSA.png");
			new FrameParam();
			this.dispose();
		}



		String nomOs = System.getProperty("os.name").toLowerCase();
		
		if(e.getSource() == this.btnFichier) 
		{

			String path = FileSystemView.getFileSystemView().getHomeDirectory().toPath().toString();

			// On ouvre directement le répertoire "Images" de l'utilisateur suivant son OS
			if(nomOs.indexOf("win") >= 0) 
			{
				path = path.replace("Desktop", "Pictures");
				ouvrirExploreur(path);
			} 
			else if(nomOs.indexOf("mac") >= 0) 
			{
				path = path.replace("Desktop", "Pictures");
				ouvrirExploreur(path);
			} 
			else if(nomOs.indexOf("nux") >= 0 || nomOs.indexOf("nix") >= 0 || nomOs.indexOf("aix") >= 0) 
			{

				if(new File(path + "/Pictures").exists()) 
				{
					path += "/Pictures";
				} 
				else if(new File(path + "/Images").exists()) 
				{
					path += "/Images";
				}

				ouvrirExploreur(path);

			} 
			else { JOptionPane.showMessageDialog(null, "Votre OS n'est pas supporté !", "Erreur", JOptionPane.ERROR_MESSAGE); }
		}
	}
}