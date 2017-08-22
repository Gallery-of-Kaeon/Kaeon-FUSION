package interfaces.machine.utilities.commands.flow_control;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class Else extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Else");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String code = "else{";
		
		boolean isIf = false;
		
		if(processed.size() > 0) {
			
			isIf = element.getElement(0).getContent().equalsIgnoreCase("Break");
			
			if(isIf && element.getElement(0).getNumElements() > 0)
				code = "else if(" + process(element.getElement(0).getElement(0)) + "){";
		}
		
		for(int i = isIf ? 1 : 0; i < processed.size(); i++)
			code += processed.get(i);
		
		return code + "}";
	}
}