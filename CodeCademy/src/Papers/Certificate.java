package Papers;

import People.Employee;

public class Certificate {

    private int certificateID;
    private double grade;
    private Employee reviewer;

    public Certificate(int certificateID, double grade, Employee reviewer) {
        this.certificateID = certificateID;
        if (grade >= 1 && grade <= 10) {
            this.grade = grade;
        } else {
            throw new IllegalArgumentException("Grade incorrect");
        }
        this.reviewer = reviewer;
    }

    public String toString() {
        return "Certificate " + this.certificateID + ": " + grade + " - Reviewed by " + reviewer.getName();
    }

}
