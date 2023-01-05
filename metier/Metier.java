package metier;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;

public class Metier {
	private static int RADIUS = 20;
	private String imgMappePath;
	private String versoCartePath;

	private ArrayList<Noeud> alNoeuds;
	private ArrayList<Arete> alAretes;
	private ArrayList<CarteDestination> alCartesDestination;
	private ArrayList<CarteWagon> alCartesWagon;

	private Image imgMappe;
	private int nbJoueurMin;
	private int nbJoueurMax;
	private int nbJoueurDoubleVoies;
	private int nbWagonJoueur;
	private int nbFin;

	public Metier() {
		this.alNoeuds = new ArrayList<Noeud>();
		this.alAretes = new ArrayList<Arete>();
		this.alCartesDestination = new ArrayList<CarteDestination>();
		this.alCartesWagon = new ArrayList<CarteWagon>();

		// Carte Wagon par défaut
		this.versoCartePath = "/images/ArriereCarte.png";
		this.imgMappePath = "/images/carteUSA.png"; // Affichage dans la menuBar

		this.alCartesWagon.add(new CarteWagon("Neutre"	, Color.GRAY	, null						, null					, 20));
		this.alCartesWagon.add(new CarteWagon("Blanc"	, Color.WHITE	, "/images/carteBlanche.png", this.versoCartePath	, 20));
		this.alCartesWagon.add(new CarteWagon("Bleu"	, Color.BLUE	, "/images/carteBleu.png"	, this.versoCartePath	, 20));
		this.alCartesWagon.add(new CarteWagon("Jaune"	, Color.YELLOW	, "/images/carteJaune.png"	, this.versoCartePath	, 20));
		this.alCartesWagon.add(new CarteWagon("Noire"	, Color.BLACK	, "/images/carteNoire.png"	, this.versoCartePath	, 20));
		this.alCartesWagon.add(new CarteWagon("Orange"	, Color.ORANGE	, "/images/carteOrange.png"	, this.versoCartePath	, 20));
		this.alCartesWagon.add(new CarteWagon("Rouge"	, Color.RED		, "/images/carteRouge.png"	, this.versoCartePath	, 20));
		this.alCartesWagon.add(new CarteWagon("Verte"	, Color.GREEN	, "/images/carteVerte.png"	, this.versoCartePath	, 20));
		this.alCartesWagon.add(new CarteWagon("Violet"	, Color.MAGENTA	, "/images/carteViolet.png"	, this.versoCartePath	, 20));
		this.alCartesWagon.add(new CarteWagon("Joker"	, Color.PINK	, "/images/carteJoker.png"	, this.versoCartePath	, 20));
	}

	/***************************/
	/* Accesseurs et Mutateurs */
	/***************************/
	public String getImgMappePath() {return this.imgMappePath;}
	public ArrayList<Noeud> getAlNoeuds() {return this.alNoeuds;}
	public ArrayList<Arete> getAlAretes() {return this.alAretes;}
	public ArrayList<CarteDestination> getAlCartesDestination() {return this.alCartesDestination;}
	public ArrayList<CarteWagon> getAlCartesWagon() {return this.alCartesWagon;}
	public Image getImgMappe() {return this.imgMappe;}
	public int getNbJoueurMin() {return this.nbJoueurMin;}
	public int getNbJoueurMax() {return this.nbJoueurMax;}
	public int getNbJoueurDoubleVoies() {return this.nbJoueurDoubleVoies;}
	public int getNbWagonJoueur() {return this.nbWagonJoueur;}
	public String getVersoCartePath() {return this.versoCartePath;}

	public void setImgMappePath(String imgMappePath) {this.imgMappePath = imgMappePath;}
	public void setImgMappe(Image imgMappe) {this.imgMappe = imgMappe;}
	public void setNbJoueurMin(int nbJoueurMin) {this.nbJoueurMin = nbJoueurMin;}
	public void setNbJoueurMax(int nbJoueurMax) {this.nbJoueurMax = nbJoueurMax;}
	public void setNbJoueurDoubleVoies(int nbJoueurDoubleVoies) {this.nbJoueurDoubleVoies = nbJoueurDoubleVoies;}
	public void setNbWagonJoueur(int nbWagonJoueur) {this.nbWagonJoueur = nbWagonJoueur;}
	public void setNbFin(int nbFin) {this.nbFin = nbFin;}

	public void setVersoCarte(String versoCartePath) {
		this.versoCartePath = versoCartePath;
		for (int i = 1; i < this.alCartesWagon.size(); i++)
			this.alCartesWagon.get(i).setImgVerso(versoCartePath);
	}

