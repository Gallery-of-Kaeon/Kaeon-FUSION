package interfaces.machine.commands;

import java.util.ArrayList;

import interfaces.machine.utilities.NativeBuilder;
import kaeon_fusion.interface_module.build_stone.BuildStone;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class BuildNativeCode extends BuildStone {
	
	public BuildNativeCode() {
		tag("Native Code");
	}
	
	public Object onBuild(ArrayList<Element> functions, ArrayList<Element> arguments) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Function");
		
		ArrayList<PhilosophersStonePlus> definitions = get(tags);
		
		Element nativeCode = functions.get(0);
		
		NativeBuilder nativeBuilder =
				new NativeBuilder(
						"" +
						(arguments.size() >= 1 ?
							arguments.get(0).getContent() : ""));
		
		for(int i = 0; i < definitions.size(); i++)
			nativeBuilder.publiclyConnect(definitions.get(i));
		
		nativeBuilder.process(nativeCode);
		
		return null;
	}
}