package ci.example.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ci.example.model.Result;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;


public class BaseController {
	private static String url = "ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com";
    private static String user = "user0071804";
    private static String pass = "Yf3IgyBsOPa34WR";
    
    protected static String userMat;
    protected static Result res;
    protected static float total = 0;
    protected static float totalSucces = 0; 
    protected static float rate = 0;

    /**
     * Navigue vers une autre page.
     * 
     * @param event L'événement déclencheur
     * @param fxmlDocName Le fichier FXML de la page cible
     * @throws IOException Si une exception d'entrée ou sortie se produit
     */
    protected void navigate(Event event, URL fxmlDocName) throws IOException {
        Parent pageParent = FXMLLoader.load(fxmlDocName);
        Scene scene = new Scene(pageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(scene);
        appStage.show();
    }

    /**
     * Obtient une connexion à la base de données.
     * 
     * @return La connexion à la base de données
     */
    public Connection getConnection() {
        Connection connexion = null;

        try {
            connexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Connexion établie.");
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la connexion. Contenu: " + e.getMessage());
        }

        return connexion;
    }
    
    public void calculPct() {
    	fetchData();
    	rate = totalSucces / total;
    }

    /**
     * Récupère les résultats, à partir d'un matricule, de la base de données.
     * 
     * @param mat Le matricule du candidat
     * @return La resultat trouvé
     */
    public Result getOne(String mat) {
        Connection connection = null;

        try {
            connection = getConnection();
            if (connection != null) {
                String query = "SELECT * FROM resultats WHERE matricule = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, mat);

                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                        	int id = rs.getInt("ID");
                            int mat1 = rs.getInt("matricule");
                            int statut = rs.getInt("statut");
                            float moyenne = rs.getFloat("moyenne");
                            return new Result(
                                id, mat1, statut, moyenne
                            );
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Erreur: " + e.getMessage());
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erreur: " + e.getMessage());
                }
            }
        }

        return null;
    }
    
    /**
     * Récupère les données depuis la base de données.
     */
    public void fetchData() {
        total = 0;
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM notes";
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    int statut = rs.getInt("statut");
                    float moyenne = rs.getFloat("moyenne");

                    total += moyenne;
                    if (statut == 1) totalSucces += moyenne;
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error fetching data: " + e.getMessage());
            }
        }
    }
}
