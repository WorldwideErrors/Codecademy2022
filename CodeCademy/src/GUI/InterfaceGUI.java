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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

//DROPDOWN
        MenuItem create = new MenuItem("Create Account");
        MenuItem view = new MenuItem("View Account");
        MenuItem update = new MenuItem("Update Account");
        MenuItem delete = new MenuItem("Delete Account");
        MenuButton accountMenu = new MenuButton("Account", null, create, view, update, delete);

        accountMenu.setTranslateX(200);
        accountMenu.setPrefSize(104, 30);

//HBOX FOR MENUBUTTON
        HBox center = new HBox(5);
        center.getChildren().addAll(accountMenu);

//CENTER FOR PLACING THE MENUBUTTON
        centerView.setCenter(center);

//SET ON ACTION ACCOUNT
        create.setOnAction((event) -> {
            Scene accountScene = new Scene(accountView.getView());
            stage.setScene(accountScene);
        });

        view.setOnAction((event2) -> {
            Scene accountViewScene = new Scene(accountViewView.getView());
            stage.setScene(accountViewScene);
        });

        update.setOnAction((event3) -> {
            Scene accountUpdateScene = new Scene(accountUpdateView.getView());
            stage.setScene(accountUpdateScene);
        });

        delete.setOnAction((event4) -> {
            Scene accountDeleteScene = new Scene(accountDeleteView.getView());
            stage.setScene(accountDeleteScene);
        });

        //SET ON ACTION COURSE
        //SET ON ACTION OTHER
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

//BUTTONS in case of need
//        Button createAccount = new Button("Create Account");
//        Button viewAccount = new Button("View Account");
//        Button deleteAccount = new Button("Delete Account");
//        Button updateAccount = new Button("Update Account");
// BUTTON ATTRIBUTES
//        createAccount.setPrefWidth(145);
//        viewAccount.setPrefWidth(145);
//        deleteAccount.setPrefWidth(145);
//        updateAccount.setPrefWidth(145);
//
//        createAccount.setTranslateX(200);
//        viewAccount.setTranslateX(-200);
//        deleteAccount.setTranslateX(200);
//        updateAccount.setTranslateX(-200);
