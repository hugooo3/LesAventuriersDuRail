package ihm.jeu;

import ihm.FrameJeu;
import metier.*;
import ihm.jeu.util.BoutonCarteWagon;

import java.awt.event.*;
import java.io.File;
import java.security.cert.TrustAnchor;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;
import java.util.Map;

public class PanelAction extends JPanel implements ActionListener
{
	private FrameJeu frameJeu;
	private Pioche pioche;

	private Boolean finDePartie;

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

	private ArrayList<CarteWagon> alCartesCourantes;

	private PanelPioche panelPioche;

	private JPanel panelPopupPossession;
	private ArrayList<Arete> alAretes;
	private JComboBox<Arete> ddlstArete;

	private JPanel panelPopupPiocheDesti;
	private ArrayList<CarteDestination> alPiocheDesti;
	private JCheckBox cbPiocheDesti;
	private JCheckBox cbPiocheDestiDeux;
	private JCheckBox cbPiocheDestiTrois;
	private JLabel lblPiocheDestiConseil;
	private JPanel panelPopupNeutre;
	private JComboBox<CarteWagon> ddlstNeutreArete;
	private JLabel lblPopupNeutreArete;

	private JList<String> lstLog;
	private JScrollPane   scrollPane;
	private DefaultListModel<String> modelListLogs;

	
	public PanelAction(FrameJeu frameJeu, int largeur, int hauteur, ArrayList<Joueur> alJoueursMetier, Pioche pioche) 
	{
		//Init
		this.pioche = frameJeu.getMetier().getPioche();
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.alJoueurs = alJoueursMetier;
		this.pioche = pioche;
		this.finDePartie = false;
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
		this.alCouleur 			= new ArrayList<Color>();
		this.scrollPane			= new JScrollPane();
		this.panelInfo.setLayout(null);

		this.alAretes = frameJeu.getMetier().getAlAretes();

		// Paramètres Panel
		this.frameJeu = frameJeu;
		this.largeur  = largeur;
		this.hauteur  = hauteur;
		
		this.modelListLogs = new DefaultListModel<String>();
		this.lstLog        = new JList<String>(this.modelListLogs);
		ArrayList<String> alLogs = frameJeu.getMetier().getAlLogs();
		for (String logs : alLogs)
			this.modelListLogs.addElement(logs);
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

		this.scrollPane.setViewportView(lstLog);
		this.panelInfo.add(scrollPane);
		scrollPane.setBounds((this.largeur/2)-100, 100, 200, 220);
		this.lstLog.ensureIndexIsVisible(this.modelListLogs.getSize()-1);
		//lstLog.setFont(new Font(getFont().getName(), Font.PLAIN, 15));


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


		//Contenu panelPopupDesti

		this.panelPopupPiocheDesti = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPopUp = new GridBagConstraints();
		gbcPopUp.insets = new Insets(5, 2, 5, 2);
		this.lblPiocheDestiConseil = new JLabel("Piocher au moins une carte.");

		ArrayList<CarteDestination> alDestiVerif = frameJeu.getMetier().getAlCartesDestination();
		this.alPiocheDesti = new ArrayList<CarteDestination>();// liste des carte piocher (a remettre si il annule)
		for(int i = 0; i < 3; i++) {
			CarteDestination carte = pioche.piocherCarteDestination();
			if (carte != null) {
				this.alPiocheDesti.add(carte);
			}
		}

		if( this.alPiocheDesti.size() > 0) {
			this.cbPiocheDesti      = new JCheckBox(this.alPiocheDesti.get(0).toString()); this.cbPiocheDesti.addActionListener(this);
		}
		else this.cbPiocheDesti      = new JCheckBox();

		
		if (this.alPiocheDesti.size() > 1) {
			this.cbPiocheDestiDeux  = new JCheckBox(this.alPiocheDesti.get(1).toString());this.cbPiocheDestiDeux.addActionListener(this);
			
		}
		else this.cbPiocheDestiDeux  = new JCheckBox();
			
		if (this.alPiocheDesti.size() > 2) {
			this.cbPiocheDestiTrois = new JCheckBox(this.alPiocheDesti.get(2).toString());this.cbPiocheDestiTrois.addActionListener(this);
			
		}
		else this.cbPiocheDestiTrois = new JCheckBox();
		this.cbPiocheDesti.addActionListener(this);
		this.cbPiocheDestiDeux.addActionListener(this);
		this.cbPiocheDestiTrois.addActionListener(this);
		
		
		

		gbcPopUp.gridx = 0;
		gbcPopUp.gridy = 0;
		gbcPopUp.fill = GridBagConstraints.HORIZONTAL;
		if( this.alPiocheDesti.size() > 0) {
			System.err.println("UN"+ this.alPiocheDesti.size());
			this.panelPopupPiocheDesti.add(cbPiocheDesti, gbcPopUp);
		}

		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 0;
		gbcPopUp.fill = GridBagConstraints.HORIZONTAL;
		if (this.alPiocheDesti.size() > 1 ) {
			System.err.println("DEUX");
			this.panelPopupPiocheDesti.add(cbPiocheDestiDeux, gbcPopUp);
		}

		gbcPopUp.gridx = 2;
		gbcPopUp.gridy = 0;
		gbcPopUp.fill = GridBagConstraints.HORIZONTAL;
		if (this.alPiocheDesti.size() > 2) {
			System.err.println("TROIS");
			this.panelPopupPiocheDesti.add(cbPiocheDestiTrois, gbcPopUp);
		}

		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 1;
		gbcPopUp.gridwidth = 3;
		gbcPopUp.fill = GridBagConstraints.HORIZONTAL;
		this.panelPopupPiocheDesti.add(lblPiocheDestiConseil, gbcPopUp);
		

		//Contenu panelPopupPossession
		this.panelPopupPossession = new JPanel();

		this.ddlstArete = new JComboBox<Arete>();
		this.panelPopupPossession.add(ddlstArete);

		//Contenu popUpNeutre dans la possession
		//Contenu panelPopupNeutre
		this.panelPopupNeutre = new JPanel();

		this.lblPopupNeutreArete = new JLabel();
		this.ddlstNeutreArete = new JComboBox<CarteWagon>();
		panelPopupNeutre.add(this.lblPopupNeutreArete);
		panelPopupNeutre.add(this.ddlstNeutreArete);

		// Ajout des composants
		this.add(this.panelHaut);
		this.add(this.panelInfo);

		this.alCartesCourantes = new ArrayList<CarteWagon>();
		for(int i=0; i < 6; i++) {
			this.alCartesCourantes.add(this.getCarteDispo());
		}

	}


