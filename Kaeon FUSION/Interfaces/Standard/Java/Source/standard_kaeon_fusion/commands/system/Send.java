package standard_kaeon_fusion.commands.system;

import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

/* STUB - INCOMPLETE */
public class Send extends FUSIONUnit {
	
	public Send() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Send");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		Socket socket = null;
		
		try {
			
			socket =
					new Socket(
							InetAddress.getByName("" + processed.get(0)),
							Integer.parseInt("" + processed.get(1)));
			
			OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			String string = "" + processed.get(2);
			
			output.write(string, 0, string.length());
		}
		
		catch (Exception e) {
			
		}
		
		if(socket != null) {
			
			try {
				socket.close();
			}
			
			catch (Exception eexception) {
				
			}
		}
		
		return null;
	}
}