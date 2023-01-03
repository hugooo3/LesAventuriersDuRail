package ihm.conception;

import metier.*;

import java.awt.*;
import javax.swing.*;


public class RendererCouleur extends DefaultListCellRenderer 
{
	private JLabel lblTxt;
	private JLabel lblNbWagon;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	{
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		Color couleur = Color.WHITE;
		// Création du panel contenant le texte et les deux images
		// Utilisation du panel comme composant du renderer
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 4));

		this.lblTxt = new JLabel();
		this.lblNbWagon = new JLabel();
	
		if (value instanceof CarteWagon) 
		{
			CarteWagon carteWagon = (CarteWagon) value;
			couleur = carteWagon.getCouleur();					
			// Redimensionnement des ImageIcon
			int width = 50;
			int height = 50;
			
			this.lblTxt.setText(carteWagon.toString());
			this.lblNbWagon.setText("Nb carte : " + Integer.toString(carteWagon.getNbCarteWagon()));
			panel.add(this.lblTxt);
			panel.add(this.lblNbWagon);

			Image imgRecto = null;
			if (carteWagon.getImgRecto() != null)
			{
				imgRecto = carteWagon.getImgRecto().getImage();
				imgRecto = imgRecto.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon imgRectoResized = new ImageIcon(imgRecto);
				panel.add(new JLabel(imgRectoResized));
			}
			
			Image imgVerso = null;
			if (carteWagon.getImgVerso() != null)
			{
				imgVerso = carteWagon.getImgVerso().getImage();
				imgVerso = imgVerso.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon imgVersoResized = new ImageIcon(imgVerso);
				panel.add(new JLabel(imgVersoResized));
			}
		}
		if (value instanceof Arete)
		{
			Arete arete = (Arete) value;
			couleur = arete.getCouleur().getCouleur();
			this.lblTxt.setText(arete.toString());
			panel.add(this.lblTxt);
		}

		panel.setBackground(couleur);

		// Définir la couleur du texte en fonction de la couleur de fond
		if (couleur.equals(Color.GREEN)) 
		{
			this.lblTxt.setForeground(Color.BLACK);
			this.lblNbWagon.setForeground(Color.BLACK);
		}
		else if (couleur.getRed() + couleur.getGreen() + couleur.getBlue() < 3 * 128)
		{
			// Si la couleur est sombre (somme des composantes rouge, verte et bleue inférieure à 3 * 128),
			// définir la couleur du texte en blanc
			this.lblTxt.setForeground(Color.WHITE);
			this.lblNbWagon.setForeground(Color.WHITE);
		} 
		else 
		{
			// Sinon, définir la couleur du texte en noir
			this.lblTxt.setForeground(Color.BLACK);
			this.lblNbWagon.setForeground(Color.BLACK);
		}				
		
	
		return panel;
	}
}