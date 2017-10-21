package one_plus.read.directives;

import java.util.ArrayList;

import aether_ONE_Plus.Aether;
import one.Element;
import one_plus.read.ONEPlusElement;

public class ONEPlusDirectiveFormatter {
	
	@SuppressWarnings("unchecked")
	public static void formatDirectives(ONEPlusElement element) {
		
		ArrayList<Directive> directives = new ArrayList<Directive>();
		
		for(int i = 0; i < element.children.size(); i++) {
			
			String definition =
					((ONEPlusElement) element.getElement(i)).getDefinition();
			
			if(definition.equals("DIRECTIVE")) {
				
				ONEPlusElement directiveElement = (ONEPlusElement) element.children.get(i);
				String content = directiveElement.content;
				
				if(content.equalsIgnoreCase("USE")) {
					
					for(Element child : directiveElement.children) {
						
						ArrayList<Object> module = null;
						
						try {
							module = (ArrayList<Object>) Aether.call(child.content, 0, null);
						}
						
						catch(Exception exception) {
							
						}
						
						if(module == null)
							module = new ArrayList<Object>();
						
						for(Object object : module) {
							
							if(object instanceof Directive)
								directives.add((Directive) object);
						}
					}
				}
				
				else {
					
					for(Directive directive : directives)
						directive.apply(directiveElement);
				}
				
				element.children.remove(i);
				i--;
			}
			
			else
				formatDirectives(((ONEPlusElement) element.getElement(i)));
		}
	}
}