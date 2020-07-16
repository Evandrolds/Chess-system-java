package ChessPiece;

import BoardGame.Board;
import Chess.ChessPiece;
import Chess.Color;

/**
 *
 * @author Evandro Lima
 */
public class King extends ChessPiece{

    public King(Board board, Color color) {
        super(board, color);
    }
    @Override
    public String toString(){
        return "K";
    }

}
