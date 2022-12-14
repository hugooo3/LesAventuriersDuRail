import metier.*;
import ihm.*;

public class Controleur {
	Metier metier;
	FrameManager frameManager;

	public static void main(String[] a) 
	{
		this.metier       = new Metier( this );
		this.frameManager = new FrameManager();
	}
}