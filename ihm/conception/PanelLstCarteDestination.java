package ihm.conception;

import metier.*;
import ihm.FrameConcepteur;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;

public class PanelLstCarteDestination extends JPanel implements ActionListener
{
	private FrameConcepteur concepteur;

	private JList<CarteDestination> lstCarteDestination;
	private ArrayList<CarteDestination> alCarteDestination;

	private JScrollPane scrollPane;
	private DefaultListModel<CarteDestination> modelListCarteDestination;

	private JButton btnNouveau, btnModif, btnSuppr, btnAuto;
	
	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelLstCarteDestination(FrameConcepteur concepteur, int largeur, int hauteur) 
	{
		this.concepteur = concepteur;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

		this.alCarteDestination = this.concepteur.getMetier().getAlCartesDestination();

		this.gbc.insets = new Insets(5, 5, 5, 5);
		this.setLayout(new GridBagLayout());

		this.modelListCarteDestination = new DefaultListModel<CarteDestination>();
		this.lstCarteDestination = new JList<CarteDestination>(this.modelListCarteDestination);

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstCarteDestination);
		this.lstCarteDestination.setVisibleRowCount(3);
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

	public void majLstCarteDestination() 
	{
		for (CarteDestination carteDestination : this.alCarteDestination)
		{
			if (!this.modelListCarteDestination.contains(carteDestination))
			{
				this.modelListCarteDestination.addElement(carteDestination);
			}
		}
		this.lstCarteDestination.clearSelection();
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
			if (this.lstCarteDestination.getSelectedIndex() != -1)
			{
				CarteDestination carteDestinationSelect = this.lstCarteDestination.getSelectedValue();
				this.alCarteDestination.remove(carteDestinationSelect);
				this.modelListCarteDestination.removeElement(carteDestinationSelect);
				this.concepteur.majIHM();
			}
		}

		if (e.getSource() == this.btnAuto)
		{
			// @TODO
		}
	}
}
