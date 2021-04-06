package exercises;

public class HowManyTimes {

	
	public static int howManyTimesCanRepeat(String a ,String b) {
		
		int count = 0;
		
		StringBuilder sb = new StringBuilder(b);
		
		if(a.length()> sb.length()) {
			return 0;
		}
		
		while (sb.length() != 0) {
			
			if(sb.length() < a.length()) {
				return count;		
			}
			
			if(hasAllTheCharacters(a, sb) == a.length()) {
				count++;	
				updateStringBuilder(a, sb);
			}else {
				return count;
			}
			
		}
		
		return count;
	}


	private static int hasAllTheCharacters(String a, StringBuilder sb) {
		int partialCount = 0;
		for(int i = 0; i < a.length(); i++) {
			
			String letter = String.valueOf(a.charAt(i));
			
			if(sb.lastIndexOf(letter)!= -1) {
				partialCount++;	
			}
		}
		return partialCount;
	}


	private static void updateStringBuilder(String a, StringBuilder sb) {
		a.chars().mapToObj(Character::toString).forEach(str -> sb.deleteCharAt(sb.lastIndexOf(str)));
	}

	
	public static void main(String[] args) {
		System.out.println(howManyTimesCanRepeat("abc","xxxxxxxxxx"));
		System.out.println(howManyTimesCanRepeat("abc","abcabc"));
		System.out.println(howManyTimesCanRepeat("abc","aabcabczazbzc"));
		System.out.println(howManyTimesCanRepeat("abc",""));
	}
}
