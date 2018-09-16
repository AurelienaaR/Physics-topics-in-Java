package model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Topic {

	private final IntegerProperty id;
	private final IntegerProperty iddomain;
	private final StringProperty title;
	private final ObjectProperty<ArrayList<Integer>> arrvar;
	private final StringProperty content;
	private final IntegerProperty idtype;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param iddomain
	 * @param title
	 * @param arrvar
	 * @param content
	 * @param idtype
	 * 
	 */

	public Topic(int id, int iddomain, String title, ArrayList<Integer> arrvar, String content, int idtype) {
		this.id = new SimpleIntegerProperty(id);
		this.iddomain = new SimpleIntegerProperty(iddomain);
		this.title = new SimpleStringProperty(title);
		this.arrvar = new SimpleObjectProperty<ArrayList<Integer>>();
		this.content = new SimpleStringProperty(content);
		this.idtype = new SimpleIntegerProperty(idtype);
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

	public int getIddomain() {
		return iddomain.get();
	}

	public void setIddomain(int iddomain) {
		this.iddomain.set(iddomain);
	}

	public IntegerProperty iddomainProperty() {
		return iddomain;
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

	public ArrayList<Integer> getArrVar() {
		return arrvar.get();
	}

	public void setArrVar(ArrayList<Integer> arrvar) {
		this.arrvar.set(arrvar);
	}

	public ObjectProperty<ArrayList<Integer>> arrvarProperty() {
		return arrvar;
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

	public int getIdType() {
		return idtype.get();
	}

	public void setIdType(int idtype) {
		this.idtype.set(idtype);
	}

	public IntegerProperty idtypeProperty() {
		return idtype;
	}

}
