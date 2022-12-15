package metier;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

import application.Application;

public class Metier 
{
    private Application app;

    private ArrayList<Noeud> alNoeuds;
	private ArrayList<Arete> alAretes;
	private ArrayList<CarteDestination> alCartesDestination;
	private ArrayList<CarteWagon>       alCartesWagon;
	private String imgMappe;
	private int nbJoueurMin;
	private int nbJoueurMax;
	private int nbJoueurDoubleVoies;
	private int nbWagonJoueur;
    
    public Metier(Application app) 
    {
        this.app = app;
    }

    public Mappe creerMappe()
    {
        return new Mappe(this.alNoeuds, this.alAretes, this.alCartesDestination, this.alCartesWagon, this.imgMappe, this.nbJoueurMin, this.nbJoueurMax, this.nbJoueurDoubleVoies, this.nbWagonJoueur);
    }

    public void ecrireXml(Mappe mappe)
	{
		PrintWriter pw;

        ArrayList<Noeud> alNoeuds = new ArrayList<Noeud>();
        ArrayList<Arete> alAretes = new ArrayList<Arete>();
        ArrayList<CarteDestination> alCartesDestination = new ArrayList<CarteDestination>();
        ArrayList<CarteWagon> alCartesWagon = new ArrayList<CarteWagon>();

        alNoeuds = mappe.getAlNoeuds();
        alAretes = mappe.getAlAretes();
        alCartesDestination = mappe.getAlCartesDestination();
        alCartesWagon = mappe.getAlCartesWagon();

        /* Tri des noeuds par ordre alphabetique */
		alNoeuds.sort(new Comparator<Noeud>() 		 
		{
			@Override
			public int compare(Noeud o1, Noeud o2) 
			{
				return o1.getNom().compareTo(o2.getNom());
			}			
		});

		try
		{
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sortie/Mappe.xml"), "UTF-8"));

			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			pw.println("<liste>");

            /* Generation XML : Noeud*/
			for (Noeud noeud : alNoeuds)
			{	
                pw.println("\t<noeud nom=\"" + noeud.getNom() + "\">");
				pw.println("\t\t<coordonees x=\""+ noeud.getX()+ "\" y=\"" + noeud.getY() + "\"/>");
				pw.println("\t</noeud>");
			}
            
            /* Generation XML : Arete*/
			for (Arete arete : alAretes)
			{	
                pw.println("\t<arete>");
                pw.println("\t\t<noeud1>" + arete.getNoeud1().getNom() + "</noeud1>");
                pw.println("\t\t<noeud2>" + arete.getNoeud2().getNom() + "</noeud2>");
                pw.println("\t\t<couleur>" + arete.getCouleur() + "</couleur>");
                pw.println("\t\t<troncons>" + arete.getTroncons() + "</troncons>");
                pw.println("\t\t<voieDouble>" + arete.getVoieDouble() + "</voieDouble>");
				pw.println("\t</arete>");
			}

            /* Generation XML : CarteDestination*/
            for (CarteDestination carteDestination : alCartesDestination)
            {
                pw.println("\t<carteDestination>");
                pw.println("\t\t<noeud1>" + carteDestination.getNoeud1().getNom() + "</noeud1>");
                pw.println("\t\t<noeud2>" + carteDestination.getNoeud2().getNom() + "</noeud2>");
                pw.println("\t\t<points>" + carteDestination.getPoints() + "</points>");
                pw.println("\t\t<imgRecto>" + carteDestination.getImgRecto() + "</imgRecto>");
                pw.println("\t\t<imgVerso>" + carteDestination.getImgVerso() + "</imgVerso>");
                pw.println("\t</carteDestination>");
            }

            /* Generation XML : CarteWagon*/
            for (CarteWagon carteWagon : alCartesWagon)
            {
                pw.println("\t<carteWagon>");
                pw.println("\t\t<couleur>" + carteWagon.getCouleur() + "</couleur>");
                pw.println("\t\t<imgRecto>" + carteWagon.getImgRecto() + "</imgRecto>");
                pw.println("\t\t<imgVerso>" + carteWagon.getImgVerso() + "</imgVerso>");
                pw.println("\t</carteWagon>");
            }

			pw.println("</liste>");

			pw.close();
		}
		catch (Exception e) { e.printStackTrace(); }
	} 
	
    public boolean creerAlNoeuds(ArrayList<Noeud> ListNoeuds) { this.alNoeuds = ListNoeuds; return true; }
    public boolean creerAlAretes(ArrayList<Arete> ListAretes) { this.alAretes = ListAretes; return true; }
    public boolean creerAlCartesDestination(ArrayList<CarteDestination> ListCartesDestination) { this.alCartesDestination = ListCartesDestination; return true; }
    public boolean creerAlCartesWagon(ArrayList<CarteWagon> ListCartesWagon) { this.alCartesWagon = ListCartesWagon; return true; }

	public boolean setImgMappe(String imgMappe) 						     {this.imgMappe = imgMappe; return true;}
	public boolean setNbJoueurMin(int nbJoueurMin) 						     {this.nbJoueurMin = nbJoueurMin; return true;}
	public boolean setNbJoueurMax(int nbJoueurMax) 						     {this.nbJoueurMax = nbJoueurMax; return true;}
	public boolean setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) 		     {this.nbJoueurDoubleVoies = nbJoueurDoubleVoies; return true;}
	public boolean setNbWagonJoueur(int nbWagonJoueur) 					     {this.nbWagonJoueur = nbWagonJoueur; return true;}


}