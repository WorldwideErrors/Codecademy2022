package GUI.Certificates;

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

public class ViewCertificate {
    ArrayList<String> certificates = new ArrayList<>();
    ComboBox certificateBox = new ComboBox<>();

    public ViewCertificate() {
    }

    /**
     * @author WorldWideErrors
     */
    public Parent getView() {
        
        try {
            Connection conn = DatabaseConnection.getConnection();

            Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM CERTIFICATE";
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
                certificateBox.getItems().addAll(rs.getString("CertificateID"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));
        certificateBox.setPromptText("Choose a certificate...");

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);
        Insets inputInset = new Insets(0, 20, 0, 20);

        Text getCertificateText = new Text("View a certificate");
        getCertificateText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

        //SAVE BUTTON
        Button viewCertificate = new Button("View");

        //BUTTON ON ACTION -> VIEWS INPUTTED COURSE FROM DATABASE
        viewCertificate.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                Statement stmt = conn.createStatement();
                String SQL = "SELECT * FROM CERTIFICATE WHERE CertificateID = '" + certificateBox.getValue().toString() + "'";
                ResultSet rs = stmt.executeQuery(SQL);

                //ATTRIBUTES AND VALUES
                while (rs.next()) {
                    String IDString = rs.getString("CertificateID");
                    String gradeString = rs.getString("Grade");
                    String reviewerString = rs.getString("ReviewerEmail");

                    //VIEW AFTER PRESSING BUTTON
                    vertBox.getChildren().removeAll(getCertificateText, certificateBox, viewCertificate);

                    Text courseInfo = new Text("Certificate information:");
                    courseInfo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

                    //INPUT FIELDS NAME
                    HBox name = new HBox(10);
                    name.setPadding(new Insets(20, 20, 0, 20));
                    name.setAlignment(Pos.CENTER);

                    //COURSENAME
                    HBox certificateID = new HBox(10);
                    certificateID.setPadding(inputInset);
                    certificateID.setAlignment(Pos.CENTER);

                    Label labelID = new Label("Certificate ID:");
                    labelID.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoID = new Label(IDString);

                    // DESCRIPTION
                    HBox certificateGrade = new HBox(10);

                    Label labelGrade = new Label("Grade: ");
                    labelGrade.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    certificateGrade.setPadding(inputInset);
                    certificateGrade.setAlignment(Pos.CENTER);
                    Label infoGrade = new Label(gradeString);

                    // LEVEL
                    HBox certificateReviewer = new HBox(10);

                    Label labelReviewer = new Label("Reviewer: ");
                    labelReviewer.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    certificateReviewer.setPadding(inputInset);
                    certificateReviewer.setAlignment(Pos.CENTER);
                    Label infoReviewer = new Label(reviewerString);
                    
                    //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
                    
                    certificateID.getChildren().addAll(labelID, infoID);
                    certificateGrade.getChildren().addAll(labelGrade, infoGrade);
                    certificateReviewer.getChildren().addAll(labelReviewer, infoReviewer);

                    vertBox.getChildren().addAll(courseInfo, certificateID, certificateGrade, certificateReviewer);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        );

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(getCertificateText, certificateBox, viewCertificate);

        return layout;
    }
}
