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

    public ArrayList<Noeud> getAlNoeuds() {
        return this.alNoeuds;
    }

    public void setAlNoeuds(ArrayList<Noeud> alNoeuds) {
        this.alNoeuds = alNoeuds;
    }

    public ArrayList<Arete> getAlAretes() {
        return this.alAretes;
    }

    public void setAlAretes(ArrayList<Arete> alAretes) {
        this.alAretes = alAretes;
    }

    public ArrayList<CarteDestination> getAlCartesDestination() {
        return this.alCartesDestination;
    }

    public void setAlCartesDestination(ArrayList<CarteDestination> alCartesDestination) {
        this.alCartesDestination = alCartesDestination;
    }

    public ArrayList<CarteWagon> getAlCartesWagon() {
        return this.alCartesWagon;
    }

    public void setAlCartesWagon(ArrayList<CarteWagon> alCartesWagon) {
        this.alCartesWagon = alCartesWagon;
    }

    public String getImgMappe() {
        return this.imgMappe;
    }

    public void setImgMappe(String imgMappe) {
        this.imgMappe = imgMappe;
    }

    public int getNbJoueurMin() {
        return this.nbJoueurMin;
    }

    public void setNbJoueurMin(int nbJoueurMin) {
        this.nbJoueurMin = nbJoueurMin;
    }

    public int getNbJoueurMax() {
        return this.nbJoueurMax;
    }

    public void setNbJoueurMax(int nbJoueurMax) {
        this.nbJoueurMax = nbJoueurMax;
    }

    public int getNbJoueurDoubleVoies() {
        return this.nbJoueurDoubleVoies;
    }

    public void setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) {
        this.nbJoueurDoubleVoies = nbJoueurDoubleVoies;
    }

    public int getNbWagonJoueur() {
        return this.nbWagonJoueur;
    }

    public void setNbWagonJoueur(int nbWagonJoueur) {
        this.nbWagonJoueur = nbWagonJoueur;
    }
}
