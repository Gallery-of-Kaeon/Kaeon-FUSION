package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;

public class Properties extends DirectiveUnit {
	
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		Element element = directive.directive;
		
		if(element.content.equalsIgnoreCase("PROPERTIES")) {
			
		}
	}
}