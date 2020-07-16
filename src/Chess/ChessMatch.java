package Chess;

import BoardGame.Board;
import BoardGame.Position;
import ChessPiece.King;
import ChessPiece.Roock;

/**
 *
 * @author Evandro Lima
 */
public class ChessMatch {

    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    public ChessPiece[][] getPieceses() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumn()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumn(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    private void initialSetup() {
        board.placePiece(new Roock(board, Color.WHITE), new Position(4, 3));
        board.placePiece(new King(board, Color.GREEN), new Position(2, 4));
        board.placePiece(new King(board, Color.BLACK), new Position(1, 4));
    }
}
