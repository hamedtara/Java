import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubtitleParser {

    public static List<Subtitle> parse(String filePath) {
        List<Subtitle> subtitles = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String sequenceNumber = scanner.nextLine();
                String time = scanner.nextLine();
                String text = scanner.nextLine();

                System.out.println("sequenceNumber: " + sequenceNumber);
                System.out.println("time: " + time);
                System.out.println("text: " + text);

                String[] times = time.split("-->");
                if (times.length != 2) {
                    throw new IllegalArgumentException("Invalid time format in subtitle file: " + time);
                }

                double start = timeToSeconds(times[0].trim());
                double end = timeToSeconds(times[1].trim());

                subtitles.add(new Subtitle(start, end, text));

                if (scanner.hasNextLine()) {  // Skip the empty line after each subtitle
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subtitles;
    }

    private static double timeToSeconds(String time) {
        String[] parts = time.split("[:,]");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid time format: " + time);
        }

        try {
            double hours = Double.parseDouble(parts[0]);
            double minutes = Double.parseDouble(parts[1]);
            double seconds = Double.parseDouble(parts[2]);
            double milliseconds = Double.parseDouble(parts[3]);
            return hours * 3600 + minutes * 60 + seconds + milliseconds / 1000.0;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid time format: " + time, e);
        }
    }
}
