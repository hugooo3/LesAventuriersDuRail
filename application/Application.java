package application;

import java.io.File;
import java.awt.Image;

import ihm.*;
import metier.*;

public class Application 
{
	Metier metier;
	FrameManager frameManager;

	public Application() 
	{
		this.metier = new Metier();
		this.frameManager = new FrameManager(this);
	}
	
	public Metier getMetier() {return this.metier;}

	public void setImgMappe(Image imgMappe) { this.metier.setImgMappe(imgMappe);}
	public void setNbJoueurMin(int nbJoueurMin) { this.metier.setNbJoueurMin(nbJoueurMin);}
	public void setNbJoueurMax(int nbJoueurMax) { this.metier.setNbJoueurMax(nbJoueurMax);}
	public void setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) { this.metier.setNbJoueurDoubleVoies(nbJoueurDoubleVoies);}
	public void setNbWagonJoueur(int nbWagonJoueur) { this.metier.setNbWagonJoueur(nbWagonJoueur);}
	public void setNbFin(int nbFin) { this.metier.setNbFin(nbFin);}

	public boolean reinitialiserDossierSortie() {return this.metier.reinitialiserDossierSortie();}
	public boolean importMappe   (File xmlPath) {return this.metier.importMappe(xmlPath);}
	public void reinitialiserMetier() {this.metier = new Metier();}

	public void ecrireXML()
	{
		this.metier.ecrireXml();
	}
}
