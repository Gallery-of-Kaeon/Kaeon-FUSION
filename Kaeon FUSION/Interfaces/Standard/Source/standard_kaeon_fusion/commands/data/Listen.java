package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Listen extends FUSIONUnit {
	
	ArrayList<ArrayList<Object>> lists;
	
	public Listen() {
		
		tags.add("Standard");
		
		lists = new ArrayList<ArrayList<Object>>();
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Listen");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 0; i < processed.size(); i++)
			lists.add((ArrayList<Object>) processed.get(i));
		
		return null;
	}
	
	public Object onCall(ArrayList<Object> packet) {
		
		for(int i = 0; i < lists.size(); i++)
			lists.get(i).add(packet);
		
		return null;
	}
}
