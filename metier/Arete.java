package metier;

public class Arete {
	private Noeud      noeud1;
	private Noeud 	   noeud2;
	private CarteWagon couleur;
	private CarteWagon couleurVoieDouble;
	private int		   troncons;
	private boolean	   voieDouble;
	private int 	   tronconsVoieDouble;

	public Arete(Noeud noeud1, Noeud noeud2, CarteWagon couleur, int troncons,
			boolean voieDouble, CarteWagon couleurVoieDouble, int tronconsVoieDouble) {
		this.noeud1 			= noeud1;
		this.noeud2 			= noeud2;
		this.couleur 		    = couleur;
		this.troncons		    = troncons;
		this.voieDouble		    = voieDouble;
		this.couleurVoieDouble  = couleurVoieDouble;
		this.tronconsVoieDouble = tronconsVoieDouble;

	}

	public Noeud	  getNoeud1() 			  { return this.noeud1; }
	public Noeud 	  getNoeud2() 			  { return this.noeud2; }
	public CarteWagon getCouleur() 			  { return this.couleur; }
	public int 	      getTroncons() 		  { return this.troncons; }
	public boolean	  getVoieDouble() 		  { return this.voieDouble; }
	public CarteWagon getCouleurDoubleVoie()  { return this.couleurVoieDouble; }
 	public int 		  getTronconsDoubleVoie() { return this.tronconsVoieDouble; }

	public void setNoeud1(Noeud noeud1) 						   { this.noeud1 = noeud1; }
	public void setNoeud2(Noeud noeud2)							   { this.noeud2 = noeud2; }
	public void setCouleur(CarteWagon couleur)					   { this.couleur = couleur; } 
	public void setTroncons(int troncons) 						   { this.troncons = troncons; }
	public void setVoieDouble(boolean voieDouble) 				   { this.voieDouble = voieDouble; }
	public void setCouleurDoubleVoie(CarteWagon couleurVoieDouble) { this.couleurVoieDouble = couleurVoieDouble; }
	public void setTronconsDoubleVoie(int tronconsVoieDouble)	   { this.tronconsVoieDouble = tronconsVoieDouble; }

	public String toString() {
		return noeud1.getNom() + " - " + noeud2.getNom() + " | " + getCouleur() + " | " + this.troncons + " | " +
				(getCouleurDoubleVoie() == null ? "" : getCouleurDoubleVoie()) +
				(this.tronconsVoieDouble != 0 ? " | " + this.tronconsVoieDouble : "");
	}
}
