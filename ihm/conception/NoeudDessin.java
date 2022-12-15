package ihm.conception;

import java.awt.geom.Ellipse2D;
import metier.Noeud;

public class NoeudDessin extends Noeud
{
	private int radius;
	private int ellipseX;
	private int ellipseY;
	private Ellipse2D ellipse2D;

	public NoeudDessin(String nom, int x, int y, int radius) 
	{
		super(nom, x, y);
		this.ellipseX = x;
		this.ellipseY = y;

		this.radius = radius;
		this.ellipse2D = new Ellipse2D.Double(this.ellipseX + (radius / 2), this.ellipseY + (radius / 2), radius, radius);
	}

	public int getRadius() {return radius;}
	public Ellipse2D getEllipse2D() {return ellipse2D;}
	public int getEllipseX() {return this.ellipseX;}
	public int getEllipseY() {return this.ellipseY;}

	public void setX(int x) {super.x = x - this.radius;}
	public void setY(int y) {super.y = y - this.radius;}

	public void setEllipseX(int ellipseX) 
	{
		this.ellipseX = ellipseX - this.radius;
		this.majEllipse2D();
	}

	public void setEllipseY(int ellipseY) 
	{
		this.ellipseY = ellipseY - this.radius;
		this.majEllipse2D();
	}

	public void majEllipse2D()
	{
		this.ellipse2D = new Ellipse2D.Double(this.ellipseX + (radius / 2), this.ellipseY + (radius / 2), radius, radius);
	}

	@Override
	public String toString() {
		return super.toString() +
			"{" +
			" radius='" + getRadius() + "'" +
			", ellipse2D='" + getEllipse2D() + "'" +
			"}";
	}


}