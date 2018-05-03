package standard_kaeon_fusion.utilities.state;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class State extends FUSIONUnit {
	
	public ArrayList<Alias> global = new ArrayList<Alias>();
	
	public ArrayList<ArrayList<Alias>> state =
			new ArrayList<ArrayList<Alias>>();
	
	public ArrayList<ArrayList<State>> inStates =
			new ArrayList<ArrayList<State>>();
	
	public State() {

		tags.add("Standard");
		tags.add("State");
		
		state.add(new ArrayList<Alias>());
		inStates.add(new ArrayList<State>());
	}
	
	public State(ArrayList<Object> list) {

		tags.add("Standard");
		tags.add("State");
		
		generateFromArrayList(list);
	}
	
	public boolean verify(
			Element element) {
		
		return true;
	}
	
	public boolean trickleDown(
			Element element) {
		
		state.add(new ArrayList<Alias>());
		inStates.add(new ArrayList<State>());
		
		return true;
	}
	
	public Element jump(
			Element element,
			ArrayList<Object> processed) {
		
		if(state.size() > 1)
			state.remove(state.size() - 1);
		
		if(inStates.size() > 1)
			inStates.remove(inStates.size() - 1);
		
		return null;
	}
	
	public void setAlias(Alias alias) {
		
		for(int i = 0; i < inStates.size(); i++) {
			
			for(int j = 0; j < inStates.get(i).size(); j++)
				inStates.get(i).get(j).setAlias(alias);
		}
		
		for(int i = global.size() - 1; i >= 0; i--) {
			
			if(global.get(i).alias.equalsIgnoreCase(alias.alias)) {
				
				global.get(i).object = alias.object;
				
				return;
			}
		}
		
		for(int i = state.size() - 1; i >= 0; i--) {
			
			for(int j = state.get(i).size() - 1; j >= 0; j--) {
				
				if(state.get(i).get(j).alias.equalsIgnoreCase(alias.alias)) {
					
					state.get(i).get(j).object = alias.object;
					
					return;
				}
			}
		}
		
		state.get(state.size() - 2).add(alias);
	}
	
	public void setGlobalAlias(Alias alias) {
		
		for(int i = 0; i < inStates.size(); i++) {
			
			for(int j = 0; j < inStates.get(i).size(); j++)
				inStates.get(i).get(j).setGlobalAlias(alias);
		}
		
		boolean found = false;
		
		for(int i = state.size() - 1; i >= 0 && !found; i--) {
			
			for(int j = state.get(i).size() - 1; j >= 0 && !found; j--) {
				
				if(state.get(i).get(j).alias.equalsIgnoreCase(alias.alias)) {
					
					state.get(i).remove(j);
					
					found = true;
				}
			}
		}
		
		for(int i = global.size() - 1; i >= 0; i--) {
			
			if(global.get(i).alias.equalsIgnoreCase(alias.alias)) {
				
				global.get(i).object = alias.object;
				
				return;
			}
		}
		
		global.add(alias);
	}
	
	public Object getByAlias(String alias) {
		
		for(int i = 0; i < inStates.size(); i++) {
			
			for(int j = 0; j < inStates.get(i).size(); j++) {
				
				Object object = inStates.get(i).get(j).getByAlias(alias);
				
				if(object != null)
					return object;
			}
		}
		
		for(int i = global.size() - 1; i >= 0; i--) {
			
			if(global.get(i).alias.equalsIgnoreCase(alias))
				return global.get(i).object;
		}
		
		for(int i = state.size() - 1; i >= 0; i--) {
			
			for(int j = state.get(i).size() - 1; j >= 0; j--) {
				
				if(state.get(i).get(j).alias.equalsIgnoreCase(alias))
					return state.get(i).get(j).object;
			}
		}
		
		return null;
	}
	
	public Object getByAliasAndType(String alias, String type) {
		
		for(int i = 0; i < inStates.size(); i++) {
			
			for(int j = 0; j < inStates.get(i).size(); j++) {
				
				Object object = inStates.get(i).get(j).getByAliasAndType(alias, type);
				
				if(object != null)
					return object;
			}
		}
		
		for(int i = global.size() - 1; i >= 0; i--) {
			
			if(global.get(i).alias.equalsIgnoreCase(alias) &&
					global.get(i).type.equalsIgnoreCase(type)) {
				
				return global.get(i).object;
			}
		}
		
		for(int i = state.size() - 1; i >= 0; i--) {
			
			for(int j = state.get(i).size() - 1; j >= 0; j--) {
				
				if(state.get(i).get(j).alias.equalsIgnoreCase(alias) &&
						state.get(i).get(j).type.equalsIgnoreCase(type)) {
					
					return state.get(i).get(j).object;
				}
			}
		}
		
		return null;
	}
	
	public ArrayList<Alias> getByType(String type) {
		
		ArrayList<Alias> aliases = new ArrayList<Alias>();
		
		for(int i = 0; i < inStates.size(); i++) {
			
			for(int j = 0; j < inStates.get(i).size(); j++)
				aliases.addAll(inStates.get(i).get(j).getByType(type));
		}
		
		for(int i = global.size() - 1; i >= 0; i--) {
			
			if(global.get(i).type.equalsIgnoreCase(type))
				aliases.add(global.get(i));
		}
		
		for(int i = state.size() - 1; i >= 0; i--) {
			
			for(int j = state.get(i).size() - 1; j >= 0; j--) {
				
				if(state.get(i).get(j).type.equalsIgnoreCase(type))
					aliases.add(state.get(i).get(j));
			}
		}
		
		return aliases;
	}
	
	public boolean hasAlias(String alias) {
		
		for(int i = 0; i < inStates.size(); i++) {
			
			for(int j = 0; j < inStates.get(i).size(); j++) {
				
				boolean has = inStates.get(i).get(j).hasAlias(alias);
				
				if(has)
					return true;
			}
		}
		
		for(int i = global.size() - 1; i >= 0; i--) {
			
			if(global.get(i).alias.equalsIgnoreCase(alias))
				return true;
		}
		
		for(int i = state.size() - 1; i >= 0; i--) {
			
			for(int j = state.get(i).size() - 1; j >= 0; j--) {
				
				if(state.get(i).get(j).alias.equalsIgnoreCase(alias))
					return true;
			}
		}
		
		return false;
	}
	
	public void push() {
		
		for(int i = 0; i < inStates.size(); i++) {
			
			for(int j = 0; j < inStates.get(i).size(); j++)
				inStates.get(i).get(j).push();
		}
		
		state.add(new ArrayList<Alias>());
		inStates.add(new ArrayList<State>());
	}
	
	public void pop() {
		
		state.remove(state.size() - 1);
		inStates.remove(inStates.size() - 1);
		
		for(int i = 0; i < inStates.size(); i++) {
			
			for(int j = 0; j < inStates.get(i).size(); j++)
				inStates.get(i).get(j).pop();
		}
	}
	
	public State copy() {
		
		State copy = new State();
		
		copy.global = new ArrayList<Alias>(global);
		
		copy.state = new ArrayList<ArrayList<Alias>>();
		
		for(int i = 0; i < state.size(); i++)
			copy.state.add(new ArrayList<Alias>(state.get(i)));
		
		return copy;
	}
	
	public ArrayList<Object> toArrayList() {
		
		ArrayList<Object> list = new ArrayList<Object>();
		
		ArrayList<Object> globalList = new ArrayList<Object>();
		
		for(int i = 0; i < global.size(); i++) {
			
			ArrayList<Object> alias = new ArrayList<Object>();
			
			alias.add(global.get(i).alias);
			alias.add(global.get(i).object);
			
			globalList.add(alias);
		}
		
		ArrayList<Object> stateList = new ArrayList<Object>();
		
		for(int i = 0; i < state.size(); i++) {
			
			ArrayList<Object> stack = new ArrayList<Object>();
			
			for(int j = 0; j < state.get(i).size(); j++) {
				
				ArrayList<Object> alias = new ArrayList<Object>();
				
				alias.add(state.get(i).get(j).alias);
				alias.add(state.get(i).get(j).object);
				
				stack.add(alias);
			}
			
			stateList.add(stack);
		}
		
		list.add(globalList);
		list.add(stateList);
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public void generateFromArrayList(ArrayList<Object> list) {
		
		global = new ArrayList<Alias>();
		state = new ArrayList<ArrayList<Alias>>();
		
		ArrayList<Object> globalList = (ArrayList<Object>) list.get(0);
		
		for(int i = 0; i < globalList.size(); i++) {
			
			ArrayList<Object> aliasList = (ArrayList<Object>) globalList.get(i);
			Alias alias = new Alias();
			
			alias.alias = "" + aliasList.get(0);
			alias.object = aliasList.get(1);
			
			global.add(alias);
		}
		
		ArrayList<Object> stateList = (ArrayList<Object>) list.get(1);
		
		for(int i = 0; i < stateList.size(); i++) {

			ArrayList<Object> stackList = (ArrayList<Object>) stateList.get(i);
			ArrayList<Alias> stack = new ArrayList<Alias>();
			
			for(int j = 0; j < stackList.size(); j++) {

				ArrayList<Object> aliasList = (ArrayList<Object>) stackList.get(i);
				Alias alias = new Alias();
				
				alias.alias = "" + aliasList.get(0);
				alias.object = aliasList.get(1);
				
				stack.add(alias);
			}
			
			state.add(stack);
		}
	}
}