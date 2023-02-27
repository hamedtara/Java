import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Hangman implements ActionListener {

    //Code Elements
    String illegalChar = ".1,8!?2:;(3){4}9[5]@*~/\\6–#$7&^%0|`"; // String illegalChar contains the list of illegal characters that cannot be used as guesses
    Random shuffle = new Random(); // Random shuffle is an instance of the Random class for shuffling wordFile
    String incorrectGuesses = ""; // String incorrectGuesses stores the incorrect guesses made by the player
    String selectedWord; // String selectedWord is the word that the player is trying to guess
    String progressDone; // String progressDone stores the correctly guessed letters in the word
    String progress; // String progress stores the progress of the player in guessing the word
    String input; // String input is the input from the player
    char[] wordToGuess; // char Array stores the selected word as a character array
    char[] guesses; // char[] guesses stores the guessed letters
    String[] wordFile; // String[] wordFile is the list of words used in the game

    int att = 1; // int att keeps track of the current attempt
    int wins = 0; // int wins stores the number of wins
    int losses = 0; // int losses stores the number of losses
    int lives = 10; // int lives stores the number of remaining lives for the player
    int gameState = 0; // int gameState is the current state of the game
    int gameInstance = 0; // int gameInstance keeps track of the current instance of the game

    //GUIs
    /*-----Frames-----*/
    JFrame mainWindow = new JFrame("Hangman"); // main window for the Hangman game
    JMenuBar menuBar = new JMenuBar(); //menu bar for the game window
    JMenu fileMenu = new JMenu("Menu"); // menu for different options
    JMenuItem menuHome = new JMenuItem("Home"); // menu item for going to the home screen
    JMenuItem menuResume = new JMenuItem("Resume"); // menu item for resuming the game
    JMenuItem menuStart = new JMenuItem("Restart"); // menu item for restarting the game
    JLabel scoreCount = new JLabel(); // label to display the score count
    /*-----Home-----*/
    JLabel title = new JLabel("Hangman Game"); // label to display the title of the game
    JButton buttonNewGame = new JButton("New Game"); // button to start a new game
    JButton buttonResume = new JButton("Resume"); // button to resume the previous game
    JButton buttonExit = new JButton("Exit"); // button to exit the game
    JLabel eye = new JLabel(new ImageIcon("src/heart.png")); // label to display the heart icon
    JLabel credits = new JLabel("Created By: Group 4"); //label to display the credits for the game


    /*-----Game-----*/
    JLabel hint = new JLabel(); // label to display the hint for the word
    JLabel lineTop = new JLabel(); // top line for separating the game window
    JLabel attemptCount = new JLabel("Attempt #" + att); // label to display the number of attempts
    JLabel lifeCount = new JLabel("Lives left: " + lives); // label to display the remaining lives
    JLabel progressVisual = new JLabel(); // label to display the hangman image
    JLabel progressWord = new JLabel(); // label to display the progress of the player in guessing the word
    JLabel lineBottom = new JLabel(); // bottom line for separating the game window
    JLabel guide = new JLabel("Please enter a letter: "); // label to guide the player to enter a letter
    //JButton buttonKeyboard = new JButton(new ImageIcon("Files/Images/GUI/ButtonSprites/buttonKeyboard.png"));
    JTextField entry = new JTextField(); // text field to enter the letter
    JButton submit = new JButton("Submit"); // button to submit the entered letter
    JButton endPlayAgain = new JButton("Play Again"); // button to play again at the end of the game
    JButton endHome = new JButton("Leave"); // button to leave the game at the end of the game
    JButton addword = new JButton("Add to the list"); // button to add words to the list of words to be used in the game

    //Window
    public Hangman() {

        //Frame
        /**

         Hangman constructor to initialize the game window, menu bar, and action listeners.
         The default size of the window is 350x420 pixels and is not resizable.
         The background color of the window is set to white and the layout is set to null.
         The game window is set to close on exit and is positioned in the center of the screen.
         The menu bar is not visible by default and contains options for "Home", "Resume", and "Restart".
         The score count is displayed on the menu bar.
         The action listeners are added to the buttons and text fields in the game window, including
         the "Resume", "New Game", "Exit", "Submit", "Play Again", "Leave", and "Add to the list" buttons.
         The startHomeScreen() method is called to display the initial home screen of the game.
         */
        /*-----Game Window-----*/
        mainWindow.setSize(350, 420);
        mainWindow.setResizable(false);
        mainWindow.getContentPane().setBackground(Color.WHITE);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(null);

        //Action Listeners
        buttonResume.addActionListener(this);
        buttonNewGame.addActionListener(this);
        buttonExit.addActionListener(this);
        entry.addActionListener(this);
        submit.addActionListener(this);
        endPlayAgain.addActionListener(this);
        endHome.addActionListener(this);
        addword.addActionListener(this);

        //Menu Bar
        menuBar.setVisible(false);
        mainWindow.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(menuHome);
        fileMenu.add(menuResume);
        fileMenu.add(menuStart);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(scoreCount);

        menuHome.addActionListener(this);
        menuResume.addActionListener(this);
        menuStart.addActionListener(this);


        startHomeScreen();
    }

    //First Home Screen
    public void startHomeScreen() {

        mainWindow.getContentPane().setBackground(Color.WHITE);

        //Title
        title.setBounds(123, 52, 250, 60);
        title.setEnabled(true);
        title.setVisible(true);
        mainWindow.add(title);

        //Resume Button
        buttonResume.setVisible(false);

        //New Game Button
        buttonNewGame.setBounds(119, 170, 101, 22);
        buttonNewGame.setContentAreaFilled(false);
        buttonNewGame.setBorderPainted(false);
        buttonNewGame.setOpaque(false);
        buttonNewGame.setEnabled(true);
        buttonNewGame.setVisible(true);
        mainWindow.add(buttonNewGame);


        //Exit Button
        buttonExit.setBounds(118, 265, 100, 20);
        buttonExit.setContentAreaFilled(false);
        buttonExit.setBorderPainted(false);
        buttonExit.setOpaque(false);
        buttonExit.setEnabled(true);
        buttonExit.setVisible(true);
        mainWindow.add(buttonExit);

        //Credits
        credits.setFont(new Font("Calibri", Font.PLAIN, 9));
        credits.setBounds(25, 363, 290, 20);
        credits.setEnabled(true);
        credits.setVisible(true);
        mainWindow.add(credits);

        //Eye
        eye.setBounds(5, 362, 15, 20);
        eye.setEnabled(true);
        eye.setVisible(true);
        mainWindow.add(eye);

        //Loads Main Window After Everything's Done
        mainWindow.setVisible(true);

    }

    //Home Screen
    public void homeScreen() {

        mainWindow.getContentPane().setBackground(Color.WHITE);

        //Disables Other GUIs
        menuHome.setEnabled(false);

        hint.setVisible(false);
        lineTop.setVisible(false);
        attemptCount.setVisible(false);
        lifeCount.setVisible(false);
        progressVisual.setVisible(false);
        progressWord.setVisible(false);
        lineBottom.setVisible(false);
        guide.setVisible(false);
        entry.setVisible(false);
        submit.setVisible(false);
        endPlayAgain.setVisible(false);
        endHome.setVisible(false);

        //Title
        title.setBounds(123, 52, 250, 60);
        title.setEnabled(true);
        title.setVisible(true);
        mainWindow.add(title);


        //Resume Button
        buttonResume.setBounds(70, 170, 200, 20);
        buttonResume.setContentAreaFilled(false);
        buttonResume.setBorderPainted(false);
        buttonResume.setOpaque(false);
        buttonResume.setEnabled(true);
        buttonResume.setVisible(true);
        mainWindow.add(buttonResume);

        //New Game Button
        buttonNewGame.setBounds(119, 210, 101, 22);
        buttonNewGame.setContentAreaFilled(false);
        buttonNewGame.setBorderPainted(false);
        buttonNewGame.setOpaque(false);
        buttonNewGame.setEnabled(true);
        buttonNewGame.setVisible(true);
        mainWindow.add(buttonNewGame);


        //Exit Button
        buttonExit.setBounds(118, 250, 100, 20);
        buttonExit.setContentAreaFilled(false);
        buttonExit.setBorderPainted(false);
        buttonExit.setOpaque(false);
        buttonExit.setEnabled(true);
        buttonExit.setVisible(true);
        mainWindow.add(buttonExit);

        //Credits
        credits.setFont(new Font("Calibri", Font.PLAIN, 9));
        credits.setBounds(25, 363, 290, 20);
        credits.setEnabled(true);
        credits.setVisible(true);
        mainWindow.add(credits);

        //Eye
        eye.setBounds(5, 362, 20, 20);
        eye.setEnabled(true);
        eye.setVisible(true);
        mainWindow.add(eye);

    }

    //Game Initialization
    public void newGame() {

        gameState = 0;
        mainWindow.getContentPane().setBackground(Color.WHITE);

        menuHome.setEnabled(true);
        menuResume.setVisible(false);
        menuStart.setText("Restart");

        //Removes Unneeded GUI
        title.setVisible(false);
        buttonResume.setVisible(false);
        buttonNewGame.setVisible(false);
        buttonExit.setVisible(false);
        eye.setVisible(false);
        credits.setVisible(false);

        endPlayAgain.setVisible(false);
        endHome.setVisible(false);

        //Resets Game Information
        att = 1;
        lives = 10;
        incorrectGuesses = "";

        //Top Line
        lineTop.setText("-------------------------------------------------------------------------------");
        lineTop.setBounds(20, 30, 320, 30);

        //Attempt Count
        attemptCount.setBounds(25, 50, 290, 30);

        //Life Count
        lifeCount.setBounds(240, 50, 290, 30);

        //Word Progression
        progressWord.setText("Word to Guess: " + progress);
        progressWord.setBounds(14, 240, 320, 30);
        progressWord.setHorizontalAlignment(SwingConstants.CENTER);

        //Bottom Line
        lineBottom.setText("---------------------------------------------------------------------");
        lineBottom.setBounds(20, 260, 320, 30);

        //Guide
        guide.setBounds(14, 280, 320, 30);
        guide.setHorizontalAlignment(SwingConstants.CENTER);

        //Input
        entry.setText("Enter your guess");
        entry.setMargin(new Insets(0, 10, 0, 10));
        entry.setBounds(68, 315, 110, 30);

        //Submission Button
        submit.setBounds(193, 315, 90, 29);
        submit.setFocusPainted(false);

        //Play Again Button
        endPlayAgain.setBounds(75, 315, 110, 29);
        endPlayAgain.setFocusPainted(false);

        //Home Button
        endHome.setBounds(200, 315, 80, 29);
        endHome.setFocusPainted(false);

        //Adds Game GUI to Window
        mainWindow.add(hint);
        mainWindow.add(lineTop);
        mainWindow.add(attemptCount);
        mainWindow.add(lifeCount);
        mainWindow.add(progressVisual);
        mainWindow.add(progressWord);
        mainWindow.add(lineBottom);
        mainWindow.add(guide);
        mainWindow.add(entry);
        mainWindow.add(submit);
        mainWindow.add(endPlayAgain);
        mainWindow.add(endHome);


        //Enables Game GUI
        hint.setVisible(true);
        lineTop.setVisible(true);
        attemptCount.setVisible(true);
        lifeCount.setVisible(true);
        progressVisual.setVisible(true);
        progressWord.setVisible(true);
        lineBottom.setVisible(true);
        guide.setVisible(true);
        entry.setVisible(true);
        submit.setVisible(true);

        guide.setText("Please enter a letter: ");
        guide.setForeground(Color.darkGray);

        //Initializes Words and Updates Variables
        printWord();
        updateVariables();

    }

    //Resume Game GUI
    public void resumeGame() {

        mainWindow.getContentPane().setBackground(Color.WHITE);

        menuHome.setEnabled(true);
        menuResume.setVisible(false);

        //Removes Home GUI
        title.setVisible(false);
        buttonResume.setVisible(false);
        buttonNewGame.setVisible(false);
        buttonExit.setVisible(false);
        eye.setVisible(false);
        credits.setVisible(false);

        //Enables Game GUI
        hint.setVisible(true);
        lineTop.setVisible(true);
        attemptCount.setVisible(true);
        lifeCount.setVisible(true);
        progressVisual.setVisible(true);
        progressWord.setVisible(true);
        lineBottom.setVisible(true);
        guide.setVisible(true);

        /*-----Checks if Game is Complete-----*/
        if (gameState == 1 || gameState == 2) {
            entry.setVisible(false);
            submit.setVisible(false);
            endPlayAgain.setVisible(true);
            endHome.setVisible(true);
        } else {
            entry.setVisible(true);
            submit.setVisible(true);
            endPlayAgain.setVisible(false);
            endHome.setVisible(false);
        }

    }

    //Information Updater
    public void updateVariables() {
        reprintWord();
        entry.setText(null);
        scoreCount.setText("W: " + wins + " L: " + losses + "  ");
        progressWord.setText("Word to Guess: " + progress);
        attemptCount.setText("Attempt #" + att);
        lifeCount.setText("Lives left: " + lives);
    }

    //Random Word Selector from WordList
    public String getWord() {
        wordFile = textFile();

        int n = wordFile.length;
        int r = shuffle.nextInt(n);
        String word = wordFile[r];

        return word;
    }

    //File Operations
    public String[] textFile() {
        //BufferedReader reader = null;
        String wordBank = "src/hangman.txt";
        File file = new File(wordBank);
        ArrayList<String> wordList = new ArrayList<String>();
        try (Scanner input= new Scanner(file)){

            while (input.hasNext()) {
                wordList.add(input.next());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
            }
        return wordList.toArray(new String[wordList.size()]);
    }

    public static void write2File(String word) {
        try {

            File file = new File("src/hangman.txt");
            FileWriter out=new FileWriter(file, true);

            out.write("\n" + word);
            out.close();
        } catch (IOException e) {
            System.out.println("Exception Occured" + e);
        }
    }


    //Replaces Selected Word With Question Marks
    public void printWord() {
        progress = "";
        selectedWord = getWord();
        progressDone = selectedWord.replaceAll(".(?!$)", "$0 ");
        wordToGuess = selectedWord.toLowerCase().toCharArray();
        guesses = new char[wordToGuess.length];
        for (int i = 0; i < guesses.length; i++) {
            guesses[i] = '?';
            progress += guesses[i] + " ";
        }
    }

    //Updates Progress
    public void reprintWord() {
        progress = "";
        for (int i = 0; i < guesses.length; i++) {
            progress += guesses[i] + " ";
        }
    }

    //Displays Guessed Word
    public void displayIncorrect() {
        progressWord.setText("Incorrect Guesses: " + incorrectGuesses.toLowerCase());
        javax.swing.Timer timer = new javax.swing.Timer(3000, e -> {
            progressWord.setText("Word to Guess: " + progress);
        });
        timer.setRepeats(false);
        timer.start();
    }

    //Action Listener
    public void actionPerformed(ActionEvent e) {

        //Menu Home Action
        if (e.getSource() == menuHome || e.getSource() == endHome) {
            //Removes Menu Bar
            menuBar.setVisible(false);
            menuResume.setVisible(true);
            mainWindow.setTitle("Home");
            System.out.println("\nGoing to Home Screen...");
            homeScreen();
        }

        //Menu Resume Action
        if (e.getSource() == menuResume || e.getSource() == buttonResume) {
            //Adds Menu Bar
            menuBar.setVisible(true);
            mainWindow.setTitle("Game #" + gameInstance);
            System.out.println("\nResuming Game...");
            resumeGame();
        }

        //Menu New Game Action
        else if (e.getSource() == menuStart || e.getSource() == buttonNewGame || e.getSource() == endPlayAgain) {
            //Adds Menu Bar
            menuBar.setVisible(true);

            gameInstance++;
            mainWindow.setTitle("Game #" + gameInstance);
            System.out.println("\nStarting New Game...");
            newGame();
        }

        //Menu Exit Action
        else if (e.getSource() == buttonExit) {
            mainWindow.setTitle("Exit");
            System.out.println("\nExiting Game...");
            JOptionPane.showMessageDialog(mainWindow, "   Thanks for playing! Stay safe!", "Message", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }

        //Submit Button Actions
        else if (e.getSource() == submit || e.getSource() == entry) {

            //Gets Input From TextField
            input = entry.getText();

            //Runs if Selected Word is Incomplete
            if (!Arrays.equals(guesses, wordToGuess)) {

                //Error Handler
                if (input.length() != 1 || illegalChar.contains(input) || incorrectGuesses.contains(input.toUpperCase()) || progress.toUpperCase().contains(input.toUpperCase())) {
                    /*-----Secret Phrases-----*/
                    //to enter a cheat-code
                    if (input.equalsIgnoreCase("answer")) {
                        guide.setForeground(Color.darkGray);
                        guide.setText("The word is: \"" + selectedWord + "\". You cheater haha ;)");
                        updateVariables();
                    } else if (input.equalsIgnoreCase("heal")) {
                        lives = 10;
                        guide.setForeground(Color.PINK);
                        guide.setText("Healed.");
                        updateVariables();
                    } else if (input.equalsIgnoreCase("incorrect")) {
                        updateVariables();
                        displayIncorrect();
                    } else if (input.equalsIgnoreCase("end")) {
                        lives = 0;
                        updateVariables();
                    }
                    /*-----Errors-----*/
                    else if (input.trim().isEmpty()) {
                        guide.setForeground(Color.GRAY);
                        guide.setText("Blank input detected! Try again. ");
                        updateVariables();
                    } else if (illegalChar.contains(input.trim())) {
                        guide.setForeground(Color.GRAY);
                        guide.setText("Illegal character detected! Try again. ");
                        updateVariables();
                    } else if (input.length() > 1) {
                        guide.setForeground(Color.GRAY);
                        guide.setText("Multiple inputs detected! Try again. ");
                        updateVariables();
                    } else if (incorrectGuesses.contains(input.toUpperCase())) {
                        guide.setForeground(Color.GRAY);
                        guide.setText("Letter is already guessed! Try another one.");
                        updateVariables();
                        displayIncorrect();
                    } else if (progress.toUpperCase().contains(input.toUpperCase())) {
                        guide.setForeground(Color.GRAY);
                        guide.setText("Letter is already guessed! Try another one.");
                        updateVariables();
                    } else {
                        System.out.println("\nUnexpected Input Error Found.");
                        System.exit(-1);
                    }
                }

                //Guess Checker (Correct)
                else if (selectedWord.toLowerCase().contains(input.toLowerCase())) {
                    att++;
                    guide.setForeground(Color.darkGray);
                    for (int i = 0; i < wordToGuess.length; i++) {
                        if (input.toLowerCase().charAt(0) == wordToGuess[i]) {
                            guesses[i] = input.toLowerCase().charAt(0);
                            guide.setText("Letter \"" + input.toUpperCase() + "\" is correct. Good job!");
                        }
                    }
                    updateVariables();
                }

                //Guess Checker (Incorrect)
                else if (!selectedWord.toLowerCase().contains(input.toLowerCase())) {
                    att++;
                    lives--;
                    guide.setForeground(Color.darkGray);
                    guide.setText("Letter \"" + input.toUpperCase() + "\" does not exist. Try again!");


                    //Updates Incorrect Guesses
                    if (!selectedWord.toUpperCase().contains(input.toUpperCase())) {
                        incorrectGuesses += input.toUpperCase();
                        if (lives != 0 && !Arrays.equals(guesses, wordToGuess)) {
                            incorrectGuesses += ", ";
                        }
                    }

                    updateVariables();
                }

                //Game Over
                if (lives == 0) {
                    entry.setVisible(false);
                    submit.setVisible(false);
                    endPlayAgain.setVisible(true);
                    endHome.setVisible(true);

                    losses++;
                    gameState = 2;
                    updateVariables();
                    progressWord.setText("Word to Guess: " + progressDone);
                    guide.setForeground(Color.RED);
                    guide.setText("Game over! You've used up all your lives.");
                    JOptionPane.showMessageDialog(mainWindow, " You died! The word was \"" + selectedWord + "\".", "Message", JOptionPane.PLAIN_MESSAGE);
                    String m = JOptionPane.showInputDialog(mainWindow, "Please enter a Word to be added to our database.", "New Word", JOptionPane.QUESTION_MESSAGE);
                    write2File(m);
                    menuStart.setText("New Game");
                }

            }

            //Runs if Selected Word is Complete
            if (Arrays.equals(guesses, wordToGuess) || input.trim().equalsIgnoreCase(selectedWord)) {
                entry.setVisible(false);
                submit.setVisible(false);
                endPlayAgain.setVisible(true);
                endHome.setVisible(true);

                wins++;
                gameState = 1;
                updateVariables();
                progressWord.setText("Word to Guess: " + progressDone);
                guide.setForeground(Color.GREEN);
                guide.setText("Well done, you get to live another day!");
                JOptionPane.showMessageDialog(mainWindow, "You got the word in " + att + " attempts with " + lives + " lives left. Congratulations!", "Message", JOptionPane.PLAIN_MESSAGE);
                String m = JOptionPane.showInputDialog(mainWindow, "Please enter a Word to be added to our database.", "New Word", JOptionPane.QUESTION_MESSAGE);
                write2File(m);
                menuStart.setText("New Game");
            }

        }
    }
}