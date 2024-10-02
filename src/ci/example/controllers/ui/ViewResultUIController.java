package ci.example.controllers.ui;

import java.net.URL;
import java.util.ResourceBundle;

import ci.example.controllers.BaseController;
import ci.example.model.Result;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewResultUIController extends BaseController implements Initializable {
	
	@FXML
	private Label userName;
	
	@FXML
	private Label userBirthDate;
	
	@FXML
	private Label userMat;
	
	@FXML
	private Label userEtab;
	
	@FXML
	private Label resultMsg;
	
	@FXML
	private Rectangle codeColor;
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		userName.setText(Integer.toString(res.getMat()));
		userBirthDate.setText(Integer.toString(res.getMat()));
		userMat.setText(Integer.toString(res.getMat()));
		userEtab.setText(Integer.toString(res.getMat()));
		resultMsg.setText(Integer.toString(res.getMat()));
		codeColor.setFill(res.getStat() ? Color.GREEN : Color.RED);
	}
}
