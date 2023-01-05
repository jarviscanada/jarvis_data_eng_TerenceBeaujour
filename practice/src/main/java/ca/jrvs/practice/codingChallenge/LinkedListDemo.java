package ca.jrvs.practice.codingChallenge;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedListLearning list = new LinkedListLearning();

        list.insertAtHead(5);
        list.insertAtHead(10);
        list.insertAtHead(2);;
        list.insertAtHead(12);
        list.insertAtHead(5);
        list.insertAtHead(10);

        System.out.println(list);
        System.out.println("Length: " + list.length());

        list.deleteFromHead();
        System.out.println(list);
        System.out.println("Length: " + list.length());

        System.out.println("Found: " + list.find(100));
    }
}
