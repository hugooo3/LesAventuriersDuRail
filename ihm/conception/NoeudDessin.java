package ihm.conception;

import java.awt.geom.Ellipse2D;
import metier.Noeud;

public class NoeudDessin {

    private int posX;
    private int posY;
    private int radius;
    private Noeud noeud;

    private Ellipse2D ellipse2D;

    public NoeudDessin(int posX, int posY, int radius, Noeud noeud) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.noeud = noeud;

        this.ellipse2D = new Ellipse2D.Double(posX + (radius / 2), posY + (radius / 2), radius, radius);
    }

    public Noeud getNoeud() {
        return noeud;
    }

    public void setNoeud(Noeud noeud) {
        this.noeud = noeud;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getRadius() {
        return radius;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        this.ellipse2D = new Ellipse2D.Double(posX, posY, radius, radius);
    }

    public void setPosY(int posY) {
        this.posY = posY;
        this.ellipse2D = new Ellipse2D.Double(posX, posY, radius, radius);
    }

    public Ellipse2D getEllipse2D() {
        return ellipse2D;
    }
}