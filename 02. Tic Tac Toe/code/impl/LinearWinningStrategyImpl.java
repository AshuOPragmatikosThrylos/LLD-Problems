package impl;

import entities.Board;
import interfaces.PieceInterface;
import interfaces.WinningStrategyInterface;

public class LinearWinningStrategyImpl implements WinningStrategyInterface {
    @Override
    public boolean checkWin(Board board, PieceInterface piece, int row, int col) {
        int n = board.getSize();

        // Check row
        if (checkLine(board, piece, row, 0, 0, 1)) {
            return true;
        }

        // Check column
        if (checkLine(board, piece, 0, col, 1, 0)) {
            return true;
        }

        // Check diagonal
        if (row == col && checkLine(board, piece, 0, 0, 1, 1)) {
            return true;
        }

        // Check anti-diagonal
        if (row + col == n - 1 && checkLine(board, piece, 0, n - 1, 1, -1)) {
            return true;
        }

        return false;
    }

    private boolean checkLine(Board board, PieceInterface piece, int startRow, int startCol, int rowIncrement,
            int colIncrement) {
        int n = board.getSize();

        for (int i = 0; i < n; i++) {
            int row = startRow + i * rowIncrement;
            int col = startCol + i * colIncrement;

            if (row < 0 || row >= n || col < 0 || col >= n) {
                return false;
            }

            if (board.getPiece(row, col) != piece) {
                return false;
            }
        }

        board.displayBoard();
        return true;
    }

}
