package ChessPiece;

import BoardGame.Board;
import Chess.ChessPiece;
import Chess.Color;

/**
 *
 * @author Evandro Lima
 */
public class Roock extends ChessPiece {

    public Roock(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

}
