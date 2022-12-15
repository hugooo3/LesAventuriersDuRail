package ihm.conception;

import metier.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class PanelMappe extends JPanel {
	private Image img;
	private int largeur;
	private int hauteur;
	private boolean cliquable;
	private NoeudDessin noeudSelec = null;
	private NoeudDessin noeudTexteSelec = null;

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
		this.setPreferredSize(new Dimension((int) (this.largeur * 0.7), this.hauteur));

		if (this.cliquable) {
			// Dessin sur le Panel
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					for (NoeudDessin noeudDessin : PanelMappe.this.alNoeudDessin) {
						if (noeudDessin.getEllipse2D().contains(e.getPoint())) {
							// JOptionPane.showMessageDialog(frame, "Un noeud existe déjà à cet
							// emplacement");
							PanelMappe.this.noeudSelec = noeudDessin;
							return;
						}
						else if (noeudDessin.getRectangle2d().contains(e.getPoint()))
						{
							PanelMappe.this.noeudTexteSelec = noeudDessin;
							return;
						}
					}

					JLabel lblNom = new JLabel("Nom du Noeud : ");
					String nomNoeud = JOptionPane.showInputDialog(PanelMappe.this.frame, lblNom, "Création d'un Noeud",
							JOptionPane.QUESTION_MESSAGE);

					while (nomNoeud == null || nomNoeud.equals("")) {
						JOptionPane.showMessageDialog(null, "Le champ saisi ne peut être vide !", "Erreur",
								JOptionPane.ERROR_MESSAGE);

						nomNoeud = JOptionPane.showInputDialog(PanelMappe.this.frame, lblNom, "Création d'un Noeud",
								JOptionPane.QUESTION_MESSAGE);
					}

					PanelMappe.this.noeudSelec = new NoeudDessin(nomNoeud, e.getX() - 20, e.getY() - 20, 20);
					PanelMappe.this.alNoeudDessin.add(PanelMappe.this.noeudSelec);
					PanelMappe.this.alNoeud.add(
							new Noeud(nomNoeud, PanelMappe.this.noeudSelec.getX(), PanelMappe.this.noeudSelec.getY()));

					if (PanelMappe.this.frame instanceof FrameNoeud) {
						((FrameNoeud) PanelMappe.this.frame).majLstNoeuds(PanelMappe.this.alNoeud);
					}

					repaint();
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
					if (PanelMappe.this.noeudSelec != null) {
						PanelMappe.this.noeudSelec.setX(e.getX());
						PanelMappe.this.noeudSelec.setY(e.getY());

						PanelMappe.this.noeudSelec.setEllipseX(e.getX());
						PanelMappe.this.noeudSelec.setEllipseY(e.getY());

						repaint();
					}
					else if (PanelMappe.this.noeudTexteSelec != null)
					{
						PanelMappe.this.noeudTexteSelec.setX(e.getX());
						PanelMappe.this.noeudTexteSelec.setY(e.getY());

						PanelMappe.this.noeudTexteSelec.setEllipseX(e.getX());
						PanelMappe.this.noeudTexteSelec.setEllipseY(e.getY());

					}
				}
			});
		}
	}

	public void setLstNoeud(ArrayList<Noeud> alNoeud) {
		this.alNoeud = alNoeud;

		for (Noeud noeud : this.alNoeud) {
			this.alNoeudDessin.add(new NoeudDessin(noeud.getNom(), noeud.getX(), noeud.getY(), 20));
		}
	}

	public void paint(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 1, 1, (int) this.getSize().getWidth() - 2, (int) this.getSize().getHeight() - 2, this);

		for (NoeudDessin noeud : this.alNoeudDessin) {
			g2d.setColor(Color.RED);
			g2d.fill(noeud.getEllipse2D());
			g2d.setColor(Color.YELLOW);
			g2d.draw(noeud.getEllipse2D());
			g2d.setColor(Color.WHITE);
			g2d.fill(noeud.getRectangle2d());
			g2d.setColor(Color.BLACK);
			g2d.draw(noeud.getRectangle2d());
			g2d.drawString(noeud.getNom(), noeud.getNomX(), noeud.getNomY());
		}
	}
}