

public class Interaction {

    private String interactionID;
    private String timestamp;
    private String details;

    public Interaction(String interactionID, String time, String detail) {

        this.interactionID=interactionID;
        this.timestamp=time;
        this.details=detail;

    }

    public String getInteractionID() {return this.interactionID;}
    public String getTimestamp() {return this.timestamp;}
    public String getDetails() {return this.details;}
}
