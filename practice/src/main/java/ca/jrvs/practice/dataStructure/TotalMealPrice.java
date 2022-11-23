package ca.jrvs.practice.dataStructure;

public class TotalMealPrice {

    public static double calculateTotalMealPrice(double listedMealPrice, double tipRate, double taxRate) {
        double tip = tipRate * listedMealPrice;
        double tax = taxRate * listedMealPrice;
        double total = listedMealPrice + tax + tip;
        return total;
    }

    public static void main(String[] args) {
        double totalMealPrice = calculateTotalMealPrice(100, 0.2, 0.08);
        System.out.println("Total meal price: " + totalMealPrice);
    }
}
