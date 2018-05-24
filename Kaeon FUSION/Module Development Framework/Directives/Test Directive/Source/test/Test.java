package test;

import java.util.ArrayList;

import one.Element;
import one_plus.directive.DirectiveUnit;
import one_plus.directive.Directive;

public class Test extends DirectiveUnit {
	
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		if(directive.directive.content.equalsIgnoreCase("TEST"))
			apply(directives, directive.directive.parent);
	}
	
	public void apply(ArrayList<Directive> directives, Element element) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
			Element child = element.children.get(i);
			
			if(!isDirective(directives, child))
				child.content = "TEST - " + child.content;
			
			apply(directives, child);
		}
	}
	
	public boolean isDirective(ArrayList<Directive> directives, Element element) {
		
		for(int i = 0; i < directives.size(); i++) {
			
			if(directives.get(i).directive == element)
				return true;
		}
		
		return false;
	}
}