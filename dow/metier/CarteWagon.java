package dow.metier;

public class CarteWagon {
	String couleur;
	String imgRecto;
	String imgVerso;

	public CarteWagon(String couleur, String imgRecto, String imgVerso)
	{
		this.couleur = couleur;
		this.imgRecto = imgRecto;
		this.imgVerso = imgVerso;
	}

	public String getCouleur()  {return this.couleur;}	 
	public String getImgRecto() {return this.imgRecto;}
	public String getImgVerso() {return this.imgVerso;}
}
