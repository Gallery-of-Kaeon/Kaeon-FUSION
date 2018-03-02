package standard_one_plus.directives;

import java.util.ArrayList;

import one.Element;
import one.ElementUtilities;
import one_plus.directive.Directive;
import standard_one_plus.utilities.DirectiveUtilities;

public class For extends Directive {
	
	public void apply(
			ArrayList<Directive> directiveUnits,
			ArrayList<Element> directives,
			Element element) {
		
		if(element.content.equalsIgnoreCase("FOR")) {
			
			ArrayList<Element> body =
					new ArrayList<Element>(element.children);
			
			int start = 0;
			int end = 0;
			
			if(isHeader(directives, element, 0)) {
				
				body.remove(0);
				
				start =
						Integer.parseInt(
								element.children.get(0).content);
				
				if(isHeader(directives, element, 1)) {

					body.remove(0);
					
					end =
							Integer.parseInt(
									element.children.get(1).content);
				}
				
				else {
					end = start;
					start = 1;
				}
			}
			
			else
				return;
			
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
	
	public boolean isHeader(
			ArrayList<Element> directives,
			Element element,
			int index) {
		
		if(element.children.size() <= index)
			return false;
		
		Element header = element.children.get(index);
		
		if(DirectiveUtilities.isDirective(directives, header)) {
			
			try {
				
				Integer.parseInt(header.content);
				
				return true;
			}
			
			catch(Exception exception) {
				return false;
			}
		}
		
		return false;
	}
	
	public ArrayList<Element> applyIndex(
			ArrayList<Element> directives,
			ArrayList<Element> elements,
			int index) {
		
		ArrayList<Element> apply = new ArrayList<Element>();
		
		for(int i = 0; i < elements.size(); i++) {
			
			Element copy = new Element();
			copy.content = elements.get(i).content;
			
			if(DirectiveUtilities.isDirective(directives, elements.get(i))) {
				
				if(elements.get(i).content.equalsIgnoreCase("INDEX"))
					copy.content = "" + index;
				
				else
					directives.add(copy);
			}
			
			ElementUtilities.addChildren(copy, applyIndex(directives, elements.get(i).children, index));
			
			apply.add(copy);
		}
		
		return apply;
	}
}