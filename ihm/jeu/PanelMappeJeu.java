package ihm.jeu;

import metier.*;
import ihm.FrameJeu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class PanelMappeJeu extends JPanel 
{
	private static int RADIUS = 20;

	private FrameJeu jeu;

	private Image img;
	private int largeur;
	private int hauteur;
	private boolean cliquable = false;

	public ArrayList<Noeud> alNoeud;

	public PanelMappeJeu(FrameJeu jeu, int largeur, int hauteur) {
		this.jeu = jeu;
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.alNoeud = this.jeu.getMetier().getAlNoeuds();

		this.setLayout(null);
		this.setPreferredSize(new Dimension((int) (this.largeur * 0.7), this.hauteur));
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
		g2d.drawImage(this.img, 1, 1, (int) this.getSize().getWidth() - 2, (int) this.getSize().getHeight() - 2, this);

		ArrayList<Arete> alAretes = this.jeu.getMetier().getAlAretes();

		for (Arete arete : alAretes) 
		{
			if (arete.getVoieDouble()) 
			{
				Path2D path1 = new Path2D.Double();
				Path2D path2 = new Path2D.Double();

				int arete1X = arete.getNoeud1().getX();
				int arete1Y = arete.getNoeud1().getY();
				int arete2X = arete.getNoeud2().getX();
				int arete2Y = arete.getNoeud2().getY();

				int intensiteY = (arete1X - arete2X) / 4;
				int intensiteX = (arete1Y - arete2Y) / 4;

				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(11));
				path1.moveTo(arete1X, arete1Y);
				path1.curveTo((double) (arete1X + arete2X) / 2 - intensiteX,
						(double) (arete1Y + arete2Y) / 2 - intensiteY,
						(double) (arete2X),
						(double) (arete2Y),
						(double) arete2X,
						(double) arete2Y);
				g2d.draw(path1);

				g2d.setColor(arete.getCouleur().getCouleur());
				g2d.setStroke(new BasicStroke(5));
				path1.moveTo(arete1X, arete1Y);
				path1.curveTo((double) (arete1X + arete2X) / 2 - intensiteX,
						(double) (arete1Y + arete2Y) / 2 - intensiteY,
						(double) (arete2X),
						(double) (arete2Y),
						(double) arete2X,
						(double) arete2Y);
				g2d.draw(path1);

				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(11));
				path2.moveTo(arete1X, arete1Y);
				path2.curveTo((double) (arete1X + arete2X) / 2 + intensiteX,
						(double) (arete1Y + arete2Y) / 2 + intensiteY,
						(double) (arete2X),
						(double) (arete2Y),
						(double) arete2X,
						(double) arete2Y);
				g2d.draw(path2);

				g2d.setColor(arete.getCouleurDoubleVoie().getCouleur());
				g2d.setStroke(new BasicStroke(5));
				path2.moveTo(arete1X, arete1Y);
				path2.curveTo((double) (arete1X + arete2X) / 2 + intensiteX,
						(double) (arete1Y + arete2Y) / 2 + intensiteY,
						(double) (arete2X),
						(double) (arete2Y),
						(double) arete2X,
						(double) arete2Y);
				g2d.draw(path2);


				int milieuX = (arete1X + arete2X) / 2 + (intensiteX/2);
				int milieuY = (arete1Y + arete2Y) / 2 + (intensiteY/2);

				g2d.setStroke(new BasicStroke((float) (1)));
				g2d.setColor(Color.WHITE);
				g2d.fill(new Rectangle2D.Double(milieuX - 3, milieuY, ("" + arete.getTronconsDoubleVoie()).length() * 7 + 5, 15));
				g2d.setColor(Color.BLACK);
				g2d.draw(new Rectangle2D.Double(milieuX - 3, milieuY, ("" + arete.getTronconsDoubleVoie()).length() * 7 + 5, 15));
				g2d.drawString("" + arete.getTronconsDoubleVoie(), milieuX, milieuY + 12);


				milieuX = (arete1X + arete2X) / 2 - (intensiteX/2);
				milieuY = (arete1Y + arete2Y) / 2 -  (intensiteY/2);

				g2d.setStroke(new BasicStroke((float) (1)));
				g2d.setColor(Color.WHITE);
				g2d.fill(new Rectangle2D.Double(milieuX - 3, milieuY, ("" + arete.getTroncons()).length() * 7 + 5, 15));
				g2d.setColor(Color.BLACK);
				g2d.draw(new Rectangle2D.Double(milieuX - 3, milieuY, ("" + arete.getTroncons()).length() * 7 + 5, 15));
				g2d.drawString("" + arete.getTroncons(), milieuX, milieuY + 12);

			} 
			else 
			{
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(11));
				g2d.drawLine(arete.getNoeud1().getX(), arete.getNoeud1().getY(), arete.getNoeud2().getX(),
						arete.getNoeud2().getY());

				g2d.setColor(arete.getCouleur().getCouleur());
				g2d.setStroke(new BasicStroke(5));
				g2d.drawLine(arete.getNoeud1().getX(), arete.getNoeud1().getY(),
						arete.getNoeud2().getX(), arete.getNoeud2().getY());

				
				int milieuX = (arete.getNoeud1().getX() + arete.getNoeud2().getX()) / 2;
				int milieuY = (arete.getNoeud1().getY() + arete.getNoeud2().getY()) / 2;

				g2d.setStroke(new BasicStroke((float) (1)));
				g2d.setColor(Color.WHITE);
				g2d.fill(new Rectangle2D.Double(milieuX - 3, milieuY + 12, ("" + arete.getTroncons()).length() * 7 + 5, 15));
				g2d.setColor(Color.BLACK);
				g2d.draw(new Rectangle2D.Double(milieuX - 3, milieuY + 12, ("" + arete.getTroncons()).length() * 7 + 5, 15));
				g2d.drawString("" + arete.getTroncons(), milieuX, milieuY + 25);
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
}