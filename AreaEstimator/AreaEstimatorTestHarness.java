public class AreaEstimatorTestHarness {

    private static int attempts;
    private static int score;
    private static int sumAttempts;
    private static int sumScore;

    public static void main(String[] args) {

        // # Shape test
        test_ShapeConstructor();
        test_ShapeBoundingBox();
        test_ShapeThrowDart();
        test_ShapeThrowMillionDarts();
        test_EstimateArea();

        // # GeometryKit test
        test_GeometryKitConstructor();
        test_GeometryKitBoundingBox();
        test_GeometryKitThrowMillionDarts(); //Includes throw single dart
        test_GeometryKitEstimateAreaUnion();
        test_GeometryKitEstimateAreaIntersection();
        test_GeometryKitEstimateAreaIndividualShapes();

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

    private static void test_ShapeConstructor() {
        testingMethod("Shape Constructors");

        //Circle Constructors
        try{
            Circle circle = new Circle(0, 0, 1);
            displaySuccessIfTrue(true, "Origin, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(0, 0, 1);
            boolean test = circle.getCenter()[0] == 0;
            test = test && circle.getCenter()[1] == 0;
            displaySuccessIfTrue(test, "Origin, Circle center");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(0, 0, 1);
            boolean test = circle.getRadius() == 1;
            displaySuccessIfTrue(test, "Origin, Circle radius");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(1, 4, 2.3);
            displaySuccessIfTrue(true, "Moved, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(1, 4, 2.3);
            boolean test = circle.getCenter()[0] == 1;
            test = test && circle.getCenter()[1] == 4;
            displaySuccessIfTrue(test, "Moved, Circle center");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(1, 4, 2.3);
            boolean test = circle.getRadius() == 2.3;
            displaySuccessIfTrue(test, "Moved, Circle radius");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(-1, -10.9, 0.8);
            displaySuccessIfTrue(true, "Moved 2.0, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(-1, -10.9, 0.8);
            boolean test = circle.getCenter()[0] == -1;
            test = test && circle.getCenter()[1] == -10.9;
            displaySuccessIfTrue(test, "Moved 2.0, Circle center");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(-1, -10.9, 0.8);
            boolean test = circle.getRadius() == 0.8;
            displaySuccessIfTrue(test, "Moved 2.0, Circle radius");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(3, -2, -1);
            displaySuccessIfTrue(false, "Illegal, Circle r < 0");
        } catch (IllegalArgumentException ILI){
            displaySuccessIfTrue(true, "Illegal, Circle r < 0");
        }

        try{
            Circle circle = new Circle(3, -2, 0);
            displaySuccessIfTrue(false, "Illegal, Circle r = 0");
        } catch (IllegalArgumentException ILI){
            displaySuccessIfTrue(true, "Illegal, Circle r = 0");
        }

        //Constructor Triangle

        try{
            Triangle triangle = new Triangle(0, 0, 0, 1, 1, 0);
            displaySuccessIfTrue(true, "Origin, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(0, 0, 0, 1, 1, 0);
            boolean test = triangle.getCoordinates()[0][0] == 0;
            test = test && triangle.getCoordinates()[0][1] == 0;
            test = test && triangle.getCoordinates()[1][0] == 0;
            test = test && triangle.getCoordinates()[1][1] == 1;
            test = test && triangle.getCoordinates()[2][0] == 1;
            test = test && triangle.getCoordinates()[2][1] == 0;
            displaySuccessIfTrue(test, "Origin, Triangle corners");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(1, 1, 3, 4, 5, 6);
            displaySuccessIfTrue(true, "Moved, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(1, 1, 3, 4, 5, 6);
            boolean test = triangle.getCoordinates()[0][0] == 1;
            test = test && triangle.getCoordinates()[0][1] == 1;
            test = test && triangle.getCoordinates()[1][0] == 3;
            test = test && triangle.getCoordinates()[1][1] == 4;
            test = test && triangle.getCoordinates()[2][0] == 5;
            test = test && triangle.getCoordinates()[2][1] == 6;
            displaySuccessIfTrue(test, "Moved, Triangle corners");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(1, -2.2, 3, -4.4, 5, -6.6);
            displaySuccessIfTrue(true, "Moved 2.0, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(1, -2.2, 3, -4.4, 5, -6.6);
            boolean test = triangle.getCoordinates()[0][0] == 1;
            test = test && triangle.getCoordinates()[0][1] == -2.2;
            test = test && triangle.getCoordinates()[1][0] == 3;
            test = test && triangle.getCoordinates()[1][1] == -4.4;
            test = test && triangle.getCoordinates()[2][0] == 5;
            test = test && triangle.getCoordinates()[2][1] == -6.6;
            displaySuccessIfTrue(test, "Moved 2.0, Triangle corners");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(1, 1, 1, 1, 1, 1);
            displaySuccessIfTrue(false, "Illegal, Triangle repeated cns");
        } catch (IllegalArgumentException ILI){
            displaySuccessIfTrue(true, "Illegal, Triangle repeated cns");
        }

        try{
            Triangle triangle = new Triangle(1, 1, 2, 2, 3, 3);
            displaySuccessIfTrue(false, "Illegal, Triangle flat");
        } catch (IllegalArgumentException ILI){
            displaySuccessIfTrue(true, "Illegal, Triangle flat");
        }

        showResults();
    }

    private static void test_ShapeBoundingBox() {
        testingMethod("Shape Bounding Box");

        //Circle bounding box
        //{{urx, ury}, {llx, lly}}

        try{
            Circle circle = new Circle(0, 0, 1);
            boolean test = circle.getBoundingBox()[0][0] == 1;
            test = test && circle.getBoundingBox()[0][1] == 1;
            test = test && circle.getBoundingBox()[1][0] == -1;
            test = test && circle.getBoundingBox()[1][1] == -1;
            displaySuccessIfTrue(test, "Origin, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(0, 0, 6);
            boolean test = circle.getBoundingBox()[0][0] == 6;
            test = test && circle.getBoundingBox()[0][1] == 6;
            test = test && circle.getBoundingBox()[1][0] == -6;
            test = test && circle.getBoundingBox()[1][1] == -6;
            displaySuccessIfTrue(test, "Origin 2.0, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(0, 0, 6.2);
            boolean test = circle.getBoundingBox()[0][0] == 6.2;
            test = test && circle.getBoundingBox()[0][1] == 6.2;
            test = test && circle.getBoundingBox()[1][0] == -6.2;
            test = test && circle.getBoundingBox()[1][1] == -6.2;
            displaySuccessIfTrue(test, "Origin 3.0, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(-3.4, 6, 6.2);
            boolean test = circle.getBoundingBox()[0][0] == (6.2 - 3.4);
            test = test && circle.getBoundingBox()[0][1] == (6.2 + 6);
            test = test && circle.getBoundingBox()[1][0] == (-3.4 - 6.2);
            test = test && circle.getBoundingBox()[1][1] == (6 - 6.2);
            displaySuccessIfTrue(test, "Moved, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        //Bounding Box Triangle

        try{
            Triangle triangle = new Triangle(0, 0, 0, 1, 1, 0);
            boolean test = triangle.getBoundingBox()[0][0] == 1;
            test = test && triangle.getBoundingBox()[0][1] == 1;
            test = test && triangle.getBoundingBox()[1][0] == 0;
            test = test && triangle.getBoundingBox()[1][1] == 0;
            displaySuccessIfTrue(test, "Origin, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(-1, 3.3, -2, 9.8, 1, 1);
            boolean test = triangle.getBoundingBox()[0][0] == 1;
            test = test && triangle.getBoundingBox()[0][1] == 9.8;
            test = test && triangle.getBoundingBox()[1][0] == -2;
            test = test && triangle.getBoundingBox()[1][1] == 1;
            displaySuccessIfTrue(test, "Moved, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        //Bounding box Area

        try{
            Circle circle = new Circle(0, 0, 1);
            boolean test = circle.getAreaBoundingBox() == 4;
            displaySuccessIfTrue(test, "Area, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(9.4, -12, 2);
            boolean test = circle.getAreaBoundingBox() == 16;
            displaySuccessIfTrue(test, "Area 2.0, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(0, 0, 1, 0, 0, 1);
            boolean test = triangle.getAreaBoundingBox() == 1;
            displaySuccessIfTrue(test, "Area, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(-2, -2, 2, -2, 0, 2);
            boolean test = triangle.getAreaBoundingBox() == 16;
            displaySuccessIfTrue(test, "Area 2.0, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_ShapeThrowDart() {
        testingMethod("Shape Throw Dart");

        try{
            Circle circle = new Circle(0, 0, 1);
            boolean test = circle.throwDart(0, 0);
            displaySuccessIfTrue(test, "Inside, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(0, 0, 1);
            boolean test = !circle.throwDart(10, 10);
            displaySuccessIfTrue(test, "Outside, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(-10, -10, 4);
            boolean test = circle.throwDart(-12, -8.6);
            displaySuccessIfTrue(test, "Inside 2.0, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(-10, -10, 4);
            boolean test = !circle.throwDart(-12, 0);
            displaySuccessIfTrue(test, "Outside 2.0, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(0, 0, 1);
            //Method isInside check for INSIDE, edge returns false
            boolean test = !circle.throwDart(0, 1);
            displaySuccessIfTrue(test, "Edge, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(0, 0, 10.6);
            boolean test = !circle.throwDart(10.6, 0);
            displaySuccessIfTrue(test, "Edge 2.0, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(0, 0, 2, 0, 0, 2);
            boolean test = triangle.throwDart(1, 1);
            displaySuccessIfTrue(test, "Inside, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(0, 0, 2, 0, 0, 2);
            boolean test = !triangle.throwDart(-1, -1);
            displaySuccessIfTrue(test, "Outside, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(17, -2.3, -2, -9.2, 9, 0);
            boolean test = triangle.throwDart(10, -4);
            displaySuccessIfTrue(test, "Inside 2.0, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(17, -2.3, -2, -9.2, 9, 0);
            boolean test = !triangle.throwDart(10, 0);
            displaySuccessIfTrue(test, "Outside 2.0, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(0, 0, 0, 1, 1, 0);
            boolean test = !triangle.throwDart(0, 0);
            displaySuccessIfTrue(test, "Edge, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(0, 0, 0, 6.6, 1, 0);
            boolean test = !triangle.throwDart(0, 6.6);
            displaySuccessIfTrue(test, "Edge 2.0, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_ShapeThrowMillionDarts() {
        testingMethod("Shape Throwinh Million Darts (takes time...)");

        try{
            Circle circle = new Circle(0, 0, 1);
            circle.throwMillionDarts(false);
            boolean test = circle.getAttempts() == 1_000_000;
            displaySuccessIfTrue(test, "Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(0, 0, 1, 0, 0, 1);
            triangle.throwMillionDarts(false);
            boolean test = triangle.getAttempts() == 1_000_000;
            displaySuccessIfTrue(test, "Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_EstimateArea() {
        testingMethod("Shape Area Estimation (also takes time...)");

        try{
            Circle circle = new Circle(0, 0, 1);
            circle.throwMillionDarts(false);
            boolean test = (circle.estimateArea() >= 3.1) && (circle.estimateArea() <= 3.2) ;
            displaySuccessIfTrue(test, "Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(0, 0, 2);
            circle.throwMillionDarts(false);
            boolean test = (circle.estimateArea() >= (4*3.1)) && (circle.estimateArea() <= (4*3.2)) ;
            displaySuccessIfTrue(test, "Circle 2.0");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(-1.2, 9.99, 2);
            circle.throwMillionDarts(false);
            boolean test = (circle.estimateArea() >= (4*3.1)) && (circle.estimateArea() <= (4*3.2)) ;
            displaySuccessIfTrue(test, "Circle 3.0");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(0, 0, 1, 0, 0, 1);
            triangle.throwMillionDarts(false);
            boolean test = (triangle.estimateArea() >= (0.4)) && (triangle.estimateArea() <= (0.6)) ;
            displaySuccessIfTrue(test, "Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Triangle triangle = new Triangle(-2, 0, 1, 0, 0, 3);
            triangle.throwMillionDarts(false);
            boolean test = (triangle.estimateArea() >= (4.3)) && (triangle.estimateArea() <= (4.6)) ;
            displaySuccessIfTrue(test, "Triangle 2.0");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_GeometryKitConstructor() {
        testingMethod("GeometryKit construction");

        try{
            Shape[] shapes = {new Circle(0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            displaySuccessIfTrue(true, "1 Element, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            displaySuccessIfTrue(true, "1 Element, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1), new Circle(0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            displaySuccessIfTrue(true, "2 Elements, Circle and Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = new Shape[20];
            for(int i = 0; i < 20; i++){
                shapes[i] = new Circle(0, 0, 1);
            }
            GeometryKit gK = new GeometryKit(shapes);
            displaySuccessIfTrue(true, "20 Elements, Circles");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = new Shape[20];
            for(int i = 0; i < 20; i++){
                shapes[i] = new Triangle(0, 0, 1, 0, 0, 1);
            }
            GeometryKit gK = new GeometryKit(shapes);
            displaySuccessIfTrue(true, "20 Elements, Triangles");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = new Shape[20];
            for(int i = 0; i < 10; i++){
                shapes[2*i] = new Triangle(-1, -1, i, -2, 7, i);
                shapes[2*i + 1] = new Circle(0, 0, i + 1);
            }
            GeometryKit gK = new GeometryKit(shapes);
            displaySuccessIfTrue(true, "20 Elements, Circles and Triangles");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = new Shape[20];
            GeometryKit gK = new GeometryKit(shapes);
            displaySuccessIfTrue(false, "20 Illegal Elements, Null");
        } catch (IllegalArgumentException ILE){
            displaySuccessIfTrue(true, "20 Illegal Elements, Null");
        }

        showResults();
    }

    private static void test_GeometryKitBoundingBox() {
        testingMethod("GeometryKit Bounding Box");

        try{
            Shape[] shapes = {new Circle(0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            boolean test = gK.getBoundingBox()[0][0] == 1;
            test = test && gK.getBoundingBox()[0][1] == 1;
            test = test && gK.getBoundingBox()[1][0] == -1;
            test = test && gK.getBoundingBox()[1][1] == -1;
            displaySuccessIfTrue(test, "1 Element, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Circle(0, 0, 1), new Circle(4, 4, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            boolean test = gK.getBoundingBox()[0][0] == 5;
            test = test && gK.getBoundingBox()[0][1] == 5;
            test = test && gK.getBoundingBox()[1][0] == -1;
            test = test && gK.getBoundingBox()[1][1] == -1;
            displaySuccessIfTrue(test, "2 Element, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(-1, -1, 1, 1, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            boolean test = gK.getBoundingBox()[0][0] == 1;
            test = test && gK.getBoundingBox()[0][1] == 1;
            test = test && gK.getBoundingBox()[1][0] == -1;
            test = test && gK.getBoundingBox()[1][1] == -1;
            displaySuccessIfTrue(test, "1 Element, Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(-10, -10, -10, -8, -8, -10), new Triangle(10, 10, 10, 8, 8, 10)};
            GeometryKit gK = new GeometryKit(shapes);
            boolean test = gK.getBoundingBox()[0][0] == 10;
            test = test && gK.getBoundingBox()[0][1] == 10;
            test = test && gK.getBoundingBox()[1][0] == -10;
            test = test && gK.getBoundingBox()[1][1] == -10;
            displaySuccessIfTrue(test, "2 Element, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            //This one test ir pretty harsh
            // - 20 unique shapes
            // - Circles and Triangles
            // - with Overlaps
            Shape[] shapes = new Shape[20];
            for(int i = 0; i < 10; i++){
                shapes[2*i] = new Triangle(-1, -1, i, -2, 7, i);
                shapes[2*i + 1] = new Circle(0, 0, i + 1);
            }
            GeometryKit gK = new GeometryKit(shapes);
            boolean test = gK.getBoundingBox()[0][0] == 10;
            test = test && gK.getBoundingBox()[0][1] == 10;
            test = test && gK.getBoundingBox()[1][0] == -10;
            test = test && gK.getBoundingBox()[1][1] == -10;
            displaySuccessIfTrue(test, "20 Element, Circles and Triangles");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_GeometryKitThrowMillionDarts() {
        testingMethod("GeometryKit Throwing Million Darts");

        try{
            Shape[] shapes = {new Circle(0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.getAttempts() == 1_000_000, "Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 2, 0, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.getAttempts() == 1_000_000, "Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 2, 0, 0, 2), new Circle(0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.getAttempts() == 1_000_000, "Circle and Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = new Shape[20];
            for(int i = 0; i < 10; i++){
                shapes[2*i] = new Triangle(-1, -1, i, -2, 7, i);
                shapes[2*i + 1] = new Circle(0, 0, i + 1);
            }
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.getAttempts() == 1_000_000, "20 Element, Circles and Triangles");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_GeometryKitEstimateAreaUnion() {
        testingMethod("GeometryKit Area Union (Yes, more waiting...)");

        try{
            Shape[] shapes = {new Circle(0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= 3.1 && gK.estimateUnion() <= 3.2, "1 Shape, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= 0.4 && gK.estimateUnion() <= 0.6, "1 Shape, Trinalge");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Circle(0, 0, 1), new Circle(0, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (4*3.1) && gK.estimateUnion() <= (4*3.2), "2 Shapes, Circle inside Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Circle(0, 0, 2), new Circle(1, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (16.5) && gK.estimateUnion() <= (16.6), "2 Shapes, Circle intersects Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Circle(0, 0, 2), new Circle(5, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (2*4*3.1) && gK.estimateUnion() <= (2*4*3.2), "2 Shapes, Circle outside Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1), new Triangle(0, 0, 2, 0, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (1.9) && gK.estimateUnion() <= (2.1), "2 Shapes, Triangle inside Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1), new Triangle(0, 0, 1, 0, 1, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (0.7) && gK.estimateUnion() <= (0.8), "2 Shapes, Triangle intersects Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1), new Triangle(2, 0, 1, 0, 1, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (0.9) && gK.estimateUnion() <= (1.1), "2 Shapes, Triangle outside Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 2, 0, 0, 2), new Circle(0, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (4*3.1) && gK.estimateUnion() <= (4*3.2), "2 Shapes, Triangle inside Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 2, 0, 0, 2), new Circle(0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (4.3) && gK.estimateUnion() <= (4.4), "2 Shapes, Circle intersects Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 2, 0, 0, 2), new Circle(-12, 9.8, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (5.1) && gK.estimateUnion() <= (5.2), "2 Shapes, Circle outside Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 2, 0, 0, 2), new Circle(1, 5, 3.3), new Triangle(4, 4, 2, -2, 5.5, 2), new Circle(12, 0, 6.6), new Triangle(0, 1, 2, -5, -5, -6), new Circle(-12, 0, 6)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateUnion() >= (313) && gK.estimateUnion() <= (316), "6 Shapes!, Circles and Triangles");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_GeometryKitEstimateAreaIntersection() {
        testingMethod("GeometryKit Area Intersecion");

        try{
            Shape[] shapes = {new Circle(0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() >= 3.1 && gK.estimateIntersection() <= 3.2, "1 Shape, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() >= 0.4 && gK.estimateIntersection() <= 0.6, "1 Shape, Trinalge");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Circle(0, 0, 1), new Circle(0, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() >= (3) && gK.estimateIntersection() <= (3.5), "2 Shapes, Circle inside Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Circle(0, 0, 2), new Circle(1, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() >= (8) && gK.estimateIntersection() <= (9), "2 Shapes, Circle intersects Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Circle(0, 0, 2), new Circle(5, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() == 0, "2 Shapes, Circle outside Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1), new Triangle(0, 0, 2, 0, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() >= (0.4) && gK.estimateIntersection() <= (0.6), "2 Shapes, Triangle inside Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1), new Triangle(0, 0, 1, 0, 1, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() >= (0.2) && gK.estimateIntersection() <= (0.3), "2 Shapes, Triangle intersects Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 1, 0, 0, 1), new Triangle(2, 0, 1, 0, 1, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() == 0, "2 Shapes, Triangle outside Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 2, 0, 0, 2), new Circle(0, 0, 2)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() >= (1.9) && gK.estimateIntersection() <= (2.1), "2 Shapes, Triangle inside Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 2, 0, 0, 2), new Circle(0, 0, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() >= (0.7) && gK.estimateIntersection() <= (0.9), "2 Shapes, Circle intersects Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(0, 0, 2, 0, 0, 2), new Circle(-12, 9.8, 1)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() == 0, "2 Shapes, Circle outside Triangle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(-8, -8, 7, 0, 0, 2), new Circle(1, 5, 3.3), new Triangle(4, 4, 2, -2, 5.5, 2), new Circle(12, 0, 6.6), new Triangle(0, 1, 2, -5, -5, -6), new Circle(-12, 0, 6)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() == 0, "6 Shapes!, Circles and Triangles");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Shape[] shapes = {new Triangle(-8, -8, 7, 0, 0, 7), new Circle(1, 0, 5.3), new Triangle(4, 4, 2, -2, 4, -4)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(gK.estimateIntersection() >= 5.9 && gK.estimateIntersection() <= 6.1 , "3 Shapes, all Intersect");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_GeometryKitEstimateAreaIndividualShapes() {
        testingMethod("GeometryKit Area Individual Shapes");

        try{
            Shape[] shapes = {new Triangle(-8, -8, 7, 0, 0, 2), new Circle(1, 5, 3.3), new Triangle(4, 4, 2, -2, 5.5, 2), new Circle(12, 0, 6.6), new Triangle(0, 1, 2, -5, -5, -6), new Circle(-12, 0, 6), new Triangle(-8, -8, 7, 0, 0, 2), new Circle(1, 5, 3.3), new Triangle(4, 4, 2, -2, 5.5, 2), new Circle(12, 0, 6.6), new Triangle(0, 1, 2, -5, -5, -6), new Circle(-12, 0, 6)};
            GeometryKit gK = new GeometryKit(shapes);
            gK.throwMillionDarts(false);
            displaySuccessIfTrue(shapes[0].estimateArea() >= 42 && shapes[0].estimateArea() <= 44.5 , "Shape 1, Triangle");
            displaySuccessIfTrue(shapes[1].estimateArea() >= 33 && shapes[1].estimateArea() <= 36 , "Shape 2, Circle");
            displaySuccessIfTrue(shapes[2].estimateArea() >= 5 && shapes[2].estimateArea() <= 8 , "Shape 3, Triangle");
            displaySuccessIfTrue(shapes[3].estimateArea() >= 136 && shapes[3].estimateArea() <= 138 , "Shape 4, Circle");
            displaySuccessIfTrue(shapes[4].estimateArea() >= 20 && shapes[4].estimateArea() <= 24 , "Shape 5, Triangle");
            displaySuccessIfTrue(shapes[5].estimateArea() >= 112 && shapes[5].estimateArea() <= 114 , "Shape 6, Circle");
            displaySuccessIfTrue(shapes[6].estimateArea() >= 41.5 && shapes[6].estimateArea() <= 44.5 , "Shape 7, Triangle");
            displaySuccessIfTrue(shapes[7].estimateArea() >= 32 && shapes[7].estimateArea() <= 36 , "Shape 8, Circle");
            displaySuccessIfTrue(shapes[8].estimateArea() >= 5 && shapes[8].estimateArea() <= 8 , "Shape 9, Triangle");
            displaySuccessIfTrue(shapes[9].estimateArea() >= 136 && shapes[9].estimateArea() <= 138 , "Shape 10, Circle");
            displaySuccessIfTrue(shapes[10].estimateArea() >= 20 && shapes[10].estimateArea() <= 24 , "Shape 11, Triangle");
            displaySuccessIfTrue(shapes[11].estimateArea() >= 112 && shapes[11].estimateArea() <= 114 , "Shape 12, Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

}
