package GUI.Courses;

import java.sql.*;

import Curriculum.Course;
import Curriculum.Level;
import Curriculum.Status;
import DatabaseConnection.DatabaseConnection;
import GUI.InterfaceGUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateCourse {

    public CreateCourse() {
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);
//TITLE
        Text createCur = new Text("Create a course");
        createCur.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
//ALL INPUT FIELDS ARE BELOW HERE
        Insets inputInset = new Insets(0, 20, 0, 20);
//INPUT FIELDS COURSENAME
        HBox name = new HBox(10);
        name.setPadding(new Insets(20, 20, 0, 20));
        name.setAlignment(Pos.CENTER);
        name.setTranslateX(-12.5);

        Label labelName = new Label("Coursename: ");
        labelName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputCourseName = new TextField();        
//INPUT FIELDS DESCRIPTION
        HBox courseInfo = new HBox(10);
        courseInfo.setPadding(inputInset);
        courseInfo.setAlignment(Pos.CENTER);
        courseInfo.setTranslateX(-12.5);

        Label labelInfo = new Label("Description: ");
        labelInfo.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextArea inputInfo = new TextArea();
//INPUT FIELDS LEVEL
        HBox courseLevel = new HBox(10);
        courseLevel.setPadding(inputInset);
        courseLevel.setAlignment(Pos.CENTER);
        courseLevel.setTranslateX(1);

        Label labelLevel = new Label("Level: ");
        labelLevel.setFont(Font.font("verdana", FontWeight.BOLD, 14));

        ComboBox courseLevels = new ComboBox<>();
        courseLevels.getItems().addAll(Level.ADVANCED, Level.BEGINNER, Level.EXPERT);
        courseLevels.setPromptText("Choose Level...");
//INPUT FIELDS STATUS
        HBox courseStatus = new HBox(10);
        courseStatus.setPadding(inputInset);
        courseStatus.setAlignment(Pos.CENTER);

        Label labelStatus = new Label("Status: ");
        labelStatus.setFont(Font.font("verdana", FontWeight.BOLD, 14));

        ComboBox courseStatusses = new ComboBox<>();
        courseStatusses.getItems().addAll(Status.ACTIVE, Status.ARCHIVED, Status.CONCEPT);
        courseStatusses.setPromptText("Choose Status...");
//ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        name.getChildren().addAll(labelName, inputCourseName);
        courseInfo.getChildren().addAll(labelInfo, inputInfo);
        courseLevel.getChildren().addAll(labelLevel, courseLevels);
        courseStatus.getChildren().addAll(labelStatus, courseStatusses);

//SAVE BUTTON
        Button saveCursist = new Button("Save");

        saveCursist.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                Course temp = new Course(inputCourseName.getText(), inputInfo.getText());
                System.out.println(temp + ", " + courseLevels.getValue() + ", " + courseStatusses.getValue());
                Statement stmt = conn.createStatement();
                String SQL = "INSERT INTO Course VALUES ('" + inputCourseName.getText() + "', '" + inputInfo.getText() + "', '" + courseLevels.getValue() + "', '" + courseStatusses.getValue() + "')";
                System.out.println(SQL);
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
                .addAll(createCur, name, courseInfo, courseLevel, courseStatus, saveCursist, backButton);

        return layout;
    }
}
