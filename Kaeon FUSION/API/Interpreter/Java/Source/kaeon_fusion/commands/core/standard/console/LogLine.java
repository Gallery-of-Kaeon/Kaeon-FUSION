package kaeon_fusion.commands.core.standard.console;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import one_plus.element.Element;

public class LogLine extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Log Line");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		processed.add('\n');
		
		ArrayList<Object> packet = new ArrayList<Object>();
		
		packet.add("Console Out");
		packet.add(processed);
		
		call(packet);
		
		return null;
	}
}