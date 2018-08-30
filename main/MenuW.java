package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuW extends JMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final JMenuBar MenuGlobal() {
		final JMenuBar menuBar = new JMenuBar();
		final JMenu menuProject = new JMenu("Projet");
		final JMenu menuEdition = new JMenu("Edition");
		final String[] subItemProjectLabel = { "Nouveau", "Ouvrir", "Enregistrer", "Quitter" };
		final String[] subMenuEditionLabel = { "Domaine", "Thème", "Variable", "Type", "Caractéristiques",
				"Symbolisation" };
		final String[] subItemEditionLabel = { "Création", "Modification", "Suppression" };
		final ArrayList<JMenuItem> subItemProject = new ArrayList<JMenuItem>();
		final ArrayList<JMenu> subMenuEdition = new ArrayList<JMenu>();
		final ArrayList<JMenuItem> subItemEdition = new ArrayList<JMenuItem>();

		for (int jPr = 0; jPr < subItemProjectLabel.length; jPr++) {
			subItemProject.add(new JMenuItem(subItemProjectLabel[jPr]));
			menuProject.add(subItemProject.get(jPr));
			switch(jPr) {
			case 0 : 
				break;
			case 1 :
				break;
			case 2 : 
				break;
			case 3 : 
				subItemProject.get(jPr).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				break;
			default : 
				break;
			}
		}
		for (int iEd = 0; iEd < subMenuEditionLabel.length; iEd++) {
			subMenuEdition.add(new JMenu(subMenuEditionLabel[iEd]));
			for (int jIEd = 0; jIEd < subItemEditionLabel.length; jIEd++) {
				subItemEdition.add(new JMenuItem(subItemEditionLabel[jIEd]));
			}
		}
		for (int ixEd = 0; ixEd < subMenuEditionLabel.length; ixEd++) {
			for (int jSIEd = 0; jSIEd < subItemEditionLabel.length; jSIEd++) {
				subMenuEdition.get(ixEd).add(subItemEdition.get(3 * ixEd + jSIEd));
				switch(ixEd) {
				case 0 : 
					break;
				case 1 :
					break;
				case 2 : 
					break;
				case 3 : 
					break;
				case 4 :
					break;
				case 5 : 
					break;
				default : 
					break;
				}
			}
			menuEdition.add(subMenuEdition.get(ixEd));
		}
		menuBar.add(menuProject);
		menuBar.add(menuEdition);
		return menuBar;
	}
}
