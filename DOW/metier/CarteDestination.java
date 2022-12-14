package metier;

public class CarteDestination {
	Noeud noeud1;
	Noeud noeud2;
	int   points;
	String imgRecto;
	String imgVerso;

	public CarteDestination(Noeud noeud1, Noeud noeud2, int points, String imgRecto, String imgVerso)
	{
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		this.points = points;
		this.imgRecto = imgRecto;
		this.imgVerso = imgVerso;
	}

	public Noeud    getNoeud1()   {return this.noeud1;}
	public Noeud    getNoeud2()   {return this.noeud2;}
	public int      getPoints()   {return this.points;}
	public String   getImgRecto() {return this.imgRecto;}
	public String   getImgVerso() {return this.imgVerso;}
}
