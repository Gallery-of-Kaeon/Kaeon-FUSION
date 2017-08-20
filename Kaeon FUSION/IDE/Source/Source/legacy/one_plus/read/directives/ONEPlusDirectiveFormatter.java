package legacy.one_plus.read.directives;

import java.util.ArrayList;

import legacy.one_plus.ONEPlus;
import legacy.one_plus.element.Element;
import legacy.one_plus.read.ONEPlusElement;
import legacy.one_plus.read.directives.data.Declaration;
import legacy.one_plus.read.directives.data.Definition;

public class ONEPlusDirectiveFormatter {
	
	public static void formatDirectives(ONEPlus onePlus) {
		
		identifyDirectives(onePlus);
		
		while(true) {
			
			if(!processDirectives(onePlus, ""))
				break;
		}
		
		processDeclarations(onePlus);
	}
	
	public static void identifyDirectives(ONEPlusElement element) {
		
		for(int i = 0; i < element.getNumElements(); i++) {
			
			String definition =
					((ONEPlusElement) element.getElement(i)).getDefinition();
			
			if(definition.equals("DIRECTIVE")) {
				
				ONEPlusElement directive =
						(ONEPlusElement) element.removeElement(i);
				
				String content = directive.getContent();

				i--;
				
				if(content.equalsIgnoreCase("INFO"))
					element.addDefinition(new Definition(directive));
				
				else if(content.equalsIgnoreCase("DECLARE"))
					element.addDeclaration(new Declaration(directive));
				
				else if(content.equalsIgnoreCase("DEFINE"))
					element.addDefinition(new Definition(directive));
				
				else if(content.equalsIgnoreCase("IMPORT")) {
					
					ONEPlus importDirective = new ONEPlus(directive.getElement(0).getContent());
					
					for(int j = 0; j < importDirective.getDeclarations().size(); j++)
						element.addDeclaration(importDirective.getDeclarations().get(j));
					
					for(int j = 0; j < importDirective.getDefinitions().size(); j++)
						element.addDefinition(importDirective.getDefinitions().get(j));
				}
				
				else {
					i++;
					element.addElement(directive, i);
				}
			}
			
			else if(definition.contains("ELEMENT"))
				identifyDirectives((ONEPlusElement) element.getElement(i));
		}
	}
	
	public static boolean processDirectives(ONEPlusElement element, String fileName) {
		
		boolean hasDirectives = false;
		
		for(int i = 0; i < element.getNumElements(); i++) {
			
			String definition =
					((ONEPlusElement) element.getElement(i)).getDefinition();
			
			if(definition.equals("DIRECTIVE")) {
				
				ONEPlusElement directive =
						(ONEPlusElement) element.removeElement(i);
				
				String content = directive.getContent();
				
				if(content.equalsIgnoreCase("FOR"))
					processFor(element, i, directive);
				
				else if(content.equalsIgnoreCase("IF"))
					processIf(element, i, directive, fileName);
				
				else if(content.equalsIgnoreCase("USE"))
					processUse(element, i, directive);
				
				hasDirectives = true;
			}
			
			else if(definition.contains("ELEMENT")) {
				
				while(true) {
					
					if(!processDirectives((ONEPlusElement) element.getElement(i), fileName))
						break;
				}
			}
		}
		
		return hasDirectives;
	}
	
