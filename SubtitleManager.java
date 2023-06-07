import javafx.scene.control.TextArea;
import javafx.util.Duration;
import java.util.List;

public class SubtitleManager {
    private List<Subtitle> subtitles;
    private TextArea subtitleTextArea;

    public SubtitleManager(TextArea subtitleTextArea) {
        this.subtitleTextArea = subtitleTextArea;
    }

    public void setSubtitles(List<Subtitle> subtitles) {
        this.subtitles = subtitles;
    }

    public void updateSubtitle(Duration currentTime) {
        long currentTimeMillis = (long) currentTime.toMillis();
        String currentSubtitle = "";
        for (Subtitle subtitle : subtitles) {
            long subtitleStart = (long) (subtitle.getStart() * 1000);
            long subtitleEnd = (long) (subtitle.getEnd() * 1000);
            if (currentTimeMillis >= subtitleStart && currentTimeMillis < subtitleEnd) {
                currentSubtitle = subtitle.getText();
                break;
            }
        }
        subtitleTextArea.setText(currentSubtitle);
    }
}
