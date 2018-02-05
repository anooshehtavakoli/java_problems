package mathandlogicpuzzles;

/**
 *
 * Chapter 6
 *
 * Created by anoosheh on 2/4/18.
 */
public class PrimeNumbers {
    public static int gcd(int a, int b)
    {
        if (a == 0)
            return b;

        return gcd(b%a, a);
    }

    // Checking to see if a number is a prime:
    // 1:
    boolean primeNaive(int n) {
        if ( n < 2 ) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if ( n % i == 0 ) {
                return false;
            }
        }
        return true;
    }

    // 2:
    boolean primeSlightlyBetter(int n) {
        if ( n < 2 ) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Generating a list of primes:
    /* The sieve of Eratosthenes: a procedure for finding prime numbers that involves writing down the odd numbers
    from 2 up in succession and crossing out every third number after 3, every fifth after 5 including those
    already crossed out, every seventh after 7, and so on with the numbers that are never crossed out being prime
    */

    boolean[] sieveOfEratosthenes(int max) {
        boolean[] flags = new boolean[max + 1];
        int count = 0;

        init(flags); // Set all flags to true other than 0 and 1
        int prime = 2;

        while (prime <= Math.sqrt(max)) {
            // Cross off remaining multiples of prime
            crossOff(flags, prime);

            // Find next value which is true
            prime = getNextPrime(flags, prime);
        }
        return flags;
    }

    void crossOff(boolean[] flags, int prime) {
        /* Cross off remaining multiples of prime. we can start with (prime * prime), because if we have a k * prime,
           where k < prime, this value would have already been crossed off in a prior iteration.
         */
        for (int i = prime * prime; i < flags.length; i += prime) {
            flags[i] = false;
        }
    }

    int getNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        while (next < flags.length && !flags[next]) {
            next ++;
        }
        return next;
    }

    void init(boolean[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i == 0 || i == 1) {
                a[i] = false;
            } else {
                a[i] = true;
            }
        }
    }

}
