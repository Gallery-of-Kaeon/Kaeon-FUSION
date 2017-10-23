package legacy.web.commands;

import java.util.ArrayList;

import io.IO;
import legacy.kaeon_fusion_legacy.interface_module.build_stone.BuildStone;
import legacy.utilities.one_plus.element.Element;
import legacy.web.utilities.script.Script;

public class BuildScript extends BuildStone {
	
	public BuildScript() {
		tag("Script");
	}
	
	public Object onBuild(ArrayList<Element> functions, ArrayList<Element> arguments) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		Element source = functions.get(0);
		
		source = copyElement(source);
		source.setContent("Script");
		
		String output = "" + new Script().process(source);
		output = output.substring(8, output.length() - 9);

		if(arguments.size() >= 1)
			IO.save(output, arguments.get(0).getContent() + source.getContent() + ".js");

		else
			IO.save(output, source.getContent() + ".js");
		
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