package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import model.Patient;

// Potilaslistamalli hakukentän tuloslaatikkoon //
public class PatientListModel extends DefaultListModel {
    private List<Patient> patientDb;
    private List<Patient> backupDb;
    
    public PatientListModel(){
    }
    
    public void setDb(List<Patient> db) {
        this.patientDb = db;
        this.backupDb = db;
    }

    @Override
    public int getSize() {
        return patientDb.size();
    }

    @Override
    public Object getElementAt(int index) {
        Patient patient = patientDb.get(index);
        return patient;    
    }
    
    public void refresh(){
        super.fireContentsChanged(this, 0, patientDb.size());
    }
    
    public void search(String input){              
        patientDb = backupDb;
        List<Patient> searchResults = new ArrayList<Patient>();
        String searchTerm = input.toLowerCase();
        searchTerm = searchTerm.trim();
        for(Patient patient : patientDb) {
            String patientFirstName = patient.getFirstName().toLowerCase();
            String patientLastName = patient.getLastName().toLowerCase();
            if(patientFirstName.contains(searchTerm) || patientLastName.contains(searchTerm)){
                searchResults.add(patient);
            }
        }               
        patientDb = searchResults;
        refresh();        
    }    
}