
package GUI.Accounts;

import DatabaseConnection.DatabaseConnection;
import GUI.InterfaceGUI;
import People.Cursist;
import java.sql.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UpdateCursist {

    public UpdateCursist() {
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);
        layout.setPadding(new Insets(50, 20, 20, 20));

        VBox vertBox = new VBox(5);
        vertBox.setAlignment(Pos.TOP_CENTER);

        layout.setRight(null);
        layout.setLeft(null);
        layout.setCenter(vertBox);
//TITLE
        Text createEmp = new Text("Update a cursist");
        createEmp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        Text underMessage = new Text("Input the email of the cursist you want to update.");
        underMessage.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));        
//OLD EMAIL
        HBox email = new HBox(10);
        email.setPadding(new Insets(0, 51, 0, 20));
        email.setAlignment(Pos.CENTER);

        Label labelEmail = new Label("Old email: ");
        labelEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputEmail = new TextField();        
//NEW EMAIL
        HBox newEmail = new HBox(10);
        newEmail.setPadding(new Insets(0, 39, 0, 0));
        newEmail.setAlignment(Pos.CENTER);

        Label labelNewEmail = new Label("New email: ");
        labelNewEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TextField inputNewEmail = new TextField();        
//ADD LABELS + TEXTFIELDS TO RESPECTIVE HBOX
        email.getChildren().addAll(labelEmail, inputEmail);
        newEmail.getChildren().addAll(labelNewEmail, inputNewEmail);        
//SAVE BUTTON
        Button saveEmployee = new Button("Update");        
//BUTTON ON ACTION -> SAVES INPUTTED USER IN DATABASE
        saveEmployee.setOnAction((event2) -> {
            try {
                Connection conn = DatabaseConnection.getConnection();

                Cursist temp = new Cursist(inputNewEmail.getText());

                Statement stmt = conn.createStatement();
                String SQL = "UPDATE Cursist SET Email = '" + inputNewEmail.getText() + "' WHERE Email = '" + inputEmail.getText() + "' ";

                inputEmail.clear();
                inputNewEmail.clear();
                stmt.executeUpdate(SQL);
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
                .addAll(createEmp, underMessage, email, newEmail, saveEmployee, backButton);

        return layout;
    }
}
