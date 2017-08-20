package interfaces.machine.utilities;

import java.util.ArrayList;

import interfaces.machine.utilities.commands.flow_control.Break;
import interfaces.machine.utilities.commands.flow_control.Else;
import interfaces.machine.utilities.commands.flow_control.Loop;
import interfaces.machine.utilities.commands.flow_control.Return;
import interfaces.machine.utilities.commands.flow_control.Scope;
import interfaces.machine.utilities.commands.list.At;
import interfaces.machine.utilities.commands.list.List;
import interfaces.machine.utilities.commands.list.Set;
import interfaces.machine.utilities.commands.logic.And;
import interfaces.machine.utilities.commands.logic.Equal;
import interfaces.machine.utilities.commands.logic.False;
import interfaces.machine.utilities.commands.logic.Greater;
import interfaces.machine.utilities.commands.logic.GreaterOrEqual;
import interfaces.machine.utilities.commands.logic.Less;
import interfaces.machine.utilities.commands.logic.LessOrEqual;
import interfaces.machine.utilities.commands.logic.Not;
import interfaces.machine.utilities.commands.logic.Or;
import interfaces.machine.utilities.commands.logic.True;
import interfaces.machine.utilities.commands.math.Add;
import interfaces.machine.utilities.commands.math.Divide;
import interfaces.machine.utilities.commands.math.Modulus;
import interfaces.machine.utilities.commands.math.Multiply;
import interfaces.machine.utilities.commands.math.Subtract;
import interfaces.machine.utilities.commands.operator.Address;
import interfaces.machine.utilities.commands.operator.Cast;
import interfaces.machine.utilities.commands.operator.CharacterType;
import interfaces.machine.utilities.commands.operator.DoubleType;
import interfaces.machine.utilities.commands.operator.FloatType;
import interfaces.machine.utilities.commands.operator.IntegerType;
import interfaces.machine.utilities.commands.operator.LongType;
import interfaces.machine.utilities.commands.operator.Pointer;
import interfaces.machine.utilities.commands.operator.Reference;
import interfaces.machine.utilities.commands.operator.ShortType;
import interfaces.machine.utilities.commands.operator.Size;
import interfaces.machine.utilities.function.Function;
import interfaces.machine.utilities.literal.Literal;
import interfaces.machine.utilities.variable.Variable;
import io.IO;
import kaeon_fusion_legacy.state.function_stone.FunctionStone;
import legacy.fusion.FUSIONStone;
import legacy.one_plus.element.Element;

public class NativeBuilder extends FUSIONStone {
	
	private String directory;
	
	public NativeBuilder(String directory) {
		
		tag("Native Builder");
		
		this.directory = directory;
		
		connectCommands();
	}
	
	public void connectCommands() {

		publiclyConnectMutually(new Function());
		publiclyConnectMutually(new Literal());
		publiclyConnectMutually(new Variable());

		publiclyConnectMutually(new Break());
		publiclyConnectMutually(new Else());
		publiclyConnectMutually(new Loop());
		publiclyConnectMutually(new Return());
		publiclyConnectMutually(new Scope());

		publiclyConnectMutually(new List());
		publiclyConnectMutually(new At());
		publiclyConnectMutually(new Set());
		
		publiclyConnectMutually(new And());
		publiclyConnectMutually(new Equal());
		publiclyConnectMutually(new False());
		publiclyConnectMutually(new Greater());
		publiclyConnectMutually(new GreaterOrEqual());
		publiclyConnectMutually(new Less());
		publiclyConnectMutually(new LessOrEqual());
		publiclyConnectMutually(new Not());
		publiclyConnectMutually(new Or());
		publiclyConnectMutually(new True());
		
		publiclyConnectMutually(new Add());
		publiclyConnectMutually(new Divide());
		publiclyConnectMutually(new Modulus());
		publiclyConnectMutually(new Multiply());
		publiclyConnectMutually(new Subtract());

		publiclyConnectMutually(new Address());
		publiclyConnectMutually(new Cast());
		publiclyConnectMutually(new CharacterType());
		publiclyConnectMutually(new DoubleType());
		publiclyConnectMutually(new FloatType());
		publiclyConnectMutually(new IntegerType());
		publiclyConnectMutually(new LongType());
		publiclyConnectMutually(new Address());
		publiclyConnectMutually(new Pointer());
		publiclyConnectMutually(new Reference());
		publiclyConnectMutually(new ShortType());
		publiclyConnectMutually(new Size());
	}
	
	public boolean onVerify(Element element) {
		
		return
				element.getParent().getContent().equalsIgnoreCase("Define") ||
				element.getContent().equalsIgnoreCase("Define");
	}
	
	public boolean onTrickleDown(Element element) {
		return false;
	}
	
	public Object onProcess(Element element, ArrayList<Object> processed) {
		
		if(!element.getContent().equalsIgnoreCase("Define")) {
			
			String code = generateCode(element, new ArrayList<String>());
			
			System.out.println(code);
			
			IO.save(
					code,
					directory + element.getContent() + ".c");
		}
		
		else
			return generateFunction(element);
		
		return null;
	}
	
