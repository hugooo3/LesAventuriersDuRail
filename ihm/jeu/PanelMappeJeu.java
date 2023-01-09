package ihm.jeu;

import metier.*;
import ihm.FrameJeu;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.geom.Line2D;

public class PanelMappeJeu extends JPanel 
{
	private FrameJeu jeu;

	private Image img;
	private int largeur;
	private int hauteur;

	public ArrayList<Noeud> alNoeud;

	public PanelMappeJeu(FrameJeu jeu, int largeur, int hauteur) {
		this.jeu = jeu;
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.alNoeud = this.jeu.getMetier().getAlNoeuds();

		this.setLayout(null);
		this.setPreferredSize(new Dimension((int) (this.largeur), this.hauteur));
	}

	public void setImg(Image image) 
	{
		this.img = image;
		repaint();
	}

	public void paint(Graphics g) 
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.img, 1, 1, this.largeur - 2, this.hauteur - 2, this);

		ArrayList<Arete> alAretes = this.jeu.getMetier().getAlAretes();

		for (Arete arete : alAretes) 
		{
			Noeud noeud1 = arete.getNoeud1();
			Noeud noeud2 = arete.getNoeud2();
			int troncons = arete.getTroncons();
			int rayon = ((noeud1.getDiametreEllipse() + noeud2.getDiametreEllipse()) / 2) / 2; // Moyenne des deux diametres divise par 2 pour avoir le rayon

			int x1 = noeud1.getX();
			int y1 = noeud1.getY();
			int x2 = noeud2.getX();
			int y2 = noeud2.getY();

			Color couleurVoieSimple = arete.getCouleur().getCouleur();
			Color couleurVoieDouble = (arete.getCouleurDoubleVoie() != null ? arete.getCouleurDoubleVoie().getCouleur() : null);

			Joueur joueurVoieSimple = arete.getJoueurVoieSimple();
			Joueur joueurVoieDouble = arete.getJoueurVoieDouble();

			for (int i = 0; i < troncons; i++)
			{
				int x3 = (int) (x1 + (x2 - x1) / (troncons - i));
				int y3 = (int) (y1 + (y2 - y1) / (troncons - i));
				
				if (arete.getVoieDouble())
				{
					// Interieur de la voie simple
					g2d.setColor(joueurVoieSimple != null ? joueurVoieSimple.getCouleur() : couleurVoieSimple);
					g2d.fill(this.creerRectangle(x1, y1, x3, y3, 0));
					// Interieur de la voie double
					g2d.setColor(joueurVoieDouble != null ? joueurVoieDouble.getCouleur() : couleurVoieDouble);
					g2d.fill(this.creerRectangle(x1, y1, x3, y3, rayon ));

					g2d.setStroke(new BasicStroke(3));
					// Interieur de la voie double
					g2d.setColor(couleurVoieSimple.getRGB() != Color.BLACK.getRGB() ? Color.BLACK : Color.GRAY);
					//g2d.draw(this.creerRectangle(x1, y1, x3, y3, 0));
					// Exterieur de la voie double
					g2d.setColor(couleurVoieDouble.getRGB() != Color.BLACK.getRGB() ? Color.BLACK : Color.GRAY);
					//g2d.draw(this.creerRectangle(x1, y1, x3, y3, rayon ));

					if (joueurVoieSimple != null)
					{
						g2d.setStroke(new BasicStroke(3));
						g2d.draw(creerDiagonale(x1, y1, x2, y2, 0));
					}
					if (joueurVoieDouble != null)
					{
						g2d.setStroke(new BasicStroke(3));
						g2d.draw(creerDiagonale(x1, y1, x2, y2, rayon));
					}
				}
				else
				{
					// Interieur de la voie
					g2d.setColor(joueurVoieSimple != null ? joueurVoieSimple.getCouleur() : couleurVoieSimple);
					g2d.fill(this.creerRectangle(x1, y1, x3, y3, rayon - 5));
					g2d.setStroke(new BasicStroke(3));
					// Exterieur de la voie
					g2d.setColor(couleurVoieSimple.getRGB() != Color.BLACK.getRGB() ? Color.BLACK : Color.GRAY);
					//g2d.draw(this.creerRectangle(x1, y1, x3, y3, rayon - 5));

					if (joueurVoieSimple != null)
					{
						g2d.setStroke(new BasicStroke(3));
						g2d.draw(creerDiagonale(x1, y1, x2, y2, rayon - 5));
					}
				}
				x1 = x3;
				y1 = y3;
			}
		}

		for (Noeud noeud : this.alNoeud) 
		{
			// Cercle
			g2d.setColor(Color.RED);
			g2d.fill(noeud.getEllipse2D());
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke((float) (2)));
			g2d.draw(noeud.getEllipse2D());

			// Rectangle
			g2d.setStroke(new BasicStroke((float) (1)));
			g2d.setColor(Color.WHITE);
			g2d.fill(noeud.getRectangle2d());
			g2d.setColor(Color.BLACK);
			g2d.draw(noeud.getRectangle2d());
			g2d.drawString(noeud.getNom(), noeud.getNomX(), noeud.getNomY());
		}
	}
	public void majIHM() {this.repaint();}

	private Polygon creerRectangle(int x1, int y1, int x2, int y2, int rayon) 
	{
		Polygon rectangle = new Polygon();
		// Calcul de l'angle entre les deux points
		Double angle = Math.atan2(y2 - y1, x2 - x1);

		// Calcul des coordonnees pour que les coords ne soient plus le centre du cercle mais un point sur le cercle
		x1 = (int) (x1 + rayon * Math.sin(angle));
		y1 = (int) (y1 - rayon * Math.cos(angle));
		x2 = (int) (x2 + rayon * Math.sin(angle));
		y2 = (int) (y2 - rayon * Math.cos(angle));

		// Calcul de la longueur de chaque coté du rectangle
		double longueur = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		double largeur = 10;
		// Calcul des coordonnées des différents points du rectangle
		int x3 = (int) (x1 + longueur * Math.cos(angle));
		int y3 = (int) (y1 + longueur * Math.sin(angle));
		int x4 = (int) (x1 + largeur  * Math.cos(angle + Math.PI / 2));
		int y4 = (int) (y1 + largeur  * Math.sin(angle + Math.PI / 2));
		int x5 = (int) (x3 + largeur  * Math.cos(angle + Math.PI / 2));
		int y5 = (int) (y3 + largeur  * Math.sin(angle + Math.PI / 2));
		// Ajout des points au rectangle
		rectangle.addPoint(x1, y1);
		rectangle.addPoint(x3, y3);
		rectangle.addPoint(x5, y5);
		rectangle.addPoint(x4, y4);
		return rectangle;
	}

	private Polygon creerDiagonale(int x1, int y1, int x2, int y2, int rayon)
	{
		Polygon diagonale = new Polygon();
		// Calcul de l'angle entre les deux points
		Double angle = Math.atan2(y2 - y1, x2 - x1);

		// Calcul des coordonnees pour que les coords ne soient plus le centre du cercle mais un point sur le cercle
		x1 = (int) (x1 + rayon * Math.sin(angle));
		y1 = (int) (y1 - rayon * Math.cos(angle));

		diagonale.addPoint(x1, y1);
		diagonale.addPoint(x2, y2);

		return diagonale;
	}
}