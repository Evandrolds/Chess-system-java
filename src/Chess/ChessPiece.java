package Chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

/**
 *
 * @author Evandro Lima
 */
public abstract class ChessPiece extends Piece {

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }

    protected void increaseMoveCount() {

    }

    protected void decreaseMoveCount() {

    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);// convertendo uma possição da peça de chadres
    }

}