	private static void processFor(
			ONEPlusElement element, int index,
			ONEPlusElement directive) {
		
		String condition = directive.getElement(0).getContent();
		
		if(Character.isDigit(condition.charAt(0))) {
			
			int loop = Integer.parseInt(directive.getElement(0).getContent());
			
			for(int i = 0; i < loop; i++) {
				
				for(int j = 0; j < directive.getNumElements(); j++) {
					
					ONEPlusElement newElement = new ONEPlusElement((ONEPlusElement) directive.getElement(j));
					useIndex(newElement, i + 1, 0);
					
					element.addElement(newElement, index);
					index++;
				}
			}
		}
		
		else if(condition.equalsIgnoreCase("RANGE")) {
			
			int lowerBound = Integer.parseInt(directive.getElement(0).getElement(0).getContent());
			int upperBound = Integer.parseInt(directive.getElement(0).getElement(1).getContent());
			
			boolean reverse = lowerBound > upperBound;
			
			for(
					int i = lowerBound;
					!reverse ? (i <= upperBound) : (i >= upperBound);
					i += (!reverse ? 1 : -1)) {
				
				for(int j = 0; j < directive.getNumElements(); j++) {
					
					ONEPlusElement newElement = new ONEPlusElement((ONEPlusElement) directive.getElement(j));
					useIndex(newElement, i, 0);
					
					element.addElement(newElement, index);
					index++;
				}
			}
		}
		
		else if(condition.equalsIgnoreCase("SIZE")) {
			
			Definition definition = findDefinition(element, directive.getElement(0).getElement(0).getContent());
			
			int lowerBound = 0;
			int upperBound = definition.getElements().size() - 1;
			
			boolean reverse = false;
			
			if(directive.getNumElements() >= 2) {
				
				if(
						directive.getElement(1).getContent().equalsIgnoreCase("RANGE") &&
						((ONEPlusElement) directive.getElement(1)).getDefinition().equals("DIRECTIVE")) {
					
					lowerBound = Integer.parseInt(directive.getElement(1).getElement(0).getContent()) - 1;
					
					if(lowerBound < 0)
						lowerBound += definition.getElements().size() + 1;
					
					upperBound = Integer.parseInt(directive.getElement(1).getElement(1).getContent()) - 1;
					
					if(upperBound < 0)
						upperBound += definition.getElements().size() + 1;
					
					reverse = lowerBound > upperBound;
				}
			}
			
			for(
					int i = lowerBound;
					!reverse ? (i <= upperBound) : (i >= upperBound);
					i += (!reverse ? 1 : -1)) {
				
				for(int j = 0; j < directive.getNumElements(); j++) {
					
					ONEPlusElement newElement = new ONEPlusElement((ONEPlusElement) directive.getElement(j));
					useIndex(newElement, i + 1, 0);
					
					element.addElement(newElement, index);
					index++;
				}
			}
		}
		
		else {
			
			Definition definition = findDefinition(element, condition);
			
			int lowerBound = 0;
			int upperBound = definition.getElements().size() - 1;
			
			boolean reverse = false;
			
			if(directive.getNumElements() >= 2) {
				
				if(
						directive.getElement(1).getContent().equalsIgnoreCase("RANGE") &&
						((ONEPlusElement) directive.getElement(1)).getDefinition().equals("DIRECTIVE")) {
					
					lowerBound = Integer.parseInt(directive.getElement(1).getElement(0).getContent()) - 1;
					
					if(lowerBound < 0)
						lowerBound += definition.getElements().size() + 1;
					
					upperBound = Integer.parseInt(directive.getElement(1).getElement(1).getContent()) - 1;
					
					if(upperBound < 0)
						upperBound += definition.getElements().size() + 1;
					
					reverse = lowerBound > upperBound;
				}
			}
			
			for(
					int i = lowerBound;
					!reverse ? (i <= upperBound) : (i >= upperBound);
					i += (!reverse ? 1 : -1)) {
				
				for(int j = 0; j < directive.getNumElements(); j++) {
					
					ONEPlusElement newElement = new ONEPlusElement((ONEPlusElement) directive.getElement(j));
					useIndex(newElement, (ONEPlusElement) definition.getElements().get(i), 0);
					
					element.addElement(newElement, index);
					index++;
				}
			}
		}
	}
	
	private static void useIndex(
			ONEPlusElement element,
			ONEPlusElement index,
			int nest) {
		
		if(element.getDefinition().equals("DIRECTIVE")) {
			
			if(element.getContent().equalsIgnoreCase("INDEX")) {
				
				int indexNest = 0;
				
				for(int i = 0; i < element.getNumElements(); i++) {
					
					if(element.getElement(i).getContent().equalsIgnoreCase("NEST"))
						indexNest = Integer.parseInt(element.getElement(i).getElement(0).getContent());
				}
				
				if(nest == indexNest) {
					
					element.setDefinition("ELEMENT");
					element.setContent(index.getContent());
					
					for(int i = 0; i < index.getNumElements(); i++)
						element.addElement(index.getElement(i));
				}
			}
			
			if(element.getContent().equalsIgnoreCase("FOR"))
				nest++;
		}
		
		for(int i = 0; i < element.getNumElements(); i++)
			useIndex((ONEPlusElement) element.getElement(i), index, nest);
	}
	
