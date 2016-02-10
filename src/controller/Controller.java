package controller;

import java.util.List;
import model.Database;
import model.Patient;
import view.AddPatientEvent;
import view.PatientSelectedEvent;

public class Controller {
    Database db = new Database();
    Database notificationDb = new Database();    
    
    public void addPatient(AddPatientEvent ev){
        String firstName = ev.getFirstName();
        String lastName = ev.getLastName();
        String address = ev.getAddress();
        String ssd = ev.getSsd();
        String phoneNumber = ev.getPhoneNumber();        
        
        Patient patient = new Patient(firstName, lastName, address, phoneNumber, ssd, true, true);
        db.add(patient);        
    }
    
    public List<Patient> getPatients() {
        return db.getPatients();
    }
    
    public List<Patient> getNotificationPatients() {
        return notificationDb.getPatients();
    }
    
    public Patient setPatientActive(PatientSelectedEvent e){
        return e.getPatient();
    }
    
    public void updatePatients() {
        db.update();
    }    
}