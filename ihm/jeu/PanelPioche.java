package ihm.jeu;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import ihm.FrameJeu;
import ihm.jeu.util.BoutonCarteWagon;
import metier.CarteWagon;
import metier.Pioche;

public class PanelPioche extends JFrame implements ActionListener {
	
	private PanelAction parent;
	private FrameJeu jeu;
	private Pioche pioche;

	private boolean aPioche;
	private boolean aPiocheUneFois;

	private ArrayList<JLabel> alLblCartes;
	private ArrayList<BoutonCarteWagon> alBtnCartes;
	private ArrayList<CarteWagon> alCartesCourantes;
	private BoutonCarteWagon btnNonVisible;
	private JLabel lblNonVisible;
	private CarteWagon carteCachee;

	private int largeur;
	private int hauteur;

	private JPanel panelContenu;


	public PanelPioche(FrameJeu jeu, PanelAction parent, int largeur, int hauteur, Pioche pioche, ArrayList<CarteWagon> alCartesCourantes) {
		this.parent = parent;
		this.jeu = jeu;
		this.pioche = pioche;
		this.largeur = largeur;
		this.hauteur = hauteur;


		// Contenu PanelPopupPioche
		// ------------------------ //
		this.setTitle("Pioche");
		this.setResizable(false);
		this.setMinimumSize(new Dimension(600, 300));
		this.setLayout(new BorderLayout());

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 25);

		this.alBtnCartes = new ArrayList<BoutonCarteWagon>();
		this.alLblCartes = new ArrayList<JLabel>();

		this.panelContenu = new JPanel();
		this.panelContenu.setLayout(null);
		this.panelContenu.setPreferredSize(dim);

