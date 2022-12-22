package ihm.conception;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ihm.FrameConcepteur;

import java.util.ArrayList;

import metier.Noeud;

public class PanelLstNoeud extends JPanel implements ActionListener, ListSelectionListener
{
	private FrameConcepteur concepteur;

	private JButton btnSuppr, btnModif;
	private JList<Noeud> lstNoeud;
	private ArrayList<Noeud> alstNoeud;
	private JScrollPane scrollPane;
	private DefaultListModel<Noeud> modelListNoeud;
	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelLstNoeud(FrameConcepteur concepteur, int largeur, int hauteur) 
	{
		this.concepteur = concepteur;
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

		this.gbc.insets = new Insets(5, 5, 5, 5);
		this.setLayout(new GridBagLayout());

		this.alstNoeud = this.concepteur.getMetier().getAlNoeuds();
		this.modelListNoeud = new DefaultListModel<Noeud>();
		this.lstNoeud = new JList<Noeud>(this.modelListNoeud);


		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstNoeud);
		this.lstNoeud.setVisibleRowCount(3);
		this.lstNoeud.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
		this.lstNoeud.setFixedCellHeight(50);

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
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnSuppr) 
		{
			if (this.lstNoeud.getSelectedIndex() != -1) 
			{
				this.removeLstNoeud(this.lstNoeud.getSelectedValue());
				this.concepteur.majIHM();
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "Veuillez selectionner un noeud");
			}
		}

		if (e.getSource() == this.btnModif && this.lstNoeud.getSelectedValue() != null)
		{
			
			JPanel panelPopUpModif = new JPanel(new GridBagLayout());
			GridBagConstraints gbcModif = new GridBagConstraints();
			gbcModif.insets = new Insets(5, 2, 5, 2);

			Noeud noeudSelect = this.lstNoeud.getSelectedValue();

			//TextField des attributs
			JTextField txtNom 	= new JTextField(noeudSelect.getNom(), 10);
			JTextField txtX 	= new JTextField(Integer.toString(noeudSelect.getX()), 5);
			JTextField txtY 	= new JTextField(Integer.toString(noeudSelect.getY()), 5);
			JTextField txtNomX 	= new JTextField(Integer.toString(noeudSelect.getNomX()), 5);
			JTextField txtNomY 	= new JTextField(Integer.toString(noeudSelect.getNomY()), 5);
			
			//Nom
			gbcModif.gridx = 0;
			gbcModif.gridy = 0;
			panelPopUpModif.add(new JLabel("Nom"), gbcModif);

			gbcModif.gridx = 0;
			gbcModif.gridy = 1;
			panelPopUpModif.add(txtNom, gbcModif);

			//Coord disque
			gbcModif.gridx = 0;
			gbcModif.gridy = 2;
			gbcModif.gridwidth = 3;
			gbcModif.fill = GridBagConstraints.HORIZONTAL;
			panelPopUpModif.add(new JLabel("Position du disque"), gbcModif);

			gbcModif.gridx = 0;
			gbcModif.gridy = 3;
			gbcModif.gridwidth = 1;
			gbcModif.fill = GridBagConstraints.NONE;
			panelPopUpModif.add(new JLabel("x "), gbcModif);

			gbcModif.gridx = 1;
			gbcModif.gridy = 3;
			panelPopUpModif.add(txtX, gbcModif);

			gbcModif.gridx = 2;
			gbcModif.gridy = 3;
			panelPopUpModif.add(new JLabel("y "), gbcModif);

			gbcModif.gridx = 3;
			gbcModif.gridy = 3;
			panelPopUpModif.add(txtY, gbcModif);
			
			//Coord nom
			gbcModif.gridx = 0;
			gbcModif.gridy = 4;
			gbcModif.gridwidth = 3;
			gbcModif.fill = GridBagConstraints.HORIZONTAL;
			panelPopUpModif.add(new JLabel("Position du nom"), gbcModif);

			gbcModif.gridx = 0;
			gbcModif.gridy = 5;
			gbcModif.gridwidth = 1;
			gbcModif.fill = GridBagConstraints.NONE;
			panelPopUpModif.add(new JLabel("x "), gbcModif);

			gbcModif.gridx = 1;
			gbcModif.gridy = 5;
			panelPopUpModif.add(txtNomX, gbcModif);

			gbcModif.gridx = 2;
			gbcModif.gridy = 5;
			panelPopUpModif.add(new JLabel("y "), gbcModif);

			gbcModif.gridx = 3;
			gbcModif.gridy = 5;
			panelPopUpModif.add(txtNomY, gbcModif);

			JOptionPane.showMessageDialog(null, panelPopUpModif);

			noeudSelect.setNom(txtNom.getText());
			noeudSelect.setX(Integer.parseInt(txtX.getText()) + 20); // Pas propre, c'est le radius du NoeudDessin qui faut ajouter
			noeudSelect.setY(Integer.parseInt(txtY.getText()) + 20); // Pas propre, c'est le radius du NoeudDessin qui faut ajouter
			noeudSelect.setNomDeltaX(Integer.parseInt(txtNomX.getText()) );
			noeudSelect.setNomDeltaY(Integer.parseInt(txtNomY.getText()));

			this.concepteur.modifierLstNoeud(this.lstNoeud.getSelectedIndex());
			this.lstNoeud.clearSelection();
			this.concepteur.majIHM();
		}

	}

	public void majLstNoeuds() 
	{
		for (Noeud noeud : this.alstNoeud)
		{
			if (!this.modelListNoeud.contains(noeud))
			{
				this.modelListNoeud.addElement(noeud);
			}
		}
	}

	public void removeLstNoeud(Noeud noeud)
	{		
		this.alstNoeud.remove(noeud);
		this.modelListNoeud.removeElement(noeud);
		this.concepteur.removeLstNoeud(noeud);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {}
}
