package view;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import control.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Project;
import model.SimpleProject;

public class ProjectSelectionScreenController {
    
//	@FXML
//	private Label lb_date;
    @FXML
    private Label lb_greeting;
//	@FXML
//	private Button bt_ProjektAuswahl;
    @FXML
    private Button bt_accept;
    @FXML
//	private TextField tb_status;
//	@FXML
//	private TextField tb_date;
    
    private MainApp mainApp;
    
    ArrayList<SimpleProject> listOfProjectNames = new ArrayList<SimpleProject>();
    
    Stage prevStage;
    
    public ProjectSelectionScreenController() {
	
    }
    
    @FXML
    private void initialize() {
	// Initialize current Date and User and show them in a textfield
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDate localDate = LocalDate.now();
	String reportDate = dtf.format(localDate);
//		tb_date.setText(reportDate);
	lb_greeting.setText("Hallo " + mainApp.loginName);
	
    }
    
    @FXML
    private void handleProjektAuswahl() {
	// Create a file chooser
	final JFileChooser fileChooser = new JFileChooser();
	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	int result = fileChooser.showOpenDialog(fileChooser);
	
	//
	
	if (result == JFileChooser.APPROVE_OPTION) {
	    
	    File file = fileChooser.getSelectedFile();
	    String filename = file.getAbsolutePath();
	    // String[] parts = string.split("/");
//			tb_status.setText(filename);
	    // This is where a real application would open the file.
	    
	} else {
//			tb_status.setText("Open command cancelled by user.");
	}
	// In response to a button click:
	// int returnVal = fc.showOpenDialog(aComponent);
    }
    
    // TB 20170907
    // button methods
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewProject() {
//		Project tmpP = new Project();
	mainApp.showProjectInsertDialog();
//		if (okClicked) {
//			mainApp.getProjektData().add(tmpP);
//		}
    }
    
    @FXML
    private void handleAccept() {
	
	listOfProjectNames.add(new SimpleProject("ich", "123"));
	listOfProjectNames.add(new SimpleProject("du", "234"));
	listOfProjectNames.add(new SimpleProject("er", "345"));
	System.out.println(listOfProjectNames.get(0).getName());
	
	MainApp mainApp = new MainApp();
	
	mainApp.showMainScreen();
	
	prevStage = (Stage) bt_accept.getScene().getWindow();
	prevStage.close();
    }
    
    public void setMainApp(MainApp mainApp) {
	this.mainApp = mainApp;
    }
    
}
