package control;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ITaskFX;
import view.MainScreenController;
import view.NewTaskDialogController;
import view.ProjectSelectionScreenController;

public class MainApp extends Application {
    
    private Stage primaryStage;
    
    private BorderPane rootLayout;
    private AnchorPane loginLayout; /////////////////////
    private AnchorPane newTaskLayout;
    
    public static String loginName;
    /**
     * The data as an observable list of Task.
     */
//    private ObservableList<ITaskFX> taskData = FXCollections.observableArrayList();
//    private Project tempProject = new Project("name", "description", 2);
//    private ObservableList<ITask> tempTaskData = tempProject.getTasksProperty();
    
    /**
     * Constructor
     */
    public MainApp() {
	// Add some sample data
//	ITask task = new Task();
//	task.setTitle("klingt gut");
//	task.setDescribtion("blablablblub");
//	taskData.add(task);
//	
//	task = new Task();
//	task.setTitle("noch eins");
//	taskData.add(task);
    }
    
    /**
     * Returns the data as an observable list of Persons.
     * 
     * @return
     */
//    public ObservableList<ITaskFX> getTaskData() {
//	return taskData;
//    }
    
    @Override
    public void start(Stage primaryStage) {
	this.primaryStage = primaryStage;
	this.primaryStage.getIcons().add(new Image("KbLogo.png"));
	this.primaryStage.setTitle("HEMS Kanban");
	
	// initRootLayout();
	showLoginScreen();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
	try {
	    
	    Stage stage = new Stage();
	    stage.getIcons().add(new Image("KbLogo.png"));
	    stage.setTitle("HEMS Kanban");
	    
	    // Load root layout from fxml file.
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainApp.class.getResource("/view/rootLayout.fxml"));
	    rootLayout = (BorderPane) loader.load();
	    
	    // Show the scene containing the root layout.
	    Scene scene = new Scene(rootLayout);
	    stage.setScene(scene);
	    stage.show();
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public void showLoginScreen() {
	try {
	    // Load login screen.
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainApp.class.getResource("/view/LoginScreen.fxml"));
	    
	    loginLayout = (AnchorPane) loader.load();
	    
	    // Show the scene containing the root layout.
	    Scene scene = new Scene(loginLayout);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showMainScreen() {
	try {
	    
	    initRootLayout();
	    // Load person overview.
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainApp.class.getResource("/view/MainScreen.fxml"));
	    BorderPane MainScreen = (BorderPane) loader.load();
	    
	    // Set mainscreen into the center of root layout.
	    rootLayout.setCenter(MainScreen);
	    
	    // Give the controller access to the main app.
	    MainScreenController controller = loader.getController();
	    controller.setMainApp(this);
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public void showProjectScreen() {
	try {
	    initRootLayout();
	    // Load project overview.
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainApp.class.getResource("/view/ProjectSelectionScreen.fxml"));
	    AnchorPane ProjectScreen = (AnchorPane) loader.load();
	    
	    // Set project selection into the center of root layout.
	    rootLayout.setCenter(ProjectScreen);
	    
	    // Give the controller access to the main app.
	    ProjectSelectionScreenController controller = loader.getController();
	    controller.setMainApp(this);
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public boolean showNewTaskDialog(ITask selectedTask) {
	
	try {
	    // Load the fxml file and create a new stage for the popup dialog.
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainApp.class.getResource("/view/NewTaskDialog.fxml"));
	    newTaskLayout = (AnchorPane) loader.load();
	    
	    // Create the dialog Stage.
	    Stage dialogStage = new Stage();
	    dialogStage.getIcons().add(new Image("KbLogo.png"));
	    dialogStage.setTitle("New Task");
	    dialogStage.initModality(Modality.WINDOW_MODAL);
	    dialogStage.initOwner(primaryStage);
	    
	    Scene scene = new Scene(newTaskLayout);
	    dialogStage.setScene(scene);
//	    dialogStage.show();
	    
	    // Set the person into the controller.
	    NewTaskDialogController controller = loader.getController();
	    controller.setDialogStage(dialogStage);
//	    controller.setTask(selectedTask);
	    
	    // Show the dialog and wait until the user closes it
	    dialogStage.showAndWait();
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	return false;
    }
    
    /**
     * Returns the main stage.
     * 
     * @return
     */
    public Stage getPrimaryStage() {
	return primaryStage;
    }
    
    public static void main(String[] args) {
	launch(args);
    }
}
