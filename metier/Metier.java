package metier;

import javax.swing.ImageIcon;
import java.awt.Color;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;

public class Metier 
{
	private ImageIcon VERSO_CARTE = new ImageIcon("images/ArriereCarte.png");

	private ArrayList<Noeud> alNoeuds;
	private ArrayList<Arete> alAretes;
	private ArrayList<CarteDestination> alCartesDestination;
	private ArrayList<CarteWagon> alCartesWagon;
	private File imgMappe;
	private int nbJoueurMin;
	private int nbJoueurMax;
	private int nbJoueurDoubleVoies;
	private int nbWagonJoueur;

	public Metier() 
	{
		this.alNoeuds = new ArrayList<Noeud>();
		this.alAretes = new ArrayList<Arete>();
		this.alCartesDestination = new ArrayList<CarteDestination>();
		this.alCartesWagon = new ArrayList<CarteWagon>();

		// Carte Wagon par défaut
		this.alCartesWagon.add(new CarteWagon("Neutre"	, Color.GRAY	, null										, null				,20));
		this.alCartesWagon.add(new CarteWagon("Blanc"	, Color.WHITE	, new ImageIcon("images/carteBlanche.png")	, this.VERSO_CARTE	, 20));
		this.alCartesWagon.add(new CarteWagon("Bleu"	, Color.BLUE	, new ImageIcon("images/carteBleu.png")		, this.VERSO_CARTE	, 20));
		this.alCartesWagon.add(new CarteWagon("Jaune"	, Color.YELLOW	, new ImageIcon("images/carteJaune.png")	, this.VERSO_CARTE	, 20));
		this.alCartesWagon.add(new CarteWagon("Noire"	, Color.BLACK	, new ImageIcon("images/carteNoire.png")	, this.VERSO_CARTE	, 20));
		this.alCartesWagon.add(new CarteWagon("Orange"	, Color.ORANGE	, new ImageIcon("images/carteOrange.png")	, this.VERSO_CARTE	, 20));
		this.alCartesWagon.add(new CarteWagon("Rouge"	, Color.RED		, new ImageIcon("images/carteRouge.png")	, this.VERSO_CARTE	, 20));
		this.alCartesWagon.add(new CarteWagon("Verte"	, Color.GREEN	, new ImageIcon("images/carteVerte.png")	, this.VERSO_CARTE	, 20));
		this.alCartesWagon.add(new CarteWagon("Violet"	, Color.MAGENTA	, new ImageIcon("images/carteViolet.png")	, this.VERSO_CARTE	, 20));
		this.alCartesWagon.add(new CarteWagon("Joker"	, Color.PINK	, new ImageIcon("images/carteJoker.png")	, this.VERSO_CARTE	, 20));
	}

	public ArrayList<Noeud> getAlNoeuds() {return this.alNoeuds;}
	public ArrayList<Arete> getAlAretes() {return this.alAretes;}
	public ArrayList<CarteDestination> getAlCartesDestination() {return this.alCartesDestination;}
	public ArrayList<CarteWagon> getAlCartesWagon() {return this.alCartesWagon;}
	public File getImgMappe() {return this.imgMappe;}
	public int getNbJoueurMin() {return this.nbJoueurMin;}
	public int getNbJoueurMax() {return this.nbJoueurMax;}
	public int getNbJoueurDoubleVoies() {return this.nbJoueurDoubleVoies;}
	public int getNbWagonJoueur() {return this.nbWagonJoueur;}
	public ImageIcon getVersoCarte() {return this.VERSO_CARTE;}

	public boolean creerAlNoeuds(ArrayList<Noeud> ListNoeuds)                                  { this.alNoeuds = ListNoeuds; return true; }
	public boolean creerAlAretes(ArrayList<Arete> ListAretes)                                  { this.alAretes = ListAretes; return true; }
	public boolean creerAlCartesDestination(ArrayList<CarteDestination> ListCartesDestination) { this.alCartesDestination = ListCartesDestination; return true; }
	public boolean creerAlCartesWagon(ArrayList<CarteWagon> ListCartesWagon)                   { this.alCartesWagon = ListCartesWagon; return true; }

