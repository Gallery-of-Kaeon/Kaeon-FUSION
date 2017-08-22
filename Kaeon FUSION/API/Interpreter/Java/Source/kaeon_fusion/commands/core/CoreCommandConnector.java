package kaeon_fusion.commands.core;

import fusion.FUSIONStone;
import kaeon_fusion.commands.core.core.flow_control.Break;
import kaeon_fusion.commands.core.core.flow_control.Call;
import kaeon_fusion.commands.core.core.flow_control.Catch;
import kaeon_fusion.commands.core.core.flow_control.Else;
import kaeon_fusion.commands.core.core.flow_control.Exit;
import kaeon_fusion.commands.core.core.flow_control.Loop;
import kaeon_fusion.commands.core.core.flow_control.Return;
import kaeon_fusion.commands.core.core.flow_control.Run;
import kaeon_fusion.commands.core.core.flow_control.Scope;
import kaeon_fusion.commands.core.core.flow_control.Split;
import kaeon_fusion.commands.core.core.flow_control.Throw;
import kaeon_fusion.commands.core.core.flow_control.Wait;
import kaeon_fusion.commands.core.core.variable.Arguments;
import kaeon_fusion.commands.core.core.variable.Build;
import kaeon_fusion.commands.core.core.variable.Execute;
import kaeon_fusion.commands.core.core.variable.Function;
import kaeon_fusion.commands.core.core.variable.Global;
import kaeon_fusion.commands.core.core.variable.In;
import kaeon_fusion.commands.core.core.variable.Literal;
import kaeon_fusion.commands.core.core.variable.Meta;
import kaeon_fusion.commands.core.core.variable.Native;
import kaeon_fusion.commands.core.core.variable.This;
import kaeon_fusion.commands.core.core.variable.Variable;
import kaeon_fusion.commands.core.standard.console.Input;
import kaeon_fusion.commands.core.standard.console.Log;
import kaeon_fusion.commands.core.standard.console.LogLine;
import kaeon_fusion.commands.core.standard.io.Open;
import kaeon_fusion.commands.core.standard.io.Save;
import kaeon_fusion.commands.core.standard.list.Append;
import kaeon_fusion.commands.core.standard.list.At;
import kaeon_fusion.commands.core.standard.list.Insert;
import kaeon_fusion.commands.core.standard.list.List;
import kaeon_fusion.commands.core.standard.list.Remove;
import kaeon_fusion.commands.core.standard.list.Set;
import kaeon_fusion.commands.core.standard.list.Size;
import kaeon_fusion.commands.core.standard.logic.And;
import kaeon_fusion.commands.core.standard.logic.Equal;
import kaeon_fusion.commands.core.standard.logic.ExclusiveOr;
import kaeon_fusion.commands.core.standard.logic.Greater;
import kaeon_fusion.commands.core.standard.logic.GreaterOrEqual;
import kaeon_fusion.commands.core.standard.logic.Is;
import kaeon_fusion.commands.core.standard.logic.Less;
import kaeon_fusion.commands.core.standard.logic.LessOrEqual;
import kaeon_fusion.commands.core.standard.logic.Not;
import kaeon_fusion.commands.core.standard.logic.Or;
import kaeon_fusion.commands.core.standard.math.Add;
import kaeon_fusion.commands.core.standard.math.Divide;
import kaeon_fusion.commands.core.standard.math.Modulus;
import kaeon_fusion.commands.core.standard.math.Multiply;
import kaeon_fusion.commands.core.standard.math.Random;
import kaeon_fusion.commands.core.standard.math.Subtract;
import kaeon_fusion.commands.core.standard.string.Character;
import kaeon_fusion.commands.core.standard.string.Concatenate;
import kaeon_fusion.commands.core.standard.string.Length;
import kaeon_fusion.commands.core.standard.string.Substring;
import kaeon_fusion.commands.core.standard.system.Time;

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