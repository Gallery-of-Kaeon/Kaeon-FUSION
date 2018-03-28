package aether_standard;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import one_plus.directive.DirectiveUnit;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.commands.console.Input;
import standard_kaeon_fusion.commands.console.Log;
import standard_kaeon_fusion.commands.console.LogLine;
import standard_kaeon_fusion.commands.data.Arguments;
import standard_kaeon_fusion.commands.data.Define;
import standard_kaeon_fusion.commands.data.Global;
import standard_kaeon_fusion.commands.data.Import;
import standard_kaeon_fusion.commands.data.Literal;
import standard_kaeon_fusion.commands.data.New;
import standard_kaeon_fusion.commands.data.Null;
import standard_kaeon_fusion.commands.data.This;
import standard_kaeon_fusion.commands.data.Type;
import standard_kaeon_fusion.commands.exporting.Build;
import standard_kaeon_fusion.commands.exporting.Derive;
import standard_kaeon_fusion.commands.exporting.Meta;
import standard_kaeon_fusion.commands.flow.Break;
import standard_kaeon_fusion.commands.flow.Call;
import standard_kaeon_fusion.commands.flow.Catch;
import standard_kaeon_fusion.commands.flow.Else;
import standard_kaeon_fusion.commands.flow.Execute;
import standard_kaeon_fusion.commands.flow.Exit;
import standard_kaeon_fusion.commands.flow.FUSIONException;
import standard_kaeon_fusion.commands.flow.In;
import standard_kaeon_fusion.commands.flow.Loop;
import standard_kaeon_fusion.commands.flow.Return;
import standard_kaeon_fusion.commands.flow.Run;
import standard_kaeon_fusion.commands.flow.Scope;
import standard_kaeon_fusion.commands.flow.Split;
import standard_kaeon_fusion.commands.flow.Throw;
import standard_kaeon_fusion.commands.flow.Wait;
import standard_kaeon_fusion.commands.io.Open;
import standard_kaeon_fusion.commands.io.Save;
import standard_kaeon_fusion.commands.list.Append;
import standard_kaeon_fusion.commands.list.At;
import standard_kaeon_fusion.commands.list.Concatenate;
import standard_kaeon_fusion.commands.list.Contains;
import standard_kaeon_fusion.commands.list.Crop;
import standard_kaeon_fusion.commands.list.Cut;
import standard_kaeon_fusion.commands.list.Index;
import standard_kaeon_fusion.commands.list.Insert;
import standard_kaeon_fusion.commands.list.List;
import standard_kaeon_fusion.commands.list.ConvertSequence;
import standard_kaeon_fusion.commands.list.Count;
import standard_kaeon_fusion.commands.list.Remove;
import standard_kaeon_fusion.commands.list.Reverse;
import standard_kaeon_fusion.commands.list.Set;
import standard_kaeon_fusion.commands.list.Size;
import standard_kaeon_fusion.commands.logic.And;
import standard_kaeon_fusion.commands.logic.Equal;
import standard_kaeon_fusion.commands.logic.ExclusiveOr;
import standard_kaeon_fusion.commands.logic.Greater;
import standard_kaeon_fusion.commands.logic.GreaterOrEqual;
import standard_kaeon_fusion.commands.logic.Is;
import standard_kaeon_fusion.commands.logic.Less;
import standard_kaeon_fusion.commands.logic.LessOrEqual;
import standard_kaeon_fusion.commands.logic.Not;
import standard_kaeon_fusion.commands.logic.Or;
import standard_kaeon_fusion.commands.math.AbsoluteValue;
import standard_kaeon_fusion.commands.math.Add;
import standard_kaeon_fusion.commands.math.Ceiling;
import standard_kaeon_fusion.commands.math.Cosine;
import standard_kaeon_fusion.commands.math.Divide;
import standard_kaeon_fusion.commands.math.Floor;
import standard_kaeon_fusion.commands.math.Infinity;
import standard_kaeon_fusion.commands.math.Modulus;
import standard_kaeon_fusion.commands.math.Multiply;
import standard_kaeon_fusion.commands.math.NaturalLogarithm;
import standard_kaeon_fusion.commands.math.Negative;
import standard_kaeon_fusion.commands.math.Power;
import standard_kaeon_fusion.commands.math.Random;
import standard_kaeon_fusion.commands.math.Sine;
import standard_kaeon_fusion.commands.math.SquareRoot;
import standard_kaeon_fusion.commands.math.Subtract;
import standard_kaeon_fusion.commands.math.Tangent;
import standard_kaeon_fusion.commands.math.ToDegrees;
import standard_kaeon_fusion.commands.math.ToRadians;
import standard_kaeon_fusion.commands.string.CharacterToNumber;
import standard_kaeon_fusion.commands.string.Lower;
import standard_kaeon_fusion.commands.string.NumberToCharacter;
import standard_kaeon_fusion.commands.string.Trim;
import standard_kaeon_fusion.commands.string.Upper;
import standard_kaeon_fusion.commands.system.Time;
import standard_kaeon_fusion.commands.undefined.Functions;
import standard_kaeon_fusion.commands.undefined.Literals;
import standard_kaeon_fusion.commands.undefined.Variables;
import standard_kaeon_fusion.utilities.Console;
import standard_kaeon_fusion.utilities.FUSIONUtilities;
import standard_kaeon_fusion.utilities.state.Alias;
import standard_kaeon_fusion.utilities.state.State;
import standard_one_plus.utilities.DirectiveUtilities;

