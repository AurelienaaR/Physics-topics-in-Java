package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Level {

	private final IntegerProperty id;
	private final StringProperty levelk;
	private final StringProperty title;
	private final StringProperty content;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param levelk
	 * @param title
	 * @param content
	 */
	public Level(int id, String levelk, String title, String content) {
		this.id = new SimpleIntegerProperty(id);
		this.levelk = new SimpleStringProperty(levelk);
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

	public String getLevelk() {
		return levelk.get();
	}

	public void setLevelk(String levelk) {
		this.levelk.set(levelk);
	}

	public StringProperty levelkProperty() {
		return levelk;
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
