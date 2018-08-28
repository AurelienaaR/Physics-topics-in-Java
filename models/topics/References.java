package models.topics;

import java.util.ArrayList;

public class References extends TopicsBin {

	ArrayList<String> listRef = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{

		}
	};

	public void fTopicsVoid(String fg) {
		listRef.add(fg);
	}

	public static String fTopicsString() {
		String fTopicsReturn = "SELECT * FROM topic WHERE iddomain = 1;";
		return fTopicsReturn;
	}

}
