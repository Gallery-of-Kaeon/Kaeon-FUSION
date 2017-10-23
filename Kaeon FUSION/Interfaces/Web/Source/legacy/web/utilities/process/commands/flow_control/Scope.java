package legacy.web.utilities.process.commands.flow_control;

import java.util.ArrayList;

import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;
import legacy.web.utilities.script.function.Function;
import legacy.web.utilities.script.variable.Variable;

public class Scope extends FUSIONStone {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Scope");
	}
	
	public boolean onTrickleDown(Element element) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Variable");
		
		Variable variable = (Variable) get(tags).get(0);
		
		variable.push();
		
		tags = new ArrayList<String>();
		tags.add("Function");
		
		Function function = (Function) get(tags).get(0);
		
		function.push();
		
		return true;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		String script = "do{";
		
		boolean isIf = false;
		
		if(element.getNumElements() >= 1) {
			
			if(element.getElement(0).getContent().equalsIgnoreCase("Break")) {
				
				script = "if(";
				
				if(element.getElement(0).getNumElements() == 1)
					script += process(element.getElement(0).getElement(0));
				
				script += "){";
				
				isIf = true;
			}
		}
		
		for(int i = 0; i < processed.size(); i++)
			script += processed.get(i);
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Variable");
		
		Variable variable = (Variable) get(tags).get(0);
		
		variable.pop();
		
		tags = new ArrayList<String>();
		tags.add("Function");
		
		Function function = (Function) get(tags).get(0);
		
		function.pop();
		
		if(isIf)
			return script + "}";
		
		return script + "}while(false);";
	}
}