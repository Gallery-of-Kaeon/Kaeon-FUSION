package updater;

import java.io.File;
import java.util.ArrayList;

import io.IO;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;

public class Updater {
	
	public static void main(String[] args) {
		
		Element update = ONEPlus.parseONEPlus(IO.openAsString(args[0]));
		
		ArrayList<String> paths = new ArrayList<String>();
		ArrayList<String> commands = new ArrayList<String>();
		
		int mode = 0;
		
		for(int i = 1; i < args.length; i++) {
			
			if(args[i].equalsIgnoreCase("path")) {
				
				mode = 1;
				
				continue;
			}
			
			if(args[i].equalsIgnoreCase("command")) {
				
				mode = 2;
				
				continue;
			}
			
			if(mode == 1)
				paths.add(args[i]);
			
			if(mode == 2)
				commands.add(args[i]);
		}
		
		Element previousUpdates = Updater.getPreviousUpdates();
		
		update(update, paths, previousUpdates);
		
		for(String command : commands) {
			
			try {
				Runtime.getRuntime().exec(command);
			}
			
			catch(Exception exception) {
				
			}
		}
	}
	
	public static Element getPreviousUpdates() {
		
		String previousUpdates = IO.openAsString("Update.op");
		
		if(previousUpdates == null)
			return new Element();
		
		return ONEPlus.parseONEPlus(previousUpdates);
	}
	
	public static void update(Element update, ArrayList<String> paths, Element previousUpdates) {
		
		for(Element child : update.children)
			updateModule(child, paths, previousUpdates);
		
		updateRecord(update);
	}
	
	public static void updateModule(Element update, ArrayList<String> paths, Element previousUpdates) {
		
		if(!isModuleUpdateAvailable(update, previousUpdates))
			return;
		
		boolean exists = ElementUtilities.hasChild(previousUpdates, update.content);
		
		if(exists) {
			
			for(String path : paths) {
				
				if(new File(path + update.content).exists()) {
					
					updateModule(update.content, getModuleSource(update), path);
					
					return;
				}
			}
		}
		
		updateModule(update.content, getModuleSource(update), "");
	}
	
	public static String getModuleSource(Element update) {
		
		if(ElementUtilities.hasChild(update, "Source")) {
			
			if(ElementUtilities.getChild(update, "Source").children.size() >= 1)
				return ElementUtilities.getChild(update, "Source").children.get(0).content;
		}
		
		return null;
	}
	
	public static void updateModule(String moduleName, String source, String path) {
		
		if(source == null)
			return;
		
		IO.download(source, path + moduleName);
	}
	
	public static void updateRecord(Element update) {
		
		Element updateRecord = getPreviousUpdates();
		
		for(Element child : update.children)
			updateModuleRecord(updateRecord, child);
		
		IO.save("" + updateRecord, "Update.op");
	}
	
	public static void updateModuleRecord(Element updateRecord, Element update) {
		
		ElementUtilities.removeChildren(updateRecord, update.content);
		
		ElementUtilities.addChild(updateRecord, ElementUtilities.copyElement(update));
	}
	
	public static boolean isUpdateAvailable(Element update, Element previousUpdates) {
		
		for(Element child : update.children) {
		
			if(isModuleUpdateAvailable(child, previousUpdates))
				return true;
		}
		
		return false;
	}
	
	public static boolean isModuleUpdateAvailable(Element update, Element previousUpdates) {
		
		if(!ElementUtilities.hasChild(previousUpdates, update.content))
			return true;
		
		try {

			Element previousDate = ElementUtilities.getChild(previousUpdates, update.content);
			previousDate = ElementUtilities.getChild(previousDate, "Date").children.get(0);
			
			if(previousDate == null)
				return true;
			
			Element date = ElementUtilities.getChild(update, "Date").children.get(0);
			
			return !previousDate.content.equals(date.content);
		}
		
		catch(Exception exception) {
			
		}
		
		return false;
	}
}