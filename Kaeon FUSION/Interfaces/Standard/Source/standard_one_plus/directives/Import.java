package standard_one_plus.directives;

import java.util.ArrayList;

import io.IO;
import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;
import one_plus.parse.Processor;
import one_plus.parse.TokenGenerator;
import tokenizer.Tokenizer;

public class Import extends DirectiveUnit {
	
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		Element element = directive.directive;
		
		if(element.content.equalsIgnoreCase("IMPORT")) {
			
			Define define = new Define();
			
			for(int i = 0; i < directiveUnits.size(); i++) {
				
				if(directiveUnits.get(i) instanceof Define) {
					
					define = (Define) directiveUnits.get(i);
					
					break;
				}
			}
			
			importDirectives(define, element);
		}
	}
	
	public void importDirectives(
			Define define,
			Element element) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
			ArrayList<Directive> directiveElements = getDirectives(IO.openAsString(element.children.get(i).content));
			
			for(int j = 0; j < directiveElements.size(); j++) {
				
				if(directiveElements.get(j).directive.content.equalsIgnoreCase("DEFINE")) {

					Element defineElement = directiveElements.get(j).directive;
					Element definition = ElementUtilities.copyElement(directiveElements.get(j).directive.children.get(0));
					
					for(int k = 1; k < defineElement.children.size(); k++)
						ElementUtilities.addChild(definition, ElementUtilities.copyElement(defineElement.children.get(k)));
					
					define.definitions.add(definition);
				}
				
				if(directiveElements.get(j).directive.content.equalsIgnoreCase("IMPORT"))
					importDirectives(define, directiveElements.get(j).directive);
			}
		}
	}
	
	public static ArrayList<Directive> getDirectives(String string) {
		
		ArrayList<String> tokens = TokenGenerator.getTokens(string);
		ArrayList<String> tokenize = Tokenizer.tokenize(tokens, string);
		
		String nestToken = TokenGenerator.getIndentToken(string);
		
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
			
			ArrayList<String> line = Processor.getLine(tokenize, i);
			int nest = Processor.nest(line, nestToken);
			
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
					Processor.isLiteralBlock(line, nest) &&
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
				
				previousElement = Processor.cropElement(Processor.getElement(literalString), true);
				ElementUtilities.addChild(currentElement, previousElement);
				
				literalString = "";
			}
			
			else if(line.size() > 0)
				previousElement = Processor.processLine(tokens, line, currentElement, directives);
			
			i += line.size() + 1;
		}
		
		return Processor.generateDirectives(directives);
	}
}