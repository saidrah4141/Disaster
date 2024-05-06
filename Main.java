import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main implements ReliefServiceInterface{
	 private static Location reliefCenter = new Location("University of Calgary", " 2500 University Dr NW");
	 private static Scanner scanner = new Scanner(System.in);
	 private static Set<Inquirer> inquirers= new HashSet<>();;
	 public static void main(String[] args) {
	 Main mainInstance = new Main();
	 Database myJDBC = new Database("jdbc:postgresql://localhost/ensf380project","oop","ucalgary");	
	 myJDBC.initializeConnection();
	 int choice;
	 int ID;     
	        
	        
	  
	 do {
	        System.out.println("Choose an option:");
	        System.out.println("1. Central Worker");
	        System.out.println("2. Location Woker");
	        System.out.println("3. Exit");
	        System.out.print("Enter your choice: ");
	        
	        choice = scanner.nextInt();
	        
	         switch (choice) {
	         case 1:
	        	 int choice1;
	             do {
	                 
	                 System.out.println("Main Menu:");
	                 System.out.println("1. View your current inquiries");
	                 System.out.println("2. View pending inquiries");
	                 System.out.println("3. Exit");
	                 System.out.println("Enter your choice: ");
	                 choice1 = scanner.nextInt();

	                 switch (choice1) {
	                     case 1:
	                         
	                         myJDBC.getAllInquirers();
	                         System.out.println("Enter your ID number: ");
	                         ID = scanner.nextInt();
	                         scanner.nextLine();
	                         System.out.println("Here are your current inquiries: ");
	                         myJDBC.getInquiryLogsById(ID);
	                         break;

	                     case 2:
	                         
	                    	 myJDBC.getAllInquirers();
	                         System.out.println("Enter your ID number: ");
	                         ID = scanner.nextInt();
	                         scanner.nextLine(); 
	                         System.out.println("Viewing pending inquiries...");
	                         Set<DisasterVictim> pendingInquiries = getPendingInquiries(ID);
	                         System.out.println("Pending inquiries:");
	                         for (DisasterVictim victim : pendingInquiries) {
	                             System.out.println(victim.getFirstName());
	                          
	                         }
	                         if (pendingInquiries.size()==0){
	                        	 System.out.println("No Pending inquiries:");
	                        	 break;
	                         }
	                         
                             DisasterVictim foundVictim = searchVictimByName(scanner);
                             Inquirer inquirer = myJDBC.getInquirerById(ID);
        
                             System.out.println("Enter interaction details: ");
                             String interactionDetails = scanner.nextLine();
                             String timestamp = java.time.LocalDate.now().toString();
                             Interaction interaction = new Interaction(String.valueOf(ID), timestamp, interactionDetails);
                             inquirer.logInteraction(foundVictim,interaction);
                             inquirers.add(inquirer);
                             foundVictim.setComments(interactionDetails);
                             foundVictim.setSocialID(0);
                             java.sql.Date sqlDate = java.sql.Date.valueOf(timestamp);
                             myJDBC.insertNewInquiryLog(ID, sqlDate, interactionDetails);
	                         break;

	                     case 3:
	                         
	                         System.out.println("Exiting...");
	                         break;

	                     default:
	                         System.out.println("Invalid choice. Please enter a valid option.");
	                 }
	             } while (choice1 != 3);

	            
	             break;

	         case 2:
	             
	        	 int choice2;
	             do {
	                 displayMenu();
	                 choice2 = scanner.nextInt();
	                 scanner.nextLine(); 

	                 switch (choice2) {
	                     case 1:
	                         mainInstance.addDisasterVictim();
	                         break;
	                     case 2:
	                         mainInstance.manageSupply();
	                         break;
	                     case 3:
	                         mainInstance.editDisasterVictim();
	                         break;
	                     case 4:
	                         
	                         System.out.println("Exiting...");
	                         break;
	                     default:
	                         System.out.println("Invalid choice. Please enter a number between 1 and 5.");
	                 }
	             } while (choice2 != 4);
	             break;

	         case 3:
	             
	             System.out.println("Exiting...");
	             break;

	         default:
	             System.out.println("Invalid choice");
	             break;
	     }
	        
	 }while(choice!=3);
	 myJDBC.close();
     
	    }
	 
	 private static void displayMenu() {
			 System.out.println("Menu:");
			 System.out.println("Welcome to Relief Service!");
		        System.out.println("1. Add Disaster Victim");
		        System.out.println("2. Manage Supply");
		        System.out.println("3. Search and Edit Victim");
		        System.out.println("4. Exit");
		        System.out.println("Enter your choice: ");
	    }
	 @Override
	 public void addDisasterVictim() {
		 	String fName;
		 	String entryDate;
		 	
	        System.out.println("Adding a disaster victim...");
	        System.out.println("Enter the first name of the disaster victim:");
	        fName = scanner.nextLine();

	        System.out.println("Enter the entry date of the disaster victim (yyyy-MM-dd):");
	        entryDate = scanner.nextLine();

	        reliefCenter.addOccupant(new DisasterVictim(fName, entryDate));

	        System.out.println("Disaster victim added successfully.");
	    }
	 @Override
	 public void editDisasterVictim() {
		
		 	Database myJDBC = new Database("jdbc:postgresql://localhost/ensf380project","oop","ucalgary");	
		 	myJDBC.initializeConnection();
	        int choice;
	       
	        DisasterVictim foundVictim = searchVictimByName(scanner);
	        
	        if (foundVictim != null) {
	            
	            System.out.println("Victim found! Current details:");
	            do {
	                System.out.println("First Name: " + foundVictim.getFirstName());
	                System.out.println("Last Name: " + foundVictim.getLastName());
	                System.out.println("Age: " + foundVictim.getAge()); 
	                System.out.println("Date of Birth: " + foundVictim.getDateOfBirth());
	                System.out.println("Medical Records:  " + foundVictim.getMedicalRecords());
	                System.out.println("Personal Belongings: " + foundVictim.getPersonalBelongings());
	                System.out.println("Gender: " + foundVictim.getGender());
	                System.out.println("Dietary Restrictions: " + foundVictim.getDietaryRestrictions());
	                System.out.println("Social Worker ID: "+ foundVictim.getAssignedSocialID());
	                System.out.println("Family Relations: "+ foundVictim.getFamilyConnections());
	                System.out.println("Comments: "+ foundVictim.getComments());
	                
	                
	                
	                System.out.println("Select an option to edit:");
	                System.out.println("1. Set Last Name");
	                System.out.println("2. Set Age");
	                System.out.println("3. Set Date of Birth");
	                System.out.println("4. Add Medical Record");
	                System.out.println("5. Add Supplies");
	                System.out.println("6. Assign Gender");
	                System.out.println("7. Add Dietary Restriction");
	                System.out.println("8. Assign Inquirer");
	                System.out.println("9. Add family Connection");
	                System.out.println("10. Exit");
	                System.out.print("***Warning if DOB is already set trying to set age will clear DOB"
	                		+ " and vice-versa.*** ");
	                System.out.print("Enter your choice: ");
	                
	                choice = scanner.nextInt();
	                scanner.nextLine(); 
	                
	                switch (choice) {
	                    case 1:
	                        System.out.println("Enter the last name:");
	                        String lastName = scanner.nextLine();
	                        foundVictim.setLastName(lastName);
	                        System.out.println("Last name set to: " + lastName);
	                        break;
	                    case 2:
	                        System.out.println("Enter the age:");
	                        String age = scanner.nextLine();
	                        foundVictim.setAge(age);
	                        System.out.println("Age set to: " + age);
	                        break;
	                    case 3:
	                        System.out.println("Enter the date of birth (YYYY-MM-DD):");
	                        String dob = scanner.nextLine();
	                        foundVictim.setDateOfBirth(dob);
	                        System.out.println("Date of birth set to: " + dob);
	                        break;
	                    case 4:
	                        System.out.println("Adding a medical record...");
	                        addVictimMedicalRecord(foundVictim);
	                        break;
	                    case 5:
	                        System.out.println("Adding supplies...");
	                        allocateVictimSupply(foundVictim);
	                        break;
	                    case 6:
	                    	try {
	                            
	                            String filePath = "GenderOptions.txt";
	                          
	                            List<String> genderOptions = FileRead.readLines(filePath);
	                            
	                            
	                            System.out.println("Available gender options:");
	                            for (int i = 0; i < genderOptions.size(); i++) {
	                                System.out.println((i + 1) + ". " + genderOptions.get(i));
	                            }
	                            
	                            
	                            System.out.print("Enter the number of the gender option: ");
	                            int optionIndex = scanner.nextInt();
	                            scanner.nextLine(); 
	                            
	                            
	                            if (optionIndex >= 1 && optionIndex <= genderOptions.size()) {
	                                String selectedGender = genderOptions.get(optionIndex - 1);
	                                foundVictim.setGender(selectedGender);
	                                System.out.println("Gender set to: " + selectedGender);
	                            } else {
	                                System.out.println("Invalid option number.");
	                            }
	                        } catch (IOException e) {
	                            System.out.println("Error reading gender options from file: " + e.getMessage());
	                        }
	                    
	                        break;
	                    case 7:
	                        System.out.println("Adding dietary restriction...");
	                        foundVictim.printAllDietaryRestrictions();
	                        System.out.println("Enter the dietary restriction to add:");
	                        String userInput = scanner.nextLine();
	                        try {
	                            DietaryRestriction restriction = DietaryRestriction.valueOf(userInput.toUpperCase());
	                            foundVictim.addDietaryRestriction(restriction);
	                            System.out.println("Dietary restriction added successfully.");
	                        } catch (IllegalArgumentException e) {
	                            System.out.println("Invalid dietary restriction entered. Please enter a valid restriction.");
	                        }
	                        
	                        break;
	                    case 8:
	                    	if(foundVictim.getComments()==null) {
	                        System.out.println("Assigning inquirer...");
	                        myJDBC.getAllInquirers();
	                        System.out.println("Select an inquirer's id to assign to victim: ");
	                        int selectedID= scanner.nextInt();
	                        foundVictim.setSocialID(selectedID);
	                    	}else {
	                    		System.out.println("Victim has already seen an inquirer");
	                    	}
	                        break;
	                    case 9:
	                        addVictimFamilyConnection(foundVictim);
	                        break;
	                   case 10:
	                        System.out.println("Exiting...");
	                        break;
	                    default:
	                        System.out.println("Invalid choice.");
	                }
	            } while(choice != 10);
	        } 
	 }
	 
	
	 @Override
	 public void manageSupply() {
		 
	        System.out.println("Managing supplies...");
	        for (Map.Entry<String, Integer> entry : reliefCenter.getAllSupplies().entrySet()) {
	            String supplyType = entry.getKey();
	            int quantity = entry.getValue();
	            System.out.println(supplyType + ": " + quantity);
	        }

	   
	 
	        while (true) {
	            System.out.println("Select an option:");
	            System.out.println("1. Add supply");
	            System.out.println("2. Remove supply");
	            System.out.println("3. Exit");

	            int choice = scanner.nextInt();
	            switch (choice) {
	                case 1:
	                    
	                    System.out.println("Enter the supply type:");
	                    String type = scanner.next();
	                    System.out.println("Enter the quantity:");
	                    int quantityToAdd = scanner.nextInt();
	                    reliefCenter.addSupply(type, quantityToAdd);
	                    break;
	                case 2:
	                    
	                    System.out.println("Enter the supply type:");
	                    String typeToRemove = scanner.next();
	                    System.out.println("Enter the quantity:");
	                    int quantityToRemove = scanner.nextInt();
	                    reliefCenter.removeSupply(typeToRemove, quantityToRemove);
	                    break;
	                case 3:
	                    
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please select again.");
	            }
	        }
	    }
	
	 private static void allocateVictimSupply(DisasterVictim victim) {
		 String type;
		 int amount;
		 System.out.println("Available Supplies:");
		 for (Map.Entry<String, Integer> entry : reliefCenter.getAllSupplies().entrySet()) {
			    String supplyType = entry.getKey();
			    int quantity = entry.getValue();
			    System.out.println(supplyType + ": " + quantity);
			}

		 System.out.println("\nEnter the supply type and amount");
		 System.out.println("Type:");
		 type = scanner.nextLine();
		 System.out.println("Amount:");
		 amount = scanner.nextInt();
		 reliefCenter.removeSupply(type, amount);
		 if (reliefCenter.removeSupply(type, amount)) {
			   victim.addPersonalBelonging(type, amount);
			} 
	 }
	 private static void addVictimMedicalRecord(DisasterVictim victim) {
		 System.out.println("Medical Records on File :");
		 ArrayList<MedicalRecord> medicalRecords = victim.getMedicalRecords();
		    
		    System.out.println("Medical Records for " + victim.getFirstName() + " " + victim.getLastName() + ":");

		    for (MedicalRecord record : medicalRecords) {
		    	System.out.println("Location: " + 		   record.getLocation());
		    	System.out.println("Treatment Details: " + record.getTreatmentDetails());
		        System.out.println("Date of Treatment: " + record.getDateOfTreatment());
		        
		    }
		    System.out.println("Enter treatment details:");
		    String treatmentDetails = scanner.nextLine();
		    
		    System.out.println("Enter date of treatment (YYYY-MM-DD):");
		    String dateOfTreatment = scanner.nextLine();
		    MedicalRecord newMedicalRecord = new MedicalRecord(reliefCenter, treatmentDetails, dateOfTreatment);
		    victim.addMedicalRecord(newMedicalRecord);
		    System.out.println("Medical Record added for " + victim.getFirstName() + " " + victim.getLastName() + ".");
	 
	 }
	 private static void addVictimFamilyConnection(DisasterVictim victim) {
		 
	        DisasterVictim foundVictim = searchVictimByName(scanner);

	        if (foundVictim != null) {
	            
	            if (victim.hasDuplicateRelationship(foundVictim)) {
	                    System.out.println("Relationship between " + victim.getFirstName() + " and " + foundVictim.getFirstName() + " already exists.");
	                    return;
	              }
	            

	            
	            System.out.println("Enter the relationship between " + victim.getFirstName() + " and " + foundVictim.getFirstName() + ": ");
	            String relationship = scanner.nextLine();

	            FamilyRelation newRelation1 = new FamilyRelation(victim, relationship, foundVictim);
	            victim.addFamilyConnection(newRelation1);
	            FamilyRelation newRelation2 = new FamilyRelation(foundVictim, relationship, victim);
	            foundVictim.addFamilyConnection(newRelation2);
	          

	            System.out.println("Relationship added successfully between " + victim.getFirstName() + " and " + foundVictim.getFirstName() + ".");
	            
	            if (!victim.hasCompleteSeries(foundVictim)) {
	               
	                updateFamilyRelations(victim, foundVictim);
	            }
	           
	        } 
	 }
	 private static void updateFamilyRelations(DisasterVictim victim, DisasterVictim foundVictim) {
		    
		    DisasterVictim thirdPerson = null;
		    for (FamilyRelation relation : victim.getFamilyConnections()) {
	            if (!relation.getPersonOne().equals(foundVictim) && !relation.getPersonTwo().equals(foundVictim)) {
	                thirdPerson = relation.getPersonOne().equals(victim) ? relation.getPersonTwo() : relation.getPersonOne();
	                break;
	            }
	        }


		    
		    for (FamilyRelation relation : victim.getFamilyConnections()) {
		        if (relation.getPersonOne().equals(thirdPerson) || relation.getPersonTwo().equals(thirdPerson)) {
		        	FamilyRelation newRelation1 = new FamilyRelation(thirdPerson, relation.getRelationshipTo(), foundVictim);
		        	foundVictim.addFamilyConnection(newRelation1);
		        	FamilyRelation newRelation2 = new FamilyRelation(foundVictim, relation.getRelationshipTo(), thirdPerson);
		        	thirdPerson.addFamilyConnection(newRelation2);
		        }
		    }
		}

	 
	 @Override
	 public void logInquirerQueries() {
	        
	        System.out.println("Logging inquirer queries...");
	    }
	 
	@Override
	public void searchDisasterVictims(Scanner scanner) {
		System.out.print("Enter part of the name to search for: ");
        String searchQuery = scanner.nextLine().toLowerCase(); 

        
        List<String> matchedNames = new ArrayList<>();
        
        for (DisasterVictim victim : reliefCenter.getOccupants()) {
            if (victim.getFirstName().toLowerCase().contains(searchQuery)
                    || victim.getLastName().toLowerCase().contains(searchQuery)) {
                matchedNames.add(victim.getFirstName() + " " + victim.getLastName());
            }
        }

        
        if (!matchedNames.isEmpty()) {
            System.out.println("Matched names:");
            for (String name : matchedNames) {
                System.out.println(name);
            }
        } else {
            System.out.println("No matching names found.");
        }
		
	}
	private static Set<DisasterVictim> getPendingInquiries(int ID) {
	    Set<DisasterVictim> pendingInquiries = new HashSet<>();

	    for (DisasterVictim victim : reliefCenter.getOccupants()) {
	        if (victim.getAssignedSocialID() == ID) {
	            pendingInquiries.add(victim);
	        }
	    }

	    if (pendingInquiries.isEmpty()) {
	        
	        return null; 
	    }

	    return pendingInquiries;
	}

	public static DisasterVictim searchVictimByName(Scanner scanner) {
	    System.out.println("Enter the partial name of the disaster victim to begin Inquiry:");
	    String partialName = scanner.nextLine();
	    List<DisasterVictim> matchingVictims = reliefCenter.searchByPartialFirstName(partialName, reliefCenter.getOccupants());

	    if (matchingVictims.isEmpty()) {
	        System.out.println("No matching victims found.");
	    } else {
	        System.out.println("Matching victims:");
	        for (int i = 0; i < matchingVictims.size(); i++) {
	            System.out.println((i + 1) + ". " + matchingVictims.get(i).getFirstName());
	        }

	        System.out.println("\nEnter the full name of the disaster victim to proceed with the search:");
	        String victimName = scanner.nextLine();
	        DisasterVictim foundVictim = reliefCenter.searchByFirstName(victimName, reliefCenter.getOccupants());
	        if (foundVictim != null) {
	            System.out.println("Victim found: " + foundVictim.getFirstName());
	            return foundVictim;
	        } else {
	            System.out.println("Victim not found.");
	        }
	    }
	    return null; 
	}
}


