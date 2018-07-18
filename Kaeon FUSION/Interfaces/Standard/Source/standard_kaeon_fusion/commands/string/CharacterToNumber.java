package standard_kaeon_fusion.commands.string;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class CharacterToNumber extends FUSIONUnit {
	
	public CharacterToNumber() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Character To Number");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return (int) ("" + processed.get(0)).charAt(0);
	}
}