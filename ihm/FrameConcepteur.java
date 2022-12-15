package ihm;

import javax.swing.*;
import javax.swing.filechooser.*;

import application.Application;
import ihm.conception.FrameParametre;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;

public class FrameConcepteur extends Frame implements ActionListener 
{
	private Application app;
	private File image;

	private JPanel panelContenu;

	private JButton btnFichier;
	private JButton btnEnregistrer;
	private JButton btnPasser;

	private JLabel lblPath;

	public String getNom() 
	{
		return "Concepteur de mappe";
	}

	public FrameConcepteur(Application app) 
	{
		this.app = app;

		// Contenu du Panel principal
		this.panelContenu = new JPanel();
		this.panelContenu.setSize(this.getSize());

		this.panelContenu.setBackground(Color.GRAY);
		this.panelContenu.setLayout(null);

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
		this.btnFichier.setBounds(this.getCentreH() - 125, (this.getCentreV() / 4), 250, 65);
		this.btnFichier.addActionListener(this);

		this.panelContenu.add(this.btnEnregistrer);
		this.btnEnregistrer.setBounds(this.getCentreH() - 100, this.getCentreV() - 60, 200, 50);
		this.btnEnregistrer.addActionListener(this);

		this.panelContenu.add(this.btnPasser);
		this.btnPasser.setBounds(this.LARGEUR - 500, this.HAUTEUR - 120, 400, 60);
		this.btnPasser.addActionListener(this);

		this.panelContenu.add(this.lblPath);
		this.lblPath.setBounds(this.getCentreH() - this.LARGEUR / 2, (this.getCentreV() / 4) + 65, this.LARGEUR, 40);

		this.add(this.panelContenu, BorderLayout.CENTER);
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
				this.image = parcourirFichier.getSelectedFile();
			}
		} 
		catch (Exception exception) {exception.printStackTrace();}
	}

	public void actionPerformed(ActionEvent e) 
	{
		// Clic sur le bouton Enregistrer
		if (e.getSource() == this.btnEnregistrer) 
		{
			new FrameParametre(this.app, this.image);
			this.dispose();
		}

		// Clic sur le bouton Passer
		if (e.getSource() == this.btnPasser) 
		{
			this.image = new File("images/carteUSA.png");
			new FrameParametre(this.app, this.image);
			System.out.println(this.image.getAbsolutePath());
			this.dispose();
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