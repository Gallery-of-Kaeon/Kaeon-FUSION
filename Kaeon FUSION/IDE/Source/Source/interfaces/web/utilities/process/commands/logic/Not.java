package interfaces.web.utilities.process.commands.logic;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Not extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Not");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "!(" + processed.get(0) + ")";
	}
}