package model;

public class MedicationItem {
    
    private String date;
    private String medName;
    
    public MedicationItem(String date, String medName) {
        this.date = date;
        this.medName = medName;
    }

    public String getDate() {
        return date;
    }

    public String getMedName() {
        return medName;
    }        
}
