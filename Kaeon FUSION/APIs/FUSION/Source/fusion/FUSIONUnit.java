package fusion;

import java.util.ArrayList;

import one.Element;
import philosophers_stone.PhilosophersStone;

public class FUSIONUnit extends PhilosophersStone {
	
	public FUSIONUnit() {
		tags.add("FUSION Unit");
	}
	
	public boolean deny(Element element) {
		return false;
	}
	
	public boolean verify(Element element) {
		return false;
	}
	
	public boolean trickleDown(Element element) {
		return true;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return null;
	}
	
	public boolean isAdded(Element element, ArrayList<Object> processed) {
		return true;
	}
	
	public boolean terminate(Element element, ArrayList<Object> processed) {
		return false;
	}
	
	public Element jump(Element element, ArrayList<Object> processed) {
		return null;
	}
	
	public void handleError(Element element, ArrayList<Object> processed, Exception exception) {
		
	}
}