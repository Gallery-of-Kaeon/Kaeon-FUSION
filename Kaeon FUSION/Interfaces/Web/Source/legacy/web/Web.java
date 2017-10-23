package legacy.web;

import legacy.kaeon_fusion_legacy.interface_module.Interface;
import legacy.web.commands.BuildPage;
import legacy.web.commands.BuildProcess;
import legacy.web.commands.BuildQuery;
import legacy.web.commands.BuildScript;
import legacy.web.commands.BuildStyle;

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