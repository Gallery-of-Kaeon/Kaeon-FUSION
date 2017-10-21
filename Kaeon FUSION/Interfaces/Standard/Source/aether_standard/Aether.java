package aether_standard;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard.commands.build.Build;
import standard.commands.build.Meta;
import standard.commands.console.Input;
import standard.commands.console.Log;
import standard.commands.console.LogLine;
import standard.commands.core.Arguments;
import standard.commands.core.Call;
import standard.commands.core.Catch;
import standard.commands.core.Define;
import standard.commands.core.Execute;
import standard.commands.core.Exit;
import standard.commands.core.Global;
import standard.commands.core.Import;
import standard.commands.core.In;
import standard.commands.core.New;
import standard.commands.core.Null;
import standard.commands.core.Return;
import standard.commands.core.Run;
import standard.commands.core.This;
import standard.commands.core.Throw;
import standard.commands.flow.Break;
import standard.commands.flow.Else;
import standard.commands.flow.Loop;
import standard.commands.flow.Scope;
import standard.commands.flow.Split;
import standard.commands.flow.Wait;
import standard.commands.io.Open;
import standard.commands.io.Save;
import standard.commands.list.Append;
import standard.commands.list.At;
import standard.commands.list.Concatenate;
import standard.commands.list.Crop;
import standard.commands.list.Insert;
import standard.commands.list.List;
import standard.commands.list.ListToString;
import standard.commands.list.Remove;
import standard.commands.list.Set;
import standard.commands.list.Size;
import standard.commands.list.StringToList;
import standard.commands.logic.And;
import standard.commands.logic.Equal;
import standard.commands.logic.ExclusiveOr;
import standard.commands.logic.Greater;
import standard.commands.logic.GreaterOrEqual;
import standard.commands.logic.Less;
import standard.commands.logic.LessOrEqual;
import standard.commands.logic.Not;
import standard.commands.logic.Or;
import standard.commands.math.Add;
import standard.commands.math.Divide;
import standard.commands.math.Modulus;
import standard.commands.math.Multiply;
import standard.commands.math.Random;
import standard.commands.math.Subtract;
import standard.commands.system.Time;
import standard.commands.undefined.Function;
import standard.commands.undefined.Literal;
import standard.commands.undefined.Variable;
import standard.utilities.Console;
import standard.utilities.FUSIONUtilities;
import standard.utilities.state.Alias;
import standard.utilities.state.State;

public class Aether {
	
	public static Object onCall(Object object) {
		
		PhilosophersStone stone = (PhilosophersStone) object;
		
		if(!PhilosophersStoneUtilities.has(stone, "Console"))
			PhilosophersStoneUtilities.publiclyConnect(stone, new Console());
		
		PhilosophersStoneUtilities.publiclyConnect(stone, new State());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Literal());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Variable());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Function());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Arguments());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Call());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Catch());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Define());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Execute());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Exit());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Global());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Import());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new In());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new New());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Null());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Return());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Run());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new This());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Throw());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Build());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Meta());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Break());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Else());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Loop());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Scope());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Split());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Wait());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Input());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Log());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new LogLine());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Open());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Save());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new At());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Append());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Concatenate());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Crop());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Insert());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new List());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ListToString());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Remove());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Set());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Size());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new StringToList());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new And());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Equal());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ExclusiveOr());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Greater());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new GreaterOrEqual());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Less());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new LessOrEqual());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Not());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Or());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Add());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Divide());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Modulus());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Multiply());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Random());
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Subtract());
		
		PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Time());
		
		new Alias();
		new FUSIONUtilities();
		
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