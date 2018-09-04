package main;

import javax.swing.JDialog;

public class WindowDialInfo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String domain;
	private String type;
	private String dim;
	private String title;
	private String content;
	

	public WindowDialInfo() {
	}

	public WindowDialInfo(String domain, String type, String dim, String title, String content) {
		this.domain = domain;
		this.type = type;
		this.dim = dim;
		this.title = title;
		this.content = content;
	}

	public String toString() {
		String infoStr;
		if (this.title != null) {
			infoStr = "Description du thème : ";
			infoStr += "Domaine : " + this.domain + "\n";
			infoStr += "Type : " + this.type + "\n";
			infoStr += "Dimension : " + this.dim + "\n";
			infoStr += "Titre : " + this.title + "\n";
			infoStr += "Contenu : " + this.content + "\n";
		} else {
			infoStr = "Non enregistré";
		}
		return infoStr;
	}
}
