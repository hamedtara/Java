import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class randomSelector {

    //A new class called RandomSelector is created and within that,
    // a method called selectRandomBrand is defined. This method returns a random car brand from the text file.
    public String selectRandomBrand() {
        //A List object called carBrands is created to store the car brands from the text file. The ArrayList class is used here to implement the List interface.
        List<String> carBrands = new ArrayList<>();
        //A try-with-resources block is used to read the contents of the text file.
        // The Scanner class is used to read the text file line by line. FileReader is used to read the file and the path of the text file is passed as an argument to FileReader.
        try (Scanner scanner = new Scanner(new FileReader("src/hangman.txt"))) {
            //The while loop is used to iterate over the contents of the text file.
            // The hasNextLine method is used to check if there is another line in the file to read. If there is another line, the line is read using the nextLine method and added to the carBrands list.
            while (scanner.hasNextLine()) {
                carBrands.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        Random random = new Random();
        int randomIndex = random.nextInt(carBrands.size());
        return carBrands.get(randomIndex);

    }
}