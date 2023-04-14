/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Registrations;

import DatabaseConnection.DatabaseConnection;
import GUI.InterfaceGUI;
import People.Cursist;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;

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
//        TextField inputEmail = new TextField();
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

        //INPUT FIELDS COURSE
        HBox course = new HBox(10);
        course.setPadding(inputInset);
        course.setAlignment(Pos.CENTER);

        course.setTranslateX(-12.5);
        Label labelCourse = new Label("Course:");
        labelCourse.setFont(Font.font("verdana", FontWeight.BOLD, 14));

        ComboBox coursesMenu = new ComboBox();
        coursesMenu.setPromptText("Courses");
        coursesMenu.setTranslateX(10);
        coursesMenu.setPrefSize(150, 30);
//SQL CODE FOR MENU

        emailMenu.setOnAction((event) -> {
            String selectedItem = emailMenu.getSelectionModel().getSelectedItem().toString();
            coursesMenu.getItems().clear();
            try {
                String query = "SELECT CourseName from Registration WHERE CursistEmail = '" + selectedItem + "'";
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String value = rs.getString("CourseName");

                    coursesMenu.getItems().add(value);
                }
            } catch (SQLException ex) {
            }

        });

        //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        email.getChildren().addAll(labelEmail, emailMenu);
        course.getChildren().addAll(labelCourse, coursesMenu);

//DELETE BUTTON
        Button addRegistration = new Button("Delete");

        addRegistration.setOnAction((event2) -> {
            try {

                Connection conn = DatabaseConnection.getConnection();

                Statement stmt = conn.createStatement();
                String SQL = "DELETE FROM Registration WHERE cursistEmail = '" + emailMenu.getValue() + "' AND CourseName = '" + coursesMenu.getValue() + "'";
                coursesMenu.getItems().clear();
                emailMenu.getItems().clear();
                stmt.executeUpdate(SQL);
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
                .addAll(createCur, email, course, addRegistration, backButton);

        return layout;
    }
}
