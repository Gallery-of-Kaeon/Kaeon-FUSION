package kaeon_fusion.commands.core.core.variable;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import kaeon_fusion.interface_module.native_stone.NativeStone;
import one_plus.element.Element;

public class Native extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Native");
	}
	
	@SuppressWarnings("unchecked")
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Native");
		tags.add("" + processed.get(0));
		
		return ((NativeStone) get(tags).get(0)).runNativeFunction((ArrayList<Object>) processed.get(1));
	}
}