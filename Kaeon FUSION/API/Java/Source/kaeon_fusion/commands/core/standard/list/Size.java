package kaeon_fusion.commands.core.standard.list;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Size extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Size");
	}
	
	@SuppressWarnings("unchecked")
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return ((ArrayList<Object>) processed.get(0)).size();
	}
}