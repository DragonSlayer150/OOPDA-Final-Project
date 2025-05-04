import javax.sound.sampled.*;
import java.io.File;


/**
 * Audio Player runnable that can play the sound given
 *
 * @author Ryan Kee and Alberto Rogriguez
 * @version 4-9-2025 1.0.0
 */
public class AudioPlayer implements Runnable{
    private String audioFilePath;

    /**
     * Constructs an AudioPlayer runnable
     * @param audioFilePath
     *      The path to the audio file to be played
     */
    public AudioPlayer(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }


    @Override
    public void run() {
        try {
            File soundFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });
            while(clip.isOpen()) {
                if (Thread.currentThread().isInterrupted()) {
                    clip.close();
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