	private static void useIndex(
			ONEPlusElement element,
			int index,
			int nest) {
		
		if(element.getDefinition().equals("DIRECTIVE")) {
			
			if(element.getContent().equalsIgnoreCase("INDEX")) {
				
				int indexNest = 0;
				
				for(int i = 0; i < element.getNumElements(); i++) {
					
					if(element.getElement(i).getContent().equalsIgnoreCase("NEST"))
						indexNest = Integer.parseInt(element.getElement(i).getElement(0).getContent());
				}
				
				if(nest == indexNest) {
					element.setDefinition("ELEMENT");
					element.setContent("" + index);
				}
			}
			
			if(element.getContent().equalsIgnoreCase("FOR"))
				nest++;
		}
		
		for(int i = 0; i < element.getNumElements(); i++)
			useIndex((ONEPlusElement) element.getElement(i), index, nest);
	}
	
	private static void processIf(
			ONEPlusElement element, int index,
			ONEPlusElement directive,
			String fileName) {
		
		boolean condition = false;
		boolean flip = false;
		
		Element ifDirective = directive;
		
		if(ifDirective.getElement(0).getContent().equals("NOT")) {
			ifDirective = ifDirective.getElement(0);
			flip = true;
		}
		
		if(ifDirective.getElement(0).getContent().equalsIgnoreCase("OS")) {
			String os = System.getProperty("os.name").toUpperCase();
			condition = os.contains(ifDirective.getElement(0).getElement(0).getContent().toUpperCase());
		}
		
		if(ifDirective.getElement(0).getContent().equalsIgnoreCase("FILE")) {
			String file = fileName.toUpperCase();
			condition = file.contains(ifDirective.getElement(0).getElement(0).getContent().toUpperCase());
		}
		
		if(flip)
			condition = !condition;
		
		if(condition)
			element.addElements(directive.getElements(), index);
	}
	
	private static void processUse(
			ONEPlusElement element, int index,
			ONEPlusElement directive) {
		
		Definition definition =
				findDefinition(element, directive.getElement(0).getContent());
		
		ArrayList<Element> newElements = new ArrayList<Element>();
		
		for(int i = 0; i < definition.getElements().size(); i++) {
			
			ONEPlusElement newElement = new ONEPlusElement((ONEPlusElement) definition.getElements().get(i));
			processUseArguments(newElement, new ONEPlusElement(directive));
			
			newElements.add(newElement);
		}
		
		if(directive.getNumElements() == 2)
			element.addElement(newElements.get(Integer.parseInt(directive.getElement(1).getContent()) - 1), index);
		
		else
			element.addElements(newElements, index);
	}
	
	private static void processUseArguments(
			ONEPlusElement element,
			ONEPlusElement directive) {
		
		if(element.getDefinition().equals("DIRECTIVE")) {
			
			if(element.getContent().equals("ARG")) {
				
				element.setDefinition("ELEMENT");
				
				Element arg = new ONEPlusElement((ONEPlusElement) directive.getElement(0));
				Element index = new ONEPlusElement((ONEPlusElement) element.getElement(0));
				
				while(index != null) {
					arg = arg.getElement(Integer.parseInt(index.getContent()) - 1);
					index = index.getElement(0);
				}
				
				element.setContent(arg.getContent());
			}
		}
		
		for(int i = 0; i < element.getNumElements(); i++)
			processUseArguments((ONEPlusElement) element.getElement(i), directive);
	}
	
	private static Definition findDefinition(
			ONEPlusElement element, String definition) {
		
		for(int i = 0; i < element.getDefinitions().size(); i++) {
			
			if(element.getDefinitions().get(i).getName().equals(definition))
				return new Definition(element.getDefinitions().get(i));
		}
		
		if(element.getParent() != null)
			return findDefinition((ONEPlusElement) element.getParent(), definition);
		
		return null;
	}
	
	private static void processDeclarations(ONEPlusElement element) {
		
		ArrayList<Declaration> declarations = getDeclarations(element);
		
		for(int i = 0; i < declarations.size(); i++)
			useDeclaration(element, declarations.get(i));
		
		ArrayList<Declaration> rules = new ArrayList<Declaration>();
		
		for(int i = 0; i < declarations.size(); i++) {

			if(
					declarations.get(i).getName().equalsIgnoreCase("ESCAPE") ||
					declarations.get(i).getName().equalsIgnoreCase("INFIX") ||
					declarations.get(i).getName().equalsIgnoreCase("PREFIX")) {
				
				rules.add(declarations.get(i));
			}
		}
		
		useRules(element, rules);
		
		for(int i = 0; i < element.getNumElements(); i++)
			processDeclarations((ONEPlusElement) element.getElement(i));
	}
	
