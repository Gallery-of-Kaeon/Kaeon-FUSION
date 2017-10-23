package legacy.kaeon_fusion_legacy.commands.core;

import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Break;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Call;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Catch;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Else;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Exit;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Loop;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Return;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Run;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Scope;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Split;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Throw;
import legacy.kaeon_fusion_legacy.commands.core.core.flow_control.Wait;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.Arguments;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.Build;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.Execute;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.Function;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.Global;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.In;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.Literal;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.Meta;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.Native;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.New;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.This;
import legacy.kaeon_fusion_legacy.commands.core.core.variable.Variable;
import legacy.kaeon_fusion_legacy.commands.core.standard.console.Input;
import legacy.kaeon_fusion_legacy.commands.core.standard.console.Log;
import legacy.kaeon_fusion_legacy.commands.core.standard.console.LogLine;
import legacy.kaeon_fusion_legacy.commands.core.standard.io.Open;
import legacy.kaeon_fusion_legacy.commands.core.standard.io.Save;
import legacy.kaeon_fusion_legacy.commands.core.standard.list.Append;
import legacy.kaeon_fusion_legacy.commands.core.standard.list.At;
import legacy.kaeon_fusion_legacy.commands.core.standard.list.Insert;
import legacy.kaeon_fusion_legacy.commands.core.standard.list.List;
import legacy.kaeon_fusion_legacy.commands.core.standard.list.Remove;
import legacy.kaeon_fusion_legacy.commands.core.standard.list.Set;
import legacy.kaeon_fusion_legacy.commands.core.standard.list.Size;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.And;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.Equal;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.ExclusiveOr;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.Greater;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.GreaterOrEqual;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.Is;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.Less;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.LessOrEqual;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.Not;
import legacy.kaeon_fusion_legacy.commands.core.standard.logic.Or;
import legacy.kaeon_fusion_legacy.commands.core.standard.math.Add;
import legacy.kaeon_fusion_legacy.commands.core.standard.math.Divide;
import legacy.kaeon_fusion_legacy.commands.core.standard.math.Modulus;
import legacy.kaeon_fusion_legacy.commands.core.standard.math.Multiply;
import legacy.kaeon_fusion_legacy.commands.core.standard.math.Random;
import legacy.kaeon_fusion_legacy.commands.core.standard.math.Subtract;
import legacy.kaeon_fusion_legacy.commands.core.standard.string.Character;
import legacy.kaeon_fusion_legacy.commands.core.standard.string.Concatenate;
import legacy.kaeon_fusion_legacy.commands.core.standard.string.Length;
import legacy.kaeon_fusion_legacy.commands.core.standard.string.Substring;
import legacy.kaeon_fusion_legacy.commands.core.standard.system.Time;
import legacy.utilities.fusion.FUSIONStone;

public class CoreCommandConnector {
	
	public static void connect(FUSIONStone fusion) {

		fusion.publiclyConnectMutually(new Input());
		fusion.publiclyConnectMutually(new Log());
		fusion.publiclyConnectMutually(new LogLine());
		fusion.publiclyConnectMutually(new Time());
		
		fusion.publiclyConnectMutually(new Break());
		fusion.publiclyConnectMutually(new Call());
		fusion.publiclyConnectMutually(new Catch());
		fusion.publiclyConnectMutually(new Else());
		fusion.publiclyConnectMutually(new Exit());
		fusion.publiclyConnectMutually(new Loop());
		fusion.publiclyConnectMutually(new Return());
		fusion.publiclyConnectMutually(new Run());
		fusion.publiclyConnectMutually(new Scope());
		fusion.publiclyConnectMutually(new Split());
		fusion.publiclyConnectMutually(new Throw());
		fusion.publiclyConnectMutually(new Wait());
		
		fusion.publiclyConnectMutually(new Arguments());
		fusion.publiclyConnectMutually(new Build());
		fusion.publiclyConnectMutually(new Execute());
		fusion.publiclyConnectMutually(new Function());
		fusion.publiclyConnectMutually(new Global());
		fusion.publiclyConnectMutually(new In());
		fusion.publiclyConnectMutually(new Literal());
		fusion.publiclyConnectMutually(new Meta());
		fusion.publiclyConnectMutually(new Native());
		fusion.publiclyConnectMutually(new New());
		fusion.publiclyConnectMutually(new This());
		fusion.publiclyConnectMutually(new Variable());
		
		fusion.publiclyConnectMutually(new Add());
		fusion.publiclyConnectMutually(new Divide());
		fusion.publiclyConnectMutually(new Modulus());
		fusion.publiclyConnectMutually(new Multiply());
		fusion.publiclyConnectMutually(new Random());
		fusion.publiclyConnectMutually(new Subtract());
		
		fusion.publiclyConnectMutually(new Character());
		fusion.publiclyConnectMutually(new Concatenate());
		fusion.publiclyConnectMutually(new Length());
		fusion.publiclyConnectMutually(new Substring());

		fusion.publiclyConnectMutually(new Open());
		fusion.publiclyConnectMutually(new Save());

		fusion.publiclyConnectMutually(new Append());
		fusion.publiclyConnectMutually(new At());
		fusion.publiclyConnectMutually(new Insert());
		fusion.publiclyConnectMutually(new List());
		fusion.publiclyConnectMutually(new Remove());
		fusion.publiclyConnectMutually(new Set());
		fusion.publiclyConnectMutually(new Size());
		
		fusion.publiclyConnectMutually(new And());
		fusion.publiclyConnectMutually(new Equal());
		fusion.publiclyConnectMutually(new ExclusiveOr());
		fusion.publiclyConnectMutually(new Greater());
		fusion.publiclyConnectMutually(new GreaterOrEqual());
		fusion.publiclyConnectMutually(new Is());
		fusion.publiclyConnectMutually(new Less());
		fusion.publiclyConnectMutually(new LessOrEqual());
		fusion.publiclyConnectMutually(new Not());
		fusion.publiclyConnectMutually(new Or());
	}
}