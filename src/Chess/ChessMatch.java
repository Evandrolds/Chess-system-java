package Chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

import Chess.pieces.King;
import Chess.pieces.Roock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Evandro Lima
 */
public class ChessMatch {

    private Board board;
    private Integer turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;
    
    private List <Piece> pieceOnTheBoard = new ArrayList<>();
    private List <Piece> capturedPieces = new ArrayList<>();
    public int getTurn(){
        return this.turn;
    }
    public Color getCurrentPlayer(){
        return this.currentPlayer;
        
    }

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.BLACK;
        initialSetup();
    }
    private void nexTurn(){
        turn++;
        currentPlayer = ( currentPlayer == Color.BLACK) ? Color.WHITE : Color.BLACK;
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

    // Imprimindo as posições possíveis
    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    private void validateTargetPosition(Position source, Position target) {

        // verificar se a posição de destino é válida com a posição de origem
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The shosen piece can't move to targer position!");
        }

    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removingPiece(source);
        Piece capturedPiece = board.removingPiece(target);
        board.placePiece(p, target);
        if(capturedPiece != null){
            pieceOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException(" There ins't piece on source  position ");
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){ // feito um down casting da classe ChessPiece.
            throw new ChessException("The shosen piece is not yours! ");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There ins't possible move for the shosen piece!");
        }
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);// Source é origem
        validateTargetPosition(source, target);// Target é destino
        Piece capturedPiece = makeMove(source, target);
        nexTurn();
        return (ChessPiece) capturedPiece;
    }

    private void placeNewPiece(char color, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(color, row).toPosition());
        pieceOnTheBoard.add(piece);
    }

    private void initialSetup() {

        placeNewPiece('c', 8, new Roock(board, Color.WHITE));
        placeNewPiece('d', 8, new King(board, Color.WHITE));
        placeNewPiece('e', 8, new Roock(board, Color.WHITE));
        placeNewPiece('c', 7, new Roock(board, Color.WHITE));
        placeNewPiece('d', 7, new Roock(board, Color.WHITE));
        placeNewPiece('e', 7, new Roock(board, Color.WHITE));
        
        placeNewPiece('c', 1, new Roock(board, Color.BLACK));
        placeNewPiece('d', 1, new King(board, Color.BLACK));
        placeNewPiece('e', 1, new Roock(board, Color.BLACK));
        placeNewPiece('c', 2, new Roock(board, Color.BLACK));
        placeNewPiece('d', 2, new Roock(board, Color.BLACK));
        placeNewPiece('e', 2, new Roock(board, Color.BLACK));
    }
}
