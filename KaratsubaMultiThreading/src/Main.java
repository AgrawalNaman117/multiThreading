import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number (x): ");
        long x = scanner.nextLong();

        System.out.print("Enter the second number (y): ");
        long y = scanner.nextLong();

        long result = KaratsubaLong.karatsuba(x, y);

        System.out.println("[Main Thread] Final Product: " + result);

        scanner.close();
    }
}

