package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;

public class Define extends Directive {
	
	public ArrayList<Element> definitions = new ArrayList<Element>();
	
	public void apply(
			ArrayList<Directive> directiveUnits,
			ArrayList<Element> directives,
			Element element) {
		
		if(element.content.equalsIgnoreCase("DEFINE")) {
			
			Element definition = ElementUtilities.copyElement(element);
			
			definition.content = definition.children.get(0).content;
			definition.children.remove(0);
			
			definitions.add(definition);
		}
	}
}