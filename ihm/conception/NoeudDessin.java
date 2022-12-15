package ihm.conception;

import java.awt.geom.Ellipse2D;
import metier.Noeud;

public class NoeudDessin extends Noeud
{
	private int radius;
	private Ellipse2D ellipse2D;

	public NoeudDessin(String nom, int x, int y, int radius) 
	{
		super(nom, x, y);

		this.radius = radius;
		this.ellipse2D = new Ellipse2D.Double(x + (radius / 2), y + (radius / 2), radius, radius);
	}

	public int getRadius() {return radius;}
	public Ellipse2D getEllipse2D() {return ellipse2D;}

	@Override
	public String toString() {
		return super.toString() +
			"{" +
			" radius='" + getRadius() + "'" +
			", ellipse2D='" + getEllipse2D() + "'" +
			"}";
	}


}