package model;

import java.util.Date;
import javafx.beans.property.*;
import javafx.collections.*;

public interface IProjectFX {
	SimpleStringProperty getIDProperty();
	void setID(SimpleStringProperty value);
	
	SimpleStringProperty getNameProperty();
	void setName(SimpleStringProperty value);
	
	ObjectProperty<Date> getModifiedProperty();
	void setModified(ObjectProperty<Date> value);
	
	ObjectProperty<Date> getCreatedProperty();
	void setCreated(ObjectProperty<Date> value);
	
	SimpleStringProperty getDescriptionProperty();
	void setDescription(SimpleStringProperty value);
	
	ObservableList<ITask> getTasksProperty();
	void setTasks(ObservableList<ITask> value);
	
	ObservableList<Integer> getMembersProperty();
	void setMembers(ObservableList<Integer> value);

}
