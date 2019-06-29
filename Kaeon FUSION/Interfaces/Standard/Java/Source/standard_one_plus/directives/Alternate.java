package standard_one_plus.directives;

import java.util.ArrayList;

import aether_one_plus.Aether;
import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;

public class Alternate extends DirectiveUnit {
	
	public void apply(
			ArrayList<DirectiveUnit> directiveUnits,
			ArrayList<Directive> directives,
			Directive directive) {
		
		Element element = directive.directive;
		int index = ElementUtilities.getIndex(element);
		
		String syntax = directive.header.get(0).content;
		
		for(int i = 0; i < directive.body.size(); i++) {
			
			try {
				
				Element document =
						(Element)
						Aether.call(
								syntax,
								0,
								directive.body.get(i).content);
				
				for(int j = 0; j < document.children.size(); j++) {
					
					ElementUtilities.addChild(
							element.parent,
							document.children.get(i),
							index + i + 1);
				}
			}
			
			catch(Exception exception) {
				
			}
		}
	}
}