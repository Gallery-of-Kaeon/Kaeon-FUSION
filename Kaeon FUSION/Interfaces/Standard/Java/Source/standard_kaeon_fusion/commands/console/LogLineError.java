package standard_kaeon_fusion.commands.console;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class LogLineError extends FUSIONUnit {
	
	public LogLineError() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Log Line Error");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 0; i < processed.size(); i++)
			System.err.print(processed.get(i));
		
		System.err.print("\n");
		
		return null;
	}
}