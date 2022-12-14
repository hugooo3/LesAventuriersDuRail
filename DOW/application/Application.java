package application;

import ihm.PanelManager;
import metier.Metier;

public class Application {
	Metier metier;
	PanelManager panelManager;

	public Application() {
		this.metier       = new Metier( this );
		this.panelManager = new PanelManager();
	}
}
