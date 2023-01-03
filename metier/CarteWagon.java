package metier;

import javax.swing.ImageIcon;
import java.awt.Color;

public class CarteWagon {
	private String nomCouleur;
	private Color couleur;
	private ImageIcon imgRecto;
	private ImageIcon imgVerso;
	private int nbCarteWagon;

	public CarteWagon(String nomCouleur, Color couleur, ImageIcon imgRecto, ImageIcon imgVerso, int nbCarteWagon) {
		this.nomCouleur = nomCouleur;
		this.couleur = couleur;
		this.imgRecto = imgRecto;
		this.imgVerso = imgVerso;
		this.nbCarteWagon = nbCarteWagon;
	}

	public String	 getNomCouleur()   { return this.nomCouleur; }
	public Color 	 getCouleur()	   { return this.couleur; }
	public ImageIcon getImgRecto() 	   { return this.imgRecto; }
	public ImageIcon getImgVerso() 	   { return this.imgVerso; }
	public int		 getNbCarteWagon() { return this.nbCarteWagon; }

	public void setNomCouleur(String couleur)	  { this.nomCouleur = couleur; }
	public void setCouleur(Color couleur) 		  { this.couleur = couleur; }
	public void setImgRecto(ImageIcon imgRecto)   { this.imgRecto = imgRecto; }
	public void setImgVerso(ImageIcon imgVerso)   { this.imgVerso = imgVerso; }
	public void setNbCarteWagon(int nbCarteWagon) { this.nbCarteWagon = nbCarteWagon; }
 
	public String toString() {
		return this.nomCouleur;
	}

}
