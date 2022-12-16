package Curriculum;

import Curriculum.Content;
import GUI.InterfaceGUI;
import Papers.Certificate;
import People.Cursist;
import People.Employee;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Employee person = new Employee("James", "james0091@hotmail.com");

        Application.launch(InterfaceGUI.class);
    }
}
