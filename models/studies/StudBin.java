package models.studies;

import models.base.Bin;

public abstract class StudBin extends Bin {

	String titleTopic = "";
	String contentTopic = "";
	String attrTitle = "Description du thème " + titleTopic;
	String attrContent = "Description du thème " + contentTopic;

	final int attrInt = 0;
	double attrDouble = 0.0;
	final boolean attrBool = false;

	public void fStudVoid() {

	}

	public String fStudString() {
		String fStudReturn = "";
		return fStudReturn;
	}

	public String fStudExpInString() {
		String fStudExpInReturn = "";
		return fStudExpInReturn;
	}

	public String fStudThString() {
		String fStudExpThReturn = "";
		return fStudExpThReturn;
	}

	public String fStudExpOutString() {
		String fStudExpOutReturn = "";
		return fStudExpOutReturn;
	}

}
