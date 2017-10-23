package legacy.utilities.philosophers_stone_plus;

import java.util.ArrayList;

public class PhilosophersStonePlus {

	private static ArrayList<PhilosophersStonePlus> philosophersStones = new ArrayList<PhilosophersStonePlus>();
	
	private ArrayList<String> tags;
	
	private ArrayList<PhilosophersStonePlus> publicConnections;
	private ArrayList<PhilosophersStonePlus> privateConnections;
	
	public PhilosophersStonePlus() {
		
		philosophersStones.add(this);
		
		tags = new ArrayList<String>();
		
		publicConnections = new ArrayList<PhilosophersStonePlus>();
		privateConnections = new ArrayList<PhilosophersStonePlus>();
	}
	
	public void tag(String tag) {
		
		tag = formatTag(tag);
		
		if(!isTagged(tag))
			tags.add(tag);
	}
	
	public void removeTag(String tag) {
		
		tag = formatTag(tag);
		
		for(int i = 0; i < tags.size(); i++) {
			
			if(tags.get(i).equals(tag)) {
				
				tags.remove(i);
				
				return;
			}
		}
	}
	
	public ArrayList<String> getTags() {
		return tags;
	}
	
	public boolean isTagged(String tag) {
		
		tag = formatTag(tag);
		
		for(int i = 0; i < tags.size(); i++) {
			
			if(tags.get(i).equals(tag))
				return true;
		}
		
		return false;
	}

	public void publiclyConnect(PhilosophersStonePlus connection) {
	
		if(isPubliclyConnected(connection))
			return;
	
		publicConnections.add(connection);
	}

	public void privatelyConnect(PhilosophersStonePlus connection) {
	
		if(isPrivatelyConnected(connection))
			return;
	
		privateConnections.add(connection);
	}
	
	public void publiclyConnectMutually(PhilosophersStonePlus connection) {
		publiclyConnect(connection);
		connection.publiclyConnect(this);
	}
	
	public void privatelyConnectMutually(PhilosophersStonePlus connection) {
		publiclyConnect(connection);
		connection.publiclyConnect(this);
	}
	
	public void disconnect(PhilosophersStonePlus connection) {
	
		int index = getIndexOfPublicConnection(connection);
	
		if(index != -1) {
	
			publicConnections.remove(index);
	
			return;
		}
	
		index = getIndexOfPrivateConnection(connection);
	
		if(index != -1) {
	
			privateConnections.remove(index);
	
			return;
		}
	}
	
	public void disconnectMutually(PhilosophersStonePlus connection) {
		disconnect(connection);
		connection.disconnect(this);
	}
	
	public void destroy() {
		
		for(int i = 0; i < philosophersStones.size(); i++) {
		
			if(philosophersStones.get(i) == this) {
				
				philosophersStones.remove(i);
				
				break;
			}
		}
		
		for(int i = 0; i < publicConnections.size(); i++)
			disconnectMutually(publicConnections.get(i));
		
		for(int i = 0; i < privateConnections.size(); i++)
			disconnectMutually(privateConnections.get(i));
	}
	
	public static ArrayList<PhilosophersStonePlus> getPhilosophersStones() {
		return philosophersStones;
	}
	
	public boolean isConnected(PhilosophersStonePlus connection) {
		return isPubliclyConnected(connection) || isPrivatelyConnected(connection);
	}
	
	public boolean isPubliclyConnected(PhilosophersStonePlus connection) {
	
		for(int i = 0; i < publicConnections.size(); i++) {
	
			if(publicConnections.get(i) == connection)
				return true;
		}
	
		return false;
	}
	
	public boolean isPrivatelyConnected(PhilosophersStonePlus connection) {
	
		for(int i = 0; i < privateConnections.size(); i++) {
	
			if(privateConnections.get(i) == connection)
				return true;
		}
	
		return false;
	}
	
	public ArrayList<PhilosophersStonePlus> getConnections() {
		
		ArrayList<PhilosophersStonePlus> connections = new ArrayList<PhilosophersStonePlus>();
		
		connections.addAll(publicConnections);
		connections.addAll(privateConnections);
		
		return connections;
	}
	
	public ArrayList<PhilosophersStonePlus> getPublicConnections() {
		return publicConnections;
	}
	
	public ArrayList<PhilosophersStonePlus> getPrivateConnections() {
		return privateConnections;
	}
	
	public ArrayList<PhilosophersStonePlus> get(ArrayList<String> tags) {
		
		tags = formatTags(tags);
		
		if(tags == null)
			return new ArrayList<PhilosophersStonePlus>();
		
		ArrayList<PhilosophersStonePlus> path = new ArrayList<PhilosophersStonePlus>();
		ArrayList<PhilosophersStonePlus> stones = new ArrayList<PhilosophersStonePlus>();
	
		getTraverse(true, tags, path, stones);
	
		return stones;
	}
	
	public ArrayList<PhilosophersStonePlus> superGet(ArrayList<String> tags) {
		
		tags = formatTags(tags);
		
		ArrayList<PhilosophersStonePlus> get = new ArrayList<PhilosophersStonePlus>();
		
		for(int i = 0; i < philosophersStones.size(); i++) {
			
			if(philosophersStones.get(i).hasAllTags(tags))
				get.add(philosophersStones.get(i));
		}
		
		return get;
	}
	
