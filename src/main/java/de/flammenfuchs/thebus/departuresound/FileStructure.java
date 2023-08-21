package de.flammenfuchs.thebus.departuresound;

import de.flammenfuchs.javalib.config.Configuration;
import de.flammenfuchs.thebus.departuresound.config.Config;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
public class FileStructure {

    private final File dataDirectory = new File("data/");
    private final File audioFile = new File("data/audio.wav");
    private final Config config;


    public FileStructure() {
        dataDirectory.mkdir();
        copyAudioFromAssetsIfNotExists();
        config = new Configuration("data/").get(Config.class);
    }

    @SneakyThrows
    private void copyAudioFromAssetsIfNotExists() {
        if (!audioFile.exists()) {
            InputStream audioResource = App.class.getResourceAsStream("/audio.wav");

            Files.copy(audioResource, Path.of("data/audio.wav"));
        }
    }
}
