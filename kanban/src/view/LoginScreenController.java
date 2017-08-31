package view;

import control.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.ITaskFX;

public class LoginScreenController {

		
	@FXML
    private BorderPane loginBorderPane;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private Label passwortLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField passwortTextfield;

    @FXML
    private TextField usernameTextfield;

    @FXML
    private Button loginButton;

    // Reference to the main application.
    private  MainApp mainApp;
  
    Stage prevStage;
    

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginScreenController() {
    	
    }

    public void setPrevStage(Stage stage){
        this.prevStage = stage;
   }
  
    @FXML
    public void dropLoginButton() {
    	System.out.println("läuft");
    	
    	

    	MainApp mainApp = new MainApp();
    	mainApp.showMainScreen();
    	
    	prevStage = (Stage) loginButton.getScene().getWindow();
    	prevStage.close();
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

//         Add observable list data to the table

    }
    
    

}
