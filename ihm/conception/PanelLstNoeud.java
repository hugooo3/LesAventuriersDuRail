package ihm.conception;

import java.awt.*;

import javax.swing.JList;
import javax.swing.JPanel;
import java.util.ArrayList;

import metier.Noeud;

public class PanelLstNoeud extends JPanel {
	private JList<Noeud> lstNoeud;

	public PanelLstNoeud(int largeur, int hauteur) {

		ArrayList<Noeud> test = new ArrayList<Noeud>();
		test.add(new Noeud("Test", 1, 1));
		test.add(new Noeud("Test1", 1, 1));
		test.add(new Noeud("Test2", 1, 1));
		test.add(new Noeud("Test3", 1, 1));
		this.setLayout(new BorderLayout());
		this.lstNoeud = new JList(test.toArray());

		// this.lstNoeud.setPreferredSize(new Dimension((int) (largeur * 0.3),
		//
		// hauteur));
		this.add(this.lstNoeud, BorderLayout.CENTER);
	}
}
