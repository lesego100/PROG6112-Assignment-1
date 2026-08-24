package com.mycompany.practical_assignment_1;
/**
 * Represents a patient who is on the hospital's system
 * @author Student
 */
public class PATIENT {
    //Patient information
    private String patientID;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PATIENT_CATEGORIES category;
    
    /**
     * Constructor used to create a Patient object
     * @param patientID unique patient ID
     * @param firstName patient's first name
     * @param lastName patient's last name
     * @param age patient's age
     * @param gender patient's gender
     * @param medicalCondition patient's medical condition
     * @param category patient's category
     */
    public PATIENT(String patientID, String firstName, String lastName, int age, String gender, String medicalCondition, PATIENT_CATEGORIES category) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.category = category;
    }
    /**
     * Return's the patient's ID
     * @return the patient ID 
     */
    public String getPatientID() {
        return patientID;
    }
    /**
     * Changes the patient's ID
     * @param patientID 
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    /**
     * Return's the patient's first name
     * @return patient first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Changes the patient's first name
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Return's the patient's last name
     * @return patient last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Changes the patient's last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Return's the patient's age
     * @return patient age
     */
    public int getAge() {
        return age;
    }
    /**
     * Changes the patient's age
     * @param age 
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Return's the patient's gender
     * @return patient gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * Changes the patient's gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * Return's the patient's medical condition
     * @return patient medical condition
     */
    public String getMedicalCondition() {
        return medicalCondition;
    }
    /**
     * Changes the patient's medical condition
     * @param medicalCondition
     */
    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }
    /**
     * Return's the patient's category
     * @return patient category
     */
    public PATIENT_CATEGORIES getCategory() {
        return category;
    }
    /**
     * Changes the patient's category
     * @param category 
     */
    public void setCategory(PATIENT_CATEGORIES category) {
        this.category = category;
    }
    /**
     * This method displays the patient's details
     */
    public void displayDetails() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Medical condition: " + medicalCondition);
        System.out.println("Category: " + category);
    }
}
