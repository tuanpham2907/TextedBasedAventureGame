# TextedBasedAventureGame
Final Project in CSC232: Object-Oriented Software Development

import java.util.ArrayList;
import java.util.Iterator;

public class ContainerItem extends Item{
    private ArrayList<Item> Items;
    public ContainerItem(String containnerName, String containerType, String containerDescription){
        super(containnerName, containerType, containerDescription);
        Items = new ArrayList<Item>();
    }

    public void addItem(Item itemToAdd){
        Items.add(itemToAdd);
    }

    public boolean hasItem(String nameToSearch){
        Iterator<Item> iter = Items.iterator();
        while(iter.hasNext()){
            Item temp = iter.next();
            String itemName = temp.getName().toLowerCase();
            nameToSearch = nameToSearch.toLowerCase();
            if(nameToSearch.compareTo(itemName)==0){
                return true;
            }
        }
        return false;
    }

    public Item removeItem(String nameToRemove){
        Iterator<Item> iter = Items.iterator();
        while(iter.hasNext()){
            Item temp = iter.next();
            String itemName = temp.getName().toLowerCase();
            nameToRemove = nameToRemove.toLowerCase();
            if(nameToRemove.compareTo(itemName)==0){
                Items.remove(temp);
                return temp;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        String outputString = super.toString() + '\n';
        if(Items.size()==0){
            outputString += "The container is currently empty \n"; 
        }
        else{
            for(int i=0; i<Items.size(); i++){
                String itemName = Items.get(i).getName();
                outputString += "+ " + itemName + "\n";
            }
        }       
        return outputString;
    }
}
