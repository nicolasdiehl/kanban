package view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import control.ClientControl;
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
	private ClientControl clientControl;
	Stage prevStage;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public LoginScreenController() {

	}

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	@FXML
	public void dropLoginButton() {

		// MainApp gets altered to pass Users full name to next Stage
		try {
			clientControl.userLogIn(usernameTextfield.getText(), mainApp);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		while (mainApp.loginName == "") {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Wait till name gets altered in other Thread, could be done better
			// with
			// Threads;
			if (mainApp.loginName == "") {
				// System.out.print(".");
			} else {
				System.out.println(mainApp.loginName + " logged in.");
			}
		}
		if (mainApp.loginName.equals("null null")) {
			System.out.println("Client Message: User does not exist!");
			usernameTextfield.setText(usernameTextfield.getText() + " nicht gefunden");
			mainApp.loginName = "";
		} else {
			System.out.println("'" + mainApp.loginName + "' in else!");
			mainApp.showProjectScreen();

			prevStage = (Stage) loginButton.getScene().getWindow();
			prevStage.close();
		}
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		clientControl = ClientControl.getInstance();

		this.mainApp = new MainApp();
		this.mainApp.loginName = "";
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		// Add observable list data to the table

	}

}
