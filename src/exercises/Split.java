package exercises;

import java.util.Arrays;


public class Split {
	public static void main(String[] args) {
		/**
		 * pruebas para evaluar un token para un ejercicio de facultad
		 * el token debe tener la forma id+123456
		 */
		String token = "12123456"; 
		String[] groups = token.split("123456");
		
		Arrays.asList(groups).forEach(System.out::println);
		
		System.out.println(token.contains("123456"));
		System.out.println(token.substring(0,1));
		
		
		if((token.length() > 6) && (token.endsWith("123456"))){
			//el token seria valido
			System.out.println(token+ " es valido");
			
		}else {
			System.out.println("no es valido: "+ token);
		}
				
		
		
	}
}
