package ihm.conception;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import metier.Noeud;

public class NoeudDessin extends Noeud
{
	private int radiusEllipse;
	private int ellipseX;
	private int ellipseY;
	private Ellipse2D ellipse2D;

	private int longueurRectangle;
	private int hauteurRectangle;
	private int rectangleX;
	private int rectangleY;
	private Rectangle2D rectangle2d;

	private int nomX;
	private int nomY;

	public NoeudDessin(String nom, int x, int y, int radius) 
	{
		super(nom, x, y);

		this.ellipseX = x;
		this.ellipseY = y;
		this.radiusEllipse = radius;
		this.ellipse2D = new Ellipse2D.Double(this.ellipseX + (this.radiusEllipse / 2), this.ellipseY + (this.radiusEllipse / 2), this.radiusEllipse, this.radiusEllipse);

		this.longueurRectangle = this.nom.length() * 10;
		this.hauteurRectangle = this.radiusEllipse - 5;
		this.rectangleX = this.x;
		this.rectangleY = this.y;
		this.rectangle2d = new Rectangle2D.Double(this.rectangleX, this.rectangleY - 15, this.longueurRectangle, this.hauteurRectangle);

		this.nomX = this.x + 3;
		this.nomY = this.y - 4;
		/*
		 * POUR LE TEXTE UNE FORME QUE JE DETECTERAIS DANS LE PANEL MAPPE
		 */
	}

	public int getRadiusEllipse() {return radiusEllipse;}
	public Ellipse2D getEllipse2D() {return ellipse2D;}
	public int getEllipseX() {return this.ellipseX;}
	public int getEllipseY() {return this.ellipseY;}


	public int getLongueurRectangle() {return this.longueurRectangle;}
	public int getHauteurRectangle() {return this.hauteurRectangle;}
	public int getRectangleX() {return this.rectangleX;}
	public int getRectangleY() {return this.rectangleY;}

	public Rectangle2D getRectangle2d() {return this.rectangle2d;}

	public int getNomX() {return this.nomX;}
	public int getNomY() {return this.nomY;}

	public void setX(int x) {super.x = x - this.radiusEllipse;}
	public void setY(int y) {super.y = y - this.radiusEllipse;}	

	public void setNomX(int nomX) {this.nomX = nomX;}
	public void setNomY(int nomY) {this.nomY = nomY;}

	public void setEllipseX(int ellipseX) 
	{
		this.ellipseX = ellipseX - this.radiusEllipse;
		this.majEllipse2D();
	}

	public void setEllipseY(int ellipseY) 
	{
		this.ellipseY = ellipseY - this.radiusEllipse;
		this.majEllipse2D();
	}

	public void majEllipse2D()
	{
		this.ellipse2D = new Ellipse2D.Double(this.ellipseX + (radiusEllipse / 2), this.ellipseY + (radiusEllipse / 2), radiusEllipse, radiusEllipse);
	}


	public void setRectangleX(int rectangleX) 
	{
		this.rectangleX = rectangleX - this.radiusEllipse;
		this.majRectangle2D();
	}

	public void setRectangleY(int rectangleY) 
	{
		this.rectangleY = rectangleY - this.radiusEllipse;
		this.majRectangle2D();
	}

	public void setRectangleLongueur()
	{
		this.longueurRectangle = this.nom.length() * 10 + 5;;
		this.majRectangle2D();
	}

	public void majRectangle2D()
	{
		this.rectangle2d = new Rectangle2D.Double(this.rectangleX, this.rectangleY - 15, this.longueurRectangle, this.hauteurRectangle);
	}

	@Override
	public String toString() {
		return super.toString() +
			"{" +
			" radius='" + getRadiusEllipse() + "'" +
			", ellipse2D='" + getEllipse2D() + "'" +
			"}";
	}


}