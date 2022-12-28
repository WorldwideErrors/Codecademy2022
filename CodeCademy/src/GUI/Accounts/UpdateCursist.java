/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accounts;

import DatabaseConnection.DatabaseConnection;
import java.sql.*;
import People.Employee;
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
public class UpdateCursist {

    public UpdateCursist() {
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setRight(null);
        layout.setLeft(null);
        layout.setCenter(vertBox);

        Text createEmp = new Text("Update an employee");
        createEmp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

        ///// EVERYTHING BELOW THIS IS WIP <--------------------------------------------
        //INPUT FIELDS NAME
        HBox name = new HBox(10);
        name.setPadding(new Insets(20, 20, 0, 20));
        name.setAlignment(Pos.CENTER);

        Label labelName = new Label("Name: ");
        labelName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputName = new TextField();

        //INPUT FIELDS EMAIL
        HBox email = new HBox(10);
        email.setPadding(new Insets(0, 20, 20, 20));
        email.setAlignment(Pos.CENTER);

        Label labelEmail = new Label("Email: ");
        labelEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputEmail = new TextField();

        //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        name.getChildren().addAll(labelName, inputName);
        email.getChildren().addAll(labelEmail, inputEmail);

        //SAVE BUTTON
        Button saveEmployee = new Button("Update");

        //BUTTON ON ACTION -> SAVES INPUTTED USER IN DATABASE
        saveEmployee.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                //  Cursist temp = new Cursist(inputEmail.getText(), inputName.getText(), datePicker.getValue(), genderChar, inputStreet.getText(), inputPostal.getText(), inputCity.getText(), inputCountry.getText());
                //   System.out.println(temp);
                Statement stmt = conn.createStatement();
                String SQL = "";

                stmt.executeUpdate(SQL);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        );

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(createEmp, name, email, saveEmployee);
      

        return layout;
    }
}
