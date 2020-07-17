package BoardGame;

/**
 *
 * @author Evandro Lima
 */
public class Board {

    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 1 && columns < 1) {
            throw new BoardException("Error criating board: There must be at least 1 row and 1 column!");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column) {
        if(!positionExists(row,column)){
            throw new BoardException(" Position not on the borad ");
        }
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        if(!positionExists(position)){
            throw new BoardException(" Position not on the borad ");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if (ThereIsAPiece(position)){
            throw new BoardException(" Already exixt a piece this position! " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < this.columns;
    }

    private boolean positionExists(Position position) {

        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean ThereIsAPiece(Position position) {
        if(!positionExists(position)){
            throw new BoardException(" Position not on the borad ");
        }
        return piece(position) != null;

    }
}
