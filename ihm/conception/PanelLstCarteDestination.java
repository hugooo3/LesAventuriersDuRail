package ihm.conception;

import metier.*;
import ihm.FrameConcepteur;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;

public class PanelLstCarteDestination extends JPanel implements ActionListener {
	private FrameConcepteur concepteur;

	private JList<CarteDestination> lstCarteDestination;
	private ArrayList<CarteDestination> alCarteDestination;

	private JPanel panelPopUpDesti;
	private JScrollPane scrollPane;
	private DefaultListModel<CarteDestination> modelListCarteDestination;

	private JComboBox<Noeud> ddlstNoeud1;
	private JComboBox<Noeud> ddlstNoeud2;
	private ArrayList<Noeud> alNoeud;

	private JButton btnNouveau, btnModif, btnSuppr, btnAuto;

	private JTextField txtNbPoint;

	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelLstCarteDestination(FrameConcepteur concepteur, int largeur, int hauteur) {
		this.concepteur = concepteur;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

		this.alCarteDestination = this.concepteur.getMetier().getAlCartesDestination();

		this.gbc.insets = new Insets(5, 5, 5, 5);
		this.setLayout(new GridBagLayout());

		this.modelListCarteDestination = new DefaultListModel<CarteDestination>();
		this.lstCarteDestination = new JList<CarteDestination>(this.modelListCarteDestination);

		// Création de la PopUp

		this.panelPopUpDesti = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPopUp = new GridBagConstraints();
		gbcPopUp.insets = new Insets(5, 2, 5, 2);

		this.txtNbPoint = new JTextField("5", 5);
		JLabel lblNbPoint = new JLabel("Nombre de points :");
		lblNbPoint.setLabelFor(this.txtNbPoint);

		this.alNoeud = this.concepteur.getMetier().getAlNoeuds();
		this.ddlstNoeud1 = new JComboBox<Noeud>(this.alNoeud.toArray(new Noeud[this.alNoeud.size()]));
		this.ddlstNoeud2 = new JComboBox<Noeud>(this.alNoeud.toArray(new Noeud[this.alNoeud.size()]));
		this.ddlstNoeud1.setPrototypeDisplayValue(new Noeud("XXXXXXXXXXXXXXXXXXXX", 0, 0, 0));
		this.ddlstNoeud2.setPrototypeDisplayValue(new Noeud("XXXXXXXXXXXXXXXXXXXX", 0, 0, 0));

		// Combobox Noeud 1
		gbcPopUp.gridx = 0;
		gbcPopUp.gridy = 0;
		this.panelPopUpDesti.add(this.ddlstNoeud1, gbcPopUp);

		// ComboBox Noeud2
		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 0;
		this.panelPopUpDesti.add(this.ddlstNoeud2, gbcPopUp);

		// TextField NbPoint
		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 1;
		this.panelPopUpDesti.add(this.txtNbPoint, gbcPopUp);

		// Label NbPoint
		gbcPopUp.gridx = 0;
		gbcPopUp.gridy = 1;
		this.panelPopUpDesti.add(lblNbPoint, gbcPopUp);

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstCarteDestination);
		this.lstCarteDestination.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
		this.lstCarteDestination.setFixedCellHeight(50);

		// Bouton Nouveau
		this.btnNouveau = new JButton("Nouveau");
		this.btnNouveau.setBackground(Color.GRAY);
		this.btnNouveau.addActionListener(this);
		this.btnNouveau.setBorderPainted(false);
		this.btnNouveau.setFocusPainted(false);

		// Bouton Modifier
		this.btnModif = new JButton("Modifier");
		this.btnModif.setBackground(Color.GRAY);
		this.btnModif.addActionListener(this);
		this.btnModif.setBorderPainted(false);
		this.btnModif.setFocusPainted(false);

		// Bouton supprimer
		this.btnSuppr = new JButton("Supprimer");
		this.btnSuppr.setBackground(Color.GRAY);
		this.btnSuppr.addActionListener(this);
		this.btnSuppr.setBorderPainted(false);
		this.btnSuppr.setFocusPainted(false);

