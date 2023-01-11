/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Statistics;

import DatabaseConnection.DatabaseConnection;
import java.sql.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
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
public class Top {

    public Top() {

    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setCenter(vertBox);

        //TOP TITLE
        Text welcome = new Text("Top 3 most viewed:");
        welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        Text underMessage = new Text("Select what you want to view.");
        underMessage.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));

        HBox topBoth = new HBox(10);
        topBoth.setAlignment(Pos.CENTER);
        topBoth.setPadding(new Insets(20, 20, 0, 20));

//WEBCAST
        VBox vboxWebcast = new VBox();

        vboxWebcast.setPadding(new Insets(20, 20, 0, 20));

        Label labelWebcast = new Label("Top 3 webcasts:");
        labelWebcast.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        Label infoWebcast = new Label();

//COURSE
        VBox vboxCourse = new VBox();

        vboxCourse.setPadding(new Insets(20, 20, 0, 20));
        Label labelCourse = new Label("Top 3 courses:");
        labelCourse.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        Label infoCourse = new Label("1234");

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String SQL = "SELECT TOP 3 CourseName, COUNT(CourseName) AS Students FROM REGISTRATION\n"
                    + "GROUP BY CourseName\n"
                    + "ORDER BY Students DESC";
            ResultSet rs = stmt.executeQuery(SQL);

            String courseStr = "";
            while (rs.next()) {

                courseStr += rs.getString("CourseName") + " - " + rs.getString("Students") + " Registrations\n";

                infoCourse.setText(courseStr);
                infoCourse.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //ADDING TO VBOX
        topBoth.getChildren().addAll(vboxWebcast, vboxCourse);

        vboxCourse.getChildren().addAll(labelCourse, infoCourse);
        vboxWebcast.getChildren().addAll(labelWebcast, infoWebcast);

        vertBox.getChildren().addAll(welcome, underMessage, topBoth);

        return layout;
    }
}
