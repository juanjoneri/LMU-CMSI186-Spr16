public class ClockSolverLine{
    
    private final static double DEFAULT_TIMESLICE = 60.0d;
    private final static double OBJECTIVE_ANGLE = 180;
    private final static double MAX_IMPUT = 1800.0d;
    private final static Clock clock = new Clock();    
    
    public static void main(String[] args){
        
        double timeSlice = DEFAULT_TIMESLICE;
        double deltaAngle = clock.getDeltaAngle(timeSlice);
        boolean shouldRun = true;
        //count how many times the clock was at 180
        int lineCount = 0;

        if(args.length > 0){
            try{
                double arg0 = Double.parseDouble(args[0]);
                if(isValidTimeSlice(arg0)){
                    timeSlice = arg0;
                    deltaAngle = clock.getDeltaAngle(timeSlice);
                } else {
                    System.out.println("\r\n## Time slice should be in the interval (0, " + MAX_IMPUT +"]\r\n");
                    shouldRun = false;
                    }
            }
            catch (NumberFormatException e) {
                System.out.println("\r\n## Time slide should be a double");
                shouldRun = false;
            } 
        }

        if(shouldRun){
            System.out.println("\r\n# Time Slice = " + timeSlice + "s\r\n");

            while(clock.getHoursElapsed() < 12){
                if(isAngleInRange(clock.getAngle(), deltaAngle)){
                    lineCount ++;
                    clock.printTimeAndAngle();           
                }
                clock.tickBySecond(timeSlice);
            }        
            System.out.println("\r\n## The hands of a clock formed " + OBJECTIVE_ANGLE + "°: " + lineCount + " times");
        }
    }
    
    private static boolean isValidTimeSlice(double timeSlice){
        return (timeSlice > 0.0 && timeSlice <= MAX_IMPUT);
    }
    
    private static boolean isAngleInRange(double actualA, double deltaA){
        //inefficient code
        return (actualA >= (OBJECTIVE_ANGLE - (deltaA/2)) && actualA <= (OBJECTIVE_ANGLE + (deltaA/2)));  
    }
}