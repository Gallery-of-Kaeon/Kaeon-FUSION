package aether_one_lisp;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import one_lisp.ONE_LISP;

public class Aether {
	
	public static Object onCall(Object object) {
		return ONE_LISP.parse("" + object);
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