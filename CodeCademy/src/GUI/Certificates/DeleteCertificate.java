/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Certificates;

import DatabaseConnection.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

import Curriculum.Course;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author WorldWideErrors
 */
public class DeleteCertificate {

    ArrayList<String> certificates = new ArrayList<>();
    ComboBox certificateBox = new ComboBox<>();

    public DeleteCertificate() {
    }

    public Parent getView() {

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

        Text delCourse = new Text("Delete a course");
        delCourse.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

        ///// EVERYTHING BELOW THIS IS WIP <--------------------------------------------
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

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(delCourse, certificateBox, btnDelete);

        return layout;
    }
}
