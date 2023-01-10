package metier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.awt.Color;

// Posssesion d'un joueur :
// - 45 wagon (a placer)
// - 4 cartes wagons (au debut)
// - 3 cartes destination (min 1, max 3) <- à chaque pioche
// - 1 score
// - 1 nom (utiliser l'id dans le code, le nom est pour l'affichage)

public class Joueur {
	private static int nbJoueur = 0;
	private int idJoueur;
	private String nomJoueur;
	private LinkedHashMap<CarteWagon, Integer> hmWagon;
	private ArrayList<CarteDestination> alCarteDestination;
	private int score;
	private boolean estEnJeu;
	private Color couleur;
	private int nbWagonJoueur;

	// Possesion d'un joueur
	private ArrayList<Arete> alAretePossede;

	public Joueur(String nomJoueur, ArrayList<CarteWagon> alCarteWagon, Color couleur) {
		this.idJoueur = ++nbJoueur;
		this.nomJoueur = nomJoueur;
		this.hmWagon = this.initHmWagon(alCarteWagon);
		this.alCarteDestination = new ArrayList<CarteDestination>();
		this.score = 0;
		this.couleur = couleur;

		this.alAretePossede = new ArrayList<Arete>();
	}

	public LinkedHashMap<CarteWagon, Integer> initHmWagon( ArrayList<CarteWagon> alCarteWagon) {
		alCarteWagon.sort(new Comparator<CarteWagon>() {
			@Override
			public int compare(CarteWagon o1, CarteWagon o2) {
				if (o1.getNomCouleur().equals("Neutre")) return -1;
				if (o2.getNomCouleur().equals("Neutre")) return 1;
				if (o1.getNomCouleur().equals("Joker")) return -1;
				if (o2.getNomCouleur().equals("Joker")) return 1;
				return o1.getNomCouleur().compareTo(o2.getNomCouleur());
			}			
		});
		LinkedHashMap<CarteWagon, Integer> retour = new LinkedHashMap<CarteWagon, Integer>();
		for (CarteWagon carteWagon : alCarteWagon) {
			if (!carteWagon.getNomCouleur().equals("Neutre"))
				retour.put(carteWagon, 0);
		}
		return retour;
	}

	public int     getIdJoueur() {return this.idJoueur;}
	public String  getNomJoueur() {return this.nomJoueur;}
	public int     getScore() {return this.score;}
	public boolean getEstEnJeu() {return this.estEnJeu;}
	public ArrayList<Arete> getAlAretePossede() {return this.alAretePossede;}
	public LinkedHashMap<CarteWagon, Integer> getHmWagon() {return this.hmWagon;}	
	public ArrayList<CarteDestination> getAlCarteDestination() {return this.alCarteDestination;}
	public Color getCouleur() {return this.couleur;}
	public int   getNbWagonJoueur() {return this.nbWagonJoueur;}

	public void setEstEnJeu(boolean bool) {this.estEnJeu = bool;}
	public void setNbWagonJoueur (int nbWagonJoueur) {this.nbWagonJoueur = nbWagonJoueur;}

	public void addCarteWagon(CarteWagon carteWagon) { this.hmWagon.put(carteWagon, this.hmWagon.get(carteWagon) + 1);}
	public void addCarteDestination(CarteDestination carteDestination) {this.alCarteDestination.add(carteDestination);}
	public void addScore(int score) {this.score += score;}

	public void removeCarteWagon(CarteWagon carteWagon, int nbCarte) {this.hmWagon.put(carteWagon, this.hmWagon.get(carteWagon) - nbCarte);}
	public void removeCarteDestination(CarteDestination carteDestination) {this.alCarteDestination.remove(carteDestination);}

	public void removeScore(int score) {
		int result = this.score - score;

		if(result >= 0)
			this.score -= score;
		else
			this.score = 0;
	}

	@Override
	public String toString() {
		return "{" +
			" idJoueur='" + getIdJoueur() + "'" +
			", nomJoueur='" + getNomJoueur() + "'" +
			", hmWagon='" + getHmWagon() + "'" +
			", alCarteDestination='" + getAlCarteDestination() + "'" +
			", score='" + getScore() + "'" +
			", estEnJeu='" + getEstEnJeu() + "'" +
			", alAretePossede='" + getAlAretePossede() + "'" +
			"}";
	}

}
