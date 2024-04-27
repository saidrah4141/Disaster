


import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeParseException;
public class ReliefService {

    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;
    private static final String DATE_FORMAT_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_FORMAT_REGEX);


    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson,
                         String dateOfInquiry, String infoProvided, Location lastKnownLocation) {
        this.inquirer=inquirer;
        this.missingPerson=missingPerson;
        this.dateOfInquiry=dateOfInquiry;
        this.infoProvided = infoProvided;
        this.lastKnownLocation= Location.getInstance();
    }

    public void setInquirer(Inquirer inquirer) {
        this.inquirer=inquirer;
    }
    public void setMissingPerson(DisasterVictim missingPerson) {
        this.missingPerson=missingPerson;
    }
    public void setDateOfInquiry(String dateOfInquiry) throws IllegalArgumentException {
        Matcher matcher = DATE_PATTERN.matcher(dateOfInquiry);

        try {
            if (matcher.matches()) {
                LocalDate enteredDate = LocalDate.parse(dateOfInquiry);
                LocalDate currentDate = LocalDate.now();

                if (enteredDate.isAfter(currentDate)) {

                    throw new IllegalArgumentException("Inquiry date cannot be in the future");
                } else {
                    this.dateOfInquiry = dateOfInquiry;
                }
            } else {
                throw new IllegalArgumentException("Invalid date format for Inquiry date. Please use yyyy-MM-dd");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for Inquiry date. Please use yyyy-MM-dd");
        }
    }
    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }
    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation=lastKnownLocation;
    }
    public Inquirer getInquirer() {return this.inquirer;}
    public DisasterVictim getMissingPerson() {return this.missingPerson;}
    public String getDateOfInquiry() {return this.dateOfInquiry;}
    public String getInfoProvided() {return this.infoProvided;}
    public Location getLastKnownLocation() {return this.lastKnownLocation;}
    public String getLogDetails() {return ("Inquirer: " + this.inquirer.getFirstName() +", Missing Person: " +this.missingPerson.getFirstName() +", Date of Inquiry: "+
            this.dateOfInquiry +", Info Provided: "+this.infoProvided+ ", Last Known Location: " + this.lastKnownLocation.getName());}


}


