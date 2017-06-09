package kaeon_fusion.commands.core.standard.list;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class List extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("List");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return processed;
	}
}