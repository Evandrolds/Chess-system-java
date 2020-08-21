package Chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

import Chess.pieces.King;
import Chess.pieces.Roock;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<Piece> pieceOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public int getTurn() {
        return this.turn;
    }

    public boolean getCheckMate() {
        return this.checkMate;
    }

    public boolean getCheck() {
        return this.check;
    }

    public Color getCurrentPlayer() {
        return this.currentPlayer;

    }

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    private void nexTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
            throw new ChessException(" The shosen piece can't move to targer position!");
        }

    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removingPiece(source);
        Piece capturedPiece = board.removingPiece(target);
        board.placePiece(p, target);
        if (capturedPiece != null) {
            pieceOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    // Desfazer uma jogada
    private void undoMuve(Position source, Position target, Piece capturedPiece) {
        Piece p = board.removingPiece(target);
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            pieceOnTheBoard.add(capturedPiece);
        }

    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException(" There ins't piece on source  position ");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) { // feito um down casting da classe ChessPiece.
            throw new ChessException(" The shosen piece is not yours! ");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException(" There ins't possible move for the shosen piece!");
        }
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);// Source é origem
        validateTargetPosition(source, target);// Target é destino
        Piece capturedPiece = makeMove(source, target);

        if (testCheck(currentPlayer)) {
            undoMuve(source, target, capturedPiece);
            throw new ChessException(" You can't put youself in chack!");
        }
        check = (testCheck(opponent(currentPlayer))) ? true : false;
        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        } else {
            nexTurn();
        }
        return (ChessPiece) capturedPiece;

    }

    private Color opponent(Color color) {
        return (color == color.WHITE)? color.BLACK : color.WHITE;
    }

    private ChessPiece king(Color color) {
        // usando Expressão Lâmbda para filtrar uma lista
        List<Piece> list = pieceOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece piece : list) {
            if (piece instanceof King) {
                return (ChessPiece) piece;
            }
        }
        throw new IllegalStateException("There isn't " + color + " King on the board!");
    }

    // verificando se o REI está em check
    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        // Pegando todas as peças do tabuleiro que seja da mesma cor, Usando expressão Lâmbda
        List<Piece> opponetPieces = pieceOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        return opponetPieces.stream().map((p) -> p.possibleMoves()).anyMatch((mat) -> (mat[kingPosition.getRow()][kingPosition.getColumn()]));
    }

    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) {
            return true;
        }
        // pegando todas as peças da cor do parametro do método
        List<Piece> list = pieceOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece p : list) {

            boolean[][] mat = p.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    if (mat[i][j]) {
                        Position source = ((ChessPiece) p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMuve(source, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void placeNewPiece(char color, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(color, row).toPosition());
        pieceOnTheBoard.add(piece);
    }

    private void initialSetup() {

        placeNewPiece('h', 7, new Roock(board, Color.WHITE));
        placeNewPiece('d', 1, new Roock(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
       

        placeNewPiece('b', 8, new Roock(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
       
    }
}
