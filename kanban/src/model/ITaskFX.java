package model;

import java.util.Date;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public interface ITaskFX {
	
	String getTitleProperty();
	void setTitle(SimpleStringProperty value);
	
	String getDescribtionProperty();
	void setDescribtion(SimpleStringProperty value);
	
	String getCategorieProperty();
	void setCategorie(SimpleStringProperty value);
	
	String getStatusProperty();
	void setStatus(SimpleStringProperty value); 
	
	String getCreatorIDProperty();
	void setCreatorID(SimpleStringProperty value); 
	
	Date getLastCallProperty();
	void setLastCall(ObjectProperty<Date> value); 
	
	Date getCreatorDateProperty();
	void setCreatorDate(ObjectProperty<Date> value); 
	
	ObservableList<User> getMemberProperty();
	void setMember(ObservableList<User> value); 
	
	ObservableList<String> getCommentProperty();
	void setComment(ObservableList<String> value);

}
