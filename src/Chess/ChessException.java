package Chess;

/**
 *
 * @author Evandro Lima
 */
public class ChessException extends RuntimeException{
   private static final long SerialVersionUID = 1L; 
   
   public ChessException (String msg){
       super(msg);
   }
}
