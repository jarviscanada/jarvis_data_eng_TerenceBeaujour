package ca.jrvs.practice.recursion;

public class Factorial {

    public int recursiveFactorial(int n) {
        if (n == 0) return 1;
        return n*recursiveFactorial(n-1);
    }

    public int iterativeFactorial(int n) {
        int result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
