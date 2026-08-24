package com.mycompany.practical_assignment_1;
public class INPATIENT extends PATIENT {
    
    private int wardNumber;
    private String bedNumber;

    public INPATIENT(String patientID, String firstName, String lastName, int age, String gender, String medicalCondition, PATIENT_CATEGORIES category, int wardNumber, String bedNumber) {
        super(patientID, firstName, lastName, age, gender, medicalCondition, category);
        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
    }
    /**
     * Return's the patient's ward number
     * @return ward number
     */
    public int getWardNumber() {
        return wardNumber;
    }
    /**
     * Changes the patient's ward number
     * @param wardNumber 
     */
    public void setWardNumber(int wardNumber) {
        this.wardNumber = wardNumber;
    }
    /**
     * Returns the patient's bed number
     * @return bed number
     */
    public String getBedNumber() {
        return bedNumber;
    }
    /**
     * Changes the patient's bed number
     * @param bedNumber 
     */
    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }
    @Override
    public void displayDetails() {
        //Displays the patient details inherited from PATIENT
        super.displayDetails();
        
        //Displays the ward number and bed number as part of the patient's information
        System.out.println("Ward number: " + wardNumber);
        System.out.println("Bed number: " + bedNumber);
    }
}
