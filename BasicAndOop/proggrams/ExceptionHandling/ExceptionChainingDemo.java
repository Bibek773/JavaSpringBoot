package BasicAndOop.proggrams.ExceptionHandling;

class ExceptionChainingDemo {

    public static void main(String[] args) {
        try {
            process();
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Original cause: " + e.getCause());
        }
    }

    static void process() {
        try {
            int result = divide(10, 0); // will cause ArithmeticException
        } catch (ArithmeticException e) {
            // chaining: wrap original exception
            throw new RuntimeException("Error while processing division", e);
        }
    }

    static int divide(int a, int b) {
        return a / b;
    }
}