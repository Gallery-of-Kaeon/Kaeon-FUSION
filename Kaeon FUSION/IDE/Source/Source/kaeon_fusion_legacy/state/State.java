package kaeon_fusion_legacy.state;

import java.util.ArrayList;

import kaeon_fusion_legacy.KaeonFUSION;
import legacy.philosophers_stone_plus.PhilosophersStonePlus;

public class State extends PhilosophersStonePlus {
	
	public State(KaeonFUSION kaeonFUSION) {
		
		tag("State");
		tag("" + kaeonFUSION.getStack());

		kaeonFUSION.pushStack();
	}
	
	public void pop() {
		
		getKaeonFUSION().popStack();
		
		ArrayList<PhilosophersStonePlus> connections = getConnections();
		
		for(int i = 0; i < connections.size(); i++)
			disconnectMutually(connections.get(i));
	}
	
	private KaeonFUSION getKaeonFUSION() {
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Kaeon FUSION");
		
		return (KaeonFUSION) (get(tags).get(0));
	}
}