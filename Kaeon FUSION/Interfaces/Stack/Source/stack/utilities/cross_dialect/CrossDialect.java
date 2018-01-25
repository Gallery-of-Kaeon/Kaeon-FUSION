package stack.utilities.cross_dialect;

import java.util.ArrayList;

import one.Element;
import stack.utilities.Dialect;

public class CrossDialect extends Dialect {
	
	public String build(
			ArrayList<ArrayList<Object>> functionDefintions,
			ArrayList<ArrayList<Object>> functions,
			ArrayList<String> arguments) {

		String name = "" + functions.get(0).get(0);
		Element main = (Element) functions.get(0).get(1);
		
		ArrayList<Category> categories = new ArrayList<Category>();
		String build = buildMain(name, main, categories);
		
		return buildCategories(name, main, build, categories);
	}
	
	public String buildMain(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return
				buildMainPrefix(name, main, categories) +
				buildMainBody(name, main, categories) +
				buildMainPostfix(name, main, categories);
	}
	
	public String buildMainPrefix(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildMainPostfix(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildMainBody(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		String body = "";
		
		for(int i = 0; i < main.children.size(); i++) {
			
			body +=
					buildElement(
							name,
							main,
							categories,
							new ArrayList<String>());
		}
		
		return body;
	}
	
	public String buildElement(
			String name,
			Element main,
			ArrayList<Category> categories,
			ArrayList<String> children) {
		
		return "";
	}
	
	public String buildCategories(
			String name,
			Element main,
			String build,
			ArrayList<Category> categories) {
		
		return build;
	}
	
	public String buildDefinition(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildDefinitionPrefix(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildDefinitionPostfix(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
	
	public String buildDefinitionBody(
			String name,
			Element main,
			ArrayList<Category> categories) {
		
		return "";
	}
}