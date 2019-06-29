package standard_kaeon_fusion.commands.system;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;

import fusion.FUSIONUnit;
import one.Element;

public class Addresses extends FUSIONUnit {
	
	public Addresses() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Addresses");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		ArrayList<Object> addressList = new ArrayList<Object>();
		
		if(processed.size() == 0) {
			
			try {
				
				Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	
				while(interfaces.hasMoreElements()) {
	
					NetworkInterface networkInterface = interfaces.nextElement();
					
					if(networkInterface.isUp()) {
						
						Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
						
						while(addresses.hasMoreElements())
							addressList.add(addresses.nextElement().getHostAddress());
					}
				}
			}
			
			catch (Exception exception) {
				
			}
		}
		
		else {
			
			try {
				
				InetAddress[] machines = InetAddress.getAllByName("" + processed.get(0));
				
				for(InetAddress address : machines)
					addressList.add(address.getHostAddress());
			}
			
			catch (Exception exception) {
				
			}
		}
		
		return addressList;
	}
}