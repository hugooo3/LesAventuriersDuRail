package metier;

public class Arete implements Comparable<Arete>{
	// Un même joueur ne peut pas prendre 2 routes reliant les 2 mêmes villes.
	private Noeud      noeud1;
	private Noeud 	   noeud2;
	private CarteWagon couleur;
	private CarteWagon couleurVoieDouble;
	private int		   troncons;
	private boolean	   voieDouble;
	private Joueur	   joueurVoieSimple;
	private Joueur     joueurVoieDouble;
	private boolean voieSimplePossede = false;
	private boolean voieDoublePossede = false;

	public Arete(Noeud noeud1, Noeud noeud2, CarteWagon couleur, int troncons,
			boolean voieDouble, CarteWagon couleurVoieDouble) {
		this.noeud1 			= noeud1;
		this.noeud2 			= noeud2;
		this.couleur 		    = couleur;
		this.troncons		    = troncons;
		this.voieDouble		    = voieDouble;
		this.couleurVoieDouble  = couleurVoieDouble;
		this.joueurVoieSimple	= null;
		this.joueurVoieDouble   = null;
	}

	public Noeud	  getNoeud1() 			  { return this.noeud1; }
	public Noeud 	  getNoeud2() 			  { return this.noeud2; }
	public CarteWagon getCouleur() 			  { return this.couleur; }
	public int 	      getTroncons() 		  { return this.troncons; }
	public boolean	  getVoieDouble() 		  { return this.voieDouble; }
	public CarteWagon getCouleurDoubleVoie()  { return this.couleurVoieDouble; }
	public Joueur     getJoueurVoieSimple()   { return this.joueurVoieSimple; }
	public Joueur     getJoueurVoieDouble()   { return this.joueurVoieDouble; }
	public boolean	  getVoieSimplePossede() { return this.voieSimplePossede; }
	public boolean 	  getVoieDoublePossede() { return this.voieDoublePossede; }

	public void setNoeud1(Noeud noeud1) 						   { this.noeud1 = noeud1; }
	public void setNoeud2(Noeud noeud2)							   { this.noeud2 = noeud2; }
	public void setCouleur(CarteWagon couleur)					   { this.couleur = couleur; } 
	public void setTroncons(int troncons) 						   { this.troncons = troncons; }
	public void setVoieDouble(boolean voieDouble) 				   { this.voieDouble = voieDouble; }
	public void setCouleurDoubleVoie(CarteWagon couleurVoieDouble) { this.couleurVoieDouble = couleurVoieDouble; }
	public void setJoueurVoieSimple(Joueur joueur) 				   { this.joueurVoieSimple = joueur; }
	public void setJoueurVoieDouble(Joueur joueur)				   {this.joueurVoieDouble = joueur; }
	public boolean setVoieSimplePossede() { return this.voieSimplePossede = true; }
	public boolean setVoieDoublePossede() { return this.voieDoublePossede = true; }

	public String toString() {
		return noeud1.getNom() + " - " + noeud2.getNom() + " | " + getCouleur() + " | " + this.troncons + " | " +
				(getCouleurDoubleVoie() == null ? "" : getCouleurDoubleVoie());
	}

	@Override
	public int compareTo(Arete arg0) {return this.noeud1.getNom().compareTo(arg0.getNoeud1().getNom());}
}
