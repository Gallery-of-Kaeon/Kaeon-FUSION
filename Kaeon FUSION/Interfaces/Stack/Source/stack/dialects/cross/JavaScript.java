package stack.dialects.cross;

import java.util.ArrayList;

import one.Element;
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
	
	public String buildVariableDeclaration(Element element, ArrayList<String> arguments, Element meta) {
		return "var " + buildVariableAssignment(element, arguments, meta);
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