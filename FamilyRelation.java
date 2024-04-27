

public class FamilyRelation {
    private DisasterVictim personOne;
    private String relationshipTo;
    private DisasterVictim personTwo;

    public FamilyRelation(DisasterVictim personOne,String relationshipTo, DisasterVictim personTwo) {
        this.personOne=personOne;
        this.personTwo=personTwo;
        this.relationshipTo=relationshipTo;
    }

    public void setPersonOne(DisasterVictim personOne) {
        this.personOne=personOne;
    }
    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo=personTwo;
    }
    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo=relationshipTo;
    }


    public DisasterVictim getPersonOne() {return this.personOne;}
    public DisasterVictim getPersonTwo() {return this.personTwo;}
    public String getRelationshipTo() {return this.relationshipTo;}
}
