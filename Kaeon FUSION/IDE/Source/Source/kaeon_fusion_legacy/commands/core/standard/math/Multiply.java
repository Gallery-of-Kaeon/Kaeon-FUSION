package kaeon_fusion_legacy.commands.core.standard.math;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Multiply extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Multiply");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		double number =
				Double.parseDouble("" + processed.get(0)) *
				Double.parseDouble("" + processed.get(1));
		
		if(number % 1 != 0)
			return number;
		
		else
			return (int) number;
	}
}