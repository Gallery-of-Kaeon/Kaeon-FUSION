package kaeon_fusion_legacy.state.function_stone;

import legacy.one_plus.element.Element;
import legacy.philosophers_stone_plus.PhilosophersStonePlus;

public class FunctionStone extends PhilosophersStonePlus {
	
	private Element function;
	
	public FunctionStone(Element function) {
		
		tag("Function");
		tag(function.getContent());
		
		setFunction(function);
	}
	
	public FunctionStone(FunctionStone stone) {
		
		tag("Function");
		tag(stone.getTags().get(1));
		
		setFunction(stone.getFunction());
	}
	
	public void setFunction(Element function) {
		this.function = function;
	}
	
	public Element getFunction() {
		return function;
	}
}