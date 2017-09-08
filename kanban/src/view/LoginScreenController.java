package view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import control.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginScreenController {
    
    @FXML
    private AnchorPane loginAnchorPane;   
    @FXML
    private Label passwortLabel;  
    @FXML
    private Label usernameLabel;    
    @FXML
    private JFXPasswordField passwortTextfield;   
    @FXML
    private JFXTextField usernameTextfield;
    @FXML
    private Button loginButton;
    
    // Reference to the main application.
    private MainApp mainApp;
    
    Stage prevStage;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginScreenController() {
	
    }
    
    public void setPrevStage(Stage stage) {
	this.prevStage = stage;
    }
    
    @FXML
    public void dropLoginButton() {
	
	MainApp mainApp = new MainApp();
	
	mainApp.loginName = this.usernameTextfield.getText();
	
	// mainApp.showMainScreen();
	mainApp.showProjectScreen();
	
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
