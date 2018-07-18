package standard_kaeon_fusion.commands.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;
import standard_kaeon_fusion.utilities.Stopper;

/* STUB - INCOMPLETE */
public class Connect extends FUSIONUnit implements Runnable {

	public ArrayList<Object> list;
	public int port;

	public boolean running;

	public Connect() {
		tags.add("Standard");
	}

	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Connect");
	}

	@SuppressWarnings("unchecked")
	public Object process(Element element, ArrayList<Object> processed) {

		Connect connect = new Connect();

		connect.list = (ArrayList<Object>) processed.get(0);
		connect.port = Integer.parseInt("" + processed.get(1));

		connect.running = true;

		((Stopper) PhilosophersStoneUtilities.get(this, "Stopper").get(0)).connections.add(connect);

		new Thread(connect).start();

		return null;
	}

	@SuppressWarnings("resource")
	public void run() {

		BufferedReader in = null;

		try {

			in =
					new BufferedReader(
							new InputStreamReader(
									new ServerSocket(port).accept().getInputStream()));
		}

		catch (Exception exception) {

		}

		while(running && list != null) {

			try {

				if(in.ready())
					list.add(in.readLine());
			}

			catch (Exception exception) {
				running = false;
			}
		}
	}
}