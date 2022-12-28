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
		this.nomDeltaX = 0;
		this.nomDeltaY = -20;
	}

	public String getNom() {return this.nom;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getNomDeltaX() {return this.nomDeltaX;}
	public int getNomDeltaY() {return this.nomDeltaY;}
	public int getNomX() {return this.x + this.nomDeltaX;}
	public int getNomY() {return this.y + this.nomDeltaY;}	

	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}	
	public void setNomDeltaX(int nomX) {this.nomDeltaX = nomX - this.x;}
	public void setNomDeltaY(int nomY) {this.nomDeltaY = nomY - this.y;}
	public void setNom(String nom) {this.nom = nom;}

	@Override
	public String toString() 
	{
		return this.nom;
	}
}
