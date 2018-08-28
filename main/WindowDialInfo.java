package main;

import javax.swing.JDialog;

public class WindowDialInfo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String dim;
	private String title;

	public WindowDialInfo() {
	}

	public WindowDialInfo(String type, String dim, String title) {
		this.type = type;
		this.dim = dim;
		this.title = title;
	}

	public String toString() {
		String infoStr;
		if (this.title != null) {
			infoStr = "Description du thème : ";
			infoStr += "Type : " + this.type + "\n";
			infoStr += "Dimension : " + this.dim + "\n";
			infoStr += "Titre : " + this.title + "\n";
		} else {
			infoStr = "Non enregistré";
		}
		return infoStr;
	}
}
