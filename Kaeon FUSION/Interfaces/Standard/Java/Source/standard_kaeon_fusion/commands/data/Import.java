package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import io.IO;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.Alias;
import standard_kaeon_fusion.utilities.state.State;

public class Import extends FUSIONUnit {
	
	public State state;
	
	public Import() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		
		try {
			
			if(state == null)
				state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		}
		
		catch(Exception exception) {
			
		}
		
		return element.content.equalsIgnoreCase("Import");
	}
	
	public double getPriority(Element element) {
		return -2;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
			
		for(Object object : processed) {
			
			ArrayList<Element> definitions =
					ElementUtilities.getChildren(
							ONEPlus.parseONEPlus(IO.openAsString("" + object)),
							"Define");
			
			for(Element definition : definitions) {
				
				for(Element child : definition.children) {
					
					try {

						String alias = child.content;
						
						Element function = ElementUtilities.copyElement(child);
						function.content = null;
						
						Alias functionAlias = new Alias();
						
						functionAlias.type = "FUNCTION";
						functionAlias.alias = alias;
						functionAlias.object = function;
						
						state.setAlias(functionAlias);
					}
					
					catch(Exception exception) {
						
					}
				}
			}
		}
		
		return null;
	}
}