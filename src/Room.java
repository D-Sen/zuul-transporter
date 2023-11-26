/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Domonic Senesi - (Original creation by Michael Kölling and David J. Barnes)
 * @version 08/02/2017
 * 
 */
import java.util.Set;
import java.util.HashMap;

public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    
    private HashMap<String, Room> exits;

    //-----------------------------------------------
    private Item itemInRoom;
    
    public Item getItem()
    {
        return itemInRoom;
    }
    
    public void setItem(Item name)
    {
        itemInRoom = name;   
    }

    //------------------------------------------------
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction - The exit's direction
     * @return - The room in the given direction
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    

    
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
        
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    

    /**
     * Return a description of the room in the form:
     *     You are at the ocean.
     *     Exits: north east
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        if(getItem()!= null)
        {
            return "You are " + description + ".\n" + "You see " + getItem().getDescription() + " on the ground. \n" + getExitString() + "\n";
        } else {
            return "You are " + description + ".\n" + getExitString() + "\n";
        }
    }

    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }



}
