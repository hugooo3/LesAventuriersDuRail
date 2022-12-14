package DOW;

import metier.*;
import ihm.*;

public class Controleur {
	Metier metier;
	FrameManager frameManager;

	public Controleur () {
		this.metier       = new Metier( this );
		this.frameManager = new FrameManager();
	}
	public static void main(String[] a) 
	{
		new Controleur();
	}
}