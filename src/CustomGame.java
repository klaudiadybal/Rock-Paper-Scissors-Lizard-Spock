import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class CustomGame extends Game {
    private final static Scanner scanner = new Scanner(System.in);

    public int customCompareMove(String computerMove, String[] winnersAgainstPlayer, String[] losersAgainstPlayer) {
        int returnStatement = 0;

        for (int i = 0; i < winnersAgainstPlayer.length; i++) {
            if (computerMove.equals(winnersAgainstPlayer[i])) {
                returnStatement = -1;
            }
        }

        for (int i = 0; i < losersAgainstPlayer.length; i++) {
            if (computerMove.equals(losersAgainstPlayer[i])) {
                returnStatement = 1;
            }
        }

        return returnStatement;
    }

    public void customGame(String name, String input) {
        System.out.println("Okay, let's start");
        System.out.println("You can type !exit to exit and !rating for your rating");

        String[] moves = input.split(",");

        String playerMove = "";

        int score = getCurrentScore(name);
        while (!playerMove.equals("!exit")) {
            playerMove = scanner.nextLine();

            if (playerMove.equals("!rating")) {
                System.out.printf("Your rating: %d%n", score);
                continue;
            }

            if (playerMove.equals("!exit")) {
                System.out.println("Bye!");
                exit(0);
            }

            while (!Arrays.asList(moves).contains(playerMove)) {
                System.out.println("Invalid move");
                playerMove = scanner.nextLine();
            }

            Random random = new Random();
            int randomInt = random.nextInt(moves.length);
            String computerMove = moves[randomInt];

//            if (playerMove.equals(computerMove)) {
//                System.out.println("Draw");
//            }

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

//            System.out.println("Winners against " + playerMove + ":");
//            for (int j = 0; j < winnersAgainstPlayer.length; j++) {
//                System.out.printf("%s ", winnersAgainstPlayer[j]);
//            }
//            System.out.println();

            String[] losersAgainstPlayer = new String[moves.length / 2];
            int index = indexOfPlayerMove - 1;
            for (int j = 0; j < losersAgainstPlayer.length; j++) {
                if (index == -1) {
                    index = moves.length - 1;
                }
                losersAgainstPlayer[j] = moves[index];
                index--;
            }

//            System.out.println("Losers against " + playerMove + ":");
//            for (int j = 0; j < losersAgainstPlayer.length; j++) {
//                System.out.printf("%s ", losersAgainstPlayer[j]);
//            }
//            System.out.println();

            String message = "";
            switch (customCompareMove(computerMove, winnersAgainstPlayer, losersAgainstPlayer)) {
                case 0 -> message = String.format("There is a draw (%s)", computerMove);
                case 1 -> message = String.format("Well done. The computer chose %s and failed",
                        computerMove);
                case -1 -> message = String.format("Sorry, but the computer chose %s", computerMove);
            }
            System.out.println(message);
            score = updateScore(customCompareMove(computerMove, winnersAgainstPlayer, losersAgainstPlayer), score);
        }

    }
}
