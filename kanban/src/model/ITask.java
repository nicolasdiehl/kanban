package model;

import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public interface ITask {
	
	String getTitleProperty();
	void setTitle(String value);
	
	String getDescribtionProperty();
	void setDescribtion(String value);
	
	String getCategorieProperty();
	void setCategorie(String value);
	
	String getStatusProperty();
	void setStatus(String value); 
	
	String getCreatorIDProperty();
	void setCreatorID(String value); 
	
	Date getLastCallProperty();
	void setLastCall(Date value); 
	
	Date getCreatorDateProperty();
	void setCreatorDate(Date value); 
	
	List<User> getMemberProperty();
	void setMember(List<User> value); 
	
	List<String> getCommentProperty();
	void setComment(List<String> value);
	
}
