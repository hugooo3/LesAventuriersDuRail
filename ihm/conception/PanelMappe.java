package ihm.conception;

import metier.*;

import javax.swing.*;

import application.Application;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class PanelMappe extends JPanel 
{
	private static int RADIUS = 20;

	private Application app;

	private Image img;
	private int largeur;
	private int hauteur;
	private NoeudDessin noeudSelec = null;
	private NoeudDessin noeudTexteSelec = null;
	private boolean cliquable = false;


	public ArrayList<NoeudDessin> alNoeudDessin = new ArrayList<NoeudDessin>();
	public ArrayList<Noeud> alNoeud;

	public PanelMappe(Application app, int largeur, int hauteur) 
	{
		this.app = app;
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.alNoeud = this.app.getMetier().getAlNoeuds();
/* 		if (this.alNoeud != null)
		{
			for (Noeud noeud : this.alNoeud)
			{
				this.alNoeudDessin.add(new NoeudDessin(noeud, PanelMappe.RADIUS));
			}
		} */

		this.setLayout(null);
		this.setPreferredSize(new Dimension((int) (this.largeur * 0.7), this.hauteur));

			// Dessin sur le Panel
		this.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if (PanelMappe.this.cliquable == false) {return;}

				for (NoeudDessin noeudDessin : PanelMappe.this.alNoeudDessin) 
				{
					if (noeudDessin.getEllipse2D().contains(e.getPoint())) // Clique sur un noeud
					{
						PanelMappe.this.noeudSelec = noeudDessin;
						return;
					}
					else if (noeudDessin.getRectangle2d().contains(e.getPoint())) // Clique sur le nom du noeud
					{
						PanelMappe.this.noeudTexteSelec = noeudDessin;
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

				PanelMappe.this.noeudSelec = new NoeudDessin(nomNoeud, e.getX() - PanelMappe.RADIUS, e.getY() - PanelMappe.RADIUS, PanelMappe.RADIUS);
				
				PanelMappe.this.alNoeudDessin.add(PanelMappe.this.noeudSelec); // C'est le même noeud qui est ajoute dans les deux lists
				PanelMappe.this.alNoeud.add(PanelMappe.this.noeudSelec); // Si un noeud d'une liste est modif, l'autre aussi
				/*  if (PanelMappe.this.app instanceof appNoeud)
				{
					((appNoeud) PanelMappe.this.app).majLstNoeuds(PanelMappe.this.alNoeud);
				} */
				repaint();
			}					

			@Override
			public void mouseReleased(MouseEvent e) 
			{
				PanelMappe.this.noeudSelec = null;
				PanelMappe.this.noeudTexteSelec = null;
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() 
		{
			public void mouseDragged(MouseEvent e) 
			{
				if (PanelMappe.this.noeudTexteSelec != null)
				{
					PanelMappe.this.noeudTexteSelec.setNomDeltaX(e.getX());
					PanelMappe.this.noeudTexteSelec.setNomDeltaY(e.getY());

					repaint();
				}
				else if (PanelMappe.this.noeudSelec != null) 
				{
					PanelMappe.this.noeudSelec.setX(e.getX());
					PanelMappe.this.noeudSelec.setY(e.getY());

					repaint();
				}
			}
		});
	}

	public void setImg(File imagePath) {this.img = getToolkit().getImage(imagePath.getAbsolutePath()); repaint();}	
	public boolean changeCliquable() {return this.cliquable = !this.cliquable;}

	public void setLstNoeud(ArrayList<Noeud> alNoeud) 
	{
		ArrayList<NoeudDessin> alNoeudDessin = new ArrayList<NoeudDessin>();

		this.alNoeud = alNoeud;

		for (Noeud noeud : this.alNoeud) 
		{
			alNoeudDessin.add(new NoeudDessin(noeud.getNom(), noeud.getX(), noeud.getY(), 20));
		}
		this.alNoeudDessin = alNoeudDessin;
	}

	public void removeLstNoeud(Noeud noeud) {this.alNoeudDessin.remove(noeud);}
	public void modifierLstNoeud(int noeudPos) 
	{
		this.alNoeudDessin.get(noeudPos).setRectangleLongueur();
		this.alNoeudDessin.get(noeudPos).majEllipse2D();
	}

	public void paint(Graphics g) 
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.img, 1, 1, (int) this.getSize().getWidth() - 2, (int) this.getSize().getHeight() - 2, this);

		for (NoeudDessin noeud : this.alNoeudDessin) 
		{
			// Cercle
			g2d.setColor(Color.RED);
			g2d.fill(noeud.getEllipse2D());
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke((float)(2)));
			g2d.draw(noeud.getEllipse2D());

			// Rectangle
			g2d.setStroke(new BasicStroke((float)(1)));
			g2d.setColor(Color.WHITE);
			g2d.fill(noeud.getRectangle2d());
			g2d.setColor(Color.BLACK);
			g2d.draw(noeud.getRectangle2d());
			g2d.drawString(noeud.getNom(), noeud.getNomX(), noeud.getNomY());
		}
	}

	public void majIHM() {this.repaint();}
}