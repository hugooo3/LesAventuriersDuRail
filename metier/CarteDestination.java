package metier;

public class CarteDestination {
	private Noeud noeud1;
	private Noeud noeud2;
	private int   points;
	private String imgRecto;
	private String imgVerso;

	public CarteDestination(Noeud noeud1, Noeud noeud2, int points, String imgRecto, String imgVerso)
	{
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		this.points = points;
		this.imgRecto = imgRecto;
		this.imgVerso = imgVerso;
	}

	public Noeud getNoeud1() {return this.noeud1;}
	public Noeud getNoeud2() {return this.noeud2;}
	public int getPoints() {return this.points;}
	public String getImgRecto() {return this.imgRecto;}
	public String getImgVerso() {return this.imgVerso;}

	public void setNoeud1(Noeud noeud1) {this.noeud1 = noeud1;}
	public void setNoeud2(Noeud noeud2) {this.noeud2 = noeud2;}
	public void setPoints(int points) {this.points = points;}
	public void setImgRecto(String imgRecto) {this.imgRecto = imgRecto;}
	public void setImgVerso(String imgVerso) {this.imgVerso = imgVerso;}
}
