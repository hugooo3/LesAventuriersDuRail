package metier;

public class Arete implements Comparable<Arete>{
	// Un même joueur ne peut pas prendre 2 routes reliant les 2 mêmes villes.
	private Noeud      noeud1;
	private Noeud 	   noeud2;
	private CarteWagon couleurVoieSimple;
	private CarteWagon couleurVoieDouble;
	private int		   troncons;
	private boolean	   voieDouble;
	private Joueur	   joueurVoieSimple;
	private Joueur     joueurVoieDouble;

	public Arete(Noeud noeud1, Noeud noeud2, CarteWagon couleurVoieSimple, int troncons, boolean voieDouble, CarteWagon couleurVoieDouble) {
		this.noeud1 			= noeud1;
		this.noeud2 			= noeud2;
		this.couleurVoieSimple  = couleurVoieSimple;
		this.troncons		    = troncons;
		this.voieDouble		    = voieDouble;
		this.couleurVoieDouble  = couleurVoieDouble;
		this.joueurVoieSimple	= null;
		this.joueurVoieDouble   = null;
	}

	public Noeud	  getNoeud1() 			  { return this.noeud1; }
	public Noeud 	  getNoeud2() 			  { return this.noeud2; }
	public int 	      getTroncons() 		  { return this.troncons; }
	public boolean	  getVoieDouble() 		  { return this.voieDouble; }
	public CarteWagon getCouleurVoieSimple()  { return this.couleurVoieSimple; }
	public CarteWagon getCouleurDoubleVoie()  { return this.couleurVoieDouble; }
	public Joueur     getJoueurVoieSimple()   { return this.joueurVoieSimple; }
	public Joueur     getJoueurVoieDouble()   { return this.joueurVoieDouble; }

	public void setNoeud1(Noeud noeud1) 						   { this.noeud1 = noeud1; }
	public void setNoeud2(Noeud noeud2)							   { this.noeud2 = noeud2; }
	public void setTroncons(int troncons) 						   { this.troncons = troncons; }
	public void setVoieDouble(boolean voieDouble) 				   { this.voieDouble = voieDouble; }
	public void setCouleurVoieSimple(CarteWagon couleur)		   { this.couleurVoieSimple = couleur; } 
	public void setCouleurDoubleVoie(CarteWagon couleurVoieDouble) { this.couleurVoieDouble = couleurVoieDouble; }
	public void setJoueurVoieSimple(Joueur joueur) 				   { this.joueurVoieSimple = joueur; }
	public void setJoueurVoieDouble(Joueur joueur)				   {this.joueurVoieDouble = joueur; }

	public String toString() {
		return noeud1.getNom() + " - " + noeud2.getNom() + " | " + getCouleurVoieSimple() + " | " + this.troncons + " | " +
				(getCouleurDoubleVoie() == null ? "" : getCouleurDoubleVoie());
	}

	@Override
	public int compareTo(Arete arg0) {
		if (this.noeud1.getNom().compareTo(arg0.getNoeud1().getNom()) == 0)
			return this.noeud2.getNom().compareTo(arg0.getNoeud2().getNom());
		return this.noeud1.getNom().compareTo(arg0.getNoeud1().getNom());
	}
}
