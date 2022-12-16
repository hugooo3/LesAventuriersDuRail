package metier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

import application.Application;

public class Metier {
    private Application app;

    private ArrayList<Noeud> alNoeuds;
    private ArrayList<Arete> alAretes;
    private ArrayList<CarteDestination> alCartesDestination;
    private ArrayList<CarteWagon> alCartesWagon;
    private File imgMappe;
    private int nbJoueurMin;
    private int nbJoueurMax;
    private int nbJoueurDoubleVoies;
    private int nbWagonJoueur;

    public Metier(Application app) {
        this.app = app;
    }

    public Mappe creerMappe() {
        return new Mappe(this.alNoeuds, this.alAretes, this.alCartesDestination, this.alCartesWagon, this.imgMappe,

                this.nbJoueurMin, this.nbJoueurMax, this.nbJoueurDoubleVoies, this.nbWagonJoueur);
    }

    public void ecrireXml(Mappe mappe) {
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
        alNoeuds.sort(new Comparator<Noeud>() {
            @Override
            public int compare(Noeud o1, Noeud o2) {
                return o1.getNom().compareTo(o2.getNom());
            }
        });

        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sortie/Mappe.xml"), "UTF-8"));

            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            pw.println("<liste>");

            /* Generation XML : Noeud */
            for (Noeud noeud : alNoeuds) {
                pw.println("\t<noeud nom=\"" + noeud.getNom() + "\">");
                pw.println("\t\t<coordonees x=\"" + noeud.getX() + "\" y=\"" + noeud.getY() + "\"/>");
                pw.println("\t</noeud>");
            }

            /* Generation XML : Arete */
            for (Arete arete : alAretes) {
                pw.println("\t<arete>");
                pw.println("\t\t<noeud1>" + arete.getNoeud1().getNom() + "</noeud1>");
                pw.println("\t\t<noeud2>" + arete.getNoeud2().getNom() + "</noeud2>");
                pw.println("\t\t<couleur>" + arete.getCouleur() + "</couleur>");
                pw.println("\t\t<troncons>" + arete.getTroncons() + "</troncons>");
                pw.println("\t\t<voieDouble>" + arete.getVoieDouble() + "</voieDouble>");
                pw.println("\t</arete>");
            }

            /* Generation XML : CarteDestination */
            for (CarteDestination carteDestination : alCartesDestination) {
                pw.println("\t<carteDestination>");
                pw.println("\t\t<noeud1>" + carteDestination.getNoeud1().getNom() + "</noeud1>");
                pw.println("\t\t<noeud2>" + carteDestination.getNoeud2().getNom() + "</noeud2>");
                pw.println("\t\t<points>" + carteDestination.getPoints() + "</points>");
                pw.println("\t\t<imgRecto>" + carteDestination.getImgRecto() + "</imgRecto>");
                pw.println("\t\t<imgVerso>" + carteDestination.getImgVerso() + "</imgVerso>");
                pw.println("\t</carteDestination>");
            }

            /* Generation XML : CarteWagon */
            for (CarteWagon carteWagon : alCartesWagon) {
                pw.println("\t<carteWagon>");
                pw.println("\t\t<couleur>" + carteWagon.getCouleur() + "</couleur>");
                pw.println("\t\t<imgRecto>" + carteWagon.getImgRecto() + "</imgRecto>");
                pw.println("\t\t<imgVerso>" + carteWagon.getImgVerso() + "</imgVerso>");
                pw.println("\t</carteWagon>");
            }

            pw.println("</liste>");

            pw.close();
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public boolean creerAlNoeuds(ArrayList<Noeud> ListNoeuds)                                  { this.alNoeuds = ListNoeuds; return true; }
    public boolean creerAlAretes(ArrayList<Arete> ListAretes)                                  { this.alAretes = ListAretes; return true; }
    public boolean creerAlCartesDestination(ArrayList<CarteDestination> ListCartesDestination) { this.alCartesDestination = ListCartesDestination; return true; }
    public boolean creerAlCartesWagon(ArrayList<CarteWagon> ListCartesWagon)                   { this.alCartesWagon = ListCartesWagon; return true; }

    public boolean setImgMappe(File imgMappe)                      { this.imgMappe = imgMappe; return true; }
    public boolean setNbJoueurMin(int nbJoueurMin)                 { this.nbJoueurMin = nbJoueurMin; return true; }
    public boolean setNbJoueurMax(int nbJoueurMax)                 { this.nbJoueurMax = nbJoueurMax; return true; }
    public boolean setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) { this.nbJoueurDoubleVoies = nbJoueurDoubleVoies; return true; }
    public boolean setNbWagonJoueur(int nbWagonJoueur)             { this.nbWagonJoueur = nbWagonJoueur; return true; }

    public void verifMAJ(String source) {
        switch (source) {
            case "param":
                System.out.println("imgMappe : " + this.imgMappe);
                System.out.println("nbJoueurMin : " + this.nbJoueurMin);
                System.out.println("nbJoueurMax : " + this.nbJoueurMax);
                System.out.println("nbJoueurDoubleVoies : " + this.nbJoueurDoubleVoies);
                System.out.println("nbWagonJoueur : " + this.nbWagonJoueur);
                System.out.println("\n");
                break;
            case "noeud":
                for (Noeud noeud : this.alNoeuds) {
                    System.out.println("nom : " + noeud.getNom());
                    System.out.println("x : " + noeud.getX());
                    System.out.println("y : " + noeud.getY());
                    System.out.println("\n");
                }
                break;
            case "arete":
                for (Arete arete : this.alAretes) {
                    System.out.println("noeud1 : " + arete.getNoeud1().getNom());
                    System.out.println("noeud2 : " + arete.getNoeud2().getNom());
                    System.out.println("couleur : " + arete.getCouleur());
                    System.out.println("troncons : " + arete.getTroncons());
                    System.out.println("voieDouble : " + arete.getVoieDouble());
                    System.out.println("\n");
                }
                break;
            case "carteDestination":
                for (CarteDestination carteDestination : this.alCartesDestination) {
                    System.out.println("noeud1 : " + carteDestination.getNoeud1().getNom());
                    System.out.println("noeud2 : " + carteDestination.getNoeud2().getNom());
                    System.out.println("points : " + carteDestination.getPoints());
                    System.out.println("imgRecto : " + carteDestination.getImgRecto());
                    System.out.println("imgVerso : " + carteDestination.getImgVerso());
                    System.out.println("\n");
                }
                break;
            case "carteWagon":
                for (CarteWagon carteWagon : this.alCartesWagon) {
                    System.out.println("couleur : " + carteWagon.getCouleur());
                    System.out.println("imgRecto : " + carteWagon.getImgRecto());
                    System.out.println("imgVerso : " + carteWagon.getImgVerso());
                    System.out.println("\n");
                }
                break;
            default:
                System.out.println("Erreur : source inconnue");
                break;
        }
    }

    public boolean majIHM() { return this.app.majIHM(); }
}