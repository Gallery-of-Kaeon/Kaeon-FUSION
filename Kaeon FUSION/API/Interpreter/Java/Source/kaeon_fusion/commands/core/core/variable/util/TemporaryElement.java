package kaeon_fusion.commands.core.core.variable.util;

import one_plus.element.Element;
import one_plus.read.ONEPlusElement;

public class TemporaryElement extends ONEPlusElement {
	
	public TemporaryElement(Element element) {
		
		setContent(element.getContent());
		
		for(int i = 0; i < element.getNumElements(); i++)
			addElement(copyElement(element.getElement(i)));
	}
	
	private Element copyElement(Element element) {
		
		ONEPlusElement newElement = new ONEPlusElement();
		
		newElement.setContent(element.getContent());
		
		for(int i = 0; i < element.getNumElements(); i++)
			newElement.addElement(copyElement(element.getElement(i)));
		
		return newElement;
	}
}