package ca.jrvs.practice.dataStructure;

public class Main {
    public static void main(String[] args) {
        Triangle triangleA = new Triangle(15, 8, 15, 8, 17);
        Triangle triangleB = new Triangle(3, 2.598, 3, 3, 3);

        double triangleAArea = triangleA.findArea();
        System.out.println("Area of triangle A: " + triangleAArea);

        double triangleBArea = triangleB.findArea();
        System.out.println("Area of triangle A: " + triangleBArea);

        System.out.println("Number of sides in a triangle: " + Triangle.numOfSides);
    }
}
