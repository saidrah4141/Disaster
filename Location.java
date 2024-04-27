

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Location implements DisasterVictimSearch {
    private static Location instance;
    private String name;
    private String address;
    private Set<DisasterVictim> occupants;
    private HashMap<String,Integer> supplies;

    private Location() {
        this.address="2500 University Dr NW, Calgary, AB T2N 1N4";
        this.name="UofC";
        this.occupants = new HashSet<>();
        occupants.add(new DisasterVictim("Jane", "2024-02-23"));
        occupants.add(new DisasterVictim("John", "2024-02-24"));
        occupants.add(new DisasterVictim("Jack", "2024-02-25"));
        this.supplies = new HashMap<>();
        supplies.put("water bottles", 500);
        supplies.put("blankets", 250);
        supplies.put("first aid kits", 50);
        supplies.put("food kits", 200);
    }
    public static Location getInstance() {
        if (instance == null) {
            instance = new Location();
        }
        return instance;
    }

    public void setName(String name) {
        this.name=name;
    }
    public void setAddress(String address) {
        this.address=address;
    }
    public void setOccupants(Set<DisasterVictim> occupants) {
        this.occupants=occupants;
    }
    public void setSupplies(HashMap<String,Integer> supplies) {
        this.supplies=supplies;
    }
    public void addOccupant(DisasterVictim occupant) {
        this.occupants.add(occupant);
    }
    public void removeOccupant(DisasterVictim occupant) {
        this.occupants.remove(occupant);
    }
    public void addSupply(String type, int quantity) {

        int currentQuantity = supplies.getOrDefault(type, 0);
        int newQuantity = currentQuantity + quantity;
        supplies.put(type, newQuantity);
    }
    public boolean removeSupply(String type, int quantity) {
        if (supplies.containsKey(type)) {
            int currentQuantity = supplies.get(type);
            if (currentQuantity >= quantity) {
                int newQuantity = currentQuantity - quantity;
                supplies.put(type, newQuantity);
                if (newQuantity == 0) {
                    supplies.remove(type);
                }
                return true;
            } else {
                System.out.println("There is not enough " + type + ", there is only " +
                        currentQuantity + " left");
                return false;
            }
        } else {
            System.out.println("Supply " + type + " not found.");
            return false;
        }
    }

    @Override
    public DisasterVictim searchByFirstName(String firstName, Set<DisasterVictim> victimList) {
        for (DisasterVictim victim : victimList) {
            if (victim.getFirstName().equalsIgnoreCase(firstName)) {
                return victim;
            }
        }
        return null;
    }



    public String getName() {return this.name;}
    public String getAddress() {return this.address;}
    public Set<DisasterVictim> getOccupants(){return this.occupants;}
    public HashMap<String,Integer> getAllSupplies() {return this.supplies;}


}
