package dev.ide;

import java.util.ArrayList;

import kaeon_fusion_legacy.KaeonFUSION;
import kaeon_fusion_legacy.console.Console;

public class DevKaeonFUSION extends KaeonFUSION {
	
	public Console getConsole() {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Console");
		
		if(has(tags))
			return (Console) get(tags).get(0);
		
		return new DevConsole();
	}
}