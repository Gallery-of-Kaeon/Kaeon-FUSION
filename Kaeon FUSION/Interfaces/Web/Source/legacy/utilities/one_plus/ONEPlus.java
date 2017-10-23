package legacy.utilities.one_plus;

import java.util.ArrayList;

import legacy.utilities.one_plus.read.ONEPlusElement;
import legacy.utilities.one_plus.read.ONEPlusReader;
import legacy.utilities.one_plus.read.directives.ONEPlusDirectiveFormatter;
import legacy.utilities.one_plus.read.format.ONEPlusFormatter;

public class ONEPlus extends ONEPlusElement {
	
	public ONEPlus() {
		super();
	}
	
	public ONEPlus(String onePlus) {
		
		this();
		
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
		
		ONEPlusReader.readONEPlus(this, format(onePlusList));
		ONEPlusDirectiveFormatter.formatDirectives(this);
	}
	
	public ONEPlus(ArrayList<String> onePlus) {
		
		this();
		
		ONEPlusReader.readONEPlus(this, format(onePlus));
		ONEPlusDirectiveFormatter.formatDirectives(this);
	}
	
	public static ArrayList<String> format(ArrayList<String> file) {
		return ONEPlusFormatter.formatONEPlus(file);
	}
	
	public static ONEPlus getONEPlusDirectivesSpecification() {
		return new ONEPlus("/specification/ONE+ Directives.op");
	}
}