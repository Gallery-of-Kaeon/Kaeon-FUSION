package legacy.kaeon_fusion_legacy.commands.core.core.variable;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.utilities.one_plus.element.Element;

public class Execute extends Command {
	
	public Execute() {
		
		super();
		
		tag("Execute");
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Execute");
	}
	
	public boolean onDescend(Element element) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add("" + element.getElement(0).getContent());
		
		return !has(tags);
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add("" + element.getElement(0).getContent());
		
		return null;
	}
}