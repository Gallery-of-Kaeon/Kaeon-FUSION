package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;
import standard_one_plus.utilities.DirectiveUtilities;

public class For extends DirectiveUnit {
	
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		Element element = directive.directive;
		
		if(element.content.equalsIgnoreCase("FOR")) {
			
			ArrayList<Element> body = directive.body;
			
			if(directive.header.size() == 0)
				return;
			
			int start = 0;
			int end = 0;
			
			if(directive.header.size() == 1) {
				start = 0;
				end = Integer.parseInt(directive.header.get(0).content) - 1;
			}
			
			if(directive.header.size() == 2) {
				start = Integer.parseInt(directive.header.get(0).content) - 1;
				end = Integer.parseInt(directive.header.get(1).content) - 1;
			}
			
			int index = ElementUtilities.getIndex(element);
			
			for(int i = start; i <= end; i++) {
				
				ArrayList<Element> apply = applyIndex(directives, body, i);
				
				for(int j = 0; j < apply.size(); j++) {
					
					ElementUtilities.addChild(
							element.parent,
							apply.get(j),
							index + (body.size() * (i - start)) + j + 1);
				}
			}
		}
	}
	
	public ArrayList<Element> applyIndex(
			ArrayList<Directive> directives,
			ArrayList<Element> elements,
			int index) {
		
		ArrayList<Element> apply = new ArrayList<Element>();
		
		for(int i = 0; i < elements.size(); i++) {
			
			Element copy = new Element();
			copy.content = elements.get(i).content;
			
			if(DirectiveUtilities.isDirective(directives, elements.get(i))) {
				
				if(elements.get(i).content.equalsIgnoreCase("INDEX"))
					copy.content = "" + index;
				
				else {
					
					Directive directive = new Directive();
					directive.directive = copy;
					
					directives.add(directive);
				}
			}
			
			ElementUtilities.addChildren(copy, applyIndex(directives, elements.get(i).children, index));
			
			apply.add(copy);
		}
		
		return apply;
	}
}