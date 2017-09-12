package view;

import control.ClientControl;
import control.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.SimpleProject;

public class ProjectSelectionScreenController {
    
    @FXML
    private Label lb_greeting;
    @FXML
    private Button bt_accept;
    @FXML
    private TableView<SimpleProject> projectOverviewTable;
    @FXML
    private TableColumn<SimpleProject, String> projectNameColumn;
    
    private ClientControl clientControl = ClientControl.getInstance();
    private MainApp mainApp;
    
    ObservableList<SimpleProject> listOfProjectNames = FXCollections.observableArrayList(clientControl.getSimpleProjects());
    
    Stage prevStage;
    
    public ProjectSelectionScreenController() {
	
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
	this.clientControl = ClientControl.getInstance();
	lb_greeting.setText("Hallo " + mainApp.loginName);
//	projectNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    }
    
//    public ObservableList<SimpleProject> getProjectData() {
//	return listOfProjectNames;
//    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new project.
     */
    @FXML
    private void handleNewProject() {
	mainApp.showProjectInsertDialog();
    }
    
    /**
     * Called when the user clicks the accept button.
     * Opens the MainScreen.
     */
    @FXML
    private void handleAccept() {
	
//	listOfProjectNames.add(new SimpleProject("ich", "123"));
//	listOfProjectNames.add(new SimpleProject("du", "234"));
//	listOfProjectNames.add(new SimpleProject("er", "345"));
//	System.out.println(listOfProjectNames.get(0).getName());
	
	MainApp mainApp = new MainApp();
	
	mainApp.showMainScreen();
	
	prevStage = (Stage) bt_accept.getScene().getWindow();
	prevStage.close();
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
	this.mainApp = mainApp;
	
//      Add observable list data to the table
//	projectOverviewTable.setItems(getProjectData());
    }
    
}
