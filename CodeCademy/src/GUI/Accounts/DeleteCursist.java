/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accounts;

import DatabaseConnection.DatabaseConnection;
import People.Cursist;
import java.sql.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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

        ///// EVERYTHING BELOW THIS IS WIP <--------------------------------------------
        HBox email = new HBox(10);
        email.setPadding(new Insets(0, 20, 20, 20));
        email.setAlignment(Pos.CENTER);

        Label labelEmail = new Label("Email: ");
        labelEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputEmail = new TextField();

        //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        email.getChildren().addAll(labelEmail, inputEmail);

        //SAVE BUTTON
        Button saveEmployee = new Button("Delete");

        //BUTTON ON ACTION -> DELETES INPUTTED CURSIST
        try {
            Connection conn = DatabaseConnection.getConnection();

            //  Cursist temp = new Cursist(inputEmail.getText(), inputName.getText(), datePicker.getValue(), genderChar, inputStreet.getText(), inputPostal.getText(), inputCity.getText(), inputCountry.getText());
            //   System.out.println(temp);
            Statement stmt = conn.createStatement();
            String SQL = "DELETE FROM Cursist WHERE Email = '" + inputEmail.getText() + "'";

            stmt.executeUpdate(SQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(createEmp, underMessage, email, saveEmployee);

        return layout;
    }
}