	private String generateCode(Element element, ArrayList<String> functions) {
		
		if(element.getNumElements() == 0)
			return "";
		
		String code = "";
		
		code += generateFunction(element);
		
		boolean isRoot = functions.size() == 0;
		
		ArrayList<String> newFunctions = getNewFunctions(functions);
		functions.addAll(newFunctions);
		
		for(int i = 0; i < newFunctions.size(); i++) {

			ArrayList<String> tags = new ArrayList<String>();
			
			tags.add("Function");
			tags.add(newFunctions.get(i));
			
			Element functionElement = ((FunctionStone) get(tags).get(0)).getFunction();
			String newCode = generateCode(functionElement, functions);
			
			if(newCode.indexOf("struct ") == 0)
				code = newCode + code;
			
			else
				code += newCode;
		}
		
		if(isRoot) {
			
			for(int i = 0; i < functions.size(); i++) {

				ArrayList<String> tags = new ArrayList<String>();
				
				tags.add("Function");
				tags.add(newFunctions.get(i));

				Element functionElement = ((FunctionStone) get(tags).get(0)).getFunction();
				String metadata = generateFunctionMetadata(functionElement);
				
				if(metadata.length() > 0 && metadata.indexOf("struct ") != 0)
					code = metadata + ";" + code;
			}
			
			code = formatMacros(code);
		}
		
		return code;
	}
	
	private ArrayList<String> getNewFunctions(ArrayList<String> functions) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Functions");
		
		ArrayList<String> newFunctions = ((Function) get(tags).get(0)).getFunctions();
		
		for(int i = 0; i < newFunctions.size(); i++) {
			
			for(int j = 0; i < functions.size(); j++) {
				
				if(newFunctions.get(i).equals(functions.get(j))) {
					
					newFunctions.remove(i);
					i--;
					
					break;
				}
			}
		}
		
