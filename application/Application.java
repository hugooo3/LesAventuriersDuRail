package application;

import java.io.File;
import java.util.ArrayList;

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

	public boolean setImgMappe(File imgMappe) {return this.metier.setImgMappe(imgMappe);}
	public boolean setNbJoueurMin(int nbJoueurMin) {return this.metier.setNbJoueurMin(nbJoueurMin);}
	public boolean setNbJoueurMax(int nbJoueurMax) {return this.metier.setNbJoueurMax(nbJoueurMax);}
	public boolean setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) {return this.metier.setNbJoueurDoubleVoies(nbJoueurDoubleVoies);}
	public boolean setNbWagonJoueur(int nbWagonJoueur) {return this.metier.setNbWagonJoueur(nbWagonJoueur);}

	public boolean reinitialiserDossierSortie() {return this.metier.reinitialiserDossierSortie();}
	public boolean importMappe   (File xmlPath) {return this.metier.importMappe(xmlPath);}

	public void ecrireXML()
	{
		this.metier.copierImage("imgMappe", this.metier.getImgMappe());
		this.metier.ecrireXml();
	}
}