public class Aether {
	
	public static Object onCall(Object object) {
		
		try {
			
			PhilosophersStone stone = (PhilosophersStone) object;
			
			if(PhilosophersStoneUtilities.has(stone, "Standard Interface"))
				return null;
			
			PhilosophersStone tag = new PhilosophersStone();
			tag.tags.add("Standard Interface");
			
			PhilosophersStoneUtilities.publiclyConnect(stone, tag);
			
			PhilosophersStone literal =
					PhilosophersStoneUtilities.get(
							stone,
							"Kaeon FUSION",
							"Literal").
					get(0);
			
			if(literal != null)
				PhilosophersStoneUtilities.destroy(literal);
			
			if(!PhilosophersStoneUtilities.has(stone, "Console"))
				PhilosophersStoneUtilities.publiclyConnect(stone, new Console());
			
			PhilosophersStoneUtilities.publiclyConnect(stone, new State());
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Literals());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Variables());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Functions());
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Arguments());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Call());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Catch());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Define());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Execute());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Exit());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new FUSIONException());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Global());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Import());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new In());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Literal());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new New());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Null());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Return());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Run());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new This());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Throw());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Type());
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Build());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Meta());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Derive());
			
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
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Append());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new At());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Concatenate());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Contains());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ConvertSequence());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Count());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Crop());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Cut());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Index());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Insert());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new List());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Remove());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Reverse());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Set());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Size());
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new And());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Equal());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ExclusiveOr());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Greater());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new GreaterOrEqual());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Is());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Less());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new LessOrEqual());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Not());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Or());

			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new AbsoluteValue());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Add());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Ceiling());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Cosine());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Divide());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Floor());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Infinity());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Modulus());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Multiply());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new NaturalLogarithm());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Negative());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Power());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Random());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Sine());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new SquareRoot());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Subtract());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Tangent());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ToDegrees());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ToRadians());
	
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new CharacterToNumber());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Lower());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new NumberToCharacter());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Trim());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Upper());
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Time());
			
			new Alias();
			new FUSIONUtilities();
		}
		
		catch(Exception exception) {
			
		}
		
		ArrayList<DirectiveUnit> directives = new ArrayList<DirectiveUnit>();
		
		directives.add(new standard_one_plus.directives.Call());
		directives.add(new standard_one_plus.directives.Define());
		directives.add(new standard_one_plus.directives.For());
		directives.add(new standard_one_plus.directives.If());
		directives.add(new standard_one_plus.directives.Import());
		directives.add(new standard_one_plus.directives.Info());
		directives.add(new standard_one_plus.directives.Properties());
		directives.add(new standard_one_plus.directives.Size());
		
		new DirectiveUtilities();
		
		return directives;
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