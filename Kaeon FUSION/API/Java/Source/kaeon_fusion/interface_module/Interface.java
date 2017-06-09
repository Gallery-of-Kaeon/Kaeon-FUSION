package kaeon_fusion.interface_module;

import philosophers_stone_plus.PhilosophersStonePlus;

public class Interface extends PhilosophersStonePlus {

	public Interface() {
		tag("Interface");
		tag(getName());
	}
	
	public String getName() {
		return "Name";
	}
	
	public void onStart() {
		
	}
	
	public Interface clone() {
		return new Interface();
	}
}