package standard_kaeon_fusion.commands.data;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.state.State;

public class Generate extends FUSIONUnit {
	
	public Generate() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Generate");
	}
	
	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {
		
		try {
			
			return
					new State(
							(ArrayList<Object>)
									PhilosophersStoneUtilities.call(
											this,
											"Generate",
											"" + processed.get(0)).get(0));
		}
		
		catch(Exception exception) {
			
		}
		
		return new State();
	}
}