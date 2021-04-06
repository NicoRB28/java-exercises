package exercises;

import java.util.ArrayDeque;

import java.util.Deque;

import java.util.function.Predicate;

public class ParenthesisCheck {

	
	public static boolean validBracketSequence(String sequence) {
		Predicate<Character> isOpenParenthesis = character -> '(' == character;
		Predicate<Character> isOpenCurlyBrackets = character -> '{' == character;
		Predicate<Character> isOpenSquareBrackets = character -> '[' == character;
		Predicate<Character> isOpen = isOpenParenthesis.or(isOpenCurlyBrackets).or(isOpenSquareBrackets);
		
		
		Deque<Character> queue = new ArrayDeque<>();
		
		for(int i = 0; i < sequence.length(); i++) {
			
			Character character = sequence.charAt(i);
			
			if(isOpen.test(character)) {
				queue.push(character);
				continue;
			}
			
			if(queue.isEmpty()) {
				return false;
			}
			
			Character temp;
			
			switch (character) {
			case ')':
				temp = queue.pop();
				if(isOpenCurlyBrackets.or(isOpenSquareBrackets).test(temp)) {
					return false;
				}
				break;
			case '}':
				temp = queue.pop();
				if(isOpenSquareBrackets.or(isOpenParenthesis).test(temp)) {
					return false;
				}
				break;
			case ']':
				temp = queue.pop();
				if(isOpenParenthesis.or(isOpenCurlyBrackets).test(temp)) {
					return false;
				}
				break;
			}
			
		}
		return queue.isEmpty();
    
	}
	
	
	public static void main(String[] args) {
		
		boolean ok = validBracketSequence("{[()]}[]");
		System.out.println(ok);
		
		ok = validBracketSequence("{[(]}");
		System.out.println(ok);
		
	}
}
