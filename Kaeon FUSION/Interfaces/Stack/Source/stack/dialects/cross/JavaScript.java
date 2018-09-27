package stack.dialects.cross;

import java.util.ArrayList;

import build_dialect.cross_dialect.Category;
import build_dialect.cross_dialect.CrossDialect;
import io.IO;
import one.Element;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStoneUtilities;

public class JavaScript extends CrossDialect {
	
	@SuppressWarnings({ "unchecked" })
	public Object onCall(ArrayList<Object> packet) {
		
		if(!(((String) packet.get(0)).equalsIgnoreCase("Build") ||
				((String) packet.get(0)).equalsIgnoreCase("Derive")) ||
				!((String) packet.get(1)).equalsIgnoreCase(getName())) {
			
			return null;
		}
		
		ArrayList<ArrayList<String>> files = new ArrayList<ArrayList<String>>();

		ArrayList<Object> code = (ArrayList<Object>) packet.get(2);
		
		ArrayList<Object> arguments =
				packet.size() > 3 ?
						(ArrayList<Object>) packet.get(3) :
						new ArrayList<Object>();
		
		String filePath = "";
		
		if(arguments.size() > 0) {
			
			if(arguments.get(0) != null)
				filePath = "" + arguments.get(0);
			
			arguments.remove(0);
		}
		
		ArrayList<Object> names = new ArrayList<Object>();
		
		if(arguments.size() > 0) {
			
			try {
				names = (ArrayList<Object>) arguments.get(0);
			}
			
			catch(Exception exception) {
				
			}
			
			arguments.remove(0);
		}
		
		if(((String) packet.get(0)).equalsIgnoreCase("Build")) {
			
			ArrayList<Element> codeElements = new ArrayList<Element>();
			
			for(int i = 0; i < code.size(); i++)
				codeElements.add(ONEPlus.parseONEPlus("" + code.get(i)));
			
			ArrayList<ArrayList<Element>> groups = getBuildGroups(codeElements);
			
			for(int i = 0; i < groups.size(); i++)
				build(files, groups.get(i), getGroupName(names, i), i, arguments);
			
			if(groups.size() == 0)
				build(files, null, null, -1, arguments);
		}
		
		else {
			
			ArrayList<String> codeStrings = new ArrayList<String>();
			
			for(int i = 0; i < code.size(); i++)
				codeStrings.add("" + code.get(i));
			
			ArrayList<ArrayList<String>> groups = getDeriveGroups(codeStrings);
			
			for(int i = 0; i < groups.size(); i++)
				derive(files, groups.get(i), getGroupName(names, i), i, arguments);
		}
		
		String workspace = "";
		
		if(filePath.length() == 0) {
			
			try {
				workspace = "" + PhilosophersStoneUtilities.call(this, "Get Build Workspace").get(0);
			}
			
			catch(Exception exception) {
				
			}
		}
		
		boolean export = true;
		
		for(int i = 0; i < arguments.size() && export; i++) {
			
			if(("" + arguments.get(i)).equalsIgnoreCase("Return"))
				export = false;
		}
		
		if(export) {
			
			for(int i = 0; i < files.size(); i++)
				IO.save(files.get(i).get(1), workspace + filePath + files.get(i).get(0));
		}
		
		return files;
	}
	
