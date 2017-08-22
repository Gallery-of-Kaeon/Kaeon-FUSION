package kaeon_fusion.commands.core.core.variable;

import java.util.ArrayList;

import kaeon_fusion.commands.Command;
import kaeon_fusion.commands.core.core.variable.util.TemporaryElement;
import kaeon_fusion.state.function_stone.FunctionStone;
import one_plus.ONEPlus;
import one_plus.element.Element;

public class Execute extends Command {
	
	public Execute() {
		
		super();
		
		tag("Execute");
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Execute");
	}
	
	public boolean onDescend(Element element) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add("" + element.getElement(0).getContent());
		
		return !has(tags);
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add("" + element.getElement(0).getContent());
		
		ArrayList<Element> elements = null;
		
		if(has(tags)) {
			System.out.println(((FunctionStone) get(tags).get(0)).getFunction());
			elements = ((FunctionStone) get(tags).get(0)).getFunction().getElements();
		}
		
		else
			elements = new ONEPlus("" + processed.get(0)).getElements();
		
		int elementPosition = getElementPosition(element) + 1;
		
		for(int i = 0; i < elements.size(); i++)
			element.getParent().addElement(new TemporaryElement(elements.get(i)), elementPosition + i);
		
		return null;
	}
	
	private int getElementPosition(Element element) {
		
		Element parent = element.getParent();
		
		for(int i = 0; i < parent.getNumElements(); i++) {
			
			if(parent.getElement(i) == element)
				return i;
		}
		
		return 0;
	}
}