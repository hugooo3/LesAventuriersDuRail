package metier;

import java.util.ArrayList;
import java.util.HashMap;

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
	private HashMap<CarteWagon, Integer> hmWagon;
	private ArrayList<CarteDestination> alCarteDestination;
	private int score;
	private boolean estEnJeu;

	// Possesion d'un joueur
	private ArrayList<Arete> alAretePossede;

	public Joueur(String nomJoueur) {
		this.idJoueur = ++nbJoueur;
		this.nomJoueur = nomJoueur;
		this.hmWagon = new HashMap<CarteWagon, Integer>();
		this.alCarteDestination = new ArrayList<CarteDestination>();
		this.score = 0;

		this.alAretePossede = new ArrayList<Arete>();
	}

	public int 	   getIdJoueur() {return this.idJoueur;}
	public String  getNomJoueur() {return this.nomJoueur;}
	public int     getScore() {return this.score;}
	public boolean getEstEnJeu() {return this.estEnJeu;}
	public ArrayList<Arete> getAlAretePossede() {return this.alAretePossede;}
	public HashMap<CarteWagon, Integer> getHmWagon() {return this.hmWagon;}

	public void setEstEnJeu() {this.estEnJeu = !estEnJeu;}

	public void addCarteWagon(CarteWagon carteWagon) {this.hmWagon.put(carteWagon, this.hmWagon.get(carteWagon) + 1);}
	public void addCarteDestination(CarteDestination carteDestination) {this.alCarteDestination.add(carteDestination);}
	public void addScore(int score) {this.score += score;}

	public void removeCarteWagon(CarteWagon carteWagon) {this.hmWagon.put(carteWagon, this.hmWagon.get(carteWagon) - 1);}
	public void removeCarteDestination(CarteDestination carteDestination) {this.alCarteDestination.remove(carteDestination);}

	public void removeScore(int score) {
		int result = this.score - score;

		if(result >= 0)
			this.score -= score;
		else
			this.score = 0;
	} // Utile pour les cartes destination en fin de partie
}
