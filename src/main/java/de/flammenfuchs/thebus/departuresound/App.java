package de.flammenfuchs.thebus.departuresound;

import de.flammenfuchs.javalib.logging.Logger;
import de.flammenfuchs.thebus.departuresound.audio.Audio;
import de.flammenfuchs.thebus.telemetryapi.TelemetryApi;
import de.flammenfuchs.thebus.telemetryapi.mission.Mission;
import de.flammenfuchs.thebus.telemetryapi.mission.Stop;
import de.flammenfuchs.thebus.telemetryapi.world.World;
import lombok.SneakyThrows;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class App {

    public static void main(String[] args) {
        new App();
    }

    @SneakyThrows
    public App() {
        Logger logger = new Logger();
        logger.info("Initialize application...");
        TelemetryApi api = TelemetryApi.getInstance();
        FileStructure fileStructure = new FileStructure();
        Audio audio = new Audio(fileStructure.getAudioFile());

        logger.info("Starting application.");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

                           @Override
                           public void run() {
                               if (!api.isAvailable()) {
                                   logger.warn("Telemetry unavailable");
                                   return;
                               }
                               Mission mission = api.getMission();
                               World world = api.getWorld();

                               if (mission == null || world == null) {
                                   logger.warn("Telemetry unavailable");
                                   return;
                               }

                               if (mission.getCurrentStopIndex() == 0) {
                                   Stop currentStop = mission.getCurrentStop();

                                   if (currentStop == null) {
                                       logger.warn("Telemetry unavailable");
                                       return;
                                   }

                                   Date current = world.getDateTime();
                                   Date departure = currentStop.getDepatureTime() != null ?
                                           currentStop.getDepatureTime() : currentStop.getArrivalTime();
                                   if (current.getTime()
                                           - (departure.getTime() - (fileStructure.getConfig().getNotifySeconds() * 1000)) > 0) {
                                       if (!audio.isPlaying()) {
                                           audio.setPlaying(true);
                                           logger.info("Departure Notification Time Reached. Start notify sound");
                                       }
                                   } else {
                                       if (audio.isPlaying()) {
                                           audio.setPlaying(false);
                                           logger.warn("Notify sound was falsely playing. Stopped sound");
                                       }
                                   }
                               } else {
                                   if (audio.isPlaying()) {
                                       audio.setPlaying(false);
                                       logger.info("Leaved first station. Stopped sound");
                                   }
                               }
                           }
                       }, 1000, 1000
        );
        while (true) ;
    }
}
