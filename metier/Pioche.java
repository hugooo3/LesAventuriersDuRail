package metier;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
	
	private ArrayList<CarteWagon> alCarteWagon; 
	private ArrayList<CarteWagon> alCarteWagonDefausse; 
	private ArrayList<CarteDestination> alCarteDestination; 

	public Pioche(ArrayList<CarteWagon> alCarteWagon, ArrayList<CarteDestination> alCarteDestination) {
		this.alCarteWagon = alCarteWagon;
		this.alCarteWagonDefausse = new ArrayList<CarteWagon>();
		this.alCarteDestination = alCarteDestination;

		this.alCarteWagonDefausse.add(new CarteWagon("Neutre", null, "" , "", 0));
		this.alCarteWagonDefausse.add(new CarteWagon("Blanc" , null, ""	, "", 0));
		this.alCarteWagonDefausse.add(new CarteWagon("Bleu"  , null, ""	, "", 0));
		this.alCarteWagonDefausse.add(new CarteWagon("Jaune" , null, ""	, "", 0));
		this.alCarteWagonDefausse.add(new CarteWagon("Noir"  , null, ""	, "", 0));
		this.alCarteWagonDefausse.add(new CarteWagon("Orange", null, ""	, "", 0));
		this.alCarteWagonDefausse.add(new CarteWagon("Rouge" , null, ""	, "", 0));
		this.alCarteWagonDefausse.add(new CarteWagon("Verte" , null, ""	, "", 0));
		this.alCarteWagonDefausse.add(new CarteWagon("Violet", null, ""	, "", 0));
		this.alCarteWagonDefausse.add(new CarteWagon("Joker" , null, ""	, "", 0));
	}

	public CarteWagon piocherCarteWagon () {
		int r = 0;

		for(CarteWagon carteWagon : this.alCarteWagon)
		{
			r += carteWagon.getNbCarteWagon();
		}
		System.out.println("Nombre de carte dans la pioche : " + r);
		if (r < 6)
		{
			System.out.println("Pioche vide, on remet la defausse dans la pioche");
			if (!this.defausseVersPiocheWagon())
				return null;
		}

		// Verification qu'il reste des cartes de cette couleur
		do {
			r = (int) (Math.random() * this.alCarteWagon.size() - 1) + 1;
		} while (!this.alCarteWagon.get(r).removeNbCarteWagon(1));

		if(this.alCarteWagon.size() > 0)
			return(this.alCarteWagon.get(r));

		return null;
	}

	public CarteDestination piocherCarteDestination () {
		CarteDestination carteDestination = null;

		if (this.alCarteDestination.size() > 0 )
		{
			carteDestination = this.alCarteDestination.get(0);
			this.removeCarteDestination(carteDestination);
		}
		return carteDestination;
	}

	public boolean defausseVersPiocheWagon() 
	{
		//Verification si la defausse est vide
		int r = 0;

		for(CarteWagon carteWagonDefausse : this.alCarteWagonDefausse)
		{
			r += carteWagonDefausse.getNbCarteWagon();
		}
		if (r == 0)
		{
			System.out.println("Pioche vide et defausse vide");
			return false;
		}

		//Transfert defausse vers pioche
		for (CarteWagon carteWagon : this.alCarteWagon)
		{
			int index = this.alCarteWagon.indexOf(carteWagon);
			
			carteWagon.setNbCarteWagon(this.alCarteWagonDefausse.get(index).getNbCarteWagon());
			this.alCarteWagonDefausse.get(this.alCarteWagonDefausse.indexOf(index)).setNbCarteWagon(0);
		}
		return true;
	}

	public boolean defausserCarteWagon (CarteWagon carteWagon, int indice) {
		int index = this.alCarteWagon.indexOf(carteWagon);

		//On retire la carte de la pioche
		int nb = this.alCarteWagon.get(index).getNbCarteWagon() - indice;
		this.alCarteWagon.get(index).setNbCarteWagon(nb);

		//On ajoute la carte à la defausse
		nb = this.alCarteWagonDefausse.get(index).getNbCarteWagon() + indice;
		this.alCarteWagonDefausse.get(index).setNbCarteWagon(nb);
		
		System.out.println("Carte défaussée : " + carteWagon.getNomCouleur() + ", nb : " + indice);
		return true;
	}

	public void addCarteWagon(CarteWagon carteWagon)                           { this.alCarteWagon.get(this.alCarteWagon.indexOf(carteWagon)).addNbCarteWagon(1); }
	public void addCarteWagonDefausse(CarteWagon carteWagon)                   { this.alCarteWagonDefausse.get(this.alCarteWagonDefausse.indexOf(carteWagon)).addNbCarteWagon(1); }
	public void addCarteDestination(CarteDestination carteDestination)         { this.alCarteDestination.add(carteDestination);         }

	public void removeCarteWagon(CarteWagon carteWagon)                           { this.alCarteWagon.get(this.alCarteWagon.indexOf(carteWagon)).removeNbCarteWagon(1); }
	public void removeCarteWagonDefausse(CarteWagon carteWagon)               	  { this.alCarteWagonDefausse.get(this.alCarteWagonDefausse.indexOf(carteWagon)).removeNbCarteWagon(1); }
	public void removeCarteDestination(CarteDestination carteDestination)         { this.alCarteDestination.remove(carteDestination);         }

	public int sizeCarteWagon()       { return this.alCarteWagon.size();       }
	public int sizeCarteDestination() { return this.alCarteDestination.size(); }

	public void melangerCarteDestination() {Collections.shuffle(alCarteDestination);}

}