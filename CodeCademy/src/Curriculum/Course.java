package Curriculum;

import People.Cursist;
import java.util.ArrayList;
import java.util.HashMap;

public class Course {

    private String courseName;
    private String subject;
    private String introductionText;
    private ArrayList<Course> interestingCourses;

    public Course(String courseName, String subject) {
        this.courseName = courseName;
        this.subject = subject;
        this.interestingCourses = new ArrayList<>();
    }

}