		// Bouton generation automatique
		this.btnAuto = new JButton("Génération automatique");
		this.btnAuto.setBackground(Color.GRAY);
		this.btnAuto.addActionListener(this);
		this.btnAuto.setBorderPainted(false);
		this.btnAuto.setFocusPainted(false);

		// Placement des composants
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.gridwidth = 3;
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.scrollPane, this.gbc);

		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.gridwidth = 1;
		this.add(this.btnNouveau, this.gbc);

		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.add(this.btnModif, this.gbc);

		this.gbc.gridx = 2;
		this.gbc.gridy = 1;
		this.add(this.btnSuppr, this.gbc);

		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.gbc.gridwidth = 3;
		this.add(this.btnAuto, this.gbc);
	}

	public void majLstCarteDestination() {
		for (CarteDestination carteDestination : this.alCarteDestination) {
			if (!this.modelListCarteDestination.contains(carteDestination)) {
				this.modelListCarteDestination.addElement(carteDestination);
			}
		}
		this.lstCarteDestination.clearSelection();
	}

	private void majComboBox() {
		this.ddlstNoeud1.removeAllItems();
		this.ddlstNoeud2.removeAllItems();
		for (Noeud noeud : this.alNoeud) {
			this.ddlstNoeud1.addItem(noeud);
			this.ddlstNoeud2.addItem(noeud);
		}
	}

	public boolean existe(CarteDestination c) {
		for (CarteDestination carte : this.alCarteDestination) {
			if (carte.getNoeud1().getNom().equals(c.getNoeud1().getNom())
					&& carte.getNoeud2().getNom().equals(c.getNoeud2().getNom()) ||
					carte.getNoeud2().getNom().equals(c.getNoeud1().getNom())
							&& carte.getNoeud1().getNom().equals(c.getNoeud2().getNom())) {

				return true;
			}
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.majComboBox();

		if (e.getSource() == this.btnAuto) {
			this.concepteur.majIHM();

			ArrayList<Noeud> alNoeud1 = new ArrayList<Noeud>();
			ArrayList<Noeud> alNoeud2 = new ArrayList<Noeud>();
			for (int i = 0; i < this.ddlstNoeud1.getItemCount(); i++)
				alNoeud1.add((Noeud) this.ddlstNoeud1.getItemAt(i));
			for (int i = 0; i < this.ddlstNoeud2.getItemCount(); i++)
				alNoeud2.add((Noeud) this.ddlstNoeud2.getItemAt(i));

			for (int i = 0; i < alNoeud1.size(); i++) {
				for (int j = 0; j < alNoeud2.size(); j++) {
					if (!alNoeud1.get(i).getNom().equals(alNoeud2.get(j).getNom())) {
						int nbPoint = (int) (Math.random() * 50) + 1;
						CarteDestination carteTemp = new CarteDestination(alNoeud1.get(i), alNoeud2.get(j), nbPoint);

						if (!existe(carteTemp))
							this.alCarteDestination.add(carteTemp);
					}
				}
			}
			this.concepteur.majIHM();
		}

		if (e.getSource() == this.btnNouveau) {
			if (this.alNoeud.size() < 2) {
				JOptionPane.showMessageDialog(null, "Nombre de noeud insuffisant pour faire une carte destination.",
						"Erreur",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (this.ddlstNoeud1.getItemCount() > 0 && this.ddlstNoeud1.getItemCount() > 0)
				do {
					this.ddlstNoeud1.setSelectedIndex(0);
					this.ddlstNoeud2.setSelectedIndex(1);
					int n = JOptionPane.showOptionDialog(this, this.panelPopUpDesti, "Création d'une carte destination",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);

					if ((Noeud) this.ddlstNoeud1.getSelectedItem() == (Noeud) this.ddlstNoeud2.getSelectedItem())
						JOptionPane.showMessageDialog(null, "Les deux noeuds sélectionnés sont identiques", "Erreur",
								JOptionPane.ERROR_MESSAGE);

					if (n != JOptionPane.OK_OPTION) // Cancel ou croix == annulation
						return;
				} while ((Noeud) this.ddlstNoeud1.getSelectedItem() == (Noeud) this.ddlstNoeud2.getSelectedItem());

			Noeud noeudSelected1 = (Noeud) this.ddlstNoeud1.getSelectedItem();
			Noeud noeudSelected2 = (Noeud) this.ddlstNoeud2.getSelectedItem();
			int nbPoints = Integer.parseInt(this.txtNbPoint.getText());

			CarteDestination carteDestination = new CarteDestination(noeudSelected1, noeudSelected2, nbPoints);

			for (CarteDestination carte : this.alCarteDestination)
				if (carteDestination.getNoeud1() == carte.getNoeud1() &&
						carteDestination.getNoeud2() == carte.getNoeud2() &&
						carteDestination.getPoints() == carte.getPoints() ||
						carteDestination.getNoeud1() == carte.getNoeud2() &&
								carteDestination.getNoeud2() == carte.getNoeud1() &&
								carteDestination.getPoints() == carte.getPoints()) {

					JOptionPane.showMessageDialog(null, "Cette carte destination existe déjà", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			this.alCarteDestination.add(carteDestination);
			this.concepteur.majIHM();
		}

		if (e.getSource() == this.btnModif) {
			if (this.lstCarteDestination.getSelectedIndex() != -1) {
				this.majComboBox();

				CarteDestination carteSelected = (CarteDestination) this.lstCarteDestination.getSelectedValue();
				this.ddlstNoeud1.setSelectedItem(carteSelected.getNoeud1());
				this.ddlstNoeud2.setSelectedItem(carteSelected.getNoeud2());
				this.txtNbPoint.setText(String.valueOf(carteSelected.getPoints()));

				if (this.ddlstNoeud1.getItemCount() > 0 && this.ddlstNoeud1.getItemCount() > 0)
					do {
						int n = JOptionPane.showOptionDialog(this, this.panelPopUpDesti,
								"Modification d'une carte destination",
								JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, null, null);

						if ((Noeud) this.ddlstNoeud1.getSelectedItem() == (Noeud) this.ddlstNoeud2.getSelectedItem())
							JOptionPane.showMessageDialog(null, "Les deux noeuds sélectionnés sont identiques",
									"Erreur",
									JOptionPane.ERROR_MESSAGE);

						if (n != JOptionPane.OK_OPTION) // Cancel ou croix == annulation
							return;
					} while ((Noeud) this.ddlstNoeud1.getSelectedItem() == (Noeud) this.ddlstNoeud2.getSelectedItem());

				CarteDestination carteTemp = new CarteDestination((Noeud) this.ddlstNoeud1.getSelectedItem(),
						(Noeud) this.ddlstNoeud2.getSelectedItem(),
						Integer.parseInt(this.txtNbPoint.getText()));

				for (CarteDestination carte : this.alCarteDestination)
					if (carteTemp.getNoeud1() == carte.getNoeud1() &&
							carteTemp.getNoeud2() == carte.getNoeud2() &&
							carteTemp.getPoints() == carte.getPoints() ||
							carteTemp.getNoeud1() == carte.getNoeud2() &&
									carteTemp.getNoeud2() == carte.getNoeud1() &&
									carteTemp.getPoints() == carte.getPoints()) {

						JOptionPane.showMessageDialog(null, "Cette carte destination existe déjà", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

				carteSelected.setNoeud1((Noeud) this.ddlstNoeud1.getSelectedItem());
				carteSelected.setNoeud2((Noeud) this.ddlstNoeud2.getSelectedItem());
				carteSelected.setPoints(Integer.parseInt(this.txtNbPoint.getText()));

				this.concepteur.majIHM();

			}
		}

		if (e.getSource() == this.btnSuppr) {
			if (this.lstCarteDestination.getSelectedIndex() != -1) {
				CarteDestination carteDestinationSelect = this.lstCarteDestination.getSelectedValue();
				this.alCarteDestination.remove(carteDestinationSelect);
				this.modelListCarteDestination.removeElement(carteDestinationSelect);
				this.lstCarteDestination.setSelectedIndex(0);
				this.concepteur.majIHM();
			} else {
				JOptionPane.showMessageDialog(null, "Veuillez selectionner une carte.", "Aucune sélection",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
