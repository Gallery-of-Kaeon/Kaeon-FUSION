package aether_test;

import java.util.ArrayList;

import one_plus.directive.DirectiveUnit;
import test.Test;

public class DirectiveInterface {
	
	public static ArrayList<DirectiveUnit> getDirectives() {
		
		ArrayList<DirectiveUnit> directiveUnits = new ArrayList<DirectiveUnit>();
		
		directiveUnits.add(new Test());
		
		return directiveUnits;
	}
}