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

			Element define = ElementUtilities.copyElement(element.children.get(0));
			
			for(int i = 1; i < element.children.size(); i++)
				ElementUtilities.addChild(define, ElementUtilities.copyElement(element.children.get(i)));
			
			definitions.add(define);
		}
	}
}