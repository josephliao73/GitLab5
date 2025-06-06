import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator {

    /**
     * Generates the first 'size' prime numbers using the Sieve of Eratosthenes.
     *
     * @param size The number of prime numbers to generate.
     * @return A list of the first 'size' prime numbers as BigInteger.
     */
    public List<BigInteger> getPrimes(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be greater than 0.");
        }
        return sieveOfEratosthenes(size);
    }

    /**
     * Implements the Sieve of Eratosthenes to generate prime numbers.
     *
     * @param size The number of prime numbers to generate.
     * @return A list of the first 'size' prime numbers as BigInteger.
     */
    private List<BigInteger> sieveOfEratosthenes(int size) {
        // Estimate an upper bound for the nth prime using the approximation: n * log(n) + n * log(log(n))
        int upperBound = (int) (size * Math.log(size) + size * Math.log(Math.log(size)));
        if (size < 6) {
            upperBound = 15; // Handle small cases
        }

        // Initialize a boolean array to mark prime numbers
        boolean[] isPrime = new boolean[upperBound + 1];
        for (int i = 2; i <= upperBound; i++) {
            isPrime[i] = true;
        }

        // Mark non-prime numbers using the Sieve of Eratosthenes
        for (int p = 2; p * p <= upperBound; p++) {
            if (isPrime[p]) {
                for (int multiple = p * p; multiple <= upperBound; multiple += p) {
                    isPrime[multiple] = false;
                }
            }
        }

        // Collect the first 'size' primes
        List<BigInteger> primes = new ArrayList<>();
        for (int i = 2; i <= upperBound && primes.size() < size; i++) {
            if (isPrime[i]) {
                primes.add(BigInteger.valueOf(i));
            }
        }

        return primes;
    }
}