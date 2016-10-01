public class DateDistanceLong{	
	public static final long[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	//main method
	public static void main(String[] args){
		
		try {
        long month0 = Long.parseLong(args[0]);
		long day0 = Long.parseLong(args[1]);
		long year0 = Long.parseLong(args[2]);
		
		long month1 = Long.parseLong(args[3]);
		long day1 = Long.parseLong(args[4]);
		long year1 = Long.parseLong(args[5]);
		
		if(isRealDate(month0, day0, year0) && isRealDate(month1, day1, year1)){
			if(dateIsInOrder(month0, day0, year0, month1, day1, year1)){
				System.out.println(distance(month0, day0, year0, month1, day1, year1));	
			} else {
				System.out.println(distance(month1, day1, year1, month0, day0, year0));	
			}
		} else {System.out.println("that is not a valid date");}		
		
		} catch (NumberFormatException ignore) {
        System.out.println("please only insert integers");
		}catch (ArrayIndexOutOfBoundsException e) {
         System.out.println("please provide 6 arguments >>MM DD YYYY MM DD YYYY<<");
      }
		
	}
	
	//is it a leap year?
	public static boolean isLeapYear (long year){
		if(year%400==0){
			return true;
		} else if (year%100==0){
			return false;
		} else if (year%4==0){
			return true;
		} else {return false;}
	}
	
	// what is the length of the month in that particular year?
	public static long monthLength (long month, long year){
		if (!isLeapYear(year) || month != 2){
			return daysInMonth[(int) (long) month-1];
		} else {
			return 29;
		}
	}
	
	//is that a valid date?
	public static boolean isRealDate (long month, long day, long year){
		if(month<=12 && month>0 && year>=0 && day<monthLength(month, year) && day>0){
			return true;
		} else {return false;}
	}
	
	//what is the distance between day0 and day1?
	public static long distance (long month0, long day0, long year0, long month1, long day1, long year1){
		long distance = 0;
		//date0 is smaller than date1
		long deltYear = year1-year0;
		long deltMonth = month1-month0;
		
		//only when there are complete yars
		if(deltYear>1){
			//the years which are complete start from year0+1 and end at year1-1
			for (int i=1; i<deltYear; i++){
				if (!isLeapYear(year0 + i)){
					distance += 365;
				} else { distance += 366;}
			}
		}
		//only when there are years to complete
		if(deltYear>0){
			//add the days to end the current year
			for (int i=1; month0+i<=12; i++){
				long month = month0 + i;
				if (month != 2 || !isLeapYear(year0)){
					distance += daysInMonth[ (int) (long) month-1];
				} else {
					distance += 29;
				}
			}
			//add days to complete last year
			for (int i=1; i<month1; i++){
				if (i != 2 || !isLeapYear(year1)){
					distance += daysInMonth[(int) (long) i];
				} else {
					distance += 29;
				}
			}
			//add days to complete current month
			if (month0 != 2 || !isLeapYear(year0)){
					distance += (daysInMonth[(int) (long) month0-1]-day0);
				} else {
					distance += 29-day0;
				}
			//add days to complete last month
			distance += day1;
		}
		//when start date and end date are at the same year
		if(deltYear==0){
			//when start date and end date are at different months
			if(deltMonth>1){
				//add the complete months from following month to month0 until previous to month1
				for (long i=month0+1; i<month1; i++){
					if (i != 2 || !isLeapYear(year0)){
						distance += (daysInMonth[ (int) (long) i]);
					} else {
						distance += 29;
					}
				}
				
			}			
			//when start date and end date are at successive months or more, that is there are moths to complete
			if(deltMonth > 0){
				//add days to complete current month
				if (month0 != 2 || !isLeapYear(year0)){
						distance += (daysInMonth[(int) (long) month0-1]-day0);
					} else {
						distance += 29-day0;
					}
				//add days to complete last month
				distance += day1;
			}
			//when start date and end date are at the same month
			if(deltMonth == 0){
				distance += day1-day0;
			}
		}
		
		return distance;
	}
	
	public static boolean dateIsInOrder(long month0, long day0, long year0, long month1, long day1, long year1){
		//check if date0 is smaller than date1
		if(year1>year0){
			return true;
		} else if (month1>month0){
			return true;
		} else if (day1>day0){
			return true;
		} else {
			return false;
		}
	}
}
