package one_plus.read.directives;

import java.util.ArrayList;

import aether_one_plus.Aether;
import one.Element;
import one_plus.read.ONEPlusElement;

public class ONEPlusDirectiveFormatter {
	
	@SuppressWarnings("unchecked")
	public static void formatDirectives(
			ONEPlusElement element,
			ArrayList<Directive> directives,
			ArrayList<Element> directiveElements) {
		
		ArrayList<Directive> localDirectives = new ArrayList<Directive>();
		
		if(directives != null)
			localDirectives.addAll(directives);
		
		if(directiveElements == null)
			directiveElements = getDirectiveElements(element);
		
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
								localDirectives.add((Directive) object);
						}
					}
				}
			}
		}
		
		for(int i = 0; i < element.children.size(); i++) {
			
			ONEPlusElement directiveElement = (ONEPlusElement) element.children.get(i);
			
			String definition = directiveElement.getDefinition();
			String content = directiveElement.content;
			
			if(definition.equals("DIRECTIVE") && content.equalsIgnoreCase("USE")) {
				
				element.children.remove(i);
				i--;
				
				continue;
			}
			
			formatDirectives(directiveElement, localDirectives, directiveElements);
			
			if(definition.equals("DIRECTIVE")) {
				
				for(Directive directive : localDirectives)
					directive.apply(directiveElements, directiveElement);
				
				element.children.remove(i);
				i--;
			}
		}
	}
	
	public static ArrayList<Element> getDirectiveElements(ONEPlusElement element) {
		
		ArrayList<Element> directiveElements = new ArrayList<Element>();
		
		for(Element child : element.children) {
			
			String definition = ((ONEPlusElement) child).getDefinition();
			
			if(definition.equalsIgnoreCase("DIRECTIVE"))
				directiveElements.add(child);
		}
		
		for(Element child : element.children)
			directiveElements.addAll(getDirectiveElements((ONEPlusElement) child));
		
		return directiveElements;
	}
}