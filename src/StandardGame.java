import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class StandardGame extends Game {
    private final static Scanner scanner = new Scanner(System.in);
    enum Moves {
        ROCK, PAPER, SCISSORS;

        public int compareMoves (Moves otherMove) {
            if (this == otherMove) {
                return 0;
            }

            switch (this) {
                case ROCK -> {
                    return (otherMove == SCISSORS ? 1 : -1);
                } case PAPER -> {
                    return (otherMove == ROCK ? 1 : -1);
                } case SCISSORS -> {
                    return (otherMove == PAPER ? 1 : -1);
                }
            }
            throw new IllegalStateException("Invalid input");
        }

    }

    public void standardGame(String name) {
        Random random = new Random();
        System.out.println("Okay, let's start");
        System.out.println("You can type !exit to exit and !rating for your rating");

        int score = getCurrentScore(name);
        Moves[] moves = Moves.values();
        String playerInput = "";
        int compareMoves = 0;

        while (!playerInput.equals("!exit")) {
            Moves computerMove = moves[random.nextInt(moves.length)];
            playerInput = scanner.nextLine();

            if (playerInput.equals("!exit")) {
                System.out.println("Bye!");
                exit(0);
            } else if (playerInput.equals("!rating")) {
                System.out.printf("Your rating: %d%n", score);
                continue;
            }

            try {
                Moves playerMove = Moves.valueOf(playerInput.toUpperCase());
                compareMoves = playerMove.compareMoves(computerMove);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid argument");
                continue;
            }

            String message = "";
            String computerMoveMessage = computerMove.toString().toLowerCase();
            switch (compareMoves) {
                case 0 -> message = String.format("There is a draw (%s)", computerMoveMessage);
                case 1 -> message = String.format("Well done. The computer chose %s and failed",
                        computerMoveMessage);
                case -1 -> message = String.format("Sorry, but the computer chose %s", computerMoveMessage);
            }

            System.out.println(message);
            score = updateScore(compareMoves, score);

        }
    }
}
