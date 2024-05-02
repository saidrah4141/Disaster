

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class InquirerTest {


    private String expectedFirstName = "Joseph";
    private String expectedLastName = "Bouillon";
    private String expectedPhoneNumber = "+1-123-456-7890";
    private String expectedMessage = "looking for my family members";
    private Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber);
    private List<Interaction> interactions;
    @Test
    public void testObjectCreation() {
        assertNotNull(inquirer);
    }



    @Test
    public void testGetFirstName() {
        assertEquals("getFirstName() should return inquirer's first name", expectedFirstName, inquirer.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("getLastName() should return inquirer's last name", expectedLastName, inquirer.getLastName());
    }

    @Test
    public void testGetServicesPhoneNum() {

        assertEquals("getServicesPhoneNum() should return the correct Services Number",expectedPhoneNumber, inquirer.getServicesPhone());
    }


    @Test
    public void testLogAndGetIneraction() {
        interactions = new ArrayList<>();
        DisasterVictim victim1 = new DisasterVictim("John", "2024-02-21");
        String id = "1A";
        String date = "2025-02-12";
        Interaction interaction = new Interaction(id, date, expectedMessage);
        inquirer.logInteraction(victim1,interaction);

        assertEquals(1, interactions.size());
        assertEquals(id, interactions.get(0).getInteractionID());
        assertEquals(date, interactions.get(0).getDate());
        assertEquals(expectedMessage, interactions.get(0).getDetails());

    }

}

