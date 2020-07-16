package aplication;
import Chess.ChessMatch;


/*
 *
 * @author Evandro Lima
 */
public class Program {

    public static void main(String[] args) {
        
        ChessMatch match = new ChessMatch();
        UI.printBoard(match.getPieceses());
    }
  
}
