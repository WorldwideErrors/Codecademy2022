package People;

public class Employee {

    private String name;
    private String email;

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return this.name;
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
}
