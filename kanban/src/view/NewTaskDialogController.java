package view;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import control.ClientControl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.IProject;
import model.ITask;
import model.Task;

public class NewTaskDialogController {
    
    @FXML
    private TextField titleTextField;
    @FXML
    private TextArea descriptionTextField = new TextArea();
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private TextArea commentTextField = new TextArea();
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    
    private Stage dialogStage;
    private ITask task;
    private boolean okClicked = false;
    private ClientControl control = ClientControl.getInstance();
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
	statusComboBox.getItems().addAll("ToDo", "Work in Progress", "Done");
	try {
	    statusComboBox.setValue(task.getStatus());
	} catch (Exception e) {
	    statusComboBox.setValue("ToDo");
	}
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
	this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the task to be edited in the dialog.
     * 
     * @param task
     */
    public void setTask(ITask task) {
	this.task = task;
	
	titleTextField.setText(task.getTitle());
	descriptionTextField.setText(task.getDescription());
//        categoryComboBox.setItems(task.getCategorie());
	commentTextField.setText(task.getComment().toString());
	statusComboBox.getItems();
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
	return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
	if (isInputValid() && dialogStage.getTitle().equals("New Task")) {
	    ITask task = new Task();
	    Date date = new Date();
	    List<String> items = Arrays.asList(commentTextField.getText().split("\\s*\\n\\s*"));
	    
	    task.setCreatorDate(date);
	    task.setCreatorID("");
	    task.setTitle(titleTextField.getText());
	    task.setDescription(descriptionTextField.getText());
	    task.setCategorie(statusComboBox.getSelectionModel().getSelectedItem());
	    task.setComment(items);
	    task.setStatus(statusComboBox.getSelectionModel().getSelectedItem());
	    
	    IProject project = control.getOpenProject();
	    project.addTask(task);
	    System.out.println(task.getStatus() + "hello");
	    
	    okClicked = true;
	    Stage prevStage;
	    prevStage = (Stage) okButton.getScene().getWindow();
	    prevStage.close();
	} else if (isInputValid() && dialogStage.getTitle().equals("Edit Task")) {
	    Date date = new Date();
	    List<String> items = Arrays.asList(commentTextField.getText().split("\\s*\\n\\s*"));
	    
	    task.setCreatorDate(date);
	    task.setCreatorID("");
	    task.setTitle(titleTextField.getText());
	    task.setDescription(descriptionTextField.getText());
	    task.setCategorie(statusComboBox.getSelectionModel().getSelectedItem());
	    task.setComment(items);
	    task.setStatus(statusComboBox.getSelectionModel().getSelectedItem());
	    okClicked = true;
	    Stage prevStage;
	    prevStage = (Stage) okButton.getScene().getWindow();
	    prevStage.close();
	    
	}
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
	Stage prevStage;
	prevStage = (Stage) cancelButton.getScene().getWindow();
	prevStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
	String errorMessage = "";
	
	if (titleTextField.getText() == null || titleTextField.getText().length() == 0) {
	    errorMessage += "Bitte einen Titel eingeben!\n";
	}
	if (descriptionTextField.getText() == null || descriptionTextField.getText().length() == 0) {
	    errorMessage += "Bitte eine Beschreibung eingeben!\n";
	}
	
	if (errorMessage.length() == 0) {
	    return true;
	} else {
	    // Show the error message.
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.initOwner(dialogStage);
	    alert.setTitle("Invalid Fields");
	    alert.setHeaderText("Please correct invalid fields");
	    alert.setContentText(errorMessage);
	    
	    alert.showAndWait();
	    
	    return false;
	}
    }
}
