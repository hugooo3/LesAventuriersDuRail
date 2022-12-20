package ihm.conception;

import metier.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class PanelMappe extends JPanel 
{
	private static int RADIUS = 20;

	private Image img;
	private int largeur;
	private int hauteur;
	private boolean cliquable;
	private NoeudDessin noeudSelec = null;
	private NoeudDessin noeudTexteSelec = null;

	private Frame frame;

	public ArrayList<NoeudDessin> alNoeudDessin = new ArrayList<NoeudDessin>();
	public ArrayList<Noeud> alNoeud;

	public PanelMappe(Frame frame, File imagePath, int largeur, int hauteur, ArrayList<Noeud> alstNoeud, boolean cliquable) 
	{
		this.frame = frame;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.cliquable = cliquable;

		if (alstNoeud != null)
			this.alNoeud = alstNoeud;
		else
			this.alNoeud = new ArrayList<Noeud>();

		this.setLayout(null);
		this.img = getToolkit().getImage(imagePath.getAbsolutePath());
		this.setPreferredSize(new Dimension((int) (this.largeur * 0.7), this.hauteur));

		if (this.cliquable) {
			// Dessin sur le Panel
			this.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mousePressed(MouseEvent e) 
				{
					for (NoeudDessin noeudDessin : PanelMappe.this.alNoeudDessin) 
					{
						if (noeudDessin.getEllipse2D().contains(e.getPoint())) // Clique sur un noeud
						{
							// JOptionPane.showMessageDialog(frame, "Un noeud existe déjà à cet emplacement");
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
					String nomNoeud = JOptionPane.showInputDialog(PanelMappe.this.frame, lblNom, "Création d'un Noeud",
							JOptionPane.QUESTION_MESSAGE);

					while (nomNoeud == null || nomNoeud.equals(""))
					{
						if (nomNoeud == null) // bouton annuler
							return;

						JOptionPane.showMessageDialog(null, "Le champ saisi ne peut être vide !", "Erreur",
								JOptionPane.ERROR_MESSAGE);

						nomNoeud = JOptionPane.showInputDialog(PanelMappe.this.frame, lblNom, "Création d'un Noeud",
								JOptionPane.QUESTION_MESSAGE);
					}

					PanelMappe.this.noeudSelec = new NoeudDessin(nomNoeud, e.getX() - PanelMappe.RADIUS, e.getY() - PanelMappe.RADIUS, PanelMappe.RADIUS);
					
					PanelMappe.this.alNoeudDessin.add(PanelMappe.this.noeudSelec); // C'est le même noeud qui est ajoute dans les deux lists
					PanelMappe.this.alNoeud.add(PanelMappe.this.noeudSelec); // Si un noeud d'une liste est modif, l'autre aussi

 					if (PanelMappe.this.frame instanceof FrameNoeud)
					{
						((FrameNoeud) PanelMappe.this.frame).majLstNoeuds(PanelMappe.this.alNoeud);
					}
					affichageTab();
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) 
				{
					affichageTab();
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

						//PanelMappe.this.noeudSelec.setEllipseX(e.getX());
						//PanelMappe.this.noeudSelec.setEllipseY(e.getY());

						repaint();
					}
				}
			});
		}
	}

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
		g2d.drawImage(img, 1, 1, (int) this.getSize().getWidth() - 2, (int) this.getSize().getHeight() - 2, this);

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

	public void affichageTab()
	{
		System.out.println("\nPanelMappe :");
		System.out.println("Noeud");
		for (Noeud noeud : this.alNoeud)
		{
			System.out.println(noeud.aff() + " " + noeud.aff().hashCode());
		}

		System.out.println("NoeudDessin");
		for (Noeud noeud : this.alNoeudDessin)
		{
			System.out.println(noeud.aff() + " " + noeud.aff().hashCode());
		}
		
		if (PanelMappe.this.frame instanceof FrameNoeud)
		{
			((FrameNoeud) PanelMappe.this.frame).getPanelLstNoeud().affichageTab();
		}
	}
}