/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accounts;

import DatabaseConnection.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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

/**
 *
 * @author Ordinary
 */
public class ViewCursist {

    public ViewCursist() {
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);
        Insets inputInset = new Insets(0, 20, 0, 20);

        Text createEmp = new Text("View a cursist");
        createEmp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        Text underMessage = new Text("Input the email of the cursist you want to view.");
        underMessage.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));

        //INPUT FIELDS EMAIL
        HBox email = new HBox(10);
        email.setPadding(new Insets(0, 20, 20, 20));
        email.setAlignment(Pos.CENTER);

        Label labelEmail = new Label("Email: ");
        labelEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputEmail = new TextField();

        //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        email.getChildren().addAll(labelEmail, inputEmail);

        //SAVE BUTTON
        Button viewEmployee = new Button("View");

        //BUTTON ON ACTION -> VIEWS INPUTTED USER FROM DATABASE
        viewEmployee.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                //  Cursist temp = new Cursist(inputEmail.getText(), inputName.getText(), datePicker.getValue(), genderChar, inputStreet.getText(), inputPostal.getText(), inputCity.getText(), inputCountry.getText());
                //   System.out.println(temp);
                //SELECT * FROM Cursist where Email = 'inputEmail.getText()';
                Statement stmt = conn.createStatement();
                String SQL = "SELECT * FROM CURSIST WHERE Email = '" + inputEmail.getText() + "'";
                ResultSet rs = stmt.executeQuery(SQL);

                //ATTRIBUTES AND VALUES
                while (rs.next()) {
                    String nameStr = rs.getString("Name");
                    String emailStr = rs.getString("Email");
                    String dobStr = rs.getString("DateOfBirth");
                    String genderStr = rs.getString("Gender");
                    String streetStr = rs.getString("Street");
                    String postalStr = rs.getString("PostalCode");
                    String cityStr = rs.getString("City");
                    String countryStr = rs.getString("Country");

                    //VIEW AFTER PRESSING BUTTON
                    vertBox.getChildren().removeAll(createEmp, underMessage, email, viewEmployee);

                    Text cursistInfo = new Text("Cursist information:");
                    cursistInfo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

                    //INPUT FIELDS NAME
                    HBox name = new HBox(10);
                    name.setPadding(new Insets(20, 20, 0, 20));
                    name.setAlignment(Pos.CENTER);

                    Label labelName = new Label("Name: ");
                    labelName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
                    Label infoName = new Label(nameStr);

                    //INPUT FIELDS EMAIL
                    HBox email2 = new HBox(10);
                    email2.setPadding(inputInset);
                    email2.setAlignment(Pos.CENTER);

                    Label labelEmail2 = new Label("Email: ");
                    labelEmail2.setFont(Font.font("verdana", FontWeight.BOLD, 14));
                    Label infoEmail = new Label(emailStr);
                    //INPUT FIELDS DOB
                    HBox DOB = new HBox(10);
                    DOB.setPadding(inputInset);
                    DOB.setAlignment(Pos.CENTER);

                    DOB.setTranslateX(-12.5);
                    Label labelDOB = new Label("Date of birth:");
                    labelDOB.setFont(Font.font("verdana", FontWeight.BOLD, 14));
                    Label infoDOB = new Label(dobStr);

                    //INPUT GENDER
                    HBox gender = new HBox(10);
                    gender.setPadding(inputInset);
                    gender.setAlignment(Pos.CENTER);

                    Label labelGender = new Label("Gender:");
                    labelGender.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoGender = new Label(genderStr);

                    //INPUT STREET, POSTAL, CITY, COUNTRY
                    //STREET
                    HBox street = new HBox(10);
                    street.setPadding(inputInset);
                    street.setAlignment(Pos.CENTER);
                    Label labelStreet = new Label("Street:");
                    labelStreet.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoStreet = new Label(streetStr);

                    //POSTAL
                    HBox postal = new HBox(10);
                    postal.setPadding(inputInset);
                    postal.setAlignment(Pos.CENTER);

                    Label labelPostal = new Label("Postal Code:");
                    labelPostal.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoPostal = new Label(postalStr);
                    //CITY
                    HBox city = new HBox(10);
                    city.setPadding(inputInset);
                    city.setAlignment(Pos.CENTER);

                    Label labelCity = new Label("City:");
                    labelCity.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoCity = new Label(cityStr);
                    //COUNTRY
                    HBox country = new HBox(10);
                    country.setPadding(inputInset);
                    country.setAlignment(Pos.CENTER);

                    Label labelCountry = new Label("Country:");
                    labelCountry.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoCountry = new Label(countryStr);
                    //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
                    name.getChildren().addAll(labelName, infoName);
                    email2.getChildren().addAll(labelEmail2, infoEmail);
                    DOB.getChildren().addAll(labelDOB, infoDOB);
                    gender.getChildren().addAll(labelGender, infoGender);
                    street.getChildren().addAll(labelStreet, infoStreet);
                    postal.getChildren().addAll(labelPostal, infoPostal);
                    city.getChildren().addAll(labelCity, infoCity);
                    country.getChildren().addAll(labelCountry, infoCountry);

                    vertBox.getChildren().addAll(cursistInfo, name, email2, DOB, gender, street, postal, city, country);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        );

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(createEmp, underMessage, email, viewEmployee);

        return layout;
    }
}
