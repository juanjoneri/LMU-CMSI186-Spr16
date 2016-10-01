public class GeometryKit{

    private static final double COOR_CONSTRAINT = 20d;
    private static final double MILLION = 1_000_000;

    private double attempts, unionHits, intersectionHits, areaBoundingBox;
    //has two R2 pairs for corners correstponding to the smallest posible bb
    //{{urx, ury}, {llx, lly}}
    private double[][] boundingBox;
    private Shape[] shapes;

    public GeometryKit(Shape[] shapes){
        //Constracts an object that handles a group of Shapes better
        //So that we do not have to handle this thing in main
        for(Shape sh : shapes){
            if(sh == null){
                throw new IllegalArgumentException("please provide a populated array");
            }
        }
        this.shapes = shapes;
        this.boundingBox = smallestBoundingBox(shapes);
        this.areaBoundingBox = Math.abs((boundingBox[0][0] - boundingBox[1][0]) * (boundingBox[0][1] - boundingBox[1][1]));
    }

    private double[] randomDart(){
      //Returns the coordinates of a random dart inside the boundingBox
      double[] coordinates = new double[2];
      //min + random(0-1) * length
      coordinates[0] = this.boundingBox[1][0] + ( Math.random() * (boundingBox[0][0] - boundingBox[1][0]) );
      coordinates[1] = this.boundingBox[1][1] + ( Math.random() * (boundingBox[0][1] - boundingBox[1][1]) );
      return coordinates;
    }

    public void throwMillionDarts(boolean verbose){
      //Throws a million random darts inside of boundingBox
      if(verbose) { System.out.println("start"); }

      unionHits = 0;
      intersectionHits = 0;

      for (long i = 0; i < MILLION; i++){
          double[] ranDart = randomDart();

          String textToOutput = ranDart[0]+ " " + ranDart[1] + " ";

          boolean inUnion = false;
          boolean inIntersection = true;

          for(Shape sh : this.shapes){
              boolean insideShape = sh.throwDart(ranDart[0], ranDart[1]);

              inIntersection = inIntersection && insideShape;
              inUnion = inUnion || insideShape;
          }
          attempts ++;
          unionHits += inUnion ? 1 : 0;
          intersectionHits += inIntersection ? 1 : 0;

          //We want to draw the union
          textToOutput += inUnion ? "in" : "out";
          if(verbose) { System.out.println(textToOutput); }
      }

      if(verbose) { System.out.println("end"); }
    }

    private double[][] smallestBoundingBox(Shape[] shapes){
        //Returns the smallest posible bounding box surrounding all the shapes
        double[][] boundingBox = {{-COOR_CONSTRAINT, -COOR_CONSTRAINT}, {COOR_CONSTRAINT, COOR_CONSTRAINT}};
        //{{urx, ury}, {llx, lly}}

        for(Shape sh : shapes){

            if(sh != null){
                double[][] shapeBoundingBox = sh.getBoundingBox();

                if(shapeBoundingBox[0][0] > boundingBox[0][0]){
                    boundingBox[0][0] = shapeBoundingBox[0][0];
                }
                if(shapeBoundingBox[0][1] > boundingBox[0][1]){
                    boundingBox[0][1] = shapeBoundingBox[0][1];
                }
                if(shapeBoundingBox[1][0] < boundingBox[1][0]){
                    boundingBox[1][0] = shapeBoundingBox[1][0];
                }
                if(shapeBoundingBox[1][1] < boundingBox[1][1]){
                    boundingBox[1][1] = shapeBoundingBox[1][1];
                }
            }
        }

        return boundingBox;
    }

    public double estimateIntersection(){
        if(attempts != 0){
            return areaBoundingBox*(intersectionHits/attempts);
        }
        else {
            return 0d;
        }
    }

    public double estimateUnion(){
        if(attempts != 0){
            return areaBoundingBox*(unionHits/attempts);
        }
        else {
            return 0d;
        }
    }

    public String toString(){
        String name = "\n## Stats for nerds:";

        name += "\n - Shape count: ";
        name += shapes.length;
        name += "\n - Shape list: ";
        for (Shape sh : this.shapes){
            name += "\n" + sh.toString();
        }

        if(attempts != 0){
            name += "\n\n - Area Bounding Box: ";
            name += this.areaBoundingBox;
            name += "\n - Area Intersection: ";
            name += this.estimateIntersection();
            name += "\n - Area Union: ";
            name += this.estimateUnion();
        } else {
            name += "\n\nWe are blind, no darts were thrown!";
        }
        return name;
    }

    public double[][] getBoundingBox(){
        return this.boundingBox;
    }

    public double getAttempts(){
        return this.attempts;
    }
}
