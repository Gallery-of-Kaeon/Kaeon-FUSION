package kaeon_fusion.commands.core.core.variable;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import kaeon_fusion.interface_module.build_stone.BuildStone;
import one_plus.element.Element;

public class Build extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Build");
	}
	
	public boolean onDescend(Element element) {
		return false;
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Build");
		tags.add(element.getElement(0).getContent());
		
		ArrayList<Element> arguments = new ArrayList<Element>();
		
		arguments.addAll(element.getElements());
		arguments.remove(0);
		
		try {
			return ((BuildStone) get(tags).get(0)).build(element.getElement(0).getElements(), arguments);
		}
		
		catch(Exception exception) {
			
		}
		
		return null;
	}
}