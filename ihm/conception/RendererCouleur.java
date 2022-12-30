package ihm.conception;

import metier.*;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class RendererCouleur extends DefaultListCellRenderer 
{
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	{
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		Color couleur = Color.WHITE;
	
		if (value instanceof CarteWagon) 
		{
			CarteWagon carteWagon = (CarteWagon) value;
			couleur = carteWagon.getCouleur();
		}
		else if (value instanceof Arete)
		{
			Arete arete = (Arete) value;
			couleur = arete.getCouleur().getCouleur();
		}

		setBackground(couleur);

		// Définir la couleur du texte en fonction de la couleur de fond
		if (couleur.equals(Color.GREEN)) 
		{
			setForeground(Color.BLACK);
		}
		else if (couleur.getRed() + couleur.getGreen() + couleur.getBlue() < 3 * 128)
		{
			// Si la couleur est sombre (somme des composantes rouge, verte et bleue inférieure à 3 * 128),
			// définir la couleur du texte en blanc
			setForeground(Color.WHITE);
		} 
		else 
		{
			// Sinon, définir la couleur du texte en noir
			setForeground(Color.BLACK);
		}				
		
	
		return this;
	}
}