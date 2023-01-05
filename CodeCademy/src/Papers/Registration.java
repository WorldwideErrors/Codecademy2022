package Papers;

import Curriculum.Course;
import java.util.Date;

public class Registration {

    private Date registrationDate;
    private Certificate certificate;
    private Course course;

    public Registration(Date registrationDate, Course course) {
        this.registrationDate = registrationDate;
        this.course = course;
        this.certificate = null;
    }

//    public Certificate getCertificate(Certificate certificate) {
//        return certificate;
//    }

    public void setCertificate(Certificate certificate) {
//registration.setCertificate(certificate)
    }
}
