package com.mycompany.practical_assignment_1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class HOSPITAL_MANAGERTest {
    
    /**
     * Test 1: Register patients
     */
    @Test
    public void testRegisterPatient() {
        
        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();
        
        PATIENT patient = new PATIENT("P001", "Lesego", "Malesa", 19, "Female", "Cracked humerus", PATIENT_CATEGORIES.EMERGENCY);
        
        boolean result = manager.registerPatient(patient);
        
        assertTrue(result);
    }
    /**
     * Test 2: Search for patient using their patient ID
     */
    @Test
    public void testSearchPatient() {
        
        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();
        
        PATIENT patient = new PATIENT("P001", "Lesego", "Malesa", 19, "Female", "Cracked humerus", PATIENT_CATEGORIES.EMERGENCY);
        
        manager.registerPatient(patient);
        
        PATIENT result = manager.searchPatient("P001");
        
        assertNotNull(result);
        assertEquals("P001", result.getPatientID());
    }
    /**
     * Test 3: Update patient details
     */
    @Test
    public void testUpdatePatientDetails() {
        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();
        
        PATIENT patient = new PATIENT("P001", "Lesego", "Malesa", 18, "Female", "Cracked humerus", PATIENT_CATEGORIES.EMERGENCY);
        
        manager.registerPatient(patient);
        
        boolean result = manager.updatePatientDetails("P001", "Lesego", "Malesa", 19, "Female", "Broken arm", PATIENT_CATEGORIES.INPATIENT);
        
        assertTrue(result);
        
        PATIENT updatedPatient = manager.searchPatient("P001");
        
        assertEquals(19, updatedPatient.getAge());
        assertEquals("Broken arm", updatedPatient.getMedicalCondition());
        assertEquals(PATIENT_CATEGORIES.INPATIENT, updatedPatient.getCategory());
    }
    /**
     * Test 4: Delete patient
     */
    @Test
    public void testDeletePatient() {
        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();
        
        PATIENT patient = new PATIENT("P001", "Lesego", "Malesa", 19, "Female", "Cracked humerus", PATIENT_CATEGORIES.EMERGENCY);
        
        manager.registerPatient(patient);
        
        boolean result = manager.deletePatient("P001");
        
        assertTrue(result);
        
        PATIENT deletedPatient = manager.searchPatient("P001");
        
        assertNull(deletedPatient);
    }
    /**
     * Test 5: Prevent duplicate patient IDs
     */
    @Test
    public void testPreventDuplicatePatientISs() {
        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();
        
        PATIENT patient1 = new PATIENT("P001", "Lesego", "Malesa", 19, "Female", "Cracked humerus", PATIENT_CATEGORIES.EMERGENCY);
        PATIENT patient2 = new PATIENT("P001", "Neo", "Malesa", 15, "Male", "Cough", PATIENT_CATEGORIES.OUTPATIENT);
        
        boolean firstResult = manager.registerPatient(patient1);
        boolean secondResult = manager.registerPatient(patient2);
        
        assertTrue(firstResult);
        assertFalse(secondResult);
    }
/**
     * Test 6: Allocate a bed to an inpatient.
     */
    @Test
    public void testAllocateBed() {

        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();

        INPATIENT inpatient = new INPATIENT("P001", "Palesa", "Ndlovu", 20, "Female", "Diabetes", PATIENT_CATEGORIES.INPATIENT, 0, "");

        manager.registerPatient(inpatient);

        boolean result = manager.allocateBeds(inpatient);

        assertTrue(result);
        assertEquals(1, inpatient.getWardNumber());
        assertEquals("B01", inpatient.getBedNumber());
    }
    /**
     * Test 7: Release a bed.
     */
    @Test
    public void testReleaseBed() {

        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();

        INPATIENT inpatient = new INPATIENT("P001", "Palesa", "Ndlovu", 20, "Female", "Diabetes", PATIENT_CATEGORIES.INPATIENT, 0, "");

        manager.registerPatient(inpatient);

        manager.allocateBeds(inpatient);

        boolean result = manager.releaseBed(inpatient);

        assertTrue(result);
        assertEquals(0, inpatient.getWardNumber());
        assertEquals("", inpatient.getBedNumber());
    }
    /**
     * Test 8: Prevent allocating an occupied bed.
     */
    @Test
    public void testPreventOccupiedBed() {

        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();

        INPATIENT inpatient1 = new INPATIENT("P001", "Palesa", "Ndlovu", 20, "Female", "Diabetes",PATIENT_CATEGORIES.INPATIENT, 0, "");

        INPATIENT inpatient2 = new INPATIENT("P002", "Neo", "Malesa", 12, "Male", "Cough", PATIENT_CATEGORIES.INPATIENT, 0, "");

        manager.registerPatient(inpatient1);
        manager.registerPatient(inpatient2);

        manager.allocateBeds(inpatient1);

        boolean result = manager.allocateBeds(inpatient2);

        assertTrue(result);

        assertEquals("B01", inpatient1.getBedNumber());
        assertEquals("B02", inpatient2.getBedNumber());
    }
    /**
     * Test 9: Prevent bed allocation when all 20 beds are occupied.
     */
    @Test
    public void testPreventAllocationWhenAllBedsAreOccupied() {

        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();

        //Fill all 20 beds.
        for (int i = 1; i <= 20; i++) {

            String patientID = "P" + i;

            INPATIENT inpatient = new INPATIENT(patientID, "Peter", "Pan", 20, "Male", "Dimensia", PATIENT_CATEGORIES.INPATIENT, 0, "");

            manager.registerPatient(inpatient);

            boolean result = manager.allocateBeds(inpatient);

            assertTrue(result);
        }
        //Allocate a 21st inpatient.
        INPATIENT extraPatient = new INPATIENT("P21", "Extra", "Patient", 21, "Female", "Condition", PATIENT_CATEGORIES.INPATIENT, 0, "");

        manager.registerPatient(extraPatient);

        boolean result = manager.allocateBeds(extraPatient);

        assertFalse(result);
    }
    /**
     * Test 10: Check the total number of registered patients.
     */
    @Test
    public void testGetTotalPatients() {

        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();

        PATIENT patient = new PATIENT("P001", "Lesego", "Malesa", 18, "Female", "Condition", PATIENT_CATEGORIES.EMERGENCY);

        manager.registerPatient(patient);

        assertEquals(1, manager.getTotalPatients());
    }
    /**
     * Test 11: Check the number of occupied beds.
     */
    @Test
    public void testGetTotalOccupiedBeds() {

        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();

        INPATIENT inpatient = new INPATIENT("P001", "Palesa", "Ndlovu", 20, "Female", "Diabetes", PATIENT_CATEGORIES.INPATIENT, 0, "");

        manager.registerPatient(inpatient);
        manager.allocateBeds(inpatient);

        assertEquals(1, manager.getTotalOccupiedBeds());
    }
    /**
     * Test 12: Check the number of available beds.
     */
    @Test
    public void testGetTotalAvailableBeds() {

        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();

        assertEquals(20, manager.getTotalAvailableBeds());
    }
    /**
     * Test 13: Check occupancy percentage.
     */
    @Test
    public void testGetOccupancyPercentage() {

        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();

        INPATIENT inpatient = new INPATIENT("P001", "Palesa", "Ndlovu", 20, "Female", "Diabetes", PATIENT_CATEGORIES.INPATIENT, 0, "");

        manager.registerPatient(inpatient);
        manager.allocateBeds(inpatient);

        assertEquals(5.0, manager.getOccupancyPercentage());
    }
    /**
     * Test 14: Sort patients by surname.
     */
    @Test
    public void testSortPatientsBySurname() {

        HOSPITAL_MANAGER manager = new HOSPITAL_MANAGER();

        PATIENT patient1 = new PATIENT("P001", "Lesego", "Zulu", 18, "Female", "Condition", PATIENT_CATEGORIES.EMERGENCY);

        PATIENT patient2 = new PATIENT("P002", "Neo", "Malesa", 12, "Male", "Condition", PATIENT_CATEGORIES.OUTPATIENT);

        manager.registerPatient(patient1);
        manager.registerPatient(patient2);

        manager.sortPatientsBySurname();

        //If the sorting worked, Malesa should appear before Zulu.
        PATIENT firstPatient = manager.searchPatient("P002");

        assertEquals("Malesa", firstPatient.getLastName());
    }
    
}
