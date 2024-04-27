import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;
    private static final String DATE_FORMAT_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_FORMAT_REGEX);

    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment) {
        this.location=location;
        this.treatmentDetails=treatmentDetails;
        this.dateOfTreatment=dateOfTreatment;
    }
    public void setLocation(Location location) {
        this.location=location;
    }
    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails=treatmentDetails;
    }
    public void setDateOfTreatment(String dateOfTreatment) throws IllegalArgumentException{
        Matcher matcher = DATE_PATTERN.matcher(dateOfTreatment);


        try {
            if (matcher.matches()) {

                this.dateOfTreatment = dateOfTreatment;

            } else {
                throw new IllegalArgumentException("Invalid date format for Treatment date. Please use yyyy-MM-dd");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for treatment date");
        }
    }
    public Location getLocation( ) {return this.location;}
    public String getTreatmentDetails() {return this.treatmentDetails;}
    public String getDateOfTreatment() {return this.dateOfTreatment;}
}
