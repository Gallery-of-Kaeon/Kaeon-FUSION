package standard_one_plus.utilities;

import java.util.ArrayList;
import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import one_plus.directive.DirectiveUnit;
import standard_one_plus.directives.Define;

public class DirectiveUtilities {
	
	public static boolean isDirective(ArrayList<Directive> directives, Element element) {
		
		for(int i = 0; i < directives.size(); i++) {
			
			if(directives.get(i).directive == element)
				return true;
		}
		
		return false;
	}
	
	public static Element getDefinition(
			ArrayList<DirectiveUnit> directiveUnits,
			String definition) {
		
		Define define = new Define();
		
		for(int i = 0; i < directiveUnits.size(); i++) {
			
			if(directiveUnits.get(i) instanceof Define) {
				
				define = (Define) directiveUnits.get(i);
				
				break;
			}
		}
		
		for(int i = 0; i < define.definitions.size(); i++) {
			
			if(definition.equalsIgnoreCase(define.definitions.get(i).content)) {
				
				Element element = ElementUtilities.copyElement(define.definitions.get(i));
				element.content = null;
				
				return element;
			}
		}
		
		return null;
	}
}