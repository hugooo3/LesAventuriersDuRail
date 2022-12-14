package dow.ihm;

import dow.Controleur;

import javax.swing.*;

import java.awt.GridLayout;

public class PanelParam extends JPanel 
{
	private Controleur ctrl;

	private JComboBox<Integer> JlstNbJoueurVoieDouble;
	private JComboBox<Integer> JlstNbJoueurMax;
	private JComboBox<Integer> JlstNbJoueurMin;
	private JTextField txtNbWagon;

	public PanelParam() 
	{

		// Contenue du Panel
		Integer[] tabNbJoueurMin = { 2, 3, 4 };
		Integer[] tabNbJoueurMax = { 3, 4, 5 };
		Integer[] tabNbJoueurVoieDouble = { 2, 3, 4, 5 };

		this.setLayout(new GridLayout(8, 1));

		JLabel lblNbJoueurMin = new JLabel("Nombre de joueurs minimum :");
		this.JlstNbJoueurMin = new JComboBox<Integer>(tabNbJoueurMin);

		JLabel lblNbJoueurMax = new JLabel("Nombre de joueurs maxuimum :");
		this.JlstNbJoueurMax = new JComboBox<Integer>(tabNbJoueurMax);

		JLabel lblNbJoueurVoieDouble = new JLabel("Nombre de joueurs pour doubles voies :");
		this.JlstNbJoueurVoieDouble = new JComboBox<Integer>(tabNbJoueurVoieDouble);

		JLabel lblNbWagon = new JLabel("Nombre de wagons par joueur :");
		this.txtNbWagon = new JTextField();

		// Ajout des composants

		this.add(lblNbJoueurMin);
		this.add(this.JlstNbJoueurMin);
		this.add(lblNbJoueurMax);
		this.add(this.JlstNbJoueurMax);
		this.add(lblNbJoueurVoieDouble);
		this.add(this.JlstNbJoueurVoieDouble);
		this.add(lblNbWagon);
		this.add(this.txtNbWagon);
	}
}
