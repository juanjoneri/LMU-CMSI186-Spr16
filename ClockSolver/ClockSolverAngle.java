public class ClockSolverAngle{
    
    private final static double DEFAULT_TIMESLICE = 60.0d;
    private final static double OBJECTIVE_ANGLE = 180;
    private final static double MAX_IMPUT_SLICE = 1800.0d;
    private final static double MAX_IMPUT_ANGLE = 180.0d;
    private final static Clock clock = new Clock();    
    
    public static void main(String[] args){
        
        double timeSlice = DEFAULT_TIMESLICE;
        double objectiveAngle = OBJECTIVE_ANGLE;
        double deltaAngle = clock.getDeltaAngle(timeSlice);
        boolean shouldRun = true;
        //count how many times the clock was at >objectiveAngle
        int lineCount = 0;

        if(args.length > 1){
            try{
                double arg0 = Double.parseDouble(args[0]);
                double arg1 = Double.parseDouble(args[1]);
                
                if(isValidTimeSlice(arg1)){
                    timeSlice = arg1;
                    deltaAngle = clock.getDeltaAngle(timeSlice);
                } else {
                    System.out.println("\r\n## Time slice should be in the interval (0, " + MAX_IMPUT_SLICE +"]\r\n");
                    shouldRun = false;
                    }
                if(isValidAngle(arg0)){
                    objectiveAngle = arg0;
                } else {
                    System.out.println("\r\n## Angle should be in the interval [0, " + MAX_IMPUT_ANGLE + ")\r\n");
                    shouldRun = false;
                    }
            } catch (NumberFormatException e) {
                System.out.println("\r\n## Time slice and angle should be doubles");
                shouldRun = false;
            } 
        }else if(args.length == 1){
            try{
                double arg0 = Double.parseDouble(args[0]);
                if(isValidAngle(arg0)){
                    objectiveAngle = arg0;
                } else{
                    System.out.println("\r\n## Angle should be in the interval [0, " + MAX_IMPUT_ANGLE + ")\r\n");
                    shouldRun = false;
                }
            }catch (NumberFormatException e) {
                System.out.println("\r\n## Angle should be a double");
                shouldRun = false;
            } 
        }else{
            System.out.println("\r\n## Please privide a value for the angle");
            shouldRun = false;
        }

        if(shouldRun){
            System.out.println("\r\n# Time Slice = " + timeSlice + "s\r\n");
            System.out.println("# Angle = " + objectiveAngle + "s\r\n");
            
            while(clock.getHoursElapsed() < 12){
                if(isAngleInRange(clock.getAngle(), deltaAngle, objectiveAngle)){
                    lineCount ++;
                    clock.printTimeAndAngle();           
                }
                clock.tickBySecond(timeSlice);
            }
            System.out.println("\r\n## The hands of a clock formed " + objectiveAngle + "°: " + lineCount + " times");
        }
    }
    
    private static boolean isValidTimeSlice(double timeSlice){
        return (timeSlice > 0.0 && timeSlice <= MAX_IMPUT_SLICE);
    }
    
    private static boolean isValidAngle(double angle){
        return (angle >= 0.0 && angle <= MAX_IMPUT_ANGLE);
    }
        
    private static boolean isAngleInRange(double actualA, double deltaA, double objectiveA){
        //inefficient code
        return (actualA >= (objectiveA - (deltaA/2)) && actualA <= (objectiveA + (deltaA/2)));  
    }

}