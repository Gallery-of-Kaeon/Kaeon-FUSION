package standard.commands.build;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.utilities.state.Alias;
import standard.utilities.state.State;

public class Build extends FUSIONUnit {
	
	public State state;
	
	public Build() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equals("Build");
	}
	
	public boolean trickleDown(Element element) {
		return false;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		ArrayList<Alias> functionAliases = state.getByType("FUNCTION");
		
		ArrayList<ArrayList<Object>> functionDefinitions = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> functions = new ArrayList<ArrayList<Object>>();
		
		for(Alias functionAlias : functionAliases) {
			
			ArrayList<Object> functionDefinition = new ArrayList<Object>();
			
			functionDefinition.add(functionAlias.alias);
			functionDefinition.add(functionAlias.object);
			
			functionDefinitions.add(functionDefinition);
		}
		
		for(Element functionElement : element.children.get(0).children) {
			
			ArrayList<Object> function = new ArrayList<Object>();
			
			function.add(functionElement.content);
			
			function.add(
					state.getByAliasAndType(
							functionElement.content,
							"FUNCTION"));
			
			functions.add(function);
		}
		
		ArrayList<String> arguments = new ArrayList<String>();
		
		for(int i = 1; i < element.children.size(); i++)
			arguments.add(element.children.get(i).content);
		
		PhilosophersStoneUtilities.call(
				this,
				"Build",
				element.children.get(0).content,
				functionDefinitions,
				functions,
				arguments);
		
		return null;
	}
}