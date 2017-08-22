package interfaces.machine.utilities.commands.operator;

import java.util.ArrayList;

import fusion.FUSIONStone;
import one_plus.element.Element;

public class CharacterType extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Character");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		return "char";
	}
}