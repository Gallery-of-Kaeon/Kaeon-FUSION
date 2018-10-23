package one_plus.directive;

import java.util.ArrayList;

import aether_one_plus.Aether;
import one.Element;

public class Use extends DirectiveUnit {
	
	@SuppressWarnings("unchecked")
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		if(!directive.directive.content.equalsIgnoreCase("USE"))
			return;
			
		for(Element child : directive.directive.children) {
				
			try {
				directiveUnits.addAll((ArrayList<DirectiveUnit>) Aether.call(child.content, 0, null));
			}
			
			catch(Exception exception) {
				
			}
		}
	}
}