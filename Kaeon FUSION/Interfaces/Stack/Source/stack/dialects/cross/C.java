package stack.dialects.cross;

import java.util.ArrayList;

import build_dialect.cross_dialect.Category;
import build_dialect.cross_dialect.CrossDialect;
import one.Element;
import one.ElementUtilities;

public class C extends CrossDialect {
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories,
			boolean utility,
			boolean snippet) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		if(utility)
			build = "";
		
		if(!snippet) {
			
			Category functions = getCategory(categories, "Functions");
			
			for(int i = 0; i < functions.objects.size(); i++)
				build = build + functions.objects.get(i) + build;
			
			Category imports = getCategory(categories, "Imports");
			
			for(int i = 0; i < imports.objects.size(); i++)
				build = "#include <" + imports.objects.get(i) + ">\n" + build;
			
			build = "#include<stdio.h>\n#include<stdlib.h>\n" + build;
		}
		
		file.add(formatIdentifier(name) + ".c");
		file.add(build);
		
		files.add(file);
	}
	
	public String buildMain(
			String name,
			Element main,
			ArrayList<Category> categories,
			String body,
			boolean utility,
			boolean snippet) {
		
		return
				(!snippet ? "int main(int argc, char* arguments[]){bool scope=false;" : "") +
				body +
				(!snippet ? "return 0;}" : "");
	}
	
	public String buildElement(
			String name,
			Element element,
			ArrayList<Category> categories,
			ArrayList<String> variables,
			Element meta,
			int nest) {
		
		String build = super.buildElement(name, element, categories, variables, meta, nest);
		
		if(element.content.equalsIgnoreCase("Meta"))
			return null;
		
		if(ElementUtilities.hasChild(meta, "Notation")) {
			
			Element notation = ElementUtilities.getChild(meta, "Notation");
			
			if(ElementUtilities.hasChild(notation, "Address")) {
				
				build = "&(" + build + ")";
				
				ElementUtilities.removeChild(notation, "Address");
			}
			
			if(ElementUtilities.hasChild(notation, "Pointer")) {
				
				build = "*(" + build + ")";
				
				ElementUtilities.removeChild(notation, "Pointer");
			}
		}
		
		return build;
	}
	
	public String buildVariableDeclaration(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String type =
				buildVariableDeclarationType(
						element,
						arguments,
						meta,
						categories);
		
		String variable =
				type +
				(type.length() > 0 ? " " : "") +
				buildVariableAssignment(
						element,
						arguments,
						meta,
						categories);
		
		String array = "";
		
		if(ElementUtilities.hasChild(meta, "Type")) {
			
			Element typeElement = ElementUtilities.getChild(meta, "Type");
			
			if(ElementUtilities.hasChild(typeElement, "array")) {
				
				Element arrayElement = ElementUtilities.getChild(typeElement, "array");
				
				for(int i = 0; i < arrayElement.children.size(); i++) {
					
					if(arrayElement.children.get(i).content.equalsIgnoreCase("null"))
						array += "[]";
					
					else
						array += "[" + Integer.parseInt(arrayElement.children.get(i).content) + "]";
				}
			}
		}
		
		return variable + array;
	}
	
	public String buildVariableDeclarationType(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		if(ElementUtilities.hasChild(meta, "Type")) {
			
			ArrayList<String> type = new ArrayList<String>();
			Element typeElement = ElementUtilities.getChild(meta, "Type");
			
			for(int i = 0; i < typeElement.children.size(); i++)
				type.add(typeElement.children.get(i).content);
			
			String struct = "";
			
			if(ElementUtilities.hasChild(typeElement, "structure"))
				struct = ElementUtilities.getChild(typeElement, "structure").children.get(0).content;
			
			return buildType(type, struct);
		}
		
		return "void*";
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
				buildType(returnType) +
				" " +
				function.content +
				"(";
		
		for(int i = 0; i < parameterNumber; i++) {
			
			build += "void* arg" + i;
			
			if(i < parameterNumber - 1)
				build += ",";
		}
		
		for(int i = 0; i < parameters.size(); i++) {
			
			build += buildParameterType(parameters.get(i)) + " " + parameters.get(i).name;
			
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
	
	public String buildParameterType(Category parameter) {
		
		ArrayList<String> type = new ArrayList<String>();
		
		for(int i = 0; i < parameter.objects.size(); i++)
			type.add("" + parameter.objects.get(i));
		
		return buildType(type);
	}
	
	public String buildClassDefinition(Element classElement, String constructor, Element metaCopy, ArrayList<Category> categories, ArrayList<String> inheritence) {
		
		boolean aliased = true;
		boolean union = false;
		
		String build =
				(!union ? "struct " : "union ") +
				(aliased ? classElement.content : "") +
				"{";
		
		Category global = getCategory(categories, "Global");
		
		for(int i = 0; i < global.objects.size(); i++)
			build += "" + global.objects.get(i) + buildBodyElementSeparator();
		
		Category functions = getCategory(categories, "Functions");
		
		for(int i = 0; i < functions.objects.size(); i++) {
			
			String function = "" + functions.objects.get(i);
			function = "&" + function.substring(0, function.indexOf("(")).trim();
			
			build += function + buildBodyElementSeparator();
		}
		
		return build + "}";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "printf(" + arguments.get(0) + ")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "printf(" + arguments.get(0) + ")";
	}
	
	public String buildList(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		
		String build = "{";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += ",";
		}
		
		return build + "}";
	}
	
	public String buildType(ArrayList<String> type) {
		return buildType(type, "");
	}
	
	public String buildType(ArrayList<String> type, String struct) {
		
		String build = "";
		
		if(type.size() == 0)
			return "void*";
		
		ArrayList<String> lower = getLowerCaseList(type);
		
		if(lower.contains("external"))
			build += "extern ";
		
		if(lower.contains("automatic"))
			build += "auto ";
		
		if(lower.contains("unsigned"))
			build += "unsigned ";
		
		if(lower.contains("signed"))
			build += "signed ";
		
		if(lower.contains("integer"))
			build += "int ";
		
		if(lower.contains("short"))
			build += "short ";
		
		if(lower.contains("long"))
			build += "long ";
		
		if(lower.contains("float"))
			build += "float ";
		
		if(lower.contains("double"))
			build += "double ";
		
		if(lower.contains("character"))
			build += "char ";
		
		if(lower.contains("boolean"))
			build += "bool ";
		
		if(lower.contains("void"))
			build += "void ";
		
		if(struct.length() > 0)
			build += struct;
		
		if(lower.contains("pointer"))
			build += "* ";
		
		if(lower.contains("address"))
			build += "& ";
		
		return build;
	}
	
	public ArrayList<String> getLowerCaseList(ArrayList<String> list) {
		
		ArrayList<String> lower = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++)
			lower.add(list.get(i).toLowerCase());
		
		return lower;
	}
}