package metier;

public class CarteWagon {
	private String couleur;
	private String imgRecto;
	private String imgVerso;

	public CarteWagon(String couleur, String imgRecto, String imgVerso)
	{
		this.couleur = couleur;
		this.imgRecto = imgRecto;
		this.imgVerso = imgVerso;
	}

	public String getCouleur() {return this.couleur;}	 
	public String getImgRecto() {return this.imgRecto;}
	public String getImgVerso() {return this.imgVerso;}

	public void setCouleur(String couleur) {this.couleur = couleur;}
	public void setImgRecto(String imgRecto) {this.imgRecto = imgRecto;}
	public void setImgVerso(String imgVerso) {this.imgVerso = imgVerso;}
}
