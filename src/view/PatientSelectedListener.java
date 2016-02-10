package view;

import java.util.EventListener;

public interface PatientSelectedListener extends EventListener {
    public void patientSelectedEventOccured(PatientSelectedEvent e);
}
