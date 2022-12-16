/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Curriculum;

import People.Employee;
import java.util.Date;

/**
 *
 * @author Ordinary
 */
public class Module extends Content {

    private String version;
    private int serialNumber;
    private Employee employee;

    

    public Module(int itemID, String title, String version, Employee employee, int serialNumber) {
        super(itemID, title);
        this.employee = employee;
        this.serialNumber = serialNumber;
    }

    public Module(String version, int serialNumber, Employee employee, int itemID, String title, String description, Date publishedDate) {
        super(itemID, title, description, publishedDate);
        this.version = version;
        this.serialNumber = serialNumber;
        this.employee = employee;
    }



}
