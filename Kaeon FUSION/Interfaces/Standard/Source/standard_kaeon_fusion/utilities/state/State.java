package standard_kaeon_fusion.utilities.state;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class State extends FUSIONUnit {
	
	public ArrayList<Alias> global = new ArrayList<Alias>();
	
	public ArrayList<ArrayList<Alias>> state =
			new ArrayList<ArrayList<Alias>>();
	
	public State() {

		tags.add("Standard");
		tags.add("State");
		
		state.add(new ArrayList<Alias>());
	}
	
	public boolean verify(
			Element element) {
		
		return true;
	}
	
	public boolean trickleDown(
			Element element) {
		
		state.add(new ArrayList<Alias>());
		
		return true;
	}
	
	public Element jump(
			Element element,
			ArrayList<Object> processed) {
		
		if(state.size() > 1)
			state.remove(state.size() - 1);
		
		return null;
	}
	
	public void setAlias(Alias alias) {
		
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
		state.add(new ArrayList<Alias>());
	}
	
	public void pop() {
		state.remove(state.size() - 1);
	}
	
	public State copy() {
		
		State copy = new State();
		
		copy.global = new ArrayList<Alias>(global);
		
		copy.state = new ArrayList<ArrayList<Alias>>();
		
		for(int i = 0; i < state.size(); i++)
			copy.state.add(new ArrayList<Alias>(state.get(i)));
		
		return copy;
	}
}