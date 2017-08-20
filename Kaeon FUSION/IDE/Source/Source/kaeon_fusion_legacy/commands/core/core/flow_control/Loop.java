package kaeon_fusion_legacy.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import kaeon_fusion_legacy.commands.core.core.variable.util.TemporaryElement;
import legacy.one_plus.element.Element;

public class Loop extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Loop");
	}
	
	public Element onChangeElement(Element element, ArrayList<Object> processed) {
		
		if(processed.size() == 0 ? true : Boolean.parseBoolean("" + processed.get(0))) {
			
			for(int i = 0; i < element.getParent().getNumElements(); i++) {

				if(element.getParent().getElement(i) instanceof TemporaryElement)
					element.getParent().removeElement(i);
			}
			
			return element.getParent();
		}
		
		return null;
	}
}