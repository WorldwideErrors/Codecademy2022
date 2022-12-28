package People;

import Curriculum.Module;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Cursist {

    private ArrayList<Module> modules;
    private String email;
    private String name;
    private LocalDate dateOfBirth;
    private char gender;

    private String street;
    private String postalCode;
    private String city;
    private String country;

    public Cursist(String email, String name, LocalDate dateOfBirth, char gender, String street, String postalCode, String city, String country) {
        this.email = email;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.street = street;
        this.postalCode = formatPostalCode(postalCode);
        this.city = city;
        this.country = country;

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
    
       @Override
    public String toString() {
        return "Name: " + name + "\n"
            + "Email: " + email + "\n"
            + "Date of birth: " + dateOfBirth + "\n"
            + "Gender: " + gender + "\n"
            + "Street: " + street + "\n"
            + "Postal code: " + postalCode + "\n"
            + "City: " + city + "\n"
            + "Country: " + country;
    }

}
