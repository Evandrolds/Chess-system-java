package Model.enttites;

/**
 *
 * @author Evandro Lima
 */
public class ChessMatch {
    private Board board;
    public ChessMatch(){
        board = new Board(8, 8);
    }
    public ChessPiece[][]getPieceses(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumn()];
        for (int i = 0; i < board.getRows();i++) {
             for (int j = 0; j < board.getColumn(); j++) {
                mat[i][j] = (ChessPiece)board.piece(i, j);
            }
        }
        return mat;
    }
}
