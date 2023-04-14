package GUI.Certificates;

import java.sql.*;

import Curriculum.Course;
import Curriculum.Level;
import Curriculum.Status;
import DatabaseConnection.DatabaseConnection;
import GUI.InterfaceGUI;
import People.Cursist;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
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

public class CreateCertificate {

public CreateCertificate() {
}

 /**
     * @author WorldWideErrors
     */

public Parent getView() {
    BorderPane layout = new BorderPane();
    layout.setPrefSize(800, 500);
    layout.setPadding(new Insets(50, 20, 20, 20));

    VBox vertBox = new VBox(5);
    vertBox.setAlignment(Pos.TOP_CENTER);

    layout.setCenter(vertBox);

    Text createCertificate = new Text("Create a certificate");
    createCertificate.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
//ALL INPUT FIELDS ARE BELOW HERE
    Insets inputInset = new Insets(0, 20, 0, 20);

    //INPUT FIELDS NAME
    HBox name = new HBox(10);
    name.setPadding(new Insets(20, 20, 0, 20));
    name.setAlignment(Pos.CENTER);
    name.setTranslateX(-12.5);

    Label labelName = new Label("CertificateID - For example: 1234");
    labelName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
    TextField inputID = new TextField();

    //INPUT FIELDS INTRODUCTIONTEXT
    HBox gradeSpinner = new HBox(10);
    gradeSpinner.setPadding(inputInset);
    gradeSpinner.setAlignment(Pos.CENTER);
    gradeSpinner.setTranslateX(-12.5);

    Label labelInfo = new Label("Grade: ");
    labelInfo.setFont(Font.font("verdana", FontWeight.BOLD, 14));
    Spinner gradSpinner = new Spinner<>(1,10,1);

    Label labelLevel = new Label("Level: ");
    labelLevel.setFont(Font.font("verdana", FontWeight.BOLD, 14));

    //INPUT FIELDS INTRODUCTIONTEXT
    HBox reviewer = new HBox(10);
    reviewer.setPadding(inputInset);
    reviewer.setAlignment(Pos.CENTER);

    Label labelReviewer = new Label("Status: ");
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
    gradeSpinner.getChildren().addAll(labelInfo, gradSpinner);
    reviewer.getChildren().addAll(labelReviewer, reviewerBox);

//SAVE BUTTON
    Button btnSave = new Button("Save");

    btnSave.setOnAction((event2) -> {
        try {
            Connection conn = DatabaseConnection.getConnection();

                Statement stmt = conn.createStatement();
                String SQL = "INSERT INTO Certificate VALUES ('" + inputID.getText() + "', '" + gradSpinner.getValue() + "', '" + reviewerBox.getValue() + "')";
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
            .addAll(createCertificate, name, gradeSpinner, reviewer, btnSave, backButton);

    return layout;
}
}