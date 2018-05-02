package super_mode;

import java.util.ArrayList;
import java.util.Arrays;

import aether_one_plus.Aether;
import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;
import tokenizer.Tokenizer;

public class SuperMode extends DirectiveUnit {
	
	public static int ALTERNATE_NONE = 0;
	public static int ALTERNATE_SIBLING = 1;
	public static int ALTERNATE_CHILDREN = 2;
	
	public static ArrayList<String> tokens = null;
	public static ArrayList<ArrayList<String>> tokenPriority = null;
	
	public int numFor;
	public ArrayList<Element> preProcessed;
	
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		if(tokens == null)
			initializeTokens();
		
		if(directive.directive.content.equalsIgnoreCase("SUPER")) {
			
			preProcessed = new ArrayList<Element>();
			
			useStandardDirectives(directiveUnits);
			
			int index = ElementUtilities.getIndex(directive.directive);
			
			directive.directive.parent.children.remove(index);
			
			Element use = getElement("Use");
			
			ElementUtilities.addChild(use, getElement("Standard"));
			ElementUtilities.addChild(use, getElement("Stack"));
			
			ElementUtilities.addChild(directive.directive.parent, use, index);
			
			superMode(directives, directive.directive.parent, index);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void useStandardDirectives(ArrayList<DirectiveUnit> directiveUnits) {
		
		try {
			
			directiveUnits.addAll(
					(ArrayList<DirectiveUnit>) Aether.call("Standard", 0, null));
		}
		
		catch(Exception exception) {
			
		}
	}
	
	public void superMode(
			ArrayList<Directive> directives,
			Element element,
			int index) {
		
		if(isDirective(directives, element))
			return;
		
		for(int i = index; i < element.children.size(); i++) {
			
			if(isDirective(directives, element.children.get(i))) {
				
				if(element.children.get(i).content.equalsIgnoreCase("SUB"))
					break;
			}
			
			superMode(directives, element.children.get(i), 0);
		}
		
		try {
			
			if(element.content != null) {
				
				if(element.content.length() > 0)
					applySuperMode(element);
			}
		}
		
		catch(Exception exception) {
			
		}
		
		for(int i = index; i < element.children.size(); i++) {
			
			if(isDirective(directives, element.children.get(i))) {
				
				if(element.children.get(i).content.equalsIgnoreCase("SUB"))
					break;
			}
			
			superMode(directives, element.children.get(i), 0);
		}
	}
	
	public boolean isDirective(
			ArrayList<Directive> directives,
			Element element) {
		
		for(int i = 0; i < directives.size(); i++) {
			
			if(directives.get(i).directive == element)
				return true;
		}
		
		return false;
	}
	
	public int getTokenIndex(String content, String token) {
		
		boolean inQuote = false;
		
		for(int i = 0; i < content.length(); i++) {
			
			if(content.charAt(i) == '\"')
				inQuote = !inQuote;
			
			if(!inQuote && i == content.toLowerCase().indexOf(token.toLowerCase(), i))
				return i;
		}
		
		return -1;
	}
	
	public ArrayList<Element> getValues(
			Element element,
			String token,
			int leftAlternate,
			int rightAlternate) {
		
		ArrayList<Element> values = new ArrayList<Element>();
		
		int index = getTokenIndex(element.content, token);
		
		String left =
				element.content.substring(0, index).trim();
		
		String right =
				element.content.substring(index + token.length()).trim();
		
		if(left.length() == 0) {
			
			if(leftAlternate == ALTERNATE_SIBLING &&
					ElementUtilities.getIndex(element) > 0) {
				
				left =
						element.parent.children.remove(
								ElementUtilities.getIndex(element) - 1).
						content;
			}
		}
		
		values.add(getElement(left));
		
		if(right.length() == 0) {
			
			if(rightAlternate == ALTERNATE_SIBLING &&
					ElementUtilities.getIndex(element) <
					element.parent.children.size() - 1) {
				
				values.add(getElement(
						element.parent.children.remove(
								ElementUtilities.getIndex(element) + 1).
						content));
			}
			
			else if(rightAlternate == ALTERNATE_CHILDREN) {
				
				values.addAll(element.children);
				
				element.children = new ArrayList<Element>();
			}
		}
		
		else
			values.add(getElement(right));
		
		if(values.size() > 1) {
			
			ElementUtilities.addChildren(values.get(values.size() - 1), element.children);
			
			element.children = new ArrayList<Element>();
		}
		
		return values;
	}
	
	public Element getElement(String content) {
		
		Element element = new Element();
		element.content = content;
		
		return element;
	}
	
	@SuppressWarnings({ "static-access" })
	public String getFirstToken(String content) {
		
		content = content.toLowerCase();
		
		ArrayList<String> tokens = new ArrayList<String>(this.tokens);
		
		tokens.add("\"");
		tokens.add(" ");
		tokens.add("\t");
		
		ArrayList<String> tokenize = Tokenizer.tokenize(tokens, content);
		
		boolean quote = false;
		
		for(int i = 0; i < tokenPriority.size(); i++) {
			
			for(int j = 0; j < tokenize.size(); j++) {
				
				if(tokenize.get(j).equals("\"")) {
					
					quote = !quote;
					
					continue;
				}
				
				if(quote)
					continue;
				
				String token = contains(tokenPriority.get(i), tokenize.get(j));
				
				if(token != null) {
					
					if(
							(i == 0 || (
									tokenize.get(j).equalsIgnoreCase("not") ||
									tokenize.get(j).equalsIgnoreCase("and") ||
									tokenize.get(j).equalsIgnoreCase("or") ||
									tokenize.get(j).equalsIgnoreCase("xor"))) &&
							tokenize.size() > 1) {
						
						if(j == 0) {
							
							if(!(tokenize.get(j + 1).equals(" ") || tokenize.get(j + 1).equals("\t")))
								continue;
						}
						
						else if(j == tokenize.size() - 1) {
							
							if(!(tokenize.get(j - 1).equals(" ") || tokenize.get(j - 1).equals("\t")))
								continue;
						}
						
						else if(!(
								(tokenize.get(j + 1).equals(" ") || tokenize.get(j + 1).equals("\t")) &&
								(tokenize.get(j - 1).equals(" ") || tokenize.get(j - 1).equals("\t")))) {
							
							continue;
						}
					}
					
					if(content.toLowerCase().indexOf(token.toLowerCase()) > -1)
						return token.toLowerCase();
				}
			}
		}
		
		return null;
	}
	
	public String contains(ArrayList<String> list, String token) {
		
		for(int i = 0; i < list.size(); i++) {
			
			if(list.get(i).equalsIgnoreCase(token))
				return token;
		}
		
		return null;
	}
	
	public void initializeTokens() {
		
		tokens = new ArrayList<String>(Arrays.asList(
				"params",
				"print",
				"log",
				"log line",
				"not",
				"import",
				"return",
				"new",
				"sin",
				"cos",
				"tan",
				"abs",
				"sqrt",
				"logn",
				"global",
				"define",
				"def",
				"if",
				"else",
				"elif",
				"while",
				"for",
				"for each",
				"for range",
				"=",
				"+=",
				"-=",
				"*=",
				"/=",
				"%=",
				"&=",
				"->",
				"=>",
				"|",
				"@",
				"and",
				"or",
				"xor",
				">",
				">=",
				"<",
				"<=",
				"==",
				"!=",
				"++",
				"--",
				"&",
				"+",
				"-",
				"**",
				"*",
				"/",
				"%",
				"from",
				"in",
				"to",
				"do"));
		
		tokenPriority = new ArrayList<ArrayList<String>>(Arrays.asList(
				new ArrayList<String>(Arrays.asList(
						"params",
						"print",
						"log",
						"log line",
						"import",
						"return",
						"new",
						"sin",
						"cos",
						"tan",
						"abs",
						"sqrt",
						"logn",
						"global",
						"define",
						"def",
						"if",
						"else",
						"elif",
						"while",
						"for",
						"for each",
						"for range")),
				new ArrayList<String>(Arrays.asList(
						"=",
						"+=",
						"-=",
						"*=",
						"/=",
						"%=",
						"&=")),
				new ArrayList<String>(Arrays.asList(
						"not",
						"and",
						"or",
						"xor")),
				new ArrayList<String>(Arrays.asList(
						">",
						">=",
						"<",
						"<=",
						"==",
						"!=")),
				new ArrayList<String>(Arrays.asList(
						"->",
						"=>",
						"|",
						"@",
						"++",
						"--")),
				new ArrayList<String>(Arrays.asList(
						"&",
						"+",
						"-")),
				new ArrayList<String>(Arrays.asList(
						"**",
						"*",
						"/",
						"%"))));
	}
	
	public boolean applySuperMode(Element element) {
		
		for(int i = 0; i < preProcessed.size(); i++) {
			
			if(preProcessed.get(i) == element)
				return false;
		}
		
		preProcessed.add(element);
		
		if(element.content.equalsIgnoreCase("for")) {
			
			processFor(element);
			
			return true;
		}
		
		if(element.content.equalsIgnoreCase("Less or Equal") ||
				element.content.equalsIgnoreCase("Greater or Equal")) {
			
			return false;
		}
		
		String token = getFirstToken(element.content);
		
		if(token == null)
			return false;
		
		if(token.equals("params"))
			processParams(element);
		
		if(token.equals("print"))
			processPrefix(element, "print", "Log Line");
		
		if(token.equals("log"))
			processPrefix(element, "log", "Log");
		
		if(token.equals("log line"))
			processPrefix(element, "log line", "Log Line");
		
		if(token.equals("not"))
			processPrefix(element, "not", "Not");
		
		if(token.equals("import"))
			processPrefix(element, "import", "Import");
		
		if(token.equals("return"))
			processPrefix(element, "return", "Return");
		
		if(token.equals("new"))
			processPrefix(element, "new ", "New");
		
		if(token.equals("sin"))
			processPrefix(element, "sin", "Sine");
		
		if(token.equals("cos"))
			processPrefix(element, "cos", "Cosine");
		
		if(token.equals("tan"))
			processPrefix(element, "tan", "Tangent");
		
		if(token.equals("abs"))
			processPrefix(element, "abs", "Absolute Value");
		
		if(token.equals("sqrt"))
			processPrefix(element, "sqrt", "Square Root");
		
		if(token.equals("logn"))
			processPrefix(element, "logn", "Natural Logarithm");
		
		if(token.equals("global"))
			processPrefix(element, "global", "Global");
		
		if(token.equals("define"))
			processPrefix(element, "define", "Define");
		
		if(token.equals("def"))
			processPrefix(element, "def", "Define");
		
		if(token.equals("if"))
			processIf(element);
		
		if(token.equals("elif"))
			processElif(element);
		
		if(token.equals("else"))
			processPrefix(element, "else", "Else");
		
		if(token.equals("while"))
			processWhile(element);
		
		if(token.equals("for"))
			processFor(element);
		
		if(token.equals("for each"))
			processForEach(element);
		
		if(token.equals("for range"))
			processForRange(element);
		
		if(token.equals("="))
			processEquals(element);
		
		if(token.equals("+="))
			processIncrementEquals(element, "+=", "Add");
		
		if(token.equals("-="))
			processIncrementEquals(element, "-=", "Subtract");
		
		if(token.equals("*="))
			processIncrementEquals(element, "*=", "Multiply");
		
		if(token.equals("/="))
			processIncrementEquals(element, "/=", "Divide");
		
		if(token.equals("%="))
			processIncrementEquals(element, "%=", "Modulus");
		
		if(token.equals("&="))
			processIncrementEquals(element, "&=", "Concatenate");
		
		if(token.equals("&"))
			processInfix(element, "&", "Concatenate");
		
		if(token.equals("|"))
			processSwap(element);
		
		if(token.equals("->"))
			processIn(element);
		
		if(token.equals("=>"))
			processField(element);
		
		if(token.equals("@"))
			processInfix(element, "@", "At");
		
		if(token.equals("=="))
			processInfix(element, "==", "Equal");
		
		if(token.equals("!="))
			processNotEqual(element);
		
		if(token.equals("and"))
			processInfix(element, "and", "And");
		
		if(token.equals("or"))
			processInfix(element, "or", "Or");
		
		if(token.equals("xor"))
			processInfix(element, "xor", "Xor");
		
		if(token.equals(">"))
			processInfix(element, ">", "Greater");
		
		if(token.equals(">="))
			processInfix(element, ">=", "Greater or Equal");
		
		if(token.equals("<"))
			processInfix(element, "<", "Less");
		
		if(token.equals("<="))
			processInfix(element, "<=", "Less or Equal");
		
		if(token.equals("++"))
			processIncrement(element, "++", "Add");
		
		if(token.equals("--"))
			processIncrement(element, "--", "Subtract");
		
		if(token.equals("+"))
			processInfix(element, "+", "Add");
		
		if(token.equals("-"))
			processInfix(element, "-", "Subtract");
		
		if(token.equals("**"))
			processInfix(element, "**", "Power");
		
		if(token.equals("*"))
			processInfix(element, "*", "Multiply");
		
		if(token.equals("/"))
			processInfix(element, "/", "Divide");
		
		if(token.equals("%"))
			processInfix(element, "%", "Modulus");
		
		return true;
	}
	
	public void processParams(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"params",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		Element parent = element.parent;
		int index = ElementUtilities.getIndex(element);
		
		element.content = "";
		
		parent.children.remove(index);
		
		for(int i = 1; i < values.size(); i++) {
			
			Element parameter = getElement(values.get(i).content);
			
			Element at = getElement("At");
			
			ElementUtilities.addChild(at, getElement("Arguments"));
			ElementUtilities.addChild(at, getElement("" + i));
			
			ElementUtilities.addChild(parameter, at);
			
			ElementUtilities.addChild(parent, parameter, index + i - 1);
		}
	}
	
	public void processPrefix(Element element, String token, String operator) {
		
		ArrayList<Element> values =
				getValues(
						element,
						token,
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		element.content = operator;
		
		for(int i = 1; i < values.size(); i++)
			ElementUtilities.addChild(element, values.get(i));
	}
	
	public void processInfix(Element element, String token, String operator) {
		
		ArrayList<Element> children = new ArrayList<Element>(element.children);
		element.children = new ArrayList<Element>();
		
		ArrayList<Element> values =
				getValues(
						element,
						token,
						ALTERNATE_SIBLING,
						ALTERNATE_SIBLING);
		
		if(values.size() < 2) {
			
			for(int i = 0; i < children.size(); i++)
				ElementUtilities.addChild(element, children.get(i));
			
			return;
		}
		
		for(int i = 0; i < values.size(); i++) {
			
			if(values.get(i).content.length() == 0) {
				
				for(int j = 0; j < children.size(); j++)
					ElementUtilities.addChild(element, children.get(j));
				
				return;
			}
		}
		
		element.content = operator;
		
		for(int i = 0; i < values.size(); i++)
			ElementUtilities.addChild(element, values.get(i));
	}
	
	public void processEquals(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"=",
						ALTERNATE_NONE,
						ALTERNATE_NONE);
		
		element.content = values.get(0).content;
		
		for(int i = 1; i < values.size(); i++)
			ElementUtilities.addChild(element, values.get(i));
	}
	
	public void processIncrementEquals(Element element, String infix, String operator) {
		
		ArrayList<Element> values =
				getValues(
						element,
						infix,
						ALTERNATE_NONE,
						ALTERNATE_NONE);
		
		element.content = values.get(0).content;
		
		Element operation = getElement(operator);
		
		ElementUtilities.addChild(operation, getElement(element.content));
		ElementUtilities.addChild(element, operation);
		
		for(int i = 1; i < values.size(); i++)
			ElementUtilities.addChild(operation, values.get(i));
	}
	
	public void processIncrement(Element element, String infix, String operator) {
		
		ArrayList<Element> values =
				getValues(
						element,
						infix,
						ALTERNATE_NONE,
						ALTERNATE_NONE);
		
		element.content = values.get(0).content;
		
		Element operation = getElement(operator);
		
		ElementUtilities.addChild(operation, getElement(element.content));
		ElementUtilities.addChild(operation, getElement("1"));
		ElementUtilities.addChild(element, operation);
	}
	
	public void processNotEqual(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"!=",
						ALTERNATE_SIBLING,
						ALTERNATE_SIBLING);
		
		element.content = "Not";
		
		Element equal = getElement("Equal");
		
		for(int i = 0; i < values.size(); i++)
			ElementUtilities.addChild(equal, values.get(i));
		
		ElementUtilities.addChild(element, equal);
	}
	
