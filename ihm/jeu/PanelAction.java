package ihm.jeu;

import ihm.FrameJeu;
import metier.*;
import ihm.jeu.util.BoutonCarteWagon;

import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;
import java.util.Map;

public class PanelAction extends JPanel implements ActionListener
{
	private FrameJeu frameJeu;
	private Pioche pioche;

	
	private int largeur;
	private int hauteur;

	private ArrayList<Joueur> alJoueurs;

	private JButton btnPioche;
	private JButton btnPossessionRoute;
	private JButton btnPiocheDesti;

	private JPanel panelHaut;
	private JPanel  panelInfo;

	private ArrayList<JLabel> alLblJoueurs;
	private ArrayList<JLabel> alLblNbWagonJoueur;
	private ArrayList<JLabel> alLblScore;
	private ArrayList<Color> alCouleur;

	private JPanel panelPopUpPioche;
	private ArrayList<JLabel> alLblCartes;
	private ArrayList<BoutonCarteWagon> alBtnCartes;
	private ArrayList<CarteWagon> alCartesCourantes;
	private BoutonCarteWagon btnNonVisible;
	private JLabel lblNonVisible;
	private CarteWagon carteCachee;

	private JPanel panelPopupPossession;
	private ArrayList<Arete> alAretes;
	private JComboBox<Arete> ddlstArete;

