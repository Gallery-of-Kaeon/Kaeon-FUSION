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
		
		Category functions = getCategory(categories, "Functions");
		
		for(int i = 0; i < functions.objects.size(); i++)
			build = functions.objects.get(i) + build;
		
		build =
				"public class " +
				formatIdentifier(name) +
				build +
				"}";
		
		Category imports = getCategory(categories, "Imports");
		
		for(int i = 0; i < imports.objects.size(); i++)
			build = "import " + imports.objects.get(i) + ";" + build;
		
		files.add(file);
	}
	
	public String buildMain(
			String name,
			Element main,
			ArrayList<Category> categories,
			String body) {
		
		return
				
				"{public static void main(String[]args){boolean scope=false;" +
				body +
				"}";
	}
	
	public String buildOperator(
			Element element,
			ArrayList<String> arguments,
			Element meta) {
		
		// STUB
		
		return null;
	}
	
	public String buildVariableDeclarationType(Element element, ArrayList<String> arguments, Element meta) {
		
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
			}
		}
		
		else {
			declaration = "Object";
		}
		
		return declaration;
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
	
	public String buildRandom(Element element, ArrayList<String> arguments, Element meta) {
		return "Math.random()";
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