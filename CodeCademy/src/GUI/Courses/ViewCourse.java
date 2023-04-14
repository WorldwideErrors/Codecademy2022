package GUI.Courses;

import DatabaseConnection.DatabaseConnection;
import GUI.InterfaceGUI;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.stage.Stage;

public class ViewCourse {
    ArrayList<String> courses = new ArrayList<>();
    ComboBox courseBox = new ComboBox<>();

    public ViewCourse() {
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
                    
                    /// ----
                    //RECOMMENDED COURSES
                    VBox recommendedCourses = new VBox(10);
                    recommendedCourses.setPadding(inputInset);
                    recommendedCourses.setAlignment(Pos.CENTER);
                    Label dash = new Label("-------------------------------------------------------------------------");
                    Label labelRecommended = new Label("Recommended Courses:");
                    labelRecommended.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoRecommended = new Label();
                    //SQL CODE FOR recommended Courses
                    try {
                        Connection conn2 = DatabaseConnection.getConnection();
                        Statement stmt2 = conn2.createStatement();
                        String SQL2 = "SELECT CourseName, InterestingCourseName FROM InterestingCourse WHERE CourseName = '" + courseBox.getValue() + "'";
                        ResultSet rs2 = stmt2.executeQuery(SQL2);

                        //ATTRIBUTES AND VALUES
                        String recommendedStr = "";
                        while (rs2.next()) {
                            if (rs2.getString("CourseName") != null) {
                                recommendedStr += rs2.getString("InterestingCourseName") + "\n";
                            }

                            infoRecommended.setText(recommendedStr);
                            infoRecommended.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
                        }
                    } catch (SQLException ex) {

                    }

                    /// ----
                    //RECEIVED CERTIFICATES
                    VBox finishedCursists = new VBox(10);
                    finishedCursists.setPadding(inputInset);
                    finishedCursists.setAlignment(Pos.CENTER);
                    Label dash1 = new Label("-------------------------------------------------------------------------");
                    Label labelFinished = new Label("Amount of finished cursists:");
                    labelFinished.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoFinished = new Label();
                    //SQL CODE FOR recommended Courses
                    try {
                        Connection conn3 = DatabaseConnection.getConnection();
                        Statement stmt3 = conn3.createStatement();
                        // SELECT COUNT(CursistEmail) FROM Registration WHERE CertificateId IS NOT NULL AND CourseName = 'ENGLISH'
                        String SQL3 = "SELECT SUM(CASE WHEN CertificateID IS NOT NULL THEN 1 ELSE 0 END) AS 'Finished', COUNT(CursistEmail) AS 'ALL' FROM Registration WHERE CourseName = '" + courseBox.getValue() + "'";
                        
                        ResultSet rs3 = stmt3.executeQuery(SQL3);
                        System.out.println(SQL3);

                        //ATTRIBUTES AND VALUES
                        while (rs3.next()) {
                            int iFinished = rs3.getInt("Finished");
                            int iAll = rs3.getInt("All");
                            
                            if(iFinished > 0){
                            float percentage = (float) iFinished / iAll * 100;
                            infoFinished.setText(String.format(iFinished + "/" + iAll +  " (%.02f", percentage) + "%)");
                            }else{
                            infoFinished.setText("0 finished");
                            }
                            
                            infoFinished.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
                        }
                    }catch(SQLException ex) {
                        System.out.println(ex);
                    }
                    //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
                    
                    coursename.getChildren().addAll(labelName, infoName);
                    coursedesc.getChildren().addAll(labelDesc, infoIntro);
                    courseLevel.getChildren().addAll(labelLevel, infoLevel);
                    courseStatus.getChildren().addAll(labelStatus, infoStatus);
                    recommendedCourses.getChildren().addAll(labelRecommended, infoRecommended);
                    finishedCursists.getChildren().addAll(labelFinished, infoFinished);

                    vertBox.getChildren().addAll(courseInfo, coursename, coursedesc, courseLevel, courseStatus, recommendedCourses, finishedCursists);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        );

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
                .addAll(getCourseText, courseBox, viewCourse, backButton);

        return layout;
    }
}
