import entities.Player;
import entities.Game;
import impl.BruteForceWinningStrategyImpl;
import impl.LinearWinningStrategyImpl;
import impl.OptimizedWinningStrategyImpl;
import impl.SymbolPieceImpl;
import interfaces.PieceInterface;
import interfaces.WinningStrategyInterface;

public class Main {
    public static void main(String[] args) {
        PieceInterface xPiece = new SymbolPieceImpl("X");
        PieceInterface oPiece = new SymbolPieceImpl("O");

        Player player1 = new Player("Bob", xPiece);
        Player player2 = new Player("Martin", oPiece);

        Player[] players = {player1, player2};
        // WinningStrategyInterface strategy = new BruteForceWinningStrategyImpl(); // O(n^2) time complexity
        // WinningStrategyInterface strategy = new LinearWinningStrategyImpl(); // O(n)
        WinningStrategyInterface strategy = new OptimizedWinningStrategyImpl(); // O(1)

        int boardSize = 3; 
        Game game = new Game(boardSize, players, strategy);
        game.play();
    }
}
