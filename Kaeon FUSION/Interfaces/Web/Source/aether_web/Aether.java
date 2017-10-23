package aether_web;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import dialects.Page;
import dialects.Query;
import dialects.Script;
import dialects.Style;
import legacy.kaeon_fusion_legacy.state.function_stone.FunctionStone;
import legacy.utilities.one_plus.element.Element;
import legacy.web.utilities.page.PageBuilder;
import legacy.web.utilities.page.attributes.Attribute;
import legacy.web.utilities.page.literal.Literal;
import legacy.web.utilities.page.tags.Tag;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class Aether {
	
	public static Object onCall(Object object) {
		
		PhilosophersStone stone = (PhilosophersStone) object;
		
		if(PhilosophersStoneUtilities.has(stone, "Web Interface"))
			return null;
		
		PhilosophersStone tag = new PhilosophersStone();
		tag.tags.add("Web Interface");
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Page());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new dialects.Process());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Query());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Script());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Style());
		
		new PageBuilder("");
		new legacy.web.utilities.query.Query(true);
		new Attribute("", "");
		new Tag("", "");
		new Literal();
		new FunctionStone(new Element());
		
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