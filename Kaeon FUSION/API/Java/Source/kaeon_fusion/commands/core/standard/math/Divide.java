package kaeon_fusion.commands.core.standard.math;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Divide extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Divide");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		double number =
				Double.parseDouble("" + processed.get(0)) /
				Double.parseDouble("" + processed.get(1));
		
		if(number % 1 != 0)
			return number;
		
		else
			return (int) number;
	}
}