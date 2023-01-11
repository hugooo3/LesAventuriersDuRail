package application;

import java.io.File;
import java.util.ArrayList;
import java.awt.Image;

import ihm.*;
import metier.*;

public class Application 
{
	Metier metier;
	FrameManager frameManager;

	public Application()
	{
		this.metier = new Metier(this);
		this.frameManager = new FrameManager(this);
	}
	
	public Metier getMetier() {return this.metier;}

	public boolean preparationJeu(ArrayList<Joueur> lstJoueur) {return this.metier.preparationJeu(lstJoueur);}
	public boolean jeu(String actionJoueur) { return this.metier.jeu(actionJoueur); }

	public File getMappeXML() { return this.frameManager.getMappeXML();}
	public ArrayList<Joueur> getLstJoueurIHM   () {return this.frameManager.getLstJoueur();}
	public ArrayList<Joueur> getLstJoueurMetier() {return this.metier.getLstJoueur();}

	public int getNbJoueurMin() {return this.metier.getNbJoueurMin();}
	public int getNbJoueurMax() {return this.metier.getNbJoueurMax();}
	public Joueur getJoueurEnJeu() { return this.metier.getJoueurEnJeu();}
	
	public void setImgMappe(Image imgMappe) { this.metier.setImgMappe(imgMappe);}
	public void setNbJoueurMin(int nbJoueurMin) { this.metier.setNbJoueurMin(nbJoueurMin);}
	public void setNbJoueurMax(int nbJoueurMax) { this.metier.setNbJoueurMax(nbJoueurMax);}
	public void setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) { this.metier.setNbJoueurDoubleVoies(nbJoueurDoubleVoies);}
	public void setNbWagonJoueur(int nbWagonJoueur) { this.metier.setNbWagonJoueur(nbWagonJoueur);}
	public void setNbFin(int nbFin) { this.metier.setNbFin(nbFin);}

	public void    reinitialiserMetier() {this.metier = new Metier(this);}
	public boolean reinitialiserDossierSortie() {return this.metier.reinitialiserDossierSortie();}
	public boolean importMappe   (File xmlFile) {return this.metier.importMappe(xmlFile);}

	public void ecrireXML()
	{
		this.metier.ecrireXml();
	}
}
