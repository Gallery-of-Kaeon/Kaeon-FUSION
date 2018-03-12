package one_plus;

import java.util.ArrayList;

import one.Element;
import one_plus.parse.Preprocessor;
import one_plus.parse.Processor;
import one_plus.parse.TokenGenerator;
import one_plus.parse.Tokenizer;
import philosophers_stone.PhilosophersStone;

public class ONEPlus {
	
	public static PhilosophersStone host;
	
	public static Element parseONEPlus(String string) {
		
		ArrayList<String> tokens = TokenGenerator.getTokens(string);
		ArrayList<String> tokenize = Tokenizer.tokenize(tokens, string);
		
		String nestToken = TokenGenerator.getIndentToken(string);
		
		return Processor.process(tokens, Preprocessor.preprocess(tokens, tokenize, nestToken), nestToken);
	}
}