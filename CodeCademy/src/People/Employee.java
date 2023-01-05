package People;

public class Employee {

    private String name;
    private String email;

    public Employee(String name, String email) {
        this.name = name;
        if (formatEmail(email) == true) {
            this.email = email;
        } else {
            throw new IllegalArgumentException(this.name + " has a incorrect email.");
        }
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return email;
    }

    public Boolean formatEmail(String email) {
        String[] parts = email.split("[@.]");

        try {

            if (!email.contains("@") || !email.contains(".")) {
                throw new IllegalArgumentException();
            }

            for (int j = 0; j < parts.length; j++) {
                if (parts[j].length() < 1) {
                    return false;
                }
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    @Override
    public String toString() {
        return this.name + ": " + this.email;
    }
}

