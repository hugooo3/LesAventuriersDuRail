package fr.ecareus.dow;

import fr.ecareus.dow.ihm.PanelManager;
import fr.ecareus.dow.metier.Metier;

public class Application {
	Metier metier;
	PanelManager panelManager;

	public Application() {
		this.metier       = new Metier( this );
		this.panelManager = new PanelManager();
	}
}
