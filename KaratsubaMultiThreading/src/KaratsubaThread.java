public class KaratsubaThread extends Thread {
    private final long a;
    private final long b;
    public long result;
    private final String taskName;

    public KaratsubaThread(long a, long b, String taskName) {
        this.a = a;
        this.b = b;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Computing " + taskName + " = " + a + " * " + b);
        result = KaratsubaLong.karatsuba(a, b);
        System.out.println("[" + Thread.currentThread().getName() + "] Result of " + taskName + " = " + result);
    }
}