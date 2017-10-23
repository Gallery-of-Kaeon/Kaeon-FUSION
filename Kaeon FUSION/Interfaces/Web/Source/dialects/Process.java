package dialects;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.state.function_stone.FunctionStone;
import legacy.utilities.one_plus.element.Element;
import philosophers_stone.PhilosophersStone;

public class Process extends PhilosophersStone {
	
	public Object onCall(ArrayList<Object> packet) {
		
		if(!((String) packet.get(0)).equalsIgnoreCase("Build") ||
				!((String) packet.get(1)).equalsIgnoreCase("Process")) {
			
			return null;
		}
		
		legacy.web.utilities.process.ProcessCommand builder = new legacy.web.utilities.process.ProcessCommand();
		
		@SuppressWarnings("unchecked")
		ArrayList<ArrayList<Object>> functionDefinitions = (ArrayList<ArrayList<Object>>) packet.get(2);
		
		for(ArrayList<Object> definition : functionDefinitions) {
			
			Element element = new Element((one.Element) definition.get(1));
			element.setContent("" + definition.get(0));
			
			builder.publiclyConnect(new FunctionStone(element));
		}

		@SuppressWarnings("unchecked")
		ArrayList<ArrayList<Object>> functions = (ArrayList<ArrayList<Object>>) packet.get(3);
		
		Element element  = new Element((one.Element) functions.get(0).get(1));
		element.setContent("Process");
		
		builder.process(element);
		
		return null;
	}
}