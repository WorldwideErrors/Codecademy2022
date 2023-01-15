/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Courses;

import DatabaseConnection.DatabaseConnection;
import Curriculum.Course;
import Curriculum.Status;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
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
public class updateCourse {

    ArrayList<String> courses = new ArrayList<>();
    ComboBox courseBox = new ComboBox<>();

    public updateCourse() {
    }

    public Parent getView() {

        try {
            Connection conn = DatabaseConnection.getConnection();

            Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM COURSE";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                courseBox.getItems().addAll(rs.getString("CourseName"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));
        courseBox.setPromptText("Select course...");

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setRight(null);
        layout.setLeft(null);
        layout.setCenter(vertBox);

        Text updateCourse = new Text("Update a course");
        updateCourse.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        

        //INPUT FIELDS EMAIL
        HBox course = new HBox(10);
        course.setPadding(new Insets(0, 20, 0, 20));
        course.setAlignment(Pos.CENTER);

        Label labelCourse = new Label("Course Status: ");
        
        labelCourse.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        
        ComboBox courseStatusses = new ComboBox<>();
        courseStatusses.getItems().addAll(Status.ACTIVE, Status.ARCHIVED, Status.CONCEPT);
        courseStatusses.setPromptText("Choose Status...");

        //NEW EMAIL
        // HBox newEmail = new HBox(10);
        // newEmail.setPadding(new Insets(0, 39, 0, 0));
        // newEmail.setAlignment(Pos.CENTER);

        Label labelNewEmail = new Label("New email: ");
        labelNewEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputNewEmail = new TextField();
        //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        course.getChildren().addAll(labelCourse, courseStatusses);
        
        //SAVE BUTTON
        Button btnSave = new Button("Update");

        //BUTTON ON ACTION -> SAVES INPUTTED USER IN DATABASE
        btnSave.setOnAction((event) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                Statement stmt = conn.createStatement();
                String SQL = "UPDATE Course SET Status = '" + courseStatusses.getValue() + "' WHERE CourseName = '" + courseBox.getValue() + "' ";

                stmt.executeUpdate(SQL);
                System.out.println(courseBox.getValue() + " status was set to " + courseStatusses.getValue());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        );

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(updateCourse, courseBox, course, btnSave);

        return layout;
    }
}
