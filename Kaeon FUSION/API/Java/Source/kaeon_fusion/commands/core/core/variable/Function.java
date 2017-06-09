package kaeon_fusion.commands.core.core.variable;

import java.util.ArrayList;

import kaeon_fusion.KaeonFUSION;
import kaeon_fusion.commands.Command;
import kaeon_fusion.interface_module.Interface;
import kaeon_fusion.state.function_stone.FunctionStone;
import kaeon_fusion.state.variable_stone.VariableStone;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class Function extends Command {
	
	public boolean onVerify(Element element) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Command");
		
		ArrayList<PhilosophersStonePlus> commands = get(tags);
		
		for(int i = 0; i < commands.size(); i++) {
			
			if(!(commands.get(i) instanceof Literal) &&
					!(commands.get(i) instanceof Variable) &&
					!(commands.get(i) instanceof Function)) {
				
				if(((Command) commands.get(i)).onVerify(element))
					return false;
			}
		}
		
		tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add(element.getContent());
		
		return has(tags);
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags.add("Function");
		tags.add(element.getContent());
		
		Element function = new Element();
		function.addElements(((FunctionStone) get(tags).get(0)).getFunction().getElements());
		
		KaeonFUSION fusion = new KaeonFUSION();
		
		tags = new ArrayList<String>();
		
		tags.add("Variable");
		
		ArrayList<PhilosophersStonePlus> variables = get(tags);
		
		for(int i = 0; i < variables.size(); i++) {
			
			if(variables.get(i).isTagged("Global"))
				fusion.publiclyConnect((VariableStone) variables.get(i));
			
			else
				fusion.publiclyConnect(new VariableStone((VariableStone) variables.get(i)));
		}
		
		tags = new ArrayList<String>();
		tags.add("Function");
		
		ArrayList<PhilosophersStonePlus> functions = get(tags);
		
		for(int i = 0; i < functions.size(); i++)
			fusion.publiclyConnect(new FunctionStone((FunctionStone) functions.get(i)));
		
		tags = new ArrayList<String>();
		tags.add("Interface");
		
		ArrayList<PhilosophersStonePlus> interfaces = get(tags);
		
		for(int i = 0; i < interfaces.size(); i++)
			fusion.publiclyConnectMutually(((Interface) interfaces.get(i)).clone());
		
		fusion.publiclyConnect(getKaeonFUSION().getConsole());
		
		Object object = fusion.process(function, processed);
		
		if(fusion.hasErrorOccured())
			getKaeonFUSION().triggerError(true);
		
		else if(!fusion.isReturning())
			return fusion;
		
		return object;
	}
}