package models.descriptions;

public class Environnement extends DescrBin {

	String titleTopic;
	String contentTopic;
	String attrTitle = "Description du thème " + titleTopic;
	String attrContent = "Description du thème " + contentTopic;

	int attrInt;
	double attrDouble;
	boolean attrBool;

	public void fDescrVoid() {

	}

	public String fDescrString() {
		String fDescrReturn = "SELECT * FROM variables WHERE idlevel = 1;";
		return fDescrReturn;
	}

}
