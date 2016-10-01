public class Test{
    
    public static void main(String[] args){
        Clock clock = new Clock();
        clock.tickByHour(11);
        clock.tickByMinute(59);
        System.out.println(clock.getHour());
        clock.tickBySecond(60);
        System.out.println(clock.toString());
        System.out.println(clock.getHour());
    }
    
}