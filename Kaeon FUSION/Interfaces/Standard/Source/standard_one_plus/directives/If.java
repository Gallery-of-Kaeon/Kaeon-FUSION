package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;

public class If extends DirectiveUnit {
	
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		Element element = directive.directive;
		
		if(element.content.equalsIgnoreCase("IF")) {
			
			for(int i = 0; i < directive.header.size() - 1; i++) {
				
				if(!ElementUtilities.elementsEqual(directive.header.get(i), directive.header.get(i + 1)))
					return;
			}
			
			int index = ElementUtilities.getIndex(element);
			
			for(int i = 0; i < directive.body.size(); i++) {
				
				ElementUtilities.addChild(
						element.parent,
						directive.body.get(i),
						index + i + 1);
			}
		}
	}
}