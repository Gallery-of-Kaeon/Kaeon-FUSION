package dev.ide;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import kaeon_fusion_legacy.console.Console;

public class DevConsole extends Console {
	
	private static JTextArea console;
	
	public static void setConsole(JTextArea newConsole) {
		
		if(console == null)
			console = newConsole;
	}
	
	public void consoleOut(ArrayList<Object> data) {
		
		if(console == null) {
			
			for(int i = 0; i < data.size(); i++) {
				
				if(data.get(i) != null)
					System.out.println(data.get(i));
			}
			
			return;
		}
		
		String log = console.getText();
		
		for(int i = 0; i < data.size(); i++) {
			
			if(data.get(i) != null)
				log += data.get(i);
		}
		
		console.setText(log);
	}
	
	public String consoleIn(ArrayList<Object> data) {
		
		String log = "";
		
		for(int i = 0; i < data.size(); i++) {
			
			if(data.get(i) != null)
				log += data.get(i);
		}
		
		if(console != null)
			return JOptionPane.showInputDialog(console, log);
		
		return JOptionPane.showInputDialog(null, log);
	}
}