package metier;

import javax.swing.ImageIcon;

public class CarteDestination {
	private Noeud noeud1;
	private Noeud noeud2;
	private int points;
	private ImageIcon imgRecto;
	private ImageIcon imgVerso;

	public CarteDestination(Noeud noeud1, Noeud noeud2, int points, ImageIcon imgRecto, ImageIcon imgVerso)
	{
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		this.points = points;
		this.imgRecto = imgRecto;
		this.imgVerso = imgVerso;
	}

	public Noeud getNoeud1() { return this.noeud1; }
	public Noeud getNoeud2() { return this.noeud2; }
	public int   getPoints() { return this.points; }
	public ImageIcon getImgRecto() { return this.imgRecto; }
	public ImageIcon getImgVerso() { return this.imgVerso; }

	public void setNoeud1(Noeud noeud1) { this.noeud1 = noeud1; }
	public void setNoeud2(Noeud noeud2) { this.noeud2 = noeud2; }
	public void setPoints(int points) { this.points = points; }
	public void setImgRecto(ImageIcon imgRecto) { this.imgRecto = imgRecto; }
	public void setImgVerso(ImageIcon imgVerso) { this.imgVerso = imgVerso; }

	public String toString() {return noeud1.getNom() + " - " + noeud2.getNom() + " | " + this.points;}
}
