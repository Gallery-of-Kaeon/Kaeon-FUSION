package stack.dialects.cross;

import java.util.ArrayList;

import build_dialect.cross_dialect.Category;
import build_dialect.cross_dialect.CrossDialect;
import one.Element;

public class JavaScript extends CrossDialect {
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories,
			boolean utility,
			boolean snippet) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		file.add(formatIdentifier(name) + ".js");
		
		if(!utility)
			build = (!snippet ? "var scope=false;" : "") + build;
		
		if(!snippet) {
			
			Category functions = getCategory(categories, "Functions");
			
			for(int i = 0; i < functions.objects.size(); i++)
				build = functions.objects.get(i) + build;
		}
		
		file.add(build);
		
		files.add(file);
	}
	
	public String buildVariableDeclarationType(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "var";
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
		
		String build = (isConstructor ? "constructor " : "function ") + (aliased ? function.content : "") + "(";
		
		for(int i = 0; i < parameterNumber; i++) {
			
			build += "arg" + i;
			
			if(i < parameterNumber - 1)
				build += ",";
		}
		
		for(int i = 0; i < parameters.size(); i++) {
			
			build += parameters.get(i).name;
			
			if(i < parameters.size() - 1)
				build += ",";
		}
		
		build += "){scope=false;arguments=[";
		
		for(int i = 0; i < parameterNumber; i++) {
			
			build += "arg" + i;
			
			if(i < parameterNumber - 1)
				build += ",";
		}
		
		return build + "];" + functionBody + "}";
	}
	
	public String buildClassDefinition(Element classElement, String constructor, Element metaCopy, ArrayList<Category> categories, ArrayList<String> inheritence) {
		
		String build =
				"class " +
				classElement.content +
				(inheritence.size() > 0 ?
						" extends " + inheritence.get(0) :
						"") +
				"{";
		
		Category global = getCategory(categories, "Global");
		
		for(int i = 0; i < global.objects.size(); i++)
			build += "" + global.objects.get(i) + buildBodyElementSeparator();
		
		build += constructor;
		
		Category functions = getCategory(categories, "Functions");
		
		for(int i = 0; i < functions.objects.size(); i++)
			build += "" + functions.objects.get(i);
		
		return build + "}";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String build = "console.log(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + ",";
		
		return build + ")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String build = "console.log(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + (i < arguments.size() - 1 ? "," : "");
		
		return build + ")";
	}
	
	public String buildList(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		
		String build = "[";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += ",";
		}
		
		return build + "]";
	}
}