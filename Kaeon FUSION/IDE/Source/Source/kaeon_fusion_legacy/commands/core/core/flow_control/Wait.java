package kaeon_fusion_legacy.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Wait extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Wait");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		try {
			Thread.sleep((int) (Double.parseDouble("" + processed.get(0)) * 1000));
		}
		
		catch (Exception e) {
			
		}
		
		return null;
	}
}