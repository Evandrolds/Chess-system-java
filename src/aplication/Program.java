package aplication;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import java.util.InputMismatchException;
import java.util.Scanner;


/*
 *
 * @author Evandro Lima
 */
public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        while (true) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch);
                System.out.println("");
                System.out.print("  Souce: ");
                ChessPosition sourse = UI.readChassPosition(sc);

                boolean [][]possibleMoves = chessMatch.possibleMoves(sourse);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieceses(),possibleMoves);
                System.out.print("  Target: ");
                ChessPosition target = UI.readChassPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(sourse, target);

            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

}
