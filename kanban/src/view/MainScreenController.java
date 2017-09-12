package view;

import java.util.function.Consumer;

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
import javafx.scene.control.TextArea;
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
    private MenuItem editSelectedTaskContextMenuItem;
    @FXML
    private Tab projectTab;
    @FXML
    private GridPane kanbanGridPane;
    @FXML
    private TextArea creatorTextArea = new TextArea();
    @FXML
    private TextArea creationDateTextArea = new TextArea();
    @FXML
    private TextArea categoryTextArea = new TextArea();
    @FXML
    private TextArea memberTextArea = new TextArea();
    @FXML
    private TextArea descriptionTextArea = new TextArea();
    @FXML
    private TextArea commentTextArea = new TextArea();
    @FXML
    private TableView<ITask> todoTTV;
    @FXML
    private TableView<ITask> wipTTV;
    @FXML
    private TableView<ITask> doneTTV;
    @FXML
    private TableColumn<ITask, String> todoTTC;
    @FXML
    private TableColumn<ITask, String> wipTTC;
    @FXML
    private TableColumn<ITask, String> doneTTC;

    
    private ClientControl control = ClientControl.getInstance();
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new task.
     */
    @FXML
    private void handleNewTask() {
	mainApp.showNewTaskDialog();
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
	ITask selectedTask = todoTTV.getSelectionModel().getSelectedItem();
	if (selectedTask != null) {
	    boolean okClicked = mainApp.showEditTaskDialog(selectedTask, "edit");
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
    private void showTaskDetails(ITask task) {
	
        creatorTextArea.setText("");
        creationDateTextArea.setText("");
        categoryTextArea.setText("");
        memberTextArea.setText("");
        descriptionTextArea.setText("");
        commentTextArea.setText("");
        
        if (task != null) {
            // Fill the labels with info from the person object.
            creatorTextArea.setText(task.getCreatorID());
            creationDateTextArea.setText(task.getCreatorDate().toLocaleString());
            categoryTextArea.setText(task.getCategorie());
//            memberTextArea.setText(task.getMember().toString());
            descriptionTextArea.setText(task.getDescription());
            for (String element : task.getComment()) {
		commentTextArea.appendText("- " + element + ";\n");
	    }
//            commentTextArea.setText(task.getComment().toString());

        } else {
            // Task is null, remove all the text.
            creatorTextArea.setText("");
            creationDateTextArea.setText("");
            categoryTextArea.setText("");
            memberTextArea.setText("");
            descriptionTextArea.setText("");
            commentTextArea.setText("");
        }
    }
    
}
