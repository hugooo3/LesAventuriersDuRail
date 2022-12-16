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

public class PanelLstNoeud extends JPanel implements ActionListener, ListSelectionListener {
	private FrameNoeud frame;
	private File imagePathNoeud;

	private JButton btnSuivant;
	private JButton btnSuppr;
	private JButton btnModif;
	private JList<Noeud> lstNoeud;
	private ArrayList<Noeud> alstNoeud;
	private JScrollPane scrollPane;
	private DefaultListModel<Noeud> defaultListModel;

	public PanelLstNoeud(FrameNoeud frame, File imagePath, int largeur, int hauteur) {
		this.frame = frame;
		this.imagePathNoeud = imagePath;
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));
		this.setLayout(null);

		this.alstNoeud = new ArrayList<Noeud>();
		this.defaultListModel = new DefaultListModel<Noeud>();

		this.lstNoeud = new JList<Noeud>(this.defaultListModel);

		// Bouton suivant
		this.btnSuivant = new JButton("Suivant");
		this.btnSuivant.addActionListener(this);
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstNoeud);
		this.lstNoeud.setVisibleRowCount(3);
		this.lstNoeud.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
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
		this.add(this.scrollPane);
		this.scrollPane.setBounds((int) (largeur * 0.3) / 2 - 150, 50, 300, 400);
		this.add(this.btnSuppr);
		this.btnSuppr.setBounds((int) (largeur * 0.3) / 2 - 75, hauteur - 220, 150, 50);
		this.add(this.btnModif);
		this.btnModif.setBounds((int) (largeur * 0.3) / 2 - 175, hauteur - 120, 150, 50);
		this.add(this.btnSuivant);
		this.btnSuivant.setBounds((int) (largeur * 0.3) / 2 + 25, hauteur - 120, 150, 50);

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
			new FrameArete(this.frame.getApp(), this.imagePathNoeud);
			this.frame.dispose();
		}

		if (e.getSource() == this.btnSuppr) {
			if (lstNoeud.getSelectedIndex() != -1) {
				alstNoeud.remove(lstNoeud.getSelectedIndex());
				lstNoeud.setListData(alstNoeud.toArray(new Noeud[alstNoeud.size()]));
			} else {
				JOptionPane.showMessageDialog(null, "Veuillez selectionner un noeud");
			}

		}

	}

	public void majLstNoeuds(ArrayList<Noeud> alNoeud) 
	{
		for (Noeud noeud : alNoeud)
		{
			if (!this.defaultListModel.contains(noeud))
				this.defaultListModel.addElement(noeud);
		}
	}

	public void setLstNoeud(ArrayList<Noeud> alNoeud) {this.frame.setLstNoeud(alNoeud);}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		// TODO Auto-generated method stub
	}
}
