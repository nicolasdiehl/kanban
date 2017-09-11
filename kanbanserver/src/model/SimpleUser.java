package model;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

public class SimpleUser implements Serializable{
	private static final long serialVersionUID = -2398503995120490637L;
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public SimpleStringProperty getFirstNameProperty() {
		return new SimpleStringProperty(firstName);
	}

	public SimpleStringProperty getLastNameProperty() {
		return new SimpleStringProperty(lastName);
	}
}
