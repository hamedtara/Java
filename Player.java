import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

// Hamed Tara - hamedtara2021@gmail.com -

// You don't have permission copy my code or idea without my permission
public class Player extends Application {
    private ListView<String> playlistListView;
    private Services services;
    private MediaView mediaView;
    private TextArea subtitleTextArea;
    private String excelFilePath = null;
    private List<Subtitle> subtitles;
    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());

    private MediaPlayer mediaPlayer;
    private Slider timeSlider;
    private String selectedLanguage = "en"; // Default language is English
    private SubtitleManager subtitleManager; // New instance of SubtitleManager

    public Player() {
        services = new Services();
        mediaView = new MediaView();
        mediaView.setFitHeight(720);
        mediaView.setFitWidth(1280);
        playlistListView = new ListView<>();
        timeSlider = new Slider();
        timeSlider.setStyle("-fx-control-inner-background: #FFFFFF; -fx-thumb-fill: #FFD700; -fx-thumb-min-width: 20px; -fx-thumb-min-height: 20px;");
        timeSlider.setMin(0);
        timeSlider.setValue(0);
        timeSlider.setShowTickMarks(true);
        timeSlider.setShowTickLabels(true);
        timeSlider.setBlockIncrement(1);
        timeSlider.setMajorTickUnit(10);
        timeSlider.setMinorTickCount(1);

        timeSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double value) {
                int totalSeconds = value.intValue();
                int hours = totalSeconds / 3600;
                int minutes = (totalSeconds % 3600) / 60;
                int seconds = totalSeconds % 60;

                return String.format("%02d:%02d:%02d", hours, minutes, seconds);
            }

            @Override
            public Double fromString(String string) {
                String[] parts = string.split(":");
                int hours = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[1]);
                int seconds = Integer.parseInt(parts[2]);

                return (double) (hours * 3600 + minutes * 60 + seconds);
            }
        });




        // Create subtitle TextArea with a specific style

        subtitleTextArea = new TextArea();
        subtitleTextArea.setMinSize(200, 50);
        subtitleTextArea.setMaxSize(1280, 50);
        subtitleTextArea.setEditable(false);
        subtitleTextArea.setWrapText(true);

        // Inside your Player constructor or wherever you initialize subtitleTextArea
        subtitleTextArea.setStyle("-fx-font-size: 20px;");

        subtitleTextArea.setContextMenu(createContextMenu());

        // Create a new instance of SubtitleManager with subtitleTextArea
        subtitleManager = new SubtitleManager(subtitleTextArea);
    }

    private ContextMenu createContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem googleTranslateMenuItem = new MenuItem("Google Translate");
        googleTranslateMenuItem.setOnAction(event -> {
            String selectedText = subtitleTextArea.getSelectedText();
            try {
                String encodedSelectedText = URLEncoder.encode(selectedText, StandardCharsets.UTF_8.toString());
                String url = "https://translate.google.com/?sl=en&tl=" + selectedLanguage + "&text=" + encodedSelectedText + "&op=translate";
                services.openWebpage(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        // search in google
        MenuItem googleSearchMenuItem = new MenuItem("Search in Oxford Dictionary");
        googleSearchMenuItem.setOnAction(event -> {
            String selectedText = subtitleTextArea.getSelectedText();
            try {
                String encodedSelectedText = URLEncoder.encode(selectedText + "  meaning in oxford dictionary", StandardCharsets.UTF_8.toString());
                String url = "https://www.google.com/search?q=" + encodedSelectedText;
                services.openWebpage(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        // search in yarn website

        MenuItem yarnSearchMenuItem = new MenuItem("Search in Yarn");
        yarnSearchMenuItem.setOnAction(event -> {
            String selectedText = subtitleTextArea.getSelectedText();
            try {
                String encodedSelectedText = URLEncoder.encode(selectedText, StandardCharsets.UTF_8.toString());
                String url = "https://yarn.co/yarn-find?text=" + encodedSelectedText;
                services.openWebpage(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        // Add the Excel
        MenuItem excelWriteMenuItem = new MenuItem("Add to Excel");
        excelWriteMenuItem.setOnAction(event -> {
            String selectedText = subtitleTextArea.getSelectedText();
            ExcelWriter excelWriter = new ExcelWriter(excelFilePath);

            if (excelFilePath == null) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
                File file = fileChooser.showSaveDialog(null);  // replace null with your primary stage if available

                if (file != null) {
                    excelFilePath = file.getAbsolutePath();
                    excelWriter.setExcelFilePath(excelFilePath);
                }
            }

            try {
                excelWriter.write(selectedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        contextMenu.getItems().addAll(googleTranslateMenuItem, googleSearchMenuItem, yarnSearchMenuItem, excelWriteMenuItem);
        return contextMenu;
    }


    @Override
    public void start(Stage primaryStage) {
        Button selectFilesButton = new Button("Select Files");
        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        Button stopButton = new Button("Stop");

        selectFilesButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: #000000;");
        playButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: #000000;");
        pauseButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: #000000;");
        stopButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: #000000;");

        ComboBox<String> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll("en", "fa", "ar", "es", "zh", "ru", "de", "fr", "hi", "ur", "ja");
        languageComboBox.setValue("en");
        languageComboBox.setStyle("-fx-background-color: #FFD700; -fx-text-fill: #000000;");

        languageComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedLanguage = newValue;
        });


        VBox mediaPane = new VBox(10);

        mediaPane.setMinSize(1280, 720); // Larger size


        mediaPane.setStyle("-fx-background-color: #000000;");
        mediaPane.getChildren().addAll(mediaView, subtitleTextArea);
        VBox.setMargin(subtitleTextArea, new Insets(5, 0, 0, 0));


        HBox buttonPane = new HBox(10);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(selectFilesButton, playButton, pauseButton, stopButton, languageComboBox);

        GridPane rootPane = new GridPane();
        rootPane.setAlignment(Pos.CENTER_LEFT);
        rootPane.setVgap(10);
        rootPane.setStyle("-fx-background-color: #000000;");

        rootPane.add(mediaView, 0, 0);
        rootPane.add(subtitleTextArea, 0, 1);
        rootPane.add(buttonPane, 0, 2);
        rootPane.add(timeSlider, 0, 3);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(160);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(10);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(10);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(20);

        rootPane.getRowConstraints().addAll(row1, row2, row3, row4);




        //
        rootPane.setMinSize(0,0);
        rootPane.setMaxSize(1280,720);


        GridPane.setHalignment(buttonPane, HPos.CENTER);
        GridPane.setHalignment(timeSlider, HPos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(rootPane);
        root.setRight(playlistListView);

        playlistListView.setStyle("-fx-background-color: linear-gradient(#000000, #FFD700); -fx-text-fill: #000000;");

        timeSlider.setMin(0);
        timeSlider.setValue(0);
        timeSlider.setShowTickMarks(true);
        timeSlider.setShowTickLabels(true);
        timeSlider.setBlockIncrement(1);
        timeSlider.setMajorTickUnit(10);
        timeSlider.setMinorTickCount(1);

        VBox bottomPane = new VBox(10);
        bottomPane.setPadding(new Insets(10, 400, 10, 100)); // Adjust the left and right padding values to align the time slider.



        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().add(timeSlider);
        bottomPane.setStyle("-fx-background-color: #000000;");


        root.setBottom(bottomPane);

        VBox rightSideContainer = new VBox();
        rightSideContainer.getChildren().add(playlistListView);
        VBox.setVgrow(playlistListView, Priority.ALWAYS);
        root.setRight(rightSideContainer);

        root.setBottom(bottomPane);

        Scene scene = new Scene(root, 1280, 720);



        primaryStage.setTitle("Learning Language Player");
        primaryStage.setScene(scene);
        primaryStage.show();

        selectFilesButton.setOnAction(event -> {
            List<File> selectedFiles = services.selectVideoFiles(primaryStage);

            if (selectedFiles != null) {
                for (File file : selectedFiles) {
                    // Add the selected file(s) to the playlist
                    playlistListView.getItems().add(file.getAbsolutePath());
                }
            }
        });

        playButton.setOnAction(event -> {
            String selectedSong = playlistListView.getSelectionModel().getSelectedItem();
            if (selectedSong != null) {
                String audioFilePath = selectedSong;
                Media media = new Media(new File(audioFilePath).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);

                mediaPlayer.setOnReady(() -> {
                    // Set the max value of the timeSlider
                    timeSlider.setMax(mediaPlayer.getMedia().getDuration().toSeconds());
                });

                // Ask the user if they want to add subtitles
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Add Subtitles");
                alert.setHeaderText("Do you want to add subtitles?");
                alert.setContentText("Choose your option:");

                ButtonType yesButton = new ButtonType("Yes");
                ButtonType noButton = new ButtonType("No");

                alert.getButtonTypes().setAll(yesButton, noButton);

                if (alert.showAndWait().orElse(noButton) == yesButton) {
                    // User wants to add subtitles, ask for the subtitle file
                    File subtitleFile = services.selectSubtitleFile(primaryStage);

                    if (subtitleFile != null) {
                        subtitles = SubtitleParser.parse(subtitleFile.getAbsolutePath());
                        subtitleManager.setSubtitles(subtitles); // Set subtitles in SubtitleManager
                    } else {
                        System.out.println("No subtitle");
                    }
                }

                mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                    Platform.runLater(() -> {
                        Duration currentTime = newValue;
                        String currentTimeFormatted = formatDuration(currentTime);
                        subtitleManager.updateSubtitle(currentTime); // Update subtitle using SubtitleManager
                        timeSlider.setValue(currentTime.toSeconds()); // Update the slider's value

                    });
                });


                timeSlider.setOnMouseReleased(timeSliderEvent -> {
                    if (mediaPlayer != null) {
                        double position = timeSlider.getValue() / timeSlider.getMax();
                        Duration duration = mediaPlayer.getMedia().getDuration();
                        Duration seekTime = duration.multiply(position);
                        mediaPlayer.seek(seekTime);
                    }
                });


                mediaPlayer.setOnPlaying(() -> {
                    mediaPane.setBackground(Background.EMPTY); // Remove the background color
                });

                mediaPlayer.setOnStopped(() -> {
                    mediaPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))); // Set background color back to black
                    mediaPane.setPrefSize(800, 600); // Set default size to 800x600
                });

                mediaPlayer.play();
            }
        });

        pauseButton.setOnAction(event -> {
            if (mediaPlayer != null) {
                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaPlayer.pause();
                } else if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
                    mediaPlayer.play();
                }
            }
        });

        stopButton.setOnAction(event -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        });
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private String formatDuration(Duration duration) {
        long totalSeconds = (long) duration.toSeconds();
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
