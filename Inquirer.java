import java.util.HashMap;

public class Inquirer {

    private String FIRST_NAME;
    private String LAST_NAME;
    private String SERVICES_PHONE;
    private HashMap<DisasterVictim, Interaction> interactionLog;

    public Inquirer(String FIRST_NAME, String LAST_NAME, String SERVICES_PHONE) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.SERVICES_PHONE = SERVICES_PHONE;
        this.interactionLog = new HashMap<>();
    }

    public void logInteraction(DisasterVictim foundVictim, Interaction interaction) {
        this.interactionLog.put(foundVictim, interaction);
    }

    public String getFirstName() {
        return this.FIRST_NAME;
    }

    public String getLastName() {
        return this.LAST_NAME;
    }

    public String getServicesPhone() {
        return this.SERVICES_PHONE;
    }
    public String getInteractionDetails(DisasterVictim victim){
        return(interactionLog.get(victim).getDetails());
    }

	
}
