package control;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ITaskFX;
import view.MainScreenController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<ITaskFX> taskData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data

    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<ITaskFX> getTaskData() {
        return taskData;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.getIcons().add(new Image("KbLogo.png"));
        this.primaryStage.setTitle("HEMS Kanban");

        initRootLayout();
        showMainScreen();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
	try {
	    // Load root layout from fxml file.
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainApp.class.getResource("/view/rootLayout.fxml"));
	    rootLayout = (BorderPane) loader.load();
	    
	    // Show the scene containing the root layout.
	    Scene scene = new Scene(rootLayout);
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
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/MainScreen.fxml"));
            BorderPane MainScreen = (BorderPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(MainScreen);
            
            // Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
	launch(args);
    }
}
