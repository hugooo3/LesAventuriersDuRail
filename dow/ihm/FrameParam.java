package dow.ihm;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;




public class FrameParam extends JFrame implements ActionListener 
{

	private JPanel panelMappe;
	private JPanel panelParam;


	private JTextfield txtNbWagon;


	public FrameParam() 
	{

		//Création de la frame 
		this.setTitle("Concepteur de mappe");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-450,
												(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-350));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2 - 50);



		//Contenue de la frame
		int[] tabNbJoueurMin = { 2, 3, 4};
		int[] tabNbJoueurMax = { 3, 4, 5};
		int[] tabNbJoueurVoieDouble = {2, 3, 4, 5};

		this.panelMappe = new JPanel();
		this.panelParam = new JPanel();
		panelParam.setLayout(new GridLayout(8,1));

		JLabel lblNbJoueurMin = new JLabel("Nombre de joueurs minimum :");
		JComboBox<int> JlstNbJoueurMin = new JComboBox<int>(tabNbJoueurMin);

		JLabel lblNbJoueurMax = new JLabel("Nombre de joueurs maxuimum :");
		JComboBox<int> JlstNbJoueurMax = new JComboBox<int>(tabNbJoueurMax);

		JLabel lblNbJoueurVoieDouble = new JLabel("Nombre de joueurs pour doubles voies :");
		JComboBox<int> JlstNbJoueurVoieDouble = new JComboBox<int>(tabNbJoueurVoieDouble);

		JLabel lblNbWagon = new JLabel("Nombre de wagons par joueur :");
		this.txtNbWagon = new JTextfield();








		//Ajout des composants

		panelParam.add(this.lblNbJoueurMin);
		panelParam.add(this.JlstNbJoueurMin);
		panelParam.add(this.lblNbJoueurMax);
		panelParam.add(this.JlstNbJoueurMax);
		panelParam.add(this.lblNbJoueurVoieDouble);
		panelParam.add(this.JlstNbJoueurVoieDouble);

		this.add(this.panelMappe, BorderLayout.WEST);
		this.add(this.panelParam, BorderLayout.EST);

		



		// Options pour la fermeture/apparence de la Frame

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}