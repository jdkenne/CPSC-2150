//Joshua Kennerly, CPSC 2150, Section 003, 02-07-2021
package cpsc2150.extendedConnectX.GameBoard;

import java.util.*;

/**
 * @invariants 0 <= row <= 5 and 0 <= col <= 8
 */
public class GameScreen {
    private static IGameBoard Game;

    public static void main(String[] args) {
        int MAX_SIZE = 100;
        int MIN_SIZE = 3;
        int MAX_WIN_CONDITION = 25;
        int MIN_WIN_CONDITION = 3;
        int MAX_PLAYERS = 10;
        int MIN_PLAYERS = 2;
        int minCol = 0;
        int maxCol;
        int row;
        int col;
        int win;
        int playerNum;
        char gameType;
        List<Character> players = new ArrayList<>();
        String cont = "";
        boolean gamePlaying = true;
        Scanner input = new Scanner(System.in);

        while (gamePlaying) {

            row = 0;
            col = 0;
            win = 0;
            playerNum = 0;
            gameType = ' ';
            players.clear();

            while (playerNum < MIN_PLAYERS || playerNum > MAX_PLAYERS) {
                System.out.println("How many players?");
                playerNum = input.nextInt();
                if (playerNum < MIN_PLAYERS) {
                    System.out.println("Must be at least 2 players");
                }
                if (playerNum > MAX_PLAYERS) {
                    System.out.println("Must be 10 players or fewer");
                }
            }

            while (players.size() != playerNum) {
                System.out.println("Enter the character to represent player " + (players.size() + 1));

                Character temp = Character.toUpperCase(input.next().charAt(0));

                if (players.contains(temp)) {
                    System.out.println(temp + " is already taken as a player token!");
                } else {
                players.add(temp);
                }
            }


            while (row < MIN_SIZE || row > MAX_SIZE) {
                System.out.println("How many rows?");
                row = input.nextInt();
                if (row < MIN_SIZE || row > MAX_SIZE) {
                    System.out.println("Rows must be between 3 and 100");
                }
            }

            while (col < MIN_SIZE || col > MAX_SIZE) {
                System.out.println("How many columns?");
                col = input.nextInt();
                if (col < MIN_SIZE || col > MAX_SIZE) {
                    System.out.println("Columns must be between 3 and 100");
                }
            }

            while (win > row || win > col || win > MAX_WIN_CONDITION || win < MIN_WIN_CONDITION) {
                System.out.println("How many in a row to win?");
                win = input.nextInt();
                if (win > row || win > col || win > MAX_WIN_CONDITION || win < MIN_WIN_CONDITION) {
                    System.out.println("Win condition must be between 3 and 25, and must be less than both rows and columns");
                }
            }


            while (gameType != 'F' && gameType != 'M') {
                System.out.println("Would you like a fast game (F/f) or a memory efficient game (M/m)?");
                gameType = Character.toUpperCase(input.next().charAt(0));
                if (gameType == 'F') {
                    Game = new GameBoard(row, col, win);
                } else if (gameType == 'M') {
                    Game = new GameBoardMem(row, col, win);
                } else {
                    System.out.println("Please enter F or M");
                }
            }

            System.out.println(Game);

            playerNum = 0;
            maxCol = Game.getNumColumns();

            while (true) {
                if (playerNum == players.size() - 1) {
                    playerNum = 0;
                } else {
                    playerNum++;
                }

                System.out.println("Player " + players.get(playerNum) + ", what column do you want to place your token in?");
                col = input.nextInt();
                if (col < minCol) {
                    col = minCol;
                    System.out.println("Column cannot be less than 0");
                    playerNum--;
                } else if (col > maxCol) {
                    col = Game.getNumColumns();
                    System.out.println("Column cannot be greater than " + Game.getNumColumns());
                    playerNum--;
                } else if (!Game.checkIfFree(col)) {
                    System.out.println("Column is full");
                    playerNum--;
                } else {
                    Game.placeToken(players.get(playerNum), col);
                    System.out.println(Game);
                }
                if (Game.checkForWin(col)) {
                    System.out.println("Player " + players.get(playerNum) + " won!");
                    break;
                } else if(Game.checkTie()) {
                    System.out.println("It's a tie!");
                    break;
                }
            }

            boolean answered = false;
            while (!answered) {
                System.out.println("Would you like to play again?(Y/N)");
                cont = input.next();
                if (cont.equals("N") || cont.equals("n") || cont.equals("Y") || cont.equals("y")) {
                    answered = true;
                }
            }
            if (cont.equals("N") || cont.equals("n")) {
                gamePlaying = false;
            }
        }
    }
}
