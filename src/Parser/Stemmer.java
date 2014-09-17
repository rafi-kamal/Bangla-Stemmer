package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


public class Stemmer {
	/**
	 * The program will be run using the following command:<p>
	 * {@code Stemmer rulesfile inputfile}.
	 */
	public static void main(String[] args) {
		assert(args.length==2);
		
		String ruleFilePath=args[0];
		String inputFilePath=args[1];
		
		RuleFileParser parser = new RuleFileParser(ruleFilePath);

		File file = new File(inputFilePath);
		
		try (BufferedReader inputFileReader = 
				new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = inputFileReader.readLine()) != null) {
				System.out.println(line);
				for (String word : line.split("[\\s।%,ঃ]+")) {
					System.out.print(parser.stemOfWord(word) + " ");
				}
				System.out.println();
			}
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
