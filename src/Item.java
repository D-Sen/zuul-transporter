
/**
 * Item class
 * 
 * Class for an item, which can be found in the rooms, and has a description and a weight to it.
 * 
 * @author Domonic Senesi 
 * @version 07/16/2017
 */
public class Item
{
    private String description;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Method - get the description of the item
     * @return  String  - a description of the item
     * 
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Method - get the weight of the item
     * @return  int - weight of the item 
     * 
     */    
    public int getWeight()
    {
        return weight;
    }
}
