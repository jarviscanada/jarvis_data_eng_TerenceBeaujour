package ca.jrvs.practice.dataStructure;

public class StudentChallenge {
    String firstName;
    String lastName;
    int graduationYear;
    double gpa;
    String major;

    public StudentChallenge(String firstName, String lastName, int graduationYear, double gpa, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.graduationYear = graduationYear;
        this.gpa = gpa;
        this.major = major;
    }

    public void incrementExpectedGraduationYear() {
        this.graduationYear += 1;
    }
}
