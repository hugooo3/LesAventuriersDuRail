package ihm.conception;

import ihm.*;
import metier.*;

import javax.swing.*;

import application.Application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.*;

public class FrameParametre extends Frame {
	@Override
	public String getName() {
		return "Définition des Paramètres";
	}
	private Application app;

	private PanelMappe panelMappe;
	private PanelParam panelParam;

	public FrameParametre(Application app, File imagePath) {
		this.app = app;
		
		// Définition des deux panels principaux
		this.panelMappe = new PanelMappe(this, imagePath, this.LARGEUR, this.HAUTEUR, false);

		// Rajout de imagePath pour le donner au suivant sur le bouton suivant
		this.panelParam = new PanelParam(this, imagePath, this.LARGEUR, this.HAUTEUR);

		// Ajout des panels à la frame
		this.add(this.panelMappe, BorderLayout.WEST);
		this.add(this.panelParam, BorderLayout.EAST);
	}

	public boolean creerAlNoeuds(ArrayList<Noeud> ListNoeuds) { return this.app.creerAlNoeuds(ListNoeuds); }
	public boolean creerAlAretes(ArrayList<Arete> ListAretes) { return this.app.creerAlAretes(ListAretes); }
	public boolean creerAlCartesDestination(ArrayList<CarteDestination> ListCartesDestination) { return this.app.creerAlCartesDestination(ListCartesDestination);}
	public boolean creerAlCartesWagon(ArrayList<CarteWagon> ListCartesWagon) 				   { return this.app.creerAlCartesWagon(ListCartesWagon); }
	
	public boolean setImgMappe(File imgMappe) 			      	   {return this.app.setImgMappe(imgMappe);}
	public boolean setNbJoueurMin(int nbJoueurMin) 	               {return this.app.setNbJoueurMin(nbJoueurMin);}
	public boolean setNbJoueurMax(int nbJoueurMax) 				   {return this.app.setNbJoueurMax(nbJoueurMax);}
	public boolean setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) {return this.app.setNbJoueurDoubleVoies(nbJoueurDoubleVoies);}
	public boolean setNbWagonJoueur(int nbWagonJoueur) 			   {return this.app.setNbWagonJoueur(nbWagonJoueur);}	
}