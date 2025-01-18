package entities;
import interfaces.PieceInterface;

public class Board {
    private final int size;
    private final PieceInterface[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new PieceInterface[size][size];
    }

    public int getSize() {
        return size;
    }

    public PieceInterface getPiece(int row, int col) {
        return board[row][col];
    }

    public boolean placePiece(PieceInterface piece, int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = piece;
            return true;
        }
        return false;
    }

    public void displayBoard() {
        System.out.println();
        System.out.print("  ");

        for (int j = 0; j < size; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    System.out.print("_ ");
                } else {
                    System.out.print(board[i][j].getSymbol() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}