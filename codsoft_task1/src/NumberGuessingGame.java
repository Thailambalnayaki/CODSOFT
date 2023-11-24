import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        int min = 1;
        int max = 100;
        int randomNumber = random.nextInt(max - min + 1) + min;
        Scanner scanner = new Scanner(System.in);
        int numberOfTries = 0;
        boolean hasGuessedCorrectly = false;
        System.out.println("Welcome to the number guessing game");
        System.out.println("Try to guess the number between" + min + "and" + max);
        while (!hasGuessedCorrectly) {
            System.out.println("Enter your guess");
            {
                int userGuess = scanner.nextInt();
                numberOfTries++;
                if (userGuess == randomNumber) {
                    hasGuessedCorrectly = true;
                    System.out.println("congratulations! you guessed the correct number in " + numberOfTries + "tries");
                } else if (userGuess < randomNumber) {
                    System.out.println("Try a higher number than this");
                } else {
                    System.out.println("Try a lower number than this");
                }

        }
    }}}





