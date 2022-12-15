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
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur - 20));
		this.setLayout(null);

		ArrayList<Noeud> test = new ArrayList<Noeud>();
		test.add(new Noeud("Test", 1, 1));
		test.add(new Noeud("Test1", 1, 1));
		test.add(new Noeud("Test2", 1, 1));
		test.add(new Noeud("Test3", 1, 1));
		test.add(new Noeud("Test", 1, 1));
		this.setLayout(new BorderLayout());
		this.lstNoeud = new JList(test.toArray());

		this.btnSuivant = new JButton("Suivant");
		// this.btnSuivant.addActionListener(this);
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(lstNoeud);
		lstNoeud.setLayoutOrientation(JList.VERTICAL);
		lstNoeud.setVisibleRowCount(3);
		lstNoeud.setBounds((int) (largeur * 0.3) / 2 - 100, 40, 200, 20);
		this.add(scrollPane);
		this.add(this.btnSuivant);
		this.btnSuivant.setBounds((int) (largeur * 0.3) / 2 - 100, 300, 200, 20);

		// scrollPane.setSize(new Dimension(200, 300));
		// scrollPane.setBounds((int) (largeur * 0.3) / 2 - 100, 40, 200, 20);

		// this.lstNoeud.setPreferredSize(new Dimension((int) (largeur * 0.3),
		//
		// hauteur));
		// this.add(this.lstNoeud, BorderLayout.CENTER);
	}
}
