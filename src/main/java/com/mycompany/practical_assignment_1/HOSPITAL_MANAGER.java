package com.mycompany.practical_assignment_1;
import java.util.ArrayList;
public class HOSPITAL_MANAGER {
    
    //This stores all the patient's registered at the hospital
    private ArrayList<PATIENT> patients;
    //Represents the hospital's 4*5 bed layout
    private String[][] beds;
    //Stores the patient ID given to each 
    private String[][] patientsBeds;
    
    /**
     * Constructor for HOSPITAL_MANAGER
     */
    public HOSPITAL_MANAGER() {
        //Creates an emty lis of registered patients
        patients = new ArrayList<>();
        
        //Creates the 4*5 bed layout
        beds = new String[4][5];
        
        //Creates the 4*5 patient bed assingment layout
        patientsBeds = new String[4][5];
        
        //Give each bed its number
        initialiseBeds();
    }
    /**
     * Gives each bed a number from BO1 to B20
     */
    private void initialiseBeds() {
        
        int bedNumber = 1;
        
        //Outer loop that moves through the 4 rows
        for(int row = 0; row < 4; row++) {
            
            //Inner loop that moves through the 5 columns
            for(int column = 0; column < 5; column++) {
                
              if(bedNumber < 10) {
                  
                  beds[row][column] = "B0" + bedNumber;
              }  
              else {
                  
                  beds[row][column] = "B" + bedNumber;
              }
              
              bedNumber++;
            }
        }
    }
    /**
     * This method registers a new patient
     * 
     * @param patient the patient to register
     * @return true if the patient was successfully registered and false if the patient ID already exists
     */
    public boolean registerPatient(PATIENT patient) {
        //This checks if the patient ID is already in use
        for(int i = 0; i < patients.size(); i++) {
            
            //Get the patient at the current position
            PATIENT existingPatient = patients.get(i);
            
            //Check if the patient ID already exists
            if(existingPatient.getPatientID().equalsIgnoreCase(patient.getPatientID())) {
                
                return false;
            }
        }
        
        //Add the patient if the ID is not already in use
        patients.add(patient);
        
        return true;
    }
    /**
     * Method searches for patients using their patient IDs
     * 
     * @param patientID the ID to search for
     * @return the matching PATIENT object, or null if not found
     */
    public PATIENT searchPatient(String patientID) {
        
        for(int i = 0; i < patients.size(); i++) {
            
            //Get the patient at the current position
            PATIENT existingPatient = patients.get(i);
            
            //Check whether their IDs are the same
            if(existingPatient.getPatientID().equalsIgnoreCase(patientID)) {
                
                return existingPatient;
            }
        }
        
        //No patient was found
        return null;
    }
    /**
     * Method displays all registered patients
     */
    public void displayRegisteredPatients() {
        
        if(patients.size() == 0) {
            
            System.out.println("No patients are currently registered.");
            
            return;
        }
        
        System.out.println("\n===============REGISTERED PATIENTS===============");
        
        for(int i = 0; i < patients.size(); i++) {
            
            PATIENT patient = patients.get(i);
            
            patient.displayDetails();
        }
    }
    /**
     * Method updates the details of the existing patients
     */
    public boolean updatePatientDetails(String patientID, String firstName, String lastName, int age, String gender, String medicalCondition, PATIENT_CATEGORIES category) {
        
        PATIENT patient = searchPatient(patientID);
        
        if(patient == null) {
            
            return false;
        }
        // Check if the category is changing
        if(patient.getCategory() != category) {

            // If the patient is currently an inpatient,
            // check whether they have a bed.
            if(patient instanceof INPATIENT) {

                INPATIENT oldInpatient = (INPATIENT) patient;

                if(oldInpatient.getBedNumber() != null && !oldInpatient.getBedNumber().equals("")) {

                    System.out.println("The inpatient has a bed. " + "Release the bed before changing the category.");

                    return false;
                }
            }

            // Create a new INPATIENT object
            if(category == PATIENT_CATEGORIES.INPATIENT) {

                PATIENT newPatient = new INPATIENT(patientID, firstName, lastName, age, gender, medicalCondition, category, 0, "");

                patients.set(patients.indexOf(patient), newPatient);

            }
            else {

                // Create a normal PATIENT object
                PATIENT newPatient = new PATIENT(patientID, firstName, lastName, age, gender, medicalCondition, category);

                patients.set(patients.indexOf(patient), newPatient);
            }

        }
        else {

            /**
             * The category did not change, so update the existing object.
             */
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setAge(age);
            patient.setGender(gender);
            patient.setMedicalCondition(medicalCondition);
        }

        return true;
    }
    /**
     * Method deletes a patient from the patient list
     */
    public boolean deletePatient(String patientID) {
        //Go through the patient list
        for(int i = 0; i < patients.size(); i++) {
            
            PATIENT patient = patients.get(i);
            
            //Check if this is the patient we want to delete
            if(patient.getPatientID().equalsIgnoreCase(patientID)) {
                
                 // Prevent deleting an inpatient who still has a bed
                if(patient instanceof INPATIENT) {

                    INPATIENT inpatient = (INPATIENT) patient;

                    if(inpatient.getBedNumber() != null&&  !inpatient.getBedNumber().equals("")) {

                        System.out.println("Release the patient's bed before deleting the patient.");

                        return false;
                    }
                }

                patients.remove(i);

                return true;
            }
        }

        //If patient was not found 
        return false;
    }
    /**
     * Method displays all the hospital beds and their current status
     */
    public void displayBeds() {
        
        System.out.println("\n===============HOSPITAL WARD===============");
        
        //Go through each row
        for(int row = 0; row < 4; row++) {
            
            //Go through each column
            for(int column = 0; column < 5; column++) {
                
                System.out.print(beds[row][column]);
                
                //Check if the bed has a patient
                if(patientsBeds[row][column] == null) {
                    
                    System.out.println(" [Available]");
                }
                else {
                    
                    System.out.print(" [Occupied: " + patientsBeds[row][column] + "]");
                }
                
                System.out.print("    ");
            }
            
            //Move to the next line after each row
            System.out.println();
        }
    }
    /**
     * Method displays all available beds
     */
    public void displayAvailableBeds() {
        
        System.out.println("\n===============AVAILABLE BEDS===============");
        
        boolean foundAvailableBed = false;
        
        //Go through every row
        for(int row = 0; row < 4; row++) {
            
            //Go through every column
            for(int column = 0; column < 5; column++) {
                
                //Check if there is a patient assigned to that bed
                if(patientsBeds[row][column] == null) {
                    
                    System.out.println(beds[row][column]);
                    
                    foundAvailableBed = true;
                }
            }
        }
        
        //If no available bed was found
        if(foundAvailableBed == false) {
            
            System.out.println("No beds are currently available.");
        }
    }
    /**
     * Displays all occupied beds.
     */
    public void displayOccupiedBeds() {

        System.out.println("\n===============OCCUPIED BEDS===============");

        boolean foundOccupiedBed = false;

        //Go through every row
        for(int row = 0; row < 4; row++) {

            //Go through every column
            for(int column = 0; column < 5; column++) {

                if(patientsBeds[row][column] != null) {

                    System.out.println(beds[row][column] + " - Patient ID: " + patientsBeds[row][column]);

                    foundOccupiedBed = true;
                }
            }
        }

        if(foundOccupiedBed == false) {

            System.out.println("No beds are currently occupied.");
        }
    }
    /**
     * Method allocates available beds to inpatients
     */
    public boolean allocateBeds(INPATIENT inpatient) {
        
        //Check if this inpatient already has a bed
        if(inpatient.getBedNumber() != null && !inpatient.getBedNumber().equals("")) {
            
            return false;
        }
        //Check every bed in the hospital ward
        for(int row = 0; row < 4; row++) {
            
            for(int column = 0; column < 5; column++) {
                
                //Check if the current bed is available
                if(patientsBeds[row][column] == null) {
                    
                    //Store the patient's ID to the bed
                    patientsBeds[row][column] = inpatient.getPatientID();
                    
                    //Store the bed number in the INPATIENT object
                    inpatient.setBedNumber(beds[row][column]);
                    
                    //Store the ward number
                    inpatient.setWardNumber(row + 1);
                    
                    return true;
                }
            }
        }
        //If no available bed was found
        return false;
    }
    /**
     * Method releases a bed that is currently assigned to an inpatient
     */
    public boolean releaseBed(INPATIENT inpatient) {
        
        //Search through every bed
        for(int row = 0; row < 4; row++) {
            
            for(int column = 0; column < 5; column++) {
                
                //Check if this bed belongs to the inpatient
                if(patientsBeds[row][column] != null) {
                    
                    if(patientsBeds[row][column].equals(inpatient.getPatientID())) {
                        
                        //Make the bed available again
                        patientsBeds[row][column] = null;
                        
                        //Remove the bed information from the inpatient
                        inpatient.setBedNumber("");
                        inpatient.setWardNumber(0);
                        
                        return true;
                    }
                }
            }
        }
        //If the inpatient did not have a bed
        return false;
    }
    /**
     * Returns the total number of registered patients.
     */
    public int getTotalPatients() {

        return patients.size();
    }
    /**
     * Returns the total number of occupied beds.
     */
    public int getTotalOccupiedBeds() {

        int occupiedBeds = 0;

        for(int row = 0; row < 4; row++) {

            for(int column = 0; column < 5; column++) {

                if(patientsBeds[row][column] != null) {

                    occupiedBeds++;
                }
            }
        }

        return occupiedBeds;
    }
    /**
     * Returns the total number of available beds.
     */
    public int getTotalAvailableBeds() {

        return 20 - getTotalOccupiedBeds();
    }
    /**
     * Calculates the ward occupancy percentage.
     */
    public double getOccupancyPercentage() {

        return (getTotalOccupiedBeds() / 20.0) * 100;
    }
    /**
     * Displays the hospital report.
     */
    public void displayHospitalReport() {

        System.out.println("\n===============HOSPITAL REPORT===============");
        System.out.println("Total registered patients: " + getTotalPatients());
        System.out.println("Total occupied beds: " + getTotalOccupiedBeds());
        System.out.println("Total available beds: " + getTotalAvailableBeds());
        System.out.println("Ward occupancy: " + getOccupancyPercentage() + "%");
    }
    /**
     * Sorts patients by surname.
     */
    public void sortPatientsBySurname() {

        for(int i = 0; i < patients.size() - 1; i++) {

            for(int x = 0; x < patients.size() - 1 - i; x++) {

                String surname1 = patients.get(x).getLastName();

                String surname2 = patients.get(x + 1).getLastName();

                if(surname1.compareToIgnoreCase(surname2) > 0) {

                    PATIENT temporary = patients.get(x);

                    patients.set(x, patients.get(x + 1));

                    patients.set(x + 1, temporary);
                }
            }
        }

        System.out.println("Patients have been sorted by surname.");
    }
}
