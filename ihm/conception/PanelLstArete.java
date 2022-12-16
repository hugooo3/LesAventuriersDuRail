package ihm.conception;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;

import metier.Arete;
import metier.Noeud;

public class PanelLstArete extends JPanel implements ActionListener, ListSelectionListener 
{
	private FrameArete frame;
	private File imagePathArete;

	private JButton btnSuivant, btnSuppr, btnModif;
	private JList<Arete> lstArete;
	private JScrollPane scrollPane;
	private ArrayList<Arete> alstArete;
	private DefaultListModel<Arete> modelListArete;
	
	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelLstArete(FrameArete frame, File imagePath, int largeur, int hauteur) 
	{
		this.frame = frame;
		this.imagePathArete = imagePath;

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

		// Bouton Suivant
		this.btnSuivant = new JButton("Suivant");
		this.btnSuivant.addActionListener(this);
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstArete);
		this.lstArete.setVisibleRowCount(3);
		this.lstArete.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		this.lstArete.setFixedCellHeight(50);

		// Bouton supprimer
		this.btnSuppr = new JButton("Supprimer");
		this.btnSuppr.setBackground(Color.GRAY);
		this.btnSuppr.addActionListener(this);
		this.btnSuppr.setBorderPainted(false);
		this.btnSuppr.setFocusPainted(false);

		// Bouton Modifier
		this.btnModif = new JButton("Modifier");
		this.btnModif.setBackground(Color.GRAY);
		this.btnModif.addActionListener(this);
		this.btnModif.setBorderPainted(false);
		this.btnModif.setFocusPainted(false);

		// Placement des composants
		/* this.add(scrollPane);
		scrollPane.setBounds((int) (largeur * 0.3) / 2 - 150, 50, 300, 400);
		this.add(this.btnSuppr);
		this.btnSuppr.setBounds((int) (largeur * 0.3) / 2 - 75, hauteur - 220, 150, 50);
		this.add(this.btnModif);
		this.btnModif.setBounds((int) (largeur * 0.3) / 2 - 175, hauteur - 120, 150, 50);
		this.add(this.btnSuivant);
		this.btnSuivant.setBounds((int) (largeur * 0.3) / 2 + 25, hauteur - 120, 150, 50); */

		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.gridwidth = 3;
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.scrollPane, this.gbc);

		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.gbc.gridwidth = 1;
		this.gbc.fill = GridBagConstraints.VERTICAL;
		this.add(this.btnSuppr, this.gbc);
		
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.add(this.btnModif, this.gbc);
		
		this.gbc.gridx = 2;
		this.gbc.gridy = 1;
		this.add(this.btnSuivant, this.gbc);

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Clic sur le bouton Suivant
		if (e.getSource() == this.btnSuivant) 
		{
			System.out.println("Clic sur le bouton suivant");
			ArrayList<Noeud> alAretes = new ArrayList<Noeud>(lstArete.getModel().getSize());

			for (int i = 0; i < lstArete.getModel().getSize(); i++) {
				alstArete.add(lstArete.getModel().getElementAt(i));
			}

			// this.frame.creeralAretes(alAretes);
			// this.frame.verifMAJ("noeud");
			new FrameCarteObjectif(this.frame.getApp(), this.imagePathArete);
			this.frame.dispose();
		}

	}
}