	public void processSwap(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"|",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		int index = ElementUtilities.getIndex(element);
		element.parent.children.remove(index);
		
		for(int i = 1; i < values.size(); i++) {
			
			Element swap = getElement(values.get(i).content);
			
			ElementUtilities.addChild(swap, getElement(values.get(0).content));
			
			for(int j = 0; j < values.get(i).children.size(); j++)
				ElementUtilities.addChild(swap, ElementUtilities.copyElement(values.get(i).children.get(j)));

			ElementUtilities.addChild(element.parent, swap, index + i - 1);
		}
	}
	
	public void processIn(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"->",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		element.content = "Scope";
		
		Element in = getElement("In");

		ElementUtilities.addChild(in, getElement(values.get(0).content));
		ElementUtilities.addChild(element, in);
		
		values.remove(0);
		
		ElementUtilities.addChildren(element, values);
	}
	
	public void processField(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"=>",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		int index = ElementUtilities.getIndex(element);
		element.parent.children.remove(index);
		
		for(int i = 1; i < values.size(); i++) {
			
			Element scope = getElement("Scope");
			
			Element in = getElement("In");
	
			ElementUtilities.addChild(in, getElement(values.get(0).content));
			ElementUtilities.addChild(scope, in);
			
			Element field = getElement("Return");
			
			ElementUtilities.addChild(field, values.get(i));
			ElementUtilities.addChild(scope, field);
			
			ElementUtilities.addChild(element.parent, scope, index + i - 1);
		}
	}
	
