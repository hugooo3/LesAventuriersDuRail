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

	private JMenuItem menuiFichierNouveau, menuiFichierOuvrir;

	private JLabel lblPath;
	private JPanel panelNouveau;

	private JButton btnFichier;
	private JButton btnDefaultImage;

	public MenuBar(FrameConcepteur frameConcepteur)
	{
		this.frameConcepteur = frameConcepteur;

		// Crea des composants

		this.lblPath = new JLabel("(Fichier : \"...\")", JLabel.CENTER);

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

		this.btnFichier = new JButton("Parcourir les fichiers");
		this.btnFichier.setBackground(Color.LIGHT_GRAY);
		this.btnFichier.setBorderPainted(false);
		this.btnFichier.setFocusPainted(false);

		this.btnDefaultImage = new JButton("Fond d'écran par défaut");
		this.btnDefaultImage.setBackground(Color.LIGHT_GRAY);
		this.btnDefaultImage.setBorderPainted(false);
		this.btnDefaultImage.setFocusPainted(false);

		this.btnFichier.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.panelNouveau.add(this.btnFichier, gbc);

		this.btnDefaultImage.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.panelNouveau.add(this.btnDefaultImage, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.panelNouveau.add(this.lblPath, gbc);
	}
	

	public void ouvrirExploreur(String path) 
	{
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
				this.lblPath.setText(parcourirFichier.getSelectedFile().getAbsolutePath());
				this.imagePath = parcourirFichier.getSelectedFile();
			}
		} 
		catch (Exception exception) {exception.printStackTrace();}
	}

	public void actionPerformed(ActionEvent e) 
	{
		// Clic sur le bouton Passer
		if (e.getSource() == this.btnDefaultImage) 
		{		
			this.lblPath.setText("../images/carteUSA.png");	
			this.imagePath = new File("images/carteUSA.png");
		}
		
		if (e.getSource() == this.menuiFichierNouveau) 
		{
			this.lblPath.setText("(Fichier : \"...\")");
			this.imagePath = null;
			JOptionPane.showMessageDialog(null, this.panelNouveau);
			this.frameConcepteur.setImgMappe(this.imagePath);
		}

		String nomOs = System.getProperty("os.name").toLowerCase();

		if (e.getSource() == this.btnFichier)
		{
			
			String path = FileSystemView.getFileSystemView().getHomeDirectory().toPath().toString();

			// On ouvre directement le répertoire "Images" de l'utilisateur suivant son OS
			if (nomOs.indexOf("win") >= 0) 
			{
				path = path.replace("Desktop", "Pictures");
				ouvrirExploreur(path);
			} 
			else if (nomOs.indexOf("mac") >= 0) 
			{
				path = path.replace("Desktop", "Pictures");
				ouvrirExploreur(path);
			} 
			else if (nomOs.indexOf("nux") >= 0 || nomOs.indexOf("nix") >= 0 || nomOs.indexOf("aix") >= 0) 
			{
				if (new File(path + "/Pictures").exists())
				{
					path += "/Pictures";
				} 
				else if (new File(path + "/Images").exists())
				{
					path += "/Images";
				}
				ouvrirExploreur(path);
			} 
			else
			{
				JOptionPane.showMessageDialog(null, "Votre OS n'est pas supporté !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
