package Chess;

import BoardGame.BoardException;

/**
 *
 * @author Evandro Lima
 */
public class ChessException extends BoardException{
   private static final long SerialVersionUID = 1L; 
   
   public ChessException (String msg){
       super(msg);
   }
}
