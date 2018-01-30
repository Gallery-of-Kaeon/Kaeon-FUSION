package standard_one_plus.utilities;

import java.util.ArrayList;
import one.Element;

public class DirectiveUtilities {
	
	public static boolean isDirective(ArrayList<Element> directives, Element element) {
		
		for(int i = 0; i < directives.size(); i++) {
			
			if(directives.get(i) == element)
				return true;
		}
		
		return false;
	}
}