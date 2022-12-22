package metier;

import java.io.File;
import java.util.ArrayList;

public class Mappe 
{
	private ArrayList<Noeud> alNoeuds;
	private ArrayList<Arete> alAretes;
	private ArrayList<CarteDestination> alCartesDestination;
	private ArrayList<CarteWagon>       alCartesWagon;
	private File imgMappe;
	private int nbJoueurMin;
	private int nbJoueurMax;
	private int nbJoueurDoubleVoies;
	private int nbWagonJoueur;

	public Mappe(ArrayList<Noeud> alNoeuds, ArrayList<Arete> alAretes, ArrayList<CarteDestination> alCartesDestination, ArrayList<CarteWagon> alCartesWagon, File imgMappe, int nbJoueurMin, int nbJoueurMax, int nbJoueurDoubleVoies, int nbWagonJoueur)
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

	public ArrayList<Noeud> getAlNoeuds() {return this.alNoeuds;}
	public ArrayList<Arete> getAlAretes() {return this.alAretes;}
	public ArrayList<CarteDestination> getAlCartesDestination() {return this.alCartesDestination;}
	public ArrayList<CarteWagon> getAlCartesWagon() {return this.alCartesWagon;}
	public File getImgMappe() {return this.imgMappe;}
	public int getNbJoueurMin() {return this.nbJoueurMin;}
	public int getNbJoueurMax() {return this.nbJoueurMax;}
	public int getNbJoueurDoubleVoies() {return this.nbJoueurDoubleVoies;}
	public int getNbWagonJoueur() {return this.nbWagonJoueur;}

	public void setAlNoeuds(ArrayList<Noeud> alNoeuds) {this.alNoeuds = alNoeuds;}
	public void setAlAretes(ArrayList<Arete> alAretes) {this.alAretes = alAretes;}
	public void setAlCartesDestination(ArrayList<CarteDestination> alCartesDestination) {this.alCartesDestination = alCartesDestination;}
	public void setAlCartesWagon(ArrayList<CarteWagon> alCartesWagon) {this.alCartesWagon = alCartesWagon;}
	public void setImgMappe(File imgMappe) {this.imgMappe = imgMappe;}
	public void setNbJoueurMin(int nbJoueurMin) {this.nbJoueurMin = nbJoueurMin;}
	public void setNbJoueurMax(int nbJoueurMax) {this.nbJoueurMax = nbJoueurMax;}
	public void setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) {this.nbJoueurDoubleVoies = nbJoueurDoubleVoies;}
	public void setNbWagonJoueur(int nbWagonJoueur) {this.nbWagonJoueur = nbWagonJoueur;}
}
