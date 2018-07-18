package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;
import standard_one_plus.utilities.DirectiveUtilities;

public class Size extends DirectiveUnit {
	
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		Element element = directive.directive;
		
		if(element.content.equalsIgnoreCase("SIZE")) {
			
			Element call = new Element();
			
			call.content =
					"" +
					DirectiveUtilities.getDefinition(
							directiveUnits,
							element.children.get(0).content).children.size();
			
			ElementUtilities.addChild(
					element.parent,
					call,
					ElementUtilities.getIndex(element) + 1);
		}
	}
}