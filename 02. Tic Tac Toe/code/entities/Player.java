package entities;
import interfaces.PieceInterface;

public class Player {
    private final String name;
    private final PieceInterface piece;

    public Player(String name, PieceInterface piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return name;
    }

    public PieceInterface getPiece() {
        return piece;
    }
}
