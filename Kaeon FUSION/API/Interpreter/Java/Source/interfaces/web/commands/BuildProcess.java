package interfaces.web.commands;

import java.util.ArrayList;

import interfaces.web.utilities.process.ProcessCommand;
import io.IO;
import kaeon_fusion.interface_module.build_stone.BuildStone;
import one_plus.element.Element;

public class BuildProcess extends BuildStone {
	
	public BuildProcess() {
		tag("Process");
	}
	
	public Object onBuild(ArrayList<Element> functions, ArrayList<Element> arguments) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		Element source = functions.get(0);
		
		source = copyElement(source);
		source.setContent("Process");
		
		String output = "" + new ProcessCommand().process(source);
		output = output.substring(9, output.length() - 10);

		if(arguments.size() >= 1)
			IO.save(output, arguments.get(0).getContent() + source.getContent() + ".php");

		else
			IO.save(output, source.getContent() + ".php");
		
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