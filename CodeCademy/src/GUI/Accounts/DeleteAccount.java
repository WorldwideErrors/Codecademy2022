/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accounts;

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
public class DeleteAccount {

    public DeleteAccount() {
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);

        //BUTTONS DELETECURSIST AND DELETEEEMPLOYEE
        Button deleteCursist = new Button("Delete cursist");
        Button deleteEmployee = new Button("Delete employee");

        //BUTTONS LOCATION
        layout.setLeft(deleteCursist);
        layout.setRight(deleteEmployee);

        deleteCursist.setTranslateY(200);
        deleteEmployee.setTranslateY(200);

        deleteCursist.setTranslateX(200);
        deleteEmployee.setTranslateX(-200);

//VBOX FOR BUTTON LEFT
        VBox leftCenter = new VBox();
        leftCenter.getChildren().addAll(deleteCursist);

//VBOX FOR BUTTON RIGHT
        VBox rightCenter = new VBox();
        rightCenter.getChildren().addAll(deleteEmployee);
//LAYOUT PLACE BUTTONS LEFT AND RIGHT
        layout.setLeft(leftCenter);
        layout.setRight(rightCenter);

        //BUTTON EVENT DELETE CURSIST
        //WIP
        //BUTTON EVENT DELETE EMPLOYEE
        deleteEmployee.setOnAction((event) -> {
            VBox vertBox = new VBox(5);
            vertBox.setAlignment(Pos.TOP_CENTER);

            layout.setRight(null);
            layout.setLeft(null);
            layout.setCenter(vertBox);

            Text createEmp = new Text("Delete an employee");
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
            Button saveEmployee = new Button("Save");

            //BUTTON ON ACTION -> SAVES INPUTTED USER IN DATABASE
            saveEmployee.setOnAction((event2) -> {
                String URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyRE;user=janko;password=Morgen;encrypt=true;trustServerCertificate=true";

                try ( Connection conn = DriverManager.getConnection(URL);  Statement stmt = conn.createStatement();) {
                    System.out.println("Inserting records into the table...");
                    //SQL QUERY
                    String sql = "INSERT INTO Employee VALUES ('" + inputName.getText() + "', '" + inputEmail.getText() + "')";
                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            );

            //ADD ALL TO VBOX
            vertBox.getChildren()
                    .addAll(createEmp, name, email, saveEmployee);
            layout.setTop(vertBox);

        }
        );

        return layout;
    }
}
