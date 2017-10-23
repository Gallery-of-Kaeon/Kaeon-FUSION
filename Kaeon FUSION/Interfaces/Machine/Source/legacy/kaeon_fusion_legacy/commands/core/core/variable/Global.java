package legacy.kaeon_fusion_legacy.commands.core.core.variable;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.commands.Command;
import legacy.kaeon_fusion_legacy.state.variable_stone.VariableStone;
import legacy.utilities.one_plus.element.Element;
import legacy.utilities.philosophers_stone_plus.PhilosophersStonePlus;

public class Global extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Global");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		String variableName = Literal.format(element.getElement(0).getContent());
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add(variableName);
		
		if(has(tags))
			return null;
		
		tags = new ArrayList<String>();
		
		tags.add("Class");
		tags.add(variableName);
		
		if(has(tags))
			return null;
		
		tags = new ArrayList<String>();
		
		tags.add("Object");
		tags.add(variableName);
		
		if(has(tags))
			return null;
		
		tags = new ArrayList<String>();
		
		tags.add("Native");
		tags.add(variableName);
		
		if(has(tags))
			return null;
		
		tags = new ArrayList<String>();
		
		tags.add("Variable");
		tags.add(variableName);
		
		Object value = null;
		
		if(has(tags)) {
			
			PhilosophersStonePlus variable = get(tags).get(0);
			value = ((VariableStone) variable).getValue();
			
			for(int i = 0; i < variable.getConnections().size(); i++)
				variable.disconnectMutually(variable.getConnections().get(i));
		}
		
		tags.add("Global");
		
		if(!has(tags)) {
			
			VariableStone global = new VariableStone(variableName, value);
			global.tag("Global");
			
			publiclyConnectMutually(global);
		}
		
		return null;
	}
}