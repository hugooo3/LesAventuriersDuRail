package dow.metier;

public class Noeud 
{
	String nom;
	int x;
	int y;

	public Noeud(String nom, int x, int y)
	{
		this.nom = nom;
		this.x = x;
		this.y = y;
	}

	public String getNom() 	{return this.nom;}
	public int 	  getX() 	{return this.x;}
	public int 	  getY() 	{return this.y;}

	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}

	@Override
	public String toString() 
	{
		return "{" +
			" nom='" + getNom() + "'" +
			", x='" + getX() + "'" +
			", y='" + getY() + "'" +
			"}";
	}

}