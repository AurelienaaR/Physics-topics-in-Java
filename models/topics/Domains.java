package models.topics;

import java.util.ArrayList;

public class Domains extends TopicsBin {

	ArrayList<String> listRef = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{

		}
	};

	public void fTopicsVoid(String fg) {
		listRef.add(fg);
	}

	public static String fTopicsString() {
		String fTopicsReturn = "SELECT title FROM topic WHERE iddomain = ";
		return fTopicsReturn;
	}

}
