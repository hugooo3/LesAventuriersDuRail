package ihm.conception;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;

import metier.Noeud;

public class PanelLstNoeud extends JPanel {
	private JButton btnSuivant;
	private JList<Noeud> lstNoeud;

	public PanelLstNoeud(int largeur, int hauteur) {

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));
		this.setLayout(null);

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

		// Bouton suivant
		this.btnSuivant = new JButton("Suivant");
		// this.btnSuivant.addActionListener(this);
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);

		// JList avec scroll
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(lstNoeud);
		lstNoeud.setLayoutOrientation(JList.VERTICAL);
		lstNoeud.setVisibleRowCount(3);
		lstNoeud.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		lstNoeud.setFixedCellHeight(50);

		// Bouton supprimer
		JButton btnSuppr = new JButton("Supprimer");
		btnSuppr.setBackground(Color.GRAY);
		// btnSuppr.addActionListener(this);
		btnSuppr.setBorderPainted(false);
		btnSuppr.setFocusPainted(false);

		// Bouton Modifier
		JButton btnModif = new JButton("Modifier");
		btnModif.setBackground(Color.GRAY);
		// btnModif.addActionListener(this);
		btnModif.setBorderPainted(false);
		btnModif.setFocusPainted(false);

		this.add(scrollPane);
		scrollPane.setBounds((int) (largeur * 0.3) / 2 - 150, 50, 300, 400);
		this.add(btnSuppr);
		btnSuppr.setBounds((int) (largeur * 0.3) / 2 - 75, hauteur - 220, 150, 50);
		this.add(btnModif);
		btnModif.setBounds((int) (largeur * 0.3) / 2 - 175, hauteur - 120, 150, 50);
		this.add(this.btnSuivant);
		this.btnSuivant.setBounds((int) (largeur * 0.3) / 2 + 25, hauteur - 120, 150, 50);

		// scrollPane.setSize(new Dimension(200, 300));

		// this.lstNoeud.setPreferredSize(new Dimension((int) (largeur * 0.3),
		//
		// hauteur));
		// this.add(this.lstNoeud, BorderLayout.CENTER);
	}
}
