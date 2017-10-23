package legacy.web.utilities.process;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.state.function_stone.FunctionStone;
import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;
import legacy.web.utilities.process.commands.console.Log;
import legacy.web.utilities.process.commands.console.LogLine;
import legacy.web.utilities.process.commands.flow_control.Break;
import legacy.web.utilities.process.commands.flow_control.Loop;
import legacy.web.utilities.process.commands.flow_control.Return;
import legacy.web.utilities.process.commands.flow_control.Scope;
import legacy.web.utilities.process.commands.list.At;
import legacy.web.utilities.process.commands.list.List;
import legacy.web.utilities.process.commands.logic.And;
import legacy.web.utilities.process.commands.logic.Equal;
import legacy.web.utilities.process.commands.logic.Greater;
import legacy.web.utilities.process.commands.logic.GreaterOrEqual;
import legacy.web.utilities.process.commands.logic.Less;
import legacy.web.utilities.process.commands.logic.LessOrEqual;
import legacy.web.utilities.process.commands.logic.Not;
import legacy.web.utilities.process.commands.logic.Or;
import legacy.web.utilities.process.commands.math.Add;
import legacy.web.utilities.process.commands.math.Divide;
import legacy.web.utilities.process.commands.math.Modulus;
import legacy.web.utilities.process.commands.math.Multiply;
import legacy.web.utilities.process.commands.math.Subtract;
import legacy.web.utilities.process.function.Function;
import legacy.web.utilities.process.literal.Literal;
import legacy.web.utilities.process.variable.Variable;

public class ProcessCommand extends FUSIONStone {
	
	private ProcessCommand process;
	
	public ProcessCommand() {
		process = new ProcessCommand(true);
	}
	
	public ProcessCommand(boolean isProcess) {

		publiclyConnectMutually(new Literal());
		
		publiclyConnectMutually(new Variable());
		
		publiclyConnectMutually(new Function());

		publiclyConnectMutually(new Log());
		publiclyConnectMutually(new LogLine());

		publiclyConnectMutually(new Break());
		publiclyConnectMutually(new Loop());
		publiclyConnectMutually(new Return());
		publiclyConnectMutually(new Scope());

		publiclyConnectMutually(new List());
		publiclyConnectMutually(new At());
		
		publiclyConnectMutually(new And());
		publiclyConnectMutually(new Equal());
		publiclyConnectMutually(new Greater());
		publiclyConnectMutually(new GreaterOrEqual());
		publiclyConnectMutually(new Less());
		publiclyConnectMutually(new LessOrEqual());
		publiclyConnectMutually(new Not());
		publiclyConnectMutually(new Or());

		publiclyConnectMutually(new Add());
		publiclyConnectMutually(new Divide());
		publiclyConnectMutually(new Modulus());
		publiclyConnectMutually(new Multiply());
		publiclyConnectMutually(new Subtract());
	}
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("Process");
	}
	
	public boolean onTrickleDown(Element element) {
		return false;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		Element processElement = element;
		
		if(processElement.getNumElements() == 1) {
			
			if(processElement.getElement(0).getNumElements() == 0) {
				
				ArrayList<String> tags = new ArrayList<String>();
				
				tags.add("Function");
				tags.add(processElement.getElement(0).getContent());
				
				processElement = ((FunctionStone) get(tags).get(0)).getFunction();
			}
		}
		
		String process = "<?php ";
		
		for(int i = 0; i < processElement.getNumElements(); i++)
			process +=	this.process.process(processElement.getElement(i));
		
		process += " ?>";
		
		return process;
	}
}