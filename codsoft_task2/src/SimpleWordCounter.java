import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleWordCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Prompt the user to enter text or provide a file path
        System.out.print("Enter text or provide a file path: ");
        String userInput = scanner.nextLine();

        // Step 2: Read the input text or file and store it in a string
        String text = userInput.contains(".txt") ? readTextFromFile(userInput) : userInput;

        // Step 3: Count words and display the result
        displayWordCount(text);
    }

    private static String readTextFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (Scanner fileScanner = new Scanner(filePath)) {
            while (fileScanner.hasNext()) {
                content.append(fileScanner.next()).append(" ");
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return content.toString();
    }

    private static void displayWordCount(String text) {
        // Step 4: Count words using a simple map
        Map<String, Integer> wordCounter = new HashMap<>();
        for (String word : text.toLowerCase().split("\\W+")) {
            wordCounter.put(word, wordCounter.getOrDefault(word, 0) + 1);
        }

        // Step 5: Display the total count of words to the user
        System.out.println("Total words: " + wordCounter.values().stream().mapToInt(Integer::intValue).sum());

        // Step 6: Display the number of unique words
        System.out.println("Unique words: " + wordCounter.size());

        // Display frequency of each word (optional)
        System.out.println("\nWord frequencies:");
        wordCounter.forEach((word, count) -> System.out.println(word + ": " + count));
    }
}