		this.alCartesCourantes = alCartesCourantes;
		for(int i=0; i < 6; i++) {
			
			if(i < 5) {
				this.alLblCartes.add(new JLabel(new ImageIcon(this.alCartesCourantes.get(i).getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));
				this.alBtnCartes.add(new BoutonCarteWagon(this.alCartesCourantes.get(i) ,"Piocher"));

				this.panelContenu.add(this.alLblCartes.get(i));
				this.panelContenu.add(this.alBtnCartes.get(i));

				this.alLblCartes.get(i).setBounds(25 + (i*110), 10, 70, 70);
				this.alBtnCartes.get(i).setBounds(10 + (i*110), 80, 100, 35);

				this.alBtnCartes.get(i).setFocusPainted(false);
				this.alBtnCartes.get(i).addActionListener(this);
			} 
			else {
				this.carteCachee = this.alCartesCourantes.get(i);
				this.btnNonVisible = new BoutonCarteWagon(this.carteCachee, "Piocher");
				this.lblNonVisible = new JLabel();
				this.lblNonVisible.setBackground(Color.BLACK);
				this.lblNonVisible.setOpaque(true);

				this.panelContenu.add(this.lblNonVisible);
				this.panelContenu.add(this.btnNonVisible);
				this.lblNonVisible.setBounds(300 - 35, 135, 70, 70);
				this.btnNonVisible.setBounds(300 - 50, 205, 100, 30);
				this.btnNonVisible.setFocusPainted(false);
				this.btnNonVisible.addActionListener(this);
			}
		}

		this.verifierTripleJoker();

		this.aPioche = false;
		this.aPiocheUneFois = false;

		this.add(this.panelContenu, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {

		// Pioche

		if(e.getSource() == this.alBtnCartes.get(0)) {

			this.verifierTripleJoker();

			if(this.alBtnCartes.get(0).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().jeu("pioche CarteWagon " + this.alBtnCartes.get(0).getCarteWagon().toString());

				this.enleverCarte(this.alBtnCartes.get(0).getCarteWagon(), 0);

				this.aPiocheUneFois = false;
				this.majUI();
				this.jeu.majIHM();
				this.majUI();
				this.parent.reactiverBoutons();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.aPiocheUneFois = true;

					this.enleverCarte(this.alBtnCartes.get(0).getCarteWagon(), 0);

					this.jeu.majIHM();
					this.majUI();
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().jeu("pioche une carte Wagon " + this.alBtnCartes.get(0).getCarteWagon().toString());
					this.aPioche = true;

					this.enleverCarte(this.alBtnCartes.get(0).getCarteWagon(), 0);

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.parent.reactiverBoutons();
					this.dispose();
				}
			}
		}



		if(e.getSource() == this.alBtnCartes.get(1)) {

			this.verifierTripleJoker();

			if(this.alBtnCartes.get(1).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().jeu("pioche une carte Wagon " + this.alBtnCartes.get(1).getCarteWagon().toString());

				this.enleverCarte(this.alBtnCartes.get(1).getCarteWagon(), 1);

				this.aPiocheUneFois = false;
				this.jeu.majIHM();
				this.majUI();
				this.parent.reactiverBoutons();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.aPiocheUneFois = true;

					this.enleverCarte(this.alBtnCartes.get(1).getCarteWagon(), 1);

					this.jeu.majIHM();
					this.majUI();
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().jeu("pioche CarteWagon " + this.alBtnCartes.get(1).getCarteWagon().toString());
					this.aPioche = true;

				this.enleverCarte(this.alBtnCartes.get(1).getCarteWagon(), 1);

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.parent.reactiverBoutons();
					this.dispose();
				}
			}

		}



		if(e.getSource() == this.alBtnCartes.get(2)) {

			this.verifierTripleJoker();

			if(this.alBtnCartes.get(2).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().jeu("pioche CarteWagon " + this.alBtnCartes.get(2).getCarteWagon().toString());

				this.enleverCarte(this.alBtnCartes.get(2).getCarteWagon(), 2);

				this.aPiocheUneFois = false;
				this.jeu.majIHM();
				this.majUI();
				this.parent.reactiverBoutons();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.aPiocheUneFois = true;

					this.enleverCarte(this.alBtnCartes.get(2).getCarteWagon(), 2);

					this.jeu.majIHM();
					this.majUI();
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().jeu("pioche CarteWagon " + this.alBtnCartes.get(2).getCarteWagon().toString());
					this.aPioche = true;

					this.enleverCarte(this.alBtnCartes.get(2).getCarteWagon(), 2);

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.parent.reactiverBoutons();
					this.dispose();
				}
			}

		}

