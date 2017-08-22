package interfaces.audio.utilities;

public class AudioUtil {
	
	MP3 mp3;
	WAV wav;
	
	String fileName;
	
	public AudioUtil() {
		
	}
	
	public AudioUtil(String fileName) {
		
		setAudio(fileName);
		
		this.fileName = fileName;
	}
	
	public void setAudio(String fileName) {
		
		stop();
		
		mp3 = null;
		wav = null;
		
		if(fileName.indexOf('.') != -1) {
			
			if(fileName.substring(fileName.lastIndexOf('.')).equalsIgnoreCase(".mp3"))
				mp3 = new MP3(fileName);
			
			if(fileName.substring(fileName.lastIndexOf('.')).equalsIgnoreCase(".wav"))
				wav = new WAV(fileName);
		}
		
		this.fileName = fileName;
	}
	
	public void play() {
		
		if(mp3 != null)
			mp3.play();
		
		if(wav != null)
			wav.play();
	}
	
	public void play(int loops) {
		
		if(mp3 != null)
			mp3.play(loops);
		
		if(wav != null)
			wav.play(loops);
	}
	
	public void stop() {
		
		if(mp3 != null)
			mp3.stop();
		
		if(wav != null)
			wav.stop();
	}
	
	public boolean isPlaying() {
		
		if(mp3 != null)
			return mp3.isPlaying();
		
		if(wav != null)
			return wav.isPlaying();
		
		return false;
	}
	
	public String getFileName() {
		return fileName;
	}
}