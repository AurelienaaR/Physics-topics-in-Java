package models.descriptions;

public class Systems extends DescrBin {

	String titleTopic;
	String contentTopic;
	String attrTitle = "Description du th�me " + titleTopic;
	String attrContent = "Description du th�me " + contentTopic;

	int attrInt;
	double attrDouble;
	boolean attrBool;

	public void fDescrVoid() {

	}

	public String fDescrString() {
		String fDescrReturn = "SELECT * FROM variables WHERE idlevel = 2;";
		return fDescrReturn;
	}
}
