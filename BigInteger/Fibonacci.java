public class Fibonacci{

    private final static int MAX_N = 10000;

    public static void main(String[] args){
        /*
        outputs the nth number in the sequence 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...,
        that is, the sequence which begins with zero, then one,
        and then each succeeding number is the sum of the two immediately preceding it.

        The program's argument will be a single Java int;
        so, to determine the ten thousandth Fibonacci number, we would run the program like this:
            java Fibonacci 10000
        */

        int n = 0;
        boolean shouldRun = true;

        if(args.length == 1){
            try{
                int arg0 = Integer.parseInt(args[0]);
                if(isValidValue(arg0)){
                    n = arg0;
                } else {
                    System.out.println("\r\n## \"n\" should be in the interval [1, " + MAX_N +"]\r\n");
                    System.out.println("This can be changed by setting MAX_N to a different value \n(not recommended)");
                    shouldRun = false;
                    }
            }
            catch (NumberFormatException e) {
                System.out.println("\r\n## \"n\" should be a Integer");
                shouldRun = false;
            }
        } else {
            System.out.println("\r\n## Please provide one imput of the type Integer");
            shouldRun = false;
        }

        if(shouldRun){
            BigInteger result = fibonacci(n);
            System.out.println("\r\n## The " + n + "the value in the Fibonacci sequence is: \n\n" + result.toString());
            System.out.println("\r\n## It has " + result.length() + " digits");
        }

    }

    private static boolean isValidValue(double arg){
        return (arg > 0 && arg <= MAX_N);
    }

    private static BigInteger fibonacci(int n){

        if(n == 1){
            return BigInteger.ZERO;
        }

        BigInteger n_m_minus_1 = BigInteger.ZERO;
        BigInteger n_m = BigInteger.ONE;
        BigInteger n_m_plus_1;

        for(int i = 1; i < n; i++){
            n_m_plus_1 = n_m.add(n_m_minus_1);
            n_m_minus_1 = n_m;
            n_m = n_m_plus_1;
        }
        return n_m;
    }
}
