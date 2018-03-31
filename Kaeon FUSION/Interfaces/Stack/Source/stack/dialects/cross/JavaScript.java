package stack.dialects.cross;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import stack.utilities.cross_dialect.Category;
import stack.utilities.cross_dialect.CrossDialect;

public class JavaScript extends CrossDialect {
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		file.add(formatIdentifier(name) + ".js");
		
		build = "var scope=false;" + build;
		
		file.add(build);
		
		files.add(file);
	}
	
	public String buildVariableDeclarationType(Element element, ArrayList<String> arguments, Element meta) {
		return "var";
	}
	
	public String buildFunctionDefinition(Element function, String functionBody, Element metaCopy, ArrayList<Category> categories, boolean isConstructor) {
		
		Element parameters = ElementUtilities.getChild(metaCopy, "Parameters");
		int paramNum = 0;
		
		if(parameters != null) {
			
			try {
				paramNum = Integer.parseInt(parameters.children.get(0).content);
			}
			
			catch(Exception exception) {
				paramNum = 0;
			}
		}
		
		else
			paramNum = 0;
		
		String build = "function " + function.content + "(";
		
		for(int j = 0; j < paramNum; j++) {
			
			build += "arg" + j;
			
			if(j < paramNum - 1)
				build += ",";
		}
		
		build += "){scope=false;arguments=[";
		
		for(int j = 0; j < paramNum; j++) {
			
			build += "arg" + j;
			
			if(j < paramNum - 1)
				build += ",";
		}
		
		return build + "];" + functionBody + "}";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "console.log(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + ",";
		
		return build + ")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "console.log(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + (i < arguments.size() - 1 ? "," : "");
		
		return build + ")";
	}
	
	public String buildList(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "[";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += ",";
		}
		
		return build + "]";
	}
}