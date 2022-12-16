package ihm.conception;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import metier.Arete;
import metier.Noeud;

public class PanelLstArete extends JPanel implements ActionListener, ListSelectionListener {
	private FrameArete frame;

	private JButton btnSuivant;
		private JList<Arete> lstArete;
	private ArrayList<Arete> alstArete;

	public PanelLstArete(FrameArete frame, int largeur, int hauteur) {
		this.frame = frame;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));
		this.setLayout(null);

		alstArete = new ArrayList<Arete>();

		ArrayList<Arete> testArete = new ArrayList<Arete>();
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		testArete.add(new Arete(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), "rouge", 2, true));
		this.lstArete = new JList(testArete.toArray());


		this.btnSuivant = new JButton("Suivant");
		// this.btnSuivant.addActionListener(this);
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);

		// JList avec scroll
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(lstArete);
		lstArete.setVisibleRowCount(3);
		lstArete.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		lstArete.setFixedCellHeight(50);

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
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
