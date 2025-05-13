/* Karatsuba Algorithm for fast multiplication
 *
 * break the two num into two halfs such that num1 = a*10^(digit_length/2)+b  and num2 = c*10^(digit_length/2)+d
 *
 * calculate ac = a*c
 * calculate bd = b*d
 * calculate e =(a+b)*(c+d)
 *
 * result = ac*10^digit_length + (e-ac-bd)*10^digit_length/2 + bd
 *
 * calculation of ac. bd and e is done recursively till the numbers being multiplied are in single units
 * */


public class KaratsubaLong {

    public static long karatsuba(long x, long y) {
        // Base case: If the numbers are small enough, just multiply
        if (x < 10 || y < 10) {
            return x * y;
        }

        // Find the number of digits in the larger number
        int digits = Math.max(Long.toString(x).length(), Long.toString(y).length());
        int half = digits / 2;
        long power = (long) Math.pow(10, half);

        // Split the numbers in half
        long high1 = x / power;
        long low1 = x % power;
        long high2 = y / power;
        long low2 = y % power;

        // Create threads for ac, bd, and abcd
        KaratsubaThread acThread = new KaratsubaThread(high1, high2, "ac");
        KaratsubaThread bdThread = new KaratsubaThread(low1, low2, "bd");
        KaratsubaThread abcdThread = new KaratsubaThread(high1 + low1, high2 + low2, "abcd");

        // Start all threads
        acThread.start();
        bdThread.start();
        abcdThread.start();

        try {
            // Wait for all threads to finish
            acThread.join();
            bdThread.join();
            abcdThread.join();
        } catch (InterruptedException e) {
        }

        // Get results from threads
        long ac = acThread.result;
        long bd = bdThread.result;
        long abcd = abcdThread.result;

        // Calculate adbc (ad + bc)
        long adbc = abcd - ac - bd;

        // Use the formula: result = ac * 10^(2*half) + adbc * 10^half + bd
        return ac * power * power + adbc * power + bd;
    }
}
