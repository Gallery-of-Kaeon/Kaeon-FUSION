package one_plus.directive;

import java.util.ArrayList;

import aether_one_plus.Aether;
import one.Element;

public class Use extends Directive {
	
	@SuppressWarnings("unchecked")
	public void apply(
			ArrayList<Directive> directiveUnits,
			ArrayList<Element> directives,
			Element directive) {
		
		if(!directive.content.equalsIgnoreCase("USE"))
			return;
			
		for(Element child : directive.children) {
				
			try {
				directiveUnits.addAll((ArrayList<Directive>) Aether.call(child.content, 0, null));
			}
			
			catch(Exception exception) {
				
			}
		}
	}
}