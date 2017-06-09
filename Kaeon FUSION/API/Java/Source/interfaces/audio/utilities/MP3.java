package interfaces.audio.utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class MP3 {

	private String mode;
	
	private boolean playing;
	
	private Player player;
	
	private FileInputStream fis;
	private BufferedInputStream bis;

	private File file;
	private String filename;

	public MP3(File f) {
		file = f;
		mode = "Foreign";
	}

	public MP3(String str) {
		filename = str;
		mode = "Local";
	}
	
	public void play() {
		play(0);
	}
	
	public void play(final int loop) {
		
		if(mode.equals("Foreign"))
			verifyFile();
		
		else
			playing = true;
		
		new Thread() {
			
			public void run() {
				
				try {
					
					int loops = 0;
					
					while (playing) {
						
						if(mode.equals("Foreign")) {
							fis = new FileInputStream(file);
							player = new Player(fis);
						}
						
						if(mode.equals("Local")) {
							
							InputStream is = getClass().getResourceAsStream(filename);
							bis = new BufferedInputStream(is);
							
							player = new Player(bis);
						}
						
						player.play();
						player.close();
						
						loops++;
						
						if(loop > 0 && loops == loop)
							playing = false;
					}
					
				}
				
				catch (Exception e) {
				}
			}
		}.start();
	}

	private void verifyFile() {
		
		if (file != null)
			
			try {
				
				fis = new FileInputStream(file);
				playing = true;
				
			}
			
			catch (Exception e) {
			}
	}

	public void stop() {
		
		if (player != null) {
			playing = false;
			player.close();
		}
	}

	public boolean isPlaying() {
		return playing;
	}
}