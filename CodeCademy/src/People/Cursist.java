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

        String formatted = formatPostalCode(postalCode);
        if (formatted.isEmpty()) {
            throw new IllegalArgumentException("Invalid postal code");
        }
        this.postalCode = formatted;

        this.city = city;
        this.country = country;

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
        // trim postal code
        postalCode = postalCode.trim();
        postalCode = postalCode.toUpperCase();
        // check if postal code 6 letters
        if (postalCode.length() != 6) {
            return "";
        }

        // check if first != 0
        if (postalCode.charAt(0) == '0') {
            return "";
        }

        // check if postalcode contains numbers and letters only
        for (int i = 1; i < postalCode.length(); i++) {
            char c = postalCode.charAt(i);
            if (!Character.isDigit(c) && !Character.isUpperCase(c)) {
                return "";
            }
        }

        // final format
        return postalCode.substring(0, 4) + " " + postalCode.substring(4);

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
