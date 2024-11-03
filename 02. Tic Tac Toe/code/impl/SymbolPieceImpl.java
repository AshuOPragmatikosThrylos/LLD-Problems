package impl;
import interfaces.PieceInterface;

public class SymbolPieceImpl implements PieceInterface {
    private final String symbol;

    public SymbolPieceImpl(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}