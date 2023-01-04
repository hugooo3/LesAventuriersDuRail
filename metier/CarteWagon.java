package metier;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.io.InputStream;

public class CarteWagon {
	private String nomCouleur;
	private Color couleur;
	private ImageIcon imgRecto;
	private ImageIcon imgVerso;
	private int nbCarteWagon;

	public CarteWagon(String nomCouleur, Color couleur, String imgRectoPath, String imgVersoPath, int nbCarteWagon) {
		this.nomCouleur = nomCouleur;
		this.couleur = couleur;
		this.nbCarteWagon = nbCarteWagon;
		this.imgRecto = this.creerImageIcon(imgRectoPath);
		this.imgVerso = this.creerImageIcon(imgVersoPath);
	}

	private ImageIcon creerImageIcon(String imgPath) {
		if (imgPath == null)
			return null;

		ImageIcon image = null;
		try 
		{
			InputStream inputStreamImg = Metier.class.getResourceAsStream(imgPath);
			image = new ImageIcon(ImageIO.read(inputStreamImg));
		} catch (Exception e) {e.printStackTrace();}
		return image;
	}

	public String	 getNomCouleur()   { return this.nomCouleur; }
	public Color 	 getCouleur()	   { return this.couleur; }
	public ImageIcon getImgRecto() 	   { return this.imgRecto; }
	public ImageIcon getImgVerso() 	   { return this.imgVerso; }
	public int		 getNbCarteWagon() { return this.nbCarteWagon; }

	public void setNomCouleur(String couleur)	  { this.nomCouleur = couleur; }
	public void setCouleur(Color couleur) 		  { this.couleur = couleur; }
	public void setImgRecto(ImageIcon imgRecto)   { this.imgRecto = imgRecto; }
	public void setImgRecto(String imgRectoPath)   { this.imgRecto = this.creerImageIcon(imgRectoPath); }
	public void setImgVerso(ImageIcon imgVerso)   { this.imgVerso = imgVerso; }
	public void setImgVerso(String imgVersoPath)   { this.imgVerso = this.creerImageIcon(imgVersoPath); }
	public void setNbCarteWagon(int nbCarteWagon) { this.nbCarteWagon = nbCarteWagon; }
 
	public String toString() {
		return this.nomCouleur;
	}

}
