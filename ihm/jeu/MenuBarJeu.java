package ihm.jeu;

import ihm.FrameJeu;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;

import java.io.File;

public class MenuBarJeu extends JMenuBar implements ActionListener 
{
	private FrameJeu jeu;

	private File imageFile;
	private File dossierPath;
	private Image image;

	private JMenuItem menuiFichierOuvrir, menuiFichierQuitter;

	private JLabel lblPathOuvrir;
	private JPanel panelOuvrir;

	private JButton btnFichierOuvrir;

	public MenuBarJeu(FrameJeu concepteur) {
		this.jeu = concepteur;

		// Crea des composants
		this.lblPathOuvrir = new JLabel("(Dossier : \"...\")", JLabel.CENTER);

		JMenu menuFichier = new JMenu("Fichier");
		menuFichier.setMnemonic(KeyEvent.VK_F);

		this.menuiFichierOuvrir = new JMenuItem("Ouvrir");
		this.menuiFichierOuvrir.setMnemonic(KeyEvent.VK_O);

		this.menuiFichierQuitter = new JMenuItem("Quitter");
		this.menuiFichierQuitter.setMnemonic(KeyEvent.VK_F4);

		// Positionnement des composants
		menuFichier.add(this.menuiFichierOuvrir);
		menuFichier.addSeparator();
		menuFichier.add(this.menuiFichierQuitter);
		this.add(menuFichier);

		// Integration des raccourcis
		this.menuiFichierOuvrir.setAccelerator( // CTRL + O
				KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		this.menuiFichierQuitter.setAccelerator( // CTRL+F4
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_DOWN_MASK));

		this.menuiFichierOuvrir.addActionListener(this);
		this.menuiFichierQuitter.addActionListener(this);

		// Contenu du Panel ouvrir
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
		if (type.equals("Images")) {
			// On lui applique l'UI du pc de l'utilisateur
			// Ici on ouvre l'explorateur de fichiers et on lui applique des paramètres
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			JFileChooser parcourirFichier = new JFileChooser(path);
			parcourirFichier.setDialogTitle("Choisissez un fond d'écran");
			parcourirFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
			parcourirFichier.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png", "jpeg");
			parcourirFichier.addChoosableFileFilter(filter);
			parcourirFichier.setApproveButtonText("Ouvrir");

			// Après que l'utilisateur ait cliqué sur "ouvrir", on récupère le fichier
			// sélectionné

			try {
				int reponse = parcourirFichier.showOpenDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION && parcourirFichier.getSelectedFile() != null) {
					this.lblPathOuvrir.setText(parcourirFichier.getSelectedFile().getAbsolutePath());
					this.imageFile = parcourirFichier.getSelectedFile();
					this.image = getToolkit().getImage(imageFile.getAbsolutePath());
					this.jeu.getMetier().setImgMappePath(parcourirFichier.getSelectedFile().getAbsolutePath());
				}
			} catch (Exception exception) {exception.printStackTrace();}
		} else {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			JFileChooser parcourirFichier = new JFileChooser(path);
			parcourirFichier.setDialogTitle("Choisissez un fichier Mappe XML");
			parcourirFichier.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			parcourirFichier.setApproveButtonText("Ouvrir");

			// On lui applique l'UI du pc de l'utilisateur
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception exception) {exception.printStackTrace();}

			// Après que l'utilisateur ait cliqué sur "ouvrir", on récupère le fichier
			// sélectionné

			try {
				int reponse = parcourirFichier.showOpenDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION && parcourirFichier.getSelectedFile() != null) {
					this.lblPathOuvrir.setText(parcourirFichier.getSelectedFile().getAbsolutePath());
					this.dossierPath = parcourirFichier.getSelectedFile();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e) 
	{
		/* TANT QUE OUVRIR NE FONCTIONNE PAS DANS LA CONCEPTION, ON NE FAIT PAS LE OUVRIR DU JEU */
		if (e.getSource() == this.menuiFichierOuvrir) 
		{/* 
			this.dossierPath = null;
			JOptionPane.showMessageDialog(null, this.panelOuvrir);
			this.lblPathOuvrir.setText(this.dossierPath.getAbsolutePath());

			for (File file : this.dossierPath.listFiles()) {
				this.lblPathNouveau.setText(file.getAbsolutePath());
				if (file.getName().contains("imgMappe")) {
					this.jeu.setImgMappe(getToolkit().getImage(file.getAbsolutePath()));
				}
				if (file.getName().contains("Mappe.xml")) {
					this.jeu.importMappe(file);
				}
			}
			this.jeu.majIHM(); */
		}

		if (e.getSource() == this.menuiFichierQuitter) {
			this.jeu.quitter();
		}

		String nomOs = System.getProperty("os.name").toLowerCase();

		if (e.getSource() == this.btnFichierOuvrir) {
			String path = FileSystemView.getFileSystemView().getHomeDirectory().toPath().toString();

			// On ouvre directement le répertoire "Documents" de l'utilisateur
			// suivant son OS
			String directory = "";
			if (e.getSource() == this.btnFichierOuvrir) 
				directory = "Documents";
			
			if (nomOs.indexOf("win") >= 0) {
				path = path.replace("Desktop", directory);
				ouvrirExploreur(path, directory);
			} else if (nomOs.indexOf("mac") >= 0) {
				path = path.replace("Desktop", directory);
				ouvrirExploreur(path, directory);
			} else if (nomOs.indexOf("nux") >= 0 || nomOs.indexOf("nix") >= 0 || nomOs.indexOf("aix") >= 0) {
				if (new File(path + "/" + directory).exists()) {
					path += "/" + directory;
				} else if (new File(path + "/" + directory).exists()) {
					path += "/" + directory;
				}
				ouvrirExploreur(path, directory);
			} else {
				JOptionPane.showMessageDialog(null, "Votre OS n'est pas supporté !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
