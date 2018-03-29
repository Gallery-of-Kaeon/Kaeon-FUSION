package stack.dialects.cross;

import java.util.ArrayList;

import one.Element;
import stack.utilities.cross_dialect.Category;
import stack.utilities.cross_dialect.CrossDialect;

public class C extends CrossDialect {
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		file.add(formatIdentifier(name) + ".c");
		file.add(build);
		
		files.add(file);
	}
	
	public String buildMain(
			String name,
			Element main,
			ArrayList<Category> categories,
			String body) {
		
		return
				"#include<stdio.h>\n#include<stdlib.h>\nint main(int argc, char* argv[]){bool scope=false;" +
				body +
				"return 0;}";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta) {
		return "printf(" + arguments.get(0) + ")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta) {
		return "printf(" + arguments.get(0) + ")";
	}
}