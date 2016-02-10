package view;

import java.util.EventListener;

public interface PatientListener extends EventListener {
    public void addPatientEventOccured(AddPatientEvent e);    
}
