package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Wait extends FUSIONUnit {
	
	public Wait() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Wait");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		try {
			Thread.sleep((long) (Double.parseDouble("" + processed.get(0)) * 1000));
		}
		
		catch (Exception exception) {
			
		}
		
		return null;
	}
}