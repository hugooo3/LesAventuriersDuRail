package dow.ihm;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
public class FrameParam extends JFrame implements ActionListener 
{

	private PanelMappe panelMappe;
	private PanelParam panelParam;


	public FrameParam(File imagePath) 
	{

		//Création de la frame 
		this.setTitle("Concepteur de mappe");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-450,
												(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-350));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2 - 50);

		this.panelMappe = new PanelMappe(imagePath);
		this.panelParam = new PanelParam();

		this.add(this.panelMappe, BorderLayout.WEST);
		this.add(this.panelParam, BorderLayout.EAST);

		// Options pour la fermeture/apparence de la Frame

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}