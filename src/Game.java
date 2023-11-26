/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Domonic Senesi - (Original creation by Michael Kölling and David J. Barnes)
 * @version 08/02/2017
 * 
 * Changes:
 * 
 * 6/29/2017 
 * - Removed default rooms, added new rooms [beach_main, boardwalk, shop_sun, res_fryers, ocean, pier, dunes]
 * - Added correct directions for access
 * 
 * 7/7/17
 * - Cleaned up description text for each room to be grammatically correct.
 * - Fixed an incorrect direction issue with room shop_sun
 * - Created public function printLocationInfo
 * - Added call to printLocationInfo to printWelcome & goRoom
 * - Removed redundant location info printing from printWelcome, goRoom
 * - room - added import hashmap
 * - room - added import set
 * - room - added private hashmap<String, Room>;
 * - room - added hashmap to public Room
 * - room - updated getExitString for use with Hashmap
 * 
 * 7/16/17
 * - added - item class
 * - game - added items in create rooms function
 * - room - getExitString
 * - game - printHelp - added parser.showCommands
 * - game - added look function
 * - game - added look command to processCommand
 * - game - printLocationInfo - printed item info if one in room
 * - parser - changed call in showCommands
 * - commandWords - added getCommandList function
 * - room - added getting and setting item in a room
 * 
 * 07/31/2017
 * - added - (game, command) - command 'printTime' 
 * - room - moved item info into getLongDescription room text print out
 *
 *
 * New Project : Zuul-Transporter
 * Project has been moved to IntelliJ
 * Classes main & scenario added
 * Code that was commented out from Version 2 has been removed
 *
 *
 * 08/02/2017
 * Added class - Main
 * Added class - Scenario (w/ constructor)
 * added class TransporterRoom (w/ constructor)
 * Game - Moved all item/room declarations to scenario constructor
 * Game.goRoom - added in check for room type being transporter room
 * Scenario - added methods : addRoom [adds room to rooms array list], getStartRoom [gets the startRoom, getRandomRoom [ uses Random lib to generate a
 *     number and use it to pick from the rooms array list]
 * TransportRoom - created getExitString which overrides same in Room class
 * TransportRoom - created findRandomRoom
 *
 *
 */

import java.util.Date;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Scenario scenario;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        scenario = new Scenario();
        parser = new Parser();
        currentRoom = scenario.getStartRoom();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Print out information about the players current location.
     * 07/31/2017 - Changed: item description and check is now part of getLongDescription
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     * 7/31/2017 - added extra command 'time' 
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("time")) {
            printTime();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the beach.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Prints out the current time and date.
     * Here we print out the current date and time. Could be implemented later.
     * Added 07/31/2017
     * 
     */
    private void printTime()
    {
        Date date = new Date();
        System.out.println(date);
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param: the command text user input to be interpreted
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if (currentRoom instanceof TransporterRoom) {
            nextRoom = scenario.getRandomRoom();
        } else {
            nextRoom = currentRoom.getExit(direction);
        }

        if (nextRoom == null) {
            System.out.println("You can't go that way!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param: the command text user input to be interpreted
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Lets the player look around.
     * Reprints the long description  
     * 
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
}
