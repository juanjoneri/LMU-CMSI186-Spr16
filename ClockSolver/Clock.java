public class Clock{
    //CLASS LEVEL VARIABLES -----------------------------
    private long hoursElapsed; //life span of the clock, only changes when time elapses.
    private long hour, minute;
    private double second;
    
    //CONSTRUCTORS --------------------------------------
    public Clock(){
        this(0,0,0.0d);
    }
    
    public Clock(long hr, long min, double sec){
        if(isValidHour(hr) && isValidMinute(min) && isValidSecond(sec)){
            this.hour = hr;
            this.minute = min;
            this.second = sec;
            this.hoursElapsed = 0;
        } else { 
            throw new IllegalArgumentException();
        } 
    }
    
    //PUBLIC METHODS-------------------------------------
    public void tickBySecond(double sec){
        this.second += sec;
        normalizeHour();
    }
    
    public void tickByMinute(long min){
        this.minute += min;
        normalizeHour();
    }
    
    public void tickByHour(long hr){
        this.hour += hr;
        this.hoursElapsed += hr;
        normalizeHour();
    }

    @Override
    public String toString() {
        return this.hour + " : " + this.minute + " : " + roundDouble(this.second);
    }
    
    public void printTimeAndAngle(){
        String output = this.toString() + "  ";
        for(int i=0; i<15 - this.toString().length(); i++){
            output = output.concat(" ");
        }
        output = output.concat(">>  ");
        output = output.concat(this.getRoundedAngle()+ "°");
        System.out.println(output);     
    }
    
    //GETTERS AND SETTERS ------------------------------
    public void setHour(long hr){
        if(isValidHour(hr)){
            this.hour = hr;           
        }
    }
    
    public void setMinute(long min){
        if(isValidMinute(min)){
            this.minute = min;
        }
    }
    
    public void setSecond(double sec){
        if(isValidSecond(sec)){
            this.second = sec;
        }
    }
    
    public void setTime(long hr, long min, double sec){
        if(isValidHour(hr) && isValidMinute(min) && isValidSecond(sec)){
            this.hour = hr;
            this.minute = min;
            this.second = sec;
        } else { 
            throw new IllegalArgumentException();
        }
    }
    
    public long getHoursElapsed(){
        return this.hoursElapsed;
    }
    
    public long getHour(){
        return this.hour;
    }
    
    public long getMinute(){
        return this.minute;
    }
    
    public double getSecond(){
        return this.second;
    }
    
    public double getHourAngle(){
        return 0.5 * (60 * this.hour + this.minute + this.second/60);
    }
    
    public double getMinuteAngle(){
        return 6 * this.minute + 0.1 * this.second;
    }
    
    public double getAngle(){
        double ang = Math.abs(getMinuteAngle() - getHourAngle());
        return (ang > 180) ? 360 - ang : ang;
    }
    
    public double getRoundedAngle(){
        return (Math.round(this.getAngle() * 100))/100;
    }
    
    public double getDeltaAngle(double timeSlice){
        Clock clock = new Clock();
        double init = clock.getAngle();
        clock.tickBySecond(timeSlice);
        double fin = clock.getAngle();
        return Math.abs(fin - init);
    }
    
    //PRIVATE MEHTODS -----------------------------------
    
    private boolean isValidHour(long hr){
        //hours go from 0 to 11
        return (hr < 12 && hr >= 0);
    }
    
    private boolean isValidMinute(long min){
        //minutes go from 0 to 59
        return (min < 60 && min >= 0);
    }
    
    private boolean isValidSecond(double min){
        //seconds go from 0 to 59
        return (min < 60 && min >= 0.0d);
    }
    
    private void normalizeHour(){
        if(this.second >= 60){
            this.minute += (long) Math.floor(this.second/60);
            this.second = this.second%60;
        }
        if(this.minute >= 60){
            this.hour += Math.floor(this.minute/60);
            this.hoursElapsed += Math.floor(this.minute/60);
            this.minute = this.minute%60;
        }
        if(this.hour >= 12){
            this.hour = this.hour % 12;
        }
    }

    private static double roundDouble(double value){
        value = Math.round(value * 100);
        return value = value/100;
    }
}