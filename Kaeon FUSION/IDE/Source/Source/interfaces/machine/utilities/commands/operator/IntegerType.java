package interfaces.machine.utilities.commands.operator;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class IntegerType extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Integer");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "int";
	}
}