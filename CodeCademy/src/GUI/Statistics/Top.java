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
        Text welcome = new Text("Statistics:");
        welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        Text underMessage = new Text("See the statistics below.");
        underMessage.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));

        HBox topBoth = new HBox(10);
        topBoth.setAlignment(Pos.CENTER);
        topBoth.setPadding(new Insets(20, 20, 0, 20));
        HBox topGender = new HBox(10);
        topGender.setAlignment(Pos.CENTER);
        topGender.setPadding(new Insets(20, 85, 0, 20));

//WEBCAST
        VBox vboxWebcast = new VBox();

        vboxWebcast.setPadding(new Insets(20, 20, 0, 20));

        Label labelWebcast = new Label("Top 3 webcasts:");
        labelWebcast.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        Label infoWebcast = new Label();

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String SQL = "SELECT TOP 3 Title, NumberOfViews FROM Webcast\n"
                    + "ORDER BY NumberOfViews DESC";
            ResultSet rs = stmt.executeQuery(SQL);

            String webcastStr = "";
            while (rs.next()) {

                webcastStr += rs.getString("Title") + " - " + rs.getString("NumberOfViews") + " View(s)\n";

                infoWebcast.setText(webcastStr);
                infoWebcast.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

//COURSE
        VBox vboxCourse = new VBox();

        vboxCourse.setPadding(new Insets(20, 20, 0, 20));
        Label labelCourse = new Label("Top 3 finished courses:");
        labelCourse.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        Label infoCourse = new Label("1234");

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String SQL = "SELECT TOP 3 CourseName, COUNT(CertificateId) AS Certificates FROM REGISTRATION\n"
                    + "GROUP BY CourseName\n"
                    + "ORDER BY Certificates DESC";
            ResultSet rs = stmt.executeQuery(SQL);

            String courseStr = "";
            while (rs.next()) {

                courseStr += rs.getString("CourseName") + " - " + rs.getString("Certificates") + " Certificate(s) received\n";

                infoCourse.setText(courseStr);
                infoCourse.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //FEMALE
        VBox vboxFemale = new VBox();

        vboxFemale.setPadding(new Insets(20, 70, 0, 20));
        Label labelFemale = new Label("Top 3 finished courses - Female:");
        labelFemale.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        Label infoFemale = new Label("1234");

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String SQL = "SELECT TOP 3  r.CourseName, c.Gender,\n"
                    + "    COUNT(r.CertificateID) AS 'Finished', \n"
                    + "    COUNT(r.CursistEmail) AS 'ALL' \n"
                    + "FROM Registration r \n"
                    + "INNER JOIN Cursist c ON r.CursistEmail = c.Email\n"
                    + "Where c.Gender = 'f'\n"
                    + "GROUP BY c.Gender, r.CourseName\n"
                    + "ORDER BY Finished DESC;";
            
            

            ResultSet rs = stmt.executeQuery(SQL);


            String femaleStr = "";
            while (rs.next()) {
                int iFinished = rs.getInt("Finished");
                int iAll = rs.getInt("ALL");

                if (iFinished > 0) {
                    float percentage = (float) iFinished / iAll * 100;
                    String result = rs.getString("CourseName") + " - " + String.format(iFinished + "/" + iAll + " (%.02f", percentage) + "%)\n";
                    femaleStr += result;
                }
            }

            if (femaleStr.isEmpty()) {
                infoFemale.setText("-");
            } else {
                infoFemale.setText(femaleStr);
            }

            infoFemale.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//MALE
        //FEMALE    
        VBox vboxMale = new VBox();

        vboxMale.setPadding(new Insets(20, 20, 0, 20));
        Label labelMale = new Label("Top 3 finished courses - Male:");
        labelMale.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        Label infoMale = new Label("1234");

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String SQL = "SELECT TOP 3  r.CourseName, c.Gender,\n"
                    + "    COUNT(r.CertificateID) AS 'Finished', \n"
                    + "    COUNT(r.CursistEmail) AS 'ALL' \n"
                    + "FROM Registration r \n"
                    + "INNER JOIN Cursist c ON r.CursistEmail = c.Email\n"
                    + "Where c.Gender = 'm'\n"
                    + "GROUP BY c.Gender, r.CourseName\n"
                    + "ORDER BY Finished DESC;";

            ResultSet rs = stmt.executeQuery(SQL);


            String maleStr = "";
            while (rs.next()) {
                int iFinished = rs.getInt("Finished");
                int iAll = rs.getInt("ALL");

                if (iFinished > 0) {
                    float percentage = (float) iFinished / iAll * 100;
                    String result = rs.getString("CourseName") + " - " + String.format(iFinished + "/" + iAll + " (%.02f", percentage) + "%)\n";
                  maleStr += result;
                }
            }

            if (maleStr.isEmpty()) {
                infoMale.setText("-");
            } else {
                infoMale.setText(maleStr);
            }

            infoMale.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, 14));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //ADDING TO VBOX
        topBoth.getChildren().addAll(vboxWebcast, vboxCourse);
        topGender.getChildren().addAll(vboxFemale, vboxMale);

        
        vboxCourse.getChildren().addAll(labelCourse, infoCourse);
        vboxWebcast.getChildren().addAll(labelWebcast, infoWebcast);
        vboxFemale.getChildren().addAll(labelFemale, infoFemale);
        vboxMale.getChildren().addAll(labelMale, infoMale);
        vertBox.getChildren().addAll(welcome, underMessage, topBoth, topGender);

        return layout;
    }
}
