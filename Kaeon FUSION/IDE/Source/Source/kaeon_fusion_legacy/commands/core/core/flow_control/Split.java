package kaeon_fusion_legacy.commands.core.core.flow_control;

import java.util.ArrayList;

import kaeon_fusion_legacy.KaeonFUSION;
import kaeon_fusion_legacy.commands.Command;
import kaeon_fusion_legacy.interface_module.Interface;
import kaeon_fusion_legacy.state.function_stone.FunctionStone;
import kaeon_fusion_legacy.state.variable_stone.VariableStone;
import legacy.one_plus.element.Element;
import legacy.philosophers_stone_plus.PhilosophersStonePlus;

public class Split extends Command implements Runnable {

	private Element element;
	
	public Split() {
		
	}
	
	public Split(Element element) {
		
		this.element = new Element();
		
		for(int i = 0; i < element.getNumElements(); i++)
			this.element.addElement(element.getElement(i));
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Split");
	}
	
	public boolean onDescend(Element element) {
		return false;
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		Split split = new Split(element);
		split.privatelyConnect(this);
		
		Thread thread = new Thread(split);
		thread.start();
		
		return null;
	}

	public void run() {
		
		KaeonFUSION fusion = new KaeonFUSION();
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Variable");
		
		ArrayList<PhilosophersStonePlus> variables = get(tags);
		
		for(int i = 0; i < variables.size(); i++)
			fusion.publiclyConnect((VariableStone) variables.get(i));
		
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
		
		tags = new ArrayList<String>();
		tags.add("Console");
		
		fusion.publiclyConnect(get(tags).get(0));
		
		fusion.process(element);
	}
}