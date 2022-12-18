package ihm.conception;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.File;
import java.util.ArrayList;

import metier.Noeud;

public class PanelLstNoeud extends JPanel implements ActionListener, ListSelectionListener
{
	private FrameNoeud frame;
	private File imagePathNoeud;

	private JButton btnSuivant, btnSuppr, btnModif;
	private JList<Noeud> lstNoeud;
	private ArrayList<Noeud> alstNoeud;
	private JScrollPane scrollPane;
	private DefaultListModel<Noeud> modelListNoeud;
	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelLstNoeud(FrameNoeud frame, File imagePath, int largeur, int hauteur) 
	{
		this.frame = frame;
		this.imagePathNoeud = imagePath;
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

		this.gbc.insets = new Insets(5, 5, 5, 5);
		this.setLayout(new GridBagLayout());

		this.alstNoeud = new ArrayList<Noeud>();
		this.modelListNoeud = new DefaultListModel<Noeud>();
		this.lstNoeud = new JList<Noeud>(this.modelListNoeud);


		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstNoeud);
		this.lstNoeud.setVisibleRowCount(3);
		this.lstNoeud.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
		this.lstNoeud.setFixedCellHeight(50);
		
		// Bouton suivant
		this.btnSuivant = new JButton("Suivant");
		this.btnSuivant.addActionListener(this);
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);

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
		// this.scrollPane.setBounds((int) (largeur * 0.3) / 2 - 150, 50, 300, 400);

		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.gbc.gridwidth = 1;
		this.gbc.fill = GridBagConstraints.VERTICAL;
		this.add(this.btnSuppr, this.gbc);
		// this.btnSuppr.setBounds((int) (largeur * 0.3) / 2 - 75, hauteur - 220, 150, 50);
		
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.add(this.btnModif, this.gbc);
		// this.btnModif.setBounds((int) (largeur * 0.3) / 2 - 175, hauteur - 120, 150, 50);
		
		this.gbc.gridx = 2;
		this.gbc.gridy = 1;
		this.add(this.btnSuivant, this.gbc);
		// this.btnSuivant.setBounds((int) (largeur * 0.3) / 2 + 25, hauteur - 120, 150, 50);

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Clic sur le bouton Suivant
		if (e.getSource() == this.btnSuivant) 
		{
			ArrayList<Noeud> alNoeuds = new ArrayList<Noeud>(lstNoeud.getModel().getSize());

			for (int i = 0; i < lstNoeud.getModel().getSize(); i++) 
				alNoeuds.add(lstNoeud.getModel().getElementAt(i));

			this.frame.creerAlNoeuds(alNoeuds);
			this.frame.verifMAJ("noeud");
			new FrameArete(this.frame.getApp(), this.imagePathNoeud, this.alstNoeud);
			this.frame.dispose();
		}

		if (e.getSource() == this.btnSuppr) 
		{
			if (lstNoeud.getSelectedIndex() != -1) 
			{
				alstNoeud.remove(lstNoeud.getSelectedIndex());
				lstNoeud.setListData(alstNoeud.toArray(new Noeud[alstNoeud.size()]));
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
			
			System.out.println(noeudSelect.aff());

			JOptionPane.showMessageDialog(null, panelPopUpModif);

			noeudSelect.setNom(txtNom.getText());
			noeudSelect.setX(Integer.parseInt(txtX.getText()));
			noeudSelect.setY(Integer.parseInt(txtY.getText()));
			noeudSelect.setNomDeltaX(Integer.parseInt(txtNomX.getText()));
			noeudSelect.setNomDeltaY(Integer.parseInt(txtNomY.getText()));

			System.out.println(noeudSelect.aff());
			this.setLstNoeud(this.alstNoeud);
			this.frame.majIHM();
		}

	}

	public void majLstNoeuds(ArrayList<Noeud> alNoeud) 
	{
		for (Noeud noeud : alNoeud)
		{
			if (!this.modelListNoeud.contains(noeud))
				this.modelListNoeud.addElement(noeud);
		}
	}

	public void setLstNoeud(ArrayList<Noeud> alNoeud) {this.frame.setLstNoeud(alNoeud);}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		// TODO Auto-generated method stub
	}
}
