public abstract class Shape{

    public static final double COOR_CONSTRAINT = 20d;
    public static final double MILLION = 1_000_000;

    private double attempts, hits, fails, areaBoundingBox;
    //has two R2 pairs for corners
    //{{urx, ury}, {llx, lly}}
    private double[][] boundingBox;

    //NEED TO BE OVERRITEN BY CHILD --------------------------------------------

    public Shape() {
        //call super Shape()
        //then add functionality
        this.attempts = 0;
        this.hits = 0;
        this.fails = 0;
        this.boundingBox = new double[2][2];
    }

    abstract boolean isInside(double x, double y);
    //This method depends on the characteristics of the specific shape

    //ALREADY IMPLEMENTED METHODS ----------------------------------------------

    public boolean throwDart(double x, double y) {
        //No need to override
        if(isInsideBoundingBox(x, y)){
            //we only want to consider the darts folling inside this bb
            //because of the way the calculation of the area is carried out
            attempts ++;
            if(isInside(x,y)){
                hits ++;
                return true;
            } else {
                fails ++;
                return false;
            }
        }
        //it in not in the bb means it is not in the shape
        return false;
    }

    public void throwMillionDarts(boolean verbose){
      //Throws a million random darts inside of boundingBox
      //Not compatible with multiple shapes
      if(verbose) { System.out.println("start"); }
        for (long i = 0; i < MILLION; i++){
            double[] ranDart = this.randomDart();

            String textToOutput = ranDart[0]+ " " + ranDart[1] + " ";
            textToOutput += throwDart(ranDart[0], ranDart[1]) ? "in" : "out";
            if(verbose) { System.out.println(textToOutput); }
        }
        if(verbose) { System.out.println("end"); }
    }

    public double estimateArea() {
        //No need to override
        if(attempts != 0){
            return areaBoundingBox*(hits/attempts);
        }
        else {
            return 0d;
        }
    }

    private boolean isInsideBoundingBox(double x, double y){
        //{{urx, ury}, {llx, lly}}
        boolean result = false;
        result = x < boundingBox[0][0] && x > boundingBox[1][0];
        result = result && y < boundingBox[0][1] && y > boundingBox[1][1];
        return result;
    }

    public double[] randomDart(){
      //Returns the coordinates of a random dart inside the boundingBox
      double[] coordinates = new double[2];
      //min + random(0-1) * length
      coordinates[0] = this.boundingBox[1][0] + ( Math.random() * (boundingBox[0][0] - boundingBox[1][0]) );
      coordinates[1] = this.boundingBox[1][1] + ( Math.random() * (boundingBox[0][1] - boundingBox[1][1]) );
      return coordinates;
    }

    public void setBoundingBox(double urx, double ury, double llx, double lly){
        //ur -> upper right | ll -> lower left

        //check if ul is actually uper left and lr is actually lr
        boolean areValuesCorrect = (urx > llx) && (ury > lly);
        //boundingBox cant exceed COOR_CONSTRAINT
        boolean isInsideConstraint =  (Math.abs(urx) <= COOR_CONSTRAINT && Math.abs(llx) <= COOR_CONSTRAINT && Math.abs(ury) <= COOR_CONSTRAINT && Math.abs(lly) <= COOR_CONSTRAINT);

        if( areValuesCorrect && isInsideConstraint ){
            this.boundingBox = new double[][] {{urx, ury}, {llx, lly}};
        } else {
            throw new IllegalArgumentException("Illegal boundingBox");
        }
        //|(x1-x2)(y1-y2)| is the area of a rectangle
        this.areaBoundingBox = Math.abs((boundingBox[0][0] - boundingBox[1][0]) * (boundingBox[0][1] - boundingBox[1][1]));
    }

    //GETTERS ------------------------------------------------------------------

    public double getAttempts(){
        return attempts;
    }
    public double getHits(){
        return hits;
    }
    public double getFails(){
        return fails;
    }
    public double[][] getBoundingBox(){
        return boundingBox;
    }
    public double getAreaBoundingBox(){
        return this.areaBoundingBox;
    }

}