		return newFunctions;
	}
	
	private String formatMacros(String string) {
		
		ArrayList<String> macros = new ArrayList<String>();
		
		while(string.indexOf('\n') > -1) {
			
			String substring = string.substring(0, string.indexOf('\n') + 1);
			int index = substring.lastIndexOf('#');
			
			macros.add(substring.substring(index));
			
			string = string.substring(0, index) + string.substring(string.indexOf('\n') + 1);
		}
		
		for(int i = 0; i < macros.size(); i++)
			string = macros.get(i) + string;
		
		return string;
	}
	
	private String generateFunction(Element element) {
		
		Element copy = copyElement(element);
		
		push();
		
		String function =
				(element.getParent().getParent().getParent() == null ? generateDependencies(copy) : "") +
				generateFunctionGlobals(copy) +
				generateFunctionMetadata(element) +
				"{" +
				generateFunctionContent(copy) +
				"}";
		
		pop();
		
		return function;
	}
	
	private String generateDependencies(Element element) {
		
		String code = "";
		
		ArrayList<Element> use = element.removeElements("Use");
		
		for(int i = 0; i < use.size(); i++) {
			
			code += "#include \"" + use.get(i).getElement(0).getContent() + "\"\n";
			
			code += generateDependencies(use.get(i));
		}
		
		return code;
	}
	
	private String generateFunctionGlobals(Element element) {
		
		String code = "";
		
		ArrayList<Element> globals = element.removeElements("Global");
		
		for(int i = 0; i < globals.size(); i++) {
			
			for(int j = 0; j < globals.get(i).getNumElements(); j++) {
				
				if(!globals.get(i).getElement(j).getContent().equalsIgnoreCase("Meta"))
					code += process(globals.get(i).getElement(j));
			}
		}
		
		return code;
	}
	
	private String generateFunctionMetadata(Element element) {
		
		String code = "";
		
		Element meta = getMeta(element);
		
		if(meta == null)
			code += "void " ;
		
		else {
			
			if(meta.hasElement("Structure")) {
				
				ArrayList<String> tags = new ArrayList<String>();
				tags.add("Functions");
				
				((Function) get(tags).get(0)).addFunction(element.getContent());
				
				return "struct " + element.getContent();
			}
			
			if(meta.hasElement("Used"))
				return "";
			
			if(meta.hasElement("Automatic"))
				code += "auto ";
			
			if(meta.hasElement("External"))
				code += "extern ";
			
			if(meta.hasElement("Static"))
				code += "static ";
			
			if(meta.hasElement("Constant"))
				code += "const ";
			
			if(meta.hasElement("Volatile"))
				code += "volatile ";
			
			if(meta.hasElement("Signed"))
				code += "signed ";
			
			else if(meta.hasElement("Unsigned"))
				code += "unsigned ";
			
			if(meta.hasElement("Integer"))
				code += "int ";
			
			else if(meta.hasElement("Float"))
				code += "float ";
			
			else if(meta.hasElement("Double"))
				code += "double ";
			
			else if(meta.hasElement("Short"))
				code += "short ";
			
			else if(meta.hasElement("Long"))
				code += "long ";
			
			else if(meta.hasElement("Character"))
				code += "char ";
			
			else if(meta.hasElement("Void"))
				code += "void ";
			
			ArrayList<String> tags = new ArrayList<String>();
			tags.add("Functions");
			
			Function function = (Function) get(tags).get(0);
			
			for(int i = 0; i < meta.getNumElements(); i++) {
				
				if(function.isStructure(meta.getElement(i).getContent()))
					code += "struct " + meta.getElement(i).getContent() + " ";
			}
			
			if(meta.hasElement("List")) {
				
				Element list = meta.getElement("List");
				
				if(list != null) {
					
					if(list.getNumElements() == 0)
						return "[]";
					
					for(int i = 0; i < list.getNumElements(); i++)
						code += "[" + process(list.getElement(i)) + "]";
				}
			}
			
			if(meta.hasElement("Pointer")) {
				
				if(meta.getElement("Pointer").getNumElements() > 0) {
					
					for(int i = 0; i < Integer.parseInt(meta.getElement("Pointer").getElement(0).getContent()); i++)
						code += "*";
				}
				
				else
					code += "*";
			}
			
			if(meta.hasElement("Address")) {
				
				if(meta.getElement("Address").getNumElements() > 0) {
					
					for(int i = 0; i < Integer.parseInt(meta.getElement("Address").getElement(0).getContent()); i++)
						code += "&";
				}
				
				else
					code += "&";
			}
		}
		
		code += element.getContent() + "(";
		
		ArrayList<Element> takes = meta.getElements("Takes");
		
		for(int i = 0; i < takes.size(); i++) {
				
			if(takes.get(i).hasElement("Automatic"))
				code += "auto ";
			
			if(takes.get(i).hasElement("External"))
				code += "extern ";
			
			if(takes.get(i).hasElement("Static"))
				code += "static ";
			
			if(takes.get(i).hasElement("Constant"))
				code += "const ";
			
			if(takes.get(i).hasElement("Volatile"))
				code += "volatile ";
			
			if(takes.get(i).hasElement("Signed"))
				code += "signed ";
			
			else if(takes.get(i).hasElement("Unsigned"))
				code += "unsigned ";
			
			if(takes.get(i).hasElement("Integer"))
				code += "int ";
			
			else if(takes.get(i).hasElement("Float"))
				code += "float ";
			
			else if(takes.get(i).hasElement("Double"))
				code += "double ";
			
			else if(takes.get(i).hasElement("Short"))
				code += "short ";
			
			else if(takes.get(i).hasElement("Long"))
				code += "long ";
			
			else if(takes.get(i).hasElement("Character"))
				code += "char ";
			
			else
				code += "void ";
			
			
			
			if(takes.get(i).hasElement("List"))
				code += "[]";
			
			if(takes.get(i).hasElement("Pointer")) {
				
				if(takes.get(i).getElement("Pointer").getNumElements() > 0) {
					
					for(int j = 0; j < Integer.parseInt(takes.get(i).getElement("Pointer").getElement(0).getContent()); j++)
						code += "*";
				}
				
				else
					code += "*";
			}
			
			if(takes.get(i).hasElement("Address")) {
				
				if(takes.get(i).getElement("Address").getNumElements() > 0) {
					
					for(int j = 0; j < Integer.parseInt(takes.get(i).getElement("Address").getElement(0).getContent()); j++)
						code += "&";
				}
				
				else
					code += "&";
			}
			
			code += "arguments" + (i + 1) + (i < takes.size() - 1 ? "," : "");
		}
		
		return code + ")";
	}
	
	public Element getMeta(Element element) {
		
		Element current = element;
		int position = getElementPosition(current);
		
		while(position != -1) {
			
			current = element.getParent().getElement(position);
			
			if(current.getContent().equalsIgnoreCase("Meta"))
				return current;
			
			position--;
		}
		
		return null;
	}
	
	private int getElementPosition(Element element) {
		
		Element parent = element.getParent();
		
		for(int i = 0; i < parent.getNumElements(); i++) {
			
			if(parent.getElement(i) == element)
				return i;
		}
		
		return 0;
	}
	
	private String generateFunctionContent(Element element) {
		
		String code = "";
		
		for(int i = 0; i < element.getNumElements(); i++) {
			
			if(!element.getElement(i).getContent().equalsIgnoreCase("Meta"))
				code += process(element.getElement(i));
		}
		
		return code;
	}
	
	private Element copyElement(Element element) {
		
		Element copy = new Element(element.getContent());
		
		for(int i = 0; i < element.getNumElements(); i++)
			copy.addElement(copyElement(element.getElement(i)));
		
		return copy;
	}
	
	public void push() {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Variable");
		
		((Variable) get(tags).get(0)).push();
	}
	
	public void pop() {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Variable");
		
		((Variable) get(tags).get(0)).pop();
	}
}