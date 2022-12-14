package ihm.conception;

import ihm.Frame;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class FrameParametre extends Frame {
	
	public String getName() {
		return "Définition des Paramètres";
	}

	private JComboBox<Integer> cbNbJoueurMin;
	private JComboBox<Integer> cbNbJoueurMax;
	private JComboBox<Integer> cbNbJoueurVD;

	private JPanel panelMappe;
	private JPanel panelParam;

	public FrameParametre() {
	
		// Définition des deux panels principaux
		this.panelMappe = new JPanel();
		this.panelMappe.setPreferredSize(new Dimension((int) (this.LARGEUR * 0.7), this.HAUTEUR));

		this.panelParam = new JPanel();
		this.panelParam.setBackground(Color.LIGHT_GRAY);
		this.panelParam.setPreferredSize(new Dimension((int) (this.LARGEUR * 0.3), this.HAUTEUR));


		// Contenu du panel Param (droite)

		JLabel lblNbJoueurMin = new JLabel("Nombre de joueurs minimum : ");
		this.cbNbJoueurMin = new JComboBox<Integer>(new Integer[]{ 2, 3, 4 });
		this.cbNbJoueurMin.setBorder(null);

		JLabel lblNbJoueurMax = new JLabel("Nombre de joueurs maximum : ");
		this.cbNbJoueurMax = new JComboBox<Integer>(new Integer[]{ 3, 4, 5 });
		this.cbNbJoueurMax.setBorder(null);

		JLabel lblNBJoueurVD = new JLabel("Nombre de joueurs pour voie double : ");
		this.cbNbJoueurVD = new JComboBox<Integer>(new Integer[]{ 2, 3, 4, 5 });
		this.cbNbJoueurVD.setBorder(null);


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

		this.add(this.panelMappe, BorderLayout.WEST);
		this.add(this.panelParam, BorderLayout.EAST);
	}

}