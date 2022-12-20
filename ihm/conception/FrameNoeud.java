package ihm.conception;

import javax.swing.*;

import application.Application;
import metier.*;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.BorderLayout;

import ihm.Frame;

public class FrameNoeud extends Frame {

	@Override
	public String getName() {
		return "Définition des Noeuds";
	}

	private Application app;

	private PanelMappe panelMappeNoeud;
	private PanelLstNoeud panelLstNoeud;
	private ArrayList<Noeud> alstNoeud;

	public FrameNoeud(Application app, File imagePath) 
	{
		this.app = app;
		this.alstNoeud = new ArrayList<Noeud>();

		// Définition des deux panels principaux
		this.panelMappeNoeud = new PanelMappe(this, imagePath, this.LARGEUR, this.HAUTEUR, this.alstNoeud, true);

		this.panelLstNoeud = new PanelLstNoeud(this, imagePath, this.LARGEUR, this.HAUTEUR, this.alstNoeud);

		// Ajout des panels à la frame
		this.add(this.panelMappeNoeud, BorderLayout.WEST);
		this.add(this.panelLstNoeud, BorderLayout.EAST);
	}

	public Application getApp() {return this.app;}

	public boolean creerAlNoeuds(ArrayList<Noeud> ListNoeuds) {return this.app.creerAlNoeuds(ListNoeuds);}

	public void majIHM() {this.panelMappeNoeud.majIHM();}

	public void verifMAJ(String source) {this.app.verifMAJ(source);}

	public void majLstNoeuds(ArrayList<Noeud> alNoeud) {this.panelLstNoeud.majLstNoeuds(alNoeud);}
	public void setLstNoeud(ArrayList<Noeud> alNoeud) {this.panelMappeNoeud.setLstNoeud(alNoeud);}
}
