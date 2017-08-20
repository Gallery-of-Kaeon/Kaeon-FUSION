package kaeon_fusion_legacy.commands.core.standard.list;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class List extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("List");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return processed;
	}
}