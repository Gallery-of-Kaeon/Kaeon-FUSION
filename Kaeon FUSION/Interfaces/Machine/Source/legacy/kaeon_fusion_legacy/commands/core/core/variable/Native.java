package legacy.kaeon_fusion_legacy.commands.core.core.variable;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.kaeon_fusion_legacy.interface_module.native_stone.NativeStone;
import legacy.utilities.one_plus.element.Element;

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