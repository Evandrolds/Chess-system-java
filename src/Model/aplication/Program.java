
package Model.aplication;

import Model.enttites.Position;

/**
 *
 * @author Evandro Lima
 */
public class Program {
    public static void main(String[] args) {
        Position position = new Position(3, 5);
        System.out.println("Position: "+position.toString());
    }
}
