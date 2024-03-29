
package GUI.Certificates;

import DatabaseConnection.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

import GUI.InterfaceGUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class DeleteCertificate {

    ArrayList<String> certificates = new ArrayList<>();
    ComboBox certificateBox = new ComboBox<>();

    public DeleteCertificate() {
    }

    public Parent getView() {
        
//SQL
        try {
            Connection conn = DatabaseConnection.getConnection();

            Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM CERTIFICATE";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                certificateBox.getItems().addAll(rs.getString("CertificateID"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));
        certificateBox.setPromptText("Choose a certificate...");

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);
//TITLE
        Text delCourse = new Text("Delete a certificate");
        delCourse.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));       
//
        HBox certificate = new HBox(10);
        certificate.setPadding(new Insets(0, 20, 20, 20));
        certificate.setAlignment(Pos.CENTER);

        Label labelCertificate = new Label("Certificate: ");
        labelCertificate.setFont(Font.font("verdana", FontWeight.BOLD, 14));        
//ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        certificate.getChildren().addAll(labelCertificate, certificateBox);        
//SAVE BUTTON
        Button  btnDelete = new Button("Delete");        
//BUTTON ON ACTION -> DELETES INPUTTED CURSIST
        btnDelete.setOnAction((event) -> {
            try {
                String selectedValue = (String) certificateBox.getSelectionModel().getSelectedItem();
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                String SQL = "DELETE FROM Certificate WHERE CertificateID = '" + selectedValue + "'";

                stmt.executeUpdate(SQL);
                System.out.println(selectedValue + " is verwijderd.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });           
//HOMEBUTTON
          Button backButton = new Button("Back to Home Screen");
        
        backButton.setOnAction((event -> {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            InterfaceGUI gui = new InterfaceGUI();
            gui.start(thisStage);
        }));        
//ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(delCourse, certificateBox, btnDelete, backButton);

        return layout;
    }
}
