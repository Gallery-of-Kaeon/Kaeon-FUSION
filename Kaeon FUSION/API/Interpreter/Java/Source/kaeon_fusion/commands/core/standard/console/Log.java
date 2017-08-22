package kaeon_fusion.commands.core.standard.console;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class Log extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Log");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> packet = new ArrayList<Object>();
		
		packet.add("Console Out");
		packet.add(processed);
		
		call(packet);
		
		return null;
	}
}