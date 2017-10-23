package dialects;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.state.function_stone.FunctionStone;
import legacy.utilities.one_plus.element.Element;
import legacy.web.utilities.page.PageBuilder;
import philosophers_stone.PhilosophersStone;

public class Page extends PhilosophersStone {
	
	public Object onCall(ArrayList<Object> packet) {
		
		if(!((String) packet.get(0)).equalsIgnoreCase("Build") ||
				!((String) packet.get(1)).equalsIgnoreCase("Page")) {
			
			return null;
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<String> arguments = (ArrayList<String>) packet.get(4);
		
		PageBuilder builder = new PageBuilder(
				"" +
				(arguments.size() >= 1 ?
						arguments.get(0) : ""));
		
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
		element.setContent("" + functions.get(0).get(0));
		element.setParent(new Element("Define"));
		
		builder.process(element);
		
		return null;
	}
}