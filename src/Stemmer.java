
import Parser.RuleFileParser;

public class Stemmer {
	/**
	 * The program will be run using the following command:<p>
	 * {@code Stemmer -r rulesfile inputfile}.
	 */
	public static void main(String[] args) {
//		assert(args.length==2);
//		String ruleFilePath=args[0];
//		String inputFilePath=args[1];
		RuleFileParser parser = new RuleFileParser("common.rules");
		String input = "এ উদ্দেশ্যে সদস্যদের কাছ থেকে বিশেষ দিনগুলোর প্রস্তাব চাই। প্রস্তাবে শুধু দিন এবং সংক্ষেপে কেন দিনটি বিশেষ তা উল্লেখ করতে হবে। উদাহরনঃ ১৬ই ডিসেম্বর – বিজয় দিবস। একটি কমেন্টে কেবল মাত্র একটি দিন উল্লেখ করা যাবে। যদি আপনার দিনটি প্রস্তাব হয়ে থাকে তাহলে সেটাকে লাইক দিন। কমেন্টে এই নিয়ম অনুসরণ করা না হলে কমেন্ট টি মুছে ফেলা হতে পারে। ";
		for (String word : input.split("[\\s।%,ঃ]+")) {
			System.out.print(parser.stemOfWord(word) + " ");
		}
	}
}
