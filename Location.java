

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Location implements DisasterVictimSearch {
    
    private String name;
    private String address;
    private Set<DisasterVictim> occupants;
    private HashMap<String,Integer> supplies;

    public Location(String name, String address) {
        this.address=address;
        this.name=name;
        this.occupants = new HashSet<>(); 
        this.supplies=new HashMap<String,Integer>();
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
        if (quantity > 0) {
            int currentQuantity = supplies.getOrDefault(type, 0);
            int newQuantity = currentQuantity + quantity;
            supplies.put(type, newQuantity);
        }
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
    public List<DisasterVictim> searchByPartialFirstName(String partialFirstName, Set<DisasterVictim> victimList) {
        List<DisasterVictim> matchingVictims = new ArrayList<>();
        for (DisasterVictim victim : victimList) {
            if (victim.getFirstName().toLowerCase().contains(partialFirstName.toLowerCase())) {
                matchingVictims.add(victim);
            }
        }
        return matchingVictims;
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

    public int getSupplyQuantity(String supply) {
        if (this.supplies.containsKey(supply)) {
            return(this.supplies.get(supply));
        }else{
            return(0);
        }
    }
    public String getName() {return this.name;}
    public String getAddress() {return this.address;}
    public Set<DisasterVictim> getOccupants(){return this.occupants;}
    public HashMap<String,Integer> getAllSupplies() {return this.supplies;}


}
