package ihm.conception;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.io.File;
import java.awt.event.*;

public class PanelParam extends JPanel implements ActionListener {
	private File imagePathParam;
	private JButton btnSuivant;

	private JComboBox<Integer> cbNbJoueurMin;
	private JComboBox<Integer> cbNbJoueurMax;
	private JComboBox<Integer> cbNbJoueurVD;

	private JTextField txtNbWagon;

	private FrameParametre frame;

	public PanelParam(FrameParametre frame, File imagePath, int largeur, int hauteur) {
		this.frame = frame;
		this.imagePathParam = imagePath;
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

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
		this.btnSuivant.addActionListener(this);
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);

		this.setLayout(null);

		this.add(lblNbJoueurMin);
		lblNbJoueurMin.setBounds((int) (largeur * 0.3) / 2 - 100, 20, 200, 20);
		this.add(this.cbNbJoueurMin);
		this.cbNbJoueurMin.setBounds((int) (largeur * 0.3) / 2 - 100, 40, 200, 20);

		this.add(lblNbJoueurMax);
		lblNbJoueurMax.setBounds((int) (largeur * 0.3) / 2 - 100, 80, 200, 20);
		this.add(this.cbNbJoueurMax);
		this.cbNbJoueurMax.setBounds((int) (largeur * 0.3) / 2 - 100, 100, 200, 20);

		this.add(lblNBJoueurVD);
		lblNBJoueurVD.setBounds((int) (largeur * 0.3) / 2 - 100, 140, 250, 20);
		this.add(this.cbNbJoueurVD);
		this.cbNbJoueurVD.setBounds((int) (largeur * 0.3) / 2 - 100, 160, 200, 20);

		this.add(lblNbWagon);
		lblNbWagon.setBounds((int) (largeur * 0.3) / 2 - 100, 200, 200, 20);
		this.add(this.txtNbWagon);
		this.txtNbWagon.setBounds((int) (largeur * 0.3) / 2 - 100, 220, 200, 20);

		this.add(this.btnSuivant);
		this.btnSuivant.setBounds((int) (largeur * 0.3) / 2 - 75, hauteur - 150, 150, 50);
	}

	public void actionPerformed(ActionEvent e) {

		// Clic sur le bouton Suivant
		if (e.getSource() == this.btnSuivant) {
			new FrameNoeud(this.imagePathParam);
			this.frame.dispose();
		}
	}
}
