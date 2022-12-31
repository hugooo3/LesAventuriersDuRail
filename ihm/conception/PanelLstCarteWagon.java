package ihm.conception;

import metier.*;
import ihm.FrameConcepteur;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;

public class PanelLstCarteWagon extends JPanel implements ActionListener
{
	private FrameConcepteur concepteur;

	private JList<CarteWagon> lstCarteWagon;
	private ArrayList<CarteWagon> alCarteWagon;

	private JScrollPane scrollPane;
	private DefaultListModel<CarteWagon> modelListCarteWagon;

	private JButton btnNouveau, btnModif, btnSuppr, btnVerso;
	
	private GridBagConstraints gbc = new GridBagConstraints();

	private JPanel panelPopUp;
	private JTextField txtNomCouleur;
	private JColorChooser colorPicker;
	private ImageIcon imageRecto;

	public PanelLstCarteWagon(FrameConcepteur concepteur, int largeur, int hauteur) 
	{
		this.concepteur = concepteur;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

		this.alCarteWagon = this.concepteur.getMetier().getAlCartesWagon();

		this.gbc.insets = new Insets(5, 5, 5, 5);
		this.setLayout(new GridBagLayout());

		this.modelListCarteWagon = new DefaultListModel<CarteWagon>();
		this.lstCarteWagon = new JList<CarteWagon>(this.modelListCarteWagon);
		this.lstCarteWagon.setCellRenderer(new RendererCouleur());
		this.majLstCarteWagon();

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstCarteWagon);
		this.lstCarteWagon.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
		this.lstCarteWagon.setFixedCellHeight(50);

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

		// Bouton verso
		this.btnVerso = new JButton("Changer le verso des cartes");
		this.btnVerso.setBackground(Color.GRAY);
		this.btnVerso.addActionListener(this);
		this.btnVerso.setBorderPainted(false);
		this.btnVerso.setFocusPainted(false);

		// Panel PopUp		
		this.panelPopUp = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPopUp = new GridBagConstraints();
		gbcPopUp.insets = new Insets(5, 2, 5, 2);

		this.txtNomCouleur = new JTextField(10);
		this.colorPicker = new JColorChooser();

		// TextField 
		gbcPopUp.gridx = 0;
		gbcPopUp.gridy = 0;
		this.panelPopUp.add(new JLabel("Nom de la couleur : "), gbcPopUp);

		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 0;
		this.panelPopUp.add(this.txtNomCouleur, gbcPopUp);

		// Couleur
		gbcPopUp.gridx = 0;
		gbcPopUp.gridy = 1;
		this.panelPopUp.add(this.colorPicker, gbcPopUp);

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
		this.add(this.btnVerso, this.gbc);
	}

	public void majLstCarteWagon() 
	{
		for (CarteWagon carteWagon : this.alCarteWagon)
		{
			if (!this.modelListCarteWagon.contains(carteWagon))
			{
				this.modelListCarteWagon.addElement(carteWagon);
			}
		}
		this.lstCarteWagon.clearSelection();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnNouveau)
		{
			// @TODO
			int n = JOptionPane.showOptionDialog(this, this.panelPopUp, "Création d'une couleur" ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		}
		
		if (e.getSource() == this.btnModif)
		{
			// @TODO
		}

		if (e.getSource() == this.btnSuppr)
		{	
			if (this.lstCarteWagon.getSelectedIndex() != -1)
			{
				CarteWagon carteDestinationSelect = this.lstCarteWagon.getSelectedValue();
				this.alCarteWagon.remove(carteDestinationSelect);
				this.modelListCarteWagon.removeElement(carteDestinationSelect);
				this.concepteur.majIHM();
			}
		}
	}
}
