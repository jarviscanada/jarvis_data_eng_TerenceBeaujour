package ca.jrvs.practice.codingChallenge;

public class DoublyEndedListDemo {

    public static void main(String[] args) {
        DoublyEndedLinkedListLearning dList = new DoublyEndedLinkedListLearning();

        dList.insertAtTail(20);
        dList.insertAtTail(19);
        dList.insertAtTail(18);

        System.out.println(dList);
    }
}
