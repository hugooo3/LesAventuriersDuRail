package ihm.jeu;

import ihm.FrameJeu;
import metier.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

import java.util.ArrayList;

public class PanelAction extends JPanel implements ActionListener
{
	private FrameJeu frameJeu;

	
	private int largeur;
	private int hauteur;

	private JButton btnPioche;
	private JButton btnPossessionRoute;
	private JButton btnPiocheDesti;

	private JPanel panelPopUpPioche;
	private ArrayList<JButton> alBtnVisible;
	private JButton btnPiocheNonVisible;

	private JPanel panelPopupPossession;
	private ArrayList<Arete> alAretesPossession;
	private JComboBox<Arete> ddlstArete;
	private JButton btnPrendrePossession;

	private JPanel panelPopupPiocheDesti;
	private ArrayList<CarteDestination> alPiocheDesti;
	private JComboBox<CarteDestination> ddlstPiocheDesti;
	private JComboBox<CarteDestination> ddlstPiocheDestiDouble;
	private JCheckBox cbPiocheDestiDouble;
	private JLabel lblPiocheDestiDouble;

	
	public PanelAction(FrameJeu frameJeu, int largeur, int hauteur) 
	{
		//Init
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.alPiocheDesti = new ArrayList<CarteDestination>();
		this.alBtnVisible = new ArrayList<JButton>();


		//Contenu Panel SCRU PAPA

		this.btnPioche = new JButton("Pioche");
		this.btnPioche.setFocusPainted(false);
		this.btnPioche.addActionListener(this);
		this.btnPioche.setBounds(this.largeur/2 -100, this.hauteur/4 -50, 200, 50);
		this.add(this.btnPioche);
		
		this.btnPossessionRoute = new JButton("Possession de route");
		this.btnPossessionRoute.setFocusPainted(false);
		this.btnPossessionRoute.addActionListener(this);
		this.btnPossessionRoute.setBounds(this.largeur/2 -100, this.hauteur/2 -50, 200, 50);
		this.add(this.btnPossessionRoute);

		this.btnPiocheDesti = new JButton("Pioche de destination");
		this.btnPiocheDesti.setFocusPainted(false);
		this.btnPiocheDesti.addActionListener(this);
		this.btnPiocheDesti.setBounds(this.largeur/2 -100, (int)(this.hauteur*0.75) -50, 200, 50);
		this.add(this.btnPiocheDesti);

		this.alAretesPossession = frameJeu.getMetier().getAlAretes();
		this.alPiocheDesti = frameJeu.getMetier().getAlCartesDestination();

		// Paramètres Panel
		this.frameJeu = frameJeu;
		this.largeur  =  largeur;
		this.hauteur  =  hauteur;
		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(this.largeur, this.hauteur));

		
		//Contenu panelPopupDesti




		this.panelPopupPiocheDesti = new JPanel(new GridLayout(2,3));
		this.cbPiocheDestiDouble = new JCheckBox();
		this.lblPiocheDestiDouble = new JLabel("Piocher 2 cartes");

		this.alPiocheDesti.toArray(new CarteDestination[this.alPiocheDesti.size()]);
		this.ddlstPiocheDesti = new JComboBox<CarteDestination>();
		this.ddlstPiocheDestiDouble = new JComboBox<CarteDestination>();


		for(int i = 0; i < 3; i++)
		{
			JLabel lblTemp = new JLabel(alPiocheDesti.get( (int)(Math.random() * alPiocheDesti.size()) ).toString());
			ddlstPiocheDesti.add(lblTemp);
			ddlstPiocheDestiDouble.add(lblTemp);
		}

		this.panelPopupPiocheDesti.add(ddlstPiocheDesti);
		this.panelPopupPiocheDesti.add(cbPiocheDestiDouble);
		this.panelPopupPiocheDesti.add(lblPiocheDestiDouble);
		this.panelPopupPiocheDesti.add(ddlstPiocheDestiDouble);
		

		//Contenu panelPopupPossession

		this.panelPopupPossession = new JPanel();

		this.ddlstArete = new JComboBox<Arete>(this.alAretesPossession.toArray(new Arete[this.alAretesPossession.size()]));
		this.panelPopupPossession.add(ddlstArete);
		this.btnPrendrePossession = new JButton("Prendre possession");
		this.panelPopupPossession.add(btnPrendrePossession);

		//Contenu PanelPopupPioche
		
		this.panelPopUpPioche = new JPanel();

		for(int i=0; i < 5; i++) {
			JButton btnTemp = new JButton();
			btnTemp.setFocusPainted(false);
			btnTemp.addActionListener(this);
			this.panelPopUpPioche.add(btnTemp);
			this.alBtnVisible.add(btnTemp);
		}



		this.btnPiocheNonVisible= new JButton();
		this.btnPioche.setFocusPainted(false);
		this.btnPioche.addActionListener(this);
		this.panelPopUpPioche.add(btnPiocheNonVisible);


		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() == this.btnPioche) {
			int n = JOptionPane.showConfirmDialog(null, this.panelPopUpPioche, "Pioche", JOptionPane.OK_CANCEL_OPTION);
			
			if (n != JOptionPane.OK_OPTION) // Cancel ou croix == annulation
				return;
		}


		if(e.getSource() == this.btnPossessionRoute) {
			if(alAretesPossession == null){
				JOptionPane.showMessageDialog(null, "Il n'y a pas d'arete ", "Erreur",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, this.panelPopupPossession, "Possession d'arete", JOptionPane.OK_CANCEL_OPTION);
			}
		}


		if(e.getSource() == this.btnPiocheDesti) {
			if(alPiocheDesti == null){
				JOptionPane.showMessageDialog(null, "Il n'y a pas de carte destination dans la pioche ", "Erreur",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				int n = JOptionPane.showConfirmDialog(null, this.panelPopupPiocheDesti, "Pioche", JOptionPane.OK_CANCEL_OPTION);

				if (n != JOptionPane.OK_OPTION) // Cancel ou croix == annulation
					return;
			}
		}

		if(e.getSource() == this.btnPossessionRoute) {
			
		}
	}
}