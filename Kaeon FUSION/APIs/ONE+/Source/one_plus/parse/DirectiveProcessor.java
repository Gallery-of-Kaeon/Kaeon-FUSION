package one_plus.parse;

import java.util.ArrayList;

import one.Element;
import one_plus.directive.Directive;
import one_plus.directive.Use;

public class DirectiveProcessor {
	
	public static void processDirectives(
			Element element,
			ArrayList<Element> directives) {
		
		ArrayList<Directive> directiveUnits = new ArrayList<Directive>();
		directiveUnits.add(new Use());
		
		processDirectives(element, directives, directiveUnits);
		
		removeDirectives(element, directives);
	}
	
	public static void processDirectives(
			Element element,
			ArrayList<Element> directives,
			ArrayList<Directive> directiveUnits) {
		
		if(isDirective(element, directives)) {
			
			for(int i = 0; i < directiveUnits.size(); i++) {
				
				try {
					directiveUnits.get(i).apply(directiveUnits, directives, element);
				}
				
				catch(Exception exception) {
					
				}
			}
			
			return;
		}
		
		ArrayList<Directive> newUnits = new ArrayList<Directive>(directiveUnits);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			processDirectives(
					element.children.get(i),
					directives,
					newUnits);
		}
	}
	
	public static boolean isDirective(
			Element element,
			ArrayList<Element> directives) {
		
		for(Element directive : directives) {
			
			if(directive == element)
				return true;
		}
		
		return false;
	}
	
	public static void removeDirectives(
			Element element,
			ArrayList<Element> directives) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(isDirective(element.children.get(i), directives)) {
				element.children.remove(i);
				i--;
			}
			
			else
				removeDirectives(element.children.get(i), directives);
		}
	}
}