/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Domonic Senesi - (Original creation by Michael Kölling and David J. Barnes)
 * @version 2017.06.30
 * 
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    //Added 7/31/2017 - Added "time" command
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "time"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Returns a single string with all valid commands.
     * @return returns a string of valid commands, preceded by the word "Commands" 
     * 
     */
    public String getCommandList()
    {
        String returnString = "Commands:";        
        for(String command : validCommands) {
            returnString  += " " + command;
        }
        return returnString;
    }
}
