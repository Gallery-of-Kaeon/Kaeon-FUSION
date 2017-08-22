package kaeon_fusion.commands.core.standard.math;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Random extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Random");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		return Math.random();
	}
}