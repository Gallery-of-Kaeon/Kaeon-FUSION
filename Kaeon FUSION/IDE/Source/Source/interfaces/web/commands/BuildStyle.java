package interfaces.web.commands;

import java.util.ArrayList;

import interfaces.web.utilities.style.Style;
import io.IO;
import kaeon_fusion_legacy.interface_module.build_stone.BuildStone;
import legacy.one_plus.element.Element;

public class BuildStyle extends BuildStone {
	
	public BuildStyle() {
		tag("Style");
	}
	
	public Object onBuild(ArrayList<Element> functions, ArrayList<Element> arguments) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		Element source = functions.get(0);
		
		source = copyElement(source);
		source.setContent("Style");
		
		String output = "" + new Style().process(source);
		output = output.substring(7, output.length() - 8);

		if(arguments.size() >= 1)
			IO.save(output, arguments.get(0).getContent() + source.getContent() + ".css");

		else
			IO.save(output, source.getContent() + ".css");
		
		return null;
	}
	
	private Element copyElement(Element element) {
		
		Element copy = new Element();
		
		copy.setContent(element.getContent());
		
		for(int i = 0; i < element.getNumElements(); i++)
			copy.addElement(copyElement(element.getElement(i)));
		
		return copy;
	}
}