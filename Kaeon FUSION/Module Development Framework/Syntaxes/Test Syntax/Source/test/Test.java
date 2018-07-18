package test;

import one.Element;
import one.ElementUtilities;

public class Test {
	
	public static Element parse(String document) {
		
		Element one = new Element();
		
		String[] lines = document.split("\n");
		
		for(int i = 0; i < lines.length; i++) {
			
			String[] values = lines[i].split(",");
			
			for(int j = 0; j < values.length; j++) {
				
				String content = values[j].trim();
				
				if(content.length() > 0) {
					
					ElementUtilities.addChild(
							one,
							ElementUtilities.createElement(content));
				}
			}
		}
		
		return one;
	}
}