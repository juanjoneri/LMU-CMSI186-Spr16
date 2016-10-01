public class GCD{

    private final static int CONSTANT = 10000;

    public static void main(String[] args){
        /*
        outputs the greatest common divisor of two BigIntegers, which must be positive.
        The program's arguments will be two String representations of BigInteger;
        so, to greatest common divisor of 5653603385532 and 7219144728274279424013,
        we would run the program like this:
        java GCD 5653603385532 7219144728274279424013
        */

        boolean shouldRun = true;
        //Juast plce holders for the variables
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ZERO;

        if(args.length == 2){
            try{
                BigInteger arg0 = new BigInteger(args[0]);
                BigInteger arg1 = new BigInteger(args[1]);

                if(isValidValue(arg0) && isValidValue(arg1)){
                    a = arg0;
                    b = arg1;
                } else {
                    System.out.println("\r\n## Both Big Integers should be positive");
                    shouldRun = false;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("\r\n## Please only provide Big Integers");
                shouldRun = false;
            }
        } else {
            System.out.println("\r\n## Please provide two positive BigIntegers");
            shouldRun = false;
        }

        if(shouldRun){
            BigInteger result = gCD(a, b);
            System.out.println("\r\n## The GCD between " + a + " and " + b + " is: \n\n" + result);
        }

    }

    private static boolean isValidValue(BigInteger arg){
        //The argument have to be positive
        return (arg.getSign() != -1);
    }

    private static BigInteger gCD(BigInteger a, BigInteger b){

        //If A = 0 then GCD(A,B)=B, since the GCD(0,B)=B, and we can stop.
        //If B = 0 then GCD(A,B)=A, since the GCD(A,0)=A, and we can stop.
        if(a.equals(BigInteger.ZERO)){
            return b;
        }else if(b.equals(BigInteger.ZERO)){
            return a;
        }

        //Write A in quotient remainder form (A = B⋅Q + R)
        //BigInteger q = a.divide(b);
        BigInteger r = a.remainder(b);
        //Find GCD(B,R) using the Euclidean Algorithm since GCD(A,B) = GCD(B,R)
        return gCD(b, r);
    }

}
