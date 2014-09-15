
import Parser.RuleFileParser;

public class Stemmer {
	/**
	 * The program will be run using the following command:<p>
	 * {@code Stemmer -r rulesfile inputfile}.
	 */
	public static void main(String[] args) {
		assert(args.length==2);
		String ruleFilePath=args[0];
		String inputFilePath=args[1];
		RuleFileParser x=new RuleFileParser(ruleFilePath);
		//x.stemOfWord("করে");
	}
}
