package ihm.conception;

import ihm.FrameConcepteur;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;

public class PanelParam extends JPanel 
{
	private JComboBox<Integer> cbNbJoueurMin;
	private JComboBox<Integer> cbNbJoueurMax;
	private JComboBox<Integer> cbNbJoueurVD;

	private JTextField txtNbWagon;
	private JTextField txtNbFin;
	private FrameConcepteur concepteur;

	public PanelParam(FrameConcepteur concepteur, int largeur, int hauteur)
	{
		this.concepteur = concepteur;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

		// Contenu du panel Param 

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

		JLabel lblNbFin = new JLabel("Nb wagons restant avant fin de partie : ");
		this.txtNbFin = new JTextField("3");

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

		this.add(lblNbFin);
		lblNbFin.setBounds((int) (largeur * 0.3) / 2 - 100, 260, 250, 20);
		this.add(this.txtNbFin);
		this.txtNbFin.setBounds((int) (largeur * 0.3) / 2 - 100, 280, 200, 20);
	}

	public void setParam() 
	{
		this.concepteur.setNbJoueurMin((int) this.cbNbJoueurMin.getSelectedItem());
		this.concepteur.setNbJoueurMax((int) this.cbNbJoueurMax.getSelectedItem());
		this.concepteur.setNbJoueurDoubleVoies((int) this.cbNbJoueurVD.getSelectedItem());
		this.concepteur.setNbWagonJoueur(Integer.parseInt(this.txtNbWagon.getText() + ""));
		this.concepteur.setNbFin(Integer.parseInt(this.txtNbFin.getText() + ""));
	}
}
