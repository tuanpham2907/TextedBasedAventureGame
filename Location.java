import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Location {
    private String name;
    private String description;
    private ArrayList<Item> Items;
    private HashMap<String, Location> connections;

    public Location(String name, String description){
        this.name = name;
        this.description = description;
        Items = new ArrayList<Item>();
        connections = new HashMap<String, Location>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void addItem(Item itemToAdd){
        Items.add(itemToAdd);
    }

    public boolean hasItem(String nameToSearch){
        if(getItem(nameToSearch)!=null){
            return true;
        }
        return false;
    }

    public Item getItem(String nameToSearch){
        Iterator<Item> iter = Items.iterator();
        while(iter.hasNext()){
            Item temp = iter.next();
            String itemName = temp.getName().toLowerCase();
            nameToSearch = nameToSearch.toLowerCase();
            if(nameToSearch.compareTo(itemName)==0){
                return temp;
            }
        }
        return null;
    }

    public Item getItem(int itemIndex){
        if(itemIndex<Items.size() && itemIndex>=0){
            Item temp = Items.get(itemIndex);
            return temp;
        }   
        return null;
    }

    public int numItems(){
        return Items.size();
    }

    public Item removeItem(String nameToRemove){
        Item itemToRemove = getItem(nameToRemove);
        Items.remove(itemToRemove);
        return itemToRemove;
    }

    public void connect(String direction, Location locationToConnect){
        connections.put(direction, locationToConnect);
    }

    public boolean canMove(String direction){
        return connections.containsKey(direction);
    }

    public Location getLocation(String direction){
        return connections.get(direction);
    }
}
