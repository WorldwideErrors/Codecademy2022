package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InterfaceGUI extends Application {

    @Override
    public void start(Stage stage) {
//VIEWS
        CreateAccount accountView = new CreateAccount();

        //WELCOME SCENE
        BorderPane mainPane = new BorderPane();

        //TOP WELCOME MESSAGE
        VBox Welcome = new VBox();
        Welcome.setAlignment(Pos.TOP_CENTER);
        Text welcomeMessage = new Text("CodeCademy");
        welcomeMessage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));
        Text underMessage = new Text("What would you like to do?");
        underMessage.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        Welcome.getChildren().addAll(welcomeMessage, underMessage);

        //ETC
        BorderPane centerView = new BorderPane();
        mainPane.setPadding(new Insets(20, 20, 20, 20));
        centerView.setPadding(new Insets(20, 20, 20, 20));

        //BUTTONS
        Button createAccount = new Button("Create Account");
        Button viewAccount = new Button("View Account");

        createAccount.setTranslateX(200);
        viewAccount.setTranslateX(-200);

//VBOX LEFT CENTER
        VBox leftCenter = new VBox();
        leftCenter.getChildren().addAll(createAccount);
//VBOX RIGHT CENTER
        VBox rightCenter = new VBox();
        rightCenter.getChildren().addAll(viewAccount);
//CENTER
        centerView.setLeft(leftCenter);
        centerView.setRight(rightCenter);

//SET ON ACTION
        createAccount.setOnAction((event) -> {
            Scene accountScene = new Scene(accountView.getView());
            stage.setScene(accountScene);
        });

        //OTHER
        mainPane.setTop(Welcome);
        mainPane.setCenter(centerView);
        Scene scene = new Scene(mainPane, 800, 500);

        //STAGE 
        stage.setTitle("CodeCademy");
        stage.setScene(scene);
        stage.show();
    }

}
