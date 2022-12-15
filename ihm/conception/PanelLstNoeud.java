package ihm.conception;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.ArrayList;

import metier.Noeud;

public class PanelLstNoeud extends JPanel implements ActionListener, ListSelectionListener {
	private FrameNoeud frame;

	private JButton btnSuivant;
	private JList<Noeud> lstNoeud;
	private ArrayList<Noeud> alstNoeud;

	public PanelLstNoeud(FrameNoeud frame, int largeur, int hauteur) {
		this.frame = frame;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));
		this.setLayout(null);

		alstNoeud = new ArrayList<Noeud>();

		ArrayList<Noeud> test = new ArrayList<Noeud>();
		test.add(new Noeud("Test", 1, 1));
		test.add(new Noeud("Test1", 1, 1));
		test.add(new Noeud("Test2", 1, 1));
		test.add(new Noeud("Test3", 1, 1));
		test.add(new Noeud("Test", 1, 1));
		test.add(new Noeud("Test", 1, 1));
		test.add(new Noeud("Test1", 1, 1));
		test.add(new Noeud("Test2", 1, 1));
		test.add(new Noeud("Test3", 1, 1));
		test.add(new Noeud("Test", 1, 1));
		this.lstNoeud = new JList(test.toArray());

		// this.lstNoeud = new JList<Noeud>();
		// Bouton suivant
		this.btnSuivant = new JButton("Suivant");
		// this.btnSuivant.addActionListener(this);
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);

		// JList avec scroll
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(lstNoeud);
		lstNoeud.setVisibleRowCount(3);
		lstNoeud.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		lstNoeud.setFixedCellHeight(50);

		// Bouton supprimer
		JButton btnSuppr = new JButton("Supprimer");
		btnSuppr.setBackground(Color.GRAY);
		btnSuppr.addActionListener(this);
		btnSuppr.setBorderPainted(false);
		btnSuppr.setFocusPainted(false);

		// Bouton Modifier
		JButton btnModif = new JButton("Modifier");
		btnModif.setBackground(Color.GRAY);
		btnModif.addActionListener(this);
		btnModif.setBorderPainted(false);
		btnModif.setFocusPainted(false);

		// Placement des composants
		this.add(scrollPane);
		scrollPane.setBounds((int) (largeur * 0.3) / 2 - 150, 50, 300, 400);
		this.add(btnSuppr);
		btnSuppr.setBounds((int) (largeur * 0.3) / 2 - 75, hauteur - 220, 150, 50);
		this.add(btnModif);
		btnModif.setBounds((int) (largeur * 0.3) / 2 - 175, hauteur - 120, 150, 50);
		this.add(this.btnSuivant);
		this.btnSuivant.setBounds((int) (largeur * 0.3) / 2 + 25, hauteur - 120, 150, 50);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Clic sur le bouton Suivant
		if (e.getSource() == this.btnSuivant) {

			ArrayList<Noeud> alNoeuds = new ArrayList<Noeud>(lstNoeud.getModel().getSize());

			for (int i = 0; i < lstNoeud.getModel().getSize(); i++) {
				alNoeuds.add(lstNoeud.getModel().getElementAt(i));
			}

			this.frame.creerAlNoeuds(alNoeuds);
			this.frame.verifMAJ("noeud");
			// new FrameArete(this.frame.getApp());
			this.frame.dispose();
		}

		if (e.getActionCommand() == "Supprimer") {

		}

	}

	public void majLstNoeuds(ArrayList<Noeud> alNoeud) {
		this.alstNoeud = alNoeud;
	}

	public void setLstNoeud(ArrayList<Noeud> alNoeud) {
		this.frame.setLstNoeud(alNoeud);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
}
