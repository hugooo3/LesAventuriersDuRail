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

		if (r == 0)
		{
			System.out.println("Pioche vide, on remet la defausse dans la pioche");
			this.defausseVersPiocheWagon();
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

	public void defausseVersPiocheWagon() 
	{
		for (CarteWagon carteWagon : this.alCarteWagonDefausse)
		{
			carteWagon.setNbCarteWagon(0);
		}

		for (CarteWagon carteWagon : this.alCarteWagon)
		{
			carteWagon.setNbCarteWagon(12);
		}
	}

	public boolean defausserCarteWagon (CarteWagon carteWagon, int nb) {
		int index = this.alCarteWagon.indexOf(carteWagon);
		
		this.alCarteDestination.get(index);
		/*à suivre je mange*/
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