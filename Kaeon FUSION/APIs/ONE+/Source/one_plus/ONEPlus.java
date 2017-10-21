package one_plus;

import java.util.ArrayList;

import one.Element;
import one_plus.read.ONEPlusElement;
import one_plus.read.ONEPlusReader;
import one_plus.read.directives.ONEPlusDirectiveFormatter;
import one_plus.read.format.ONEPlusFormatter;

public class ONEPlus {
	
	public static Element parseONEPlus(String onePlus) {
		
		ArrayList<String> onePlusList = new ArrayList<String>();
		String currentString = "";
		
		for(int i = 0; i < onePlus.length(); i++) {
			
			if(onePlus.charAt(i) != '\n')
				currentString += onePlus.charAt(i);
			
			if(onePlus.charAt(i) == '\n' || i == onePlus.length() - 1) {
				onePlusList.add(currentString);
				currentString = "";
			}
		}
		
		return parseONEPlus(onePlusList);
	}
	
	public static Element parseONEPlus(ArrayList<String> onePlus) {
		
		ONEPlusElement element = new ONEPlusElement();
		
		ONEPlusReader.readONEPlus(element, format(onePlus));
		ONEPlusDirectiveFormatter.formatDirectives(element);
		
		return element;
	}
	
	private static ArrayList<String> format(ArrayList<String> file) {
		return ONEPlusFormatter.formatONEPlus(file);
	}
}