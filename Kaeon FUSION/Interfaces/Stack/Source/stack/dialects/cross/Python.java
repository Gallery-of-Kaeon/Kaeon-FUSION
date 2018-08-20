package stack.dialects.cross;

import java.util.ArrayList;

import build_dialect.cross_dialect.Category;
import build_dialect.cross_dialect.CrossDialect;
import one.Element;

public class Python extends CrossDialect {
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories,
			boolean utility,
			boolean snippet) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		file.add(formatIdentifier(name) + ".py");
		
		if(!utility)
			build = (!snippet ? "arguments=sys.argv\nscope=False\n" : "") + build;
		
		if(!snippet) {
			
			Category classes = getCategory(categories, "Classes");
			
			for(int i = 0; i < classes.objects.size(); i++)
				build = classes.objects.get(i) + build;
			
			Category functions = getCategory(categories, "Functions");
			
			for(int i = 0; i < functions.objects.size(); i++)
				build = functions.objects.get(i) + build;
			
			Category imports = getCategory(categories, "Imports");
			
			for(int i = 0; i < imports.objects.size(); i++)
				build = "import " + imports.objects.get(i) + "\n" + build;
			
			build = "import sys\n" + build;
		}
		
		file.add(build);
		
		files.add(file);
	}
	
	public String buildBodyElementSeparator() {
		return "\n";
	}
	
	public String buildLiteral(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		try {
			
			Double.parseDouble(element.content);
				
			return element.content;
		}
		
		catch(Exception exception) {
			
		}
		
		if(element.content.equalsIgnoreCase("true"))
			return "True";
		
		if(element.content.equalsIgnoreCase("false"))
			return "False";
		
		if(element.content.indexOf('\"') == 0 &&
				element.content.lastIndexOf('\"') == element.content.length() - 1) {

			if(element.content.length() > 1)
				return element.content;
			
			return "\"\"";
		}
		
		return "\"" + element.content + "\"";
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
		
		String build =
				"def " +
				(isConstructor ? "__init__" : function.content) +
				"(" +
				(isConstructor ? "self" + (parameterNumber > 0 ? "," : "") : "");
		
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
		
		build += "):\n\tscope=False\n\targuments=[";
		
		for(int i = 0; i < parameterNumber; i++) {
			
			build += "arg" + i;
			
			if(i < parameterNumber - 1)
				build += ",";
		}
		
		return build + "]\n" + indentBody(functionBody, 1);
	}
	
	public String buildClassDefinition(Element classElement, String constructor, Element metaCopy, ArrayList<Category> categories, ArrayList<String> inheritence) {
		
		String build = "class " + classElement.content + "(";
		
		for(int i = 0; i < inheritence.size(); i++) {
			
			build += inheritence.get(i);
			
			if(i < inheritence.size() - 1)
				build += ",";
		}
		
		build += "):\n" + indentBody(constructor, 1) + "\n";
		
		Category functions = getCategory(categories, "Functions");
		
		for(int i = 0; i < functions.objects.size(); i++)
			build += indentBody("" + functions.objects.get(i), 1) + "\n";
		
		return build;
	}
	
	public String buildThis(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "self";
	}
	
	public String buildNew(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String args = "";
		
		for(int i = 0; i < element.children.get(0).children.size(); i++) {
			
			String arg =
					buildElement(
							"",
							element.children.get(0).children.get(i),
							categories,
							new ArrayList<String>(),
							new Element(),
							0);
			
			if(arg != null) {
				
				if(args.length() > 0)
					args += ',';
				
				args += arg;
			}
		}
		
		return element.children.get(0).content + "(" + args + ")";
	}
	
	public String buildObjectOperation(String operator, String operation, Element meta, ArrayList<Category> categories) {
		
		if(operation.indexOf("return ") == 0)
			return operator + '.' + operation.substring(7);
		
		return operator + '.' + operation;
	}
	
	public String buildNull(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "null";
	}
	
	public String indent(int indent) {
		
		String build = "";
		
		for(int i = 0; i < indent; i++)
			build += "\t";
		
		return build;
	}
	
	public String buildScope(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, int nest) {
		
		String build = "while True:\n";
		
		for(int i = 0; i < arguments.size(); i++)
			build += indent(nest) + arguments.get(i) + '\n';
		
		return build + indent(nest) + "break\n";
	}
	
	public String buildBreak(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, int nest) {
		
		return
				"if(" +
				(arguments.size() > 0 ? arguments.get(0) : "True") +
				"):\n" +
				indent(nest) +
				"scope=True\n" +
				indent(nest) +
				"break";
	}
	
	public String buildElse(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, int nest) {
		
		String build = "if scope:\n" + indent(nest) + "scope=False\n" + indent(nest) + "while True:\n";
		
		for(int i = 0; i < arguments.size(); i++)
			build += indent(nest + 1) + arguments.get(i) + '\n';
		
		return build + "\n" + indent(nest + 1) + "break\n";
	}
	
	public String buildLoop(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, int nest) {
		
		return
				"if(" +
				(arguments.size() > 0 ? arguments.get(0) : "True") +
				"):\n" +
				indent(nest + 1) +
				"continue";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String build = "print(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + ",";
		
		return build + "end=\"\")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String build = "print(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + (i < arguments.size() - 1 ? "," : "");
		
		return build + ")";
	}
	
	public String buildInput(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "input(str(" + arguments.get(0) + "))";
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
	
	public String buildSize(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return "len(" + arguments.get(0) + ")";
	}
	
	public String buildAppend(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return arguments.get(0) + ".append(" + arguments.get(1) + ")";
	}
	
	public String buildInsert(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return arguments.get(0) + ".insert(" + arguments.get(1) + ",(" + arguments.get(2) + ")-" + index + ")";
	}
	
	public String buildRemove(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		return arguments.get(0) + ".remove((" + arguments.get(1) + ")-" + index + ")";
	}
	
	public String buildConcatenate(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		
		String build = "\"\"+";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += "str(" + arguments.get(i) + ")";
			
			if(i < arguments.size() - 1)
				build += "+";
		}
		
		return build;
	}
	
	public String buildNot(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "not (" + arguments.get(0) + ")";
	}
	
	public String buildOr(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, " or ");
	}
	
	public String buildExclusiveOr(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return buildInfix(element, arguments, meta, " xor ");
	}
	
	public String indentBody(String body, int indent) {
		
		String newBody = "";
		
		String[] lines = body.split("\n");
		
		String indentLine = "";
		
		for(int i = 0; i < indent; i++)
			indentLine += '\t';
		
		for(int i = 0; i < lines.length; i++)
			newBody += indentLine + lines[i] + '\n';
		
		return newBody;
	}
}