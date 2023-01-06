package GUI.Registrations;

import DatabaseConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ViewRegistration {

    public ViewRegistration() {

    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);
        Insets inputInset = new Insets(0, 20, 0, 20);

        Text createEmp = new Text("View a registration");
        createEmp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        Text underMessage = new Text("Who's registrations do you want to see?.");
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
        Button viewRegistration = new Button("View");

        //BUTTON ON ACTION -> VIEWS INPUTTED USER FROM DATABASE
        viewRegistration.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                Statement stmt = conn.createStatement();
                String SQL = "SELECT RegistrationDate, CourseName FROM Registration WHERE CursistEmail = '" + emailMenu.getValue() + "'";
                ResultSet rs = stmt.executeQuery(SQL);

                //ATTRIBUTES AND VALUES
                // Remove all children from the vertBox container
                vertBox.getChildren().removeAll(createEmp, underMessage, email, viewRegistration);

// Add the "Registrations:" text to the vertBox container
                Text cursistInfo = new Text("Registrations:");
                cursistInfo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
                vertBox.getChildren().add(cursistInfo);

// Loop through the result set and add the courses and dates to the vertBox container
                while (rs.next()) {
                    String courses = rs.getString("CourseName");
                    String date = rs.getString("RegistrationDate");

                    Label labelDate = new Label(date);
                    Label labelCourse = new Label(courses);
                    Label dash = new Label("========");

                    vertBox.getChildren().addAll(labelCourse, labelDate, dash);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        );

        //ADD ALL TO VBOX
        vertBox.getChildren()
                .addAll(createEmp, underMessage, email, viewRegistration);

        return layout;
    }
}