	private static ArrayList<Declaration> getDeclarations(ONEPlusElement element) {
		
		ArrayList<Declaration> declarations = new ArrayList<Declaration>();
		
		ONEPlusElement currentElement = element;
		
		while(currentElement != null) {
			declarations.addAll(currentElement .getDeclarations());
			currentElement = (ONEPlusElement) currentElement.getParent();
		}
		
		return declarations;
	}
	
	private static void useDeclaration(ONEPlusElement element, Declaration declaration) {
		
		if(declaration.getName().equalsIgnoreCase("PROPERTIES"))
			useProperty(element, declaration);
	}
	
	private static void useProperty(ONEPlusElement element, Declaration declaration) {
		
		String elementName = declaration.getElements().get(0).getContent();
		String propertiesName = null;
		
		if(declaration.getElements().get(0).getNumElements() >= 1)
			propertiesName = declaration.getElements().get(0).getElement(0).getContent();
		
		if(propertiesName != null) {
			
			if(elementName.equals(element.getContent())) {
				
				for(int i = 0; i < element.getNumElements(); i++) {
					
					if(((ONEPlusElement) element.getElement(i)).getDefinition().equals("ELEMENT PROPERTY")) {
						
						((ONEPlusElement) element.getElement(i)).setDefinition("ELEMENT");
						
						ONEPlusElement property = new ONEPlusElement(propertiesName);
						property.addElement(element.removeElement(i));
						
						unmarkProperty(property);
						
						element.addElement(property, i);
					}
				}
			}
		}
		
		else {
			
			for(int i = 0; i < element.getNumElements(); i++) {
				
				if(((ONEPlusElement) element.getElement(i)).getDefinition().equals("ELEMENT PROPERTY")) {
					
					((ONEPlusElement) element.getElement(i)).setDefinition("ELEMENT");
					
					ONEPlusElement property = new ONEPlusElement(elementName);
					property.addElement(element.removeElement(i));
					
					unmarkProperty(property);
					
					element.addElement(property, i);
				}
			}
		}
	}
	
	private static void unmarkProperty(ONEPlusElement element) {
		
		element.setDefinition("ELEMENT");
		
		for(int i = 0; i < element.getNumElements(); i++)
			unmarkProperty((ONEPlusElement) element.getElement(i));
	}
	
	private static void useRules(ONEPlusElement element, ArrayList<Declaration> rules) {
		
		for(int i = 0; i < element.getNumElements(); i++)
			parseInfixes((ONEPlusElement) element.getElement(i), i, rules);
		
		for(int i = 0; i < element.getNumElements(); i++)
			parsePrefixes((ONEPlusElement) element.getElement(i), i, rules);
	}
	
	private static void parseInfixes(ONEPlusElement element, int index, ArrayList<Declaration> rules) {
		
		if(!element.getDefinition().contains("ELEMENT"))
			return;
		
		parseInfix(element, index, rules);
	}
	
	private static void parsePrefixes(ONEPlusElement element, int index, ArrayList<Declaration> rules) {
		
		if(!element.getDefinition().contains("ELEMENT"))
			return;
		
		for(int i = 0; i < rules.size(); i++)
			parsePrefix(element, index, rules, i);
	}
	
	private static void parseInfix(
			ONEPlusElement element, int index,
			ArrayList<Declaration> rules) {
		
		String line = element.getContent();
		
		int escapeSequence = -1;
		
		for(int i = 0; i < line.length(); i++) {
			
			if(escapeSequence >= 0) {
				
				if(isEndEscapeSequence(rules.get(escapeSequence), line, i))
					escapeSequence = -1;
			}
			
			else
				escapeSequence = isBeginEscapeSequence(rules, line, i);
			
			if(escapeSequence >= 0)
				continue;
			
			int infixLength = isInfix(rules, line, i);
			
			if(infixLength > 0 && escapeSequence == -1) {
				
				ONEPlusElement parent = (ONEPlusElement) element.getParent();
				
				String infixOperator = line.substring(i, i + infixLength);
				
				String left = line.substring(0, i).trim();
				String right = line.substring(i + infixLength).trim();
				
				parent.removeElement(index);
				
				if(left.length() > 0) {
					
					ONEPlusElement leftElement = new ONEPlusElement(left, element.getDefinition());
					
					leftElement.setLineNumber(element.getLineNumber());
					leftElement.setElementNumber(element.getElementNumber());
					
					parent.addElement(leftElement, index);
					
					index++;
				}
				
				ONEPlusElement infixElement = new ONEPlusElement(infixOperator, element.getDefinition() + " INFIX");
				
				infixElement.setLineNumber(element.getLineNumber());
				infixElement.setElementNumber(element.getElementNumber());
				
				infixElement.addElements(element.getElements());
				
				parent.addElement(infixElement, index);
				
				index++;
				
				if(right.length() > 0) {
					
					ONEPlusElement rightElement = new ONEPlusElement(right, element.getDefinition());
					
					rightElement.setLineNumber(element.getLineNumber());
					rightElement.setElementNumber(element.getElementNumber());
					
					parent.addElement(rightElement, index);
				}
				
				if(left.length() > 0 || right.length() > 0)
					return;
			}
		}
	}
	
