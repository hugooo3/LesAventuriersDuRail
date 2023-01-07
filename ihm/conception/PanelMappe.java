package ihm.conception;

import metier.*;
import ihm.FrameConcepteur;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelMappe extends JPanel {
	private static int RADIUS = 20;

	private FrameConcepteur concepteur;

	private Image img;
	private int largeur;
	private int hauteur;
	private Noeud noeudSelec = null;
	private Noeud noeudTexteSelec = null;
	private boolean cliquable = false;

	public ArrayList<Noeud> alNoeud;

	public PanelMappe(FrameConcepteur concepteur, int largeur, int hauteur) {
		this.concepteur = concepteur;
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.alNoeud = this.concepteur.getMetier().getAlNoeuds();

		this.setLayout(null);
		this.setPreferredSize(new Dimension((int) (this.largeur), this.hauteur));

		// Dessin sur le Panel
		this.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if (PanelMappe.this.cliquable == false)
					return;

				for (Noeud noeud : PanelMappe.this.alNoeud) 
				{
					if (noeud.getRectangle2d().contains(e.getPoint())) // Clique sur le nom du noeud
					{
						PanelMappe.this.noeudTexteSelec = noeud;
						return;
					}
					else if (noeud.getEllipse2D().contains(e.getPoint())) // Clique sur un noeud
					{
						PanelMappe.this.noeudSelec = noeud;
						return;
					}
				}

				// Rien sur le clique, creation d'un noeud
				JLabel lblNom = new JLabel("Nom du Noeud : ");
				String nomNoeud = JOptionPane.showInputDialog(PanelMappe.this, lblNom, "Création d'un Noeud",
						JOptionPane.QUESTION_MESSAGE);

				while (nomNoeud == null || nomNoeud.equals("")) 
				{
					if (nomNoeud == null) // bouton annuler
						return;

					JOptionPane.showMessageDialog(null, "Le champ saisi ne peut être vide !", "Erreur",
							JOptionPane.ERROR_MESSAGE);

					nomNoeud = JOptionPane.showInputDialog(PanelMappe.this, lblNom, "Création d'un Noeud",
							JOptionPane.QUESTION_MESSAGE);
				}


				PanelMappe.this.noeudSelec = new Noeud(nomNoeud, e.getX(), e.getY(), PanelMappe.RADIUS);
				
				PanelMappe.this.alNoeud.add(PanelMappe.this.noeudSelec); // Si un noeud d'une liste est modif, l'autre aussi
				PanelMappe.this.concepteur.majIHM();
			}

			@Override
			public void mouseReleased(MouseEvent e) 
			{
				PanelMappe.this.noeudSelec = null;
				PanelMappe.this.noeudTexteSelec = null;
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (PanelMappe.this.noeudTexteSelec != null) {
					PanelMappe.this.noeudTexteSelec.setNomDeltaX(e.getX());
					PanelMappe.this.noeudTexteSelec.setNomDeltaY(e.getY());

					repaint();
				} else if (PanelMappe.this.noeudSelec != null) {
					PanelMappe.this.noeudSelec.setX(e.getX());
					PanelMappe.this.noeudSelec.setY(e.getY());

					repaint();
				}
			}
		});
	}

	public void setImg(Image image) 
	{
		this.img = image;
		repaint();
	}	
	public void setCliquable(Boolean cliquable) {this.cliquable = cliquable;}

	public void modifierLstNoeud(int noeudPos) 
	{
		this.alNoeud.get(noeudPos).setRectangleLongueur();
		this.alNoeud.get(noeudPos).majEllipse2D();
	}

	public void paint(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.img, 1, 1, (int) this.getSize().getWidth() - 2, (int) this.getSize().getHeight() - 2, this);

		ArrayList<Arete> alAretes = this.concepteur.getMetier().getAlAretes();

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
			for (int i = 0; i < troncons; i++)
			{
				int x3 = (int) (x1 + (x2 - x1) / (troncons - i));
				int y3 = (int) (y1 + (y2 - y1) / (troncons - i));
				
				g2d.setColor(arete.getCouleur().getCouleur());
				g2d.fill(this.creerRectangle(x1, y1, x3, y3, rayon));
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(3));
				g2d.draw(this.creerRectangle(x1, y1, x3, y3, rayon));
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
		x1 = (int) (x1 + rayon * Math.sin(angle));
		y1 = (int) (y1 - rayon * Math.cos(angle));
		x2 = (int) (x2 + rayon * Math.sin(angle));
		y2 = (int) (y2 - rayon * Math.cos(angle));

		// Calcul de la longueur de chaque coté du rectangle
		double longueur = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		double largeur = 15;
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
}