package metier;

public class Noeud 
{
	protected String nom;
	protected int x;
	protected int y;

	protected int nomDeltaX; // Distance entre les coords du nom et le point
	protected int nomDeltaY; // Distance entre les coords du nom et le point

	public Noeud(String nom, int x, int y) 
	{
		this.nom = nom;
		this.x = x;
		this.y = y;
	}

	public String getNom() {return this.nom;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getNomDeltaX() {return this.nomDeltaX;}
	public int getNomDeltaY() {return this.nomDeltaY;}	

	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}	
	public void setNomDeltaX(int nomDeltaX) {this.nomDeltaX = nomDeltaX;}
	public void setNomDeltaY(int nomDeltaY) {this.nomDeltaY = nomDeltaY;}
	public void setNom(String nom) {this.nom = nom;}

	@Override
	public String toString() 
	{
		return this.nom;
	}
}
