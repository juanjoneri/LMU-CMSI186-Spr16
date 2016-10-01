public class GeneralizedChangemakerTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        System.out.println("\n # USA\n");
        test_USA();
        System.out.println("\n # UK\n");
        test_UK();
        System.out.println("\n # Uruguay\n");
        test_Uruguay();
        System.out.println("\n # Euro\n");
        test_Euro();
        System.out.println("\n # Tie\n");
        test_Tie();
        System.out.println("\n # Impossible\n");
        test_Impossible();
        System.out.println("\n # Cero\n");
        test_Cero();

        System.out.println("\n ## (" + successes + "/" + attempts + ") tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    public static void test_USA() {
        int[] localDenominations = new int[] { 25, 10, 5, 1 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 99);
        try {
            displaySuccessIfTrue(3 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(4 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_UK() {
        int[] localDenominations = new int[] { 1, 2, 5, 10, 20, 50 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 13223);
        try {
            displaySuccessIfTrue(1 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(264 == result.getElement(5));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_Uruguay() {
        int[] localDenominations = new int[] { 5, 2, 1, 50, 10 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 10);
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_Euro() {
        int[] localDenominations = new int[] { 2, 1, 50, 20, 10 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 72);
        try {
            displaySuccessIfTrue(1 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_Tie() {
        int[] localDenominations = new int[] { 2, 6, 10 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 12);
        try {
            displaySuccessIfTrue(1 == result.getElement(0) || 0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(1) || 0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(2) || 0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        localDenominations = new int[] { 4, 1, 10 };

        result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 12);
        try {
            displaySuccessIfTrue(3 == result.getElement(0) || 0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(1) || 0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(2) || 0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_Impossible() {
        int[] localDenominations = new int[] { 2109, 71, 65, 100 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 128);
        try {
            displaySuccessIfTrue(result.isImpossible());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 12);
        try {
            displaySuccessIfTrue(result.isImpossible());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 64);
        try {
            displaySuccessIfTrue(result.isImpossible());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 66);
        try {
            displaySuccessIfTrue(result.isImpossible());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 120);
        try {
            displaySuccessIfTrue(result.isImpossible());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_Cero() {
        int[] localDenominations = new int[] { 1, 12, 7, 9 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(localDenominations, 0);
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

}
