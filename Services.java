import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Services {

    public void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public List<File> selectVideoFiles(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Video Files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv"));
        return fileChooser.showOpenMultipleDialog(stage);
    }

    public File selectSubtitleFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Subtitle File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Subtitle Files", "*.srt"));
        return fileChooser.showOpenDialog(stage);
    }
}
