package ihm.conception;

import ihm.FrameConcepteur;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.InputStream;

public class MenuBar extends JMenuBar implements ActionListener {
	private FrameConcepteur concepteur;

	private File fileXml;
	private Image image;

	private JMenuItem menuiFichierNouveau, menuiFichierOuvrir, menuiFichierQuitter;

	private JLabel lblPathNouveau;
	private JLabel lblPathOuvrir;
	private JPanel panelNouveau;
	private JPanel panelOuvrir;

	private JButton btnFichierOuvrir, btnFichierNouveau, btnDefaultImage;

	public MenuBar(FrameConcepteur concepteur) {
		this.concepteur = concepteur;

		// Crea des composants

		this.lblPathNouveau = new JLabel("(Image : \"...\")", JLabel.CENTER);
		this.lblPathOuvrir  = new JLabel("(Fichier : \"...\")", JLabel.CENTER);

		JMenu menuFichier = new JMenu("Fichier");
		menuFichier.setMnemonic(KeyEvent.VK_F);

		this.menuiFichierNouveau = new JMenuItem("Nouveau");
		this.menuiFichierNouveau.setMnemonic(KeyEvent.VK_N);

		this.menuiFichierOuvrir = new JMenuItem("Ouvrir");
		this.menuiFichierOuvrir.setMnemonic(KeyEvent.VK_O);

		this.menuiFichierQuitter = new JMenuItem("Quitter");
		this.menuiFichierQuitter.setMnemonic(KeyEvent.VK_F4);

		// Positionnement des composants
		menuFichier.add(this.menuiFichierNouveau);
		menuFichier.add(this.menuiFichierOuvrir);
		menuFichier.addSeparator();
		menuFichier.add(this.menuiFichierQuitter);
		this.add(menuFichier);

		// Integration des raccourcis
		this.menuiFichierNouveau.setAccelerator( // CTRL + N
				KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		this.menuiFichierOuvrir.setAccelerator( // CTRL + O
				KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		this.menuiFichierQuitter.setAccelerator( // CTRL+F4
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_DOWN_MASK));

		this.menuiFichierNouveau.addActionListener(this);
		this.menuiFichierOuvrir.addActionListener(this);
		this.menuiFichierQuitter.addActionListener(this);

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

	public void ouvrirExploreur(String path, String type) {
		if (type.equals("Images")) {
			// On lui applique l'UI du pc de l'utilisateur
			// Ici on ouvre l'explorateur de fichiers et on lui applique des paramètres
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception exception) {exception.printStackTrace();}

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
					this.lblPathNouveau.setText(parcourirFichier.getSelectedFile().getAbsolutePath());
					File imageFile = parcourirFichier.getSelectedFile();
					this.image = getToolkit().getImage(imageFile.getAbsolutePath());
					this.concepteur.getMetier().setImgMappePath(parcourirFichier.getSelectedFile().getAbsolutePath());
				}
			} catch (Exception exception) {exception.printStackTrace();}
		} else {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception exception) {exception.printStackTrace();}

			JFileChooser parcourirFichier = new JFileChooser(path);
			parcourirFichier.setDialogTitle("Choisissez un fichier Mappe XML");
			parcourirFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
			parcourirFichier.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
			parcourirFichier.addChoosableFileFilter(filter);
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
					this.fileXml = parcourirFichier.getSelectedFile();
				}
			} catch (Exception exception) {exception.printStackTrace();}
		}
	}

	public void actionPerformed(ActionEvent e) {
		// Clic sur le bouton Passer
		if (e.getSource() == this.btnDefaultImage) {
			try {
				this.lblPathNouveau.setText("../images/carteUSA.png");
				InputStream inputStream = MenuBar.class.getResourceAsStream("/images/carteUSA.png");
				this.image = ImageIO.read(inputStream);
			} catch (Exception e1) {e1.printStackTrace();}
		}

		if (e.getSource() == this.menuiFichierNouveau) {
			Image imgBackup = this.image; // Au cas ou l'utilisateur annule la creation d'une nouvelle carte (on remet l'image d'origine)
			String imgPathBackup = this.concepteur.getMetier().getImgMappePath();

			int reponse = JOptionPane.showConfirmDialog(null, this.panelNouveau, "Sélection  d'une image de fond", JOptionPane.OK_CANCEL_OPTION);

			if (reponse == JOptionPane.OK_OPTION && this.image != null)
			{
				this.menuiFichierNouveau.setEnabled(false);
				this.menuiFichierOuvrir.setEnabled(false);
				this.concepteur.setImgMappe(this.image);
			}
			else {
				this.image = imgBackup;
				this.concepteur.getMetier().setImgMappePath(imgPathBackup);
				this.lblPathNouveau.setText(imgPathBackup);
			}
		}

		if (e.getSource() == this.menuiFichierOuvrir) {
			this.fileXml = null;
			int reponse = JOptionPane.showConfirmDialog(null, this.panelOuvrir, "Sélection  d'un fichier XML", JOptionPane.OK_CANCEL_OPTION);

			if (reponse == JOptionPane.OK_OPTION && this.fileXml != null)
			{
				this.concepteur.importMappe(this.fileXml);
				this.concepteur.setImgMappe(this.concepteur.getMetier().getImgMappe());
				this.concepteur.majIHM();
				this.menuiFichierNouveau.setEnabled(false);
				this.menuiFichierOuvrir.setEnabled(false);
			}
		}

		if (e.getSource() == this.menuiFichierQuitter) {
			this.concepteur.quitter();
		}

		String nomOs = System.getProperty("os.name").toLowerCase();

		if (e.getSource() == this.btnFichierNouveau || e.getSource() == this.btnFichierOuvrir) {
			String path = FileSystemView.getFileSystemView().getHomeDirectory().toPath().toString();

			// On ouvre directement le répertoire "Images" ou "Documents" de l'utilisateur
			// suivant son OS
			String directory = "";
			if (e.getSource() == this.btnFichierNouveau) {
				directory = "Images";
			} else if (e.getSource() == this.btnFichierOuvrir) {
				directory = "Documents";
			}

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
