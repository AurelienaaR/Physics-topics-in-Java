package model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Variable {

	private final IntegerProperty id;
	private final IntegerProperty dim;
	private final IntegerProperty idExtensivity;
	private final IntegerProperty idContinuity;
	private final IntegerProperty idCharacteristics;
	private final StringProperty title;
	private final StringProperty content;
	private final IntegerProperty idLevel;
	private final ObjectProperty<ArrayList<Integer>> arrType;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param dim
	 * @param idExtensivity
	 * @param idContinuity
	 * @param idCharacteristics
	 * @param title
	 * @param content
	 * @param idLevel
	 * @param arrType
	 */

	public Variable(int id, int dim, int idExtensivity, int idContinuity, int idCharacteristics, String title,
			String content, int idLevel, ArrayList<Integer> arrType) {
		this.id = new SimpleIntegerProperty(id);
		this.dim = new SimpleIntegerProperty(dim);
		this.idExtensivity = new SimpleIntegerProperty(idExtensivity);
		this.idContinuity = new SimpleIntegerProperty(idContinuity);
		this.idCharacteristics = new SimpleIntegerProperty(idCharacteristics);
		this.title = new SimpleStringProperty(title);
		this.content = new SimpleStringProperty(content);
		this.idLevel = new SimpleIntegerProperty(idLevel);
		this.arrType = new SimpleObjectProperty<ArrayList<Integer>>(arrType);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
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

	public int getIdExtensivity() {
		return idExtensivity.get();
	}

	public void setExtensivity(int idExtensivity) {
		this.idExtensivity.set(idExtensivity);
	}

	public IntegerProperty idExtensivityProperty() {
		return idExtensivity;
	}

	public int getIdContinuity() {
		return idContinuity.get();
	}

	public void setContinuity(int idContinuity) {
		this.idContinuity.set(idContinuity);
	}

	public IntegerProperty idContinuityProperty() {
		return idContinuity;
	}

	public int getIdCharacteristics() {
		return idCharacteristics.get();
	}

	public void setCharacteristics(int idCharacteristics) {
		this.idCharacteristics.set(idCharacteristics);
	}

	public IntegerProperty idCharacteristicsProperty() {
		return idCharacteristics;
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

	public int getIdLevel() {
		return idLevel.get();
	}

	public void setIdLevel(int idLevel) {
		this.idLevel.set(idLevel);
	}

	public ArrayList<Integer> getArrType() {
		return arrType.get();
	}

	public void setType(ArrayList<Integer> arrType) {
		this.arrType.set(arrType);
	}

	public ObjectProperty<ArrayList<Integer>> typeProperty() {
		return arrType;
	}

}