	private static void parsePrefix(
			ONEPlusElement element, int index,
			ArrayList<Declaration> rules, int rule) {
		
		String line = element.getContent();
		
		int escapeSequence = -1;
		
		for(int i = 0; i < line.length(); i++) {
			
			if(escapeSequence >= 0) {
				
				if(isEndEscapeSequence(rules.get(escapeSequence), line, i))
					escapeSequence = -1;
			}
			
			else
				escapeSequence = isBeginEscapeSequence(rules, line, i);
			
			if(escapeSequence >= 0)
				continue;
			
			int prefixLength = isPrefix(rules, rule, line, i);
			
			if(prefixLength > 0 && escapeSequence == -1) {
				
				ONEPlusElement parent = (ONEPlusElement) element.getParent();
				
				String prefixOperator = line.substring(i, i + prefixLength);
				
				String left = line.substring(0, i).trim();
				String right = line.substring(i + prefixLength).trim();
				
				parent.removeElement(index);
				
				ONEPlusElement prefixElement = new ONEPlusElement();
				
				if(left.length() > 0) {
					
					ONEPlusElement leftElement = new ONEPlusElement(left, element.getDefinition());
					
					leftElement.setLineNumber(element.getLineNumber());
					leftElement.setElementNumber(element.getElementNumber());
					
					prefixElement.addElement(leftElement);
				}
				
				prefixElement.setContent(prefixOperator);
				prefixElement.setDefinition(element.getDefinition() + " PREFIX");
				
				prefixElement.setLineNumber(element.getLineNumber());
				prefixElement.setElementNumber(element.getElementNumber());
				
				prefixElement.addElements(element.getElements());
				
				if(right.length() > 0) {
					
					ONEPlusElement rightElement = new ONEPlusElement(right, element.getDefinition());
					
					rightElement.setLineNumber(element.getLineNumber());
					rightElement.setElementNumber(element.getElementNumber());
					
					prefixElement.addElement(rightElement);
				}
				
				parent.addElement(prefixElement, index);
				
				if(left.length() > 0 || right.length() > 0)
					return;
			}
		}
	}
	
	private static int isBeginEscapeSequence(
			ArrayList<Declaration> rules,
			String line, int index) {
		
		for(int i = 0; i < rules.size(); i++) {
			
			if(rules.get(i).getName().equalsIgnoreCase("ESCAPE")) {
				
				if(line.indexOf(rules.get(i).getElements().get(0).getContent(), index) == index)
					return i;
			}
		}
		
		return -1;
	}
	
	private static boolean isEndEscapeSequence(
			Declaration escapeSequence,
			String line, int index) {
		
		return line.indexOf(escapeSequence.getElements().get(1).getContent(), index) == index;
	}
	
	private static int isInfix(
			ArrayList<Declaration> rules,
			String line, int index) {
		
		for(int i = 0; i < rules.size(); i++) {
			
			if(rules.get(i).getName().equalsIgnoreCase("INFIX")) {
				
				if(line.indexOf(rules.get(i).getElements().get(0).getContent(), index) == index)
					return rules.get(i).getElements().get(0).getContent().length();
			}
		}
		
		return 0;
	}
	
	private static int isPrefix(
			ArrayList<Declaration> rules, int rule,
			String line, int index) {
		
		if(rules.get(rule).getName().equalsIgnoreCase("PREFIX")) {
			
			if(line.indexOf(rules.get(rule).getElements().get(0).getContent(), index) == index)
				return rules.get(rule).getElements().get(0).getContent().length();
		}
		
		return 0;
	}
}