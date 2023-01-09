package metier;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.InputStream;
import java.io.File;

public class CarteWagon {
	private String nomCouleur;
	private Color couleur;
	private ImageIcon imgRecto;
	private String imgRectoPath;
	private ImageIcon imgVerso;
	private String imgVersoPath;
	private int nbCarteWagon;

	public CarteWagon(String nomCouleur, Color couleur, String imgRectoPath, String imgVersoPath, int nbCarteWagon) {
		this.nomCouleur = nomCouleur;
		this.couleur = couleur;
		this.nbCarteWagon = nbCarteWagon;
		this.imgRecto = this.creerImageIcon(imgRectoPath);
		this.imgVerso = this.creerImageIcon(imgVersoPath);
		this.imgRectoPath = imgRectoPath;
		this.imgVersoPath = imgVersoPath;
	}

	public CarteWagon(String nomCouleur, Color couleur, File imgRectoFile, File imgVersoFile, int nbCarteWagon, String imgRectoPath, String imgVersoPath) {
		this.nomCouleur = nomCouleur;
		this.couleur = couleur;
		this.nbCarteWagon = nbCarteWagon;
		this.imgRecto = this.creerImageIcon(imgRectoFile);
		this.imgVerso = this.creerImageIcon(imgVersoFile);
		this.imgRectoPath = imgRectoPath;
		this.imgVersoPath = imgVersoPath;
	}

	private ImageIcon creerImageIcon(String imgPath) {
		if (imgPath == null || imgPath.equals("") )
			return null;

		ImageIcon image = null;
		try 
		{
			InputStream inputStreamImg = getClass().getResourceAsStream(imgPath);
			BufferedImage img = ImageIO.read(inputStreamImg);
			image = new ImageIcon(img);
		} catch (Exception e) {e.printStackTrace();}
		return image;
	}

	private ImageIcon creerImageIcon(File imgFile) {
		if (imgFile == null || imgFile.equals("") )
			return null;

		ImageIcon image = null;
		try 
		{
			Image img = ImageIO.read(imgFile);
			image = new ImageIcon(img);
		} catch (Exception e) {e.printStackTrace();}
		return image;
	}


	public String	 getNomCouleur()   { return this.nomCouleur; }
	public Color 	 getCouleur()	   { return this.couleur; }
	public ImageIcon getImgRecto() 	   { return this.imgRecto; }
	public String	 getImgRectoPath() { return this.imgRectoPath; }
	public ImageIcon getImgVerso() 	   { return this.imgVerso; }
	public String	 getImgVersoPath() { return this.imgVersoPath; }
	public int		 getNbCarteWagon() { return this.nbCarteWagon; }

	public void setNomCouleur(String couleur)	  { this.nomCouleur = couleur; }
	public void setCouleur(Color couleur) 		  { this.couleur = couleur; }
	public void setNbCarteWagon(int nbCarteWagon) { this.nbCarteWagon = nbCarteWagon; }

	public void setImgRecto(ImageIcon imgRecto, String imgRectoPath)   {
		this.imgRecto = imgRecto;
		this.imgRectoPath = imgRectoPath;
		}
	public void setImgRecto(String imgRectoPath)   {
		this.imgRecto = this.creerImageIcon(imgRectoPath);
		this.imgRectoPath = imgRectoPath;
	}
	public void setImgVerso(ImageIcon imgVerso, String imgVersoPath)   {
		this.imgVerso = imgVerso;
		this.imgVersoPath = imgVersoPath;
	}
	public void setImgVerso(String imgVersoPath) { 
		this.imgVerso = this.creerImageIcon(imgVersoPath); 
		this.imgVersoPath = imgVersoPath;
	}

	public boolean removeNbCarteWagon(int nbCarteWagon) {
		if (this.nbCarteWagon < nbCarteWagon)
			return false;
		else
		{
			this.nbCarteWagon -= nbCarteWagon;
			return true;
		}
	}
	
	public String toString() {
		return this.nomCouleur;
	} 

}
