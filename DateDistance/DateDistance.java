public class DateDistance{	
	public static final int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	//main method
	public static void main(String[] args){
		
		try {
        int month0 = Integer.parseInt(args[0]);
		int day0 = Integer.parseInt(args[1]);
		int year0 = Integer.parseInt(args[2]);
		
		int month1 = Integer.parseInt(args[3]);
		int day1 = Integer.parseInt(args[4]);
		int year1 = Integer.parseInt(args[5]);
		
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
         System.out.println("please provide 6 arguments");
      }
		
	}
	
	//is it a leap year?
	public static boolean isLeapYear (int year){
		if(year%400==0){
			return true;
		} else if (year%100==0){
			return false;
		} else if (year%4==0){
			return true;
		} else {return false;}
	}
	
	// what is the length of the month in that particular year?
	public static int monthLength (int month, int year){
		if (!isLeapYear(year) || month != 2){
			return daysInMonth[month-1];
		} else {
			return 29;
		}
	}
	
	//is that a valid date?
	public static boolean isRealDate (int month, int day, int year){
		if(month<=12 && month>0 && year>=0 && day<monthLength(month, year) && day>0){
			return true;
		} else {return false;}
	}
	
	//what is the distance between day0 and day1?
	public static int distance (int month0, int day0, int year0, int month1, int day1, int year1){
		int distance = 0;
		//date0 is smaller than date1
		int deltYear = year1-year0;
		int deltMonth = month1-month0;
		
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
				int month = month0 + i;
				if (month != 2 || !isLeapYear(year0)){
					distance += daysInMonth[month-1];
				} else {
					distance += 29;
				}
			}
			//add days to complete last year
			for (int i=1; i<month1; i++){
				if (i != 2 || !isLeapYear(year1)){
					distance += daysInMonth[i];
				} else {
					distance += 29;
				}
			}
			//add days to complete current month
			if (month0 != 2 || !isLeapYear(year0)){
					distance += (daysInMonth[month0-1]-day0);
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
				for (int i=month0+1; i<month1; i++){
					if (i != 2 || !isLeapYear(year0)){
						distance += (daysInMonth[i]);
					} else {
						distance += 29;
					}
				}
				
			}			
			//when start date and end date are at successive months or more, that is there are moths to complete
			if(deltMonth > 0){
				//add days to complete current month
				if (month0 != 2 || !isLeapYear(year0)){
						distance += (daysInMonth[month0-1]-day0);
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
	
	public static boolean dateIsInOrder(int month0, int day0, int year0, int month1, int day1, int year1){
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
