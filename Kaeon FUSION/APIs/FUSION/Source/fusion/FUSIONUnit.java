package fusion;

import java.util.ArrayList;

import one.Element;
import philosophers_stone.PhilosophersStone;

public class FUSIONUnit extends PhilosophersStone {
	
	public FUSIONUnit() {
		tags.add("FUSION Command");
	}
	
	public boolean deny(Element element) {
		return false;
	}
	
	public boolean verify(Element element) {
		return false;
	}
	
	public double getPriority(Element element) {
		return 0;
	}
	
	public boolean trickleDown(Element element) {
		return true;
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return null;
	}
	
	public Element jump(Element element, ArrayList<Object> processed) {
		return null;
	}
	
	public int changeDepth(Element element, ArrayList<Object> processed, int currentDepth) {
		return currentDepth;
	}
	
	public void handleError(Element element, Exception exception) {
		
	}
}