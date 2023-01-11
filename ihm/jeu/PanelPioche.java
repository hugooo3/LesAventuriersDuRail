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


	public PanelPioche(FrameJeu jeu, PanelAction parent, int largeur, int hauteur, Pioche pioche) {
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
		this.alCartesCourantes = new ArrayList<CarteWagon>();
		this.alLblCartes = new ArrayList<JLabel>();

		this.panelContenu = new JPanel();
		this.panelContenu.setLayout(null);
		this.panelContenu.setPreferredSize(dim);


		for(int i=0; i < 5; i++) {
			this.alCartesCourantes.add(pioche.piocherCarteWagon());
			this.alLblCartes.add(new JLabel(new ImageIcon(this.alCartesCourantes.get(i).getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));
			this.alBtnCartes.add(new BoutonCarteWagon(this.alCartesCourantes.get(i) ,"Piocher"));

			this.panelContenu.add(this.alLblCartes.get(i));
			this.panelContenu.add(this.alBtnCartes.get(i));
			this.alLblCartes.get(i).setBounds(25 + (i*110), 10, 70, 70);
			this.alBtnCartes.get(i).setBounds(10 + (i*110), 80, 100, 35);
			this.alBtnCartes.get(i).setFocusPainted(false);
			this.alBtnCartes.get(i).addActionListener(this);

			if(i == 4) {
				this.carteCachee = pioche.piocherCarteWagon();
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

		this.aPioche = false;
		this.aPiocheUneFois = false;

		this.add(this.panelContenu, BorderLayout.CENTER);

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}


	public CarteWagon getCarteDispo() { return this.pioche.piocherCarteWagon(); }

	public void actionPerformed(ActionEvent e) {

		// Pioche

		if(e.getSource() == this.alBtnCartes.get(0)) {

			if(this.alBtnCartes.get(0).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(0).getCarteWagon());
				this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(0).getCarteWagon().toString());

				CarteWagon carteRemplacee = getCarteDispo();
				this.alBtnCartes.set(0, new BoutonCarteWagon(carteRemplacee, "Piocher"));
				this.alCartesCourantes.set(0, carteRemplacee);
				this.alLblCartes.set(0, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

				this.aPiocheUneFois = false;
				this.majUI();
				this.jeu.majIHM();
				this.majUI();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(0).getCarteWagon());
					this.aPiocheUneFois = true;
					
					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(0, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(0, carteRemplacee);
					this.alLblCartes.set(0, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.alBtnCartes.get(0).setEnabled(false);
						this.verifierTripleJoker();
					}

					this.jeu.majIHM();
					this.majUI();
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(0).getCarteWagon());
					this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(0).getCarteWagon().toString());
					this.aPioche = true;

					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(0, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(0, carteRemplacee);
					this.alLblCartes.set(0, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.verifierTripleJoker();
					}

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.dispose();
				}
			}
		}



		if(e.getSource() == this.alBtnCartes.get(1)) {

			if(this.alBtnCartes.get(1).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(1).getCarteWagon());
				this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(1).getCarteWagon().toString());

				CarteWagon carteRemplacee = getCarteDispo();
				this.alBtnCartes.set(1, new BoutonCarteWagon(carteRemplacee, "Piocher"));
				this.alCartesCourantes.set(1, carteRemplacee);
				this.alLblCartes.set(1, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

				this.aPiocheUneFois = false;
				this.jeu.majIHM();
				this.majUI();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(1).getCarteWagon());
					this.aPiocheUneFois = true;
					
					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(1, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(1, carteRemplacee);
					this.alLblCartes.set(1, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.alBtnCartes.get(1).setEnabled(false);
						this.verifierTripleJoker();
					}

					this.jeu.majIHM();
					this.majUI();
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(1).getCarteWagon());
					this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(1).getCarteWagon().toString());
					this.aPioche = true;

					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(1, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(1, carteRemplacee);
					this.alLblCartes.set(1, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.verifierTripleJoker();
					}

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.dispose();
				}
			}

		}



		if(e.getSource() == this.alBtnCartes.get(2)) {

			if(this.alBtnCartes.get(2).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(2).getCarteWagon());
				this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(2).getCarteWagon().toString());

				CarteWagon carteRemplacee = getCarteDispo();
				this.alBtnCartes.set(2, new BoutonCarteWagon(carteRemplacee, "Piocher"));
				this.alCartesCourantes.set(2, carteRemplacee);
				this.alLblCartes.set(2, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

				this.aPiocheUneFois = false;
				this.jeu.majIHM();
				this.majUI();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(2).getCarteWagon());
					this.aPiocheUneFois = true;
					
					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(2, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(2, carteRemplacee);
					this.alLblCartes.set(2, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.alBtnCartes.get(2).setEnabled(false);
						this.verifierTripleJoker();
					}

					this.jeu.majIHM();
					this.majUI();
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(2).getCarteWagon());
					this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(2).getCarteWagon().toString());
					this.aPioche = true;

					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(2, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(2, carteRemplacee);
					this.alLblCartes.set(2, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.verifierTripleJoker();
					}

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.dispose();
				}
			}

		}

		if(e.getSource() == this.alBtnCartes.get(3)) {
			
			if(this.alBtnCartes.get(3).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(3).getCarteWagon());
				this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(3).getCarteWagon().toString());

				CarteWagon carteRemplacee = getCarteDispo();
				this.alBtnCartes.set(3, new BoutonCarteWagon(carteRemplacee, "Piocher"));
				this.alCartesCourantes.set(3, carteRemplacee);
				this.alLblCartes.set(3, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

				this.aPiocheUneFois = false;
				this.jeu.majIHM();
				this.majUI();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(3).getCarteWagon());
					this.aPiocheUneFois = true;
					
					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(3, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(3, carteRemplacee);
					this.alLblCartes.set(3, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.alBtnCartes.get(3).setEnabled(false);
						this.verifierTripleJoker();
					}

					this.jeu.majIHM();
					this.majUI();
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(3).getCarteWagon());
					this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(3).getCarteWagon().toString());
					this.aPioche = true;

					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(3, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(3, carteRemplacee);
					this.alLblCartes.set(3, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.verifierTripleJoker();
					}

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.dispose();
				}
			}

		}



		if(e.getSource() == this.alBtnCartes.get(4)) {
			
			if(this.alBtnCartes.get(4).getCarteWagon().toString().equals("Joker")) { // Pioche du Joker
				this.aPioche = true;
				this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(4).getCarteWagon());
				this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(4).getCarteWagon().toString());

				CarteWagon carteRemplacee = getCarteDispo();
				this.alBtnCartes.set(4, new BoutonCarteWagon(carteRemplacee, "Piocher"));
				this.alCartesCourantes.set(4, carteRemplacee);
				this.alLblCartes.set(4, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

				this.aPiocheUneFois = false;
				this.jeu.majIHM();
				this.majUI();
				this.dispose();
			} 
			else { // Pioche classique
				
				if(!aPiocheUneFois) { // Première Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(4).getCarteWagon());
					this.aPiocheUneFois = true;
					
					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(4, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(4, carteRemplacee);
					this.alLblCartes.set(4, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.alBtnCartes.get(4).setEnabled(false);
						this.verifierTripleJoker();
					}

					this.majUI();
					this.jeu.majIHM();
				} 
				else { // Deuxième Pioche
					this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.alBtnCartes.get(4).getCarteWagon());
					this.jeu.getMetier().jeu("Pioche CarteWagon " + this.alBtnCartes.get(4).getCarteWagon().toString());
					this.aPioche = true;

					CarteWagon carteRemplacee = getCarteDispo();
					this.alBtnCartes.set(4, new BoutonCarteWagon(carteRemplacee, "Piocher"));
					this.alCartesCourantes.set(4, carteRemplacee);
					this.alLblCartes.set(4, new JLabel(new ImageIcon(carteRemplacee.getImgRecto().getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH))));

					if(carteRemplacee.toString().equals("Joker")) { 
						this.verifierTripleJoker();
					}

					this.aPiocheUneFois = false;
					this.jeu.majIHM();
					this.majUI();
					this.dispose();
				}
			}

		}



		if(e.getSource() == this.btnNonVisible) {
			
			if(!this.aPiocheUneFois) {
				this.aPioche = true;
				this.jeu.getMetier().getJoueurEnJeu().addCarteWagon(this.btnNonVisible.getCarteWagon());
				this.jeu.getMetier().jeu("Pioche CarteWagon " + this.btnNonVisible.getCarteWagon().toString());

				CarteWagon carteRemplacee = getCarteDispo();
				this.btnNonVisible = new BoutonCarteWagon(carteRemplacee, "Piocher");

				this.jeu.majIHM();
				this.majUI();
				this.dispose();
			}
		}

	}


	public void verifierTripleJoker() {

		int nbJoker = 0;
		for(int i=0; i < this.alCartesCourantes.size(); i++) {
			if(this.alCartesCourantes.get(i).toString().equals("Joker")) { nbJoker++; }
		}

		if(nbJoker >= 3) {
			// défausser les cartes VISIBLES jusqu'à ce qu'il y ait moins de 3 jokers !
			// Une fois fait, il faut réactiver tous les boutons !

			// Penser aux alLabels, alCartesCourantes, alBtnCartes
		}
	}

	public void majUI() {
		for(JLabel lbl : this.alLblCartes) { lbl.updateUI(); lbl.repaint(); lbl.revalidate(); }
		for(BoutonCarteWagon btn : this.alBtnCartes) { btn.updateUI(); btn.repaint(); btn.revalidate(); }

		this.panelContenu.repaint();
		this.panelContenu.revalidate();
		this.panelContenu.updateUI();
	}
}
