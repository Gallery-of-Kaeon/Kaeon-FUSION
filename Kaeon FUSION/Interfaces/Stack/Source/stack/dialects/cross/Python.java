package stack.dialects.cross;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import stack.utilities.cross_dialect.Category;
import stack.utilities.cross_dialect.CrossDialect;

public class Python extends CrossDialect {
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		file.add(formatIdentifier(name) + ".py");
		
		build = "arguments=sys.argv\nscope=False\n" + build;
		
		Category imports = getCategory(categories, "Imports");
		
		for(int i = 0; i < imports.objects.size(); i++)
			build = imports.objects.get(i) + build;
		
		Category functions = getCategory(categories, "Functions");
		
		for(int i = 0; i < functions.objects.size(); i++)
			build = functions.objects.get(i) + build;
		
		build = "import sys\n" + build;
		
		file.add(build);
		
		files.add(file);
	}
	
	public String buildBodyElementSeparator() {
		return "\n";
	}
	
	public boolean trickleDown(Element element, Element meta, ArrayList<Category> categories) {
		
		if(element.content.equalsIgnoreCase("Define"))
			return false;
		
		return true;
	}
	
	public String buildLiteral(Element element, ArrayList<String> arguments, Element meta) {
		
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
	
	public String buildVariableDeclaration(Element element, ArrayList<String> arguments, Element meta) {
		return buildVariableAssignment(element, arguments, meta);
	}
	
	public String buildFunctionCall(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = element.content + "(";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += ",";
		}
		
		return build + ")";
	}
	
	public String buildDefine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		Element metaCopy = ElementUtilities.copyElement(meta);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(element.children.get(i).content.equalsIgnoreCase("Meta")) {
				
				ammendMeta(metaCopy, element.children.get(i), categories);
				
				continue;
			}
			
			String definition = "Function";
			
			if(ElementUtilities.hasChild(metaCopy, "Definition"))
				definition = ElementUtilities.getChild(metaCopy, "Definition").children.get(0).content;
			
			if(definition.equalsIgnoreCase("Class")) {
				
				ArrayList<String> inheritence = new ArrayList<String>();
				
				for(int j = 0; j < ElementUtilities.getChild(metaCopy, "Definition").children.get(0).children.size(); j++)
					inheritence.add(ElementUtilities.getChild(metaCopy, "Definition").children.get(0).children.get(i).content);
				
				getCategory(categories, "Functions").objects.add(
						buildClassDefinition(
								element.children.get(i),
								metaCopy,
								categories,
								inheritence));
			}
			
			else {
				
				getCategory(categories, "Functions").objects.add(
						buildFunctionDefinition(
								element.children.get(i),
								metaCopy,
								categories));
			}
		}
		
		return null;
	}
	
	public String buildFunctionDefinition(Element function, Element metaCopy, ArrayList<Category> categories) {
		
		String build = "def " + function.content + "(";
		
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
		
		for(int j = 0; j < paramNum; j++) {
			
			build += "arg" + j;
			
			if(j < paramNum - 1)
				build += ",";
		}
		
		build += "):\n\tscope=False\n\targuments=[";
		
		for(int j = 0; j < paramNum; j++) {
			
			build += "arg" + j;
			
			if(j < paramNum - 1)
				build += ",";
		}
		
		build += "]\n";
		
		for(int j = 0; j < function.children.size(); j++) {
			
			build +=
					"\t" +
					buildElement(
							"",
							function.children.get(j),
							categories,
							new ArrayList<String>(),
							new Element(),
							1) +
					"\n";
		}
		
		return build;
	}
	
	public String buildClassDefinition(Element function, Element metaCopy, ArrayList<Category> categories, ArrayList<String> inheritence) {
		
		String build = "class " + function.content + "(";
		
		for(int i = 0; i < inheritence.size(); i++) {
			
			build += inheritence.get(i);
			
			if(i < inheritence.size() - 1)
				build += ',';
		}
		
		build += "):\n\tdef __init__(self";
		
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
		
		for(int j = 0; j < paramNum; j++)
			build += "," + "arg" + j;
		
		build += "):\n\t\tscope=False\n\t\targuments=[";
		
		for(int j = 0; j < paramNum; j++) {
			
			build += "arg" + j;
			
			if(j < paramNum - 1)
				build += ",";
		}
		
		build += "]\n";
		
		ArrayList<Category> newCategories = new ArrayList<Category>(categories);
		
		for(int i = 0; i < newCategories.size(); i++) {
			
			if(newCategories.get(i).name.equalsIgnoreCase("Functions") ||
					newCategories.get(i).name.equalsIgnoreCase("Function Names")) {
			
				newCategories.remove(i);
				
				i--;
			}
		}
		
		Category functions = new Category();
		
		functions.name = "Functions";
		
		newCategories.add(functions);
		
		Category functionNames = new Category();
		
		functionNames.name = "Function Names";
		functionNames.objects = new ArrayList<Object>(getCategory(categories, "Function Names").objects);
		
		newCategories.add(functionNames);
		
		String init = "";
		
		for(int j = 0; j < function.children.size(); j++) {
			
			String buildElement =
					buildElement(
							"",
							function.children.get(j),
							newCategories,
							new ArrayList<String>(),
							new Element(),
							0);
			
			if(!function.children.get(j).content.equalsIgnoreCase("Define"))
				init += buildElement + "\n";
		}
		
		build += indentBody(init, 2);
		
		ArrayList<Object> methods = getCategory(newCategories, "Functions").objects;
		
		for(int i = 0; i < methods.size(); i++) {
			
			String method = "" + methods.get(i);
			
			method =
					indentBody(
							method.substring(0, method.indexOf('(') + 1) +
									(method.indexOf(')') != method.indexOf('(') + 1 ? "self," : "self") +
									method.substring(method.indexOf('(') + 1),
							1);
			
			build += method + '\n';
		}
		
		return build;
	}
	
	public String buildArguments(Element element, ArrayList<String> arguments, Element meta) {
		return "arguments";
	}
	
	public String buildImport(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		getCategory(categories, "Imports").objects.add("import " + element.children.get(0).content + "\n");
		
		return null;
	}
	
	public String buildThis(Element element, ArrayList<String> arguments, Element meta) {
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
	
	public String buildObjectOperation(String operator, String operation, Element meta) {
		
		if(operation.indexOf("return ") == 0)
			return operator + '.' + operation.substring(7);
		
		return operator + '.' + operation;
	}
	
	public String buildNull(Element element, ArrayList<String> arguments, Element meta) {
		return "null";
	}
	
	public String buildReturn(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "return ";
		
		return build + arguments.get(0);
	}
	
	public String indent(int indent) {
		
		String build = "";
		
		for(int i = 0; i < indent; i++)
			build += "\t";
		
		return build;
	}
	
	public String buildScope(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		String build = "while True:\n";
		
		for(int i = 0; i < arguments.size(); i++)
			build += indent(nest) + arguments.get(i) + '\n';
		
		return build + indent(nest) + "break\n";
	}
	
	public String buildBreak(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		return
				"if(" +
				(arguments.size() > 0 ? arguments.get(0) : "True") +
				"):\n" +
				indent(nest) +
				"scope=True\n" +
				indent(nest) +
				"break";
	}
	
	public String buildElse(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		String build = "if scope:\n" + indent(nest) + "scope=False\n" + indent(nest) + "while True:\n";
		
		for(int i = 0; i < arguments.size(); i++)
			build += indent(nest + 1) + arguments.get(i) + '\n';
		
		return build + "\n" + indent(nest + 1) + "break\n";
	}
	
	public String buildLoop(Element element, ArrayList<String> arguments, Element meta, int nest) {
		
		return
				"if(" +
				(arguments.size() > 0 ? arguments.get(0) : "True") +
				"):\n" +
				indent(nest + 1) +
				"continue";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "print(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + ",";
		
		return build + "end=\"\")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "print(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + (i < arguments.size() - 1 ? "," : "");
		
		return build + ")";
	}
	
	public String buildInput(Element element, ArrayList<String> arguments, Element meta) {
		return "input(str(" + arguments.get(0) + "))";
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
	
	public String buildSize(Element element, ArrayList<String> arguments, Element meta) {
		return "len(" + arguments.get(0) + ")";
	}
	
	public String buildAppend(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + ".append(" + arguments.get(1) + ")";
	}
	
	public String buildSet(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + "[(" + arguments.get(1) + ")-1]=" + arguments.get(2);
	}
	
	public String buildInsert(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + ".insert(" + arguments.get(1) + ",(" + arguments.get(2) + ")-1)";
	}
	
	public String buildRemove(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + ".remove((" + arguments.get(1) + ")-1)";
	}
	
	public String buildConcatenate(Element element, ArrayList<String> arguments, Element meta) {
		
		String build = "\"\"+";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += "str(" + arguments.get(i) + ")";
			
			if(i < arguments.size() - 1)
				build += "+";
		}
		
		return build;
	}
	
	public String buildNot(Element element, ArrayList<String> arguments, Element meta) {
		return "not (" + arguments.get(0) + ")";
	}
	
	public String buildOr(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, " or ");
	}
	
	public String buildExclusiveOr(Element element, ArrayList<String> arguments, Element meta) {
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