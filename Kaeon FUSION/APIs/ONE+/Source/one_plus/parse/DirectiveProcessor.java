package one_plus.parse;

import java.util.ArrayList;

import one.Element;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;
import one_plus.directive.Use;

public class DirectiveProcessor {
	
	public static void processDirectives(
			Element element,
			ArrayList<Directive> directives) {
		
		ArrayList<DirectiveUnit> directiveUnits = new ArrayList<DirectiveUnit>();
		directiveUnits.add(new Use());
		
		while(hasDirectives(element, directives))
			processDirectives(element, directiveUnits, directives);
	}
	
	public static void processDirectives(
			Element element,
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives) {
		
		if(isDirective(element, directives)) {
			
			for(int i = 0; i < element.children.size(); i++) {
				
				boolean inHeader = false;
				
				Directive directive = getDirective(element, directives);
				
				for(int j = 0; j < directive.header.size(); j++) {
					
					if(directive.header.get(j) == element.children.get(i)) {
						
						inHeader = true;
						
						break;
					}
				}
				
				if(inHeader) {
					
					processDirectives(
							element.children.get(i),
							directiveUnits,
							directives);
				}
			}
			
			for(int i = 0; i < directiveUnits.size(); i++) {
				
				try {
					
					directiveUnits.get(i).apply(
							directiveUnits,
							directives,
							getDirective(
									element,
									directives));
				}
				
				catch(Exception exception) {
					
				}
			}
			
			return;
		}
		
		ArrayList<DirectiveUnit> newUnits = new ArrayList<DirectiveUnit>(directiveUnits);
		
		for(int i = 0; i < element.children.size(); i++) {
			
			processDirectives(
					element.children.get(i),
					newUnits,
					directives);
			
			if(isDirective(element.children.get(i), directives)) {
				
				element.children.remove(i);
				
				i--;
			}
		}
	}
	
	public static boolean hasDirectives(
			Element element,
			ArrayList<Directive> directives) {
		
		if(isDirective(element, directives))
			return true;
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(hasDirectives(element.children.get(i), directives))
				return true;
		}
		
		return false;
	}
	
	public static boolean isDirective(
			Element element,
			ArrayList<Directive> directives) {
		
		for(Directive directive : directives) {
			
			if(directive.directive == element)
				return true;
		}
		
		return false;
	}
	
	public static Directive getDirective(
			Element element,
			ArrayList<Directive> directives) {
		
		for(Directive directive : directives) {
			
			if(directive.directive == element)
				return directive;
		}
		
		return null;
	}
}