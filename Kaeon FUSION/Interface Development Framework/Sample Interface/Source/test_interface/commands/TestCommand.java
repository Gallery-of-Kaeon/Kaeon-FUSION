package test_interface.commands;
import java.util.ArrayList;

import kaeon_fusion.command.Command;
import one.Element;

public class TestCommand extends Command {
	
	public boolean onCommandVerify(Element element) {
		return element.content.equalsIgnoreCase("Test Command");
	}
	
	public boolean onCommandTrickleDown(Element element) {
		return true;
	}
	
	public Object onCommandProcess(Element element, ArrayList<Object> processed) {
		return "Test Successful";
	}
}