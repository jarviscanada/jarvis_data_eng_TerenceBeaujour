package ca.jrvs.practice.dataStructure;
import java.util.Scanner;

public class Fortune {
    public static void main(String[] args) {
        System.out.println("Choose a number between 1 and 10:");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();

        if (number < 5) {
            System.out.println("Enjoy yhe good luck a friend brings you");
        } else {
            System.out.println("Your shoe selection will make you very happy today");
        }
    }
}
