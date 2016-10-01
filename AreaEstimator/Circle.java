public class Circle extends Shape{

    double radius;
    double[] center = new double[2];

    public Circle(double x, double y, double r){
        super();
        if( isValidInput(x,y,r) ){
            this.center[0] = x;
            this.center[1] = y;
            this.radius = r;
            this.setBoundingBox(x+r, y+r, x-r, y-r);
        } else {
            throw new IllegalArgumentException("Invalid parameters for a circle");
        }
    }

    public boolean isValidInput(double x, double y, double r){
        boolean isRadiousValid = (r > 0);
        boolean isInsideConstraint = ((x+r < COOR_CONSTRAINT) && (y+r < COOR_CONSTRAINT) && (x-r > -COOR_CONSTRAINT) && (y-r > -COOR_CONSTRAINT));
        return (isInsideConstraint && isRadiousValid);
    }

    public boolean isInside(double x, double y){
        //the distance to the center is smaller than r
        //(x-x0)^2 + (y-y0)^2 <= r^2
        return (((x-center[0])*(x-center[0]) + (y-center[1])*(y-center[1])) <= (radius*radius));
    }

    public String toString(){
        String name = "\nCircle [r = " + this.radius + " c = (" + this.center[0] + ", " + this.center[1] + ")]";
        if(super.getAttempts() != 0){
            name += "\n > Area = " + super.estimateArea();
        }
        return name;
    }

    public double getRadius(){
        return this.radius;
    }

    public double[] getCenter(){
        return this.center;
    }
}
