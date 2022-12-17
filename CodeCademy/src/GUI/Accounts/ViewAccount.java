/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accounts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
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
 * @author Janko
 */
public class ViewAccount {

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);

        //BUTTONS CREATECURSIST AND CREATEEMPLOYEE
        Button viewCursist = new Button("View cursist");
        Button viewEmployee = new Button("View employee");

        //BUTTONS LOCATION
        layout.setLeft(viewCursist);
        layout.setRight(viewEmployee);

        viewCursist.setTranslateY(200);
        viewEmployee.setTranslateY(200);

        viewCursist.setTranslateX(200);
        viewEmployee.setTranslateX(-200);

//VBOX FOR BUTTON LEFT
        VBox leftCenter = new VBox();
        leftCenter.getChildren().addAll(viewCursist);

//VBOX FOR BUTTON RIGHT
        VBox rightCenter = new VBox();
        rightCenter.getChildren().addAll(viewEmployee);
//LAYOUT PLACE BUTTONS LEFT AND RIGHT
        layout.setLeft(leftCenter);
        layout.setRight(rightCenter);

        //BUTTON EVENT View CURSIST
        //WIP
        //BUTTON EVENT View EMPLOYEE
        viewEmployee.setOnAction((event) -> {
            VBox vertBox = new VBox(5);
            vertBox.setAlignment(Pos.TOP_CENTER);

            layout.setRight(null);
            layout.setLeft(null);
            layout.setCenter(vertBox);

            Text createEmp = new Text("View an employee");
            createEmp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
            Text underMessage = new Text("Input an email to search for a specific employee");
            underMessage.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
            Text empty = new Text();

            //INPUT FIELDS EMAIL
            HBox email = new HBox(10);
            email.setPadding(new Insets(0, 20, 20, 20));
            email.setAlignment(Pos.CENTER);

            Label labelEmail = new Label("Email: ");
            labelEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
            TextField inputEmail = new TextField();

            //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
            email.getChildren().addAll(labelEmail, inputEmail);

            //SAVE BUTTON
            Button searchEmployee = new Button("Search");

            //BUTTON ON ACTION -> SEARCHES INPUTTED USER IN DATABASE
            HBox output = new HBox();
            searchEmployee.setOnAction((event2) -> {
                String URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyRE;user=janko;password=Morgen;encrypt=true;trustServerCertificate=true";
                // ADD CODE FOR SEARCHING USER
                try ( Connection conn = DriverManager.getConnection(URL);  Statement stmt = conn.createStatement();) {
                    String sql = "SELECT Name, Email FROM Employee WHERE Email = '" + inputEmail.getText() + "'";
                    ResultSet rs = stmt.executeQuery(sql); //SQL QUERY

                    Label namelabel = new Label(rs.getString("Name"));
                    Label emaillabel = new Label(rs.getString("Email"));

                    output.getChildren().addAll(namelabel, emaillabel);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            );

            //ADD ALL TO VBOX
            vertBox.getChildren()
                    .addAll(createEmp, underMessage, empty, email, searchEmployee, output);
            layout.setTop(vertBox);

        }
        );

        return layout;
    }
}
