package models.descriptions;

import models.base.Bin;

public abstract class DescrBin extends Bin {

	String titleTopic = "";
	String contentTopic = "";
	String attrTitle = "Description du thème " + titleTopic;
	String attrContent = "Description du thème " + contentTopic;

	final int attrInt = 0;
	double attrDouble = 0.0;
	final boolean attrBool = true;

	public void fDescrVoid() {

	}

	public String fDescrString() {
		String fDescrReturn = "";
		return fDescrReturn;
	}

	public String fDescrExpInString() {
		String fDescrExpInReturn = "";
		return fDescrExpInReturn;
	}

	public String fDescrThString() {
		String fDescrExpThReturn = "";
		return fDescrExpThReturn;
	}

	public String fDescrExpOutString() {
		String fDescrExpOutReturn = "";
		return fDescrExpOutReturn;
	}

}
