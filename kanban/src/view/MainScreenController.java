package view;

import control.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
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
