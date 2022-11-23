package ca.jrvs.practice.dataStructure;
import java.util.Scanner;

public class PlayingSong {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isOnRepeat = true;

        while (isOnRepeat) {
            System.out.println("Playing Song");
            System.out.println("Do you want to play the next song ? (yes/no)");
            String answer = input.next();
            if (answer.equals("yes")) {
                isOnRepeat = false;
            }
        }
        System.out.println("Playing next song");
    }
}
