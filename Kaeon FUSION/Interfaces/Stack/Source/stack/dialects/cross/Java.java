package stack.dialects.cross;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import stack.utilities.cross_dialect.Category;
import stack.utilities.cross_dialect.CrossDialect;

public class Java extends CrossDialect {
	
	public String buildBody(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "boolean scope=false;" + super.buildBody(name, main, categories);
	}
	
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
				"{public static void main(String[]args){" +
				body +
				"}}";
	}
	
	public String buildBodyElementSeparator() {
		return ";";
	}
	
	public String buildVariableDeclaration(Element element, ArrayList<String> arguments, Element meta) {
		
		String declaration = "";
		
		Element type = ElementUtilities.getChild(meta, "Type");
		
		for(int i = 0; i < type.children.size(); i++) {
			
		}
		
		return declaration + " " + element.content + "=" + arguments.get(0);
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
		
		return build + "}while(false);";
	}
	
	public String buildBreak(Element element, ArrayList<String> arguments, Element meta, int nest) {
		return "if(" + (arguments.size() > 0 ? arguments.get(0) : "true") + "){scope=true;break;}";
	}
	
	public String buildElse(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		String build = "if(scope) do{scope=false;";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + ';';
		
		return build + "}while(false);";
	}
	
	public String buildLoop(Element element, ArrayList<String> arguments, Element meta, int nest) {
		return "if(" + (arguments.size() > 0 ? arguments.get(0) : "true") + ")continue;";
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
	
	public String buildConcatenate(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "\"\"+";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += "+";
		}
		
		return build;
	}
}