package aether_stack;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import stack.dialects.CSS;
import stack.dialects.HTML;
import stack.dialects.JSON;
import stack.dialects.SQL;
import stack.dialects.XML;
import stack.dialects.cross.C;
import stack.dialects.cross.CPP;
import stack.dialects.cross.CS;
import stack.dialects.cross.Java;
import stack.dialects.cross.JavaScript;
import stack.dialects.cross.PHP;
import stack.dialects.cross.Python;
import stack.dialects.cross.Swift;
import stack.utilities.cross_dialect.Category;

public class Aether {
	
	public static Object onCall(Object object) {
		
		PhilosophersStone stone = (PhilosophersStone) object;
		
		if(PhilosophersStoneUtilities.has(stone, "Stack Interface"))
			return null;
		
		PhilosophersStone tag = new PhilosophersStone();
		tag.tags.add("Stack Interface");

		PhilosophersStoneUtilities.publiclyConnect(stone, tag);
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new C());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new CPP());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new CS());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Java());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new JavaScript());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new PHP());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Python());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Swift());

		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new XML());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new JSON());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new HTML());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new CSS());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new SQL());
		
		new Category();
		
		return null;
	}
	
	public static Integer id;
	
	public static void assignID() {
		
		if(id != null)
			return;
	}
	
	public static ArrayList<Integer> getActiveModuleIDs(String name) {
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		return ids;
	}
	
	public static Object call(String name, int id, Object argument) {
		return id == 0 ? staticCall(name, argument) : activeCall(name, id, argument);
	}
	
	public static Object staticCall(String name, Object argument) {
		
		try {
			
			URLClassLoader loader = new URLClassLoader(new URL[] {new URL("file:" + name + ".jar")});
			
			String formattedName = name.toLowerCase();
			
			for(int i = 0; i < formattedName.length(); i++) {
				
				if(formattedName.charAt(i) == ' ')
					formattedName = formattedName.substring(0, i) + '_' + formattedName.substring(i + 1);
			}
			
			Class<?> aether = Class.forName("aether_" + formattedName + ".Aether", true, loader);
			Method onCall = aether.getMethod("onCall", new Object().getClass());
			
			Object object = onCall.invoke(aether.getConstructor().newInstance(), argument);
			
			loader.close();
			
			return object;
		}
		
		catch(Exception exception) {
			return null;
		}
	}
	
	public static Object activeCall(String name, int id, Object argument) {
		return null;
	}
}