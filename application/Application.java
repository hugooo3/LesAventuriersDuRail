package application;

import ihm.FrameManager;
import metier.Metier;

public class Application {
	Metier metier;
	FrameManager frameManager;

	public Application() {
		this.metier = new Metier(this);
		this.frameManager = new FrameManager(this);
	}
}
