package People;

public class Employee {

    private String name;
    private String email;

    public Employee(String name, String email) {
        this.name = name;
        this.email = formatEmail(email);
    }

    public String getName() {
        return this.name;
    }

    public String formatEmail(String email) {
        String[] parts = email.split("[@.]");

        
        
        try {

            for (int j = 0; j < parts.length; j++) {
                if (parts[j].length() < 1) {
                    System.out.println("Error");
                    throw new IllegalArgumentException(this.name + " has an incorrect email");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Email incorrect");
        }

        return email;
    }
}
