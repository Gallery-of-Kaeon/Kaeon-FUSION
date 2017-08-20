package kaeon_fusion_legacy.commands.core.standard.list;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

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