package kaeon_fusion.commands.core.standard.string;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Concatenate extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Concatenate");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		String concatenate = "";
		
		for(int i = 0; i < processed.size(); i++)
			concatenate += processed.get(i);
		
		return concatenate;
	}
}