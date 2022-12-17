package GUI;

import GUI.Accounts.ViewAccount;
import GUI.Accounts.CreateAccount;
import GUI.Accounts.DeleteAccount;
import GUI.Accounts.UpdateAccount;
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
        ViewAccount accountViewView = new ViewAccount();
        UpdateAccount accountUpdateView = new UpdateAccount();
        DeleteAccount accountDeleteView = new DeleteAccount();

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

        //IDK
        BorderPane centerView = new BorderPane();
        mainPane.setPadding(new Insets(20, 20, 20, 20));
        centerView.setPadding(new Insets(20, 20, 20, 20));

        //BUTTONS
        Button createAccount = new Button("Create Account");
        Button viewAccount = new Button("View Account");
        Button deleteAccount = new Button("Delete Account");
        Button updateAccount = new Button("Update Account");

        createAccount.setPrefWidth(145);
        viewAccount.setPrefWidth(145);
        deleteAccount.setPrefWidth(145);
        updateAccount.setPrefWidth(145);

        createAccount.setTranslateX(200);
        viewAccount.setTranslateX(-200);
        deleteAccount.setTranslateX(200);
        updateAccount.setTranslateX(-200);

//VBOX LEFT CENTER FOR PLACING BUTTONS
        VBox leftCenter = new VBox(5);
        leftCenter.getChildren().addAll(createAccount, deleteAccount);
//VBOX RIGHT CENTER FOR PLACING BUTTONS
        VBox rightCenter = new VBox(5);
        rightCenter.getChildren().addAll(viewAccount, updateAccount);
//CENTER FOR PLACING THE VBOXES THAT CONTAIN THE BUTTONS
        centerView.setLeft(leftCenter);
        centerView.setRight(rightCenter);

//SET ON ACTION
        createAccount.setOnAction((event) -> {
            Scene accountScene = new Scene(accountView.getView());
            stage.setScene(accountScene);
        });

        viewAccount.setOnAction((event2) -> {
            Scene accountViewScene = new Scene(accountViewView.getView());
            stage.setScene(accountViewScene);
        });

        updateAccount.setOnAction((event3) -> {
            Scene accountUpdateScene = new Scene(accountUpdateView.getView());
            stage.setScene(accountUpdateScene);
        });

        deleteAccount.setOnAction((event4) -> {
            Scene accountDeleteScene = new Scene(accountDeleteView.getView());
            stage.setScene(accountDeleteScene);
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
