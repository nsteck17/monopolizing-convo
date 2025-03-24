package stechschulte.uno.ese.monopolizing_convo.file.util;

import java.time.Duration;

public class FormatUtil {

    /**
     * Formats a duration into a human-readable string
     * @param duration
     * @return
     */
    public static String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
    }
}
