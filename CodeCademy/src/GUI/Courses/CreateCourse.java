package GUI.Courses;

import java.sql.*;
import DatabaseConnection.DatabaseConnection;
import People.Cursist;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

    Text createCur = new Text("Create a course");
    createCur.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
//ALL INPUT FIELDS ARE BELOW HERE
    Insets inputInset = new Insets(0, 20, 0, 20);

    //INPUT FIELDS NAME
    HBox name = new HBox(10);
    name.setPadding(new Insets(20, 20, 0, 20));
    name.setAlignment(Pos.CENTER);

    Label labelName = new Label("Coursename: ");
    labelName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
    TextField inputCourseName = new TextField();

    //INPUT FIELDS EMAIL
    HBox courseInfo = new HBox(10);
    courseInfo.setPadding(inputInset);
    courseInfo.setAlignment(Pos.CENTER);

    Label labelInfo = new Label("Description: ");
    labelInfo.setFont(Font.font("verdana", FontWeight.BOLD, 14));
    TextArea inputInfo = new TextArea();

    //INPUT FIELDS DOB
    HBox DOB = new HBox(10);
    DOB.setPadding(inputInset);
    DOB.setAlignment(Pos.CENTER);

    DOB.setTranslateX(-12.5);
    Label labelDOB = new Label("Date of birth:");
    labelDOB.setFont(Font.font("verdana", FontWeight.BOLD, 14));
    DatePicker datePicker = new DatePicker();

    //INPUT GENDER
    HBox gender = new HBox(10);
    gender.setPadding(inputInset);
    gender.setAlignment(Pos.CENTER);

    ToggleGroup genderToggle = new ToggleGroup();

    RadioButton maleGender = new RadioButton("Male");
    RadioButton femaleGender = new RadioButton("Female");

    maleGender.setToggleGroup(genderToggle);
    femaleGender.setToggleGroup(genderToggle);

    Label labelGender = new Label("Gender:");
    labelGender.setFont(Font.font("verdana", FontWeight.BOLD, 14));

    //INPUT STREET, POSTAL, CITY, COUNTRY
    //STREET
    HBox street = new HBox(10);
    street.setPadding(inputInset);
    street.setAlignment(Pos.CENTER);
    Label labelStreet = new Label("Street:");
    labelStreet.setFont(Font.font("verdana", FontWeight.BOLD, 14));

    TextField inputStreet = new TextField();

    //POSTAL
    HBox postal = new HBox(10);
    postal.setPadding(inputInset);
    postal.setAlignment(Pos.CENTER);

    Label labelPostal = new Label("Postal Code:");
    labelPostal.setFont(Font.font("verdana", FontWeight.BOLD, 14));

    TextField inputPostal = new TextField();

    //CITY
    HBox city = new HBox(10);
    city.setPadding(inputInset);
    city.setAlignment(Pos.CENTER);

    Label labelCity = new Label("City:");
    labelCity.setFont(Font.font("verdana", FontWeight.BOLD, 14));

    TextField inputCity = new TextField();

    //COUNTRY
    HBox country = new HBox(10);
    country.setPadding(inputInset);
    country.setAlignment(Pos.CENTER);

    Label labelCountry = new Label("Country:");
    labelCountry.setFont(Font.font("verdana", FontWeight.BOLD, 14));

    TextField inputCountry = new TextField();

    //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
    name.getChildren().addAll(labelName, inputCourseName);
    courseInfo.getChildren().addAll(labelInfo, inputInfo);
    DOB.getChildren().addAll(labelDOB, datePicker);
    gender.getChildren().addAll(labelGender, maleGender, femaleGender);
    street.getChildren().addAll(labelStreet, inputStreet);
    postal.getChildren().addAll(labelPostal, inputPostal);
    city.getChildren().addAll(labelCity, inputCity);
    country.getChildren().addAll(labelCountry, inputCountry);

//SAVE BUTTON
    Button saveCursist = new Button("Save");

    saveCursist.setOnAction((event2) -> {
        try {
            Connection conn = DatabaseConnection.getConnection();

            char genderChar = 0;

            if (femaleGender.isSelected()) {
                genderChar = 'f';
            } else {
                genderChar = 'm';
            }

            Cursist temp = new Cursist(inputInfo.getText(), inputCourseName.getText(), datePicker.getValue(), genderChar, inputStreet.getText(), inputPostal.getText(), inputCity.getText(), inputCountry.getText());
            System.out.println(temp);
            Statement stmt = conn.createStatement();
            String SQL = "INSERT INTO Cursist VALUES ('" + inputInfo.getText() + "', '" + inputCourseName.getText() + "', '" + datePicker.getValue() + "', '" + genderChar + "', '" + inputStreet.getText() + "', '" + inputPostal.getText() + "', '" + inputCity.getText() + "', '" + inputCountry.getText() + "')";

            stmt.executeUpdate(SQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    });

    //ADD ALL TO VBOX
    vertBox.getChildren()
            .addAll(createCur, name, courseInfo, DOB, gender, street, postal, city, country, saveCursist);

    return layout;
}
}
