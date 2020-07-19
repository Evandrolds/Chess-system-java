package Chess;

import BoardGame.Board;
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
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    private void placeNewPiece(char color, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(color, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Roock(board, Color.WHITE));
        placeNewPiece('b', 2, new King(board, Color.GREEN));
        placeNewPiece('c', 3, new King(board, Color.BLACK));
    }
}