	public void processIf(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"if",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		element.content = "Scope";
		
		Element condition = getElement("Break");
		Element not = getElement("Not");
		
		ElementUtilities.addChild(not, values.get(1));
		ElementUtilities.addChild(condition, not);
		ElementUtilities.addChild(element, condition);
		
		for(int i = 2; i < values.size(); i++)
			ElementUtilities.addChild(element, values.get(i));
	}
	
	public void processElif(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"elif",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		element.content = "Else";
		
		Element condition = getElement("Break");
		Element not = getElement("Not");
		
		ElementUtilities.addChild(not, values.get(1));
		ElementUtilities.addChild(condition, not);
		ElementUtilities.addChild(element, condition);
		
		for(int i = 2; i < values.size(); i++)
			ElementUtilities.addChild(element, values.get(i));
	}
	
	public void processWhile(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"while",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		element.content = "Scope";
		
		for(int i = 2; i < values.size(); i++)
			ElementUtilities.addChild(element, values.get(i));
		
		Element condition = getElement("Loop");
		
		ElementUtilities.addChild(condition, values.get(1));
		ElementUtilities.addChild(element, condition);
	}
	
	public void processFor(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"for",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		Element varDec = getElement("For" + numFor);
		ElementUtilities.addChild(varDec, getElement("0"));
		
		Element varLim = getElement("ForLim" + numFor);
		ElementUtilities.addChild(varLim, values.get(1));

		ElementUtilities.addChild(element.parent, varLim, ElementUtilities.getIndex(element));
		ElementUtilities.addChild(element.parent, varDec, ElementUtilities.getIndex(element));
		
		element.content = "Scope";
		
		for(int i = 2; i < values.size(); i++)
			ElementUtilities.addChild(element, values.get(i));
		
		Element increment = getElement("For" + numFor);
		Element add = getElement("Add");

		ElementUtilities.addChild(add, getElement("For" + numFor));
		ElementUtilities.addChild(add, getElement("1"));
		ElementUtilities.addChild(increment, add);
		ElementUtilities.addChild(element, increment);
		
		Element condition = getElement("Loop");
		Element less = getElement("Less");
		
		ElementUtilities.addChild(less, getElement("For" + numFor));
		ElementUtilities.addChild(less, getElement("ForLim" + numFor));
		ElementUtilities.addChild(condition, less);
		ElementUtilities.addChild(element, condition);
		
		numFor++;
	}
	
