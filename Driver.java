import java.util.Scanner;

public class Driver {
    private static ContainerItem myInventory = new ContainerItem("Inventory", "Cointainer", "Works as a backpack, use this to store items.");
    private static Location currLocation;

    public static void createWorld(){
        //Initialize the rooms in the map
        Location bedroom = new Location("Bedroom", "Sleep to increase your mana.");
        Location hallway = new Location("Hallway", "Lead to other rooms.");
        Location kitchen = new Location("Kitchen", "A place to cook.");
        Location livingRoom = new Location("Living room", "A cozy place to sit down.");
        
        //Connect rooms to each other
        hallway.connect("south", bedroom);
        bedroom.connect("north", hallway);
        hallway.connect("north", kitchen);
        kitchen.connect("south", hallway);
        hallway.connect("west", livingRoom);
        livingRoom.connect("east", hallway);

        //Create items in kitchen
        Item knife = new Item("Knife", "Tool", "Use this to cut meats.");
        Item chicken = new Item("Chicken", "Food", "Eat this to prevent starving.");
        Item plate = new Item("Plate", "Tool", "Put your meal on this to eat.");
        Item tissue = new Item("Tissue", "Tool", "Clean your hand after eating.");
        kitchen.addItem(knife);
        kitchen.addItem(chicken);
        kitchen.addItem(plate);
        kitchen.addItem(tissue);

        //Create items in hallway
        Item vase = new Item("Vase", "Furniture", "This has some weird patterns on it.");
        Item picture = new Item("Picture", "Furniture", "Is that the picture that has been missing for years?");
        Item phone = new Item("Phone", "Tool", "Make a phone call.");
        Item flashlight = new Item("Flashlight", "Tool", "Use this to find the way.");
        hallway.addItem(vase);
        hallway.addItem(picture);
        hallway.addItem(phone);
        hallway.addItem(flashlight);

        //Create items in living room
        Item remote = new Item("Remote", "Tool", "Use this to turn the TV on/off.");
        Item sword = new Item("Sword", "Tool", "An ancient sword, it must be more than 500 years old.");
        Item wineBottle = new Item("WineBottle", "Furniture", "Is it still drinkable?");
        Item thermometer = new Item("Thermometer", "Tool", "Use this to see if the room's temperature is abnormal.");
        livingRoom.addItem(remote);
        livingRoom.addItem(sword);
        livingRoom.addItem(wineBottle);
        livingRoom.addItem(thermometer);  
        
        //Create a chest in the living room
        ContainerItem chest = new ContainerItem("Chest", "Container", "Use this to store your private stuffs.");
        livingRoom.addItem(chest);

        //Add items to the chest
        Item diamond = new Item("Diamond", "Jewelery", "It shines, that's all.");
        Item gun = new Item("Gun", "Weapon", "A shortgun. You wouldn't want to pull the trigger.");
        chest.addItem(diamond);
        chest.addItem(gun);

        //Create items in bedroom
        Item glasses = new Item("Glasses", "Tool", "Wear this for better visibility.");
        Item cup = new Item("Cup", "Tool", "Use this to get water.");
        Item towel = new Item("Towel", "Tool", "Use this to dry yourself if you get wet.");
        Item teddyBear = new Item("TeddyBear", "Tool", "A good companion with hidden function.");
        bedroom.addItem(glasses);
        bedroom.addItem(towel);
        bedroom.addItem(cup);
        bedroom.addItem(teddyBear);

        //Create a wardrobe in the bedroom
        ContainerItem wardrobe = new ContainerItem("Wardrobe", "Cointainer", "Use this to store your clothes");
        bedroom.addItem(wardrobe);

        //Add items to the wardrobe
        Item vest = new Item("Vest", "Clothes", "Wear this for parties.");
        Item pyjama = new Item("Pyjama", "Clothes", "Wear this before going to sleep");
        wardrobe.addItem(vest);
        wardrobe.addItem(pyjama);

        //Set current location
        currLocation = hallway;
    }
    public static void main(String[]args){

        //initialize the map
        createWorld();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter command: ");
        String command = sc.nextLine();
        command = command.toLowerCase();
        while(command.compareTo("quit")!=0){
            String[] splittedCommand = command.split(" ");
            switch(splittedCommand[0]){
                case "help":
                    System.out.println("Supported commands:");
                    System.out.println("- look: see the description of the location, then look around to see items in the room.");
                    System.out.println("- examine [item name]: get description of the item.");
                    System.out.println("- go [direction]: go to another room that is connected to this room. Can go north, south, east, or west.");
                    System.out.println("- inventory: see what's in the inventory.");
                    System.out.println("- take [item name]: take the item and put into inventory.");
                    System.out.println("- take [item name] from [container name]: take the item from the specified container and put into your inventory.");
                    System.out.println("- drop [item name]: remove item from the inventory and put to the current location.");
                    System.out.println("- put [item name]: in [container name]: remove the item from your inventory and put into the specified container");
                    System.out.println("Note: currently supporting one-word items.");
                break;
                case "look":
                    System.out.println(currLocation.getName()+" - "+currLocation.getDescription()+" Currently has the following item: ");
                    if(currLocation.numItems()==0){
                        System.out.println("There is currently no item at this location.");
                    }
                    else{
                        for(int i=0; i<currLocation.numItems(); i++){
                                Item temp = currLocation.getItem(i);
                                System.out.println("+ "+temp.getName());
                        }
                    }
                    break;
                case "examine":
                    if(splittedCommand.length==2){
                        Item itemToExamine = currLocation.getItem(splittedCommand[1]);
                        if(itemToExamine!=null){
                            System.out.println(itemToExamine.toString());
                        }
                        else{
                            System.out.println("Item not found, please try again.");
                        }
                    }
                    else{
                        System.out.println("Please enter Item name");
                    }
                    break;
                case "go":
                    if(splittedCommand.length < 2){
                        System.out.println("Please enter a direction.");
                    }
                    else{
                        switch(splittedCommand[1]){
                            case "north":
                                if(currLocation.canMove("north")){
                                    currLocation = currLocation.getLocation("north");
                                    System.out.println("You are currently at "+currLocation.getName());
                                }
                                else{
                                    System.out.println("There is no room connected to the North of this location. Please choose another direction.");
                                }
                            break;
                            case "south":
                                if(currLocation.canMove("south")){
                                    currLocation = currLocation.getLocation("south");
                                    System.out.println("You are currently at "+currLocation.getName());
                                }
                                else{
                                    System.out.println("There is no room connected to the South of this location. Please choose another direction.");
                                }
                            break;
                            case "west":
                                if(currLocation.canMove("west")){
                                    currLocation = currLocation.getLocation("west");
                                    System.out.println("You are currently at "+currLocation.getName());
                                }
                                else{
                                    System.out.println("There is no room connected to the West of this location. Please choose another direction.");
                                }
                            break;
                            case "east":
                                if(currLocation.canMove("east")){
                                    currLocation = currLocation.getLocation("east");
                                    System.out.println("You are currently at "+currLocation.getName());
                                }
                                else{
                                    System.out.println("There is no room connected to the East of this location. Please choose another direction.");
                                }
                            break;
                            default: 
                                System.out.println("Please enter a valid direction. It can be north, south, west, or east.");
                        }
                    }    
                break;
                case "inventory":
                    System.out.println(myInventory.toString());
                break;
                case "take":
                    //Take an item from the current location
                    if(splittedCommand.length < 2){
                        System.out.println("Please enter an item's name to take.");
                    }
                    else if (splittedCommand.length==2){
                        if(currLocation.hasItem(splittedCommand[1])){
                            Item itemToAdd = currLocation.getItem(splittedCommand[1]);
                            myInventory.addItem(itemToAdd);
                            currLocation.removeItem(splittedCommand[1]);
                        }
                        else{
                            System.out.println("Cannot find that item here.");
                        }
                    }
                    //Take an item from an inventory
                    else if(splittedCommand[2].equals("from")){
                        String containerToSearch = splittedCommand[3];
                        //Check if there is an item with the name typed
                        if(currLocation.hasItem(containerToSearch)){
                            Item temp = (currLocation.getItem(containerToSearch));
                            //Check if the item is a container
                            if(temp instanceof ContainerItem){
                                ContainerItem containerToTakeItem = (ContainerItem)temp;
                                Item itemToTake = containerToTakeItem.removeItem(splittedCommand[1]);
                                if(itemToTake!=null){
                                    myInventory.addItem(itemToTake);
                                }
                                else{
                                    System.out.println("Item not found, please try another item.");
                                } 
                            }
                            else{
                                System.out.println("That is not a container, please try again.");
                            }                           
                        }
                        else{
                            System.out.println("There is no container with such name, please try again.");
                        }
                    }
                    else{
                        System.out.println("Invalid use of take statement. Please try again.");
                    }
                break;
                case "drop":
                    if(splittedCommand.length < 2){
                        System.out.println("Please enter an item's name to drop.");
                    }
                    else{
                        if(myInventory.hasItem(splittedCommand[1])){
                            Item itemToTake = myInventory.removeItem(splittedCommand[1]);
                            currLocation.addItem(itemToTake);
                        }
                        else{
                            System.out.println("Cannot find that item in your inventory.");
                        }
                    }
                break;
                //Move an item from the inventory to a container
                case "put":
                    if(splittedCommand.length==4 && splittedCommand[2].equals("in")){
                        String containerToSearch = splittedCommand[3];
                        Item temp = (currLocation.getItem(containerToSearch));
                        //Check if there is an item with the specified name
                        if(temp!=null){
                            //Check if the item is a container
                            if(temp instanceof ContainerItem){
                                ContainerItem containerToPutItem = (ContainerItem)temp;
                                if(containerToPutItem!=null){
                                    String nameToPut = splittedCommand[1];
                                    Item itemToPut = myInventory.removeItem(nameToPut);
                                    if(itemToPut!=null){
                                        containerToPutItem.addItem(itemToPut);
                                    }
                                    else{
                                        System.out.println("Item not in your inventory, please try again.");
                                    }
                                }
                            }
                            else{
                                System.out.println("That is not a container, please try again");
                            }
                        }
                        else{
                            System.out.println("There is no container with such name, please try again.");
                        }
                    }  
                    else{
                        System.out.println("Invalid use of put statement, please try again.");
                    }
                break;
                default: 
                    System.out.println("Please enter a valid command");
            }
            System.out.print("Enter command: ");
            command = sc.nextLine();
            command = command.toLowerCase();
        }
        sc.close();
    }
}
