/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accounts;

import DatabaseConnection.DatabaseConnection;
import java.sql.*;

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

        //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        email.getChildren().addAll(labelEmail, emailMenu);

        //SAVE BUTTON
        Button viewEmployee = new Button("View");

        //BUTTON ON ACTION -> VIEWS INPUTTED USER FROM DATABASE
        viewEmployee.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                String SQL = "SELECT * FROM CURSIST WHERE Email = '" + emailMenu.getValue() + "'";
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
//FONT

                    //INPUT FIELDS NAME
                    HBox name = new HBox(10);
                    name.setPadding(new Insets(20, 20, 0, 20));
                    name.setAlignment(Pos.CENTER);

                    Label labelName = new Label("Name: ");
                    labelName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
                    Label infoName = new Label(nameStr);
                    infoName.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
                    //INPUT FIELDS EMAIL
                    HBox email2 = new HBox(10);
                    email2.setPadding(inputInset);
                    email2.setAlignment(Pos.CENTER);

                    Label labelEmail2 = new Label("Email: ");
                    labelEmail2.setFont(Font.font("verdana", FontWeight.BOLD, 14));
                    Label infoEmail = new Label(emailStr);
                    infoEmail.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
                    //INPUT FIELDS DOB
                    HBox DOB = new HBox(10);
                    DOB.setPadding(inputInset);
                    DOB.setAlignment(Pos.CENTER);

                    DOB.setTranslateX(-12.5);
                    Label labelDOB = new Label("Date of birth:");
                    labelDOB.setFont(Font.font("verdana", FontWeight.BOLD, 14));
                    Label infoDOB = new Label(dobStr);
                    infoDOB.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));

                    //INPUT GENDER
                    HBox gender = new HBox(10);
                    gender.setPadding(inputInset);
                    gender.setAlignment(Pos.CENTER);

                    Label labelGender = new Label("Gender:");
                    labelGender.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoGender = new Label(genderStr);
                    infoGender.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
                    //INPUT STREET, POSTAL, CITY, COUNTRY
                    //STREET
                    HBox street = new HBox(10);
                    street.setPadding(inputInset);
                    street.setAlignment(Pos.CENTER);
                    Label labelStreet = new Label("Street:");
                    labelStreet.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoStreet = new Label(streetStr);
                    infoStreet.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
                    //POSTAL
                    HBox postal = new HBox(10);
                    postal.setPadding(inputInset);
                    postal.setAlignment(Pos.CENTER);

                    Label labelPostal = new Label("Postal Code:");
                    labelPostal.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoPostal = new Label(postalStr);
                    infoPostal.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
                    //CITY
                    HBox city = new HBox(10);
                    city.setPadding(inputInset);
                    city.setAlignment(Pos.CENTER);

                    Label labelCity = new Label("City:");
                    labelCity.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoCity = new Label(cityStr);
                    infoCity.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
                    //COUNTRY
                    HBox country = new HBox(10);
                    country.setPadding(inputInset);
                    country.setAlignment(Pos.CENTER);

                    Label labelCountry = new Label("Country:");
                    labelCountry.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoCountry = new Label(countryStr);
                    infoCountry.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));

                    /// ----
                    //CERTIFICATES
                    VBox certificates = new VBox(10);
                    certificates.setPadding(inputInset);
                    certificates.setAlignment(Pos.CENTER);
                    Label dash = new Label("-------------------------------------------------------------------------");
                    Label labelCertificates = new Label("Certificates:");
                    labelCertificates.setFont(Font.font("verdana", FontWeight.BOLD, 14));

                    Label infoCertificates = new Label();
                    //SQL CODE FOR CERTIFICATES
                    try {
                        Connection conn2 = DatabaseConnection.getConnection();
                        Statement stmt2 = conn2.createStatement();
                        String SQL2 = "SELECT CertificateId, CourseName FROM Registration WHERE CursistEmail = '" + emailMenu.getValue() + "'";
                        ResultSet rs2 = stmt2.executeQuery(SQL2);

                        //ATTRIBUTES AND VALUES
                        String certificateStr = "";
                        while (rs2.next()) {
                            if (rs2.getString("CertificateId") != null) {

                                certificateStr += rs2.getString("CertificateId") + " - " + rs2.getString("CourseName") + "\n";
                            }

                            infoCertificates.setText(certificateStr);
                            infoCertificates.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
                        }
                    } catch (SQLException ex) {

                    }

                    //ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
                    name.getChildren().addAll(labelName, infoName);
                    email2.getChildren().addAll(labelEmail2, infoEmail);
                    DOB.getChildren().addAll(labelDOB, infoDOB);
                    gender.getChildren().addAll(labelGender, infoGender);
                    street.getChildren().addAll(labelStreet, infoStreet);
                    postal.getChildren().addAll(labelPostal, infoPostal);
                    city.getChildren().addAll(labelCity, infoCity);
                    country.getChildren().addAll(labelCountry, infoCountry);
                    certificates.getChildren().addAll(dash, labelCertificates, infoCertificates);

                    vertBox.getChildren().addAll(cursistInfo, name, email2, DOB, gender, street, postal, city, country, certificates);

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
