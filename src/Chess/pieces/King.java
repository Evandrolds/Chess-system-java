package Chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessPiece;
import Chess.Color;

/**
 *
 * @author Evandro Lima
 */
public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // Testar se cada uma das 8 direçoes possiveis que um rei pode se mover
        // above
        p.setValues(position.getRow() - 1, position.getColumn());

        if (getBoard().positionExists(p) && canMuve(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //below
        p.setValues(position.getRow() + 1, position.getColumn());

        if (getBoard().positionExists(p) && canMuve(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMuve(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // hight
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMuve(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // Nw
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMuve(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // Ne
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMuve(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // Sw

        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMuve(p)) {
            mat[p.getRow()][p.getColumn()] = true;

        }
        //Se

        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMuve(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    private boolean canMuve(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

}
