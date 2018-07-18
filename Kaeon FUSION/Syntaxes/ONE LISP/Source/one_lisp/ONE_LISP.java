package one_lisp;

import java.util.ArrayList;
import java.util.Arrays;

import one.Element;
import one.ElementUtilities;
import tokenizer.Tokenizer;

public class ONE_LISP {
	
	public static Element parse(String string) {
		
		Element one = new Element();
		
		ArrayList<String> tokens =
				Tokenizer.tokenize(
						new ArrayList<String>(
								Arrays.asList(
										" ", "\t", "\n", "\"", "(", ")", "\\")),
						string);
		
		Element currentElement = one;
		one.content = null;
		
		boolean isQuote = false;
		String literal = "";
		
		for(int i = 0; i < tokens.size(); i++) {
			
			if(tokens.get(i).equals("\"")) {
				
				isQuote = !isQuote;
				
				if(!isQuote && literal.length() > 0) {
					
					if(currentElement.content == null)
						currentElement.content = literal;
					
					else
						ElementUtilities.addChild(currentElement, ElementUtilities.createElement(literal));
					
					literal = "";
				}
				
				continue;
			}
			
			if(tokens.get(i).equals("\\") && i < tokens.size() - 1) {
				
				if(isQuote)
					literal += tokens.get(i + 1);
				
				else {
					
					if(currentElement.content == null)
						currentElement.content = tokens.get(i + 1);
					
					else
						ElementUtilities.addChild(currentElement, ElementUtilities.createElement(tokens.get(i + 1)));
				}
				
				i++;
				
				continue;
			}
			
			if(tokens.get(i).equals("(") && i < tokens.size() - 1) {
				
				Element newElement = new Element();
				newElement.content = null;
				
				ElementUtilities.addChild(currentElement, newElement);
				
				currentElement = newElement;
			}
			
			else if(tokens.get(i).equals(")") && currentElement.parent != null)
				currentElement = currentElement.parent;
			
			else if(isQuote)
				literal += tokens.get(i);
			
			else if(!tokens.get(i).equals(" ") && !tokens.get(i).equals("\n") && !tokens.get(i).equals("\t")) {
				
				if(currentElement.content == null)
					currentElement.content = tokens.get(i);
				
				else
					ElementUtilities.addChild(currentElement, ElementUtilities.createElement(tokens.get(i)));
			}
		}
		
		one.content = "";
		
		if(currentElement.content == null)
			currentElement.content = "";
		
		return one;
	}
}