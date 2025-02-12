/*
 * Class: CMSC203 
 * Instructor: Prof. Ahmed Tarek
 * Description: 
 * Due: 02/11/2025
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Kidus M Mandefro
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ESPGame {
    public static void main(String[] args) throws IOException {
        final String COLORS_FILE = "colors.txt";
        final String OUTPUT_FILE = "EspGameResults.txt";
        final int MAX_ATTEMPTS = 3;
        
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.println("CMSC203 Assignment1: Test your ESP skills!");
        System.out.println("Welcome to ESP - extrasensory perception!");
        
        boolean continueGame = true;
        while (continueGame) {
            System.out.println("\nWould you please choose one of the 4 options from the menu:\n");
            System.out.println("1-\tread and display on the screen first 16 names of colors from a file colors.txt, so the player can select one of them names of colors.");
            System.out.println("2-\tread and display on the screen first 10 names of colors from a file colors.txt, so the player can select one of them names of colors.");
            System.out.println("3-\tread and display on the screen first 5 names of colors from a file colors.txt, so the player can select one of them names of colors.");
            System.out.println("4-\tExit from a program");
            
            System.out.print("Enter the option: ");
            int menuChoice = input.nextInt();
            input.nextLine();
            
            if (menuChoice == 4) {
                System.out.println("Exiting program.");
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
            System.out.println("\nThere are sixteen colors from a file:");
            int colorIndex = 1;
            String color = "", selectedColor = "";
            
            while (fileReader.hasNextLine() && colorIndex <= numColorsToRead) {
                color = fileReader.nextLine();
                System.out.println(colorIndex + " " + color);
                colorIndex++;
            }
            fileReader.close();
            
            int correctGuesses = 0;
            
            for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
                System.out.println("\nRound " + attempt);
                System.out.println("\nI am thinking of a color.");
                System.out.println("Is it one of list of colors above?  ");
                
                fileReader = new Scanner(new File(filename));
                int randomNumber = rand.nextInt(16) + 1;
                colorIndex = 1;
                
                while (fileReader.hasNextLine() && colorIndex <= randomNumber) {
                    selectedColor = fileReader.nextLine();
                    colorIndex++;
                }
                fileReader.close();
                
                System.out.print("Enter your guess: \n");
                String userGuess = input.nextLine().trim();
                
                if (userGuess.equalsIgnoreCase(selectedColor)) {
                    correctGuesses++;
                }
                
                System.out.println("\nI was thinking of " + selectedColor + ".");
            }
            
            System.out.println("\nGame Over");
            System.out.println("You guessed " + correctGuesses + " out of 3 colors correctly.\n");
            
            System.out.print("Would you like to continue a Game? Type Yes/No\n");
            String continueResponse = input.nextLine().trim();
            if (!continueResponse.equalsIgnoreCase("Yes")) {
                continueGame = false;
            }
        }
        input.close();
    }
}
