package test;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Test extends FUSIONUnit {
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Test");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		return "Test Successful";
	}
}