package ihm.conception;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import metier.Noeud;

public class NoeudDessin extends Noeud
{
	private final int TAILLE_CARA_BOX = 15; // Hauteur pour que le rectangle laisse le texte avec un espace satisfesant
	private final int MARGE_X = 3;
	private final int MARGE_Y = 4;

	private int radiusEllipse;
	private int ellipseX;
	private int ellipseY;
	private Ellipse2D ellipse2D;

	private int longueurRectangle;
	private int rectangleX;
	private int rectangleY;
	private Rectangle2D rectangle2d;

	public NoeudDessin(String nom, int x, int y, int radius) 
	{
		super(nom, x, y);

		this.ellipseX = x - radius / 2;
		this.ellipseY = y - radius / 2;
		this.radiusEllipse = radius;
		this.majEllipse2D();
		
		this.setRectangleX();
		this.setRectangleY();
		this.setRectangleLongueur(); // this.majRectangle2d() est execute ici
	}

	public NoeudDessin(Noeud noeud, int radius)
	{
		this(noeud.getNom(), noeud.getX(), noeud.getY(), radius);
		this.nomDeltaX = noeud.getNomDeltaX();
		this.nomDeltaY = noeud.getNomDeltaY();
		this.setRectangleX();
		this.setRectangleY();
	}

	public int getRadiusEllipse() {return radiusEllipse;}
	public Ellipse2D getEllipse2D() {return ellipse2D;}
	public int getEllipseX() {return this.ellipseX;}
	public int getEllipseY() {return this.ellipseY;}


	public int getLongueurRectangle() {return this.longueurRectangle;}
	public int getRectangleX() {return this.rectangleX;}
	public int getRectangleY() {return this.rectangleY;}

	public Rectangle2D getRectangle2d() {return this.rectangle2d;}

	public void setX(int x) {super.setX(x); this.setRectangleX(); this.setEllipseX(x);}
	public void setY(int y) {super.setY(y); this.setRectangleY(); this.setEllipseY(y);}	

	public void setNomDeltaX(int nomX) {super.setNomDeltaX(nomX); this.setRectangleX();}
	public void setNomDeltaY(int nomY) {super.setNomDeltaY(nomY); this.setRectangleY();}

	public void setEllipseX(int x) {this.ellipseX = x - (this.radiusEllipse / 2); this.majEllipse2D();}
	public void setEllipseY(int y) {this.ellipseY = y - (this.radiusEllipse / 2); this.majEllipse2D();}

	public void majEllipse2D() 
	{
		this.ellipse2D = new Ellipse2D.Double(this.ellipseX, this.ellipseY, this.radiusEllipse, this.radiusEllipse);
	}

	public void setRectangleX() {this.rectangleX = this.getNomX() - this.MARGE_X;	this.majRectangle2D();}
	public void setRectangleY() {this.rectangleY = this.getNomY() + this.MARGE_Y;	this.majRectangle2D();}

	public void setRectangleLongueur() {this.longueurRectangle = this.nom.length() * 7 + 5; this.majRectangle2D();} // Si le texte est maj -> Resize la box

	public void majRectangle2D() 
	{
		this.rectangle2d = new Rectangle2D.Double(this.rectangleX, this.rectangleY - this.TAILLE_CARA_BOX, this.longueurRectangle, this.TAILLE_CARA_BOX);
	}

	@Override
	public String toString() 
	{
		return super.toString();
	}
}