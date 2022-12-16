/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import People.Employee;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

/**
 *
 * @author Ordinary
 */
public class CreateAccount {

    private Employee employee;

    public CreateAccount() {
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 500);

        Button createCursist = new Button("Create cursist");
        Button createEmployee = new Button("Create employee");

        layout.setLeft(createCursist);
        layout.setRight(createEmployee);

        createCursist.setTranslateY(200);
        createEmployee.setTranslateY(200);

        createCursist.setTranslateX(200);
        createEmployee.setTranslateX(-200);

//VBOX LEFT CENTER
        VBox leftCenter = new VBox();
        leftCenter.getChildren().addAll(createCursist);

//VBOX RIGHT CENTER
        VBox rightCenter = new VBox();
        rightCenter.getChildren().addAll(createEmployee);
//CENTER
        layout.setLeft(leftCenter);
        layout.setRight(rightCenter);

        //BUTTON EVENT
        createEmployee.setOnAction((event) -> {
            VBox vertBox = new VBox(5);
            vertBox.setAlignment(Pos.TOP_CENTER);

            layout.setRight(null);
            layout.setLeft(null);

            layout.setCenter(vertBox);

            Text createEmp = new Text("Create an employee");
            createEmp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

            //INPUT
            HBox name = new HBox(10);
            name.setPadding(new Insets(20, 20, 0, 20));
            name.setAlignment(Pos.CENTER);

            Label labelName = new Label("Name: ");
            labelName.setFont(Font.font("verdana", FontWeight.BOLD, 14));
            TextField inputName = new TextField();

            HBox email = new HBox(10);
            email.setPadding(new Insets(0, 20, 20, 20));
            email.setAlignment(Pos.CENTER);

            Label labelEmail = new Label("Email: ");
            labelEmail.setFont(Font.font("verdana", FontWeight.BOLD, 14));
            TextField inputEmail = new TextField();

            name.getChildren().addAll(labelName, inputName);
            email.getChildren().addAll(labelEmail, inputEmail);

            Button saveEmployee = new Button("Save");
            saveEmployee.setOnAction((event2) -> {
                new Employee(inputName.getText(), inputEmail.getText());
            });

            vertBox.getChildren().addAll(createEmp, name, email, saveEmployee);
            layout.setTop(vertBox);

        });

        return layout;
    }
}