	public void processForEach(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"for each",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		Element varDec = getElement("For" + numFor);
		ElementUtilities.addChild(varDec, getElement("1"));
		
		ElementUtilities.addChild(element.parent, varDec, ElementUtilities.getIndex(element));
		
		element.content = "Scope";
		
		Element var = getElement(values.get(1).content);
		Element at = getElement("At");

		ElementUtilities.addChild(at, ElementUtilities.copyElement(values.get(2)));
		ElementUtilities.addChild(at, getElement("For" + numFor));
		ElementUtilities.addChild(var, at);
		ElementUtilities.addChild(element, var);
		
		for(int i = 3; i < values.size(); i++)
			ElementUtilities.addChild(element, values.get(i));
		
		Element increment = getElement("For" + numFor);
		Element add = getElement("Add");

		ElementUtilities.addChild(add, getElement("For" + numFor));
		ElementUtilities.addChild(add, getElement("1"));
		ElementUtilities.addChild(increment, add);
		ElementUtilities.addChild(element, increment);
		
		Element condition = getElement("Loop");
		Element less = getElement("Less or Equal");
		Element size = getElement("Size");

		ElementUtilities.addChild(size, ElementUtilities.copyElement(values.get(2)));
		ElementUtilities.addChild(less, getElement("For" + numFor));
		ElementUtilities.addChild(less, size);
		ElementUtilities.addChild(condition, less);
		ElementUtilities.addChild(element, condition);
		
		numFor++;
	}
	
