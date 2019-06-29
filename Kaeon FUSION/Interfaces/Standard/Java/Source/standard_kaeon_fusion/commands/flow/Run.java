package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Run extends FUSIONUnit {
	
	public Run() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Run");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(Object object : processed) {
			
			try {
				Runtime.getRuntime().exec("" + object);
			}
			
			catch(Exception exception) {
				
			}
		}
		
		return null;
	}
}