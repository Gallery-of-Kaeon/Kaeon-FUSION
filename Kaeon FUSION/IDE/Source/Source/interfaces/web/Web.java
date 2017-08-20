package interfaces.web;

import interfaces.web.commands.BuildProcess;
import interfaces.web.commands.BuildQuery;
import interfaces.web.commands.BuildScript;
import interfaces.web.commands.BuildStyle;
import kaeon_fusion_legacy.interface_module.Interface;
import interfaces.web.commands.BuildPage;

public class Web extends Interface {
	
	public String getName() {
		return "Web";
	}
	
	public void onStart() {
		publiclyConnectMutually(new BuildPage());
		publiclyConnectMutually(new BuildStyle());
		publiclyConnectMutually(new BuildScript());
		publiclyConnectMutually(new BuildProcess());
		publiclyConnectMutually(new BuildQuery());
	}
	
	public Interface clone() {
		
		Web web = new Web();
		
		web.publiclyConnectMutually(new BuildPage());
		web.publiclyConnectMutually(new BuildStyle());
		web.publiclyConnectMutually(new BuildScript());
		web.publiclyConnectMutually(new BuildProcess());
		web.publiclyConnectMutually(new BuildQuery());
		
		return web;
	}
}