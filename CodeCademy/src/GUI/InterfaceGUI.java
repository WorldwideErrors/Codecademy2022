package GUI;

import GUI.Accounts.CreateCursist;
import GUI.Accounts.DeleteCursist;
import GUI.Accounts.UpdateCursist;
import GUI.Accounts.ViewCursist;
import GUI.Courses.CreateCourse;
import GUI.Courses.ViewCourses;
<<<<<<< Updated upstream
import GUI.Registrations.CreateRegistration;
import GUI.Registrations.DeleteRegistration;
import GUI.Registrations.ViewRegistration;
import GUI.Statistics.Top;

=======
import GUI.Registrations.AddRegistration;
>>>>>>> Stashed changes
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
//CURSIST VIEWS
        CreateCursist cursistCreateView = new CreateCursist();
        ViewCursist cursistViewView = new ViewCursist();
        UpdateCursist cursistUpdateView = new UpdateCursist();
        DeleteCursist cursistDeleteView = new DeleteCursist();
//COURSE VIEWS
        CreateCourse courseCreateView = new CreateCourse();
        ViewCourses courseViewView = new ViewCourses();
//REGISTRATION VIEWS
        CreateRegistration createRegistrationView = new CreateRegistration();
        DeleteRegistration deleteRegistrationView = new DeleteRegistration();
        ViewRegistration viewRegistrationView = new ViewRegistration();
        //CERTIFICATE VIEWS
//STATISTIC VIEWS
        Top topView = new Top();
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

//DROPDOWN ACCOUNT
        MenuItem create = new MenuItem("Create Cursist");
        MenuItem view = new MenuItem("View Cursist");
        MenuItem update = new MenuItem("Update Cursist");
        MenuItem delete = new MenuItem("Delete Cursist");
        MenuButton cursistMenu = new MenuButton("Cursist", null, create, view, update, delete);

        cursistMenu.setTranslateX(90);
        cursistMenu.setPrefSize(104, 30);
//DROPDOWN COURSE
        MenuItem createC = new MenuItem("Create Course");
        MenuItem viewC = new MenuItem("View Course");
        MenuItem updateC = new MenuItem("Update Course");
        MenuItem deleteC = new MenuItem("Delete Course");
        MenuButton courseMenu = new MenuButton("Course", null, createC, viewC, updateC, deleteC);

        courseMenu.setTranslateX(90);
        courseMenu.setPrefSize(104, 30);
//DROPDOWN REGISTRATIONS
        MenuItem createR = new MenuItem("Create Registration");
        MenuItem viewR = new MenuItem("View Registration");
        MenuItem updateR = new MenuItem("Update Registration");
        MenuItem deleteR = new MenuItem("Delete Registration");
        MenuButton registrationMenu = new MenuButton("Registration", null, createR, viewR, updateR, deleteR);

        registrationMenu.setTranslateX(90);
        registrationMenu.setPrefSize(104, 30);
//DROPDOWN CERTIFICATES
        MenuItem createCR = new MenuItem("Create Certificate");
        MenuItem viewCR = new MenuItem("View Certificate");
        MenuItem updateCR = new MenuItem("Update Certificate");
        MenuItem deleteCR = new MenuItem("Delete Certificate");
        MenuButton certificateMenu = new MenuButton("Registration", null, createCR, viewCR, updateCR, deleteCR);

        certificateMenu.setTranslateX(90);
        certificateMenu.setPrefSize(104, 30);

//DROPDOWN STATISTICS
        MenuItem viewTop = new MenuItem("View top items");
        MenuButton statMenu = new MenuButton("Statistics", null, viewTop);
        statMenu.setTranslateX(90);
        statMenu.setPrefSize(104, 30);
//HBOX FOR MENUBUTTON
        HBox center = new HBox(5);
        center.getChildren().addAll(cursistMenu, courseMenu, registrationMenu, certificateMenu, statMenu);

//CENTER FOR PLACING THE MENUBUTTON
        centerView.setCenter(center);

//SET ON ACTION CURSIST
        create.setOnAction((event) -> {
            Scene accountScene = new Scene(cursistCreateView.getView());
            stage.setScene(accountScene);
        });

        view.setOnAction((event2) -> {
            Scene accountViewScene = new Scene(cursistViewView.getView());
            stage.setScene(accountViewScene);
        });

        update.setOnAction((event3) -> {
            Scene accountUpdateScene = new Scene(cursistUpdateView.getView());
            stage.setScene(accountUpdateScene);
        });

        delete.setOnAction((event4) -> {
            Scene accountDeleteScene = new Scene(cursistDeleteView.getView());
            stage.setScene(accountDeleteScene);
        });
        //SET ON ACTION COURSE
        createC.setOnAction((eventC) -> {
            Scene createCourseScene = new Scene(courseCreateView.getView());
            stage.setScene(createCourseScene);
        });

        viewC.setOnAction((eventC2) -> {
                Scene courseViewScene = new Scene(courseViewView.getView());
                stage.setScene(courseViewScene);
        });

        updateC.setOnAction((eventC3) -> {

        });

        deleteC.setOnAction((eventC4) -> {

        });
        //SET ON ACTION REGISTRATION   
        createR.setOnAction((eventR) -> {
            Scene createRegistrationScene = new Scene(createRegistrationView.getView());
            stage.setScene(createRegistrationScene);
        });

        viewR.setOnAction((eventR2) -> {
            Scene viewRegistrationScene = new Scene(viewRegistrationView.getView());
            stage.setScene(viewRegistrationScene);
        });

        updateR.setOnAction((eventR3) -> {

        });

        deleteR.setOnAction((eventR4) -> {
            Scene deleteRegistrationScene = new Scene(deleteRegistrationView.getView());
            stage.setScene(deleteRegistrationScene);
        });
        //SET ON ACTION CERTIFICATE
        createCR.setOnAction((eventCR) -> {

        });

        viewCR.setOnAction((eventCR2) -> {

        });

        updateCR.setOnAction((eventCR3) -> {

        });

        deleteCR.setOnAction((eventCR4) -> {

        });
        //SET ON ACTION STATISTICS
        viewTop.setOnAction((eventVT) ->{
           Scene viewTopScene = new Scene(topView.getView());
           stage.setScene(viewTopScene);
        });
        //OTHER
        mainPane.setTop(Welcome);
        mainPane.setCenter(centerView);

        //SCENE
        Scene scene = new Scene(mainPane, 800, 500);

        //STAGE 
        stage.setTitle("CodeCademy");
        stage.setScene(scene);
        stage.show();
    }

}
