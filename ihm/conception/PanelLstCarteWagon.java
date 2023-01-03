package ihm.conception;

import metier.*;
import ihm.FrameConcepteur;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;

public class PanelLstCarteWagon extends JPanel implements ActionListener {
	private FrameConcepteur concepteur;

	private JList<CarteWagon> lstCarteWagon;
	private ArrayList<CarteWagon> alCarteWagon;

	private JScrollPane scrollPane;
	private JPanel panelPopUpWagon;
	private JTextField txtNbCarteWagon;
	private JButton btnCarteWagonVerso;
	private JButton btnCarteWagonRecto;
	private DefaultListModel<CarteWagon> modelListCarteWagon;

	private JButton btnModif;

	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelLstCarteWagon(FrameConcepteur concepteur, int largeur, int hauteur) {
		this.concepteur = concepteur;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

		this.alCarteWagon = this.concepteur.getMetier().getAlCartesWagon();

		this.gbc.insets = new Insets(5, 5, 5, 5);
		this.setLayout(new GridBagLayout());

		this.modelListCarteWagon = new DefaultListModel<CarteWagon>();
		this.lstCarteWagon = new JList<CarteWagon>(this.modelListCarteWagon);
		this.lstCarteWagon.setCellRenderer(new RendererCouleur());

		this.panelPopUpWagon = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPopUp = new GridBagConstraints();
		gbcPopUp.insets = new Insets(5, 2, 5, 2);
		this.majLstCarteWagon();

		this.txtNbCarteWagon = new JTextField("1", 5);
		JLabel lblNbCarteWagon = new JLabel("Nb  carte Wagon :");
		lblNbCarteWagon.setLabelFor(this.txtNbCarteWagon);

		this.btnCarteWagonRecto = new JButton("Choisir image recto");

		// Button btnCarteWagonRecto
		gbcPopUp.gridx = 0;
		gbcPopUp.gridy = 0;
		this.panelPopUpWagon.add(this.btnCarteWagonRecto, gbcPopUp);

		// TextField NbCarteWagon
		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 0;
		this.panelPopUpWagon.add(this.txtNbCarteWagon, gbcPopUp);

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstCarteWagon);
		this.lstCarteWagon.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
		this.lstCarteWagon.setFixedCellHeight(50);

		// Bouton btnCarteWagonVerso
		this.btnCarteWagonVerso = new JButton("Choisir image verso");
		this.btnCarteWagonVerso.setBackground(Color.GRAY);
		this.btnCarteWagonVerso.addActionListener(this);
		this.btnCarteWagonVerso.setBorderPainted(false);
		this.btnCarteWagonVerso.setFocusPainted(false);

		// Bouton Modifier
		this.btnModif = new JButton("Modifier");
		this.btnModif.setBackground(Color.GRAY);
		this.btnModif.addActionListener(this);
		this.btnModif.setBorderPainted(false);
		this.btnModif.setFocusPainted(false);

		// Placement des composants
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.gridwidth = 3;
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.scrollPane, this.gbc);

		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.add(this.btnModif, this.gbc);

	}

	public void majLstCarteWagon() {
		for (CarteWagon carteWagon : this.alCarteWagon) {
			if (!this.modelListCarteWagon.contains(carteWagon)) {
				this.modelListCarteWagon.addElement(carteWagon);
			}
		}
		this.lstCarteWagon.clearSelection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnModif) {
			if (this.lstCarteWagon.getSelectedIndex() != -1) {
				CarteWagon carteWagonSelected = this.lstCarteWagon.getSelectedValue();

				this.txtNbCarteWagon.setText(Integer.toString(carteWagonSelected.getNbCarteWagon()));

				int n = JOptionPane.showOptionDialog(this, this.panelPopUpWagon, "Modification d'une carte destination",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (n != JOptionPane.OK_OPTION) // Cancel ou croix == annulation
					return;

			} else {
				JOptionPane.showMessageDialog(this, "Veuillez sélectionner une carte wagon", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// this.lstCarteWagon.setSelectedItem(carteWagonSelected.getImgVerso());
			// this.lstCarteWagon.setSelectedItem(carteWagonSelected.getImgRecto()));

		}
		if (e.getSource() == this.btnCarteWagonRecto) {

		}

	}
}