		if(e.getSource() == this.alBtnCartes.get(3)) {

			this.verifierTripleJoker();
			
			if(this.alBtnCartes.get(3).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().jeu("pioche CarteWagon " + this.alBtnCartes.get(3).getCarteWagon().toString());

				this.enleverCarte(this.alBtnCartes.get(3).getCarteWagon(), 3);

				this.aPiocheUneFois = false;
				this.jeu.majIHM();
				this.majUI();
				this.parent.reactiverBoutons();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.aPiocheUneFois = true;

					this.enleverCarte(this.alBtnCartes.get(3).getCarteWagon(), 3);

					this.jeu.majIHM();
					this.majUI();
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().jeu("pioche CarteWagon " + this.alBtnCartes.get(3).getCarteWagon().toString());
					this.aPioche = true;

					this.enleverCarte(this.alBtnCartes.get(3).getCarteWagon(), 3);

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.parent.reactiverBoutons();
					this.dispose();
				}
			}

		}



		if(e.getSource() == this.alBtnCartes.get(4)) {
			
			if(this.alBtnCartes.get(4).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().jeu("pioche CarteWagon " + this.alBtnCartes.get(4).getCarteWagon().toString());

				this.enleverCarte(this.alBtnCartes.get(4).getCarteWagon(), 4);

				this.aPiocheUneFois = false;
				this.jeu.majIHM();
				this.majUI();
				this.parent.reactiverBoutons();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.aPiocheUneFois = true;

					this.enleverCarte(this.alBtnCartes.get(4).getCarteWagon(), 4);

					this.jeu.majIHM();
					this.majUI();
					
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().jeu("pioche CarteWagon " + this.alBtnCartes.get(4).getCarteWagon().toString());
					this.aPioche = true;

					this.enleverCarte(this.alBtnCartes.get(4).getCarteWagon(), 4);

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.parent.reactiverBoutons();
					this.dispose();
				}
			}

		}



		if(e.getSource() == this.btnNonVisible) {
			this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.btnNonVisible.getCarteWagon());

			this.parent.enleverCarteWagon(this.alCartesCourantes.get(5), 5);
			this.alCartesCourantes = this.parent.getAlCartesCourantes();

			this.btnNonVisible.setCarteWagon(this.alCartesCourantes.get(5));

			this.jeu.majIHM();
			this.majUI();
			if(this.aPiocheUneFois) { 
				this.jeu.getMetier().jeu("pioche CarteWagon face cachée"); 
				this.majUI();
				this.jeu.majIHM();
				this.aPioche = true;
				this.aPiocheUneFois = false;
				this.parent.reactiverBoutons();
				this.dispose(); }
			else { this.aPiocheUneFois = true; }
		}

	}


	public void verifierTripleJoker() {

		int nbJoker = 0;
		for(int i=0; i < 5; i++) {
			if(this.alCartesCourantes.get(i).toString().equals("Joker")) { nbJoker++; }
		}

		while(nbJoker >= 3) {
			
			for(int i=0; i < 6; i++) { this.parent.enleverCarteWagon(this.alCartesCourantes.get(i), i); }

			this.alCartesCourantes = this.parent.getAlCartesCourantes();
			this.btnNonVisible.setCarteWagon(this.alCartesCourantes.get(5));
			for(int i=0; i < 5; i++) {
				this.alBtnCartes.get(i).setCarteWagon(this.alCartesCourantes.get(i)); 
				this.alLblCartes.get(i).setIcon(new ImageIcon(this.alBtnCartes.get(i).getCarteWagon().getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
			}

			nbJoker = 0;
			for(int i=0; i < 5; i++) {
				if(this.alBtnCartes.get(i).toString().equals("Joker")) { nbJoker++; }
			}

			this.majUI();
			JOptionPane.showMessageDialog(parent, "Défausse des 5 cartes visibles suite à la présence de 3 Jokers !");
		}
	}


	public void majUI() { 
		for(int i=0; i < this.alLblCartes.size(); i++) { this.alLblCartes.get(i).repaint(); }

		if(this.aPiocheUneFois) { for(BoutonCarteWagon btn : this.alBtnCartes) { if(btn.getCarteWagon().toString().equals("Joker")) { btn.setEnabled(false); }}}
		else { for(BoutonCarteWagon btn : this.alBtnCartes) { if(btn.getCarteWagon().toString().equals("Joker")) { btn.setEnabled(true); }}}

		if(this.aPiocheUneFois) { this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); }
		else { this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); }

		for(CarteWagon carte : this.alCartesCourantes) {
			int index = this.alBtnCartes.indexOf(carte);
			if(index > 0 && index < 5  && this.alBtnCartes.get(index).getCarteWagon() == null) { this.alBtnCartes.get(index).setEnabled(false); }
		}
	}


	public void enleverCarte(CarteWagon carte, int index) {

		if(carte != null) {
			this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(index).getCarteWagon());
			this.parent.enleverCarteWagon(this.alBtnCartes.get(index).getCarteWagon(), index);
			this.alCartesCourantes = this.parent.getAlCartesCourantes();

			CarteWagon carteRemplacee = this.alCartesCourantes.get(index);
			this.alBtnCartes.get(index).setCarteWagon(carteRemplacee);
			this.alLblCartes.get(index).setIcon(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));

			if(carteRemplacee.toString().equals("Joker")) { this.verifierTripleJoker(); }
		}
		else {
			this.alBtnCartes.get(index).setEnabled(false);
		}
	}
}
