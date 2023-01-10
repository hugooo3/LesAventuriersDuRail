package ihm.jeu;

import ihm.FrameJeu;
import metier.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.*;

import java.util.ArrayList;
import java.util.Map;

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
	private ArrayList<Arete> alAretes;
	private JComboBox<Arete> ddlstArete;

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

		this.alAretes = frameJeu.getMetier().getAlAretes();
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
/* 

		for(int i = 0; i < 3; i++)
		{
			JLabel lblTemp = new JLabel(alPiocheDesti.get( (int)(Math.random() * alPiocheDesti.size()) ).toString());
			ddlstPiocheDesti.add(lblTemp);
			ddlstPiocheDestiDouble.add(lblTemp);
		} */

		this.panelPopupPiocheDesti.add(ddlstPiocheDesti);
		this.panelPopupPiocheDesti.add(cbPiocheDestiDouble);
		this.panelPopupPiocheDesti.add(lblPiocheDestiDouble);
		this.panelPopupPiocheDesti.add(ddlstPiocheDestiDouble);
		

		//Contenu panelPopupPossession

		this.panelPopupPossession = new JPanel();

		this.ddlstArete = new JComboBox<Arete>();
		this.panelPopupPossession.add(ddlstArete);

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
			ArrayList<Arete> alAretesPopUp = new ArrayList<Arete>();
			Joueur joueurActuelle = this.frameJeu.getMetier().getJoueurEnJeu();

			Arete arete1;
			Arete arete2;
			int nbWagonArete;
			boolean NbWagonArete1Ok;
			boolean NbWagonArete2Ok;

			// Creation de l'arrayList personnalise pour le joueur courrant
			for (Arete arete : this.alAretes)
			{
				nbWagonArete = arete.getTroncons();
				arete1 = null;
				arete2 = null;
				
				NbWagonArete1Ok = false;
				NbWagonArete2Ok = false;

				// Division des voies double en voie simple
				if (arete.getVoieDouble())
				{
					arete1 = arete.getJoueurVoieSimple() == null ? new Arete(arete.getNoeud1(), arete.getNoeud2(), arete.getCouleurVoieSimple(), arete.getTroncons(), false, null) : null;
					arete2 = arete.getJoueurVoieDouble() == null ? new Arete(arete.getNoeud1(), arete.getNoeud2(), arete.getCouleurDoubleVoie(), arete.getTroncons(), false, null) : null;
				}
				else
					arete1 = arete.getJoueurVoieSimple() == null ? new Arete(arete.getNoeud1(), arete.getNoeud2(), arete.getCouleurVoieSimple(), arete.getTroncons(), false, null) : null;

				// Gestion du neutre
				if (arete1 != null && arete1.getCouleurVoieSimple().getNomCouleur().equals("Neutre"))
				{
					for (Map.Entry<CarteWagon, Integer> entry : joueurActuelle.getHmWagon().entrySet())
					{
						if (entry.getValue() >= nbWagonArete)
						{
							NbWagonArete1Ok = true;
							break;
						}
					}
				}
				else if (arete1 != null 
							&& !arete1.getCouleurVoieSimple().getNomCouleur().equals("Neutre") 
							&& (joueurActuelle.getHmWagon().get(arete1.getCouleurVoieSimple()) + joueurActuelle.getHmWagon().get(this.frameJeu.getMetier().getAlCartesWagon().get(1))) >= nbWagonArete)
					NbWagonArete1Ok = true;

				if (arete2 != null && arete2.getCouleurVoieSimple().getNomCouleur().equals("Neutre"))
				{
					for (Map.Entry<CarteWagon, Integer> entry : joueurActuelle.getHmWagon().entrySet())
					{
						if (entry.getValue() >= nbWagonArete)
						{
							NbWagonArete2Ok = true;
							break;
						}
					}
				}
				else if (arete2 != null 
							&& !arete2.getCouleurVoieSimple().getNomCouleur().equals("Neutre") 
							&& ((joueurActuelle.getHmWagon().get(arete2.getCouleurVoieSimple()) + joueurActuelle.getHmWagon().get(this.frameJeu.getMetier().getAlCartesWagon().get(1)))) >= nbWagonArete)
					NbWagonArete2Ok = true;

				if (arete1 != null && NbWagonArete1Ok)
					alAretesPopUp.add(arete1);

				if (arete2 != null && NbWagonArete2Ok)
					alAretesPopUp.add(arete2);
			}
			this.ddlstArete.setModel(new DefaultComboBoxModel<Arete>(alAretesPopUp.toArray(new Arete[alAretesPopUp.size()])));

			if(alAretesPopUp.size() == 0){
				JOptionPane.showMessageDialog(null, "Il n'y a pas d'arête possédable", "Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}

			int n = JOptionPane.showOptionDialog(this, this.panelPopupPossession, "Possession d'arete", JOptionPane.OK_CANCEL_OPTION,  JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (n == JOptionPane.OK_OPTION) // Validation
			{
				Arete areteSelectionne = (Arete) this.ddlstArete.getSelectedItem();
				for (Arete arete : this.alAretes)
				{
					if (areteSelectionne.getNoeud1().equals(arete.getNoeud1()) && areteSelectionne.getNoeud2().equals(arete.getNoeud2())) // Determination de l'arete
					{
						// Trouver si l'arete selectionne est la voie simple ou double
						if (areteSelectionne.getCouleurVoieSimple() != null && areteSelectionne.getCouleurVoieSimple() == arete.getCouleurVoieSimple())
							arete.setJoueurVoieSimple(joueurActuelle);
						if (areteSelectionne.getCouleurVoieSimple() != null && areteSelectionne.getCouleurVoieSimple() == arete.getCouleurDoubleVoie())
							arete.setJoueurVoieDouble(joueurActuelle);
						/* TO DO
						 * - Gestion des cartesWagon de la main du joueur
						 * - Gestion du score du joueur
						 */
						this.frameJeu.majIHM();
						break;
					}
				}
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