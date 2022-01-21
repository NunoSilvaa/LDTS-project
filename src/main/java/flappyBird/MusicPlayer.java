package flappyBird;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MusicPlayer {
    private Clip eventSound;

    public MusicPlayer(String nameMusic){
        this.eventSound = loadMusic(nameMusic);
    }

    private Clip loadMusic(String nameSound) throws NullPointerException{
        try{
            String path = "/music/" + nameSound;
            File musicFile = new File(MusicPlayer.class.getResource(path).getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return musicClip;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void playSound(){
        eventSound.setMicrosecondPosition(0);
        eventSound.start();
    }
}
