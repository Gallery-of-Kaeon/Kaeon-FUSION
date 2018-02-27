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
		return element.content + "=" + arguments.get(0);
	}
	
	public String buildVariableAssignment(Element element, ArrayList<String> arguments, Element meta) {
		return buildVariableDeclaration(element, arguments, meta);
	}
	
	public String buildVariableReference(Element element, ArrayList<String> arguments, Element meta) {
		return element.content;
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
				
				ammendMeta(metaCopy, element.children.get(i));
				
				continue;
			}
			
			Element function = element.children.get(i);
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
			
			getCategory(categories, "Functions").objects.add(build);
		}
		
		return null;
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
	
	public String buildAt(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + "[(" + arguments.get(1) + ")-1]";
	}
	
	public String buildAppend(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + ".append(" + arguments.get(1) + ")";
	}
	
	public String buildSet(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + "[" + arguments.get(1) + "]=" + arguments.get(2);
	}
	
	public String buildInsert(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + ".insert(" + arguments.get(1) + "," + arguments.get(2) + ")";
	}
	
	public String buildRemove(Element element, ArrayList<String> arguments, Element meta) {
		return arguments.get(0) + ".remove(" + arguments.get(1) + ")";
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
	
	public String buildInfix(Element element, ArrayList<String> arguments, Element meta, String infix) {
		
		String build = "(" + arguments.get(0);
		
		for(int i = 1; i < arguments.size(); i++)
			build += infix + arguments.get(i);
		
		return build + ")";
	}
	
	public String buildLogicInfix(Element element, ArrayList<String> arguments, Element meta, String infix) {
		
		String build = "(";
		
		for(int i = 0; i < arguments.size() - 1; i++) {
			
			build += "(" + arguments.get(i) + infix + arguments.get(i + 1) + ")";
			
			if(i < arguments.size() - 2)
				build += " and ";
		}
		
		return build + ")";
	}
	
	public String buildIs(Element element, ArrayList<String> arguments, Element meta) {
		return buildLogicInfix(element, arguments, meta, "==");
	}
	
	public String buildEqual(Element element, ArrayList<String> arguments, Element meta) {
		return buildIs(element, arguments, meta);
	}
	
	public String buildAnd(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, " and ");
	}
	
	public String buildOr(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, " or ");
	}
	
	public String buildExclusiveOr(Element element, ArrayList<String> arguments, Element meta) {
		return buildInfix(element, arguments, meta, " xor ");
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
}