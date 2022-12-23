package ihm.conception;

import ihm.FrameConcepteur;
import metier.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class PanelLstArete extends JPanel implements ActionListener 
{
	private FrameConcepteur concepteur;

	private JButton btnNouveau, btnModif, btnSuppr;
	private JList<Arete> lstArete;
	private JScrollPane scrollPane;
	private ArrayList<Arete> alstArete;
	private DefaultListModel<Arete> modelListArete;
	
	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelLstArete(FrameConcepteur concepteur, int largeur, int hauteur) 
	{
		this.concepteur = concepteur;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

		this.gbc.insets = new Insets(5, 5, 5, 5);
		this.setLayout(new GridBagLayout());

		this.alstArete = new ArrayList<Arete>();
		this.modelListArete = new DefaultListModel<Arete>();
		this.lstArete = new JList<Arete>(this.modelListArete);

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstArete);
		this.lstArete.setVisibleRowCount(3);
		this.lstArete.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
		this.lstArete.setFixedCellHeight(50);

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstArete);
		this.lstArete.setVisibleRowCount(3);
		this.lstArete.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		this.lstArete.setFixedCellHeight(50);

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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
/* 		// Clic sur le bouton Suivant
		if (e.getSource() == this.btnSuivant) 
		{
			System.out.println("Clic sur le bouton suivant");
			ArrayList<Noeud> alAretes = new ArrayList<Noeud>(lstArete.getModel().getSize());

			for (int i = 0; i < lstArete.getModel().getSize(); i++) {
				alstArete.add(lstArete.getModel().getElementAt(i));
			}

			// this.frame.creeralAretes(alAretes);
			// this.frame.verifMAJ("noeud");
		} */
	}
}
