package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one_plus.directive.Directive;

public class Arguments extends Directive {
	
	public void apply(
			ArrayList<Directive> directiveUnits,
			ArrayList<Element> directives,
			Element element) {
		
		if(element.content.equalsIgnoreCase("ARGUMENTS")) {
			
		}
	}
}