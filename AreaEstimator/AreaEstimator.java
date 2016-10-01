public class AreaEstimator{

    //         /change TESTING if you want to run the actual program  \
    //       /     however for the test harness it has to be true     |
    //      | otherwise it will output Dartboard stuff to the console /
    //                               V
    final static boolean TESTING = true;
    private static final int MAX_SHAPES = 20;
    private static final double COOR_CONSTRAINT = 20;
    private static Shape[] shapes = new Shape[MAX_SHAPES];
    private static boolean shouldRun = true;

    public static void main(String[] args){

    int numOfShapes = 0;

        //Search for keywords "circle" and "triangle" for the initial construction of shapes
        //then check if the parameters given after match the expected paramenters for the construction of the shape
        for(int i = 0; i  < args.length; i++){
            switch (args[i]){
                case "circle":
                    try {
                        shapes[numOfShapes] = new Circle(Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]));
                        numOfShapes ++;
                    } catch (NumberFormatException e) {
                        System.out.println("\r\n## Please only provide double values for the construction of circles");
                        shouldRun = false;
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("\r\n## Please provide 3 arguments for the construction of circles");
                        shouldRun = false;
                    } catch (IllegalArgumentException e){
                        System.out.println("\r\n## Please provide x, y and r>0 as boolean values \r\n- make sure the shape is not bigger than 20 x 20 \r\n(can be changed in Shape.java)");
                        shouldRun = false;
                    }
                    break;
                case "triangle":
                    try {
                        shapes[numOfShapes] = new Triangle(Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]), Double.parseDouble(args[i+4]), Double.parseDouble(args[i+5]), Double.parseDouble(args[i+6]));
                        numOfShapes ++;
                    } catch (NumberFormatException e) {
                        System.out.println("\r\n## Please only provide Double values for the construction of triangles");
                        shouldRun = false;
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("\r\n## Please provide 6 arguments for the construction of triangles");
                        shouldRun = false;
                    } catch (IllegalArgumentException e){
                        System.out.println("\r\n## Please provide x0, y0, x1, y1, x2, y2 as double values \r\n- make sure the corners are unique \r\n- and that the shape is not bigger than 20 x 20 \r\n(can be changed in Shape.java)");
                        shouldRun = false;
                    }
                    break;
                case "polygon":
                    try {
                        // i -> polygon
                        // i + 1 -> numOfVertices
                        // i + 2 -> arg
                        int numOfVertices = Integer.parseInt(args[i+1]);
                        double[][] coordinates = new double[numOfVertices][2];

                        for(int j = 0; j < 2*numOfVertices; j++){
                            if(j % 2 == 0){
                                coordinates[(int)Math.floor(j/2)][0] = Double.parseDouble(args[i+j+2]);
                            } else {
                                coordinates[(int)Math.floor(j/2)][1] = Double.parseDouble(args[i+j+2]);
                            }
                        }

                        shapes[numOfShapes] = new Polygon(coordinates);
                        numOfShapes ++;
                    } catch (NumberFormatException e) {
                        System.out.println("\r\n## Please only provide Double values for the construction of polygons");
                        shouldRun = false;
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("\r\n## You should have provided as many value pairs as vertices specified");
                        shouldRun = false;
                    } catch (IllegalArgumentException e){
                        System.out.println("\r\n## Please specify the ammount of verices and their coordinates as follows");
                        System.out.println(" polygon number_of_vertices first_vertex_x first_vertex_y second_vertex_x second_vertex_y ...");
                        shouldRun = false;
                    }
                    break;
            }
        }
        //check if we actually added any shape to shapes[]
        if(shapes[0] == null){
            System.out.println("\r\n## No Correct shape was detected");
            shouldRun = false;
        }

        if(shouldRun){
            if(numOfShapes == 1){
                //!TESTING means, if testing we do not want the output used for Dartboard
                shapes[0].throwMillionDarts(!TESTING);
                System.out.println(shapes[0].toString());
            } else {
                shapes = cleanShapeArray(shapes, numOfShapes);
                GeometryKit geometryKit = new GeometryKit(shapes);
                geometryKit.throwMillionDarts(!TESTING);
                System.out.println(geometryKit.toString());
            }
        }
    }

    static private Shape[] cleanShapeArray(Shape[] shapes, int numOfShapes){
        //We change the length of shapes to that we dont have null entries
        Shape[] moment = new Shape[numOfShapes];
        for (int i = 0 ; i < numOfShapes ; i++){
            moment[i] = shapes[i];
        }
        return moment;
    }
}
