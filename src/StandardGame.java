import java.util.Random;
import java.util.Scanner;

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

    public void standardGame() {
        Random random = new Random();
        System.out.println("Okay, let's start");
        System.out.println("You can type !exit to exit and !rating for your rating");

        int score = 0;
        Moves[] moves = Moves.values();
        String playerInput = "";
        int compareMoves = 0;

        while (!playerInput.equals("!exit")) {
            Moves computerMove = moves[random.nextInt(moves.length)];
            playerInput = scanner.nextLine();
            if(checkInput(playerInput, score)) {
                continue;
            }

            try {
                Moves playerMove = Moves.valueOf(playerInput.toUpperCase());
                compareMoves = playerMove.compareMoves(computerMove);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid argument");
                continue;
            }

            String computerMoveMessage = computerMove.toString().toLowerCase();
            resultMessage(compareMoves, computerMoveMessage);

            score = updateScore(compareMoves, score);

        }
    }
}
