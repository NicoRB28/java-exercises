package exercises;

public class HowManyTimes {

	
	public static int howManyTimesCanRepeat(String a ,String b) {
		int count = 0;
		StringBuilder sb = new StringBuilder(b);
		
		if(a.length()> sb.length()) {
			return 0;
		}
		
		boolean ok = true;
		
		while (ok) {
			
			if(sb.length() < a.length() || sb.equals("")) {
				break;		
			}
			
			int partialCount = 0;

			for(int i = 0; i < a.length(); i++) {
				
				String letter = String.valueOf(a.charAt(i));
				
				if(sb.lastIndexOf(letter)!= -1) {
					partialCount++;	
				}
			}
			
			if(partialCount == a.length()) {
				count++;	
				a.chars().mapToObj(Character::toString).forEach(str -> sb.deleteCharAt(sb.lastIndexOf(str)));
			}else {
				ok = false;
			}
			
		}
		
		return count;
	}

	
	public static void main(String[] args) {
		System.out.println(howManyTimesCanRepeat("zzzzz","zzzzzzzzz"));
	}
}
