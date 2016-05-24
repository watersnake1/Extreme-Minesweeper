import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound
{
	private Clip soundClip;
	private FloatControl gainControl;

	public Sound(String path)
	{
		try
		{
			InputStream audio = getClass().getResourceAsStream(path);
			InputStream bufferedInputStream = new BufferedInputStream(audio);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedInputStream);
			AudioFormat baseFormat  = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
												   	   baseFormat.getSampleRate(), 16,
												   	   baseFormat.getChannels(),
												       baseFormat.getChannels() * 2,
												       baseFormat.getSampleRate(),
												       false);
		   AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);

		   soundClip = AudioSystem.getClip();
		   soundClip.open(dais);

		   gainControl = (FloatControl)soundClip.getControl(FloatControl.Type.MASTER_GAIN);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//If the sound clip is available and is not playing, then play the clip.
	public void play()
	{
		if(soundClip == null)
		{
			return;
		}
		stop();
		soundClip.setFramePosition(0);

		while(!soundClip.isRunning())
		{
			soundClip.start();
		}
	}

	//Plays the audio clip continuously, not sure if it works as intended.
	public void loop()
	{
		soundClip.loop(Clip.LOOP_CONTINUOUSLY);
		while(!soundClip.isRunning())
		{
			soundClip.start();
		}
	}

	//If the audio clip is playing, stop it from playing.
	public void stop()
	{
		if(soundClip.isRunning())
		{
			soundClip.stop();
		}
	}

	//Drains queued clips from the stack
	//Closes this input stream and
	//releases any system resources associated with the stream.
	public void close()
	{
		stop();
		soundClip.drain();
		soundClip.close();
	}

	//Sets the volume of the clip. Associated with the speaker
	//gain value.
	public void setVolume(float value)
	{
		gainControl.setValue(value);
	}

	//Checks if the sound is currently playing.
	public boolean isRunning()
	{
		return soundClip.isRunning();
	}
}
