package legacy.web.utilities.script;

import java.util.ArrayList;

import legacy.kaeon_fusion_legacy.state.function_stone.FunctionStone;
import legacy.utilities.fusion.FUSIONStone;
import legacy.utilities.one_plus.element.Element;
import legacy.web.utilities.script.commands.flow_control.Break;
import legacy.web.utilities.script.commands.flow_control.Loop;
import legacy.web.utilities.script.commands.flow_control.Return;
import legacy.web.utilities.script.commands.flow_control.Scope;
import legacy.web.utilities.script.commands.logic.And;
import legacy.web.utilities.script.commands.logic.Equal;
import legacy.web.utilities.script.commands.logic.Greater;
import legacy.web.utilities.script.commands.logic.GreaterOrEqual;
import legacy.web.utilities.script.commands.logic.Less;
import legacy.web.utilities.script.commands.logic.LessOrEqual;
import legacy.web.utilities.script.commands.logic.Not;
import legacy.web.utilities.script.commands.logic.Or;
import legacy.web.utilities.script.commands.math.Add;
import legacy.web.utilities.script.commands.math.Divide;
import legacy.web.utilities.script.commands.math.Modulus;
import legacy.web.utilities.script.commands.math.Multiply;
import legacy.web.utilities.script.commands.math.Subtract;
import legacy.web.utilities.script.function.Function;
import legacy.web.utilities.script.literal.Literal;
import legacy.web.utilities.script.statement.Statement;
import legacy.web.utilities.script.variable.Global;
import legacy.web.utilities.script.variable.Variable;

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