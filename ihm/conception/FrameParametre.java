package ihm.conception;

import ihm.Frame;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.awt.event.*;

public class FrameParametre extends Frame implements ActionListener {

	public String getName() {
		return "Définition des Paramètres";
	}

	private JComboBox<Integer> cbNbJoueurMin;
	private JComboBox<Integer> cbNbJoueurMax;
	private JComboBox<Integer> cbNbJoueurVD;

	private JTextField txtNbWagon;
	private JButton btnSuivant;

	private PanelMappe panelMappe;
	private JPanel panelParam;

	public FrameParametre(File imagePath) {


		// Définition des deux panels principaux
		this.panelMappe = new PanelMappe(imagePath, this.LARGEUR, this.HAUTEUR);

		this.panelParam = new JPanel();
		this.panelParam.setBackground(Color.LIGHT_GRAY);
		this.panelParam.setPreferredSize(new Dimension((int) (this.LARGEUR * 0.3), this.HAUTEUR));


		// Contenu du panel Param (droite)

		JLabel lblNbJoueurMin = new JLabel("Nombre de joueurs minimum : ");
		this.cbNbJoueurMin = new JComboBox<Integer>(new Integer[] { 2, 3, 4 });
		this.cbNbJoueurMin.setBorder(null);

		JLabel lblNbJoueurMax = new JLabel("Nombre de joueurs maximum : ");
		this.cbNbJoueurMax = new JComboBox<Integer>(new Integer[] { 3, 4, 5 });
		this.cbNbJoueurMax.setBorder(null);

		JLabel lblNBJoueurVD = new JLabel("Nombre de joueurs pour voie double : ");
		this.cbNbJoueurVD = new JComboBox<Integer>(new Integer[] { 2, 3, 4, 5 });
		this.cbNbJoueurVD.setBorder(null);

		JLabel lblNbWagon = new JLabel("Nombre de wagons par joueur : ");
		this.txtNbWagon = new JTextField("40");


		this.btnSuivant = new JButton("Suivant");
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);



		this.panelParam.setLayout(null);

		this.panelParam.add(lblNbJoueurMin);
		lblNbJoueurMin.setBounds((int) (this.LARGEUR * 0.3) / 2 - 100, 20, 200, 20);
		this.panelParam.add(this.cbNbJoueurMin);
		this.cbNbJoueurMin.setBounds((int) (this.LARGEUR * 0.3) / 2 - 100, 40, 200, 20);

		this.panelParam.add(lblNbJoueurMax);
		lblNbJoueurMax.setBounds((int) (this.LARGEUR * 0.3) / 2 - 100, 80, 200, 20);
		this.panelParam.add(this.cbNbJoueurMax);
		this.cbNbJoueurMax.setBounds((int) (this.LARGEUR * 0.3) / 2 - 100, 100, 200, 20);

		this.panelParam.add(lblNBJoueurVD);
		lblNBJoueurVD.setBounds((int) (this.LARGEUR * 0.3) / 2 - 100, 140, 250, 20);
		this.panelParam.add(this.cbNbJoueurVD);
		this.cbNbJoueurVD.setBounds((int) (this.LARGEUR * 0.3) / 2 - 100, 160, 200, 20);

		this.panelParam.add(lblNbWagon);
		lblNbWagon.setBounds((int) (this.LARGEUR * 0.3) / 2 - 100, 200, 200, 20);
		this.panelParam.add(this.txtNbWagon);
		this.txtNbWagon.setBounds((int) (this.LARGEUR * 0.3) / 2 - 100, 220, 200, 20);

		this.panelParam.add(this.btnSuivant);
		this.btnSuivant.setBounds((int) (this.LARGEUR * 0.3) / 2 - 75, this.HAUTEUR - 150, 150, 50);
		

		// Ajout des panels à la frame
		this.add(this.panelMappe, BorderLayout.WEST);
		this.add(this.panelParam, BorderLayout.EAST);
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnSuivant) {
			// TODO : vérifier les données
			// TODO : passer à la frame suivante
		}
	}
}