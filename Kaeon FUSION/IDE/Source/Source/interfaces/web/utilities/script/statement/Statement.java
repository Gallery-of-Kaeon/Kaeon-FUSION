package interfaces.web.utilities.script.statement;

import java.util.ArrayList;

import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class Statement extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("In");
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String statement = "";
		
		if(!element.getElement(0).getContent().equalsIgnoreCase("Default"))
			statement += element.getElement(0).getContent() + ".";
		
		for(int i = getElementPosition(element) + 1; i < element.getParent().getNumElements();) {
			
			statement +=
					processElement(element.getParent().removeElement(i)) +
					(i < element.getParent().getNumElements() ? "." : "");
		}
		
		statement += ";";
		
		return statement;
	}
	
	private String processElement(Element element) {
		
		if(element.getContent().equalsIgnoreCase("Return"))
			return "" + element.getElement(0).getContent();
		
		String processed = element.getContent() + "(";
		
		for(int i = 0; i < element.getNumElements(); i++) {

			processed +=
					process(element.getElement(i)) +
					(i < element.getNumElements() - 1 ? "," : "");
		}
		
		processed += ")";
		
		return processed;
	}
	
	private int getElementPosition(Element element) {
		
		Element parent = element.getParent();
		
		if(parent != null) {
			
			for(int i = 0; i < parent.getNumElements(); i++) {
				
				if(parent.getElement(i) == element)
					return i;
			}
		}
		
		return -1;
	}
}