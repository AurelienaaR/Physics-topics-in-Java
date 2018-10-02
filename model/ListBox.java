package model;

public class ListBox {

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param elt
	 * @param title
	 * @param content
	 */

	int id;
	int elt;
	String title;
	String content;

	public ListBox(int id, int elt, String title, String content) {
		this.id = id;
		this.elt = elt;
		this.title = title;
		this.content = content;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getElt() {
		return this.elt;
	}

	public void setElt(int elt) {
		this.elt = elt;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
