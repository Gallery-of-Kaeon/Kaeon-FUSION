package standard_kaeon_fusion.commands.data;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import fusion.FUSION;
import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.commands.flow.Catch;
import standard_kaeon_fusion.commands.flow.FUSIONException;
import standard_kaeon_fusion.commands.flow.Return;
import standard_kaeon_fusion.commands.list.ElementToList;
import standard_kaeon_fusion.commands.list.ListToElement;
import standard_kaeon_fusion.utilities.FUSIONUtilities;
import standard_kaeon_fusion.utilities.Stopper;
import standard_kaeon_fusion.utilities.state.State;

public class Reflect extends FUSIONUnit {

	public FUSION fusion;
	public State state;
	
	public Reflect() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Reflect");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(state == null)
			state = (State) PhilosophersStoneUtilities.get(this, "State").get(0);
		
		if(fusion == null)
			fusion = (FUSION) PhilosophersStoneUtilities.get(this, "FUSION").get(0);
		
		Element deny = getAlias(processed.get(0));
		Element verify = getAlias(processed.get(1));
		Element trickleDown = getAlias(processed.get(2));
		Element process = getAlias(processed.get(3));
		Element terminate = getAlias(processed.get(4));
		Element isAdded = getAlias(processed.get(5));
		Element jump = getAlias(processed.get(6));
		Element handleError = getAlias(processed.get(7));
		
		FUSIONUnit unit = new FUSIONUnit() {
			
			public boolean deny(Element element) {
				
				if(deny == null)
					return false;
				
				return Boolean.parseBoolean("" + processFunction(deny, elementToList(element)));
			}
			
			public boolean verify(Element element) {
				
				if(verify == null)
					return false;
				
				return Boolean.parseBoolean("" + processFunction(verify, elementToList(element)));
			}
			
			public boolean trickleDown(Element element) {
				
				if(trickleDown == null)
					return true;
				
				return Boolean.parseBoolean("" + processFunction(trickleDown, elementToList(element)));
			}
			
			public Object process(Element element, ArrayList<Object> processed) {
				
				if(process == null)
					return null;
				
				return processFunction(process, elementToList(element), processed);
			}
			
			public boolean terminate(Element element, ArrayList<Object> processed) {
				
				if(terminate == null)
					return false;
				
				return Boolean.parseBoolean("" + processFunction(terminate, elementToList(element), processed));
			}
			
			public boolean isAdded(Element element, ArrayList<Object> processed) {
				
				if(isAdded == null)
					return true;
				
				return Boolean.parseBoolean("" + processFunction(isAdded, elementToList(element), processed));
			}
			
			@SuppressWarnings("unchecked")
			public Element jump(Element element, ArrayList<Object> processed) {
				
				if(jump == null)
					return null;
				
				Object object = processFunction(jump, elementToList(element), processed);
				
				if(object == null)
					return null;
				
				return listToElement((ArrayList<Object>) object);
			}
			
			public void handleError(Element element, ArrayList<Object> processed, Exception exception) {
				
				if(handleError == null)
					return;
				
				String error = "";
				
				if(exception != null) {
					
					StringWriter errors = new StringWriter();
					exception.printStackTrace(new PrintWriter(errors));
					
					error = errors.toString();
				}
				
				processFunction(handleError, elementToList(element), processed, error);
			}
		};
		
		unit.tags.add("Standard");
		unit.tags.add("Reflect");
		
		PhilosophersStoneUtilities.publiclyConnectMutually(fusion, unit);
		fusion.update();
		
		return null;
	}
	
	public Element getAlias(Object alias) {
		
		Element element = null;
		
		if(alias != null) {
			
			element = ElementUtilities.copyElement((Element) state.getByAliasAndType("" + alias, "FUNCTION"));
			
			element.parent = null;
			element.content = "";
		}
		
		return element;
	}
	
	public ArrayList<Object> elementToList(Element element) {
		
		ArrayList<Object> indexes = new ArrayList<Object>();
		Element root = element;
		
		while(root.parent != null) {
			
			indexes.add(0, ElementUtilities.getIndex(root) + 1);
			
			root = root.parent;
		}
		
		return new ArrayList<Object>(Arrays.asList(ElementToList.elementToList(root), indexes));
	}
	
	@SuppressWarnings("unchecked")
	public Element listToElement(ArrayList<Object> list) {
		
		Element element = ListToElement.listToElement((ArrayList<Object>) list.get(0));
		ArrayList<Object> indexes = (ArrayList<Object>) list.get(1);
		
		for(int i = 0; i < indexes.size(); i++)
			element = element.children.get(Integer.parseInt("" + indexes.get(i)) - 1);
		
		return element;
	}
	
	public Object processFunction(Element element, Object... args) {
		
		ArrayList<Object> processed = new ArrayList<Object>(Arrays.asList(args));
		
		FUSION functionFUSION = FUSIONUtilities.copy(fusion);
		
		ArrayList<PhilosophersStone> atlas = PhilosophersStoneUtilities.getAtlas(functionFUSION);
		
		for(PhilosophersStone stone : atlas) {
			
			if(PhilosophersStoneUtilities.isTagged(stone, "State") ||
					PhilosophersStoneUtilities.isTagged(stone, "Arguments")) {
				
				PhilosophersStoneUtilities.disconnectMutually(functionFUSION, stone);
			}
		}
		
		State newState = new State();
		
		newState.global.addAll(state.global);
		
		for(int i = 0; i < state.inStates.size(); i++) {
			
			for(int j = 0; j < state.inStates.get(i).size(); j++)
				newState.global.addAll(state.inStates.get(i).get(j).global);
		}
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, newState);
		
		PhilosophersStone argumentStone = new PhilosophersStone() {
			
			public Object onCall(ArrayList<Object> packet) {
				return ((String) packet.get(0)).equalsIgnoreCase("Arguments") ? processed : null;
			}
		};
		
		PhilosophersStoneUtilities.publiclyConnect(functionFUSION, argumentStone);
		
		argumentStone.tags.add("Arguments");
		
		((Stopper) PhilosophersStoneUtilities.get(functionFUSION, "Stopper").get(0)).fusion.add(functionFUSION);
		
		functionFUSION.internalProcess(element, false);
		
		Object toReturn = null;
		
		try {
			toReturn = ((Return) PhilosophersStoneUtilities.get(functionFUSION, "Return").get(0)).toReturn;
		}
		
		catch(Exception exception) {
			
		}
		
		Catch localCatch = (Catch) PhilosophersStoneUtilities.get(this, "Catch").get(0);
		Catch functionCatch = (Catch) PhilosophersStoneUtilities.get(functionFUSION, "Catch").get(0);
		
		FUSIONException localException = (FUSIONException) PhilosophersStoneUtilities.get(this, "Exception").get(0);
		FUSIONException functionException = (FUSIONException) PhilosophersStoneUtilities.get(functionFUSION, "Exception").get(0);
		
		if(!localCatch.caught)
			localCatch.caught = functionCatch.caught;
		
		localException.exception = functionException.exception;
		
		PhilosophersStoneUtilities.destroy(functionFUSION);
		
		return toReturn;
	}
}