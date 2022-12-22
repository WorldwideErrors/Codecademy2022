package People;

public class Employee {

    private String name;
    private String email;

    public Employee(String name, String email) {
        this.name = name;
        if (formatEmail(email) != null) {
            this.name = email;
        } else {
            throw new IllegalArgumentException(this.name + " has a faulty email.");
        }
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return email;
    }

    public String formatEmail(String email) {
        String[] parts = email.split("[@.]");

        try {

            if (!email.contains("@") || !email.contains(".")) {
                throw new IllegalArgumentException();
            }

            for (int j = 0; j < parts.length; j++) {
                if (parts[j].length() < 1) {
                    throw new IllegalArgumentException();
                }
            }
            return email;
        } catch (IllegalArgumentException e) {
            return null;
        }

    }

    @Override
    public String toString() {
        return this.name + ": " + this.email;
    }
}
