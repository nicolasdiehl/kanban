package view;

import control.ClientControl;
import control.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import model.ITask;
import model.ITaskFX;
import model.Task;

public class MainScreenController {
    
    @FXML
    private TabPane projectTabPane;
    @FXML
    private ContextMenu rightClickContextMenu;
    @FXML
    private MenuItem newTaskContextMenuItem;
    @FXML
    private Tab projectTab;
    @FXML
    private GridPane kanbanGridPane;
    @FXML
    private TableView<ITask> todoTTV;
//    @FXML
//    private TableView<Wip> wipTTV;
//    @FXML
//    private TableView<Done> doneTTV;
    @FXML
    private TableColumn<ITask, String> todoTTC;
//    @FXML
//    private TableColumn<Wip, String> wipTTC;
//    @FXML
//    private TableColumn<Done, String> doneTTC;

//    @FXML
//    private 
    
    private ClientControl control = ClientControl.getInstance();
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new task.
     */
    @FXML
    private void handleNewTask() {
	Task tempTask = new Task();
	mainApp.showNewTaskDialog(tempTask);
//        boolean okClicked = mainApp.showNewTaskDialog(tempTask);
//        if (okClicked) {
//            mainApp.getTaskData().add(tempTask);
//        }
    }
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected task.
     */
    @FXML
    private void handleEditPerson() {
	ITaskFX selectedTask = todoTTV.getSelectionModel().getSelectedItem();
	if (selectedTask != null) {
	    boolean okClicked = mainApp.showNewTaskDialog(selectedTask);
	    if (okClicked) {
		showTaskDetails(selectedTask);
	    }
	    
	} else {
	    // Nothing selected.
	    Alert alert = new Alert(AlertType.WARNING);
	    alert.initOwner(mainApp.getPrimaryStage());
	    alert.setTitle("No Selection");
	    alert.setHeaderText("No task Selected");
	    alert.setContentText("Please select a task in the table.");
	    
	    alert.showAndWait();
	}
    }
    
    // Reference to the main application.
    private MainApp mainApp;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainScreenController() {
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
	// Initialize the task table with one column.
	todoTTC.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
	// Clear task details.
	showTaskDetails(null);
	
	// Listen for selection changes and show the task details when changed.
	todoTTV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTaskDetails(newValue));
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
	this.mainApp = mainApp;
	
//         Add observable list data to the table
	todoTTV.setItems((control.getOpenProject().getTasksProperty()));
    }
    
    /**
     * Fills all text fields to show details about the task.
     * If the specified task is null, all text fields are cleared.
     * 
     * @param task
     *            or null
     */
    private void showTaskDetails(ITaskFX task) {
//	
//        if (task != null) {
//            // Fill the labels with info from the person object.
//            firstNameLabel.setText(task.getTitle());
//            lastNameLabel.setText(person.getLastName());
//            streetLabel.setText(person.getStreet());
//            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
//            cityLabel.setText(person.getCity());
//
//            // TODO: We need a way to convert the birthday into a String! 
//            // birthdayLabel.setText(...);
//        } else {
//            // Person is null, remove all the text.
//            firstNameLabel.setText("");
//            lastNameLabel.setText("");
//            streetLabel.setText("");
//            postalCodeLabel.setText("");
//            cityLabel.setText("");
//            birthdayLabel.setText("");
//        }
    }
    
}
