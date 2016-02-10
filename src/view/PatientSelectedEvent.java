package view;

import java.util.EventObject;
import model.Patient;

public class PatientSelectedEvent extends EventObject {
    
    private Patient patient;

    public PatientSelectedEvent(Object source, Patient patient) {
        super(source);
        this.patient = patient;
    }        

    public Patient getPatient() {
        return this.patient;
    }               
}
