package interfaces.web.utilities.script;

import java.util.ArrayList;

import fusion.FUSIONStone;
import interfaces.web.utilities.script.commands.flow_control.Break;
import interfaces.web.utilities.script.commands.flow_control.Loop;
import interfaces.web.utilities.script.commands.flow_control.Return;
import interfaces.web.utilities.script.commands.flow_control.Scope;
import interfaces.web.utilities.script.commands.logic.And;
import interfaces.web.utilities.script.commands.logic.Equal;
import interfaces.web.utilities.script.commands.logic.Greater;
import interfaces.web.utilities.script.commands.logic.GreaterOrEqual;
import interfaces.web.utilities.script.commands.logic.Less;
import interfaces.web.utilities.script.commands.logic.LessOrEqual;
import interfaces.web.utilities.script.commands.logic.Not;
import interfaces.web.utilities.script.commands.logic.Or;
import interfaces.web.utilities.script.commands.math.Add;
import interfaces.web.utilities.script.commands.math.Divide;
import interfaces.web.utilities.script.commands.math.Modulus;
import interfaces.web.utilities.script.commands.math.Multiply;
import interfaces.web.utilities.script.commands.math.Subtract;
import interfaces.web.utilities.script.function.Function;
import interfaces.web.utilities.script.literal.Literal;
import interfaces.web.utilities.script.statement.Statement;
import interfaces.web.utilities.script.variable.Global;
import interfaces.web.utilities.script.variable.Variable;
import kaeon_fusion.state.function_stone.FunctionStone;
import one_plus.element.Element;

public class Script extends FUSIONStone {
	
	private Script script;
	
	public Script() {
		script = new Script(true);
	}
	
	public Script(boolean isScript) {
		
		publiclyConnectMutually(new Literal());
		
		publiclyConnectMutually(new Global());
		publiclyConnectMutually(new Variable());
		
		publiclyConnectMutually(new Function());
		
		publiclyConnectMutually(new Statement());
		
		publiclyConnectMutually(new Break());
		publiclyConnectMutually(new Loop());
		publiclyConnectMutually(new Scope());
		publiclyConnectMutually(new Return());
		
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
		return element.getContent().equalsIgnoreCase("Script");
	}
	
	public boolean onTrickleDown(Element element) {
		return false;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		Element scriptElement = element;
		
		if(scriptElement.getNumElements() == 1) {
			
			if(scriptElement.getElement(0).getNumElements() == 0) {
				
				ArrayList<String> tags = new ArrayList<String>();
				
				tags.add("Function");
				tags.add(scriptElement.getElement(0).getContent());
				
				scriptElement = ((FunctionStone) get(tags).get(0)).getFunction();
			}
		}
		
		String script = "<script>";
		
		for(int i = 0; i < scriptElement.getNumElements(); i++)
			script += this.script.process(scriptElement.getElement(i));
		
		script += "</script>";
		
		return script;
	}
}