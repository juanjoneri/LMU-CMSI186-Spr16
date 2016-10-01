public class BigIntegerTestHarness {

    private static int attempts;
    private static int score;
    private static int sumAttempts;
    private static int sumScore;

    public static void main(String[] args) {

        //Basic method needed for the rest of the testas
        test_Constructor(); //includes constants and valueOf
        test_toString();
        test_Equals();

        //Core methods of the class
        test_Addition();
        test_Subtraction();
        test_Halve();
        test_Twice();
        test_Multiplication();
        test_Division();

        //Other methods of the class
        test_SwitchSign();
        test_SetSign();
        test_GetSign();
        test_Opposite();
        test_Remainder();
        test_Exponent();
        test_CompareTo();
        test_GetValues();
        test_AbsoluteValue();
        test_Length();

        sumUp();

    }

    private static void displaySuccessIfTrue(boolean test, String label) {
        attempts ++;
        score += test ? 1 : 0;

        label = (test ? "   [+] " : " =>[-] ") + label;
        System.out.println(label);
    }

    private static void displayUnimplementedMethodFailure() {
        System.out.println("    => " + ": (NYI)");
    }

    public static void testingMethod(String testName){
        attempts = 0;
        score = 0;
        System.out.println("\n ## Testing " + testName + ": \n");
    }

    public static void showResults(){
        sumAttempts += attempts;
        sumScore += score;

        String label = "-------------------------------------(" + String.valueOf(score) + "/" + String.valueOf(attempts) + ")";
        System.out.println(label);
    }

    public static void sumUp(){
        int percent =  (int) ( ( (float)sumScore / (float)sumAttempts ) * 100F);
        String grade = "F ";
        if(percent > 95){ grade = "A+";}
        else if(percent > 93){grade = "A ";}
        else if(percent > 90){grade = "A-";}
        else if(percent > 87){grade = "B+";}
        else if(percent > 83){grade = "B ";}
        else if(percent > 80){grade = "B-";}
        else if(percent > 77){grade = "C+";}
        else if(percent > 73){grade = "C ";}
        else if(percent > 70){grade = "C-";}
        else if(percent > 67){grade = "D+";}
        else if(percent > 63){grade = "D ";}
        else if(percent > 60){grade = "D-";}

        String label =" __________________\n";
        label += "/\\                 \\\n";
        label += "\\_| Test Restults: |          /)\n";
        label += "  |                |         //\n  |";
        label += " -->(" + String.valueOf(sumScore) + "/" + String.valueOf(sumAttempts) + ")<- |        (/\n";
        label += "  | ----- -------  |       _/\n";
        label += "  | ------.     "+ grade +" |      ) (\n";
        label += "  |  ______________|_    /INK\\\n";
        label += "  \\_/_______________/    \\___/";

        System.out.println(label);
    }

    private static void test_Constructor() {
        testingMethod("Constructors");

        //String Constructors
        try{
            BigInteger bigInt = new BigInteger("31415926535897932384626");
            displaySuccessIfTrue(bigInt.toString().equals("31415926535897932384626"), "String, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-31415926535897932384626");
            displaySuccessIfTrue(bigInt.toString().equals("-31415926535897932384626"), "String, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("0");
            displaySuccessIfTrue(bigInt.toString().equals("0"), "String, zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-0");
            displaySuccessIfTrue(bigInt.toString().equals("0"), "String, negative zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("+0");
            displaySuccessIfTrue(bigInt.toString().equals("0"), "String, positive zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("*//Manzana//*");
            displaySuccessIfTrue(false, "String, illegal Argument");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "String, illegal Argument");
        }

        //Array constructors
        try{
            BigInteger bigInt = new BigInteger(new int[]{3,1,4,1,5,9,2,6,5,3,5,4,9,7,9,3,2,3,8,4,6,2,6}, (byte) 1);
            displaySuccessIfTrue(bigInt.toString().equals("31415926535497932384626"), "Array, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger(new int[]{3,1,4,1,5,9,2,6,5,3,5,4,9,7,9,3,2,3,8,4,6,2,6}, (byte) -1);
            displaySuccessIfTrue(bigInt.toString().equals("-31415926535497932384626"), "Array, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger(new int[]{0}, (byte) 0);
            displaySuccessIfTrue(bigInt.toString().equals("0"), "Array, zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger(new int[]{0}, (byte) 1);
            displaySuccessIfTrue(bigInt.toString().equals("0"), "Array, positive zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger(new int[]{0}, (byte) -1);
            displaySuccessIfTrue(bigInt.toString().equals("0"), "Array, negative zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger(new int[]{0, 90, -2}, (byte) 0);
            displaySuccessIfTrue(false, "Array, illegal Argument");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Array, illegal Argument");
        }

        //Long Constructors
        try{
            BigInteger bigInt = new BigInteger(3141592653589793238L);
            displaySuccessIfTrue(bigInt.toString().equals("3141592653589793238"), "Long, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger(-3141592653589793238L);
            displaySuccessIfTrue(bigInt.toString().equals("-3141592653589793238"), "Long, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger((long) 0 );
            displaySuccessIfTrue(bigInt.toString().equals("0"), "Long, zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        //valueOf Constructors
        try{
            BigInteger bigInt = BigInteger.valueOf(3141592653589793238L);
            displaySuccessIfTrue(bigInt.toString().equals("3141592653589793238"), "valueOf, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.valueOf(-3141592653589793238L);
            displaySuccessIfTrue(bigInt.toString().equals("-3141592653589793238"), "valueOf, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.valueOf(0);
            displaySuccessIfTrue(bigInt.toString().equals("0"), "valueOf, zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        //Constants
        try{
            BigInteger bigInt = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt.toString().equals("0"), "Constant, ZERO");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.ONE;
            displaySuccessIfTrue(bigInt.toString().equals("1"), "Constant, ONE");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.TEN;
            displaySuccessIfTrue(bigInt.toString().equals("10"), "Constant, TEN");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }
        showResults();
    }

    private static void test_toString() {
        testingMethod("To String");

        try{
            BigInteger bigInt = new BigInteger("3141592653589793238");
            displaySuccessIfTrue(bigInt.toString().equals("3141592653589793238"), "toString, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-3141592653589793238");
            displaySuccessIfTrue(bigInt.toString().equals("-3141592653589793238"), "toString, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("0");
            displaySuccessIfTrue(bigInt.toString().equals("0"), "toString, zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("*//Manzana//*");
            displaySuccessIfTrue(false, "String, illegal Argument");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "String, illegal Argument");
        }

        showResults();
    }

    private static void test_Equals() {
        testingMethod("Equals");

        try{
            BigInteger bigInt = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt.equals(new BigInteger(0)), "Equals, zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.ONE;
            displaySuccessIfTrue(bigInt.equals(new BigInteger(1)), "Equals, one");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.TEN;
            displaySuccessIfTrue(bigInt.equals(new BigInteger(10)), "Equals, ten");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("314159265");
            displaySuccessIfTrue(bigInt.equals(new BigInteger(314159265)), "Equals, positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-314159265");
            displaySuccessIfTrue(bigInt.equals(new BigInteger(-314159265)), "Equals, negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("314159265");
            displaySuccessIfTrue(!bigInt.equals(new BigInteger(-314159265)), "Not Equals, op sign (+/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-314159265");
            displaySuccessIfTrue(!bigInt.equals(new BigInteger(314159265)), "Not Equals, op sign (-/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("314159265");
            displaySuccessIfTrue(!bigInt.equals(new BigInteger(271828182)), "Not Equals, diferent numbers");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_Addition() {
        testingMethod("Addition");

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.add(bigInt2).toString().equals("58598744820488384738228"), "Addition, (+/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.add(bigInt2).toString().equals("4233108251307480031024"), "Addition, (+/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.add(bigInt2).toString().equals("-4233108251307480031024"), "Addition, (-/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.add(bigInt2).toString().equals("-58598744820488384738228"), "Addition, (-/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.add(bigInt2).toString().equals("27182818284590452353602"), "Addition, (0/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("27182818284590452353602");
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.add(bigInt2).toString().equals("27182818284590452353602"), "Addition, (+/0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.add(bigInt2).toString().equals("-27182818284590452353602"), "Addition, (0/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-27182818284590452353602");
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.add(bigInt2).toString().equals("-27182818284590452353602"), "Addition, (-/0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.add(bigInt2).toString().equals("0"), "Addition, (0/0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_Subtraction() {
        testingMethod("subtraction");

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.subtract(bigInt2).toString().equals("4233108251307480031024"), "Subtraction, (+/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.subtract(bigInt2).toString().equals("58598744820488384738228"), "Subtraction, (+/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.subtract(bigInt2).toString().equals("-58598744820488384738228"), "Subtraction, (-/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.subtract(bigInt2).toString().equals("-4233108251307480031024"), "Subtraction, (-/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.subtract(bigInt2).toString().equals("-27182818284590452353602"), "Subtraction, (0/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("27182818284590452353602");
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.subtract(bigInt2).toString().equals("27182818284590452353602"), "Subtraction, (+/0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.subtract(bigInt2).toString().equals("27182818284590452353602"), "Subtraction, (0/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-27182818284590452353602");
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.subtract(bigInt2).toString().equals("-27182818284590452353602"), "Subtraction, (-/0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.subtract(bigInt2).toString().equals("0"), "Subtraction, (0/0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_Multiplication() {
        testingMethod("Multiplication");

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.multiply(bigInt2).toString().equals("853973422267356706546315814220731878020522852"), "Multiplication, (+/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.multiply(bigInt2).toString().equals("-853973422267356706546315814220731878020522852"), "Multiplication, (+/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.multiply(bigInt2).toString().equals("-853973422267356706546315814220731878020522852"), "Multiplication, (-/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.multiply(bigInt2).toString().equals("853973422267356706546315814220731878020522852"), "Multiplication, (-/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.multiply(bigInt2).toString().equals("0"), "Multiplication, (0/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("27182818284590452353602");
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.multiply(bigInt2).toString().equals("0"), "Multiplication, (+/0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.multiply(bigInt2).toString().equals("0"), "Multiplication, (0/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-27182818284590452353602");
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.multiply(bigInt2).toString().equals("0"), "Multiplication, (-/0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.multiply(bigInt2).toString().equals("0"), "Multiplication, (0/0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_Division() {
        testingMethod("Division");

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284");
            displaySuccessIfTrue(bigInt1.divide(bigInt2).toString().equals("1155727349816"), "Division, (+/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284");
            displaySuccessIfTrue(bigInt1.divide(bigInt2).toString().equals("-1155727349816"), "Division, (+/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284");
            displaySuccessIfTrue(bigInt1.divide(bigInt2).toString().equals("-1155727349816"), "Division, (-/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284");
            displaySuccessIfTrue(bigInt1.divide(bigInt2).toString().equals("1155727349816"), "Division, (-/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.divide(bigInt2).toString().equals("0"), "Division, (0/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = BigInteger.ZERO;
            bigInt1.divide(bigInt2);
            displaySuccessIfTrue(false, "Division, invalid (+/0)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Division, invalid (+/0)");
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.divide(bigInt2).toString().equals("0"), "Division, (0/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = BigInteger.ZERO;
            bigInt1.divide(bigInt2);
            displaySuccessIfTrue(false, "Division, invalid (-/0)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Division, invalid (-/0)");
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = BigInteger.ZERO;
            bigInt1.divide(bigInt2);
            displaySuccessIfTrue(false, "Division, invalid (0/0)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Division, invalid (0/0)");
        }
        showResults();
    }

    private static void test_Halve() {
        testingMethod("Halve");

        try{
            BigInteger bigInt = new BigInteger("3141592653589793238");
            displaySuccessIfTrue(bigInt.halve().toString().equals("1570796326794896619"), "Halve, Big even positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-3141592653589793238");
            displaySuccessIfTrue(bigInt.halve().toString().equals("-1570796326794896619"), "Halve, Big even negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("3141592653589793239");
            bigInt.halve();
            displaySuccessIfTrue(false, "Halve, invalid Big odd pos");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Halve, invalid Big odd pos");
        }

        try{
            BigInteger bigInt = new BigInteger("-3141592653589793239");
            bigInt.halve();
            displaySuccessIfTrue(false, "Halve, invalid Big odd neg");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Halve, invalid Big odd neg");
        }

        try{
            BigInteger bigInt = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt.halve().toString().equals("0"), "Halve, Zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_Twice() {
        testingMethod("Twice");

        try{
            BigInteger bigInt = new BigInteger("3141592653589793238");
            displaySuccessIfTrue(bigInt.twice().toString().equals("6283185307179586476"), "Twice, Big even positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-3141592653589793238");
            displaySuccessIfTrue(bigInt.twice().toString().equals("-6283185307179586476"), "Twice, Big even negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("3141592653589793239");
            displaySuccessIfTrue(bigInt.twice().toString().equals("6283185307179586478"), "Twice, Big odd positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-3141592653589793239");
            displaySuccessIfTrue(bigInt.twice().toString().equals("-6283185307179586478"), "Twice, Big odd negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt.twice().toString().equals("0"), "Twice, Zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_SwitchSign() {
        testingMethod("Switch Sign");

        try{
            BigInteger bigInt =  new BigInteger("31415926535897932384626");
            bigInt.switchSign();
            displaySuccessIfTrue(bigInt.toString().equals("-31415926535897932384626"), "Switch Sign, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt =  new BigInteger("-31415926535897932384626");
            bigInt.switchSign();
            displaySuccessIfTrue(bigInt.toString().equals("31415926535897932384626"), "Switch Sign, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.ZERO;
            bigInt.switchSign();
            displaySuccessIfTrue(bigInt.toString().equals("0"), "Switch Sign, Zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_SetSign() {
        testingMethod("Set Sign");

        try{
            BigInteger bigInt =  new BigInteger("31415926535897932384626");
            bigInt.setSign((byte) -1);
            displaySuccessIfTrue(bigInt.toString().equals("-31415926535897932384626"), "Set Sign, (+ to -)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt =  new BigInteger("-31415926535897932384626");
            bigInt.setSign((byte) 1);
            displaySuccessIfTrue(bigInt.toString().equals("31415926535897932384626"), "Set Sign, (- to +)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt =  new BigInteger("31415926535897932384626");
            bigInt.setSign((byte) 0 );
            displaySuccessIfTrue(false, "Set Sign, invalid (+ to 0)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Set Sign, invalid (+ to 0)");
        }

        try{
            BigInteger bigInt =  new BigInteger("-31415926535897932384626");
            bigInt.setSign((byte) 0 );
            displaySuccessIfTrue(false, "Set Sign, invalid (- to 0)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Set Sign, invalid (- to 0)");
        }

        try{
            BigInteger bigInt =  BigInteger.ZERO;
            bigInt.setSign((byte) 1 );
            displaySuccessIfTrue(false, "Set Sign, invalid (0 to +)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Set Sign, invalid (0 to +)");
        }

        try{
            BigInteger bigInt =  BigInteger.ZERO;
            bigInt.setSign((byte) -1 );
            displaySuccessIfTrue(false, "Set Sign, invalid (0 to -)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Set Sign, invalid (0 to -)");
        }

        showResults();
    }

    private static void test_GetSign() {
        testingMethod("Get Sign");

        try{
            BigInteger bigInt =  new BigInteger("31415926535897932384626");
            displaySuccessIfTrue(bigInt.getSign() == 1, "Get Sign, Big Positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt =  new BigInteger("-31415926535897932384626");
            displaySuccessIfTrue(bigInt.getSign() == -1, "Get Sign, Big Negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt =  BigInteger.ZERO;
            displaySuccessIfTrue(bigInt.getSign() == 0, "Get Sign, ZERO");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_Opposite() {
        testingMethod("Opposite");

        try{
            BigInteger bigInt = new BigInteger("31415926535897932384626");
            displaySuccessIfTrue(bigInt.opposite().toString().equals("-31415926535897932384626"), "Opposite, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-31415926535897932384626");
            displaySuccessIfTrue(bigInt.opposite().toString().equals("31415926535897932384626"), "Opposite, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt.opposite().toString().equals("0"), "Opposite, zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_Remainder() {
        testingMethod("Remainder");

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284");
            displaySuccessIfTrue(bigInt1.remainder(bigInt2).toString().equals("703548882"), "Remainder, (+/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284");
            displaySuccessIfTrue(bigInt1.remainder(bigInt2).toString().equals("703548882"), "Remainder, (+/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("27182818284");
            displaySuccessIfTrue(bigInt1.remainder(bigInt2).toString().equals("-703548882"), "Remainder, (-/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = new BigInteger("-27182818284");
            displaySuccessIfTrue(bigInt1.remainder(bigInt2).toString().equals("-703548882"), "Remainder, (-/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.remainder(bigInt2).toString().equals("0"), "Remainder, (0/+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626");
            BigInteger bigInt2 = BigInteger.ZERO;
            bigInt1.divide(bigInt2);
            displaySuccessIfTrue(false, "Remainder, invalid (+/0)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Remainder, invalid (+/0)");
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.divide(bigInt2).toString().equals("0"), "Remainder, (0/-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("27182818284590452353602");
            BigInteger bigInt2 = BigInteger.ONE;
            displaySuccessIfTrue(bigInt1.divide(bigInt2).toString().equals("27182818284590452353602"), "Remainder, (+/1)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-27182818284590452353602");
            BigInteger bigInt2 = BigInteger.ONE;
            displaySuccessIfTrue(bigInt1.divide(bigInt2).toString().equals("-27182818284590452353602"), "Remainder, (-/1)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626");
            BigInteger bigInt2 = BigInteger.ZERO;
            bigInt1.divide(bigInt2);
            displaySuccessIfTrue(false, "Remainder, invalid (-/0)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Remainder, invalid (-/0)");
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            BigInteger bigInt2 = BigInteger.ZERO;
            bigInt1.divide(bigInt2);
            displaySuccessIfTrue(false, "Remainder, invalid (0/0)");
        } catch (IllegalArgumentException IAE){
            displaySuccessIfTrue(true, "Remainder, invalid (0/0)");
        }

        showResults();
    }

    private static void test_Exponent() {
        testingMethod("exponent BigInt*10^int");

        try{
            BigInteger bigInt1 = new BigInteger("3141592653589793238");
            int exp = 2718;
            String result = "3141592653589793238";
            for(int i = 1; i <= exp; i++){
                result += "0";
            }
            displaySuccessIfTrue(bigInt1.exponent(exp).toString().equals(result), "Exponent, (+E+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("3141592653589793238");
            int exp = -2;
            String result = "31415926535897932";
            displaySuccessIfTrue(bigInt1.exponent(exp).toString().equals(result), "Exponent, (+E-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-3141592653589793238");
            int exp = 2718;
            String result = "-3141592653589793238";
            for(int i = 1; i <= exp; i++){
                result += "0";
            }
            displaySuccessIfTrue(bigInt1.exponent(exp).toString().equals(result), "Exponent, (-E+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-3141592653589793238");
            int exp = -2;
            String result = "-31415926535897932";
            displaySuccessIfTrue(bigInt1.exponent(exp).toString().equals(result), "Exponent, (-E-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            int exp = 2718;
            String result = "0";
            displaySuccessIfTrue(bigInt1.exponent(exp).toString().equals(result), "Exponent, (0E+)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = BigInteger.ZERO;
            int exp = -2718;
            String result = "0";
            displaySuccessIfTrue(bigInt1.exponent(exp).toString().equals(result), "Exponent, (0E-)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("3141592653589793238");
            int exp = 0;
            String result = "3141592653589793238";
            displaySuccessIfTrue(bigInt1.exponent(exp).toString().equals(result), "Exponent, (+E0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-3141592653589793238");
            int exp = 0;
            String result = "-3141592653589793238";
            displaySuccessIfTrue(bigInt1.exponent(exp).toString().equals(result), "Exponent, (-E0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_CompareTo() {
        testingMethod("Compare To");

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.compareTo(bigInt2) == 1, "CompareTo, (+ > +)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt2.compareTo(bigInt1) == -1, "CompareTo, (+ < +)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("31415926535897932384626433832795");
            displaySuccessIfTrue(bigInt2.compareTo(bigInt1) == 0, "CompareTo, (+ = +)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.compareTo(bigInt2) == -1, "CompareTo, (- > -)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt2.compareTo(bigInt1) == 1, "CompareTo, (- < -)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("-31415926535897932384626433832795");
            displaySuccessIfTrue(bigInt2.compareTo(bigInt1) == 0, "CompareTo, (- = -)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("-27182818284590452353602");
            displaySuccessIfTrue(bigInt1.compareTo(bigInt2) == 1, "CompareTo, (+ > -)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("27182818284590452353602");
            displaySuccessIfTrue(bigInt1.compareTo(bigInt2) == -1, "CompareTo, (- < +)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626433832795");
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.compareTo(bigInt2) == 1, "CompareTo, (+ > 0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626433832795");
            BigInteger bigInt2 = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt1.compareTo(bigInt2) == -1, "compareTo, (- < 0)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("31415926535897932384626433832796");
            displaySuccessIfTrue(bigInt2.compareTo(bigInt1) == 1, "CompareTo, (+ < + plus 1)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("31415926535897932384626433832794");
            displaySuccessIfTrue(bigInt2.compareTo(bigInt1) == -1, "CompareTo, (+ > + minus 1)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("-31415926535897932384626433832796");
            displaySuccessIfTrue(bigInt2.compareTo(bigInt1) == -1, "CompareTo, (- < - minus 1)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt1 = new BigInteger("-31415926535897932384626433832795");
            BigInteger bigInt2 = new BigInteger("-31415926535897932384626433832794");
            displaySuccessIfTrue(bigInt2.compareTo(bigInt1) == 1, "CompareTo, (- > - plus 1)");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_GetValues() {
        testingMethod("Get Values");

        try{
            BigInteger bigInt = new BigInteger("3141592653589793238");
            int[] values = new int[]{8, 3, 2, 3, 9, 7, 9, 8, 5, 3, 5, 6, 2, 9, 5, 1, 4, 1, 3};
            boolean testSuccess = true;
            for (int i = 0; i < values.length; i++){
                if(values[i] != bigInt.getValues()[i]){
                    testSuccess = false;
                }
            }
            displaySuccessIfTrue(testSuccess, "getValues, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-3141592653589793238");
            int[] values = new int[]{8, 3, 2, 3, 9, 7, 9, 8, 5, 3, 5, 6, 2, 9, 5, 1, 4, 1, 3};
            boolean testSuccess = true;
            for (int i = 0; i < values.length; i++){
                if(values[i] != bigInt.getValues()[i]){
                    testSuccess = false;
                }
            }
            displaySuccessIfTrue(testSuccess, "getValues, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.ZERO;
            boolean testSuccess = bigInt.getValues()[0] == 0;
            displaySuccessIfTrue(testSuccess, "getValues, Zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_AbsoluteValue() {
        testingMethod("absolute Value");

        try{
            BigInteger bigInt = new BigInteger("31415926535897932384626433832795");
            displaySuccessIfTrue(bigInt.absoluteValue().toString().equals("31415926535897932384626433832795"), "absoluteValue, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-31415926535897932384626433832795");
            displaySuccessIfTrue(bigInt.absoluteValue().toString().equals("31415926535897932384626433832795"), "absoluteValue, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt.absoluteValue().toString().equals("0"), "absoluteValue, Zero");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_Length() {
        testingMethod("Length");

        try{
            BigInteger bigInt = new BigInteger("31415926535897932384626433832795");
            displaySuccessIfTrue(bigInt.length() == 32, "Length, Big positive");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("+31415926535897932384626433832795");
            displaySuccessIfTrue(bigInt.length() == 32, "Length, Big positive with sign");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("-31415926535897932384626433832795");
            displaySuccessIfTrue(bigInt.length() == 32, "Length, Big negative");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = new BigInteger("+JamonYQueso31415926535897932384626433832795$$$$$");
            displaySuccessIfTrue(bigInt.length() == 32, "Length, Big positive with characters");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BigInteger bigInt = BigInteger.ZERO;
            displaySuccessIfTrue(bigInt.length() == 1, "Length, ZERO");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }
}