	public boolean setImgMappe(File imgMappe)                      	{ this.imgMappe = imgMappe; return true; }
	public boolean setNbJoueurMin(int nbJoueurMin)                 	{ this.nbJoueurMin = nbJoueurMin; return true; }
	public boolean setNbJoueurMax(int nbJoueurMax)                 	{ this.nbJoueurMax = nbJoueurMax; return true; }
	public boolean setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) 	{ this.nbJoueurDoubleVoies = nbJoueurDoubleVoies; return true; }
	public boolean setNbWagonJoueur(int nbWagonJoueur)             	{ this.nbWagonJoueur = nbWagonJoueur; return true; }
	
	public void setVersoCarte(ImageIcon versoCarte)				
	{
		this.VERSO_CARTE = versoCarte;
		for (int i = 1; i < this.alCartesWagon.size(); i++)
			this.alCartesWagon.get(i).setImgVerso(versoCarte);
	}

	public Mappe creerMappe() 
	{
		return new Mappe(this.alNoeuds, this.alAretes, this.alCartesDestination, this.alCartesWagon, this.imgMappe,
				this.nbJoueurMin, this.nbJoueurMax, this.nbJoueurDoubleVoies, this.nbWagonJoueur);
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
			public int compare(Noeud o1, Noeud o2) {return o1.getNom().compareTo(o2.getNom());}
		});

		try 
		{
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sortie/Mappe.xml"), "UTF-8"));

			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			pw.println("<liste>");

			/* Generation XML : Parametre */

			pw.println("\t<parametre>");
			pw.println("\t\t<nbJoueurMin>" + this.nbJoueurMin + "</nbJoueurMin>");
			pw.println("\t\t<nbJoueurMax>" + this.nbJoueurMax + "</nbJoueurMax>");
			pw.println("\t\t<nbJoueurDoubleVoies>" + this.nbJoueurDoubleVoies + "</nbJoueurDoubleVoies>");
			pw.println("\t\t<nbWagonJoueur>" + this.nbWagonJoueur + "</nbWagonJoueur>");
			pw.println("\t</parametre>");

			/* Generation XML : Noeud */
			for (Noeud noeud : alNoeuds) 
			{
				pw.println("\t<noeud nom=\"" + noeud.getNom() + "\">");
				pw.println("\t\t<coordonees x=\"" + noeud.getX() + "\" y=\"" + noeud.getY() + "\"/>");
				pw.println("\t\t<coordoneesNom x=\"" + noeud.getNomX() + "\" y=\"" + noeud.getNomY() + "\"/>");
				pw.println("\t</noeud>");
			}

			/* Generation XML : Arete */
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

			/* Generation XML : CarteDestination */
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

			/* Generation XML : CarteWagon */
			for (CarteWagon carteWagon : alCartesWagon) 
			{
				pw.println("\t<carteWagon>");
				pw.println("\t\t<nomCouleur>" + carteWagon.getNomCouleur() + "</nomCouleur>");
				pw.println("\t\t<couleur>" + carteWagon.getCouleur() + "</couleur>");
				pw.println("\t\t<imgRecto>" + carteWagon.getImgRecto() + "</imgRecto>");
				pw.println("\t\t<imgVerso>" + carteWagon.getImgVerso() + "</imgVerso>");
				pw.println("\t</carteWagon>");
			}

			pw.println("</liste>");

			pw.close();

			System.out.println("Fichier Mappe.xml généré avec succès !");
		} 
		catch (Exception e) {e.printStackTrace();}
	}

	public boolean copierImage(String nom, File file)
	{
		File src = file;
		String extension = src.getAbsolutePath().substring(src.getAbsolutePath().lastIndexOf("."));

		if (extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".JPG") || extension.equals(".JPEG") || extension.equals(".png") || extension.equals(".PNG"))
		{
			File target = new File("sortie/" + nom + extension);	
			try 
			{
				Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} 
			catch (IOException e) 
			{
				System.out.println("Erreur lors de la copie de l'image");
				e.printStackTrace();
			}
			return true;
		}
		System.out.println("Fichier non supporté");
		return false;
	}

	public boolean reinitialiserDossierSortie()
	{
		File dossierSortie = new File("sortie");
		if (dossierSortie.exists()) 
		{ 
			if (dossierSortie.list().length ==0) { dossierSortie.delete(); }
			else
			{
				String files[] = dossierSortie.list();
     
				for (String tmp : files) {
				   File file = new File(dossierSortie, tmp);
				   //suppression récursive
				   file.delete();
				}
			}
		}
		return dossierSortie.mkdir();
	}
}