package ihm;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;


public class PanelConcepteur extends JFrame implements ActionListener {

	private File image;

	private JPanel panelContenu;

	private JButton btnFichier;
	private JButton btnEnregistrer;
	private JButton btnPasser;

	private JLabel lblPath;


	public PanelConcepteur() {

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

		int centreH = (int) this.panelContenu.getSize().getWidth() / 2; // Ces deux paramètres serviront à placer proprement les éléments sur notre panel
		int centreV = (int) this.panelContenu.getSize().getHeight() / 2;


		this.btnFichier = new JButton("Parcourir les fichiers");
		this.btnFichier.setBackground(Color.LIGHT_GRAY);
		this.btnFichier.setBorderPainted(false);

		this.lblPath = new JLabel("(Fichier : \"...\")", JLabel.CENTER);
		

		this.panelContenu.add(this.btnFichier);
		this.btnFichier.setBounds(centreH - 125, (centreV / 4), 250, 50);
		this.btnFichier.addActionListener(this);

		this.panelContenu.add(this.lblPath);
		this.lblPath.setBounds(centreH -125, (centreV / 4)+50, 250, 40);

		this.add(this.panelContenu, BorderLayout.CENTER);

		// Options pour la fermeture/apparence de la Frame

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void ouvrirExploreur(String path) {

		// Ici on ouvre l'explorateur de fichiers et on lui applique des paramètres

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception exception) { exception.printStackTrace(); }

		JFileChooser parcourirFichier = new JFileChooser(path);
		parcourirFichier.setDialogTitle("Choisissez un fond d'écran");
		parcourirFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		parcourirFichier.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png", "jpeg");
		parcourirFichier.addChoosableFileFilter(filter);
		parcourirFichier.setApproveButtonText("Ouvrir");


		// On lui applique l'UI du pc de l'utilisateur
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch(Exception exception) { exception.printStackTrace(); }


		// Après que l'utilisateur ait cliqué sur "ouvrir"

		try {
			int reponse = parcourirFichier.showOpenDialog(null);
			if(reponse == JFileChooser.APPROVE_OPTION && parcourirFichier.getSelectedFile() != null) {
				this.lblPath.setText(parcourirFichier.getSelectedFile().getAbsolutePath());
				this.image = parcourirFichier.getSelectedFile();
			}
		} catch(Exception exception) { exception.printStackTrace(); }



		
	}


	public void actionPerformed(ActionEvent e) {
		
		String nomOs = System.getProperty("os.name").toLowerCase();
		
		if(e.getSource() == this.btnFichier) {

			String path = FileSystemView.getFileSystemView().getHomeDirectory().toPath().toString();;

			// On ouvre directement le répertoire "Images" de l'utilisateur suivant son OS
			if(nomOs.indexOf("truc") >= 0) { 
				path = path.replace("Desktop", "Pictures");
				ouvrirExploreur(path);
			} else if(nomOs.indexOf("mac") >= 0) { 
				path = path.replace("Desktop", "Pictures");
				ouvrirExploreur(path);
			} else if(nomOs.indexOf("nux") >= 0 || nomOs.indexOf("nix") >= 0 || nomOs.indexOf("aix") >= 0) { 
				
				if(new File(path + "/Pictures").exists()) {
					path += "/Pictures";
				} else if(new File(path + "/Images").exists()) {
					path += "/Images";
				}


			} else { JOptionPane.showMessageDialog(null, "Votre OS n'est pas supporté !", "Erreur", JOptionPane.ERROR_MESSAGE); }
		}
	}
}