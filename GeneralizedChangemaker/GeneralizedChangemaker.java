public class GeneralizedChangemaker {

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            return;
        }

        try {
            int amount = Integer.parseInt(args[0]);
            if (amount < 0) {
                System.out.println("Change cannot be made for negative amounts.");
                System.out.println();
                printUsage();
                return;
            }

            String[] denominationStrings = args[1].split(",");
            int[] denominations = new int[denominationStrings.length];

            for (int i = 0; i < denominations.length; i++) {
                denominations[i] = Integer.parseInt(denominationStrings[i]);
                if (denominations[i] <= 0) {
                    System.out.println("Denominations must all be greater than zero.");
                    System.out.println();
                    printUsage();
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (denominations[j] == denominations[i]) {
                        System.out.println("Duplicate denominations are not allowed.");
                        System.out.println();
                        printUsage();
                        return;
                    }
                }
            }

            Tuple change = makeChangeWithDynamicProgramming(denominations, amount);
            if (change.isImpossible()) {
                System.out.println("It is impossible to make " + amount + " cents with those denominations.");
            } else {
                int coinTotal = change.total();
                System.out.println(amount + " cents can be made with " + coinTotal + " coin" +
                        getSimplePluralSuffix(coinTotal) + " as follows:");

                for (int i = 0; i < denominations.length; i++) {
                    int coinCount = change.getElement(i);
                    System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" +
                            getSimplePluralSuffix(coinCount));
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Denominations and amount must all be integers.");
            System.out.println();
            printUsage();
        }
    }

    public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int amount) {
        /*
        * Return a Tuple with the amount of the corresponding coin in each position
        * for makeChangeWithDynamicProgramming({ 25, 10, 5, 1 }, 99)
        * return {3, 2, 0, 4}
        */
        Tuple[][] memo = new Tuple[denominations.length][amount + 1];

        for(int row = 0; row < denominations.length; row++){
            for(int column = 0; column <= amount; column++){
                //colum also corresponds to money

                //The data to be used in that entry
                int[] data = new int[denominations.length];

                if(column == 0){
                    //To make 0 we just need all elements 0
                    memo[row][0] = new Tuple(denominations.length);
                    continue;
                }
                //if i can use one of these coins we add it to the Tuple
                if( (column - denominations[row]) >= 0 ){

                    data[row] = 1;
                    memo[row][column] = new Tuple(data);
                    //Add the solution for the remainder
                    if( memo[row][column - denominations[row]].isImpossible() ){
                        memo[row][column] = Tuple.IMPOSSIBLE;
                    } else {
                        memo[row][column] = memo[row][column].add(memo[row][column - denominations[row]]);
                    }

                } else {
                    memo[row][column] = Tuple.IMPOSSIBLE;
                }

                //if the prevous solution is better we use that one
                //check if we are not int he first row
                if(row > 0){
                    if(!memo[row-1][column].isImpossible() && memo[row][column].total() > memo[row-1][column].total() || memo[row][column].isImpossible()){
                        memo[row][column] = memo[row-1][column];
                    }
                }
            }
        }
        return memo[denominations.length - 1][amount];

    }

    private static void printUsage() {
        System.out.println("Usage: java GeneralizedChangemaker <amount> <denominations>");
        System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
        System.out.println("  - <amount> is the amount for which to make change");
    }

    private static String getSimplePluralSuffix(int count) {
        return count == 1 ? "" : "s";
    }

}
