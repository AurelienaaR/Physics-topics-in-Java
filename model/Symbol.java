package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Symbol {

	private final IntegerProperty id;
	private final StringProperty title;
	private final StringProperty content;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param title
	 * @param content
	 */
	public Symbol(int id, String title, String content) {
		this.id = new SimpleIntegerProperty(id);
		this.title = new SimpleStringProperty(title);
		this.content = new SimpleStringProperty(content);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public StringProperty titleProperty() {
		return title;
	}

	public String getcontent() {
		return content.get();
	}

	public void setcontent(String content) {
		this.content.set(content);
	}

	public StringProperty contentProperty() {
		return content;
	}

}
