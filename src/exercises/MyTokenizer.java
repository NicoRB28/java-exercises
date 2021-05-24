package exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.aliasi.tokenizer.EnglishStopTokenizerFactory;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;

public class MyTokenizer {

	public static BufferedReader reader;

	public static void setUpBufferedReader() throws IOException {
		reader = Files.newBufferedReader(Paths.get("files/Sonnet.txt"), StandardCharsets.UTF_8);
	}

	public static void closeBufferedReader() throws IOException {
		reader.close();
	}

	public static void main(String[] args) throws IOException {

		MyTokenizer.setUpBufferedReader();

		Consumer<String> toLower = String::toLowerCase;
		Consumer<String> noSpace = String::trim;
		Consumer<String> lowerAndNoSpace = toLower.andThen(noSpace);
		Consumer<String> print = System.out::println;

		List<String> strings = new ArrayList<>();

		Consumer<String> addToList = str -> strings.add(str);
		Consumer<String> lowerAndNoSpaceAndThenAdd = lowerAndNoSpace.andThen(addToList);

		System.out.println("texto antes de la tokenizacion: ");
		reader.lines().forEach(lowerAndNoSpace.andThen(print));

		closeBufferedReader();

		setUpBufferedReader();

		TokenizerFactory factory = IndoEuropeanTokenizerFactory.INSTANCE;

		factory = new EnglishStopTokenizerFactory(factory);

		reader.lines().forEach(lowerAndNoSpaceAndThenAdd);

		StringBuilder allInOneString = new StringBuilder();
		strings.forEach(str -> allInOneString.append(str));

		System.out.println("all in one: " + allInOneString.toString());

		Tokenizer tok = factory.tokenizer(allInOneString.toString()
												.toCharArray(), 0, allInOneString.length());

		System.out.println("texto despues de la tokenizacion: ");
		tok.forEach(print);

		closeBufferedReader();

	}
}
