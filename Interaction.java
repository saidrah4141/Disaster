

public class Interaction {

    private String interactionID;
    private String date;
    private String details;

    public Interaction(String interactionID, String date, String detail) {

        this.interactionID=interactionID;
        this.date=date;
        this.details=detail;

    }

    public String getInteractionID() {return this.interactionID;}
    public String getDate() {return this.date;}
    public String getDetails() {return this.details;}
}
