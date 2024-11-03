package impl;

import entities.Board;
import interfaces.PieceInterface;
import interfaces.WinningStrategyInterface;

public class BruteForceWinningStrategyImpl implements WinningStrategyInterface {
    @Override
    public boolean checkWin(Board board, PieceInterface piece, int row, int col) {
        int n = board.getSize();

        // Check all rows and all columns
        for (int i = 0; i < n; i++) {
            if (checkLine(board, piece, i, 0, 0, 1) || checkLine(board, piece, i, 0, 1, 0)) {
                board.displayBoard();
                return true;
            }
        }

        // Check diagonals
        if (checkLine(board, piece, 0, 0, 1, 1) || checkLine(board, piece, 0, n - 1, 1, -1)) {
            board.displayBoard();
            return true;
        }

        return false;
    }

    private boolean checkLine(Board board, PieceInterface piece, int startRow, int startCol, int rowIncrement,
            int colIncrement) {
        for (int i = 0; i < board.getSize(); i++) {
            int row = startRow + i * rowIncrement;
            int col = startCol + i * colIncrement;

            if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                return false;
            }

            if (board.getPiece(row, col) != piece) {
                return false;
            }
        }
        return true;
    }
}
