package ihm;

import application.Application;
import ihm.conception.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;


public class FrameConcepteur extends JFrame 
{
	private Application app;
	private PanelMappe panelMappe;

	public FrameConcepteur(Application app) 
	{
		this.setTitle("Concepteur");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-450,
										  (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-350));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2 - 50);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.app = app;
		this.setJMenuBar(new MenuBar(this));

		this.panelMappe = new PanelMappe(this.app, (int)this.getSize().getWidth(), (int)this.getSize().getHeight());

		this.add(this.panelMappe, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void setImgMappe(File imagePath) {this.panelMappe.setImg(imagePath); this.panelMappe.changeCliquable();}
}