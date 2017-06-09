package interfaces.machine.commands;

import java.util.ArrayList;

import interfaces.machine.utilities.NativeBuilder;
import kaeon_fusion.commands.Command;
import kaeon_fusion.state.function_stone.FunctionStone;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class BuildNativeCode extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Build Native Code");
	}
	
	public boolean onDescend(Element element) {
		return false;
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		ArrayList<PhilosophersStonePlus> definitions = get(tags);
		
		Element nativeCode = null;
		
		for(int i = 0; i < definitions.size() && nativeCode == null; i++) {
			
			if(definitions.get(i).isTagged(element.getElement(0).getContent()))
				nativeCode = ((FunctionStone) definitions.get(i)).getFunction();
		}
		
		NativeBuilder nativeBuilder =
				new NativeBuilder(
						"" +
						(element.getNumElements() >= 2 ?
							element.getElement(1).getContent() : ""));
		
		for(int i = 0; i < definitions.size(); i++)
			nativeBuilder.publiclyConnect(definitions.get(i));
		
		nativeBuilder.process(nativeCode);
		
		return null;
	}
}