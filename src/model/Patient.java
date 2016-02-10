package model;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String patienAddress;
    private String patientPhoneNumber;
    private HeartSensor heartSensor;
    private BloodPressureSensor bloodPressureSensor;
    private List<HistoryItem> history;
    private List<MedicationItem> medications;
    private static int historyIdCount;    
    
    public Patient(String firstName, String lastName, String address, 
                   String phoneNumber, String ssd, boolean heartSensorInstalled,
                   boolean bloodPressureSensorInstalled) {
        this.socialSecurityNumber = ssd;
        this.patienAddress = address;
        this.patientPhoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.history = new ArrayList<HistoryItem>();
        this.medications = new ArrayList<MedicationItem>();
        this.historyIdCount = 0;
        heartSensor = new HeartSensor(heartSensorInstalled);
        bloodPressureSensor = new BloodPressureSensor(bloodPressureSensorInstalled);        
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatienAddress() {
        return patienAddress;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }
              
    @Override
    public String toString(){
        return this.lastName + ", " + this.firstName + " (" + this.socialSecurityNumber + ")";
    }
    
    public void addHistoryItem(String diagnosis, String shortDiagnosis, String unit, String date) {
        this.history.add(new HistoryItem(historyIdCount, date, unit, diagnosis, shortDiagnosis));
        historyIdCount++;
    }

    public List<HistoryItem> getHistory() {
        return history;
    }
    
    public void addMedicationItem(String date, String medName) {
        this.medications.add(new MedicationItem(date, medName));
    }
    
    public List<MedicationItem> getMedications() {
        return medications;
    }
    
    public HeartSensor getHeartSensor(){
        return this.heartSensor;
    }
    
    public BloodPressureSensor getBloodPressureSensor(){
        return this.bloodPressureSensor;
    }
    
    public void updateVitals() {
        this.bloodPressureSensor.update();
        this.heartSensor.update();
    }            
}
