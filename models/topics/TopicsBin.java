package models.topics;

import java.util.ArrayList;

public abstract class TopicsBin {

	// static ArrayList<String> listRef = null;
	ArrayList<String> listRef;

	String titleTopic = "";
	String contentTopic = "";
	String attrTitle = "Description du th�me " + titleTopic;
	String attrContent = "Description du th�me " + contentTopic;

	final int attrInt = 0;
	double attrDouble = 0.0;
	final boolean attrBool = false;

	public void fTopicsVoid() {

	}

	public static String fTopicsString() {
		String fTopicsReturn = "";
		return fTopicsReturn;
	}

}
