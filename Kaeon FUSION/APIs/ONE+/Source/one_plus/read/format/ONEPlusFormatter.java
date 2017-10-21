package one_plus.read.format;

import java.util.ArrayList;

public class ONEPlusFormatter {
	
	public static ArrayList<String> formatONEPlus(ArrayList<String> file) {

		ONEPlusCropper.cropONEPlus(file);
		
		ArrayList<String> one = new ArrayList<String>();

		String indent = ONEPlusIndenter.getIndent(file);
		String elementDefinition = null;
		
		for(int i = 0; i < file.size(); i++) {
			
			String line = file.get(i);
			String trim = line.trim();
			
			if(elementDefinition == null) {
				
				if(trim.equals("-")) {
					
					one.add(line);
					elementDefinition = line;
					
					continue;
				}
			}
			
			else if(elementDefinition.equals(line)) {
				
				one.add(line);
				elementDefinition = null;
				
				continue;
			}
			
			if(elementDefinition != null) {
				
				if(
						ONEPlusIndenter.getNumIndents(line, indent) <
						ONEPlusIndenter.getNumIndents(elementDefinition, indent) + 1) {
					
					line =
							ONEPlusIndenter.indentLine(
									indent,
									ONEPlusIndenter.getNumIndents(
											elementDefinition,
											indent) + 1) + trim;
				}
				
				one.add(line);
			}
			
			else
				processLine(line, indent, file, i, one);
		}
		
		return one;
	}
	
	private static void processLine(
			String line, String indent,
			ArrayList<String> file, int lineNumber,
			ArrayList<String> one) {
		
		ArrayList<String> elementDefinition = new ArrayList<String>();
		
		int elementNumber = 1;
		
		ArrayList<Integer> nestStack = new ArrayList<Integer>();
		
		int nest = ONEPlusIndenter.getNumIndents(line, indent);
		int initialNest = nest;
		
		int propertyNest = 0;
		
		int preDirectiveNest = 0;
		int directiveNest = 0;
		
		boolean inQuote = false;
		char quoteCharacter = 0;
		
		for(int i = 0; i < line.length(); i++) {
			
			char character = line.charAt(i);
			
			if(character == '~') {
				
				i++;
				
				continue;
			}
			
			if(character == '\'' || character == '\"') {	
				
				if(!inQuote) {
					inQuote = true;
					quoteCharacter = character;
				}	
				
				else if(character == quoteCharacter) {
					inQuote = false;
				}
			}
			
			if(inQuote || (character == '\"' && quoteCharacter == '\"'))
				continue;
			
			if(
					character == ':' || character == '|' || character == ',' || character == ';' ||
					character == '{' || character == '}' ||
					character == '(' || character == ')' ||
					character == '[' || character == ']') {
				
				String element = escape(line.substring(0, i).trim(), indent, nest + 1);
				
				if(element.length() > 0) {
					
					elementDefinition.add(
							ONEPlusIndenter.indentLine(indent, nest) +
							lineNumber + " " +
							elementNumber + " " +
							(directiveNest <= 0 ?
									(propertyNest == 0 ? "ELEMENT" : "ELEMENT PROPERTY") :
										"DIRECTIVE"));
					
					if(!element.equals("#")) {
						
						elementDefinition.add(
								ONEPlusIndenter.indentLine(
										indent, nest + 1) + element);
					}
					
					elementDefinition.add(
							ONEPlusIndenter.indentLine(indent, nest) +
							lineNumber + " " +
							elementNumber + " " +
							(directiveNest <= 0 ?
									(propertyNest == 0 ? "ELEMENT" : "ELEMENT PROPERTY") :
										"DIRECTIVE"));
					
					elementNumber++;
				}
				
				line = line.substring(i + 1);
				i = -1;
			}
			
			if(character == '{' || character == '(')
				nestStack.add(nest);
			
			if(character == ':' || character == '{')
				nest++;
			
			if(character == '{')
				propertyNest++;
			
			if(character == ';' && nest > initialNest)
				nest--;
			
			if(character == '}' || character == ')' && nest > initialNest)
				nest = nestStack.remove(nestStack.size() - 1);
			
			if(character == '}')
				propertyNest--;
			
			if(character == '[') {
				
				if(directiveNest == 0)
					preDirectiveNest = nest;
				
				directiveNest++;
			}
			
			if(character == ']') {
				
				directiveNest--;
				nest--;
				
				if(directiveNest == 0)
					nest = preDirectiveNest;
			}
		}
		
		line = line.trim();
		
		if(line.length() > 0) {
			
			String element = escape(line, indent, nest + 1);
			
			elementDefinition.add(
					ONEPlusIndenter.indentLine(indent, nest) +
					lineNumber + " " +
					elementNumber + " " +
					(directiveNest <= 0 ?
							(propertyNest == 0 ? "ELEMENT" : "ELEMENT PROPERTY") :
								"DIRECTIVE"));
			
			if(!element.equals("#")) {
				
				elementDefinition.add(
						ONEPlusIndenter.indentLine(
								indent, nest + 1) + element);
			}
			
			elementDefinition.add(
					ONEPlusIndenter.indentLine(indent, nest) +
					lineNumber + " " +
					elementNumber + " " +
					(directiveNest <= 0 ?
							(propertyNest == 0 ? "ELEMENT" : "ELEMENT PROPERTY") :
								"DIRECTIVE"));
			
			elementNumber++;
		}
		
		one.addAll(elementDefinition);
		
		for(int i = lineNumber + 1; i < file.size(); i++) {
			
			line = file.get(i);
			
			if(line.trim().length() > 0) {
			
				if(ONEPlusIndenter.getNumIndents(line, indent) <= initialNest)
					break;
			}
			
			file.set(i, ONEPlusIndenter.indentLine(indent, nest - initialNest) + line);
		}
	}
	
	private static String escape(String string, String indent, int nest) {
		
		char inQuote = '~';
		
		for(int i = 0; i < string.length(); i++) {
			
			if(string.charAt(i) == '\'' && (inQuote == '~' || inQuote == '\'')) {
				
				string = string.substring(0, i) + string.substring(i + 1);
				
				if(inQuote == '~')
					inQuote = '\'';
				
				else
					inQuote = '~';
			}
			
			else if(string.charAt(i) == '\"' && (inQuote == '~' || inQuote == '\"')) {
				
				if(inQuote == '~')
					inQuote = '\"';
				
				else
					inQuote = '~';
			}
			
			else if(string.charAt(i) == '~' && inQuote == '~') {
				
				if(i < string.length() - 1) {
					
					if(string.charAt(i + 1) == 'n')
						string = string.substring(0, i) + '\n' + string.substring(i + 2);
					
					else if(string.charAt(i + 1) == 't')
						string = string.substring(0, i) + '\t' + string.substring(i + 2);
					
					else
						string = string.substring(0, i) + string.substring(i + 1);
				}
				
				else
					string = string.substring(0, i) + string.substring(i + 1);
			}
		}
		
		return string;
	}
}