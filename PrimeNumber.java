import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class primeNumber{
    
    private static Result findFactors(int number) {
    StringBuilder factors = new StringBuilder();
    int counter = 0;
    for (int i = 2; i < number; i++) {
        if (number % i == 0) {
            if (factors.length() > 0) {
                factors.append(",");
            }
            factors.append(i);
        }
        counter++;
    }

    

    return new Result(counter, factors.toString());
}
    
    private static class Result {
    private final int iterations;
    private final String factors;

    public Result(int iterations, String factors) {
        this.iterations = iterations;
        this.factors = factors;
    }

    public int getIterations() {
        return iterations;
    }

    public String getFactors() {
        return factors;
    }
}

    
     public static PrimeResult isPrime(int n, int k) {
        if (n <= 1 || n == 4)
            return new PrimeResult(false, 0);
        if (n <= 3)
            return new PrimeResult(true, 0);

        // Find r and d such that n - 1 = 2^r * d
        
        int d = n - 1;
        int r = 0;
        while (d % 2 == 0) {
            
            d /= 2;
            r++;
        }

        // Perform Miller-Rabin test k times
        int iterations = 0;
        Random random = new Random();
        int a = 2 + random.nextInt((int) (n - 2));
        for (int i = 0; i < k; i++) {
            
            int x = modularExponentiation(a, d, n);
            iterations ++;
            if (x == 1 || x == n - 1)
                continue;

            boolean isComposite = true;
            for (int j = 1; j < r; j++) {
                x = modularExponentiation(x, 2, n);
                iterations ++;
                if (x == n - 1) {
                    isComposite = false;
                    break;
                }
            }

            if (isComposite)
                return new PrimeResult(false, iterations);
        }

        return new PrimeResult(true, iterations);
    }

    // Modular exponentiation: (a^b) % n
    public static int modularExponentiation(int a, int b, int n) {
        int result = 1;
        a = a % n;

        while (b > 0) {
            if (b % 2 == 1){
                result = (result * a) % n;}
            b /= 2;
            a = (a * a) % n;
        }

        return result;
    }

    // Find lowest common factors of a composite number
    public static HashSet<Integer> findLowestFactors(int n) {
        HashSet<Integer> factors = new HashSet<>();

        for (int i = 2; i <= Math.sqrt(n); i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        if (n > 1) {
            factors.add(n);
        }

        return factors;
    }
    
    private static class PrimeResult {
        boolean isPrime;
        int iterations;

        PrimeResult(boolean isPrime, int iterations) {
            this.isPrime = isPrime;
            this.iterations = iterations;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Find Prime Number and Factors");
            
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                

                case 1:
                    System.out.print("Enter a number: ");
                    int num = scanner.nextInt();
                    
                    int counting;
                    String factzz;
                    
                    Result result1 = findFactors(num);
                    counting = result1.getIterations();
                    factzz = result1.getFactors();
                    
                    int p_iterations = 5; // Number of possible iterations for the Miller-Rabin test
                    boolean isPrime;
                    int iterationCount;

                    // Call the isPrime method and retrieve the result and iteration count
                    PrimeResult result = isPrime(num, p_iterations);
                    isPrime = result.isPrime;
                    iterationCount = result.iterations;
                    
                    if (factzz.length() > 0 && !isPrime) {
                        System.out.println(num + " is a composite number and its factors are -> " + factzz);
                        HashSet<Integer> factorrs = findLowestFactors(num);
                        ArrayList<Integer> factorList = new ArrayList<>(factorrs);
                        System.out.print(num + " is a composite number and its lowest or prime factors are: ");
                        for (int i = 0; i < factorList.size(); i++) {
                            System.out.print(factorList.get(i));
                            if (i < factorrs.size() - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println();
                        System.out.println("With 1st method, number of iterations is: " + counting);
                        System.out.println("With 2nd method, number of iteration  is: " + iterationCount);
                    } else {
                        System.out.println(num + " is a prime number and its factors are -> " + factzz);
                        HashSet<Integer> factorrs = findLowestFactors(num);
                        ArrayList<Integer> factorList = new ArrayList<>(factorrs);
                        System.out.print(num + " is a prime number and its lowest or prime factors are: ");
                        System.out.println();
                        System.out.println("With 1st method, number of iterations is: " + counting);
                        System.out.println("With 2nd method, number of iteration  is: " + iterationCount);
                    }

                    
                        System.out.println();
                    
                    break;

                case 2:
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                    
            }
        }
         while (choice != 2);

        scanner.close();
        
        }
        
        
}
