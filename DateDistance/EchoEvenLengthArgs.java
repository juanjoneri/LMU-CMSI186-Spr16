public class EchoEvenLengthArgs{
	
	public static void main(String[] args){
		for(String word: args){
			if(isEven(word)){
				System.out.printf(word + " ");
			}
		}
	
	}
	
	public static boolean isEven(String word){
		if(word.length()%2 == 0){
			return true;
		} else {return false;}
	}
}