package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;

public class Define extends DirectiveUnit {
	
	public ArrayList<Element> definitions = new ArrayList<Element>();
			
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		Element element = directive.directive;
		
		if(element.content.equalsIgnoreCase("DEFINE")) {

			Element define = ElementUtilities.copyElement(element.children.get(0));
			
			for(int i = 1; i < element.children.size(); i++)
				ElementUtilities.addChild(define, ElementUtilities.copyElement(element.children.get(i)));
			
			definitions.add(define);
		}
	}
}