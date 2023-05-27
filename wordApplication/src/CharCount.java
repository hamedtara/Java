// Import necessary JavaFX classes
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

import org.apache.poi.xwpf.usermodel.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

// Extend the Application class to create a JavaFX application
public class CharCount extends Application {
    // Override the start method, which is the entry point for JavaFX applications
    @Override
    public void start(Stage primaryStage) {
        // Set the title of the window
        primaryStage.setTitle("Char Word Count Tool");

        // Create a TextArea for text input
        TextArea textArea = new TextArea();
        // Set the preferred height of the TextArea
        textArea.setPrefHeight(200);

        // Create Text nodes for displaying the character and word counts
        Text label1 = new Text("Characters: ");
        Text label2 = new Text("Words: ");

        // Create a Button for counting characters and words
        Button button1 = new Button("Count");
        // Set an action to be performed when the button is clicked
        button1.setOnAction(e -> {
            // Get the text from the TextArea
            String text = textArea.getText();
            // Set the text of the label1 Text node to the number of characters
            label1.setText("Characters: " + text.length());
            // Split the text into words and set the text of the label2 Text node to the number of words
            String words[] = text.split("\\s");
            label2.setText("Words: " + words.length);
        });

        // Create a Label for the background color picker
        Label padLabel = new Label("Background Color:");
        // Create a ColorPicker for changing the background color of the TextArea
        ColorPicker pad = new ColorPicker(Color.BLACK);
        // Set an action to be performed when a color is selected
        pad.setOnAction(e -> textArea.setStyle("-fx-control-inner-background: #" + pad.getValue().toString().substring(2, 8)));

        // Create a Label for the text color picker
        Label textLabel = new Label("Text Color:");
        // Create a ColorPicker for changing the text color of the TextArea
        ColorPicker text = new ColorPicker(Color.BLACK);
        // Set an action to be performed when a color is selected
        text.setOnAction(e -> textArea.setStyle("-fx-text-fill: #" + text.getValue().toString().substring(2, 8)));

        // Create a ComboBox for font selection
        Label fontLabel = new Label("Font:");
        ComboBox<String> fontBox = new ComboBox<>();
        fontBox.getItems().addAll(Font.getFamilies());
        fontBox.setOnAction(e -> textArea.setFont(Font.font(fontBox.getValue(), textArea.getFont().getSize())));

        // Create a Slider for font size selection
        Label fontSizeLabel = new Label("Font Size:");
        Slider fontSizeSlider = new Slider(10, 72, 12);
        fontSizeSlider.setShowTickLabels(true);
        fontSizeSlider.setShowTickMarks(true);
        fontSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> textArea.setFont(Font.font(textArea.getFont().getName(), newValue.doubleValue())));

        // Create a Button for saving as Word
        Button saveWordButton = new Button("Save as Word");
        saveWordButton.setOnAction(e -> saveAsWord(textArea.getText()));

        // Create a Button for saving as PDF
        Button savePdfButton = new Button("Save as PDF");
        savePdfButton.setOnAction(e -> saveAsPdf(textArea.getText()));

        // Create a VBox layout to arrange the nodes vertically
        VBox vbox = new VBox(10, label1, label2, textArea, button1, padLabel, pad, textLabel, text, fontLabel, fontBox, fontSizeLabel, fontSizeSlider, saveWordButton, savePdfButton);
        // Set some padding around the edges of the VBox
        vbox.setPadding(new Insets(10));

        // Create a Scene with the VBox as the root node and a size of 400x600 pixels
        Scene scene = new Scene(vbox, 400, 650);
        // Set the Scene for the Stage
        primaryStage.setScene(scene);
        // Show the Stage
        primaryStage.show();
    }

    // Method to save the text as a Word document
    private void saveAsWord(String text) {
        // Create a new Word document
        XWPFDocument document = new XWPFDocument();
        // Create a paragraph and set the text
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);

        // Create a FileChooser to specify the save location
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Word File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Documents", "*.docx"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                // Save the document to the specified file
                FileOutputStream out = new FileOutputStream(file);
                document.write(out);
                out.close();
                System.out.println("File saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to save the text as a PDF document
    private void saveAsPdf(String text) {
        // Create a new PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        // Add the page to the document
        document.addPage(page);

        try {
            // Create a content stream to write the text
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();

            // Set the font and font size
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Set the position on the page for the text
            contentStream.newLineAtOffset(50, 700);

            // Write the text to the content stream
            contentStream.showText(text);

            contentStream.endText();
            contentStream.close();

            // Create a FileChooser to specify the save location
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Documents", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                // Save the document to the specified file
                document.save(file);
                document.close();
                System.out.println("File saved successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The main method launches the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
