public class Triangle extends Shape{

    double[][] coordinates = new double[3][2];

    public Triangle(double x0, double y0, double x1, double y1, double x2, double y2){
        super();
        if( isValidInput(x0, y0, x1, y1, x2, y2) ){
            this.coordinates[0][0] = x0;
            this.coordinates[0][1] = y0;
            this.coordinates[1][0] = x1;
            this.coordinates[1][1] = y1;
            this.coordinates[2][0] = x2;
            this.coordinates[2][1] = y2;
            setBoundingBox(max(x0, x1, x2), max(y0, y1, y2), min(x0, x1, x2), min(y0, y1, y2));
        } else {
            throw new IllegalArgumentException("Invalid parameters for a triangle");
        }
    }

    private boolean isValidInput(double x0, double y0, double x1, double y1, double x2, double y2){

        boolean areUniqueValues = ((x0 != x1) || (y0 != y1));
        areUniqueValues = areUniqueValues && ((x1 != x2) || (y1 != y2));
        areUniqueValues = areUniqueValues && ((x0 != x2) || (y0 != y2));

        //X product of 0
        boolean isTriangleFlat = det((x1-x0),(y1-y0),(x2-x0),(y2-y0)) == 0;

        boolean isInsideConstraint = max(x0, x1, x2) < COOR_CONSTRAINT;
        isInsideConstraint = isInsideConstraint && Math.abs(min(x0, x1, x2)) < COOR_CONSTRAINT;
        isInsideConstraint = isInsideConstraint && max(y0, y1, y2) < COOR_CONSTRAINT;
        isInsideConstraint = isInsideConstraint && Math.abs(min(y0, y1, y2)) < COOR_CONSTRAINT;

        return (areUniqueValues && isInsideConstraint && !isTriangleFlat);
    }

    public boolean isInside(double x, double y){
        //the x product of the vertices with the point all need to be the same sign
        double vx = coordinates[1][0] - coordinates[0][0];
        double vy = coordinates[1][1] - coordinates[0][1];

        double ux = x - coordinates[0][0];
        double uy = y - coordinates[0][1];

        byte sign0 = detSign(vx, vy, ux, uy);

        vx = coordinates[2][0] - coordinates[1][0];
        vy = coordinates[2][1] - coordinates[1][1];

        ux = x - coordinates[1][0];
        uy = y - coordinates[1][1];

        byte sign1 = detSign(vx, vy, ux, uy);

        vx = coordinates[0][0] - coordinates[2][0];
        vy = coordinates[0][1] - coordinates[2][1];

        ux = x - coordinates[2][0];
        uy = y - coordinates[2][1];

        byte sign2 = detSign(vx, vy, ux, uy);

        //check if they are all equal or cero (meaning its overlaps with the side)
        boolean result = (sign0 == sign1 || sign0 == 0 || sign1 == 0);
        result = result && (sign0 == sign2 || sign0 == 0 || sign2 == 0);
        result = result && (sign1 == sign2 || sign1 == 0 || sign2 == 0);

        return result;
    }

    private double det( double x0, double y0, double x1, double y1 ){
        return ((x0 * y1) - (y0 * x1));
    }

    private byte detSign( double x0, double y0, double x1, double y1 ){
        if(det(x0, y0, x1, y1) == 0d){
            return 0;
        } else {
            return (det(x0, y0, x1, y1) > 0) ? (byte) 1 : (byte) -1;
        }
    }

    private double max(double a, double b, double c){
        double max = ( a > b ) ? a : b;
        max = ( max > c ) ? max : c;
        return max;
    }

    private double min(double a, double b, double c){
        double min = ( a < b ) ? a : b;
        min = ( min < c ) ? min : c;
        return min;
    }

    public String toString(){
        String name = "\nTriangle [C1 = (" + this.coordinates[0][0] + ", " + this.coordinates[0][1] + ") ";
        name += "C2 = (" + this.coordinates[1][0] + ", " + this.coordinates[1][1] + ") ";
        name += "C3 = (" + this.coordinates[2][0] + ", " + this.coordinates[2][1] + ")]";
        if(super.getAttempts() != 0){
            name += "\n > Area = " + super.estimateArea();
        }
        return name;
    }

    public double[][] getCoordinates(){
        return this.coordinates;
    }
}
