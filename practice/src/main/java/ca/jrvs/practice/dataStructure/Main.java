package ca.jrvs.practice.dataStructure;

public class Main {
    public static void main(String[] args) {
        ArrayJList<String> list = new ArrayJList<>();
        list.add("Terence");
        list.add("Fanny");
        list.add("Socrate");

        System.out.println("Number of elements: " + list.size() + "\n");

        System.out.println("Index of Fanny: " + list.indexOf("Fanny") + "\n");

        String bool = list.contains("Socrate") ? "yes" : "no";

        System.out.println("Does it contain Socrate: " + bool + "\n");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        list.clear();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        String isListEmpty = list.isEmpty() ? "yes" : "no";

        System.out.println("Is list empty: " + isListEmpty);

    }
}
