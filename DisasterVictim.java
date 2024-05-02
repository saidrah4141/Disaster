import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum DietaryRestriction {
    AVML,
    DBML,
    GFML,
    KSML,
    LSML,
    MOML,
    PFML,
    VGML,
    VJML
}

public class DisasterVictim {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String comments;
    private int assigned_social_id;
    private String gender;
    private String age;
    private ArrayList<MedicalRecord> medicalRecords;
    private ArrayList<FamilyRelation> familyConnections;
    private HashMap<String,Integer> personalBelongings;
    private Set<DietaryRestriction> dietaryRestrictions;
    private final String ENTRY_DATE;
    private static final String DATE_FORMAT_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_FORMAT_REGEX);


    public DisasterVictim(String fName,String entryDate) throws IllegalArgumentException{
        this.firstName=fName;
        this.personalBelongings = new HashMap<>();
        this.medicalRecords = new ArrayList <>();
        this.familyConnections = new ArrayList <>();
        Matcher matcher = DATE_PATTERN.matcher(entryDate);
        this.assigned_social_id = 0;
        this.dietaryRestrictions= new HashSet<>();


        try {
            if (matcher.matches()) {
                this.ENTRY_DATE = entryDate;

            } else {
                throw new IllegalArgumentException("Invalid date format for entry date. Please use yyyy-MM-dd");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for entry date");
        }

    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }
    public void setLastName(String lastName) {
        this.lastName=lastName;
    }
    public void setAge(String age) {
        this.dateOfBirth=null;
        this.age=age;
    }
    public void setSocialID(int ID) {
        this.assigned_social_id=ID;
    }
    public void setDateOfBirth(String dateOfBirth)throws IllegalArgumentException {
        this.age=null;
        Matcher matcher = DATE_PATTERN.matcher(dateOfBirth);

        try {
            if (matcher.matches()) {
                LocalDate enteredDate = LocalDate.parse(dateOfBirth);
                LocalDate currentDate = LocalDate.now();

                if (enteredDate.isAfter(currentDate)) {
                    throw new IllegalArgumentException("Date of birth cannot be in the future");
                } else {
                    this.dateOfBirth = dateOfBirth;
                }
            } else {
                throw new IllegalArgumentException("Invalid date format for date of birth. Please use yyyy-MM-dd");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for date of birth");
        }
    }


    public void setComments(String comments) {
        this.comments=comments;
    }
    public void setMedicalRecords(ArrayList<MedicalRecord> medicalRecords) {
        this.medicalRecords=medicalRecords;
    }
    public void setPersonalBelongings(HashMap<String,Integer> supplies) {
        this.personalBelongings=supplies;
    }
    public void setFamilyConnections(ArrayList<FamilyRelation> relation) {
        this.familyConnections=relation;
    }
    public void setGender(String gender) {
        this.gender=gender;
    }
    public void addDietaryRestriction(DietaryRestriction restriction) {
        this.dietaryRestrictions.add(restriction);
    }
    public void addPersonalBelonging(String type, int quantity) {
        if (this.personalBelongings.containsKey(type)) {
            int currentValue = this.personalBelongings.get(type);
            int newValue = currentValue + quantity;
            this.personalBelongings.put(type, newValue);
        } else {
            this.personalBelongings.put(type, quantity);
        }
    }

    public void removePersonalBelonging(String type) {
        this.personalBelongings.remove(type);
    }


    public void addFamilyConnection(FamilyRelation familyConnection) {
        this.familyConnections.add(familyConnection);
    }

    public void removeFamilyConnection(FamilyRelation familyConnection) {
        Iterator<FamilyRelation> iterator = familyConnections.iterator();
        while (iterator.hasNext()) {
            FamilyRelation relation = iterator.next();
            if (relation.equals(familyConnection)) {
                iterator.remove();
                break;
            }
        }
    }
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecords.add(medicalRecord);

    }
    public void printAllDietaryRestrictions() {
        System.out.println("Available dietary restrictions:");
        for (DietaryRestriction restriction : DietaryRestriction.values()) {
            System.out.println(restriction);
        }
    }
    public boolean hasDuplicateRelationship(DisasterVictim personTwo) {
        for (FamilyRelation relation : familyConnections) {
            if ((relation.getPersonOne().equals(this) && relation.getPersonTwo().equals(personTwo)) ||
                    (relation.getPersonOne().equals(personTwo) && relation.getPersonTwo().equals(this))) {
                return true;
            }
        }
        return false;
    }
    public boolean hasCompleteSeries(DisasterVictim otherPerson) {
        DisasterVictim thirdPerson = null;

        for (FamilyRelation relation : familyConnections) {
            if (!relation.getPersonOne().equals(this) && !relation.getPersonTwo().equals(this)) {
                thirdPerson = relation.getPersonOne().equals(otherPerson) ? relation.getPersonTwo() : relation.getPersonOne();
                break;
            }
        }


        
        boolean hasRelationWithThird = false;
        boolean hasRelationWithOther = false;
        for (FamilyRelation relation : familyConnections) {
            if ((relation.getPersonOne().equals(this) && relation.getPersonTwo().equals(thirdPerson)) ||
                    (relation.getPersonOne().equals(thirdPerson) && relation.getPersonTwo().equals(this))) {
                hasRelationWithThird = true;
            } else if ((relation.getPersonOne().equals(otherPerson) && relation.getPersonTwo().equals(thirdPerson)) ||
                    (relation.getPersonOne().equals(thirdPerson) && relation.getPersonTwo().equals(otherPerson))) {
                hasRelationWithOther = true;
            }
        }
        return hasRelationWithThird && hasRelationWithOther;
    }


    public String getFirstName() {return(this.firstName);}
    public String getLastName() {return(this.lastName);}
    public String getDateOfBirth() {return(this.dateOfBirth);}
    public String getComments() {return(this.comments);}
    public String getAge() {return this.age;}
    public ArrayList<MedicalRecord> getMedicalRecords() {return(this.medicalRecords);}
    public String getEntryDate() {return(this.ENTRY_DATE);}
    public int getAssignedSocialID() {return(this.assigned_social_id);}
    public HashMap<String, Integer> getPersonalBelongings() {return(this.personalBelongings);}
    public ArrayList<FamilyRelation> getFamilyConnections() {return(this.familyConnections);}
    public String getGender() {return(this.gender);}
    public Set<DietaryRestriction> getDietaryRestrictions() {return(this.dietaryRestrictions);}

}

