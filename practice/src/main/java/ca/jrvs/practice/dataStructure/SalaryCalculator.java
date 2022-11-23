package ca.jrvs.practice.dataStructure;

public class SalaryCalculator {
    public static double calculateSalary(double hoursPerWeek, double moneyPerHour, double vacationDays) {
        if (hoursPerWeek < 0 || moneyPerHour <0) {
            return -1;
        }
        double moneyOff = vacationDays * 8 * moneyPerHour;
        return (hoursPerWeek * moneyPerHour * 52) - moneyOff;
    }
    public static void main(String[] args) {
        double hoursPerWeek = 37.5;
        double moneyPerHour = 50;
        double vacationDays = 0;
        double salary = calculateSalary(hoursPerWeek, moneyPerHour, vacationDays);

        if (salary >= 0) {
            System.out.println("Salary: " + salary);
        } else {
            System.out.println("Negative parameters are not accepted");
        }

    }
}
