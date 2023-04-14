/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Courses;

import DatabaseConnection.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

import Curriculum.Course;
import GUI.InterfaceGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.stage.Stage;

/**
 *
 * @author WorldWideErrors
 */
public class DeleteCourse {

    ArrayList<String> courses = new ArrayList<>();
    ComboBox courseBox = new ComboBox<>();

    public DeleteCourse() {
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
        courseBox.setPromptText("Choose a course...");

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);

        Text delCourse = new Text("Delete a course");
        delCourse.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

        ///// EVERYTHING BELOW THIS IS WIP <--------------------------------------------
        HBox course = new HBox(10);
        course.setPadding(new Insets(0, 20, 20, 20));
        course.setAlignment(Pos.CENTER);

        Label labelCourse = new Label("Coursename: ");
        labelCourse.setFont(Font.font("verdana", FontWeight.BOLD, 14));

        //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        course.getChildren().addAll(labelCourse, courseBox);

        //SAVE BUTTON
        Button  btnDelete = new Button("Delete");

        //BUTTON ON ACTION -> DELETES INPUTTED CURSIST
        btnDelete.setOnAction((event) -> {
            try {
                String selectedValue = (String) courseBox.getSelectionModel().getSelectedItem();
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                String SQL = "DELETE FROM Course WHERE CourseName = '" + selectedValue + "'";

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
                .addAll(delCourse, courseBox, btnDelete, backButton);

        return layout;
    }
}
