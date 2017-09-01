package view;

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
import model.Task;
import model.ITask;
import model.ITaskFX;

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
    private TableView<ITaskFX> todoTTV;
//    @FXML
//    private TableView<Wip> wipTTV;
//    @FXML
//    private TableView<Done> doneTTV;
    @FXML
    private TableColumn<ITaskFX, String> todoTTC;
//    @FXML
//    private TableColumn<Wip, String> wipTTC;
//    @FXML
//    private TableColumn<Done, String> doneTTC;

//    @FXML
//    private 
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
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
     * details for the selected person.
     */
//    @FXML
//    private void handleEditPerson() {
//        Task selectedTask = todoTTV.getSelectionModel().getSelectedItem();
//        if (selectedTask != null) {
//            boolean okClicked = mainApp.showTaskEditDialog(selectedTask);
//            if (okClicked) {
//                showPersonDetails(selectedTask);
//            }
//
//        } else {
//            // Nothing selected.
//            Alert alert = new Alert(AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Person Selected");
//            alert.setContentText("Please select a person in the table.");
//
//            alert.showAndWait();
//        }
//    }
    
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
        // Initialize the person table with the two columns.
        todoTTC.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

//         Add observable list data to the table
        todoTTV.setItems((mainApp.getTaskData()));
    }
    
}
