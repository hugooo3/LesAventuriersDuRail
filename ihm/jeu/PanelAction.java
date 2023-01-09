package ihm.jeu;

import ihm.FrameJeu;
import metier.Arete;

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


	private Object concepteur;
	
	public PanelAction(FrameJeu frameJeu, int largeur, int hauteur) 
	{


		this.alAretesPossession = ((FrameJeu) this.concepteur).getMetier().getAlAretes();

		// Paramètres Panel
		this.frameJeu = frameJeu;
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(this.largeur, this.hauteur));

		

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


		//Contenu Panel SCRU PAPA

		this.btnPioche = new JButton("Pioche");
		this.btnPioche.setFocusPainted(false);
		this.btnPioche.addActionListener(this);
		this.add(this.btnPioche);
		

		this.btnPossessionRoute = new JButton("Possession de route");
		this.btnPossessionRoute.setFocusPainted(false);
		this.btnPossessionRoute.addActionListener(this);
		this.add(this.btnPossessionRoute);

		this.btnPiocheDesti = new JButton("Pioche de destination");
		this.btnPiocheDesti.setFocusPainted(false);
		this.btnPiocheDesti.addActionListener(this);
		this.add(this.btnPiocheDesti);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() == this.btnPioche) {
			JOptionPane.showMessageDialog(this, this.panelPopUpPioche, "Pioche", JOptionPane.OK_CANCEL_OPTION);
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
			
		}

		if(e.getSource() == this.btnPossessionRoute) {
			
		}
	}
}