	public ArrayList<PhilosophersStonePlus> getAtlas() {
	
		ArrayList<PhilosophersStonePlus> path = new ArrayList<PhilosophersStonePlus>();
		ArrayList<PhilosophersStonePlus> stones = new ArrayList<PhilosophersStonePlus>();
	
		getTraverse(true, null, path, stones);
	
		return stones;
	}
	
	public boolean has(ArrayList<String> tags) {
		
		tags = formatTags(tags);
		
		ArrayList<PhilosophersStonePlus> path = new ArrayList<PhilosophersStonePlus>();
	
		return hasTraverse(true, tags, path);
	}
	
	public boolean superHas(ArrayList<String> tags) {
		
		tags = formatTags(tags);
		
		for(int i = 0; i < philosophersStones.size(); i++) {
			
			if(philosophersStones.get(i).hasAllTags(tags))
				return true;
		}
		
		return false;
	}
	
	public ArrayList<Object> call(ArrayList<Object> packet) {
	
		ArrayList<PhilosophersStonePlus> path = new ArrayList<PhilosophersStonePlus>();
		ArrayList<Object> calls = new ArrayList<Object>();
	
		callTraverse(true, packet, path, calls);
	
		return calls;
	}
	
	public ArrayList<Object> superCall(ArrayList<Object> packet) {
		
		ArrayList<Object> call = new ArrayList<Object>();
		
		for(int i = 0; i < philosophersStones.size(); i++) {
			
			Object object = philosophersStones.get(i).onCall(packet);
			
			if(object != null)
				call.add(object);
		}
		
		return call;
	}
	
	public Object onCall(ArrayList<Object> packet) {
		return null;
	}
	
	private int getIndexOfPublicConnection(PhilosophersStonePlus connection) {
	
		for(int i = 0; i < publicConnections.size(); i++) {
	
			if(publicConnections.get(i) == connection)
				return i;
		}
	
		return -1;
	}
	
	private int getIndexOfPrivateConnection(PhilosophersStonePlus connection) {
	
		for(int i = 0; i < privateConnections.size(); i++) {
	
			if(privateConnections.get(i) == connection)
				return i;
		}
	
		return -1;
	}
	
	private void callTraverse(
			boolean init,
			ArrayList<Object> packet,
			ArrayList<PhilosophersStonePlus> path,
			ArrayList<Object> calls) {
	
		if(traversed(path))
			return;
	
		path.add(this);
	
		Object call = null;
		
		try {
			call = onCall(packet);
		}
		
		catch(Exception exception) {
			
		}
	
		if(call != null)
			calls.add(call);
	
		if(init) {
	
			for(int i = 0; i < privateConnections.size(); i++)
				privateConnections.get(i).callTraverse(false, packet, path, calls);
		}
	
		for(int i = 0; i < publicConnections.size(); i++)
			publicConnections.get(i).callTraverse(false, packet, path, calls);
	}
	
	private void getTraverse(
			boolean init,
			ArrayList<String> tags,
			ArrayList<PhilosophersStonePlus> path,
			ArrayList<PhilosophersStonePlus> stones) {
	
		if(traversed(path))
			return;
	
		path.add(this);
	
		if(tags != null) {
			
			if(hasAllTags(tags))
				stones.add(this);
		}
		
		else
			stones.add(this);
	
		if(init) {
	
			for(int i = 0; i < privateConnections.size(); i++)
				privateConnections.get(i).getTraverse(false, tags, path, stones);
		}
	
		for(int i = 0; i < publicConnections.size(); i++)
			publicConnections.get(i).getTraverse(false, tags, path, stones);
	}
	
	boolean hasTraverse(
			boolean init,
			ArrayList<String> tags,
			ArrayList<PhilosophersStonePlus> path) {
	
		if(traversed(path))
			return false;
	
		path.add(this);
	
		if(hasAllTags(tags))
			return true;
	
		if(init) {
	
			for(int i = 0; i < privateConnections.size(); i++) {
	
				if(privateConnections.get(i).hasTraverse(false, tags, path))
					return true;
			}
		}
	
		for(int i = 0; i < publicConnections.size(); i++) {
	
			if(publicConnections.get(i).hasTraverse(false, tags, path))
				return true;
		}
	
		return false;
	}
	
	private boolean traversed(ArrayList<PhilosophersStonePlus> path) {
	
		for(int i = 0; i < path.size(); i++) {
	
			if(path.get(i) == this)
				return true;
		}
	
		return false;
	}
	
	private boolean hasAllTags(ArrayList<String> tags) {
		
		for(int i = 0; i < tags.size(); i++) {
			
			if(!isTagged(tags.get(i)))
				return false;
		}
		
		return true;
	}
	
	private ArrayList<String> formatTags(ArrayList<String> tags) {
		
		ArrayList<String> formattedTags = new ArrayList<String>();
		
		for(int i = 0; i < tags.size(); i++)
			formattedTags.add(formatTag(tags.get(i)));
		
		return formattedTags;
	}
	
	private String formatTag(String tag) {
		
		String formattedTag = "";
		
		for(int i = 0; i < tag.length(); i++) {
			
			if(!Character.isWhitespace(tag.charAt(i)))
				formattedTag += tag.charAt(i);
		}
		
		return formattedTag.toLowerCase();
	}
}