package metier;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
	
	private ArrayList<CarteWagon> alCarteWagon; 
	private ArrayList<CarteWagon> alCarteWagonDefausse; 
	private ArrayList<CarteDestination> alCarteDestination; 
	private ArrayList<CarteDestination> alCarteDestinationDefausse;

	public Pioche(ArrayList<CarteWagon> alCarteWagon, ArrayList<CarteDestination> alCarteDestination) {
		this.alCarteWagon = alCarteWagon;
		this.alCarteWagonDefausse = new ArrayList<CarteWagon>();
		this.alCarteDestination = alCarteDestination;
		this.alCarteDestinationDefausse = new ArrayList<CarteDestination>();
	}

	public CarteWagon piocherCarteWagon () {
		    int r = 0;

			// Verification qu'il reste des cartes de cette couleur
			do {
				r = (int) (Math.random() * this.alCarteWagon.size() - 1) + 1;
			} while (!this.alCarteWagon.get(r).removeNbCarteWagon(1));

			return(this.alCarteWagon.get(r));
	}

	public CarteDestination piocherCarteDestination () {
			CarteDestination carteDestination = this.getCarteDestination(0);

			this.removeCarteDestination(carteDestination);
			return carteDestination;
	}

	public CarteDestination getCarteDestination (int i) { return this.alCarteDestination.get(i); };

	public void addCarteWagon(CarteWagon carteWagon)                           { this.alCarteWagon.add(carteWagon);                     }
	public void addCarteDestination(CarteDestination carteDestination)         { this.alCarteDestination.add(carteDestination);         }
	public void addCarteWagonDefausse(CarteWagon carteWagon)                   { this.alCarteWagonDefausse.add(carteWagon);             }
	public void addCarteDestinationDefausse(CarteDestination carteDestination) { this.alCarteDestinationDefausse.add(carteDestination); }

	public void removeCarteWagon(CarteWagon carteWagon)                           { this.alCarteWagon.remove(carteWagon);                     }
	public void removeCarteDestination(CarteDestination carteDestination)         { this.alCarteDestination.remove(carteDestination);         }
	public void removeCarteWagonDefausse(CarteWagon carteWagon)                   { this.alCarteWagonDefausse.remove(carteWagon);             }
	public void removeCarteDestinationDefausse(CarteDestination carteDestination) { this.alCarteDestinationDefausse.remove(carteDestination); }

	public int sizeCarteWagon()       { return this.alCarteWagon.size();       }
	public int sizeCarteDestination() { return this.alCarteDestination.size(); }

	public void melangerCarteDestination() {Collections.shuffle(alCarteDestination);}

}
