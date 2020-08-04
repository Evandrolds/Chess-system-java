package aplication;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import java.util.Scanner;


/*
 *
 * @author Evandro Lima
 */
public class Program {

    public static void main(String[] args) {

        ChessMatch match = new ChessMatch();
        while (true) {
            Scanner sc = new Scanner(System.in);
            UI.printBoard(match.getPieceses());
            System.out.println("");
            System.out.print("  Souce: ");

            ChessPosition sourse = UI.readChassPosition(sc);

            System.out.print("  Target: ");
            ChessPosition target = UI.readChassPosition(sc);

            ChessPiece capturedPiece = match.performChessMove(sourse, target);
        }
    }

}
