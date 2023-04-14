package GUI.Certificates;

import java.sql.*;

import DatabaseConnection.DatabaseConnection;
import GUI.InterfaceGUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateCertificate {

    public CreateCertificate() {
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);
//TITLE
        Text createCertificate = new Text("Create a certificate");
        createCertificate.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
//ALL INPUT FIELDS ARE BELOW HERE
        Insets inputInset = new Insets(0, 20, 0, 20);        
//INPUT FIELDS CERTIFICATEID
        HBox name = new HBox(10);
        name.setPadding(new Insets(20, 40, 0, 20));
        name.setAlignment(Pos.CENTER);
        name.setTranslateX(-12.5);

        Label labelName = new Label("CertificateID");
        labelName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputID = new TextField();        
//INPUT FIELDS EMAIL
        HBox email = new HBox(10);
        email.setPadding(inputInset);
        email.setAlignment(Pos.CENTER);

        Label labelEmail = new Label("Email: ");
        labelEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
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
                String query = "SELECT CourseName from Registration WHERE CursistEmail = '" + selectedItem + "' AND CertificateId IS NULL";
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
//INPUT FIELDS GRADE
        HBox gradeSpinner = new HBox(10);
        gradeSpinner.setPadding(inputInset);
        gradeSpinner.setAlignment(Pos.CENTER);
        gradeSpinner.setTranslateX(-12.5);

        Label labelInfo = new Label("Grade: ");
        labelInfo.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        Spinner gradSpinner = new Spinner<>(1, 10, 1);

        Label labelLevel = new Label("Level: ");
        labelLevel.setFont(Font.font("verdana", FontWeight.BOLD, 14));        
//INPUT FIELDS REVIEWEREMAIL
        HBox reviewer = new HBox(10);
        reviewer.setPadding(inputInset);
        reviewer.setAlignment(Pos.CENTER);

        Label labelReviewer = new Label("Reviewer: ");
        labelReviewer.setFont(Font.font("verdana", FontWeight.BOLD, 14));

        ComboBox reviewerBox = new ComboBox<>();

        try {
            String query = "SELECT Email FROM Employee";
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String value = rs.getString("Email");

                reviewerBox.getItems().add(value);
            }
        } catch (SQLException ex) {
        }

        reviewerBox.setPromptText("Choose Reviewer...");        
//ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        name.getChildren().addAll(labelName, inputID);
        email.getChildren().addAll(labelEmail, emailMenu);
        course.getChildren().addAll(labelCourse, coursesMenu);
        gradeSpinner.getChildren().addAll(labelInfo, gradSpinner);
        reviewer.getChildren().addAll(labelReviewer, reviewerBox);
//SAVE BUTTON
        Button btnSave = new Button("Save");

        btnSave.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                Statement stmt = conn.createStatement();
                String SQL = "INSERT INTO Certificate VALUES ('" + inputID.getText() + "', '" + gradSpinner.getValue() + "', '" + reviewerBox.getValue() + "')"
                        + "\nUPDATE Registration SET CertificateID = '" + inputID.getText() + "' WHERE CursistEmail = '" + emailMenu.getValue() + "' AND CourseName = '" + coursesMenu.getValue() + "'";
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
                .addAll(createCertificate, name, email, course, gradeSpinner, reviewer, btnSave, backButton);

        return layout;
    }
}
