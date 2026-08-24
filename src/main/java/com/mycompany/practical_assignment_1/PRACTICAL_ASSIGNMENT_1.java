package com.mycompany.practical_assignment_1;
import java.util.Scanner;
public class PRACTICAL_ASSIGNMENT_1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        //HOSPITAL_MANAGER object
        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();
        
        //Variable used to store the user's menu choice
        int choice =0;
        
        //Keeps displaying the menu until the user exits by choosing 10
        while(choice != 13) {
            
            System.out.println("\n==========HOSPITAL PATIENT ADMISSION SYSTEM==========");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display All Patients");
            System.out.println("6. Display Hospital Ward");
            System.out.println("7. Display Available Beds");
            System.out.println("8. Display Occupied Beds");
            System.out.println("9. Allocate Bed");
            System.out.println("10. Release Bed");
            System.out.println("11. Hospital Report");
            System.out.println("12. Sort Patients by Surname");
            System.out.println("13. Exit");
            
            System.out.print("\nEnter your choice: ");
            choice = input.nextInt();
            
            input.nextLine();
            
            switch(choice) {
                case 1:

                    registerPatient(input, manager);

                    break;

                case 2: 

                    System.out.println("\n==========SEARCH PATIENT==========");
                    System.out.print("Enter Patient ID: ");
                    String searchID = input.nextLine();

                    PATIENT patient = manager.searchPatient(searchID);

                    if(patient == null) {

                        System.out.println("Patient not found.");

                    }
                    else {

                        System.out.println("Patient found.");

                        patient.displayDetails();
                    }

                    break;

                case 3: 

                    System.out.println("\n==========UPDATE PATIENT==========");
                    System.out.print("Enter Patient ID to update: ");
                    String updateID = input.nextLine();

                    patient = manager.searchPatient(updateID);

                    if(patient == null) {

                    System.out.println("Patient not found.");

                    }
                    else {

                        System.out.print("Enter new First Name: ");
                        String firstName = input.nextLine();

                        System.out.print("Enter new Last Name: ");
                        String lastName = input.nextLine();

                        System.out.print("Enter new Age: ");
                        int age = input.nextInt();

                        input.nextLine();

                        System.out.print("Enter new Gender: ");
                        String gender = input.nextLine();

                        System.out.print("Enter new Medical Condition: ");
                        String medicalCondition = input.nextLine();

                        System.out.println("\nSelect a new patient category:");
                        System.out.println("1. Inpatient");
                        System.out.println("2. Outpatient");
                        System.out.println("3. Emergency");

                        System.out.print("Enter Category: ");
                        int categoryChoice = input.nextInt();

                        input.nextLine();

                        PATIENT_CATEGORIES category = null;

                        if(categoryChoice == 1) {

                            category = PATIENT_CATEGORIES.INPATIENT;

                        }
                        else if(categoryChoice == 2) {

                            category = PATIENT_CATEGORIES.OUTPATIENT;

                        }
                        else if(categoryChoice == 3) {

                            category = PATIENT_CATEGORIES.EMERGENCY;

                        }
                        else {

                            System.out.println("Invalid category.");       
                        }

                        boolean updated = manager.updatePatientDetails(updateID, firstName, lastName,age, gender, medicalCondition, category);

                        if(updated == true) {

                            System.out.println("Patient updated successfully.");

                        }
                        else {

                            System.out.println("Patient could not be updated.");
                        }
                    }
                    break;

                case 4: 

                    System.out.println("\n==========DELETE PATIENT==========");
                    System.out.print("Enter Patient ID to delete: ");
                    String deleteID = input.nextLine();

                    boolean deleted = manager.deletePatient(deleteID);

                    if(deleted == true) {

                        System.out.println("Patient deleted successfully.");

                    }
                    else {

                        System.out.println("Patient could not be deleted.");
                    }
                    break;

                case 5: 

                    manager.displayRegisteredPatients();

                    break;

                case 6: 

                    manager.displayBeds();

                    break;

                case 7: 

                    manager.displayAvailableBeds();

                    break;

                case 8: 

                    manager.displayOccupiedBeds();

                    break;

                case 9: 

                    System.out.println("\n==========ALLOCATE BED==========");
                    System.out.print("Enter Inpatient ID: ");
                    String inpatientID = input.nextLine();

                    patient = manager.searchPatient(inpatientID);

                    if(patient == null) {

                        System.out.println("Patient not found.");

                    }
                    else if(patient instanceof INPATIENT) {

                        INPATIENT inpatient = (INPATIENT) patient;

                        boolean allocated = manager.allocateBeds(inpatient);

                        if(allocated == true) {

                            System.out.println("Bed allocated successfully.");
                            System.out.println("Ward Number: " + inpatient.getWardNumber());
                            System.out.println("Bed Number: " + inpatient.getBedNumber());

                        }
                        else {

                            System.out.println("Bed could not be allocated. " + "The inpatient may already have a bed " + "or no beds are available.");
                        }

                    }
                    else {

                        System.out.println("This patient is not registered as an inpatient.");
                    }
                    break;

                case 10: 

                    System.out.println("\n==========RELEASE BED==========");
                    System.out.print("Enter Inpatient ID: ");
                    inpatientID = input.nextLine();

                    patient = manager.searchPatient(inpatientID);

                    if(patient == null) {

                        System.out.println("Patient not found.");

                    }
                    else if(patient instanceof INPATIENT) {

                        INPATIENT inpatient = (INPATIENT) patient;

                        boolean released = manager.releaseBed(inpatient);

                        if(released == true) {

                            System.out.println("Bed released successfully.");

                        }
                        else {

                            System.out.println("This inpatient does not have a bed.");
                        }

                    }
                    else {

                        System.out.println("This patient is not an inpatient.");
                    }
                    break;

                case 11: 

                    manager.displayHospitalReport();

                    break;

                case 12:

                    manager.sortPatientsBySurname();

                    manager.displayRegisteredPatients();

                    break;

                case 13:

                    System.out.println("Thank you for using the Hospital Admission System. Goodbye...");

                    break;

                default: 

                    System.out.println("Invalid choice. Please select a number from 1 to 13.");

                    break;
            }
        }
    }
    public static void registerPatient(Scanner input, HOSPITAL_MANAGER manager) {
        
        System.out.println("\n==========REGISTER PATIENT==========");
        
        //Ask for the patient ID
        System.out.print("Enter Patient ID: ");
        String patientID = input.nextLine();
        
        //Check if the patient ID is already in use
        PATIENT existingPatient = manager.searchPatient(patientID);
        
        if(existingPatient != null) {
            
            System.out.println("A patient with this ID already exists.");
            
            return;
        }
        
        //Ask for the patient's first name
        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();
        
        //Ask for the patient's last name
        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();
        
        //Ask for the patient's age
        System.out.print("Enter Age: ");
        int age = input.nextInt();
        
        input.nextLine();
        
        //Ask for the patient's gender
        System.out.print("Enter Gender: ");
        String gender = input.nextLine();
        
        //Ask for the patient's medical condition
        System.out.print("Enter Medical Condition: ");
        String medicalCondition = input.nextLine();
        
        //Ask the patient to choose a category
        System.out.println("\nSelect a patient category: ");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");
        
        System.out.print("Enter Category: ");
        int categoryChoice = input.nextInt();
        
        input.nextLine();
        
        PATIENT_CATEGORIES category;
        
        if(categoryChoice == 1) {
            
            category = PATIENT_CATEGORIES.INPATIENT;
        } 
        else if(categoryChoice == 2) {
            
            category = PATIENT_CATEGORIES.OUTPATIENT;
        }
        else if(categoryChoice == 3) {
            category = PATIENT_CATEGORIES.EMERGENCY;
        }
        else {
            System.out.println("Invalid category.");
            return;
        }
        
        //Create the PATIENT object
        PATIENT patient;
        
        if(category == PATIENT_CATEGORIES.INPATIENT) {
            
            //Create an INPATIENT object
            patient = new INPATIENT(patientID, firstName, lastName, age, gender, medicalCondition, category, 0, "");
            
        }
        else {
            //Create a patient object
            patient = new PATIENT(patientID, firstName, lastName, age, gender, medicalCondition, category);
        }
        
        //Send the PATIENT object to HOSPITAL_MANAGER
        boolean registered = manager.registerPatient(patient);
        
        if(registered == true) {
            
            System.out.println("Patient registered successfully.");
        }
        else {
            
            System.out.println("Patient registration failed.");
        }
    }
}

