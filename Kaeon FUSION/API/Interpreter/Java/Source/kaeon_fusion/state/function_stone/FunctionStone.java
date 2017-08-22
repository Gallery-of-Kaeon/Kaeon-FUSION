package kaeon_fusion.state.function_stone;

import one_plus.element.Element;
import philosophers_stone_plus.PhilosophersStonePlus;

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