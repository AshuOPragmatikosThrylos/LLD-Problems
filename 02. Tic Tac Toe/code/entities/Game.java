package entities;

import java.util.Scanner;
import interfaces.WinningStrategyInterface;

public class Game {
    private final Board board;
    private final Player[] players;
    private final WinningStrategyInterface winningStrategy;
    private int currentPlayerIndex = 0;

    public Game(int size, Player[] players, WinningStrategyInterface strategy) {
        this.board = new Board(size);
        this.players = players;
        this.winningStrategy = strategy;
    }

    public void play() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean won = false;

            while (!won) {
                board.displayBoard();
                Player currentPlayer = players[currentPlayerIndex];

                System.out.println(currentPlayer.getName() + "'s turn. Enter row and column (0 to "
                        + (board.getSize() - 1) + "):");

                int[] move = getValidMove(scanner);
                if (makeMove(currentPlayer, move[0], move[1])) {
                    won = checkWinOrDraw(currentPlayer, move[0], move[1]);
                } else {
                    System.out.println("Cell already occupied. Try again.");
                }
            }
        }
    }

    private int[] getValidMove(Scanner scanner) {
        int row = -1, col = -1;
        boolean validInput = false;

        while (!validInput) {
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    col = scanner.nextInt();
                    if (isValidMove(row, col)) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Row and column must be within the board size. Try again:");
                    }
                } else {
                    System.out.println("Invalid input. Please enter two integers (row and column):");
                    scanner.next();
                }
            } else {
                System.out.println("Invalid input. Please enter two integers (row and column):");
                scanner.next();
            }
        }
        return new int[] { row, col };
    }

    private boolean makeMove(Player player, int row, int col) {
        return board.placePiece(player.getPiece(), row, col);
    }

    private boolean checkWinOrDraw(Player player, int row, int col) {
        if (winningStrategy.checkWin(board, player.getPiece(), row, col)) {
            System.out.println(player.getName() + " wins!");
            return true;
        } else if (isBoardFull()) {
            board.displayBoard();
            System.out.println("The game is a draw!");
            return true;
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length; // Switch player
            return false;
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize();
    }

    private boolean isBoardFull() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getPiece(i, j) == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
