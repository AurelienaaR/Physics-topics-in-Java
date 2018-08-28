package model;

import java.time.LocalDate;
import java.sql.Array;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Variable {

	private final IntegerProperty id;
	private final StringProperty title;
	private final StringProperty domain;
	private final ObjectProperty<Array> type;
	private final StringProperty characteristics;
	private final IntegerProperty dim;
	private final StringProperty extensivity;
	private final StringProperty continuity;
	private final StringProperty content;
	private final IntegerProperty idLevelInt;
	private final ObjectProperty<LocalDate> saved;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param title
	 * @param domain
	 * @param type
	 * @param characteristics
	 * @param dim
	 * @param extensivity
	 * @param continuity
	 * @param content
	 * @param idLevelInt
	 * @param saved
	 */

	public Variable(int id, String title, String domain, Array type, String characteristics, int dim,
			String extensivity, String continuity, String content, int idLevelInt, LocalDate saved) {
		this.id = new SimpleIntegerProperty(id);
		this.title = new SimpleStringProperty(title);
		this.domain = new SimpleStringProperty(domain);
		this.type = new SimpleObjectProperty<Array>(type);
		this.characteristics = new SimpleStringProperty(characteristics);
		this.dim = new SimpleIntegerProperty(dim);
		this.extensivity = new SimpleStringProperty(extensivity);
		this.continuity = new SimpleStringProperty(continuity);
		this.content = new SimpleStringProperty(content);
		this.idLevelInt = new SimpleIntegerProperty(idLevelInt);
		this.saved = new SimpleObjectProperty<LocalDate>(saved);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
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

	public String getDomain() {
		return domain.get();
	}

	public void setDomain(String domain) {
		this.domain.set(domain);
	}

	public StringProperty domainProperty() {
		return domain;
	}

	public Array getType() {
		return type.get();
	}

	public void setType(Array type) {
		this.type.set(type);
	}

	public ObjectProperty<Array> typeProperty() {
		return type;
	}

	public String getCharacteristics() {
		return characteristics.get();
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics.set(characteristics);
	}

	public StringProperty characteristicsProperty() {
		return characteristics;
	}

	public int getDim() {
		return dim.get();
	}

	public void setDim(int dim) {
		this.dim.set(dim);
	}

	public IntegerProperty dimProperty() {
		return dim;
	}

	public String getExtensivity() {
		return extensivity.get();
	}

	public void setExtensivity(String extensivity) {
		this.extensivity.set(extensivity);
	}

	public StringProperty extensivityProperty() {
		return extensivity;
	}

	public String getContinuity() {
		return extensivity.get();
	}

	public void setContinuity(String continuity) {
		this.continuity.set(continuity);
	}

	public StringProperty continuityProperty() {
		return continuity;
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

	public LocalDate getSaved() {
		return saved.get();
	}

	public void setSaved(LocalDate saved) {
		this.saved.set(saved);
	}

	public int getIdLevelInt() {
		return idLevelInt.get();
	}

	public void setIdLevelInt(int idLevelInt) {
		this.idLevelInt.set(idLevelInt);
	}

	public ObjectProperty<LocalDate> savedProperty() {
		return saved;
	}

	public String getListVar() {
		return content.get();
	}

	public void setListVar(String content) {
		this.content.set(content);
	}

}
