public class Factorial{

    private final static int MAX_N = 400;

    public static void main(String[] args){
        /*
        outputs the factorial of n (or n!), that is, the product of all positive integers less than or equal to n.
        For example, 7! is 7 × 6 × 5 × 4 × 3 × 2 × 1 = 5040.
        The program's argument will be a single Java int;
        so, to determine the factorial of 21, we would run the program like this:
          java Factorial 21
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
                    System.out.println("This can be changed by setting MAX_N to a different value \n(not recommended, 313! took my PC 3m20.825s)");
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
            BigInteger result = factorial(n);
            System.out.println("\r\n## " + n + "! is: \n\n" + result.toString());
            System.out.println("\r\n## It has " + result.length() + " digits");
        }

    }

    private static boolean isValidValue(double arg){
        return (arg > 0 && arg <= MAX_N);
    }

    private static BigInteger factorial(int n){

        if(n == 1){
            return BigInteger.ONE;
        }

        return new BigInteger(n).multiply(factorial(n-1));
    }
}
