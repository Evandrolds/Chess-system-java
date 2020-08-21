package aplication;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/*
 *
 * @author Evandro Lima
 */
public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch,captured);
                System.out.println("");
                System.out.print("  Source: ");
                ChessPosition sourse = UI.readChassPosition(sc);

                boolean [][]possibleMoves = chessMatch.possibleMoves(sourse);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieceses(),possibleMoves);
                System.out.print("  Target: ");
                ChessPosition target = UI.readChassPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(sourse, target);
                if(capturedPiece != null){
                    captured.add(capturedPiece);
                }

            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }

}
