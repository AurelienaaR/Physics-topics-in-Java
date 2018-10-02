package model;

public class IdTitle {

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param title
	 */

	int id;
	String title;

	public IdTitle(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
