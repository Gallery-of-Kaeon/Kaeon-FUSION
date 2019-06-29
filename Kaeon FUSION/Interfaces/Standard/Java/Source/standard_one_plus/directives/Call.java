package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;
import standard_one_plus.utilities.DirectiveUtilities;

public class Call extends DirectiveUnit {
	
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		Element element = directive.directive;
		
		if(element.content.equalsIgnoreCase("CALL")) {
			
			Element call =
					DirectiveUtilities.getDefinition(
							directiveUnits,
							element.children.get(0).content);
			
			if(call != null) {
				
				applyArguments(directives, call, element.children.get(0).children);
				
				int start = 0;
				int end = call.children.size() - 1;
				
				if(element.children.size() == 2) {
					start = Integer.parseInt(element.children.get(1).content) - 1;
					end = start;
				}
				
				if(element.children.size() == 3) {
					start = Integer.parseInt(element.children.get(1).content) - 1;
					end = Integer.parseInt(element.children.get(2).content) - 1;
				}
				
				for(int i = start; i <= end; i++) {
					
					ElementUtilities.addChild(
							element.parent,
							call.children.get(i),
							ElementUtilities.getIndex(element) + (i - start) + 1);
				}
			}
		}
	}
	
	public void applyArguments(
			ArrayList<Directive> directives,
			Element element,
			ArrayList<Element> arguments) {
		
		for(int i = 0; i < element.children.size(); i++) {
			
			if(DirectiveUtilities.isDirective(directives, element.children.get(i))) {
				
				if(element.children.get(i).content.equalsIgnoreCase("ARGUMENTS")) {
					
					ElementUtilities.addChild(
							element,
							ElementUtilities.copyElement(
									arguments.get(
											Integer.parseInt(
													element.children.remove(i).children.get(0).content) - 1)));
				}
				
				continue;
			}
			
			applyArguments(directives, element.children.get(i), arguments);
		}
	}
}