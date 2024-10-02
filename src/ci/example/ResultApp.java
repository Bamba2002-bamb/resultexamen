package ci.example;

import java.io.IOException;
import ci.example.ui.FXMLPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ResultApp extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(FXMLPage.HOME.getPage());

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Résultat de l'examen national");
        stage.setResizable(false);
        stage.show();
	}

	
	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
