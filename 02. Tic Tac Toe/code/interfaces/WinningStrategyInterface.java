package interfaces;

import entities.Board;

public interface WinningStrategyInterface {
    boolean checkWin(Board board, PieceInterface piece, int row, int col);
}