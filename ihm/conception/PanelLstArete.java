package ihm.conception;

import ihm.FrameConcepteur;
import metier.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.ArrayList;

public class PanelLstArete extends JPanel implements ActionListener 
{
	private FrameConcepteur concepteur;

	private JButton btnNouveau, btnModif, btnSuppr;
	private JList<Arete> lstArete;
	private ArrayList<Arete> alAretes;
	private JScrollPane scrollPane;
	private DefaultListModel<Arete> modelListArete;
	
	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelLstArete(FrameConcepteur concepteur, int largeur, int hauteur) 
	{
		this.concepteur = concepteur;

		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension((int) (largeur * 0.3), hauteur));

		this.alAretes = this.concepteur.getMetier().getAlAretes();

		this.gbc.insets = new Insets(5, 5, 5, 5);
		this.setLayout(new GridBagLayout());

		this.modelListArete = new DefaultListModel<Arete>();
		this.lstArete = new JList<Arete>(this.modelListArete);

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstArete);
		this.lstArete.setVisibleRowCount(3);
		this.lstArete.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
		this.lstArete.setFixedCellHeight(50);

		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstArete);
		this.lstArete.setVisibleRowCount(3);
		this.lstArete.setFont(new Font(getFont().getName(), Font.PLAIN, 20));
		this.lstArete.setFixedCellHeight(50);

		// Bouton Nouveau
		this.btnNouveau = new JButton("Nouveau");
		this.btnNouveau.setBackground(Color.GRAY);
		this.btnNouveau.addActionListener(this);
		this.btnNouveau.setBorderPainted(false);
		this.btnNouveau.setFocusPainted(false);

		// Bouton Modifier
		this.btnModif = new JButton("Modifier");
		this.btnModif.setBackground(Color.GRAY);
		this.btnModif.addActionListener(this);
		this.btnModif.setBorderPainted(false);
		this.btnModif.setFocusPainted(false);

		// Bouton supprimer
		this.btnSuppr = new JButton("Supprimer");
		this.btnSuppr.setBackground(Color.GRAY);
		this.btnSuppr.addActionListener(this);
		this.btnSuppr.setBorderPainted(false);
		this.btnSuppr.setFocusPainted(false);

		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.gridwidth = 3;
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.scrollPane, this.gbc);

		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.gridwidth = 1;
		this.add(this.btnNouveau, this.gbc);
		
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.add(this.btnModif, this.gbc);

		this.gbc.gridx = 2;
		this.gbc.gridy = 1;
		this.add(this.btnSuppr, this.gbc);
	}

	public void majLstArete() 
	{
		for (Arete arete : this.alAretes)
		{
			if (!this.modelListArete.contains(arete))
			{
				this.modelListArete.addElement(arete);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnNouveau)
		{
			JPanel panelPopUpNouveau = new JPanel(new GridBagLayout());
			GridBagConstraints gbcNouveau = new GridBagConstraints();
			gbcNouveau.insets = new Insets(5, 2, 5, 2);

			ArrayList<Noeud> alNoeud1 = this.concepteur.getMetier().getAlNoeuds();
			ArrayList<Noeud> alNoeud2 = this.concepteur.getMetier().getAlNoeuds();
			ArrayList<CarteWagon> alCouleur = this.concepteur.getMetier().getAlCartesWagon();

			JComboBox<Noeud> ddlstNoeud1 = new JComboBox<Noeud>(alNoeud1.toArray(new Noeud[alNoeud1.size()]));
			JComboBox<Noeud> ddlstNoeud2 = new JComboBox<Noeud>(alNoeud2.toArray(new Noeud[alNoeud2.size()]));
			ddlstNoeud1.setPrototypeDisplayValue(new Noeud("XXXXXXXXXXXXXXXXXXXX", 0, 0));
			ddlstNoeud2.setPrototypeDisplayValue(new Noeud("XXXXXXXXXXXXXXXXXXXX", 0, 0));

			
			JComboBox<CarteWagon> ddlstCouleur = new JComboBox<CarteWagon>(alCouleur.toArray(new CarteWagon[alCouleur.size()]));
			ddlstCouleur.removeItemAt(alCouleur.size() - 1); // Joker retire des choix possibles

			for (CarteWagon carteWagon : alCouleur)
				System.out.println(carteWagon);

			JTextField txtNbWagon = new JTextField("1", 5);
			JCheckBox cbVoieDouble = new JCheckBox("Voie double");

			// ComboBox Noeud 1
			gbcNouveau.gridx = 0;
			gbcNouveau.gridy = 0;
			panelPopUpNouveau.add(ddlstNoeud1, gbcNouveau);

			// ComboBox Noeud2
			gbcNouveau.gridx = 1;
			gbcNouveau.gridy = 0;
			panelPopUpNouveau.add(ddlstNoeud2, gbcNouveau);

			// ComboBox Couleur
			gbcNouveau.gridx = 0;
			gbcNouveau.gridy = 1;
			panelPopUpNouveau.add(ddlstCouleur, gbcNouveau);

			// TextField NbWagon
			gbcNouveau.gridx = 1;
			gbcNouveau.gridy = 1;
			panelPopUpNouveau.add(txtNbWagon, gbcNouveau);

			// CheckBox Voie Double
			gbcNouveau.gridx = 2;
			gbcNouveau.gridy = 1;
			panelPopUpNouveau.add(cbVoieDouble, gbcNouveau);


			JOptionPane.showOptionDialog(this, panelPopUpNouveau, "Création d'une arête" ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			Noeud noeudSelected1 = (Noeud)ddlstNoeud1.getSelectedItem();
			Noeud noeudSelected2 = (Noeud)ddlstNoeud2.getSelectedItem();
			CarteWagon carteWagon = (CarteWagon)ddlstCouleur.getSelectedItem();
			int nbWagon = Integer.parseInt(txtNbWagon.getText());

			this.alAretes.add(new Arete(noeudSelected1, noeudSelected2, carteWagon, nbWagon, cbVoieDouble.isSelected()));
			this.concepteur.majIHM();
		}

		if (e.getSource() == this.btnModif)
		{

		}

		if (e.getSource() == this.btnSuppr)
		{
			
		}
/* 		// Clic sur le bouton Suivant
		if (e.getSource() == this.btnSuivant) 
		{
			System.out.println("Clic sur le bouton suivant");
			ArrayList<Noeud> alAretes = new ArrayList<Noeud>(lstArete.getModel().getSize());

			for (int i = 0; i < lstArete.getModel().getSize(); i++) {
				alstArete.add(lstArete.getModel().getElementAt(i));
			}

			// this.frame.creeralAretes(alAretes);
			// this.frame.verifMAJ("noeud");
		} */
	}
}
