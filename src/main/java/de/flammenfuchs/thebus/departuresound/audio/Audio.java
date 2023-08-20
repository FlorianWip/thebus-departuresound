package de.flammenfuchs.thebus.departuresound.audio;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Audio {


    private final Clip clip;

    @Getter
    @Setter
    private boolean playing;

    @SneakyThrows
    public Audio(File audioFile) {
        AudioInputStream stream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(audioFile)));
        clip = AudioSystem.getClip();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.open(stream);
        new Thread(() -> {
            while (true) {
                if (playing) {
                    if (!clip.isRunning()) {
                        clip.start();
                    }
                    clip.loop(-1);
                } else {
                    clip.stop();
                }
            }
        }).start();
    }
}
