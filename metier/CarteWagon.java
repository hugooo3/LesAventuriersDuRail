package metier;

import javax.swing.ImageIcon;

public class CarteWagon 
{
	private String couleur;
	private ImageIcon imgRecto;
	private ImageIcon imgVerso;

	public CarteWagon(String couleur, ImageIcon imgRecto, ImageIcon imgVerso)
	{
		this.couleur = couleur;
		this.imgRecto = imgRecto;
		this.imgVerso = imgVerso;
	}

	public String getCouleur() {return this.couleur;}	 
	public ImageIcon getImgRecto() {return this.imgRecto;}
	public ImageIcon getImgVerso() {return this.imgVerso;}

	public void setCouleur(String couleur) {this.couleur = couleur;}
	public void setImgRecto(ImageIcon imgRecto) {this.imgRecto = imgRecto;}
	public void setImgVerso(ImageIcon imgVerso) {this.imgVerso = imgVerso;}


	@Override
	public String toString() 
	{
		return this.couleur;
	}

}
