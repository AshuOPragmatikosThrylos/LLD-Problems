package impl;

import entities.Board;
import interfaces.PieceInterface;
import interfaces.WinningStrategyInterface;

public class OptimizedWinningStrategyImpl implements WinningStrategyInterface {
    private int[] rows;
    private int[] cols;
    private int diag = 0, antiDiag = 0;
    private int boardSize = 0;

    @Override
    public boolean checkWin(Board board, PieceInterface piece, int row, int col) {
        if (boardSize == 0) {
            boardSize = board.getSize();
            rows = new int[boardSize];
            cols = new int[boardSize];
        }

        int increment = piece.getSymbol().equals("X") ? 1 : -1;

        rows[row] += increment;
        cols[col] += increment;
        if (row == col)
            diag += increment;
        if (row + col == boardSize - 1)
            antiDiag += increment;

        boolean result = Math.abs(rows[row]) == boardSize || Math.abs(cols[col]) == boardSize ||
                Math.abs(diag) == boardSize || Math.abs(antiDiag) == boardSize;
        if (result) {
            board.displayBoard(); // board maintained only if viewing after each move is required; otherwise can be skipped for this strategy 
        }
        return result;
    }
}