package ihm.conception;

import metier.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;

public class PanelMappe extends JPanel {
	private Image img;
	private int largeur;
	private int hauteur;
	private boolean cliquable;

	private Frame frame;

	public ArrayList<NoeudDessin> alNoeudDessin = new ArrayList<NoeudDessin>();
	public ArrayList<Noeud> alNoeud = new ArrayList<Noeud>();

	public PanelMappe(Frame frame, File imagePath, int largeur, int hauteur, boolean cliquable) {
		this.frame = frame;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.cliquable = cliquable;

		this.setLayout(null);
		this.img = getToolkit().getImage(imagePath.getAbsolutePath());
		this.setPreferredSize(new Dimension((int) (largeur * 0.7), hauteur));

		if (cliquable) {
			// Dessin sur le Panel
			this.addMouseListener(new MouseAdapter() {

				private NoeudDessin noeudSelec = null;

				@Override
				public void mousePressed(MouseEvent e) 
				{
					for (NoeudDessin noeudDessin : PanelMappe.this.alNoeudDessin)
					{
						if (noeudDessin.getEllipse2D().contains(e.getPoint()))
						{
							JOptionPane.showMessageDialog(frame, "Un noeud existe déjà à cet emplacement");
							return;
						}
					}
			
					this.noeudSelec = new NoeudDessin(e.getX() - 20, e.getY() - 20, 20, null);
					alNoeudDessin.add(this.noeudSelec);

					JLabel lblNom = new JLabel("Nom du Noeud : ");
					JOptionPane.showInputDialog(frame, lblNom, "Création d'un Noeud", JOptionPane.QUESTION_MESSAGE);

					repaint();				
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					this.noeudSelec = null;
				}
			});

			for (NoeudDessin noeud : this.alNoeudDessin) {
				this.alNoeud.add(noeud.getNoeud());
			}
		}
	}

	public void paint(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 1, 1, (int) this.getSize().getWidth() - 2, (int) this.getSize().getHeight() - 2, this);

		System.out.println(this.alNoeudDessin.size());
		for (NoeudDessin noeud : this.alNoeudDessin) {

			g2d.setColor(Color.RED);
			g2d.fill(noeud.getEllipse2D());
			g2d.setColor(Color.YELLOW);
			g2d.draw(noeud.getEllipse2D());
		}
	}
}