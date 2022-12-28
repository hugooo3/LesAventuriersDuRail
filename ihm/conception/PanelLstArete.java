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

	private ArrayList<Noeud> alNoeud;
	private JPanel panelPopUp;
	private JComboBox<Noeud> ddlstNoeud1;
	private JComboBox<Noeud> ddlstNoeud2;
	private JComboBox<CarteWagon> ddlstCouleur;
	private JTextField txtNbWagon;
	private JCheckBox cbVoieDouble;
	
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


		this.panelPopUp = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPopUp = new GridBagConstraints();
		gbcPopUp.insets = new Insets(5, 2, 5, 2);

		this.alNoeud = this.concepteur.getMetier().getAlNoeuds();
		ArrayList<CarteWagon> alCouleur = this.concepteur.getMetier().getAlCartesWagon();

		this.ddlstNoeud1 = new JComboBox<Noeud>(this.alNoeud.toArray(new Noeud[this.alNoeud.size()]));
		this.ddlstNoeud2 = new JComboBox<Noeud>(this.alNoeud.toArray(new Noeud[this.alNoeud.size()]));
		this.ddlstNoeud1.setPrototypeDisplayValue(new Noeud("XXXXXXXXXXXXXXXXXXXX", 0, 0));
		this.ddlstNoeud2.setPrototypeDisplayValue(new Noeud("XXXXXXXXXXXXXXXXXXXX", 0, 0));
		
		this.ddlstCouleur = new JComboBox<CarteWagon>(alCouleur.toArray(new CarteWagon[alCouleur.size()]));
		this.ddlstCouleur.removeItemAt(alCouleur.size() - 1); // Joker retire des choix possibles

		this.txtNbWagon = new JTextField("1", 5);
		this.cbVoieDouble = new JCheckBox("Voie double");

		// ComboBox Noeud 1
		gbcPopUp.gridx = 0;
		gbcPopUp.gridy = 0;
		this.panelPopUp.add(this.ddlstNoeud1, gbcPopUp);

		// ComboBox Noeud2
		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 0;
		this.panelPopUp.add(this.ddlstNoeud2, gbcPopUp);

		// ComboBox Couleur
		gbcPopUp.gridx = 0;
		gbcPopUp.gridy = 1;
		this.panelPopUp.add(this.ddlstCouleur, gbcPopUp);

		// TextField NbWagon
		gbcPopUp.gridx = 1;
		gbcPopUp.gridy = 1;
		this.panelPopUp.add(this.txtNbWagon, gbcPopUp);

		// CheckBox Voie Double
		gbcPopUp.gridx = 2;
		gbcPopUp.gridy = 1;
		this.panelPopUp.add(this.cbVoieDouble, gbcPopUp);



		// JList avec scroll
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.lstArete);
		this.lstArete.setVisibleRowCount(3);
		this.lstArete.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
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
		this.lstArete.clearSelection();
	}

	private void majComboBox()
	{
		this.ddlstNoeud1.removeAllItems();
		this.ddlstNoeud2.removeAllItems();
		for (Noeud noeud : this.alNoeud)
		{
			this.ddlstNoeud1.addItem(noeud);
			this.ddlstNoeud2.addItem(noeud);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnNouveau)
		{
			if (this.alNoeud.size() < 2)
			{
				JOptionPane.showMessageDialog(null, "Nombre de noeud insuffisant pour faire une arête", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}

			this.majComboBox();

			do 
			{
				this.ddlstNoeud1.setSelectedIndex(0);
				this.ddlstNoeud2.setSelectedIndex(1);
				int n = JOptionPane.showOptionDialog(this, this.panelPopUp, "Création d'une arête" ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				if ((Noeud)this.ddlstNoeud1.getSelectedItem() == (Noeud)this.ddlstNoeud2.getSelectedItem())
					JOptionPane.showMessageDialog(null, "Les deux noeuds sélectionnés sont identiques", "Erreur", JOptionPane.ERROR_MESSAGE);
				
				if (n != JOptionPane.OK_OPTION) // Cancel ou croix == annulation
					return;
			}
			while ((Noeud)this.ddlstNoeud1.getSelectedItem() == (Noeud)this.ddlstNoeud2.getSelectedItem());


			Noeud noeudSelected1 = (Noeud)this.ddlstNoeud1.getSelectedItem();
			Noeud noeudSelected2 = (Noeud)this.ddlstNoeud2.getSelectedItem();
			CarteWagon carteWagon = (CarteWagon)this.ddlstCouleur.getSelectedItem();
			int nbWagon = Integer.parseInt(this.txtNbWagon.getText());

			this.alAretes.add(new Arete(noeudSelected1, noeudSelected2, carteWagon, nbWagon, this.cbVoieDouble.isSelected()));
			this.concepteur.majIHM();
		}

		if (e.getSource() == this.btnModif)
		{
			if (this.lstArete.getSelectedIndex() != -1)
			{
				this.majComboBox();

				Arete areteSelected = this.lstArete.getSelectedValue();

				this.ddlstCouleur.setSelectedItem(areteSelected.getCouleur());
				this.txtNbWagon.setText(Integer.toString(areteSelected.getTroncons()));
				this.cbVoieDouble.setSelected(areteSelected.getVoieDouble());	

				do
				{
					this.ddlstNoeud1.setSelectedItem(areteSelected.getNoeud1());
					this.ddlstNoeud2.setSelectedItem(areteSelected.getNoeud2());

					int n = JOptionPane.showOptionDialog(this, this.panelPopUp, "Modification d'une arête" , JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

					if ((Noeud)this.ddlstNoeud1.getSelectedItem() == (Noeud)this.ddlstNoeud2.getSelectedItem())
						JOptionPane.showMessageDialog(null, "Les deux noeuds sélectionnés sont identiques", "Erreur", JOptionPane.ERROR_MESSAGE);

					if (n != JOptionPane.OK_OPTION) // Cancel ou croix == annulation
						return;
				}
				while ((Noeud)this.ddlstNoeud1.getSelectedItem() == (Noeud)this.ddlstNoeud2.getSelectedItem());

				areteSelected.setNoeud1((Noeud)this.ddlstNoeud1.getSelectedItem());
				areteSelected.setNoeud2((Noeud)this.ddlstNoeud2.getSelectedItem());
				areteSelected.setCouleur((CarteWagon)this.ddlstCouleur.getSelectedItem());
				areteSelected.setTroncons(Integer.parseInt(this.txtNbWagon.getText()));
				areteSelected.setVoieDouble(this.cbVoieDouble.isSelected());

				this.concepteur.majIHM();
			}
		}

		if (e.getSource() == this.btnSuppr)
		{	
			if (this.lstArete.getSelectedIndex() != -1)
			{
				Arete areteSelect = this.lstArete.getSelectedValue();
				this.alAretes.remove(areteSelect);
				this.modelListArete.removeElement(areteSelect);
				this.concepteur.majIHM();
			}
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