	@SuppressWarnings("unchecked")
	public void build(
			ArrayList<ArrayList<String>> files,
			ArrayList<Element> code,
			String name,
			int index,
			ArrayList<Object> arguments) {
		
		if(code != null)
			super.build(files, code, name, index, arguments);
		
		for(int i = 0; i < arguments.size(); i++) {
			
			if(arguments.get(i) instanceof ArrayList) {
				
				ArrayList<Object> list = (ArrayList<Object>) arguments.get(i);
				
				if(("" + list.get(0)).equalsIgnoreCase("Application")) {
					
					for(int j = 2; j < list.size(); j++) {
						
						ArrayList<String> file = new ArrayList<String>();
						file.add(list.get(j) + ".html");
						
						String html = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\">";
						
						if(("" + list.get(1)).equalsIgnoreCase("Native"))
							html += "<script type=\"module\" src=\"" + list.get(j) + "\">";
						
						else
							html += "<script type=\"text/javascript\" data-main=\"./" + list.get(j) + "\">R=function(e,n){function t(e,o,u,a){if(e.g)return o(e.e,e);var c=e.g=e.l,f=new XMLHttpRequest;f.onload=function(i,l){function s(){l--||o(n,e)}200==f.status||e.t?(i=[],(e.t=e.t||f.response).replace(/(\\/\\*[\\w\\W]*?\\*\\/|\\/\\/[^\\n]*|[.$]r)|\\brequire\\s*\\(\\s*[\"']([^\"']*)[\"']\\s*\\)/g,function(e,n,t){n||i.push(t)}),l=i.length,i.map(function(o){t(r(e.l,o),s,\".\"!=o[0]?c+\"/../\":n,o)}),s()):u?t(e.n=r(u+=\"../\",a),o,u,a):(e.e=f,o(f,e))},e.t?f.onload():(f.open(\"GET\",c,!0),f.send())}function r(e,n,t){if(e.e)throw e.e;return n?(f.href=e,i.href=\".\"!=n[0]?\"./node_modules/\"+n:n,t=i.href+\".js\",f.href=\"\",u[t]=u[t]||{l:t}):e.n?r(e.n):(e[c]||(e.f||a(\"(function(require,\"+c+\",module){\"+e.t+\"\\n})//# sourceURL=\"+e.l))(function(n){return r(r(e.l,n))},e[c]={},e),e[c])}function o(e,n){t(e.call?{l:\"\",t:\"\"+e,f:e}:r(\"\",e),function(t,o){try{e=r(o)}catch(u){t=u}n&&n(t,e)})}var u={},a=eval,c=\"createElement\",f=e[c](\"base\"),i=e[c](\"a\");return e.head.appendChild(f),c=e.querySelector(\"script[data-main]\"),c&&o(c.dataset.main),c=\"exports\",o}(document);";
						
						html += "</script></head></html>";
						
						file.add(html);
						
						files.add(file);
					}
				}
			}
		}
	}
	
	public void buildCategories(
			ArrayList<ArrayList<String>> files,
			String name,
			Element main,
			String build,
			ArrayList<Category> categories,
			boolean utility,
			boolean snippet) {
		
		ArrayList<String> file = new ArrayList<String>();
		
		file.add(formatIdentifier(name) + ".js");
		
		if(!utility)
			build = (!snippet ? "var scope=false;var arguments=[];" : "") + build;
		
		if(!snippet) {
			
			Category functions = getCategory(categories, "Functions");
			
			for(int i = 0; i < functions.objects.size(); i++)
				build = functions.objects.get(i) + build;
		}
		
		file.add(build);
		
		files.add(file);
	}
	
	public String buildVariableDeclarationType(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		return "var";
	}
	
	public String buildFunctionDefinition(
			Element function,
			String functionBody,
			Element metaCopy,
			ArrayList<Category> categories,
			ArrayList<String> returnType,
			boolean isConstructor,
			ArrayList<Category> parameters,
			int parameterNumber,
			boolean aliased) {
		
		String build = (isConstructor ? "constructor " : "function ") + (aliased ? function.content : "") + "(";
		
		for(int i = 0; i < parameterNumber; i++) {
			
			build += "arg" + i;
			
			if(i < parameterNumber - 1)
				build += ",";
		}
		
		for(int i = 0; i < parameters.size(); i++) {
			
			build += parameters.get(i).name;
			
			if(i < parameters.size() - 1)
				build += ",";
		}
		
		build += "){scope=false;arguments=[";
		
		for(int i = 0; i < parameterNumber; i++) {
			
			build += "arg" + i;
			
			if(i < parameterNumber - 1)
				build += ",";
		}
		
		return build + "];" + functionBody + "}";
	}
	
	public String buildClassDefinition(Element classElement, String constructor, Element metaCopy, ArrayList<Category> categories, ArrayList<String> inheritence) {
		
		String build =
				"class " +
				classElement.content +
				(inheritence.size() > 0 ?
						" extends " + inheritence.get(0) :
						"") +
				"{";
		
		Category global = getCategory(categories, "Global");
		
		for(int i = 0; i < global.objects.size(); i++)
			build += "" + global.objects.get(i) + buildBodyElementSeparator();
		
		build += constructor;
		
		Category functions = getCategory(categories, "Functions");
		
		for(int i = 0; i < functions.objects.size(); i++)
			build += "" + functions.objects.get(i);
		
		return build + "}";
	}
	
	public String buildLog(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String build = "console.log(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + ",";
		
		return build + ")";
	}
	
	public String buildLogLine(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories) {
		
		String build = "console.log(";
		
		for(int i = 0; i < arguments.size(); i++)
			build += arguments.get(i) + (i < arguments.size() - 1 ? "," : "");
		
		return build + ")";
	}
	
	public String buildList(Element element, ArrayList<String> arguments, Element meta, ArrayList<Category> categories, String size, int index) {
		
		String build = "[";
		
		for(int i = 0; i < arguments.size(); i++) {
			
			build += arguments.get(i);
			
			if(i < arguments.size() - 1)
				build += ",";
		}
		
		return build + "]";
	}
}