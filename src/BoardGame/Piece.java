package BoardGame;

/**
 *
 * @author Evandro Lima
 */
public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) { // método concreto utilizando o método abstrato/chamado de hook Methodos
        return possibleMoves()[position.getRow()][position.getColumn()]; // método abstrato
    }
    // Uma utilização padrão concreta da utilização de um método abstrato
    public boolean isThereAnyPossibleMove(){
        boolean[][] mat = possibleMoves();// utilizanso o método abstrato
        for(int i= 0; i < mat.length; i++){
            for(int j = 0; j < mat.length; j++){
                if(mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
