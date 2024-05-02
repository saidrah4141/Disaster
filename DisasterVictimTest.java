
import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class DisasterVictimTest {
    private DisasterVictim victim;
    private HashMap<String,Integer> suppliesToSet;
    private List<FamilyRelation> familyRelations;
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String validDate = "2024-01-15";
    private String invalidDate = "15/13/2024";
    private String expectedGender = "female";
    private String expectedComments = "Needs medical attention and speaks 2 languages";
    public DisasterVictimTest() {

    }
    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE);
        suppliesToSet = new HashMap<>();
        suppliesToSet.put("candy", 1);
        suppliesToSet.put("blanket", 1);
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

    }
    @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024";
        new DisasterVictim("Freda", invalidEntryDate);

    }




    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDate);
    }

        @Test
        public void testSetAndGetFirstName() {
            String newFirstName = "Alice";
            victim.setFirstName(newFirstName);
            assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName, victim.getFirstName());
        }

        @Test
        public void testSetAndGetLastName() {
            String newLastName = "Smith";
            victim.setLastName(newLastName);
            assertEquals("setLastName should update and getLastName should return the new last name", newLastName, victim.getLastName());
        }

        @Test
        public void testGetComments() {
            victim.setComments(expectedComments);
            assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
        }

        @Test
        public void testSetComments() {
            victim.setComments(expectedComments);
            String newComments = "Has a minor injury on the left arm";
            victim.setComments(newComments);
            assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
        }
        @Test
        public void testGetEntryDate() {
            assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
        }

        @Test
        public void testSetAndGetGender() {
            String newGender = "male";
            victim.setGender(newGender);
            assertEquals("setGender should update and getGender should return the new gender", newGender.toLowerCase(), victim.getGender());
        }
        @Test(expected = IllegalArgumentException.class)
        public void testSetGenderWithIllegal() {
            String newGender = "Rhino";
            victim.setGender(newGender);
        }
        @Test
        public void testSetAndGetAge() {

            victim.setAge("15");
            String expResult = "15";
            String result = victim.getAge();
            assertEquals("Calculated age was incorrect: ", expResult, result);
            assertEquals("Date of birth should be null",null,victim.getDateOfBirth());
        }

        @Test
        public void testSetDateOfBirth() {
            String newDateOfBirth = "1987-05-21";
            victim.setDateOfBirth(newDateOfBirth);
            assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth, victim.getDateOfBirth());
            assertEquals("Age should be null",null,victim.getAge());
        }


        @Test
        public void testAddPersonalBelonging() {
            String newSupply = "Emergency Kit";
            int quantity = 1;
            victim.addPersonalBelonging(newSupply, quantity);
            HashMap<String, Integer> testSupplies = victim.getPersonalBelongings();
            boolean correct = false;


            for (String key : testSupplies.keySet()) {
                if (key.equals(newSupply)) {
                    correct = true;
                    break;
                }
            }
            assertTrue("addPersonalBelonging should add the supply to personal belongings", correct);
        }
        @Test
        public void testRemovePersonalBelonging() {

            String supplyToRemove = null;
            int quantity=0;
            for (Map.Entry<String, Integer> entry : suppliesToSet.entrySet()) {
                supplyToRemove = entry.getKey();
                quantity = suppliesToSet.get(supplyToRemove);
                break;
            }
            victim.addPersonalBelonging(supplyToRemove,quantity);
            victim.removePersonalBelonging(supplyToRemove);

            HashMap<String,Integer> testSupplies = victim.getPersonalBelongings();

            boolean correct = !testSupplies.containsKey(supplyToRemove);

            assertTrue("removePersonalBelonging should remove the supply from personal belongings", correct);
        }

        @Test
        public void testAddFamilyConnection() {
            DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
            DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

            FamilyRelation relation = new FamilyRelation(victim2, "parent", victim1);
            ArrayList<FamilyRelation> expectedRelations= new ArrayList<>();
            expectedRelations.add(relation);

            victim2.setFamilyConnections(expectedRelations);

            ArrayList<FamilyRelation> testFamily=victim2.getFamilyConnections();
            boolean correct = false;

            if ((testFamily!=null) && (testFamily.get(0) == expectedRelations.get(0))) {
                correct = true;
            }
            assertTrue("addFamilyConnection should add a family relationship", correct);
        }
        @Test
        public void testRemoveFamilyConnection() {
            DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
            DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
            FamilyRelation relation1 = new FamilyRelation(victim, "sibling", victim1);
            FamilyRelation relation2 = new FamilyRelation(victim, "sibling", victim2);

            ArrayList<FamilyRelation> expectedRelations = new ArrayList<>();
            expectedRelations.add(relation2);
            ArrayList<FamilyRelation> originalRelations = new ArrayList<>();
            originalRelations.add(relation1);
            originalRelations.add(relation2);

            victim.setFamilyConnections(originalRelations);

            DisasterVictim victim = new DisasterVictim("Freda", "2024-01-23");
            victim.addFamilyConnection(relation1);
            victim.addFamilyConnection(relation2);
            victim.removeFamilyConnection(relation1);

            ArrayList<FamilyRelation> testFamily = victim.getFamilyConnections();
            boolean correct = true;

            int i;
            for (i = 0; i < testFamily.size(); i++) {
                if (testFamily.get(i) == relation1) {
                    correct = false;
                }
            }
            assertTrue("removeFamilyConnection should remove the family member", correct);
        }
        @Test
        public void testSetMedicalRecords() {
            Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
            MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");
            boolean correct = true;

            ArrayList<MedicalRecord> newRecords = new ArrayList<>();
            newRecords.add(testRecord);
            victim.setMedicalRecords(newRecords);
            ArrayList<MedicalRecord> actualRecords = victim.getMedicalRecords();


            if (newRecords.size() != actualRecords.size()) {
                correct = false;
            } else {
                int i;
                for (i=0;i<newRecords.size();i++) {
                    if (!actualRecords.get(i).equals(newRecords.get(i))) {
                        correct = false;
                    }
                }
            }
            assertTrue("setMedicalRecords should correctly update medical records", correct);
        }
        @Test
        public void testSetPersonalBelongings() {
            HashMap<String, Integer> newSupplies = new HashMap<>();
            newSupplies.put("Juice", 2);
            newSupplies.put("Chocolate", 1);
            boolean correct = true;

            victim.setPersonalBelongings(newSupplies);
            HashMap<String, Integer> actualSupplies = victim.getPersonalBelongings();


            if (newSupplies.size() != actualSupplies.size()) {
                correct = false;
            } else {

                for (String key : actualSupplies.keySet()) {

                    if (!newSupplies.containsKey(key)) {
                        correct = false;
                        break;
                    }

                    if (!newSupplies.get(key).equals(actualSupplies.get(key))) {
                        correct = false;
                        break;
                    }
                }
            }
            assertEquals("setPersonalBelongings should correctly update personal belongings", newSupplies, actualSupplies);
        }
            @Test
        public void testAddAndGetDietaryRestriction() {

            DisasterVictim victim = new DisasterVictim("Freda", "2024-01-18");


            victim.addDietaryRestriction(DietaryRestriction.AVML);
            victim.addDietaryRestriction(DietaryRestriction.DBML);


            assertTrue(victim.getDietaryRestrictions().contains(DietaryRestriction.AVML));
            assertTrue(victim.getDietaryRestrictions().contains(DietaryRestriction.DBML));
        }


    }
