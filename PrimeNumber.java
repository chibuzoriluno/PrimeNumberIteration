import java.util.Random;

public class PrimeTest {
    private static Random random = new Random();

    public static void main(String[] args) {
        int num = 12;
        int iterations = 10; // Number of iterations for the Fermat test

        // Call the isPrime method and retrieve the result and iteration count
        PrimeResult result = isPrime(num, iterations);

        if (result.isPrime) {
            System.out.println(num + " is a prime number.");
        } else {
            System.out.println(num + " is not a prime number.");
        }
        System.out.println("Number of iterations: " + result.iterations);
    }

    private static PrimeResult isPrime(int n, int k) {
        if (n < 2)
            return new PrimeResult(false, 0);
        if (n == 2 || n == 3)
            return new PrimeResult(true, 0);
        int a = getRandomNumber(2, n - 2);
        int iterations = 0;
        for (int i = 0; i < k; i++) {
            
            int x = modularExponentiation(a, n - 1, n);

            iterations++; // Increment iteration count for each iteration

            if (x != 1)
                return new PrimeResult(false, iterations);
        }

        return new PrimeResult(true, iterations); // Assign the final iteration count
    }

    private static int modularExponentiation(int base, int exponent, int modulus) {
        int result = 1;
        base %= modulus;

        while (exponent > 0) {
            if (exponent % 2 == 1)
                result = (result * base) % modulus;
            base = (base * base) % modulus;
            exponent /= 2;
        }

        return result;
    }

    private static int getRandomNumber(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    // Custom class to store the result and iteration count
    private static class PrimeResult {
        boolean isPrime;
        int iterations;

        PrimeResult(boolean isPrime, int iterations) {
            this.isPrime = isPrime;
            this.iterations = iterations;
        }
    }
}
