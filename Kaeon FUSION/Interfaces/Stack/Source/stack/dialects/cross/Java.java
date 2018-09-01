package stack.dialects.cross;

import java.util.ArrayList;

import build_dialect.cross_dialect.Category;
import build_dialect.cross_dialect.CrossDialect;
import one.Element;
import one.ElementUtilities;

public class Java extends CrossDialect {
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories,
			boolean utility,
			boolean snippet) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(!snippet) {
			
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
			
			Category classes = getCategory(categories, "Classes");
			
			for(int i = 0; i < classes.objects.size(); i++)
				buildClass("" + classes.objects.get(i), files, imports);
		}
		
		file.add(formatIdentifier(name) + ".java");
		file.add(build);
		
		files.add(file);
	}
	
	public void buildClass(String classBuild, ArrayList<ArrayList<String>> files, Category imports) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		int index = classBuild.indexOf("public class ");
		
		file.add(formatIdentifier(classBuild.substring(index + 14, classBuild.indexOf("{"))) + ".java");
		file.add(classBuild);
		
		files.add(file);
	}
	
	public String buildMain(
			String name,
			Element main,
			ArrayList<Category> categories,
			String body,
			boolean utility,
			boolean snippet) {
		
		String build = "";
		
		if(!snippet) {
			
			ArrayList<Object> helper = new ArrayList<Object>();
	
			helper.addAll(getCategory(categories, "Globals").objects);
			helper.addAll(getCategory(categories, "Functions").objects);
			
			for(int i = 0; i < helper.size(); i++) {
				
				String help = "" + helper.get(i);
				
				if(help.startsWith("public "))
					help = help.substring(7);
				
				if(help.startsWith("private "))
					help = help.substring(8);
				
				if(help.startsWith("protected "))
					help = help.substring(10);
				
				if(help.startsWith("package "))
					help = help.substring(8);
				
				if(help.startsWith("static "))
					help = help.substring(7);
				
				help += "public static ";
				
				build += help;
			}
		}
		
		if(!utility) {
			
			build +=
					(!snippet ?
							"{public static void main(String[]args){" +
							"boolean scope=false;" +
							"ArrayList<Object> arguments=" +
							"new ArrayList<Object>(Arrays.asList(args));" : "") +
					body +
					(!snippet ? "}" : "");
		}
		
		return build;
	}
	
	public void ammendNotation(Element metaNotation, Element notation, ArrayList<Category> categories) {
		
		try {
			
			getCategory(
					categories,
					"Packages").objects.add(
							ElementUtilities.getChild(
									notation,
									"Package").children.get(0).content);
		}
		
		catch(Exception exception) {
			
		}
		
		if(ElementUtilities.hasChild(notation, "Interfaces")) {
			
			getCategory(categories, "Interfaces").objects = new ArrayList<Object>();
			
			Element interfaces = ElementUtilities.getChild(notation, "Interfaces");
			
			for(int i = 0; i < interfaces.children.size(); i++)
				getCategory(categories, "Interfaces").objects.add(interfaces.children.get(i).content);
		}
	}
	
	public void nullifyNotation(Element metaNotation, Element notation, ArrayList<Category> categories) {
		
		if(ElementUtilities.hasChild(notation, "Package"))
			getCategory(categories, "Packages").objects = new ArrayList<Object>();
		
		if(ElementUtilities.hasChild(notation, "Interfaces"))
			getCategory(categories, "Interfaces").objects = new ArrayList<Object>();
	}
	
	public String buildOperator(
			Element element,
			ArrayList<String> arguments,
			Element meta,
			ArrayList<Category> categories) {
		
		if(element.content.equalsIgnoreCase("Instance Of")) {
			return arguments.get(0) + " instanceof " + element.children.get(1).content;
		}
		
		return null;
	}
	
	public String buildVariableDeclarationType(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		if(ElementUtilities.hasChild(meta, "Type"))
			return buildType(ElementUtilities.getChild(meta, "Type"));
		
		return "";
	}
	
	public String buildFunctionDefinition(
			Element function,
			String functionBody,
			Element metaCopy,
			ArrayList<Category> categories,
			ArrayList<String> returnType,
			boolean isConstructor,
			ArrayList<Category> parameters,
			int parameterNumber,
			boolean aliased) {
		
		Element returnTypeElement = new Element();

		try {
			
			returnTypeElement =
					ElementUtilities.getChild(
							ElementUtilities.getChild(
									metaCopy,
									"Function"),
							"Type");
		}
		
		catch(Exception exception) {
			
		}
		
		String build = "";
		
		if(!isConstructor) {
			
			build +=
					buildType(returnTypeElement) +
					" " +
					function.content +
					"(";
		}
		
		else {
			
			build +=
					"public " +
					function.content +
					"(";
		}
		
		for(int i = 0; i < parameterNumber; i++) {
			
			build += "Object arg" + i;
			
			if(i < parameterNumber - 1)
				build += ",";
		}
		
		for(int i = 0; i < parameters.size(); i++) {
			
			Element parameter =
					ElementUtilities.getChild(
							ElementUtilities.getChild(
									metaCopy,
									"Function"),
							"Parameters").children.get(i);
			
			build += buildType(parameter) + " " + parameters.get(i).name;
			
			if(i < parameters.size() - 1)
				build += ",";
		}
		
		build += "){bool scope=false;void* arguments[]={";
		
		for(int i = 0; i < parameterNumber; i++) {
			
			build += "arg" + i;
			
			if(i < parameterNumber - 1)
				build += ",";
		}
		
		return build + "};" + functionBody + "}";
	}
	
	public String buildClassDefinition(Element classElement, String constructor, Element metaCopy, ArrayList<Category> categories, ArrayList<String> inheritence) {
		
		String build = "";
		
		Category packages = getCategory(categories, "Packages");
		
		if(packages.objects.size() > 0)
			build += "package " + packages.objects.get(packages.objects.size() - 1) + ";";
		
		if(packages.objects.size() > 1)
			packages.objects.remove(packages.objects.size() - 1);
		
		Category imports = getCategory(categories, "Imports");
		
		for(int i = 0; i < imports.objects.size(); i++)
			build += "import " + imports.objects.get(i) + ";";
		
		build += "public class " + classElement.content + " ";
		
		if(inheritence.size() >= 1)
			build += "extends " +  inheritence.get(0) + " ";
		
		Category interfaces = getCategory(categories, "Interfaces");
		
		if(interfaces.objects.size() > 0) {
			
			build = "implements ";
			
			for(int i = 0; i < interfaces.objects.size(); i++)
				build += interfaces.objects.get(i);
		}
		
		build += "{" + constructor;
		
		Category global = getCategory(categories, "Global");
		
		for(int i = 0; i < global.objects.size(); i++)
			build += global.objects.get(i) + buildBodyElementSeparator();
		
		Category functions = getCategory(categories, "Functions");
		
		for(int i = 0; i < functions.objects.size(); i++)
			build += functions.objects.get(i);
		
		build += "}";
		
		return build;
	}
	
	public String buildRun(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "try{Runtime.getRuntime().exec(" + buildConcatenate(element, arguments, meta, categories) + ");}catch(Exception e){}";
	}
	
	public String buildWait(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "try{Thread.sleep(((int)" + arguments.get(0) + ")*1000);}catch(Exception e){}";
	}
	
	public String buildSplit(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String body = "";
		
		for(int i = 0; i < arguments.size(); i++)
			body += arguments.get(i) + buildBodyElementSeparator();
		
		return "try{new Thread(new Runnable(){public void run(){" + body + "}}).start();}catch(Exception e){}";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "System.out.print(" + buildConcatenate(element, arguments, meta, categories) + ")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "System.out.println(" + buildConcatenate(element, arguments, meta, categories) + ")";
	}
	
	public String buildInput(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "new Scanner(System.in).nextLine()";
	}
	
	public String buildTime(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "(double) System.currentTimeMillis() / 1000";
	}
	
	// STUB - IO
	
	public String buildList(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		
		String build = "";
		
		if(size.equalsIgnoreCase("Variable"))
			build += "new ArrayList<Object>(Arrays.asList(";
		
		else
			build += "{";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += ",";
		}
		
		if(size.equalsIgnoreCase("Variable"))
			build += "))";
		
		else
			build += "}";
			
		return build;
	}
	
	public String buildSize(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		
		if(size.equalsIgnoreCase("Variable"))
			return arguments.get(0) + ".size()";
		
		else
			return arguments.get(0) + ".length";
	}
	
	public String buildAt(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		
		if(size.equalsIgnoreCase("Variable"))
			return arguments.get(0) + ".get((" + arguments.get(1) + ")-" + index + ")";
		
		else
			return arguments.get(0) + "[(" + arguments.get(1) + ")-" + index + "]";
	}
	
	public String buildAppend(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return arguments.get(0) + ".add(" + arguments.get(1) + ")";
	}
	
	public String buildSet(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		
		if(size.equalsIgnoreCase("Variable"))
			return arguments.get(0) + ".set((" + arguments.get(1) + ")-" + index + "," + arguments.get(2) + ")";
		
		else
			return arguments.get(0) + "[(" + arguments.get(1) + ")-" + index + "]=" + arguments.get(2);
	}
	
	public String buildInsert(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildRemove(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return arguments.get(0) + ".remove(" + arguments.get(1) + ")";
	}
	
	public String buildConcatenate(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String build = "\"\"+";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += "+";
		}
		
		return build;
	}
	
	public String buildCrop(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildContains(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildIndex(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildCount(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildCut(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildReverse(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildConvertSequence(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "";
	}
	
	public String buildCharacterToNumber(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildNumberToCharacter(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildUpper(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildLower(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildTrim(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "";
	}
	
	public String buildRandom(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.random()";
	}
	
	public String buildPower(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.pow(" + arguments.get(0) + "," + arguments.get(1) + ")";
	}
	
	public String buildSine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.sin(" + arguments.get(0) + ")";
	}
	
	public String buildCosine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.cos(" + arguments.get(0) + ")";
	}
	
	public String buildTangent(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.tan(" + arguments.get(0) + ")";
	}
	
	public String buildSquareRoot(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.sqrt(" + arguments.get(0) + ")";
	}
	
	public String buildNaturalLogarithm(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.log(" + arguments.get(0) + ")";
	}
	
	public String buildFloor(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.floor(" + arguments.get(0) + ")";
	}
	
	public String buildCeiling(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.ceil(" + arguments.get(0) + ")";
	}
	
	public String buildToRadians(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.toRadians(" + arguments.get(0) + ")";
	}
	
	public String buildToDegrees(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.toDegrees(" + arguments.get(0) + ")";
	}
	
	public String buildAbsoluteValue(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "Math.abs(" + arguments.get(0) + ")";
	}
	
	public String buildType(Element type) {
		
		String declaration = "";
		
		if(ElementUtilities.hasChild(type, "Public"))
			declaration += "public ";
		
		if(ElementUtilities.hasChild(type, "Private"))
			declaration += "private ";
		
		if(ElementUtilities.hasChild(type, "Protected"))
			declaration += "protected ";
		
		if(ElementUtilities.hasChild(type, "Package"))
			declaration += "package ";
		
		if(ElementUtilities.hasChild(type, "Static"))
			declaration += "static ";
		
		if(ElementUtilities.hasChild(type, "Final"))
			declaration += "final ";
		
		if(ElementUtilities.hasChild(type, "Void"))
			declaration += "void ";
		
		else if(ElementUtilities.hasChild(type, "Class"))
			declaration += ElementUtilities.getChild(type, "Class").children.get(0).content + " ";
		
		else if(ElementUtilities.hasChild(type, "Integer"))
			declaration += "int ";
		
		else if(ElementUtilities.hasChild(type, "Double"))
			declaration += "double ";
		
		else if(ElementUtilities.hasChild(type, "Float"))
			declaration += "float ";
		
		else if(ElementUtilities.hasChild(type, "Character"))
			declaration += "char ";
		
		else if(ElementUtilities.hasChild(type, "Boolean"))
			declaration += "boolean ";
		
		else if(ElementUtilities.hasChild(type, "Byte"))
			declaration += "byte ";
		
		else if(ElementUtilities.hasChild(type, "Short"))
			declaration += "short ";
		
		else if(ElementUtilities.hasChild(type, "Long"))
			declaration += "Long ";
		
		else
			declaration += "Object ";
		
		if(ElementUtilities.hasChild(type, "Template"))
			declaration += "<" + buildType(ElementUtilities.getChild(type, "Template")) + "> ";
		
		if(ElementUtilities.hasChild(type, "Array")) {
			
			int dimensions = 1;
			
			if(ElementUtilities.getChild(type, "Array").children.size() > 0)
				dimensions = Integer.parseInt(ElementUtilities.getChild(type, "Array").children.get(0).content);
			
			for(int i = 0; i < dimensions; i++)
				declaration += "[] ";
		}
		
		return declaration;
	}
}