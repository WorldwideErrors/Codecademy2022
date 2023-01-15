/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accounts;

import DatabaseConnection.DatabaseConnection;
import java.sql.*;
import People.Cursist;

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
 * @author Ordinary
 */
public class DeleteCursist {

    public DeleteCursist() {
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);

        Text createEmp = new Text("Delete a cursist");
        createEmp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        Text underMessage = new Text("Input the email of the cursist you want to delete.");
        underMessage.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));

        //INPUT FIELDS EMAIL
        HBox email = new HBox(10);
        email.setPadding(new Insets(0, 20, 20, 20));
        email.setAlignment(Pos.CENTER);

        Label labelEmail = new Label("Email: ");
        ComboBox emailMenu = new ComboBox();
        emailMenu.setPromptText("Email");

        emailMenu.setPrefSize(150, 30);

//SQL CODE FOR MENU
        try {
            String query = "SELECT Email FROM Cursist";
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String value = rs.getString("Email");

                emailMenu.getItems().add(value);
            }
        } catch (SQLException ex) {
        }
        //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        email.getChildren().addAll(labelEmail, emailMenu);

        //SAVE BUTTON
        Button deleteCursist = new Button("Delete");

        //BUTTON ON ACTION -> DELETES INPUTTED CURSIST
        deleteCursist.setOnAction((event) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                String SQL = "DELETE FROM Cursist WHERE Email = '" + emailMenu.getValue() + "'";

                stmt.executeUpdate(SQL);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(createEmp, underMessage, email, deleteCursist);

        return layout;
    }
}
