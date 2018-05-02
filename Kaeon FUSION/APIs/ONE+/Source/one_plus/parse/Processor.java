package one_plus.parse;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import tokenizer.Tokenizer;

public class Processor {
	
	public static Element process(
			ArrayList<String> tokens,
			ArrayList<String> tokenize,
			String nestToken) {
		
		ArrayList<ArrayList<Element>> directives = new ArrayList<ArrayList<Element>>();
		
		Element element = new Element();
		
		Element currentElement = element;
		
		int previousNest = 0;
		Element previousElement = element;
		
		ArrayList<Element> baseElements = new ArrayList<Element>();
		
		boolean inLiteralBlock = false;
		int literalNest = 0;
		String literalString = "";
		
		for(int i = 0; i < tokenize.size();) {
			
			ArrayList<String> line = getLine(tokenize, i);
			int nest = nest(line, nestToken);
			
			if(!inLiteralBlock) {
				
				if(nest > previousNest) {
					
					baseElements.add(currentElement);
					
					currentElement = previousElement;
				}
				
				else if(nest < previousNest) {
					
					for(int j = nest; j < previousNest && baseElements.size() > 0; j++)
						currentElement = baseElements.remove(baseElements.size() - 1);
				}
				
				previousNest = nest;
			}
			
			boolean literal =
					isLiteralBlock(line, nest) &&
					!(inLiteralBlock && nest != literalNest);
			
			if(literal) {
				inLiteralBlock = !inLiteralBlock;
				literalNest = nest;
			}
			
			if(inLiteralBlock) {
				
				if(!literal) {
					
					for(int j = literalNest + 1; j < line.size(); j++)
						literalString += line.get(j);
					
					literalString += '\n';
				}
			}
			
			else if(literal) {
				
				previousElement = cropElement(getElement(literalString), true);
				ElementUtilities.addChild(currentElement, previousElement);
				
				literalString = "";
			}
			
			else if(line.size() > 0)
				previousElement = processLine(tokens, line, currentElement, directives);
			
			i += line.size() + 1;
		}
		
		DirectiveProcessor.processDirectives(element, generateDirectives(directives));
		
		return element;
	}
	
	public static ArrayList<String> getLine(ArrayList<String> tokenize, int index) {
		
		ArrayList<String> line = new ArrayList<String>();
		
		for(int i = index; i < tokenize.size() && !tokenize.get(i).equals("\n"); i++)
			line.add(tokenize.get(i));
		
		return line;
	}
	
	public static int nest(ArrayList<String> line, String nestToken) {

		int nest = 0;
		
		for(String token : line) {
			
			if(!token.equals(nestToken))
				break;
			
			nest++;
		}
		
		return nest;
	}
	
	public static boolean isLiteralBlock(ArrayList<String> line, int nest) {
		
		if(line.size() != nest + 1 || line.size() == 0)
			return false;
		
		return line.get(line.size() - 1).equals("-");
	}
	
	public static Element getElement(String string) {
		
		Element element = new Element();
		element.content = string;
		
		return element;
	}
	
	public static Element cropElement(Element element, boolean literal) {
		
		element.content = literal ?
				element.content.substring(0, element.content.length() - 1) :
				element.content.trim();
		
		return element;
	}
	
	public static Element processLine(
			ArrayList<String> tokens,
			ArrayList<String> line,
			Element currentElement,
			ArrayList<ArrayList<Element>> directives) {
		
		Element baseElement = currentElement;
		
		boolean directive = false;
		
		ArrayList<Element> stack = new ArrayList<Element>();
		ArrayList<Element> nestStack = new ArrayList<Element>();
		ArrayList<Element> directiveStack = new ArrayList<Element>();
		
		ArrayList<String> newLine = preprocessLine(tokens, line);
		
		for(String token : newLine) {
			
			if((!Tokenizer.isToken(tokens, token) || token.equals("-")) && token.trim().length() > 0) {
				
				Element newElement = cropElement(getElement(processContent(token)), false);
				
				ElementUtilities.addChild(currentElement, newElement);
				
				if(directive)
					directives.get(directives.size() - 1).add(newElement);
			}
			
			if(token.equals(":") || token.equals("{")) {
				
				if(token.equals("{"))
					nestStack.add(currentElement);
				
				if(currentElement.children.size() > 0)
					currentElement = currentElement.children.get(currentElement.children.size() - 1);
			}
			
			if(token.equals(";") || token.equals("}")) {
				
				if(token.equals("}")) {
					
					if(nestStack.size() > 0)
						currentElement = nestStack.remove(nestStack.size() - 1);
				}
				
				else if(currentElement.parent != null)
					currentElement = currentElement.parent;
			}
			
			if(token.equals("("))
				stack.add(currentElement);
			
			if(token.equals(")") && stack.size() > 0)
				currentElement = stack.remove(stack.size() - 1);
			
			if(token.equals("[")) {
				
				directiveStack.add(currentElement);
				directives.add(new ArrayList<Element>());
				
				directive = true;
			}
			
			if(token.equals("]") && directiveStack.size() > 0) {
				
				currentElement = directiveStack.remove(directiveStack.size() - 1);
				
				directive = directiveStack.size() != 0;
			}
		}
		
		Element previousElement = null;
		
		if(currentElement.children.size() > 0)
			previousElement = currentElement.children.get(currentElement.children.size() - 1);
		
		else
			previousElement = currentElement;
		
		currentElement = baseElement;
		
		return previousElement;
	}
	
