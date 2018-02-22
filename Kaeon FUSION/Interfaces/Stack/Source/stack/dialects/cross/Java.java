package stack.dialects.cross;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import stack.utilities.cross_dialect.Category;
import stack.utilities.cross_dialect.CrossDialect;

public class Java extends CrossDialect {
	
	public String formatIdentifier(String identifier) {
		return identifier.replaceAll(" ", "").toLowerCase();
	}
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		file.add(formatIdentifier(name) + ".java");
		file.add(build);
		
		files.add(file);
	}
	
	public String buildMain(
			String name,
			Element main,
			ArrayList<Category> categories,
			String body) {
		
		return
				"public class " +
				formatIdentifier(name) +
				"{public static void main(String[]args){boolean scope=false;" +
				body +
				"}}";
	}
	
	public String buildBodyElementSeparator() {
		return ";";
	}
	
	public String buildVariableDeclaration(Element element, ArrayList<String> arguments, Element meta) {
		
		String declaration = "";
		
		Element type = ElementUtilities.getChild(meta, "Type");
		
		if(type != null) {
			
			for(int i = 0; i < type.children.size(); i++) {
				
				if(type.children.get(i).content.equalsIgnoreCase("Integer"))
					declaration += "int ";
				
				if(type.children.get(i).content.equalsIgnoreCase("Double"))
					declaration += "double ";
				
				if(type.children.get(i).content.equalsIgnoreCase("Float"))
					declaration += "float ";
				
				if(type.children.get(i).content.equalsIgnoreCase("Character"))
					declaration += "char ";
				
				if(type.children.get(i).content.equalsIgnoreCase("Boolean"))
					declaration += "boolean ";
				
				// STUB
			}
			
			// STUB
		}
		
		else {
			// STUB
		}
		
		return declaration + element.content + "=" + arguments.get(0);
	}
	
	public String buildObjectOperation(
			String operator,
			String operation,
			Element meta) {
		
		// STUB
		
		return "";
	}
	
	public String buildOperator(
			Element element,
			ArrayList<String> arguments,
			Element meta) {
		
		// STUB
		
		return null;
	}
	
	public String buildVariableAssignment(Element element, ArrayList<String> arguments, Element meta) {
		return element.content + "=" + arguments.get(0);
	}
	
	public String buildVariableReference(Element element, ArrayList<String> arguments, Element meta) {
		return element.content;
	}
	
	public String buildThis(Element element, ArrayList<String> arguments, Element meta) {
		return "this";
	}
	
	public String buildNull(Element element, ArrayList<String> arguments, Element meta) {
		return "null";
	}
	
	public String buildReturn(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "return ";
		
		return build + arguments.get(0);
	}
	
	public String buildScope(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		String build = "do{";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + ';';
		
		return build + "}while(false)";
	}
	
	public String buildBreak(Element element, ArrayList<String> arguments, Element meta, int nest) {
		return "if(" + (arguments.size() > 0 ? arguments.get(0) : "true") + "){scope=true;break;}";
	}
	
	public String buildElse(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		String build = "if(scope)do{scope=false;";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + ';';
		
		return build + "}while(false)";
	}
	
	public String buildLoop(Element element, ArrayList<String> arguments, Element meta, int nest) {
		return "if(" + (arguments.size() > 0 ? arguments.get(0) : "true") + ")continue;";
	}
	
	public String buildRun(Element element, ArrayList<String> arguments, Element meta) {
		return "try{Runtime.getRuntime().exec(" + buildConcatenate(element, arguments, meta) + ");}catch(Exception e){}";
	}
	
	public String buildWait(Element element, ArrayList<String> arguments, Element meta) {
		return "try{Thread.sleep(((int)" + arguments.get(0) + ")*1000);}catch(Exception e){}";
	}
	
	public String buildSplit(Element element, ArrayList<String> arguments, Element meta) {
		
		String body = "";
		
		for(int i = 0; i < arguments.size(); i++)
			body += arguments.get(i) + buildBodyElementSeparator();
		
		return "try{new Thread(new Runnable(){public void run(){" + body + "}}).start();}catch(Exception e){}";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta) {
		return "System.out.print(" + buildConcatenate(element, arguments, meta) + ")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta) {
		return "System.out.println(" + buildConcatenate(element, arguments, meta) + ")";
	}
	
	public String buildInput(Element element, ArrayList<String> arguments, Element meta) {
		return "new Scanner(System.in).nextLine()";
	}
	
	public String buildTime(Element element, ArrayList<String> arguments, Element meta) {
		return "(double) System.currentTimeMillis() / 1000";
	}
	
	/*
	 * OPEN
	 * SAVE
	 * LIST
	 * LIST OPERATIONS
	 */
	
	public String buildConcatenate(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "\"\"+";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += "+";
		}
		
		return build;
	}
	
	// CROP
	// CASTING OPERATIONS
	
	public String buildNot(Element element, ArrayList<String> arguments, Element meta) {
		return "!(" + arguments.get(0) + ")";
	}
	
	public String buildInfix(Element element, ArrayList<String> arguments, Element meta, String infix) {
		
		String build = "";
		
		for(int i = 0; i < arguments.size(); i++)
			build = "(" + build + infix + arguments.get(i) + ")";
		
		return build;
	}
	
	public String buildLogicInfix(Element element, ArrayList<String> arguments, Element meta, String infix) {
		
		String build = "(";
		
		for(int i = 0; i < arguments.size() - 1; i++) {
			
			build += "(" + arguments.get(i) + infix + arguments.get(i + 1) + ")";
			
			if(i < arguments.size() - 2)
				build += "&&";
		}
		
		return build + ")";
	}
	
	public String buildIs(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, "==");
	}
	
	public String buildEqual(Element element, ArrayList<String> arguments, Element meta, String infix) {
		
		String build = "(";
		
		for(int i = 0; i < arguments.size() - 1; i++) {
			
			build += "(" + arguments.get(i) + ".equals(" + arguments.get(i + 1) + "))";
			
			if(i < arguments.size() - 2)
				build += "&&";
		}
		
		return build + ")";
	}
	
	public String buildAnd(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "&&");
	}
	
	public String buildOr(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "||");
	}
	
	public String buildExclusiveOr(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "^");
	}
	
	public String buildGreater(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, ">");
	}
	
	public String buildGreaterOrEqual(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, ">=");
	}
	
	public String buildLess(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, "<");
	}
	
	public String buildLessOrEqual(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, "<=");
	}
	
	public String buildAdd(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "+");
	}
	
	public String buildSubtract(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "-");
	}
	
	public String buildMultiply(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "*");
	}
	
	public String buildDivide(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "/");
	}
	
	public String buildModulus(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, "%");
	}
	
	public String buildRandom(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.random()";
	}
	
	public String buildNegative(Element element, ArrayList<String> arguments, Element meta) {
		return "-" + arguments.get(0);
	}
	
	public String buildPower(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.pow(" + arguments.get(0) + "," + arguments.get(1) + ")";
	}
	
	public String buildSine(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.sin(" + arguments.get(0) + ")";
	}
	
	public String buildCosine(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.cos(" + arguments.get(0) + ")";
	}
	
	public String buildTangent(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.tan(" + arguments.get(0) + ")";
	}
	
	public String buildSquareRoot(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.sqrt(" + arguments.get(0) + ")";
	}
	
	public String buildNaturalLogarithm(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.log(" + arguments.get(0) + ")";
	}
	
	public String buildFloor(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.floor(" + arguments.get(0) + ")";
	}
	
	public String buildCeiling(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.ceil(" + arguments.get(0) + ")";
	}
	
	public String buildToRadians(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.toRadians(" + arguments.get(0) + ")";
	}
	
	public String buildToDegrees(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.toDegrees(" + arguments.get(0) + ")";
	}
	
	public String buildAbsoluteValue(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.abs(" + arguments.get(0) + ")";
	}
}