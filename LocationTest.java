

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class LocationTest {
    private Location location;
    private DisasterVictim victim;
    String expectedType ="Candy";
    int expectedQuantity=5;

    @Before
    public void setUp() {
        // Initializing test objects before each test method
        location = new Location("Shelter A", "1234 Shelter Ave");
        victim = new DisasterVictim("John Doe", "2024-01-01");
        location.addSupply(expectedType,expectedQuantity);
    }

    @Test
    public void testConstructor() {
        assertNotNull("Constructor should create a non-null Location object", location);
        assertEquals("Constructor should set the name correctly", "Shelter A", location.getName());
        assertEquals("Constructor should set the address correctly", "1234 Shelter Ave", location.getAddress());
    }
    @Test
    public void testSetName() {
        String newName = "Shelter B";
        location.setName(newName);
        assertEquals("setName should update the name of the location", newName, location.getName());
    }

    @Test
    public void testSetAddress() {
        String newAddress = "4321 Shelter Blvd";
        location.setAddress(newAddress);
        assertEquals("setAddress should update the address of the location", newAddress, location.getAddress());
    }

    @Test
    public void testAddOccupant() {
        location.addOccupant(victim);
        assertTrue("addOccupant should add a disaster victim to the occupants list", location.getOccupants().contains(victim));
    }

    @Test
    public void testRemoveOccupant() {
        location.addOccupant(victim);
        location.removeOccupant(victim);
        assertFalse("removeOccupant should remove the disaster victim from the occupants list", location.getOccupants().contains(victim));
    }
    @Test
    public void testSetAndGetOccupants() {
        HashSet<DisasterVictim> newOccupants = new HashSet<>();
        newOccupants.add(victim);
        location.setOccupants(newOccupants);
        assertTrue("setOccupants should replace the occupants list with the new list", location.getOccupants().containsAll(newOccupants));
    }
    @Test
    public void testAddSupply_AlreadyInLocation() {
        location.addSupply("Flashlight", 1);
        int actual= location.getSupplyQuantity("Flashlight");
        location.addSupply("Flashlight",5);
        int actualAfter = location.getSupplyQuantity("Flashlight");
        assertEquals("addSupply should should increase the quantity of the already existing supply", actual+5,actualAfter);
    }
    @Test
    public void testAddSupply_NotInLocation() {
        location.addSupply("blanket",1);
        assertEquals("addSupply should add a supply to the supplies hash map", location.getSupplyQuantity("blanket"),1);
    }


    @Test
    public void testRemoveSupplyCompletely() {
        location.addSupply("chocolate",1);
        location.removeSupply("chocolate",1);
        assertFalse("removeSupply should remove the supply from the supplies list", location.getAllSupplies().containsKey("chocolate"));
    }
    @Test
    public void testRemoveSupplyDecrement() {
        location.addSupply("milk",2);
        location.removeSupply("milk",1);
        assertEquals("removeSupply should decrement the supply quantity", location.getSupplyQuantity("milk"),1);
    }

    @Test

    public void testGetSupplyQuantity() {
        int actualQuantity= location.getSupplyQuantity(expectedType);
        assertEquals("getSupplyQuantity should show the quantity of a certain supply", actualQuantity,expectedQuantity);
    }


}
