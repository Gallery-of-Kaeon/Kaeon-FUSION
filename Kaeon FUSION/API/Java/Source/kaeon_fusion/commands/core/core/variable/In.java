package kaeon_fusion.commands.core.core.variable;

import java.util.ArrayList;

import kaeon_fusion.KaeonFUSION;
import kaeon_fusion.commands.Command;
import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

public class In extends Command {
	
	public boolean onVerify(Element element) {
		return element.getContent().equalsIgnoreCase("In");
	}
	
	public Object onCommand(Element element, ArrayList<Object> processed) {
		
		Element function = new Element();
		
		for(int i = getElementPosition(element) + 1; i < element.getParent().getNumElements(); i++) {
			
			function.addElement(element.getParent().removeElement(i));
			
			i--;
		}
		
		KaeonFUSION fusion = (KaeonFUSION) processed.get(0);
		PhilosophersStonePlus oldData = getOldData(fusion);
		
		fusion.publiclyConnect(oldData);
		
		Object object = processObject(function, fusion);

		fusion.disconnect(oldData);
		
		return object;
	}
	
	private PhilosophersStonePlus getOldData(KaeonFUSION fusion) {
		
		PhilosophersStonePlus oldData = new PhilosophersStonePlus();
		
		addStones(fusion, oldData, "Variable");
		addStones(fusion, oldData, "Function");
		addStones(fusion, oldData, "Interface");
		addStones(fusion, oldData, "Native");
		addStones(fusion, oldData, "Console");
		
		return oldData;
	}
	
	private void addStones(KaeonFUSION fusion, PhilosophersStonePlus oldData, String tag) {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add(tag);
		
		ArrayList<PhilosophersStonePlus> stones = get(tags);
		
		for(int i = 0; i < stones.size(); i++) {
			
			if(!fusion.has(stones.get(i).getTags()))
				oldData.publiclyConnectMutually(stones.get(i));
		}
	}
	
	private Object processObject(Element element, KaeonFUSION object) {

		Element code = generateObjectElement(element);
		Object returned = object.process(code, new ArrayList<Object>());
		
		if(object.hasErrorOccured())
			getKaeonFUSION().triggerError(true);
		
		return returned;
	}
	
	private Element generateObjectElement(Element element) {
		
		Element objectElement = new Element();
		
		for(int i = 0; i < element.getNumElements(); i++)
			objectElement.addElement(copyElement(element.getElement(i)));
		
		return objectElement;
	}
	
	private Element copyElement(Element element) {
		
		Element copy = new Element();
		
		copy.setContent(element.getContent());
		
		for(int i = 0; i < element.getNumElements(); i++)
			copy.addElement(copyElement(element.getElement(i)));
		
		return copy;
	}
	
	private int getElementPosition(Element element) {
		
		Element parent = element.getParent();
		
		if(parent != null) {
			
			for(int i = 0; i < parent.getNumElements(); i++) {
				
				if(parent.getElement(i) == element)
					return i;
			}
		}
		
		return -1;
	}
}