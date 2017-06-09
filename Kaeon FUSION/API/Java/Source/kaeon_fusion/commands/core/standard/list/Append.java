package kaeon_fusion.commands.core.standard.list;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Append extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Append");
	}
	
	@SuppressWarnings("unchecked")
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		((ArrayList<Object>) processed.get(0)).add(processed.get(1));
		
		return null;
	}
}