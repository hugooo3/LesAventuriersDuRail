package ihm.conception;

import ihm.FrameConcepteur;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;

import java.io.File;

public class MenuBar extends JMenuBar implements ActionListener
{
	private FrameConcepteur frameConcepteur;

	private File imagePath;
	private File dossierPath;

	private JMenuItem menuiFichierNouveau, menuiFichierOuvrir;

	private JLabel lblPathNouveau;
	private JLabel lblPathOuvrir;
	private JPanel panelNouveau;
	private JPanel panelOuvrir;

	private JButton btnFichierOuvrir;
	private JButton btnFichierNouveau;
	private JButton btnDefaultImage;

	public MenuBar(FrameConcepteur frameConcepteur)
	{
		this.frameConcepteur = frameConcepteur;

		// Crea des composants

		this.lblPathNouveau = new JLabel("(Fichier : \"...\")", JLabel.CENTER);
		this.lblPathOuvrir  = new JLabel("(Dossier : \"...\")", JLabel.CENTER);

		JMenu menuFichier = new JMenu("Fichier");
		menuFichier.setMnemonic('F');

		this.menuiFichierNouveau = new JMenuItem("Nouveau");
		this.menuiFichierNouveau.setMnemonic('N');
		
		this.menuiFichierOuvrir = new JMenuItem("Ouvrir");
		this.menuiFichierOuvrir.setMnemonic('O');

		// Positionnement des composants
		menuFichier.add(this.menuiFichierNouveau);
		menuFichier.add(this.menuiFichierOuvrir);
		this.add(menuFichier);

		// Integration des raccourcis
		this.menuiFichierNouveau.setAccelerator( // CTRL + N
			KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		this.menuiFichierOuvrir.setAccelerator( // CTRL + O
			KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

		this.menuiFichierNouveau.addActionListener(this);
		this.menuiFichierOuvrir.addActionListener(this);

		
		// Contenu du Panel nouveau
		this.panelNouveau = new JPanel(new GridBagLayout());		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		this.btnFichierNouveau = new JButton("Parcourir les fichiers");
		this.btnFichierNouveau.setBackground(Color.LIGHT_GRAY);
		this.btnFichierNouveau.setBorderPainted(false);
		this.btnFichierNouveau.setFocusPainted(false);

		this.btnDefaultImage = new JButton("Fond d'écran par défaut");
		this.btnDefaultImage.setBackground(Color.LIGHT_GRAY);
		this.btnDefaultImage.setBorderPainted(false);
		this.btnDefaultImage.setFocusPainted(false);

		this.btnFichierNouveau.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.panelNouveau.add(this.btnFichierNouveau, gbc);

		this.btnDefaultImage.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.panelNouveau.add(this.btnDefaultImage, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.panelNouveau.add(this.lblPathNouveau, gbc);

		//Contenu du Panel ouvrir
		this.panelOuvrir = new JPanel(new GridLayout(2, 1));

		this.btnFichierOuvrir = new JButton("Parcourir les fichiers");
		this.btnFichierOuvrir.setBackground(Color.LIGHT_GRAY);
		this.btnFichierOuvrir.setBorderPainted(false);
		this.btnFichierOuvrir.setFocusPainted(false);

		this.btnFichierOuvrir.addActionListener(this);

		this.panelOuvrir.add(btnFichierOuvrir);
		this.panelOuvrir.add(lblPathOuvrir);
	}
	

	public void ouvrirExploreur(String path, String type) 
	{
		if (type.equals("Images")) 
		{
			// On lui applique l'UI du pc de l'utilisateur
			// Ici on ouvre l'explorateur de fichiers et on lui applique des paramètres
			try 
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} 
			catch (Exception exception) {exception.printStackTrace();}

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
			catch (Exception exception) {exception.printStackTrace();}

			// Après que l'utilisateur ait cliqué sur "ouvrir", on récupère le fichier
			// sélectionné

			try 
			{
				int reponse = parcourirFichier.showOpenDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION && parcourirFichier.getSelectedFile() != null) 
				{
					this.lblPathNouveau.setText(parcourirFichier.getSelectedFile().getAbsolutePath());
					this.imagePath = parcourirFichier.getSelectedFile();
				}
			} 
			catch (Exception exception) {exception.printStackTrace();}
		}
		else {
			try 
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} 
			catch (Exception exception) {exception.printStackTrace();}

			JFileChooser parcourirFichier = new JFileChooser(path);
			parcourirFichier.setDialogTitle("Choisissez un fichier Mappe XML");
			parcourirFichier.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			parcourirFichier.setApproveButtonText("Ouvrir");

			// On lui applique l'UI du pc de l'utilisateur
			try 
			{
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} 
			catch (Exception exception) {exception.printStackTrace();}

			// Après que l'utilisateur ait cliqué sur "ouvrir", on récupère le fichier
			// sélectionné

			try 
			{
				int reponse = parcourirFichier.showOpenDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION && parcourirFichier.getSelectedFile() != null) 
				{
					this.lblPathOuvrir.setText(parcourirFichier.getSelectedFile().getAbsolutePath());
					this.dossierPath = parcourirFichier.getSelectedFile();
				}
			} 
			catch (Exception exception) {exception.printStackTrace();}
		}
	}

	public void actionPerformed(ActionEvent e) 
	{
		// Clic sur le bouton Passer
		if (e.getSource() == this.btnDefaultImage) 
		{		
			this.lblPathNouveau.setText("../images/carteUSA.png");	
			this.imagePath = new File("images/carteUSA.png");
		}
		
		if (e.getSource() == this.menuiFichierNouveau) 
		{
			this.imagePath = null;
			JOptionPane.showMessageDialog(null, this.panelNouveau);
			this.lblPathNouveau.setText(this.imagePath.getAbsolutePath());
			this.frameConcepteur.setImgMappe(this.imagePath);
		}

		if (e.getSource() == this.menuiFichierOuvrir) 
		{
			this.dossierPath = null;
			JOptionPane.showMessageDialog(null, this.panelOuvrir);
			this.lblPathOuvrir.setText(this.dossierPath.getAbsolutePath());

			for (File file : this.dossierPath.listFiles())
			{
				if (file.getName().contains("imgMappe"))
				{
					this.frameConcepteur.setImgMappe(file);
				}
				if (file.getName().contains("Mappe.xml"))
				{
					this.frameConcepteur.importMappe(file);
				}
			}
			this.frameConcepteur.majIHM();
		}

		String nomOs = System.getProperty("os.name").toLowerCase();

		if (e.getSource() == this.btnFichierNouveau || e.getSource() == this.btnFichierOuvrir)
		{
			String path = FileSystemView.getFileSystemView().getHomeDirectory().toPath().toString();

			// On ouvre directement le répertoire "Images" ou "Documents" de l'utilisateur suivant son OS
			String directory = "";
			if (e.getSource() == this.btnFichierNouveau) { directory = "Images"; }
			else if (e.getSource() == this.btnFichierOuvrir) { directory = "Documents"; }

			if (nomOs.indexOf("win") >= 0) 
			{
				path = path.replace("Desktop", directory);
				ouvrirExploreur(path, directory);
			} 
			else if (nomOs.indexOf("mac") >= 0) 
			{
				path = path.replace("Desktop", directory);
				ouvrirExploreur(path, directory);
			} 
			else if (nomOs.indexOf("nux") >= 0 || nomOs.indexOf("nix") >= 0 || nomOs.indexOf("aix") >= 0) 
			{
				if (new File(path + "/" + directory).exists())
				{
					path += "/" + directory;
				} 
				else if (new File(path + "/" + directory).exists())
				{
					path += "/" + directory;
				}
				ouvrirExploreur(path, directory);
			} 
			else
			{
				JOptionPane.showMessageDialog(null, "Votre OS n'est pas supporté !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
