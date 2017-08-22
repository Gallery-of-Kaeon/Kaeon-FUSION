package kaeon_fusion.state.variable_stone;

import philosophers_stone_plus.PhilosophersStonePlus;

public class VariableStone extends PhilosophersStonePlus {
	
	private Object value;
	
	public VariableStone(String name, Object value) {
		
		tag("Variable");
		tag(name);
		
		setValue(value);
	}
	
	public VariableStone(VariableStone stone) {
		
		tag("Variable");
		tag(stone.getTags().get(1));
		
		setValue(stone.getValue());
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
}