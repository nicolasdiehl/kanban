package view;

import control.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class RootLayoutController {
    @FXML private MenuItem projectChangeMenu;
    @FXML private MenuBar rootMenuBar;
    private MainApp mainApp;
    @FXML
    private void changeProject(){
	mainApp.showProjectScreen();
	    Stage prevStage;
	    prevStage = (Stage) rootMenuBar.getScene().getWindow();
	    prevStage.close();
    }
}
