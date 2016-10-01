public class Polygon extends Shape{

    //                   (you may change that nomber)
    //                                 V      (and that one)
    private static int MAX_VERTICES = 20; //       V
    public static final double COOR_CONSTRAINT = 20d;
    double[][] coordinates = new double[MAX_VERTICES][2];
    int numOfVertices = 0;

    public Polygon(double[][] coordinates){
        super();
        if(isValidInput(coordinates)){

            numOfVertices = coordinates.length;
            //this way they will allways get overriten by any of the actual verices
            double maxX = -COOR_CONSTRAINT;
            double maxY = -COOR_CONSTRAINT;
            double minX = COOR_CONSTRAINT;
            double minY = COOR_CONSTRAINT;

            //copy the values of the vertices to the local variable
            for(int i = 0; i < numOfVertices; i++){
                for(int j = 0; j < 2; j++){

                    double var = coordinates[i][j];
                    this.coordinates[i][j] = var;

                    //find xy coordinates of the bb
                    if(j == 0){
                        maxX = (maxX < var) ? var : maxX;
                        minX = (minX > var) ? var : minX;
                    } else {
                        maxY = (maxY < var) ? var : maxY;
                        minY = (minY > var) ? var : minY;
                    }
                }
            }
            setBoundingBox(maxX, maxY, minX, minY);

        } else {
            throw new IllegalArgumentException("Invalid parameters for a Polygon");
        }
    }

    private  boolean isValidInput(double[][] input){
        int numOfVertices = input.length;

        boolean areUniqueVertices = true;
        for(int i = 0; i < numOfVertices - 1; i++){
            for(int j = (i + 1); j < numOfVertices; j++){

                areUniqueVertices = ((input[i][0] != input[j][0]) || (input[i][1] != input[j][1])) && areUniqueVertices;
            }
        }

        boolean isInsideConstraint = true;
        for(int i = 0; i < numOfVertices; i++){
            isInsideConstraint = isInsideConstraint &&  Math.abs(input[i][0]) < COOR_CONSTRAINT;
            isInsideConstraint = isInsideConstraint &&  Math.abs(input[i][1]) < COOR_CONSTRAINT;
        }

        //X product of 0
        boolean isPolygonNotFlat = false;
        double x0 = input[0][0];
        double y0 = input[0][1];
        for(int i = 1; i < numOfVertices - 1; i++){

            double x1 = input[i][0];
            double y1 = input[i][1];
            double x2 = input[i+1][0];
            double y2 = input[i+1][1];

            //only 2 non-aligned vertices sufice
            //if ever the det != 0 => notFlat = true
            isPolygonNotFlat = isPolygonNotFlat || (det(x1 - x0, y1 -y0, x2 - x0, y2 - y0) != 0);
        }

        return isPolygonNotFlat && isInsideConstraint && areUniqueVertices;

    }

    public boolean isInside(double x, double y){
        /*
        compute the sum of the angles made between the test point and each pair of points making up the polygon.
        If it is 2*pi, then it is an interior point.
        If it is 0, then it is an exterior point.
        */
        double angle = 0;
        for(int i = 0; i < numOfVertices; i++){
            double x0 = coordinates[i][0];
            double y0 = coordinates[i][1];
            //with the mod we make sure we cover the angle between last and first
            double x1 = coordinates[(i + 1) % numOfVertices][0];
            double y1 = coordinates[(i + 1) % numOfVertices][1];

            angle += angle(x, y, x0, y0, x1, y1);
        }

        //System.out.println("= " + angle+ "\n");
        return angle > 6.28318 && angle < 6.28319 ;
    }

    private double det( double x0, double y0, double x1, double y1 ){
        return ((x0 * y1) - (y0 * x1));
    }

    private double dot(double x0, double y0, double x1, double y1 ) {
        return (x0 * x1)+(y0 * y1);
    }

    private double angle(double xC, double yC, double x0, double y0, double x1, double y1){
        //computes the angle between the vectors u and v
        //we assume all this operations are defined because the shapes was created
        //which means it passed the isValidInput method
        double vX = x0 - xC;
        double vY = y0 - yC;
        double uX = x1 - xC;
        double uY = y1 - yC;

        double magV = Math.sqrt(vX*vX + vY*vY);
        double magU = Math.sqrt(uX*uX + uY*uY);

        // |v X u| = v * u * sin(ang)
        //**for some reason I cant figure out this approach does not work**
        //double sinAngle = (det(vX, vY, uX, uY)) / (magU * magV);
        //return Math.asin(sinAngle);

        // v . u = v * u * cos(ang)
        double cosAngle = dot(vX, vY, uX, uY) / (magU * magV);
        return Math.acos(cosAngle);

    }

    public String toString(){
        //Polygon [C1 = () C2 = () ...]
        String name = "\nPolygon [";

        for(int i = 0; i < numOfVertices; i ++){
            name += "C" + (i + 1) + " = (" + this.coordinates[i][0] + ", " + this.coordinates[i][1] + ")";
        }
        name += "]";

        if(super.getAttempts() != 0){
            name += "\n > Area = " + super.estimateArea();
        }
        return name;
    }

}
