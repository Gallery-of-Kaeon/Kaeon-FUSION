package standard_kaeon_fusion.commands.kaeon;

import java.util.ArrayList;

import aether_standard.Aether;
import fusion.FUSIONUnit;
import one.Element;

public class AetherCommand extends FUSIONUnit {
	
	public AetherCommand() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Aether");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return Aether.call("" + processed.get(0), 0, processed.get(1));
	}
}