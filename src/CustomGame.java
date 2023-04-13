import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CustomGame extends Game {
    private final static Scanner scanner = new Scanner(System.in);

    public int customCompareMove(String computerMove, String[] winnersAgainstPlayer, String[] losersAgainstPlayer) {
        int returnStatement = 0;

        for (String winner : winnersAgainstPlayer) {
            if (computerMove.equals(winner)) {
                returnStatement = -1;
                break;
            }
        }

        for (String loser : losersAgainstPlayer) {
            if (computerMove.equals(loser)) {
                returnStatement = 1;
                break;
            }
        }

        return returnStatement;
    }

    public void customGame(String input) {
        System.out.println("Okay, let's start");
        System.out.println("You can type !exit to exit and !rating for your rating");

        String[] moves = input.split(",");
        String playerMove = "";

        int score = 0;
        while (!playerMove.equals("!exit")) {
            playerMove = scanner.nextLine();
            if(checkInput(playerMove, score)) {
                continue;
            }

            while (!Arrays.asList(moves).contains(playerMove)) {
                if(checkInput(playerMove, score)) {
                    continue;
                }
                System.out.println("Invalid move");
                playerMove = scanner.nextLine();
            }

            Random random = new Random();
            int randomInt = random.nextInt(moves.length);
            String computerMove = moves[randomInt];

            int indexOfPlayerMove = 0;
            for (int i = 0; i < moves.length; i++) {
                if (playerMove.equals(moves[i])) {
                    indexOfPlayerMove = i;
                }
            }

            String[] winnersAgainstPlayer = new String[moves.length / 2];
            int i = indexOfPlayerMove + 1;
            for (int j = 0; j < winnersAgainstPlayer.length; j++) {
                if (i == moves.length) {
                    i = 0;
                }
                winnersAgainstPlayer[j] = moves[i];
                i++;
            }

            String[] losersAgainstPlayer = new String[moves.length / 2];
            int index = indexOfPlayerMove - 1;
            for (int j = 0; j < losersAgainstPlayer.length; j++) {
                if (index == -1) {
                    index = moves.length - 1;
                }
                losersAgainstPlayer[j] = moves[index];
                index--;
            }

            int result = customCompareMove(computerMove, winnersAgainstPlayer, losersAgainstPlayer);

            resultMessage(result, computerMove);
            score = updateScore(result, score);
        }

    }
}
