package standard_kaeon_fusion.commands.build;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class Derive extends FUSIONUnit {
	
	public Derive() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Derive");
	}
	
	public boolean trickleDown(Element element) {
		return false;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> code = new ArrayList<String>();
		
		for(Element codeElement : element.children.get(0).children)
			code.add(codeElement.content);
		
		ArrayList<Element> arguments = new ArrayList<Element>();
		
		for(int i = 1; i < element.children.size(); i++)
			arguments.add(element.children.get(i));
		
		PhilosophersStoneUtilities.call(
				this,
				"Derive",
				element.children.get(0).content,
				code,
				arguments);
		
		return null;
	}
}