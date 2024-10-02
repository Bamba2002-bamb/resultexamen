package ci.example.controllers.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ci.example.controllers.BaseController;
import ci.example.ui.FXMLPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class HomeUIController extends BaseController implements Initializable {

	@FXML
    private Label successRate;
	
	@FXML
	private TextField mat;


	/**
     * Modifie la note sélectionnée.
     * 
     * @param event L'événement déclencheur
     * @throws IOException Si une exception d'entrée ou sortie se produit
     */
    @FXML
    void doViewResult(ActionEvent event) throws IOException {
        if (userMat != null) {
        	res = getOne(userMat);
            navigate(event, FXMLPage.VIEW.getPage());
        } else {
        	Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Matricule incorrect");
            alert.setContentText("Veuillez renseigner votre matricule.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	calculPct();
    	
    	successRate.setText(Float.toString(rate));

    	mat.setOnKeyReleased(e -> {
    		if (mat.getText().length() >= 7) userMat = mat.getText();
    	});
    }
}
