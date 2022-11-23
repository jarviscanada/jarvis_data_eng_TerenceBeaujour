package ca.jrvs.practice.dataStructure;
import java.util.Scanner;

public class TeaTime {

    public static void announceDevTeaTime() {
        System.out.println("Wait for developer tea time");
        System.out.println("Type a random word and press Enter to start dev tea time");
        Scanner input = new Scanner(System.in);
        input.next();
        System.out.println("It's dev tea time");
    }

    public static void main(String[] args) {
        announceDevTeaTime();
    }
}
