package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Domain {

	private final IntegerProperty id;
	private final IntegerProperty idcadre;
	private final IntegerProperty idtype;
	private final StringProperty title;
	private final StringProperty content;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param idcadre
	 * @param idtype
	 * @param title
	 * @param content
	 */
	public Domain(int id, int idcadre, int idtype, String title, String content) {
		this.id = new SimpleIntegerProperty(id);
		this.idcadre = new SimpleIntegerProperty(idcadre);
		this.idtype = new SimpleIntegerProperty(idtype);
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

	public int getIdCadre() {
		return idcadre.get();
	}

	public void setIdCadre(int idcadre) {
		this.idcadre.set(idcadre);
	}

	public IntegerProperty idcadreProperty() {
		return idcadre;
	}

	public int getIdType() {
		return idtype.get();
	}

	public void setIdType(int idtype) {
		this.idtype.set(idtype);
	}

	public IntegerProperty idtypeProperty() {
		return idtype;
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

	public String getContent() {
		return content.get();
	}

	public void setContent(String content) {
		this.content.set(content);
	}

	public StringProperty contentProperty() {
		return content;
	}

}