	public void processForRange(Element element) {
		
		ArrayList<Element> values =
				getValues(
						element,
						"for range",
						ALTERNATE_NONE,
						ALTERNATE_CHILDREN);
		
		Element varDec = getElement("For" + numFor);
		ElementUtilities.addChild(varDec, values.get(2));
		
		Element varLim = getElement("ForLim" + numFor);
		ElementUtilities.addChild(varLim, values.get(3));

		ElementUtilities.addChild(element.parent, varLim, ElementUtilities.getIndex(element));
		ElementUtilities.addChild(element.parent, varDec, ElementUtilities.getIndex(element));
		
		element.content = "Scope";
		
		Element var = getElement(values.get(1).content);
		
		ElementUtilities.addChild(var, getElement("For" + numFor));
		ElementUtilities.addChild(element, var);
		
		for(int i = 4; i < values.size(); i++)
			ElementUtilities.addChild(element, values.get(i));
		
		Element increment = getElement("For" + numFor);
		Element add = getElement("Add");

		ElementUtilities.addChild(add, getElement("For" + numFor));
		ElementUtilities.addChild(add, getElement("1"));
		ElementUtilities.addChild(increment, add);
		ElementUtilities.addChild(element, increment);
		
		Element condition = getElement("Loop");
		Element less = getElement("Less or Equal");
		
		ElementUtilities.addChild(less, getElement("For" + numFor));
		ElementUtilities.addChild(less, getElement("ForLim" + numFor));
		ElementUtilities.addChild(condition, less);
		ElementUtilities.addChild(element, condition);
		
		numFor++;
	}
}