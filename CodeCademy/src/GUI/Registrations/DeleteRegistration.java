/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Registrations;

import DatabaseConnection.DatabaseConnection;
import People.Cursist;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DeleteRegistration {

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);

        Text createCur = new Text("Delete a registration");
        createCur.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
//ALL INPUT FIELDS ARE BELOW HERE
        Insets inputInset = new Insets(0, 20, 0, 20);

        //INPUT FIELDS EMAIL
        HBox email = new HBox(10);
        email.setPadding(inputInset);
        email.setAlignment(Pos.CENTER);

        Label labelEmail = new Label("Email: ");
        labelEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputEmail = new TextField();

        //INPUT FIELDS COURSE
        HBox course = new HBox(10);
        course.setPadding(inputInset);
        course.setAlignment(Pos.CENTER);

        course.setTranslateX(-12.5);
        Label labelDOB = new Label("Course:");
        labelDOB.setFont(Font.font("verdana", FontWeight.BOLD, 14));

        MenuItem test = new MenuItem("test");

        MenuButton coursesMenu = new MenuButton("Cursist", null, test);
        coursesMenu.setTranslateX(150);
        coursesMenu.setPrefSize(104, 30);

        //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        email.getChildren().addAll(labelEmail, inputEmail);
        course.getChildren().addAll(labelDOB, coursesMenu);

//SAVE BUTTON
        Button addRegistration = new Button("Create");

        addRegistration.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                Statement stmt = conn.createStatement();
                String SQL = "";

                stmt.executeUpdate(SQL);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(createCur, email, addRegistration);

        return layout;
    }
}
