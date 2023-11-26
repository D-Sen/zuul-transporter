
/**
 * Represents a game scenario including connected rooms and items
 *
 * @author Domonic Senesi & Cara Tang
 * @version 08/02/2017
 *
 */

import java.util.ArrayList;
import java.util.Random;


public class Scenario
{
    private ArrayList<Room> rooms;
    private Room startRoom;
    private Random random;

    /**
     * Constructor for objects of class Scenario
     */
    public Scenario()
    {
        rooms = new ArrayList<>();
        random = new Random();

        // Set up your rooms, exits, and items
        // Move code from Game.createRooms here
        // Set the start room
        // Create the rooms ArrayList and add all your rooms to it

        /**
         * Create all the rooms and link their exits together.
         */
        Room beach_main, boardwalk, shop_sun, res_fryers, ocean, pier, dunes, lifeguard_stand, sand_pit;
        TransporterRoom transporterRoom;

        // create the rooms
        beach_main = new Room("at the main beach, not too far from the ocean or boardwalk");
        boardwalk = new Room("on a long boardwalk that extends down most of the beach");
        shop_sun = new Room("in a shop full of various plastic trinketry");
        res_fryers = new Room("at a french fry stand, a ramshackle little place");
        ocean = new Room("at the ocean, where fish live");
        pier = new Room("on a long, wooden pier that goes out into the water");
        dunes = new Room("on a wind-created sand dune created during the beginning of time");

        lifeguard_stand = new Room("in the lifeguard stand");
        sand_pit = new Room("in a hole in the middle of the sand");

        transporterRoom = new TransporterRoom("in a Transporter Room");

        addRoom(beach_main);
        addRoom(boardwalk);
        addRoom(shop_sun);
        addRoom(res_fryers);
        addRoom(ocean);
        addRoom(pier);
        addRoom(dunes);
        addRoom(lifeguard_stand);
        addRoom(sand_pit);

        startRoom = beach_main;

        // initialise room exits
        beach_main.setExit("north", boardwalk);
        beach_main.setExit("south", ocean);
        beach_main.setExit("west", pier);
        beach_main.setExit("up", lifeguard_stand);
        beach_main.setExit("down", sand_pit);

        boardwalk.setExit("east", shop_sun);
        boardwalk.setExit("south", beach_main);
        boardwalk.setExit("west", res_fryers);

        ocean.setExit("north", beach_main);
        ocean.setExit("east", dunes);
        ocean.setExit( "south", transporterRoom);

        shop_sun.setExit("west", boardwalk);
        res_fryers.setExit("east", boardwalk);
        pier.setExit("east", beach_main);
        dunes.setExit("west", ocean);
        sand_pit.setExit("up", beach_main);
        lifeguard_stand.setExit("down", beach_main);


        // Item addition area
        Item beach_ball, flip_flop;
        beach_ball = new Item("a beach ball",5);
        flip_flop = new Item("a single flop, from a pair of flip-flops", 2);
        beach_main.setItem(beach_ball);
        boardwalk.setItem(flip_flop);

    }

    /*
     * Adds an instance of Room to the array list rooms
     * @param: room to be added to the array list
     */
    public void addRoom(Room room){
        rooms.add(room);

    }

    /**
     * @return  the start room for this scenario
     */
    public Room getStartRoom()
    {
        return startRoom;
    }
    
    /**
     * uses Random lib to generate a number and use it to pick from the rooms array list
     * @return  a random room from this scenario
     */

    public Room getRandomRoom()
    {
        random = new Random();
        int index = random.nextInt(rooms.size() );
        return rooms.get(index);
    }

}
