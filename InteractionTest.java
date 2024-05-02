import org.junit.*;
import static org.junit.Assert.*;
public class InteractionTest {
    String interactionId = "1A";
    String date = "2025-02-12";
    String details = "Expected details";
    Interaction interaction = new Interaction(interactionId, date, details);

    @Test
    public void testGetInteractionId() {
        assertEquals(interactionId, interaction.getInteractionID());
    }

    @Test
    public void testGetDate() {
        assertEquals(date, interaction.getDate());
    }

    @Test
    public void testGetDetails() {
        assertEquals(details, interaction.getDetails());
    }


    @Test
    public void testObjectCreation() {
        assertNotNull(interaction);
    }

}
