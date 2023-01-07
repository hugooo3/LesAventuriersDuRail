package ihm.jeu;

import ihm.FrameJeu;

import javax.swing.*;

import java.awt.event.*;

public class MenuBarJeu extends JMenuBar implements ActionListener {
	private FrameJeu jeu;

	private JMenuItem menuiFichierQuitter;

	public MenuBarJeu(FrameJeu concepteur) {
		this.jeu = concepteur;

		// Creation des composants
		JMenu menuFichier = new JMenu("Fichier");
		menuFichier.setMnemonic(KeyEvent.VK_F);

		this.menuiFichierQuitter = new JMenuItem("Quitter");
		this.menuiFichierQuitter.setMnemonic(KeyEvent.VK_F4);

		// Positionnement des composants
		menuFichier.add(this.menuiFichierQuitter);
		this.add(menuFichier);

		// Integration des raccourcis
		this.menuiFichierQuitter.setAccelerator( // CTRL+F4
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_DOWN_MASK));

		this.menuiFichierQuitter.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.menuiFichierQuitter)
			this.jeu.quitter();
	}
}
