package interfaces.audio.utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WAV {

	private Clip clip;

	public WAV(String fileName) {

		try {

			URL defaultSound = getClass().getResource(fileName);
			AudioInputStream sound = AudioSystem.getAudioInputStream(defaultSound);
			
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		
		catch (MalformedURLException e) {

			e.printStackTrace();
			throw new RuntimeException("Sound: Malformed URL: " + e);
		} 
		
		catch (UnsupportedAudioFileException e) {

			e.printStackTrace();
			throw new RuntimeException("Sound: Unsupported Audio File: " + e);
		}
		
		catch (IOException e) {

			e.printStackTrace();
			throw new RuntimeException("Sound: Input/Output Error: " + e);
		}
		
		catch (LineUnavailableException e) {

			e.printStackTrace();
			throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
		}
	}

	public void play(int loops){
		clip.loop(loops - 1);
	}

	public void play() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

	public boolean isPlaying() {
		return clip.isRunning();
	}
}