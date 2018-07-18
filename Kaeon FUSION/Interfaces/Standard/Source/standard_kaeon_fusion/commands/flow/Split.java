package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.FUSIONUtilities;
import standard_kaeon_fusion.utilities.Stopper;
import standard_kaeon_fusion.utilities.state.State;

public class Split extends FUSIONUnit implements Runnable {
	
	public FUSION fusion;
	public Element element;
	public State state;
	
	public Split() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
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
		
		ArrayList<PhilosophersStone> atlas = PhilosophersStoneUtilities.getAtlas(functionFUSION);
		
		for(PhilosophersStone stone : atlas) {
			
			if(PhilosophersStoneUtilities.isTagged(stone, "State"))
				PhilosophersStoneUtilities.disconnectMutually(functionFUSION, stone);
		}
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, state.copy());
		
		split.fusion = functionFUSION;
		
		Element newElement = ElementUtilities.copyElement(element);
		newElement.content = "";
		
		split.element = newElement;
		
		((Stopper) PhilosophersStoneUtilities.get(functionFUSION, "Stopper").get(0)).fusion.add(functionFUSION);
		
		new Thread(split).start();
		
		return null;
	}

	public void run() {
		
		fusion.process(element);
		
		PhilosophersStoneUtilities.destroy(fusion);
	}
}