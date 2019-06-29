package standard_kaeon_fusion.commands.list;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class IsSortedNumerical extends FUSIONUnit {
	
	public IsSortedNumerical() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Is Sorted Numerical");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(processed.get(0) instanceof ArrayList) {
			
			ArrayList<Object> list = (ArrayList<Object>) processed.get(0);
			
			if(processed.size() == 1) {
				
				for(int i = 0; i < list.size() - 1; i++) {
					
					Double a = Double.parseDouble("" + list.get(i));
					Double b = Double.parseDouble("" + list.get(i + 1));
					
					if(a.compareTo(b) > 0)
						return false;
				}
			}
			
			else {
				
				for(int i = 0; i < list.size() - 1; i++) {
					
					int givenKey = Integer.parseInt("" + processed.get(1)) - 1;
					
					Double a = Double.parseDouble("" + ((ArrayList<Object>) list.get(i)).get(givenKey));
					Double b = Double.parseDouble("" + ((ArrayList<Object>) list.get(i + 1)).get(givenKey));
					
					if(a.compareTo(b) > 0)
						return false;
				}
			}
		}
		
		else {
			
			String string = "" + processed.get(0);
			
			for(int i = 0; i < string.length() - 1; i++) {
				
				if(((int) string.charAt(i)) > ((int) string.charAt(i + 1)))
					return false;
			}
		}
		
		return true;
	}
}