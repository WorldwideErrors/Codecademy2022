/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ordinary
 */
public class Account {

    public Account() {
    }

    public Parent getView() {
        GridPane layout = new GridPane();
        layout.setPrefSize(800, 500);
        
        
        Button test = new Button("Hi");
        
        layout.add(test, 1, 0);
        
        
        
        return layout;
    }
}