	/****************************************************************/
	/* PARTIE CONCEPTEUR */
	/*																*/
	/* Méthodes permettant la conception de la mappe */
	/****************************************************************/

	/***********************************************************/
	/* Parcours des ArrayLists et ecriture dans le fichier XML */
	/***********************************************************/
	public void ecrireXml() {
		PrintWriter pw;

		// Tri des noeuds par ordre alphabetique
		this.alNoeuds.sort(new Comparator<Noeud>() {
			@Override
			public int compare(Noeud o1, Noeud o2) {
				return o1.getNom().compareTo(o2.getNom());
			}
		});

		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sortie/Mappe.xml"), "UTF-8"));

			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			pw.println("<liste>");

			// Encodage carte fond
			String imgMappeEncode = "";
			if (new File(this.imgMappePath).exists()) {
				File file = new File(this.imgMappePath);
				imgMappeEncode = fileToString(file);
			} else {
				imgMappeEncode = this.imgMappePath;
			}

			/* Generation XML : Parametre */
			pw.println("\t<parametres>");
			pw.println("\t\t<nbJoueurMin>" + this.nbJoueurMin + "</nbJoueurMin>");
			pw.println("\t\t<nbJoueurMax>" + this.nbJoueurMax + "</nbJoueurMax>");
			pw.println("\t\t<nbJoueurDoubleVoies>" + this.nbJoueurDoubleVoies + "</nbJoueurDoubleVoies>");
			pw.println("\t\t<nbWagonJoueur>" + this.nbWagonJoueur + "</nbWagonJoueur>");
			pw.println("\t\t<nbFin>" + this.nbFin + "</nbFin>");
			pw.println("\t\t<mappe>" + imgMappeEncode + "</mappe>");
			pw.println("\t</parametres>");

			/* Generation XML : Noeud */
			for (Noeud noeud : this.alNoeuds) {
				pw.println("\t<noeud nom=\"" + noeud.getNom() + "\">");
				pw.println("\t\t<x>" + noeud.getX() + "</x>");
				pw.println("\t\t<y>" + noeud.getY() + "</y>");
				pw.println("\t\t<nomDeltaX>" + noeud.getNomDeltaX() + "</nomDeltaX>");
				pw.println("\t\t<nomDeltaY>" + noeud.getNomDeltaY() + "</nomDeltaY>");
				pw.println("\t</noeud>");
			}

			/* Generation XML : Arete */
			for (Arete arete : this.alAretes) {
				pw.println("\t<arete>");
				pw.println("\t\t<noeud1>" + arete.getNoeud1().getNom() + "</noeud1>");
				pw.println("\t\t<noeud2>" + arete.getNoeud2().getNom() + "</noeud2>");
				pw.println("\t\t<couleur>" + arete.getCouleur() + "</couleur>");
				pw.println("\t\t<troncons>" + arete.getTroncons() + "</troncons>");
				pw.println("\t\t<voieDouble>" + arete.getVoieDouble() + "</voieDouble>");
				pw.println("\t\t<tronconsVoieDouble>" + arete.getTronconsDoubleVoie() + "</tronconsVoieDouble>");
				pw.println("\t\t<couleurVoieDouble>" + arete.getCouleurDoubleVoie() + "</couleurVoieDouble>");
				pw.println("\t</arete>");
			}

			/* Generation XML : CarteDestination */
			for (CarteDestination carteDestination : this.alCartesDestination) {
				pw.println("\t<carteDestination>");
				pw.println("\t\t<noeud1>" + carteDestination.getNoeud1().getNom() + "</noeud1>");
				pw.println("\t\t<noeud2>" + carteDestination.getNoeud2().getNom() + "</noeud2>");
				pw.println("\t\t<points>" + carteDestination.getPoints() + "</points>");
				pw.println("\t</carteDestination>");
			}

			// Encodage carte wagon
			String imgRectoEncode = "";
			String imgVersoEncode = "";
			/* Generation XML : CarteWagon */
			for (CarteWagon carteWagon : this.alCartesWagon) {
				if (!carteWagon.getNomCouleur().equals("Neutre")) {
					// Encodage image recto
					if (new File(carteWagon.getImgRectoPath()).exists()) {
						File file = new File(carteWagon.getImgRectoPath());
						imgRectoEncode = fileToString(file);
					} else {
						imgRectoEncode = carteWagon.getImgRectoPath();
					}
					// Encodage image verso
					if (new File(carteWagon.getImgVersoPath()).exists()) {
						File file = new File(carteWagon.getImgVersoPath());
						imgVersoEncode = fileToString(file);
					} else {
						imgVersoEncode = carteWagon.getImgVersoPath();
					}
				}
				pw.println("\t<carteWagon>");
				pw.println("\t\t<nomCouleur>" + carteWagon.getNomCouleur() + "</nomCouleur>");
				pw.println("\t\t<couleur>" + carteWagon.getCouleur().getRGB() + "</couleur>");
				pw.println("\t\t<imgRectoPath>" + imgRectoEncode + "</imgRectoPath>");
				pw.println("\t\t<imgVersoPath>" + imgVersoEncode + "</imgVersoPath>");
				pw.println("\t\t<nbCarteWagon>" + carteWagon.getNbCarteWagon() + "</nbCarteWagon>");
				pw.println("\t</carteWagon>");
			}

			pw.println("</liste>");

			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**************************************/
	/* Reinitialisation du dossier sortie */
	/**************************************/
	public boolean reinitialiserDossierSortie() {
		File dossierSortie = new File("sortie");
		if (dossierSortie.exists()) {
			if (dossierSortie.list().length == 0) {
				dossierSortie.delete();
			} else {
				String files[] = dossierSortie.list();

				for (String tmp : files) {
					File file = new File(dossierSortie, tmp);
					// suppression récursive
					file.delete();
				}
			}
		}
		return dossierSortie.mkdir();
	}

	/****************************************************************/
	/* PARTIE JEU */
	/*																*/
	/* Méthodes permettant le jeu */
	/****************************************************************/

	/****************************************************************/
	/* PARTIE AUXILIAIRE */
	/*																*/
	/* Méthodes communes aux deux applications */
	/****************************************************************/

	/**************************************/
	/* Importation d'une carte depuis un */
	/* dossier */
	/**************************************/
	public boolean importMappe(File xmlFile) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			/* Paramètres */
			NodeList nList = doc.getElementsByTagName("parametres");
			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
				this.nbJoueurMin = Integer.parseInt(e.getElementsByTagName("nbJoueurMin").item(0).getTextContent());
				this.nbJoueurMax = Integer.parseInt(e.getElementsByTagName("nbJoueurMax").item(0).getTextContent());
				this.nbJoueurDoubleVoies = Integer
						.parseInt(e.getElementsByTagName("nbJoueurDoubleVoies").item(0).getTextContent());
				this.nbWagonJoueur = Integer.parseInt(e.getElementsByTagName("nbWagonJoueur").item(0).getTextContent());
				this.nbFin = Integer.parseInt(e.getElementsByTagName("nbFin").item(0).getTextContent());

				// Decodage carte fond
				String imgEncode = e.getElementsByTagName("mappe").item(0).getTextContent();
				File imgMappeFile = null;
				if (imgEncode.substring(0, 8).equals("/images/")
						&& imgEncode.substring(imgEncode.length() - 4, imgEncode.length()).equals(".png")) {
					imgMappeFile = new File("." + imgEncode);
				} else {
					imgMappeFile = stringToFile(imgEncode, new File("./sortie/mappe.png"));
				}
				this.imgMappe = ImageIO.read(imgMappeFile);
			}

