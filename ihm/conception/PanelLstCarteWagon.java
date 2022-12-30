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

	private JButton btnNouveau, btnModif, btnSuppr;
	
	private GridBagConstraints gbc = new GridBagConstraints();

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
		this.lstCarteWagon.setVisibleRowCount(3);
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