	public static String processContent(String content) {
		
		for(int i = 0; i < content.length(); i++) {
			
			if(content.charAt(i) == '~') {
				
				content = content.substring(0, i) + content.substring(i + 1);

				if(content.length() > 0) {
					
					if(content.charAt(i) == 'n')
						content = content.substring(0, i) + '\n' + content.substring(i + 1);
					
					else if(content.charAt(i) == 't')
						content = content.substring(0, i) + '\t' + content.substring(i + 1);
				}
			}
		}
		
		return content;
	}
	
	public static ArrayList<String> preprocessLine(ArrayList<String> tokens, ArrayList<String> line) {
		
		ArrayList<String> newLine = new ArrayList<String>(line);
		
		while(newLine.size() > 0) {
			
			if(!Tokenizer.isToken(tokens, newLine.get(0)) ||
					(newLine.get(0).equals("~") ||
							newLine.get(0).equals("\"") ||
							newLine.get(0).equals("\'") ||
							newLine.get(0).equals("[")) ||
							newLine.get(0).equals("-")) {
				
				break;
			}
		
			newLine.remove(0);
		}
		
		for(int i = 0; i < newLine.size(); i++) {
			
			if(newLine.get(i).equals("~")) {
				
				if(i < newLine.size() - 1)
					newLine.set(i, newLine.get(i) + newLine.remove(i + 1));
				
				if(i > 0) {
					
					if(!Tokenizer.isToken(tokens, newLine.get(i - 1))) {
						
						i--;
						
						newLine.set(i, newLine.get(i) + newLine.remove(i + 1));
					}
				}
			}
			
			if(newLine.get(i).equals("\"")) {
				
				String literal = "\"";
				
				while(i + 1 < newLine.size()) {
					
					String token = newLine.remove(i + 1);
					
					if(token.equals("\""))
						break;
					
					literal += token;
				}
				
				newLine.set(i, literal + '\"');
			}
			
			if(newLine.get(i).equals("\'")) {
				
				String literal = "";
				
				while(i + 1 < newLine.size()) {
					
					String token = newLine.remove(i + 1);
					
					if(token.equals("\'"))
						break;
					
					literal += token;
				}
				
				newLine.set(i, literal);
			}
		}
		
		for(int i = 0; i < newLine.size() - 1; i++) {
			
			if((!Tokenizer.isToken(tokens, newLine.get(i)) || newLine.get(i).equals("-")) &&
					(!Tokenizer.isToken(tokens, newLine.get(i + 1)) || newLine.get(i + 1).equals("-"))) {
				
				newLine.set(i, newLine.get(i) + newLine.remove(i + 1));
				
				i--;
			}
		}
		
		return newLine;
	}
	
	public static ArrayList<Directive> generateDirectives(ArrayList<ArrayList<Element>> elements) {
		
		ArrayList<Directive> directives = new ArrayList<Directive>();
		
		for(int i = 0; i < elements.size(); i++) {
			
			for(int j = 0; j < elements.get(i).size(); j++) {
				
				Directive directive = new Directive();
				
				directive.directive = elements.get(i).get(j);
				
				for(int k = 0; k < elements.get(i).get(j).children.size(); k++) {
					
					boolean isHeader = false;

					for(int l = 0; l < elements.get(i).size(); l++) {
						
						if(elements.get(i).get(j).children.get(k) == elements.get(i).get(l)) {
							
							isHeader = true;
							
							break;
						}
					}
					
					if(isHeader)
						directive.header.add(elements.get(i).get(j).children.get(k));
					
					else
						directive.body.add(elements.get(i).get(j).children.get(k));
				}
				
				directives.add(directive);
			}
		}
		
		return directives;
	}
}