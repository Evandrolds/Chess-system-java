package Chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;
import Chess.pieces.King;
import Chess.pieces.Roock;

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
    public ChessPiece performChessMove(ChessPosition sourcePosition,ChessPosition targetPosition){
            Position source = sourcePosition.toPosition();
            Position target = targetPosition.toPosition();
            ValidateSourcePosition(source);
            Piece capturedPiece = makeMove(source,target);
            return (ChessPiece)capturedPiece;
    }
    private void ValidateSourcePosition(Position position){
        if(!board.ThereIsAPiece(position)){
            throw new ChessException(" There ins't piece on source  position ");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There ins't possible move for the shosen piece!");
        }
    }
    private Piece makeMove(Position source, Position target){
        Piece p = board.movingPieces(source);
        Piece capturedPiece= board.movingPieces(target);
        board.placePiece(p, target);
        return capturedPiece;
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
