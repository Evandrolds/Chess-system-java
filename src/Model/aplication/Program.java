package Model.aplication;
import Model.enttites.ChessMatch;
import Model.enttites.UI;


/**
 *
 * @author Evandro Lima
 */
public class Program {

    public static void main(String[] args) {
        
        ChessMatch match = new ChessMatch();
        UI.printBoard(match.getPieceses());
    }
  
}
