package metier;

import java.util.ArrayList;

public class Mappe {
	ArrayList<Noeud> alNoeuds;
	ArrayList<Arete> alAretes;
	ArrayList<CarteDestination> alCartesDestination;
	ArrayList<CarteWagon>       alCartesWagon;
	String imgMappe;
	int nbJoueurMin;
	int nbJoueurMax;
	int nbJoueurDoubleVoies;
	int nbWagonJoueur;

	public Mappe(ArrayList<Noeud> alNoeuds, ArrayList<Arete> alAretes, ArrayList<CarteDestination> alCartesDestination, ArrayList<CarteWagon> alCartesWagon, String imgMappe, int nbJoueurMin, int nbJoueurMax, int nbJoueurDoubleVoies, int nbWagonJoueur)
	{
		this.alNoeuds = alNoeuds;
		this.alAretes = alAretes;
		this.alCartesDestination = alCartesDestination;
		this.alCartesWagon = alCartesWagon;
		this.imgMappe = imgMappe;
		this.nbJoueurMin = nbJoueurMin;
		this.nbJoueurMax = nbJoueurMax;
		this.nbJoueurDoubleVoies = nbJoueurDoubleVoies;
		this.nbWagonJoueur = nbWagonJoueur;
	}

	public ArrayList<Noeud> 			getAlNoeuds() 				{return this.alNoeuds;}
	public ArrayList<Arete> 			getAlAretes() 				{return this.alAretes;}
	public ArrayList<CarteDestination> 	getAlCartesDestination() 	{return this.alCartesDestination;}
	public ArrayList<CarteWagon> 		getAlCartesWagon() 			{return this.alCartesWagon;}
	public String 						getImgMappe() 				{return this.imgMappe;}
	public int 							getNbJoueurMin() 			{return this.nbJoueurMin;}
	public int 							getNbJoueurMax() 			{return this.nbJoueurMax;}
	public int 							getNbJoueurDoubleVoies() 	{return this.nbJoueurDoubleVoies;}
	public int 							getNbWagonJoueur() 			{return this.nbWagonJoueur;}
}
