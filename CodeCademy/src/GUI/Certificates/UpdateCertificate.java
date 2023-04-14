package GUI.Certificates;

import DatabaseConnection.DatabaseConnection;

import GUI.InterfaceGUI;

import java.sql.*;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateCertificate {

    ArrayList<String> certificates = new ArrayList<>();
    ComboBox cerficateBox = new ComboBox<>();

    public UpdateCertificate() {
    }

    public Parent getView() {
//SQL
        try {
            Connection conn = DatabaseConnection.getConnection();

            Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM CERTIFICATE";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                cerficateBox.getItems().addAll(rs.getString("CertificateID"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));
        cerficateBox.setPromptText("Select Certificate...");

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setRight(null);
        layout.setLeft(null);
        layout.setCenter(vertBox);
//TITLE
        Text updateCourse = new Text("Update a certificate");
        updateCourse.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
//INPUT FIELDS EMAIL
        HBox course = new HBox(10);
        course.setPadding(new Insets(0, 20, 0, 20));
        course.setAlignment(Pos.CENTER);

        Label labelCourse = new Label("Certificate Grade: ");

        labelCourse.setFont(Font.font("verdana", FontWeight.BOLD, 14));

        Spinner gradeSpinner = new Spinner<>(1, 10, 1);

        TextField inputNewEmail = new TextField();
//ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        course.getChildren().addAll(labelCourse, gradeSpinner);
//SAVE BUTTON
        Button btnSave = new Button("Update");
//BUTTON ON ACTION -> SAVES INPUTTED USER IN DATABASE
        btnSave.setOnAction((event) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                Statement stmt = conn.createStatement();
                String SQL = "UPDATE Certificate SET Grade = '" + gradeSpinner.getValue() + "' WHERE CertificateID = '" + cerficateBox.getValue() + "' ";

                stmt.executeUpdate(SQL);
                System.out.println(cerficateBox.getValue() + " grade was set to " + gradeSpinner.getValue());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        );
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
                .addAll(updateCourse, cerficateBox, gradeSpinner, btnSave, backButton);

        return layout;
    }
}
