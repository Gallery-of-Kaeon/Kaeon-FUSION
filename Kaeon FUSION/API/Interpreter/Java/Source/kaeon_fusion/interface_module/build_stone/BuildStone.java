package kaeon_fusion.interface_module.build_stone;

import java.util.ArrayList;

import kaeon_fusion.state.function_stone.FunctionStone;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class BuildStone extends PhilosophersStonePlus {
	
	public BuildStone() {
		tag("Build");
	}
	
	public Object build(ArrayList<Element> names, ArrayList<Element> arguments) {
		return onBuild(getFunctions(names), arguments);
	}
	
	private ArrayList<Element> getFunctions(ArrayList<Element> names) {
		
		ArrayList<Element> functions = new ArrayList<Element>();
		
		for(int i = 0; i < names.size(); i++) {
			
			Element function = getFunction(names.get(i).getContent());
			
			if(function != null)
				functions.add(function);
		}
		
		return functions;
	}
	
	private Element getFunction(String name) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add(name);
		
		try {
			return ((FunctionStone) get(tags).get(0)).getFunction();
		}
		
		catch(Exception exception) {
			return null;
		}
	}
	
	public Object onBuild(ArrayList<Element> functions, ArrayList<Element> arguments) {
		return null;
	}
}