package ihm.conception;

import javax.swing.*;

import application.Application;
import metier.*;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.BorderLayout;

import ihm.Frame;

public class FrameArete extends Frame {

	@Override
	public String getName() {
		return "Définition des Aretes";
	}

	private Application app;

	private PanelMappe panelMappeArete;
	private PanelLstArete panelLstArete;

	public FrameArete(Application app, File imagePath) {
		this.app = app;

		// Définition des deux panels principaux
		this.panelMappeArete = new PanelMappe(this, imagePath, this.LARGEUR, this.HAUTEUR, true);

		this.panelLstArete = new PanelLstArete(this, imagePath,this.LARGEUR, this.HAUTEUR);

		// Ajout des panels à la frame
		this.add(this.panelMappeArete, BorderLayout.WEST);
		this.add(this.panelLstArete, BorderLayout.EAST);
	}

	public Application getApp() {
		return this.app;
	}






	/*
	public boolean creerAlAretes(ArrayList<Noeud> ListNoeuds) {
		return this.app.creerAlAretes(ListArete);
	}

	public void verifMAJ(String source) {
		this.app.verifMAJ(source);
	}

	public void majLstNoeuds(ArrayList<Noeud> alNoeud) {
		this.panelLstNoeud.majLstNoeuds(alNoeud);
	}

	public void setLstNoeud(ArrayList<Noeud> alNoeud) {
		this.panelMappeNoeud.setLstNoeud(alNoeud);
	}*/
}