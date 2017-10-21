package standard.commands.flow;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.utilities.FUSIONUtilities;
import standard.utilities.state.State;

public class Split extends FUSIONUnit implements Runnable {
	
	public FUSION fusion;
	public Element element;
	
	public Split() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Split");
	}
	
	public boolean trickleDown(Element element) {
		return false;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		Split split = new Split();
		
		FUSION functionFUSION =
				FUSIONUtilities.copy(
						(FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0));
		
		for(int i = 0; i < functionFUSION.publicConnections.size(); i++) {
			
			PhilosophersStone stone = functionFUSION.publicConnections.get(i);
			
			if(PhilosophersStoneUtilities.isTagged(stone, "State")) {
				
				PhilosophersStoneUtilities.publiclyConnect(functionFUSION, ((State) stone).copy());
				
				PhilosophersStoneUtilities.disconnectMutually(functionFUSION, stone);
			}
		}
		
		for(int i = 0; i < functionFUSION.privateConnections.size(); i++) {
			
			PhilosophersStone stone = functionFUSION.privateConnections.get(i);
			
			if(PhilosophersStoneUtilities.isTagged(stone, "State")) {
				
				PhilosophersStoneUtilities.publiclyConnect(functionFUSION, ((State) stone).copy());
				
				PhilosophersStoneUtilities.disconnectMutually(functionFUSION, stone);
			}
		}
		
		split.fusion = functionFUSION;
		
		Element newElement = ElementUtilities.copyElement(element);
		newElement.content = null;
		
		split.element = newElement;
		
		new Thread(split).start();
		
		return null;
	}

	public void run() {
		
		fusion.process(element);
		
		PhilosophersStoneUtilities.destroy(fusion);
	}
}