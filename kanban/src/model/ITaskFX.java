package model;

import java.util.Date;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public interface ITaskFX {
	
	SimpleStringProperty getTitleProperty();
	void setTitle(SimpleStringProperty value);
	
	SimpleStringProperty getDescribtionProperty();
	void setDescribtion(SimpleStringProperty value);
	
	SimpleStringProperty getCategorieProperty();
	void setCategorie(SimpleStringProperty value);
	
	SimpleStringProperty getStatusProperty();
	void setStatus(SimpleStringProperty value); 
	
	SimpleStringProperty getCreatorIDProperty();
	void setCreatorID(SimpleStringProperty value); 
	
	ObjectProperty<Date> getLastCallProperty();
	void setLastCall(ObjectProperty<Date> value); 
	
	ObjectProperty<Date> getCreatorDateProperty();
	void setCreatorDate(ObjectProperty<Date> value); 
	
	ObservableList<User> getMemberProperty();
	void setMember(ObservableList<User> value); 
	
	ObservableList<String> getCommentProperty();
	void setComment(ObservableList<String> value);

}
