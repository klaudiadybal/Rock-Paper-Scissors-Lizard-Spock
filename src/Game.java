import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private final String FILE_PATH = "C:\\Users\\klaud\\IdeaProjects\\Rock-Paper-Scissors (Java)\\Rock-Paper-Scissors (Java)\\task\\src\\rockpaperscissors\\rating.txt";
    private final static Scanner scanner = new Scanner(System.in);

    public int updateScore(int compareMoves, int score) {
        switch (compareMoves) {
            case 0 -> score += 50;
            case 1 -> score += 100;
            case -1 -> score += 0;
        }
        return score;
    }

    public int getCurrentScore(String name) {
        File file = new File(FILE_PATH);
        int currentScore = 0;
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                if (parts[0].equals(name)) {
                    currentScore = Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("An error appeared" + e.getMessage());
        }

        return currentScore;

    }

    public static void main(String[] args) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.printf("Hello, %s%n", name);
        System.out.println("If you want to play a standard game, press enter. " +
                "If you want to switch to a custom game, please enter options separated by commas.");

        String input = scanner.nextLine();
        if (input.isEmpty()) {
            StandardGame standardGame = new StandardGame();
            standardGame.standardGame(name);
        } else {
            CustomGame customGame = new CustomGame();
            customGame.customGame(name, input);
        }
    }
}
