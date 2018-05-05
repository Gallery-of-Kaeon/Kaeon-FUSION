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
	
	public String buildVariableDeclarationType(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		if(ElementUtilities.hasChild(meta, "Type")) {
			
			ArrayList<String> type = new ArrayList<String>();
			Element typeElement = ElementUtilities.getChild(meta, "Type");
			
			for(int i = 0; i < typeElement.children.size(); i++)
				type.add(typeElement.children.get(i).content);
			
			return buildType(type);
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
			int parameterNumber) {
		
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
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "printf(" + arguments.get(0) + ")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "printf(" + arguments.get(0) + ")";
	}
	
	public String buildType(ArrayList<String> type) {
		
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
		
		if(lower.contains("pointer"))
			build += "* ";
		
		if(lower.contains("address"))
			build += "& ";
		
		if(lower.contains("array"))
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