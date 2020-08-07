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
        ChessMatch match = new ChessMatch();
        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(match.getPieceses());
                System.out.println("");
                System.out.print("  Souce: ");

                ChessPosition sourse = UI.readChassPosition(sc);

                System.out.print("  Target: ");
                ChessPosition target = UI.readChassPosition(sc);

                ChessPiece capturedPiece = match.performChessMove(sourse, target);

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
