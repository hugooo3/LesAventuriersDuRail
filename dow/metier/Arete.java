package dow.metier;

public class Arete 
{
	Noeud noeud1;
	Noeud noeud2;
	String     couleur;
	int       troncons;
	boolean voieDouble;

	public Arete(Noeud noeud1, Noeud noeud2, String couleur, int troncons, boolean voieDouble)
	{
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		this.couleur    = couleur;
		this.troncons   = troncons;
		this.voieDouble = voieDouble;
	}

	public Noeud    getNoeud1() 	{return this.noeud1;}
	public Noeud    getNoeud2() 	{return this.noeud2;}
	public String   getCouleur() 	{return this.couleur;}
	public int      getTroncons() 	{return this.troncons;}
	public boolean  getVoieDouble() {return this.voieDouble;}

	public String toString() {
		return "{" +
			" noeud1='" + getNoeud1() + "'" +
			", noeud2='" + getNoeud2() + "'" +
			", couleur='" + getCouleur() + "'" +
			", troncons='" + getTroncons() + "'" +
			"}";
	}
}
