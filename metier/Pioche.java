package metier;

import java.util.ArrayList;

public class Pioche {
	
	private ArrayList<CarteWagon> alCarteWagon; 
	private ArrayList<CarteWagon> alCarteWagonDefausse; 
	private ArrayList<CarteDestination> alCarteDestination; 
	private ArrayList<CarteDestination> alCarteDestinationDefausse;

	public Pioche() {
		this.alCarteWagon = new ArrayList<CarteWagon>();
		this.alCarteWagonDefausse = new ArrayList<CarteWagon>();
		this.alCarteDestination = new ArrayList<CarteDestination>();
		this.alCarteDestinationDefausse = new ArrayList<CarteDestination>();
	}

	public void addCarteWagon(CarteWagon carteWagon)                           { this.alCarteWagon.add(carteWagon);                     }
	public void addCarteDestination(CarteDestination carteDestination)         { this.alCarteDestination.add(carteDestination);         }
	public void addCarteWagonDefausse(CarteWagon carteWagon)                   { this.alCarteWagonDefausse.add(carteWagon);             }
	public void addCarteDestinationDefausse(CarteDestination carteDestination) { this.alCarteDestinationDefausse.add(carteDestination); }

	public void removeCarteWagon(CarteWagon carteWagon)                           { this.alCarteWagon.remove(carteWagon);                     }
	public void removeCarteDestination(CarteDestination carteDestination)         { this.alCarteDestination.remove(carteDestination);         }
	public void removeCarteWagonDefausse(CarteWagon carteWagon)                   { this.alCarteWagonDefausse.remove(carteWagon);             }
	public void removeCarteDestinationDefausse(CarteDestination carteDestination) { this.alCarteDestinationDefausse.remove(carteDestination); }

	public void melangerCarteDestination() 
	{ 
		ArrayList<CarteDestination> alCarteDestinationTemp = new ArrayList<CarteDestination>();
		for (CarteDestination carteDestination : this.alCarteDestination) {
			alCarteDestinationTemp.add(carteDestination);
		}
			
		this.alCarteDestination.clear();

		while (alCarteDestinationTemp.size() > 0) {
			int i = (int) (Math.random() * alCarteDestinationTemp.size());
			this.alCarteDestination.add(alCarteDestinationTemp.get(i));
			alCarteDestinationTemp.remove(i);
		}
	}
}