	public ArrayList<CarteWagon> getAlCartesCourantes() { return this.alCartesCourantes; }

	public void enleverCarteWagon(CarteWagon carte, int index) {
		this.alCartesCourantes.set(index, this.getCarteDispo());
	}



	public CarteWagon getCarteDispo() { return this.pioche.piocherCarteWagon(); }

	public void majIHM() {
		for (int i = 0; i < this.alJoueurs.size(); i++) {
			Joueur joueur = this.alJoueurs.get(i);

			this.alLblNbWagonJoueur.get(i).setText(Integer.toString(joueur.getNbWagonJoueur()));
			this.alLblScore.get(i).setText(Integer.toString(joueur.getScore()));
		}
		this.modelListLogs.clear();
		ArrayList<String> alLogs = frameJeu.getMetier().getAlLogs();
		for (String logs : alLogs)
			this.modelListLogs.addElement(logs);
	}

	
	public void reactiverBoutons() { 
		this.btnPioche.setEnabled(true);
		this.btnPossessionRoute.setEnabled(true);
		this.btnPiocheDesti.setEnabled(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == this.btnPioche || e.getSource() == this.btnPossessionRoute || e.getSource() == this.btnPiocheDesti) {
			this.btnPioche.setEnabled(false);
			this.btnPossessionRoute.setEnabled(false);
			this.btnPiocheDesti.setEnabled(false);
		}

		if(e.getSource() == this.btnPioche) {

			int nbCartes = 0;
			for(CarteWagon carte : this.alCartesCourantes) {
				if(carte != null) 
					nbCartes += carte.getNbCarteWagon();
			}

			System.out.println(nbCartes);

			if(nbCartes > 5) { 
				this.panelPioche = new PanelPioche(frameJeu, this, largeur, hauteur, pioche, this.alCartesCourantes);
				this.panelPioche.addWindowListener(new WindowAdapter(){
						@Override
						public void windowDeactivated(WindowEvent e) {
							if(!e.getWindow().isVisible()) { PanelAction.this.reactiverBoutons(); }
						}
					});
			}
			else { 
				JOptionPane.showMessageDialog(this.frameJeu, "Nombre insuffisant de cartes dans la Pioche !");
				this.reactiverBoutons(); 
			}
		}


		if(e.getSource() == this.btnPossessionRoute) {
			ArrayList<Arete> alAretesPopUp = new ArrayList<Arete>();
			Joueur joueurActuel = this.frameJeu.getMetier().getJoueurEnJeu();

			Arete arete1;
			Arete arete2;
			int nbWagonArete = 0;
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
				if (joueurActuel.getNbWagonJoueur() < nbWagonArete)
					continue;

				// Division des voies double en voie simple
				if (arete.getVoieDouble())
				{
					arete1 = arete.getJoueurVoieSimple() == null ? new Arete(arete.getNoeud1(), arete.getNoeud2(), arete.getCouleurVoieSimple(), arete.getTroncons(), false, null) : null;
					arete2 = arete.getJoueurVoieDouble() == null ? new Arete(arete.getNoeud1(), arete.getNoeud2(), arete.getCouleurDoubleVoie(), arete.getTroncons(), false, null) : null;
				}
				else
					arete1 = arete.getJoueurVoieSimple() == null ? new Arete(arete.getNoeud1(), arete.getNoeud2(), arete.getCouleurVoieSimple(), arete.getTroncons(), false, null) : null;

				// Gestion du neutre
				if (arete1 != null && arete1.getCouleurVoieSimple().getNomCouleur().equals("Neutre")) {
					for (Map.Entry<CarteWagon, Integer> entry : joueurActuel.getHmWagon().entrySet()) {
						if (entry.getKey().getNomCouleur().equals("Joker") && entry.getValue() >= nbWagonArete) {
							NbWagonArete1Ok = true;
							break;
						}
						else if (entry.getValue() + joueurActuel.getHmWagon().get(this.frameJeu.getMetier().getAlCartesWagon().get(1)) >= nbWagonArete) {
							NbWagonArete1Ok = true;
							break;
						}
					}
				}
				else if (arete1 != null 
							&& !arete1.getCouleurVoieSimple().getNomCouleur().equals("Neutre") 
							&& (joueurActuel.getHmWagon().get(arete1.getCouleurVoieSimple()) + joueurActuel.getHmWagon().get(this.frameJeu.getMetier().getAlCartesWagon().get(1))) >= nbWagonArete)
					NbWagonArete1Ok = true;

				if (arete2 != null && arete2.getCouleurVoieSimple().getNomCouleur().equals("Neutre")) {
					for (Map.Entry<CarteWagon, Integer> entry : joueurActuel.getHmWagon().entrySet()) {
						if (entry.getKey().getNomCouleur().equals("Joker") && entry.getValue() >= nbWagonArete)	{
							NbWagonArete1Ok = true;
							break;
						}
						else if (entry.getValue() + joueurActuel.getHmWagon().get(this.frameJeu.getMetier().getAlCartesWagon().get(1)) >= nbWagonArete) {
							NbWagonArete2Ok = true;
							break;
						}
					}
				}
				else if (arete2 != null 
							&& !arete2.getCouleurVoieSimple().getNomCouleur().equals("Neutre") 
							&& ((joueurActuel.getHmWagon().get(arete2.getCouleurVoieSimple()) + joueurActuel.getHmWagon().get(this.frameJeu.getMetier().getAlCartesWagon().get(1)))) >= nbWagonArete)
					NbWagonArete2Ok = true;

				if (arete1 != null && NbWagonArete1Ok)
					alAretesPopUp.add(arete1);

				if (arete2 != null && NbWagonArete2Ok)
					alAretesPopUp.add(arete2);
			}
			alAretesPopUp.sort(null);
			this.ddlstArete.setModel(new DefaultComboBoxModel<Arete>(alAretesPopUp.toArray(new Arete[alAretesPopUp.size()])));

			if(alAretesPopUp.size() == 0){
				JOptionPane.showMessageDialog(null, "Il n'y a pas d'arête possédable", "Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}

			int n1 = JOptionPane.showOptionDialog(this, this.panelPopupPossession, "Possession d'arete", JOptionPane.OK_CANCEL_OPTION,  JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (n1 == JOptionPane.OK_OPTION) { // Validation
				Arete areteSelectionne = (Arete) this.ddlstArete.getSelectedItem();
				for (Arete arete : this.alAretes) {
					nbWagonArete = arete.getTroncons();
					if (areteSelectionne.getNoeud1().equals(arete.getNoeud1()) && areteSelectionne.getNoeud2().equals(arete.getNoeud2())) { // Determination de l'arete
						// Trouver si l'arete selectionne est la voie simple ou double
						if (areteSelectionne.getCouleurVoieSimple() != null && arete.getJoueurVoieSimple() == null && areteSelectionne.getCouleurVoieSimple() == arete.getCouleurVoieSimple()) {
							arete.setJoueurVoieSimple(joueurActuel);
							joueurActuel.addArete(arete);
						}
						else if (areteSelectionne.getCouleurVoieSimple() != null &&  arete.getJoueurVoieDouble() == null && areteSelectionne.getCouleurVoieSimple() == arete.getCouleurDoubleVoie()) {
							arete.setJoueurVoieDouble(joueurActuel);
							joueurActuel.addArete(arete);
						}
						
						CarteWagon couleurTerritoire = arete.getCouleurVoieSimple();
						// Retirer les cartes wagons de la main du joueur
				

 						// Cas du neutre
						if (arete.getCouleurVoieSimple().getNomCouleur().equals("Neutre")) {
							// creation d'une arrayList de couleur de wagon pour lui dire avec quelle couleur il peut prendre l'arete
							ArrayList<CarteWagon> alCouleurWagonPopUpNeutre = new ArrayList<CarteWagon>();
							for (Map.Entry<CarteWagon, Integer> entry : joueurActuel.getHmWagon().entrySet()) {
								if (entry.getKey().getNomCouleur().equals("Joker") && entry.getValue() >= nbWagonArete)	{
									alCouleurWagonPopUpNeutre.add(entry.getKey());
								}
								else if ((!entry.getKey().getNomCouleur().equals("Joker") && entry.getValue() != 0 && (entry.getValue() + joueurActuel.getHmWagon().get(this.frameJeu.getMetier().getAlCartesWagon().get(1))) >= nbWagonArete)) {
									alCouleurWagonPopUpNeutre.add(entry.getKey());
								}
							}
							alCouleurWagonPopUpNeutre.sort(null);
							
							this.lblPopupNeutreArete.setText(arete.toString());	
							this.ddlstNeutreArete.setModel(new DefaultComboBoxModel<CarteWagon>(alCouleurWagonPopUpNeutre.toArray(new CarteWagon[alCouleurWagonPopUpNeutre.size()])));						
							
							int n2 = JOptionPane.showOptionDialog(this, this.panelPopupNeutre, "Couleur à utiliser pour prendre l'arête neutre", JOptionPane.OK_CANCEL_OPTION,  JOptionPane.QUESTION_MESSAGE, null, null, null);
							if (n2 == JOptionPane.OK_OPTION) { // Validation
								couleurTerritoire = (CarteWagon) this.ddlstNeutreArete.getSelectedItem();
								if (joueurActuel.getHmWagon().get(couleurTerritoire) >= nbWagonArete) {
									// Retire les cartes couleurTerritoire de la main du joueur et les defausses
									joueurActuel.removeCarteWagon(couleurTerritoire, nbWagonArete);
									this.frameJeu.getMetier().getPioche().defausserCarteWagon(couleurTerritoire, nbWagonArete);
								}
								else if (joueurActuel.getHmWagon().get(couleurTerritoire) + joueurActuel.getHmWagon().get(this.frameJeu.getMetier().getAlCartesWagon().get(1)) >= nbWagonArete) {
									// Retire les cartes Joker de la main du joueur et les defausses
									joueurActuel.removeCarteWagon(this.frameJeu.getMetier().getAlCartesWagon().get(1), nbWagonArete - joueurActuel.getHmWagon().get(couleurTerritoire));
									this.frameJeu.getMetier().getPioche().defausserCarteWagon(this.frameJeu.getMetier().getAlCartesWagon().get(1), nbWagonArete - joueurActuel.getHmWagon().get(couleurTerritoire));
									// Retire les cartes couleurTerritoire de la main du joueur et les defausses
									joueurActuel.removeCarteWagon(couleurTerritoire, joueurActuel.getHmWagon().get(couleurTerritoire));
									this.frameJeu.getMetier().getPioche().defausserCarteWagon(couleurTerritoire, joueurActuel.getHmWagon().get(couleurTerritoire));
								}
							}
							else // Annulation de la pioche
								return;
						}
						else {
							if (joueurActuel.getHmWagon().get(couleurTerritoire) >= nbWagonArete)
							{
								// Retire les cartes couleurTerritoire de la main du joueur et les defausses
								joueurActuel.removeCarteWagon(couleurTerritoire, nbWagonArete);
								this.frameJeu.getMetier().getPioche().defausserCarteWagon(couleurTerritoire, nbWagonArete);
							}
							else if (joueurActuel.getHmWagon().get(couleurTerritoire) + joueurActuel.getHmWagon().get(this.frameJeu.getMetier().getAlCartesWagon().get(1)) >= nbWagonArete) {
								// Retire les cartes Joker de la main du joueur et les defausses
								joueurActuel.removeCarteWagon(this.frameJeu.getMetier().getAlCartesWagon().get(1), nbWagonArete - joueurActuel.getHmWagon().get(couleurTerritoire));
								this.frameJeu.getMetier().getPioche().defausserCarteWagon(this.frameJeu.getMetier().getAlCartesWagon().get(1), nbWagonArete - joueurActuel.getHmWagon().get(couleurTerritoire));
								// Retire les cartes couleurTerritoire de la main du joueur et les defausses
								joueurActuel.removeCarteWagon(couleurTerritoire, joueurActuel.getHmWagon().get(couleurTerritoire));
								this.frameJeu.getMetier().getPioche().defausserCarteWagon(couleurTerritoire, joueurActuel.getHmWagon().get(couleurTerritoire));
							}
						}
						this.frameJeu.getMetier().calculScore();
						this.finDePartie = this.frameJeu.jeu(" prend possession de l'arete " + arete.getNoeud1().getNom() + " - " + arete.getNoeud2().getNom());
						this.frameJeu.majIHM();
						reactiverBoutons();
						break;
					}
				}
			}
			else if (n1 == JOptionPane.CANCEL_OPTION) // Annulation
			{
				reactiverBoutons();
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

				this.finDePartie = this.frameJeu.jeu("pioche une carte Destination");
				this.frameJeu.majIHM();
				reactiverBoutons();
			}
			
		}

		if (this.finDePartie) {
			JOptionPane.showMessageDialog(frameJeu, "Fin de la partie !", "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}