package ihm.conception;

import metier.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class PanelMappe extends JPanel 
{
	private Image img;
	private int largeur;
	private int hauteur;
	private boolean cliquable;
	private NoeudDessin noeudSelec = null;

	private Frame frame;

	public ArrayList<NoeudDessin> alNoeudDessin = new ArrayList<NoeudDessin>();
	public ArrayList<Noeud> alNoeud = new ArrayList<Noeud>();

	public PanelMappe(Frame frame, File imagePath, int largeur, int hauteur, boolean cliquable) 
	{
		this.frame = frame;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.cliquable = cliquable;

		this.setLayout(null);
		this.img = getToolkit().getImage(imagePath.getAbsolutePath());
		this.setPreferredSize(new Dimension((int) (this.largeur * 0.7), this.hauteur));

		if (this.cliquable) 
		{
			// Dessin sur le Panel
			this.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mousePressed(MouseEvent e) 
				{
					for (NoeudDessin noeudDessin : PanelMappe.this.alNoeudDessin)
					{
						if (noeudDessin.getEllipse2D().contains(e.getPoint()))
						{
							//JOptionPane.showMessageDialog(frame, "Un noeud existe déjà à cet emplacement");
							PanelMappe.this.noeudSelec = noeudDessin;
							return;
						}
					}

					JLabel lblNom = new JLabel("Nom du Noeud : ");
					String getMessage = JOptionPane.showInputDialog(PanelMappe.this.frame, lblNom, "Création d'un Noeud", JOptionPane.QUESTION_MESSAGE);
					if (getMessage != null)
					{
						PanelMappe.this.noeudSelec = new NoeudDessin(getMessage, e.getX() - 20, e.getY() - 20, 20);
						PanelMappe.this.alNoeudDessin.add(PanelMappe.this.noeudSelec);
						repaint();
					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {PanelMappe.this.noeudSelec = null;}
			});

			this.addMouseMotionListener(new MouseMotionAdapter() 
			{			
				public void mouseDragged(MouseEvent e) 
				{
	 				if (PanelMappe.this.noeudSelec != null)
					{
						PanelMappe.this.noeudSelec.setX(e.getX());
						PanelMappe.this.noeudSelec.setY(e.getY());

						PanelMappe.this.noeudSelec.setEllipseX(e.getX());
						PanelMappe.this.noeudSelec.setEllipseY(e.getY());
			
						repaint();
					}
				}
			});
		}
	}

	public void paint(Graphics g) 
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 1, 1, (int) this.getSize().getWidth() - 2, (int) this.getSize().getHeight() - 2, this);

		System.out.println(this.alNoeudDessin.size());
		for (NoeudDessin noeud : this.alNoeudDessin) 
		{
			g2d.setColor(Color.RED);
			g2d.fill(noeud.getEllipse2D());
			g2d.setColor(Color.YELLOW);
			g2d.draw(noeud.getEllipse2D());
			g2d.setColor(Color.BLACK);
			g2d.drawString(noeud.getNom(), noeud.getX(), noeud.getY());
		}
	}
}