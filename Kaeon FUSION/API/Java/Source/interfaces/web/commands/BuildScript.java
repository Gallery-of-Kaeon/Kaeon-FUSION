package interfaces.web.commands;

import java.util.ArrayList;

import interfaces.web.utilities.script.Script;
import io.IO;
import kaeon_fusion.commands.Command;
import kaeon_fusion.state.function_stone.FunctionStone;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class BuildScript extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Build Script");
	}
	
	public boolean onDescend(Element element) {
		return false;
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		ArrayList<PhilosophersStonePlus> definitions = get(tags);
		
		Element source = null;
		
		for(int i = 0; i < definitions.size() && source == null; i++) {
			
			if(definitions.get(i).isTagged(element.getElement(0).getContent()))
				source = ((FunctionStone) definitions.get(i)).getFunction();
		}
		
		source = copyElement(source);
		source.setContent("Script");
		
		String output = "" + new Script().process(source);
		output = output.substring(8, output.length() - 9);

		if(element.getNumElements() >= 2)
			IO.save(output, element.getElement(1).getContent() + source.getContent() + ".js");

		if(element.getNumElements() >= 2)
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