			/* Noeuds */
			nList = doc.getElementsByTagName("noeud");
			for (int i = 0; i < nList.getLength(); i++) {
				nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) nNode;
					Noeud noeud = new Noeud(e.getAttribute("nom"),
							Integer.parseInt(e.getElementsByTagName("x").item(0).getTextContent()),
							Integer.parseInt(e.getElementsByTagName("y").item(0).getTextContent()),
							Metier.RADIUS);
					noeud.setNomDeltaX(Integer.parseInt(e.getElementsByTagName("nomDeltaX").item(0).getTextContent())
							+ noeud.getX());
					noeud.setNomDeltaY(Integer.parseInt(e.getElementsByTagName("nomDeltaY").item(0).getTextContent())
							+ noeud.getY());
					this.alNoeuds.add(noeud);
				}
			}

			/* CarteDestination */
			nList = doc.getElementsByTagName("carteDestination");
			for (int i = 0; i < nList.getLength(); i++) {
				nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) nNode;
					Noeud noeud1 = null;
					Noeud noeud2 = null;
					for (Noeud noeud : this.alNoeuds) {
						if (noeud.getNom().equals(e.getElementsByTagName("noeud1").item(0).getTextContent())) {
							noeud1 = noeud;
						}
						if (noeud.getNom().equals(e.getElementsByTagName("noeud2").item(0).getTextContent())) {
							noeud2 = noeud;
						}
					}
					CarteDestination carteDestination = new CarteDestination(noeud1, noeud2,
							Integer.parseInt(e.getElementsByTagName("points").item(0).getTextContent()));
					this.alCartesDestination.add(carteDestination);
				}
			}

			/* CarteWagon */
			this.alCartesWagon = new ArrayList<CarteWagon>();
			nList = doc.getElementsByTagName("carteWagon");
			for (int i = 0; i < nList.getLength(); i++) {
				nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) nNode;

					String nomCouleur = e.getElementsByTagName("nomCouleur").item(0).getTextContent();
					Color couleur = new Color(
							Integer.parseInt(e.getElementsByTagName("couleur").item(0).getTextContent()));

					// Decodage image des cartes wagons
					String imgEncode = e.getElementsByTagName("imgRectoPath").item(0).getTextContent();
					File imgRectoFile = null;
					File imgVersoFile = null;

					CarteWagon carteWagon;
					if (!imgEncode.equals("") && imgEncode!=null)
					{
						if (imgEncode.substring(0, 8).equals("/images/")
								&& imgEncode.substring(imgEncode.length() - 4, imgEncode.length()).equals(".png")) {
							imgRectoFile = new File(imgEncode);
						} else {
							imgRectoFile = stringToFile(imgEncode, new File("./sortie/" + nomCouleur + ".png"));
						}


						imgEncode = e.getElementsByTagName("imgVersoPath").item(0).getTextContent();
						if (imgEncode.substring(0, 8).equals("/images/")
							&& imgEncode.substring(imgEncode.length() - 4, imgEncode.length()).equals(".png")) {
							imgVersoFile = new File(imgEncode);
						} else {
							imgVersoFile = stringToFile(imgEncode, new File("./sortie/" + nomCouleur + ".png"));
						}

						
						carteWagon = new CarteWagon(nomCouleur, couleur, imgRectoFile.getAbsolutePath(),
													imgVersoFile.getAbsolutePath(),
													Integer.parseInt(e.getElementsByTagName("nbCarteWagon").item(0).getTextContent()));
					}
					else {
						carteWagon = new CarteWagon(nomCouleur, couleur, null, null,
													Integer.parseInt(e.getElementsByTagName("nbCarteWagon").item(0).getTextContent()));
					}

					this.alCartesWagon.add(carteWagon);
				}
			}

			/* Arete */
			nList = doc.getElementsByTagName("arete");
			for (int i = 0; i < nList.getLength(); i++) {
				nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) nNode;
					Noeud noeud1 = null;
					Noeud noeud2 = null;
					CarteWagon carteWagon = null;
					CarteWagon couleurVoieDouble = null;

					for (Noeud noeud : this.alNoeuds) {
						if (noeud.getNom().equals(e.getElementsByTagName("noeud1").item(0).getTextContent())) {
							noeud1 = noeud;
						}
						if (noeud.getNom().equals(e.getElementsByTagName("noeud2").item(0).getTextContent())) {
							noeud2 = noeud;
						}
					}
					for (CarteWagon carteWagonTemp : this.alCartesWagon) {
						if (carteWagonTemp.getNomCouleur()
								.equals(e.getElementsByTagName("couleur").item(0).getTextContent())) {
							carteWagon = carteWagonTemp;
						}
						if (carteWagonTemp.getNomCouleur()
								.equals(e.getElementsByTagName("couleurVoieDouble").item(0).getTextContent())) {
							couleurVoieDouble = carteWagonTemp;
						}
					}
					Arete arete = new Arete(noeud1, noeud2, carteWagon,
							Integer.parseInt(e.getElementsByTagName("troncons").item(0).getTextContent()),
							e.getElementsByTagName("voieDouble").item(0).getTextContent().equals("true"),
							couleurVoieDouble,
							Integer.parseInt(e.getElementsByTagName("tronconsVoieDouble").item(0).getTextContent()));
					this.alAretes.add(arete);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Convertit un fichier en une chaîne de caractères encodée en base 64.
	 * 
	 * @param file Le fichier à convertir.
	 * @return La chaîne de caractères encodée en base 64. Si une erreur survient,
	 *         retourne null.
	 */
	public static String fileToString(File file) {
		try {
			FileInputStream r = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			r.read(bytes);
			r.close();
			return Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Convertit une chaîne de caractères encodée en base 64 en un fichier.
	 * 
	 * @param encodedString chaîne de caractères encodée en base 64.
	 * @param file          le fichier à créer.
	 * @return Le fichier créé. Si une erreur survient, retourne null.
	 */
	public static File stringToFile(String encodedString, File file) {
		try {
			byte[] bytes = Base64.getDecoder().decode(encodedString);
			file.createNewFile();
			FileOutputStream w = new FileOutputStream(file);
			w.write(bytes);
			w.close();
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}