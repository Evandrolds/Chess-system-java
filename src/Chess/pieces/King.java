package Chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessPiece;
import Chess.Color;

/**
 *
 * @author Evandro Lima
 */
public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
     public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
     private boolean canMuve(Position position){
         ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p.getColor()!= getColor();
     }

}
