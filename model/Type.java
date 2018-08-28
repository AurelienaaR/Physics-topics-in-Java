package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Type {

	private final IntegerProperty id;
	private final StringProperty content;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param content
	 */
	public Type(int id, String content) {
		this.id = new SimpleIntegerProperty(id);
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
