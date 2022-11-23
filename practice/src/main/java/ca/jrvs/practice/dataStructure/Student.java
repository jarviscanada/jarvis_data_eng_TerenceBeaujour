package ca.jrvs.practice.dataStructure;
import java.util.Scanner;

public class Student {
    public static void main(String[] args) {
        String studentFirstName = "Terence";
        String studentLastName = "Beaujour";
        int studentAge = 15;
        double studentGPA = 3.45;
        char studentFirstInitial = studentFirstName.charAt(0);
        char studentLastInitial = studentLastName.charAt(0);
        boolean hasPerfectAttendance = true;
        System.out.println("Enter a GPA: ");
        Scanner input = new Scanner(System.in);
        studentGPA = input.nextDouble();

        System.out.println(studentAge);
        System.out.println(studentGPA);
        System.out.println(studentFirstInitial);
        System.out.println(studentLastInitial);
        System.out.println(hasPerfectAttendance);
        System.out.println(studentFirstName);
        System.out.println(studentLastName);
        System.out.println(studentFirstName + " " + studentLastName + " has a GPA of: " + studentGPA);
    }
}
