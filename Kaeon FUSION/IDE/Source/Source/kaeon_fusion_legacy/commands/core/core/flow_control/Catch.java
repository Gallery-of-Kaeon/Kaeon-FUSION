package kaeon_fusion_legacy.commands.core.core.flow_control;

import kaeon_fusion_legacy.KaeonFUSION;
import kaeon_fusion_legacy.commands.Command;
import legacy.one_plus.element.Element;

public class Catch extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Catch");
	}
	
	public boolean onDescend(Element element) {
		
		KaeonFUSION fusion = getKaeonFUSION();
		
		if(fusion.hasErrorOccured()) {
			
			fusion.triggerError(false);
			
			return true;
		}
		
		return false;
	}
}