package GUI.Courses;

import DatabaseConnection.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class ViewCourses {
    ArrayList<String> courses = new ArrayList<>();
    ComboBox courseBox = new ComboBox<>();

    public ViewCourses() {
    }

    /**
     * @author WorldWideErrors
     */
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
        Insets inputInset = new Insets(0, 20, 0, 20);

        Text getCourseText = new Text("View a course");
        getCourseText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

        //SAVE BUTTON
        Button viewCourse = new Button("View");

        //BUTTON ON ACTION -> VIEWS INPUTTED COURSE FROM DATABASE
        viewCourse.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                Statement stmt = conn.createStatement();
                String SQL = "SELECT * FROM COURSE WHERE CourseName = '" + courseBox.getValue().toString() + "'";
                ResultSet rs = stmt.executeQuery(SQL);

                //ATTRIBUTES AND VALUES
                while (rs.next()) {
                    String courseNameStr = rs.getString("CourseName");
                    String courseDescriptionStr = rs.getString("IntroductionText");
                    String courseLevelStr = rs.getString("Level");
                    String courseStatusStr = rs.getString("Status");

                    //VIEW AFTER PRESSING BUTTON
                    vertBox.getChildren().removeAll(getCourseText, courseBox, viewCourse);

                    Text courseInfo = new Text("Course information:");
                    courseInfo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

                    //INPUT FIELDS NAME
                    HBox name = new HBox(10);
                    name.setPadding(new Insets(20, 20, 0, 20));
                    name.setAlignment(Pos.CENTER);

                    Label labelName = new Label("Name: ");
                    labelName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
                  
                    //COURSENAME
                    HBox coursename = new HBox(10);
                    coursename.setPadding(inputInset);
                    coursename.setAlignment(Pos.CENTER);

                    Label labelCourseName = new Label("Coursename:");
                    labelCourseName.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoName = new Label(courseNameStr);

                    // DESCRIPTION
                    HBox coursedesc = new HBox(10);

                    Label labelDesc = new Label("Introduction: ");
                    labelDesc.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    coursedesc.setPadding(inputInset);
                    coursedesc.setAlignment(Pos.CENTER);
                    Label infoIntro = new Label(courseDescriptionStr);

                    // LEVEL
                    HBox courseLevel = new HBox(10);

                    Label labelLevel = new Label("Level: ");
                    labelLevel.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    courseLevel.setPadding(inputInset);
                    courseLevel.setAlignment(Pos.CENTER);
                    Label infoLevel = new Label(courseLevelStr);
                    
                    // STATUS
                    HBox courseStatus = new HBox(10);

                    Label labelStatus = new Label("Status: ");
                    labelStatus.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    courseStatus.setPadding(inputInset);
                    courseStatus.setAlignment(Pos.CENTER);
                    Label infoStatus = new Label(courseStatusStr);
                    
                    //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
                    
                    coursename.getChildren().addAll(labelName, infoName);
                    coursedesc.getChildren().addAll(labelDesc, infoIntro);
                    courseLevel.getChildren().addAll(labelLevel, infoLevel);
                    courseStatus.getChildren().addAll(labelStatus, infoStatus);

                    vertBox.getChildren().addAll(courseInfo, coursename, coursedesc, courseLevel, courseStatus);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        );

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(getCourseText, courseBox, viewCourse);

        return layout;
    }
}
