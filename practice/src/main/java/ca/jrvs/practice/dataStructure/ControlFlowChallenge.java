package ca.jrvs.practice.dataStructure;
import java.util.Scanner;

public class ControlFlowChallenge {
    public static void main(String[] args) {
        String question = "What is the capital of Mali ?";
        String choiceOne = "niamey";
        String choiceTwo = "bamako";
        String choiceThree = "abidjan";
        String correctAnswer = choiceTwo;

        Scanner input = new Scanner(System.in);
        System.out.println(question);
        System.out.println("Choice 1: " + choiceOne);
        System.out.println("Choice 2: " + choiceTwo);
        System.out.println(("Choice 3: " + choiceThree));

        String userChoice = input.next();

        if (userChoice.toLowerCase().equals(correctAnswer)) {
            System.out.println("Congrats, you're educated");
        } else {
            System.out.println("You're wrong");
        }
    }
}
