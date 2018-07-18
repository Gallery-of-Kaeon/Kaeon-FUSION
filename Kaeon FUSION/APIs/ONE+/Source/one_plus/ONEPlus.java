package one_plus;

import java.util.ArrayList;

import aether_one_plus.Aether;
import one.Element;
import one_plus.parse.Preprocessor;
import one_plus.parse.Processor;
import one_plus.parse.TokenGenerator;
import philosophers_stone.PhilosophersStone;
import tokenizer.Tokenizer;

public class ONEPlus {
	
	public static Element parseONEPlus(String string) {
		return parseONEPlus(string, new PhilosophersStone());
	}
	
	public static Element parseONEPlus(String string, PhilosophersStone host) {
		
		if(string.trim().startsWith("-[")) {
			
			try {
				
				return
						(Element)
						Aether.call(
								string.substring(string.indexOf("-[") + 2, string.indexOf("]\n")),
								0,
								string.substring(string.indexOf("]\n") + 2));
			}
			
			catch(Exception exception) {
				
			}
		}
		
		ArrayList<String> tokens = TokenGenerator.getTokens(string);
		ArrayList<String> tokenize = Tokenizer.tokenize(tokens, string);
		
		String nestToken = TokenGenerator.getIndentToken(string);
		
		return Processor.process(tokens, Preprocessor.preprocess(tokens, tokenize, nestToken), nestToken);
	}
}