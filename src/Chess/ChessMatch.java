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
        placeNewPiece('a', 2, new Roock(board, Color.WHITE));
        placeNewPiece('b', 2, new Roock(board, Color.WHITE));
        placeNewPiece('c', 3, new Roock(board, Color.WHITE));
        placeNewPiece('d', 1, new Roock(board, Color.WHITE));
        placeNewPiece('e', 2, new Roock(board, Color.WHITE));
        placeNewPiece('f', 4, new Roock(board, Color.WHITE));
        placeNewPiece('g', 4, new Roock(board, Color.WHITE));
        placeNewPiece('h', 4, new King(board, Color.WHITE));

        placeNewPiece('a', 1, new King(board, Color.BLACK));
        placeNewPiece('b', 3, new King(board, Color.BLACK));
        placeNewPiece('c', 2, new King(board, Color.BLACK));
        placeNewPiece('d', 2, new King(board, Color.BLACK));
        placeNewPiece('e', 5, new King(board, Color.BLACK));
        placeNewPiece('f', 5, new King(board, Color.BLACK));
        placeNewPiece('g', 5, new King(board, Color.BLACK));
        placeNewPiece('h', 5, new Roock(board, Color.BLACK));
    }
}
