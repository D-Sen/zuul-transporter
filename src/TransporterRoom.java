
/**
 * Represents a class of room that is a transport room,
 *  transporter rooms have different descriptions and different exit properties
 *
 * @author Domonic Senesi & Cara Tang
 * @version 08/02/2017
 *
 */

public class TransporterRoom extends Room{

   private Scenario scenario;

    public TransporterRoom( String description) {
        super(description);
    }

    /**
     * Return a room that is randomly picked from the list of
     * room instances in the rooms array list
     * @return selected room
     *
     */
    public Room findRandomRoom(){
        return scenario.getRandomRoom();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @Overrides : Room.getExitString()
     * @return Details of the room's exits.
     */
    public String getExitString()
    {
        String returnString = "You don't see any exits, but the walls of the " +
                "room have a strange glow.";
        return returnString;
    }
}
