package exercises;

import java.util.Arrays;

public class BufferCircular {
	
	public static void main(String[] args) {
		
		var buff = Arrays.asList("uno","dos","tres");
		
		var index = 0;
		
		while(true) {
			System.out.println(buff.get(index));
			index = (index + 1) % buff.size();
		}
	}
}
