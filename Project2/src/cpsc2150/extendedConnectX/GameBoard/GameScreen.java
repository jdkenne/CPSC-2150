//Joshua Kennerly, CPSC 2150, Section 003, 02-07-2021
package cpsc2150.extendedConnectX.GameBoard;

import java.util.*;

/**
 * @invariants 0 <= row <= 5 and 0 <= col <= 8
 */
public class GameScreen {
    public static void main(String[] args) {
        char player1, player2;
        int colNum = 0;
        System.out.println("Would you like to play ConnectX?");
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                player1 = 'X';
                player2 = 'O';
                GameBoard board = new GameBoard();
                boolean validInput;
                boolean status = false;
                int turnNum = 0;
                boolean playAgain = false;
                while (!playAgain) {
                    while (!status) {
                        System.out.println(board);
                        turnNum += 1;
                        validInput = false;
                        if ((turnNum % 2) == 1) {
                            while (!validInput) {
                                System.out.println("Player " + player1 + ", please enter what column you would like to place your token\n");
                                input = scanner.nextLine();
                                colNum = Integer.parseInt(input);
                                if ((colNum >= 0) && (colNum <= 8)) {
                                    validInput = true;
                                } else {
                                    System.out.println(board);
                                    System.out.println("This was an invalid input. Try again!\n");
                                }
                            }
                            board.placeToken(player1, colNum);
                        } else {
                            while (!validInput) {
                                System.out.println("Player " + player2 + ", please enter what column you would like to place your token\n");
                                input = scanner.nextLine();
                                colNum = Integer.parseInt(input);
                                if ((colNum >= 0) && (colNum <= 8)) {
                                    validInput = true;
                                } else {
                                    System.out.println(board);
                                    System.out.println("This was an invalid input. Try again!\n");
                                }
                            }
                            board.placeToken(player2, colNum);
                        }
                        if (board.checkForWin(colNum) || board.checkTie()) {
                            status = true;
                        }
                    }
                    if ((turnNum % 2) == 1 && !(board.checkTie())) {
                        System.out.println(board);
                        System.out.println("Player " + player1 + ", you won!");
                    } else if ((turnNum % 2) == 0 && !(board.checkTie())) {
                        System.out.println(board);
                        System.out.println("Player " + player2 + ", you won!");
                    } else {
                        System.out.println(board);
                        System.out.println("No one won!");
                    }
                    System.out.println("Would you like to play again?");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("yes")) {
                        board = new GameBoard();
                        status = false;
                    }
                    if (input.equalsIgnoreCase("no")) {
                        playAgain = true;
                        System.out.println("Thanks for playing!");
                    } else {
                        board = new GameBoard();
                    }
                }
            } else {
                System.out.println("That's okay!");
            }
        }
    }

}
