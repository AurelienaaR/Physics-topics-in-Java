package models.studies;

public class Symbols extends StudBin {

	String titleTopic;
	String contentTopic;
	String attrTitle = "Description du th�me " + titleTopic;
	String attrContent = "Description du th�me " + contentTopic;

	int attrInt;
	double attrDouble;
	boolean attrBool;

	public void fStudVoid() {

	}

	public String fStudString() {
		String fStudReturn = "SELECT * FROM topics WHERE idlevel = 5;";
		return fStudReturn;
	}

}
