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
import standard_kaeon_fusion.commands.console.LogError;
import standard_kaeon_fusion.commands.console.LogLine;
import standard_kaeon_fusion.commands.console.LogLineError;
import standard_kaeon_fusion.commands.data.Arguments;
import standard_kaeon_fusion.commands.data.Define;
import standard_kaeon_fusion.commands.data.Destroy;
import standard_kaeon_fusion.commands.data.Disable;
import standard_kaeon_fusion.commands.data.Enable;
import standard_kaeon_fusion.commands.data.Form;
import standard_kaeon_fusion.commands.data.GetCode;
import standard_kaeon_fusion.commands.data.GetCodeIndex;
import standard_kaeon_fusion.commands.data.GetFunction;
import standard_kaeon_fusion.commands.data.Global;
import standard_kaeon_fusion.commands.data.Import;
import standard_kaeon_fusion.commands.data.Interpreter;
import standard_kaeon_fusion.commands.data.IsCommand;
import standard_kaeon_fusion.commands.data.IsFunction;
import standard_kaeon_fusion.commands.data.IsVariable;
import standard_kaeon_fusion.commands.data.Listen;
import standard_kaeon_fusion.commands.data.Literal;
import standard_kaeon_fusion.commands.data.LockDown;
import standard_kaeon_fusion.commands.data.New;
import standard_kaeon_fusion.commands.data.Null;
import standard_kaeon_fusion.commands.data.Reflect;
import standard_kaeon_fusion.commands.data.This;
import standard_kaeon_fusion.commands.data.Type;
import standard_kaeon_fusion.commands.data.With;
import standard_kaeon_fusion.commands.exporting.Meta;
import standard_kaeon_fusion.commands.flow.AutomaticCatch;
import standard_kaeon_fusion.commands.flow.Block;
import standard_kaeon_fusion.commands.flow.Break;
import standard_kaeon_fusion.commands.flow.Call;
import standard_kaeon_fusion.commands.flow.Catch;
import standard_kaeon_fusion.commands.flow.CatchEnabled;
import standard_kaeon_fusion.commands.flow.Else;
import standard_kaeon_fusion.commands.flow.Execute;
import standard_kaeon_fusion.commands.flow.Exit;
import standard_kaeon_fusion.commands.flow.FUSIONException;
import standard_kaeon_fusion.commands.flow.Flip;
import standard_kaeon_fusion.commands.flow.In;
import standard_kaeon_fusion.commands.flow.Isolate;
import standard_kaeon_fusion.commands.flow.Loop;
import standard_kaeon_fusion.commands.flow.Out;
import standard_kaeon_fusion.commands.flow.Retrieve;
import standard_kaeon_fusion.commands.flow.Return;
import standard_kaeon_fusion.commands.flow.Run;
import standard_kaeon_fusion.commands.flow.Scope;
import standard_kaeon_fusion.commands.flow.Shift;
import standard_kaeon_fusion.commands.flow.Split;
import standard_kaeon_fusion.commands.flow.Ternary;
import standard_kaeon_fusion.commands.flow.Throw;
import standard_kaeon_fusion.commands.flow.Vanish;
import standard_kaeon_fusion.commands.flow.Wait;
import standard_kaeon_fusion.commands.io.AbsolutePath;
import standard_kaeon_fusion.commands.io.BuildWorkspace;
import standard_kaeon_fusion.commands.io.CreateDirectory;
import standard_kaeon_fusion.commands.io.Delete;
import standard_kaeon_fusion.commands.io.Directory;
import standard_kaeon_fusion.commands.io.FileExists;
import standard_kaeon_fusion.commands.io.FileSize;
import standard_kaeon_fusion.commands.io.IsDirectory;
import standard_kaeon_fusion.commands.io.IsHidden;
import standard_kaeon_fusion.commands.io.LocalDirectory;
import standard_kaeon_fusion.commands.io.Open;
import standard_kaeon_fusion.commands.io.ParentDirectory;
import standard_kaeon_fusion.commands.io.PathSeparator;
import standard_kaeon_fusion.commands.io.Rename;
import standard_kaeon_fusion.commands.io.RootDirectories;
import standard_kaeon_fusion.commands.io.Save;
import standard_kaeon_fusion.commands.io.Separator;
import standard_kaeon_fusion.commands.io.SourceWorkspaces;
import standard_kaeon_fusion.commands.kaeon.AetherCommand;
import standard_kaeon_fusion.commands.kaeon.SOULCore;
import standard_kaeon_fusion.commands.list.Append;
import standard_kaeon_fusion.commands.list.AppendAll;
import standard_kaeon_fusion.commands.list.At;
import standard_kaeon_fusion.commands.list.Concatenate;
import standard_kaeon_fusion.commands.list.Contains;
import standard_kaeon_fusion.commands.list.Crop;
import standard_kaeon_fusion.commands.list.Cut;
import standard_kaeon_fusion.commands.list.ElementToList;
import standard_kaeon_fusion.commands.list.Index;
import standard_kaeon_fusion.commands.list.Indexes;
import standard_kaeon_fusion.commands.list.Insert;
import standard_kaeon_fusion.commands.list.InsertAll;
import standard_kaeon_fusion.commands.list.IsSortedAlphabetical;
import standard_kaeon_fusion.commands.list.IsSortedNumerical;
import standard_kaeon_fusion.commands.list.KeyIndex;
import standard_kaeon_fusion.commands.list.KeyIndexes;
import standard_kaeon_fusion.commands.list.List;
import standard_kaeon_fusion.commands.list.ListToElement;
import standard_kaeon_fusion.commands.list.Rank;
import standard_kaeon_fusion.commands.list.ConvertSequence;
import standard_kaeon_fusion.commands.list.Count;
import standard_kaeon_fusion.commands.list.Remove;
import standard_kaeon_fusion.commands.list.Replace;
import standard_kaeon_fusion.commands.list.Reverse;
import standard_kaeon_fusion.commands.list.Set;
import standard_kaeon_fusion.commands.list.Shuffle;
import standard_kaeon_fusion.commands.list.Size;
import standard_kaeon_fusion.commands.list.SortAlphabetical;
import standard_kaeon_fusion.commands.list.SortNumerical;
import standard_kaeon_fusion.commands.list.Swap;
import standard_kaeon_fusion.commands.list.Tokenize;
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
import standard_kaeon_fusion.commands.math.ArcCosine;
import standard_kaeon_fusion.commands.math.ArcSine;
import standard_kaeon_fusion.commands.math.ArcTangent;
import standard_kaeon_fusion.commands.math.BinaryToDecimal;
import standard_kaeon_fusion.commands.math.Ceiling;
import standard_kaeon_fusion.commands.math.Cosine;
import standard_kaeon_fusion.commands.math.DecimalToBinary;
import standard_kaeon_fusion.commands.math.DecimalToHexadecimal;
import standard_kaeon_fusion.commands.math.Divide;
import standard_kaeon_fusion.commands.math.Floor;
import standard_kaeon_fusion.commands.math.HexadecimalToDecimal;
import standard_kaeon_fusion.commands.math.HyperbolicCosine;
import standard_kaeon_fusion.commands.math.HyperbolicSine;
import standard_kaeon_fusion.commands.math.HyperbolicTangent;
import standard_kaeon_fusion.commands.math.Infinity;
import standard_kaeon_fusion.commands.math.Maximum;
import standard_kaeon_fusion.commands.math.Mean;
import standard_kaeon_fusion.commands.math.Median;
import standard_kaeon_fusion.commands.math.Minimum;
import standard_kaeon_fusion.commands.math.Modulus;
import standard_kaeon_fusion.commands.math.Multiply;
import standard_kaeon_fusion.commands.math.NaturalLogarithm;
import standard_kaeon_fusion.commands.math.Negative;
import standard_kaeon_fusion.commands.math.Power;
import standard_kaeon_fusion.commands.math.Random;
import standard_kaeon_fusion.commands.math.Range;
import standard_kaeon_fusion.commands.math.Sine;
import standard_kaeon_fusion.commands.math.SquareRoot;
import standard_kaeon_fusion.commands.math.Subtract;
import standard_kaeon_fusion.commands.math.Tangent;
import standard_kaeon_fusion.commands.math.Theta;
import standard_kaeon_fusion.commands.math.ToDegrees;
import standard_kaeon_fusion.commands.math.ToRadians;
import standard_kaeon_fusion.commands.string.CharacterToNumber;
import standard_kaeon_fusion.commands.string.Lower;
import standard_kaeon_fusion.commands.string.NumberToCharacter;
import standard_kaeon_fusion.commands.string.PatternMatch;
import standard_kaeon_fusion.commands.string.Trim;
import standard_kaeon_fusion.commands.string.Upper;
import standard_kaeon_fusion.commands.system.Addresses;
import standard_kaeon_fusion.commands.system.Connect;
import standard_kaeon_fusion.commands.system.Day;
import standard_kaeon_fusion.commands.system.Hour;
import standard_kaeon_fusion.commands.system.Minute;
import standard_kaeon_fusion.commands.system.Month;
import standard_kaeon_fusion.commands.system.OperatingSystem;
import standard_kaeon_fusion.commands.system.Second;
import standard_kaeon_fusion.commands.system.Send;
import standard_kaeon_fusion.commands.system.Time;
import standard_kaeon_fusion.commands.system.Weekday;
import standard_kaeon_fusion.commands.system.Year;
import standard_kaeon_fusion.commands.undefined.Functions;
import standard_kaeon_fusion.commands.undefined.Literals;
import standard_kaeon_fusion.commands.undefined.Variables;
import standard_kaeon_fusion.commands.use.Use;
import standard_kaeon_fusion.utilities.Console;
import standard_kaeon_fusion.utilities.FUSIONUtilities;
import standard_kaeon_fusion.utilities.Priority;
import standard_kaeon_fusion.utilities.SortList;
import standard_kaeon_fusion.utilities.Stopper;
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
			
			ArrayList<PhilosophersStone> use =
					PhilosophersStoneUtilities.get(
							stone,
							"Kaeon FUSION",
							"Use");
			
			for(int i = 0; i < use.size(); i++)
				PhilosophersStoneUtilities.destroy(use.get(i));
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Use());
			
			if(!PhilosophersStoneUtilities.has(stone, "Console"))
				PhilosophersStoneUtilities.publiclyConnect(stone, new Console());
			
			PhilosophersStoneUtilities.publiclyConnect(stone, new State());
			PhilosophersStoneUtilities.publiclyConnect(stone, new Priority());
			PhilosophersStoneUtilities.publiclyConnect(stone, new Stopper());
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Literals());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Variables());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Functions());

			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Arguments());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Define());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Destroy());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Disable());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Enable());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Form());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new standard_kaeon_fusion.commands.data.Functions());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new GetCode());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new GetCodeIndex());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new GetFunction());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Global());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Import());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Interpreter());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new IsCommand());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new IsFunction());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new IsVariable());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Listen());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Literal());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new LockDown());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new New());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Null());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Reflect());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new This());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Type());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new standard_kaeon_fusion.commands.data.Variables());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new With());

			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new AutomaticCatch());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Block());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Break());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Call());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Catch());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new CatchEnabled());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Else());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Execute());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Exit());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Flip());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new FUSIONException());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new In());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Isolate());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Loop());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Out());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Retrieve());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Return());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Run());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Scope());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Shift());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Split());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Ternary());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Throw());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Wait());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Vanish());
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Input());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Log());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new LogError());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new LogLine());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new LogLineError());

			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Addresses());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Connect());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Day());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Hour());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Minute());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Month());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new OperatingSystem());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Second());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Send());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Time());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Weekday());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Year());

			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new AbsolutePath());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new BuildWorkspace());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new CreateDirectory());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Delete());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Directory());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new FileExists());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new FileSize());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new IsDirectory());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new IsHidden());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new LocalDirectory());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Open());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ParentDirectory());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new PathSeparator());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Rename());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new RootDirectories());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Save());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Separator());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new SourceWorkspaces());
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Append());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new AppendAll());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new At());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Concatenate());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Contains());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ConvertSequence());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Count());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Crop());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Cut());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ElementToList());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Index());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Indexes());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Insert());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new InsertAll());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new IsSortedAlphabetical());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new IsSortedNumerical());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new KeyIndex());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new KeyIndexes());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new List());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ListToElement());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Rank());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Remove());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Replace());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Reverse());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Set());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Shuffle());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Size());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new SortAlphabetical());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new SortNumerical());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Swap());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Tokenize());
	
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new CharacterToNumber());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Lower());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new NumberToCharacter());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new PatternMatch());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Trim());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Upper());
			
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
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ArcCosine());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ArcSine());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ArcTangent());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new BinaryToDecimal());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Ceiling());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Cosine());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new DecimalToBinary());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new DecimalToHexadecimal());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Divide());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Floor());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new HexadecimalToDecimal());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new HyperbolicCosine());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new HyperbolicSine());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new HyperbolicTangent());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Infinity());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Maximum());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Mean());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Median());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Minimum());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Modulus());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Multiply());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new NaturalLogarithm());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Negative());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Power());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Random());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Range());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Sine());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new SquareRoot());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Subtract());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Tangent());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Theta());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ToDegrees());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new ToRadians());
			
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new Meta());

			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new AetherCommand());
			PhilosophersStoneUtilities.publiclyConnectMutually(stone, new SOULCore());
			
			new Alias();
			new FUSIONUtilities();
			new SortList();
		}
		
		catch(Exception exception) {
			
		}
		
		ArrayList<DirectiveUnit> directives = new ArrayList<DirectiveUnit>();
		
		directives.add(new standard_one_plus.directives.Alternate());
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