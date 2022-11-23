package ca.jrvs.practice.dataStructure;

public class StudentChallengeTest {
    public static void main(String[] args) {
        StudentChallenge student1 = new StudentChallenge("Richard","Grayson", 2025, 4.0, "Computer Science");
        StudentChallenge student2 = new StudentChallenge("Jason","Todd", 2023, 3.9, "Maths");

        student1.incrementExpectedGraduationYear();
        System.out.println("New graduation year: " + student1.graduationYear);
    }
}
