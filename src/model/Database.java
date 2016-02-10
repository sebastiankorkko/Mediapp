package model;

import java.util.LinkedList;
import java.util.List;

public class Database {
    
    private List<Patient> patientDb;
    
    public Database(){
        this.patientDb = new LinkedList<Patient>();
        addExamplePatients();
       
    }
    
    public void add(Patient patient) {
        this.patientDb.add(patient);        
    }           
    
    public List<Patient> getPatients() {
        return patientDb;
    }
    
    public Patient getPatient(String comparedSSD) {
       for(Patient patient : patientDb){
           if(patient.getSocialSecurityNumber().equals(comparedSSD)){
               return patient;
           }
       }
       return null;
    }
    
    public void update() {
        for(Patient patient : patientDb) {
            patient.updateVitals();
        }
    }    
    
    public void addExamplePatients() {
        patientDb.add(new Patient("Erkki", "Esimerkki", "Kohtauskatu 2", "044560960", "010120-112E", true, true));
        patientDb.add(new Patient("Pekka", "Potilas", "Kihtikuja 5", "040911922", "301250-991P", true, true));
        patientDb.add(new Patient("Kirsi", "Kipeähkölä", "Oivoitie 9", "044044112", "120691-123A", true, true));
        patientDb.add(new Patient("Mari", "Mysteeri", "Arpakatu 7", "0400112331", "220568-111X", true, true));
        patientDb.get(0).addHistoryItem("Idiootti kaatui portaissa", "Kaatui portaissa", "Myllyoja TK", "28.7.2015");
        patientDb.get(0).addHistoryItem("Idiootti kaatui taas portaissa", "Kaatui portaissa", "Myllyoja TK", "29.7.2015");    
        patientDb.get(0).addMedicationItem("28.7.2015", "Burana 400mg");
        patientDb.get(0).addMedicationItem("29.7.2015", "Burana 800mg");
        patientDb.get(0).addMedicationItem("29.7.2015", "Voltaren 5mg");
        patientDb.get(1).addHistoryItem("Tippui puuhevosen selästä", "Tippui", "Kontinkangas", "18.9.2015");
        patientDb.get(1).addHistoryItem("Tippui oikean hevosen selästä", "Tippui korkeammalta", "OYS", "19.9.2015");
        patientDb.get(1).addMedicationItem("18.9.2015", "Atenolol 100mg");
        patientDb.get(1).addMedicationItem("19.9.2015", "Varafariini 3mg");
        patientDb.get(2).addHistoryItem("Melko kipeästi sattuu vasempaan korvaan uidessa valtameressä yön pimeydessä, mutta huhtikuisina torstaiaamuina myös oikea peukalo oireilee", "Korvakipua uidessa", "Höyhtyä TK", "12.5.2014");
        patientDb.get(2).addHistoryItem("Palatessa kotiin Höyhtyä TK:sta jäi Über-kuskin alle risteyksessä, vasen lonkka murtunut, oikea tärykalvo puhki, peukalot osoittavat alaspäin", "Auto-onnettomuus", "OYS teho-osasto", "12.5.2014");    
        patientDb.get(2).addMedicationItem("12.5.2014", "Lumelääke 10mg");
        patientDb.get(2).addMedicationItem("12.5.2014", "Burana 1600mg");
        patientDb.get(2).addMedicationItem("12.5.2014", "Voltaren 25mg");
        patientDb.get(3).addHistoryItem("Potilaan mukaan kipua jossain selässä tai sitten polvessa", "Selkä-/polvikipu", "Kiiminki TK", "21.12.2015");
        patientDb.get(3).addHistoryItem("Potilaan mukaan vasen silmä kuulee epäilyttävän hyvin, epäilee pimeitä voimia, pakkohoitopäätös annettu", "Pakkohoitopäätös", "Haukipudas TK", "22.12.2015");    
    }
}
