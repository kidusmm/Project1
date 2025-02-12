/*
 * Class: CMSC203, CRN #31274
 * Instructor: Prof. Ahmed Tarek
 * Description: ESPGame program that tests the user's extrasensory perception (ESP) by having them guess randomly selected colors from a file. The game runs for three rounds, keeps track of correct guesses, and allows the user to play again or exit.
 * Due: 02/11/2025
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently. 
 * I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * 
 * Print your Name here: Kidus M Mandefro
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ESPGame {
    public static void main(String[] args) throws IOException {
        final String OUTPUT_FILE = "EspGameResults.txt";
        final int MAX_ATTEMPTS = 3;

        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("CMSC203 Assignment1: Test your ESP skills!");
        System.out.println("Welcome to ESP - extrasensory perception!");

        boolean continueGame = true;
        int correctGuesses = 0;

        while (continueGame) {
            System.out.println("\nWould you please choose one of the 4 options from the menu:\n");
            System.out.println("1 - Read and display the first 16 colors from a file.");
            System.out.println("2 - Read and display the first 10 colors from a file.");
            System.out.println("3 - Read and display the first 5 colors from a file.");
            System.out.println("4 - Exit the program");

            System.out.print("Enter the option: ");
            int menuChoice = input.nextInt();
            input.nextLine();

            if (menuChoice == 4) {
                // Prompt user for details before exiting
                System.out.print("\nEnter your name: ");
                String userName = input.nextLine();

                System.out.print("Describe yourself: ");
                String userDescription = input.nextLine();

                String date = "02/11"; // Hardcoded due date

                // Display user details
                System.out.println("\nDue Date: " + date);
                System.out.println("Username: " + userName);
                System.out.println("User Description: " + userDescription);
                System.out.println("Date: " + date);

                // Write results to EspGameResults.txt
                FileWriter writer = new FileWriter(OUTPUT_FILE);
                writer.write("Game Over\n");
                writer.write("You guessed " + correctGuesses + " out of " + MAX_ATTEMPTS + " colors correctly.\n");
                writer.write("Due Date: " + date + "\n");
                writer.write("Username: " + userName + "\n");
                writer.write("User Description: " + userDescription + "\n");
                writer.write("Date: " + date + "\n");
                writer.close();

                System.out.println("\nResults have been saved to " + OUTPUT_FILE);
                break;
            }

            System.out.print("Enter the filename: ");
            String filename = input.nextLine();
            File file = new File(filename);

            if (!file.exists()) {
                System.out.println("File not found.");
                continue;
            }

            int numColorsToRead = (menuChoice == 1) ? 16 : (menuChoice == 2) ? 10 : 5;

            Scanner fileReader = new Scanner(file);
            System.out.println("\nAvailable colors from file:");

            int colorIndex = 1;
            String[] colors = new String[numColorsToRead];

            while (fileReader.hasNextLine() && colorIndex <= numColorsToRead) {
                colors[colorIndex - 1] = fileReader.nextLine();
                System.out.println(colorIndex + " " + colors[colorIndex - 1]);
                colorIndex++;
            }
            fileReader.close();

            correctGuesses = 0; // Reset for each session

            for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
                System.out.println("\nRound " + attempt);
                System.out.println("I am thinking of a color from the list above.");
                System.out.print("Enter your guess: ");

                String userGuess = input.nextLine().trim();

                // Randomly select a color
                int randomIndex = rand.nextInt(numColorsToRead);
                String selectedColor = colors[randomIndex];

                if (userGuess.equalsIgnoreCase(selectedColor)) {
                    correctGuesses++;
                }

                System.out.println("I was thinking of " + selectedColor + ".");
            }

            System.out.println("\nGame Over");
            System.out.println("You guessed " + correctGuesses + " out of " + MAX_ATTEMPTS + " colors correctly.\n");

            System.out.print("Would you like to continue the game? Type Yes/No: ");
            String continueResponse = input.nextLine().trim();
            if (!continueResponse.equalsIgnoreCase("Yes")) {
                continueGame = false;
            }
        }
        input.close();
    }
}
