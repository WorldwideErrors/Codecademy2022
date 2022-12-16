package People;

import Curriculum.Module;
import java.util.ArrayList;
import java.util.Date;

public class Cursist {

    private ArrayList<Module> modules;
    private String email;
    private String name;
    private Date dateOfBirth;
    private char gender;

    private String street;
    private String postalCode;
    private String city;
    private String country;

    public Cursist(String email, String name, Date dateOfBirth, char gender, String address, String city, String country) {
        this.email = email;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        String[] parts = address.split(" ");
        this.street = parts[0];
        this.postalCode = formatPostalCode(parts[1]);
        this.city = city;
        this.country = country;
        this.modules = new ArrayList<>();
    }

    //FOR TESTING EMAIL REMOVE
    public Cursist(String email) {
        this.email = formatEmail(email);
        this.modules = new ArrayList<>();
    }

    public String formatEmail(String email) {
        String[] parts = email.split("[@.]");

        for (String i : parts) {
            System.out.println(i);
        }
        for (int j = 0; j < parts.length; j++) {
            if (parts[j].length() < 1) {
                throw new IllegalArgumentException(this.name + " has an incorrect email.");
            }
        }

        System.out.println(email);
        return email;
    }

    public String formatPostalCode(String postalCode) {
        return "";
    }

    public void addContent(Module module) {
        this.modules.add(module);
    }


}
