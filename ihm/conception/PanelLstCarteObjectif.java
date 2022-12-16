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
import java.io.File;

import metier.Arete;
import metier.Noeud;
import metier.CarteDestination;

public class PanelLstCarteObjectif extends JPanel implements ActionListener, ListSelectionListener {
	private FrameCarteObjectif frame;
	private File imagePathArete;

	private JButton btnSuivant;
	private JList<CarteDestination> lstCarteObjectif;
	private ArrayList<CarteDestination> alstCarteObjectif;

	public PanelLstCarteObjectif(FrameCarteObjectif frame, File imagePath, int largeur, int hauteur) {
		this.frame = frame;
		this.imagePathArete = imagePath;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));
		this.setLayout(null);

		alstCarteObjectif = new ArrayList<CarteDestination>();

		ArrayList<CarteDestination> testArete = new ArrayList<CarteDestination>();
		testArete.add(new CarteDestination(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), 20, null, null));
		testArete.add(new CarteDestination(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), 20, null, null));
		testArete.add(new CarteDestination(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), 20, null, null));
		testArete.add(new CarteDestination(new Noeud("test", 1, 1), new Noeud("test2", 10, 10), 20, null, null));
		this.lstCarteObjectif = new JList(testArete.toArray());

		// Bouton Suivant
		this.btnSuivant = new JButton("Suivant");
		this.btnSuivant.addActionListener(this);
		this.btnSuivant.setBackground(Color.GRAY);
		this.btnSuivant.setBorderPainted(false);
		this.btnSuivant.setFocusPainted(false);

		// JList avec scroll
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(lstCarteObjectif);
		lstCarteObjectif.setVisibleRowCount(3);
		lstCarteObjectif.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		lstCarteObjectif.setFixedCellHeight(50);

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
		// Clic sur le bouton Suivant
		if (e.getSource() == this.btnSuivant) {
			System.out.println("Clic sur le bouton suivant");
			ArrayList<Noeud> alAretes = new ArrayList<Noeud>(lstCarteObjectif.getModel().getSize());

			for (int i = 0; i < lstCarteObjectif.getModel().getSize(); i++) {
				alstCarteObjectif.add(lstCarteObjectif.getModel().getElementAt(i));
			}

			// this.frame.creeralAretes(alAretes);
			// this.frame.verifMAJ("noeud");
			// new FrameCarteObjectif(this.frame.getApp(), this.imagePathArete);
			this.frame.dispose();
		}

	}
}
