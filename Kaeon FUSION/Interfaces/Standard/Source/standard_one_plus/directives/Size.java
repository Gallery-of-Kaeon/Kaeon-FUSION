package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import standard_one_plus.utilities.DirectiveUtilities;

public class Size extends Directive {
	
	public void apply(
			ArrayList<Directive> directiveUnits,
			ArrayList<Element> directives,
			Element element) {
		
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