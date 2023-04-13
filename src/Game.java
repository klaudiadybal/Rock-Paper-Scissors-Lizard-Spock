import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    private final static Scanner scanner = new Scanner(System.in);

    public int updateScore(int compareMoves, int score) {
        switch (compareMoves) {
            case 0 -> score += 50;
            case 1 -> score += 100;
            case -1 -> score += 0;
        }
        return score;
    }

    public void resultMessage (int compareMoves, String computerMove) {
        String message = "";
        switch (compareMoves) {
            case 0 -> message = String.format("There is a draw (%s)", computerMove);
            case 1 -> message = String.format("Well done. The computer chose %s and failed",
                    computerMove);
            case -1 -> message = String.format("Sorry, but the computer chose %s", computerMove);
        }

        System.out.println(message);
    }

    public boolean checkInput(String playerInput, int score) {
        if (playerInput.equals("!rating")) {
            System.out.println("Win - 100, draw - 50, lose - 0");
            System.out.printf("Your rating: %d%n", score);
            return true;

        } else if (playerInput.equals("!exit")) {
            System.out.println("Bye!");
            exit(0);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("If you want to play a standard game, press enter. " +
                "If you want to switch to a custom game, please enter options separated by commas.");

        String input = scanner.nextLine();
        if (input.isEmpty()) {
            StandardGame standardGame = new StandardGame();
            standardGame.standardGame();
        } else {
            CustomGame customGame = new CustomGame();
            customGame.customGame(input);
        }
    }
}