	private JPanel panelPopupPiocheDesti;
	private ArrayList<CarteDestination> alPiocheDesti;
	private JCheckBox cbPiocheDesti;
	private JCheckBox cbPiocheDestiDeux;
	private JCheckBox cbPiocheDestiTrois;
	private JLabel lblPiocheDestiConseil;

	
	public PanelAction(FrameJeu frameJeu, int largeur, int hauteur, ArrayList<Joueur> alJoueursMetier, Pioche pioche) 
	{
		//Init
		this.pioche = frameJeu.getMetier().getPioche();
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.alBtnCartes = new ArrayList<BoutonCarteWagon>();
		this.alJoueurs = alJoueursMetier;
		this.pioche = pioche;
		this.setLayout(new GridLayout(2, 1));

		//Contenu Panel

		this.panelHaut = new JPanel();
		this.panelHaut.setLayout(null);

		int hauteurPH = (int)(this.hauteur*0.5);

		this.btnPioche = new JButton("Pioche");
		this.btnPioche.setFocusPainted(false);
		this.btnPioche.addActionListener(this);
		this.panelHaut.add(this.btnPioche);
		this.btnPioche.setBounds(this.largeur/2 - 100, hauteurPH/4 - 25, 200, 50);
		
		this.btnPossessionRoute = new JButton("Possession de route");
		this.btnPossessionRoute.setFocusPainted(false);
		this.btnPossessionRoute.addActionListener(this);
		this.panelHaut.add(this.btnPossessionRoute);
		this.btnPossessionRoute.setBounds(this.largeur/2 - 100, hauteurPH/2 - 25, 200, 50);

		this.btnPiocheDesti = new JButton("Pioche de destination");
		this.btnPiocheDesti.setFocusPainted(false);
		this.btnPiocheDesti.addActionListener(this);
		this.panelHaut.add(this.btnPiocheDesti);
		this.btnPiocheDesti.setBounds(this.largeur/2 - 100, (int)(hauteurPH*0.75) - 25, 200, 50);



		// Panel Info
		// --------- //

		this.panelInfo = new JPanel();
		this.alLblJoueurs 		= new ArrayList<JLabel>();
		this.alLblNbWagonJoueur = new ArrayList<JLabel>();
		this.alLblScore 		= new ArrayList<JLabel>();
		this.alCouleur 		= new ArrayList<Color>();
		this.panelInfo.setLayout(null);

		this.alAretes = frameJeu.getMetier().getAlAretes();
		//this.alPiocheDesti = frameJeu.getMetier().getAlCartesDestination();

		// Paramètres Panel
		this.frameJeu = frameJeu;
		this.largeur  =  largeur;
		this.hauteur  =  hauteur;
		
		this.setPreferredSize(new Dimension(this.largeur, this.hauteur));

		//Contenu panelInfos
		JLabel lblNbPions = new JLabel("Wagons", JLabel.RIGHT);
		JLabel lblScore = new JLabel("Score", JLabel.RIGHT);
		JLabel lblJoueur = new JLabel("Joueur(s)", JLabel.LEFT);

		this.panelInfo.add(lblNbPions);
		lblNbPions.setBounds(this.largeur/3, 10, this.largeur/3, 20);

		this.panelInfo.add(lblScore);
		lblScore.setBounds((this.largeur/3)*2, 10, this.largeur/3, 20);

		this.panelInfo.add(lblJoueur);
		lblJoueur.setBounds(10, 10, this.largeur/3, 20);


		for(Joueur joueur : this.alJoueurs) {
			this.alLblJoueurs.add(		new JLabel(joueur.getNomJoueur()));
			this.alLblNbWagonJoueur.add(new JLabel(Integer.toString(joueur.getNbWagonJoueur()), JLabel.RIGHT));
			this.alLblScore.add(		new JLabel(Integer.toString(joueur.getScore()), JLabel.RIGHT));
			this.alCouleur.add(joueur.getCouleur());
		}
		
		for(int i = 0; i < this.alJoueurs.size(); i++)
		{
			this.alLblJoueurs.get(i).setBackground(this.alCouleur.get(i));
			this.alLblJoueurs.get(i).setOpaque(true);
			this.panelInfo.add(this.alLblJoueurs.get(i));
			this.panelInfo.add(this.alLblNbWagonJoueur.get(i));
			this.panelInfo.add(this.alLblScore.get(i));

			this.alLblJoueurs.get(i).setBounds(10, (40 + 15*i), this.largeur/3, 20);
			this.alLblNbWagonJoueur.get(i).setBounds(this.largeur/3, (40 + 15*i), this.largeur/3, 20);
			this.alLblScore.get(i).setBounds((this.largeur/3)*2, (40 + 15*i), this.largeur/3, 20);
		}
		//ex :label.setFont(new Font("Serif", Font.BOLD, 20));


		//Contenu panelPopupDesti

		this.panelPopupPiocheDesti = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPopUp = new GridBagConstraints();
		gbcPopUp.insets = new Insets(5, 2, 5, 2);
		this.lblPiocheDestiConseil = new JLabel("Piocher au moins une carte.");

		this.alPiocheDesti = new ArrayList<CarteDestination>();// liste des carte piocher (a remettre si il annule)
		for(int i = 0; i < 3; i++) {
			this.alPiocheDesti.add(pioche.piocherCarteDestination());
		}
		this.cbPiocheDesti      = new JCheckBox(this.alPiocheDesti.get(0).toString());
		this.cbPiocheDestiDeux  = new JCheckBox(this.alPiocheDesti.get(1).toString());
		this.cbPiocheDestiTrois = new JCheckBox(this.alPiocheDesti.get(2).toString());
		this.cbPiocheDesti.addActionListener(this);
		this.cbPiocheDestiDeux.addActionListener(this);
		this.cbPiocheDestiTrois.addActionListener(this);

		gbcPopUp.gridx = 0;
		gbcPopUp.gridy = 0;
		gbcPopUp.fill = GridBagConstraints.HORIZONTAL;
		this.panelPopupPiocheDesti.add(cbPiocheDesti, gbcPopUp);

		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 0;
		gbcPopUp.fill = GridBagConstraints.HORIZONTAL;
		this.panelPopupPiocheDesti.add(cbPiocheDestiDeux, gbcPopUp);

		gbcPopUp.gridx = 2;
		gbcPopUp.gridy = 0;
		gbcPopUp.fill = GridBagConstraints.HORIZONTAL;
		this.panelPopupPiocheDesti.add(cbPiocheDestiTrois, gbcPopUp);

		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 1;
		gbcPopUp.gridwidth = 3;
		gbcPopUp.fill = GridBagConstraints.HORIZONTAL;
		this.panelPopupPiocheDesti.add(lblPiocheDestiConseil, gbcPopUp);
		

		//Contenu panelPopupPossession
		this.panelPopupPossession = new JPanel();

		this.ddlstArete = new JComboBox<Arete>();
		this.panelPopupPossession.add(ddlstArete);



		// Contenu PanelPopupPioche
		// ------------------------ //
		this.panelPopUpPioche = new JPanel();
		this.panelPopUpPioche.setPreferredSize(new Dimension(550, 300));
		this.panelPopUpPioche.setLayout(null);

		this.alBtnCartes = new ArrayList<BoutonCarteWagon>();
		this.alCartesCourantes = new ArrayList<CarteWagon>();
		this.alLblCartes = new ArrayList<JLabel>();


		for(int i=0; i < 5; i++) {
			this.alCartesCourantes.add(pioche.piocherCarteWagon());
			this.alLblCartes.add(new JLabel(new ImageIcon(this.alCartesCourantes.get(i).getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));
			this.alBtnCartes.add(new BoutonCarteWagon(this.alCartesCourantes.get(i) ,"Piocher"));

			this.panelPopUpPioche.add(this.alLblCartes.get(i));
			this.panelPopUpPioche.add(this.alBtnCartes.get(i));
			this.alLblCartes.get(i).setBounds(25 + (i*110), 10, 70, 70);
			this.alBtnCartes.get(i).setBounds(10 + (i*110), 80, 100, 35);
			this.alBtnCartes.get(i).addActionListener(this);

			if(i == 4) {
				this.carteCachee = pioche.piocherCarteWagon();
				this.btnNonVisible = new BoutonCarteWagon(this.carteCachee, "Piocher");
				this.lblNonVisible = new JLabel(new ImageIcon());

				try {
					this.lblNonVisible.setBackground(Color.BLACK);
					this.lblNonVisible.setOpaque(true);
				} catch(Exception e) { e.printStackTrace(); }

				this.panelPopUpPioche.add(this.lblNonVisible);
				this.panelPopUpPioche.add(this.btnNonVisible);
				this.lblNonVisible.setBounds(275 - 35, 135, 70, 70);
				this.btnNonVisible.setBounds(275 - 50, 205, 100, 30);
			}
		}

		this.add(this.panelHaut);
		this.add(this.panelInfo);

	}


	public CarteWagon getCarteDispo() { return this.pioche.piocherCarteWagon(); }

	public void majIHM() 
	{
		/*  MAJ panelInfo
		for (int i = 0; i < this.alJoueurs.size(); i++)
		{
			Joueur joueur = this.alJoueurs.get(i);

			this.alLblNbWagonJoueur.get(i).setText(Integer.toString(joueur.getNbWagonJoueur()));
			this.alLblScore.get(i).setText(Integer.toString(joueur.getScore()));
		}
		*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() == this.btnPioche) {
			int ret = JOptionPane.showOptionDialog(this.frameJeu, this.panelPopUpPioche, "Pioche", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(ret != JOptionPane.OK_CANCEL_OPTION) { return; }
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
						if (areteSelectionne.getCouleurVoieSimple() != null && arete.getJoueurVoieSimple() == null && areteSelectionne.getCouleurVoieSimple() == arete.getCouleurVoieSimple())
						{
							arete.setJoueurVoieSimple(joueurActuelle);
							joueurActuelle = null; // Pour empecher le joueur de prendre une deuxieme fois si la deuxieme voie de l'arete est de la meme couleur
						}
						if (areteSelectionne.getCouleurVoieSimple() != null &&  arete.getJoueurVoieDouble() == null && areteSelectionne.getCouleurVoieSimple() == arete.getCouleurDoubleVoie())
							arete.setJoueurVoieDouble(joueurActuelle);
						/* TO DO
						 * - Gestion des cartesWagon de la main du joueur
						 * - Gestion du score du joueur
						 */
						this.frameJeu.majIHM();
						this.frameJeu.jeu("Possession");;
						break;
					}
				}
			}
		}


		if(e.getSource() == this.btnPiocheDesti) {
			if(alPiocheDesti == null || alPiocheDesti.size() == 0){
				JOptionPane.showMessageDialog(null, "Il n'y a pas de carte destination dans la pioche ", "Erreur",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				do {
					JOptionPane.showMessageDialog(this.frameJeu, this.panelPopupPiocheDesti, "Pioche", JOptionPane.OK_CANCEL_OPTION, null);

					if ( ! (cbPiocheDesti.isSelected() || cbPiocheDestiDeux.isSelected() || cbPiocheDestiTrois.isSelected()))
						JOptionPane.showMessageDialog(null, "Veuillez en selectionner au moins un. ", "Erreur",JOptionPane.ERROR_MESSAGE);
					else {
						if(this.alPiocheDesti.size() > 0) {
							if(cbPiocheDesti.isSelected()) {
								frameJeu.getMetier().getJoueurEnJeu().addCarteDestination(this.alPiocheDesti.get(0));
							}
							else pioche.addCarteDestination(this.alPiocheDesti.get(0));
						}
						
						if(this.alPiocheDesti.size() > 1) {
							if(cbPiocheDestiDeux.isSelected() ) {
								frameJeu.getMetier().getJoueurEnJeu().addCarteDestination(this.alPiocheDesti.get(1));
							}
							else pioche.addCarteDestination(this.alPiocheDesti.get(1));
						}
						
						if(this.alPiocheDesti.size() > 2) {
							if(cbPiocheDestiTrois.isSelected()) {
								frameJeu.getMetier().getJoueurEnJeu().addCarteDestination(this.alPiocheDesti.get(2));
							}
							else pioche.addCarteDestination(this.alPiocheDesti.get(2));
						}
						//refait la pioche d'après
						this.alPiocheDesti.clear();
						for (int i = 0; i < 3; i++) {
							CarteDestination carte = pioche.piocherCarteDestination();
							if (carte != null) {
								this.alPiocheDesti.add(carte);
							}
						}
					}
				} while ( ! (cbPiocheDesti.isSelected() || cbPiocheDestiDeux.isSelected() || cbPiocheDestiTrois.isSelected()) );

				this.cbPiocheDesti.setSelected(false);
				this.cbPiocheDestiDeux.setSelected(false);
				this.cbPiocheDestiTrois.setSelected(false);
				if( this.alPiocheDesti.size() > 0 )
					this.cbPiocheDesti.setText(this.alPiocheDesti.get(0).toString());

				if( this.alPiocheDesti.size() > 1 )
					this.cbPiocheDestiDeux.setText(this.alPiocheDesti.get(1).toString());
				else
					this.cbPiocheDestiDeux.setVisible(false);

				if( this.alPiocheDesti.size() > 2 )
					this.cbPiocheDestiTrois.setText(this.alPiocheDesti.get(2).toString());
				else
					this.cbPiocheDestiTrois.setVisible(false);

				this.frameJeu.jeu("Piocher Carte Destination");
				this.frameJeu.majIHM();
			}
			
		}

		if(e.getSource() == this.btnPossessionRoute) {
			
		}
	}
}