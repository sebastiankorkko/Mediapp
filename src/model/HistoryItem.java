package model;

public class HistoryItem {
    private int id;
    private String date;
    private String unit;
    private String diagnosis;
    private String shortDiagnosis;
    
    public HistoryItem(int id, String date, String unit, String diagnosis,
                       String shortDiagnosis) {
        this.id = id;
        this.date = date;
        this.unit = unit;
        this.diagnosis = diagnosis;
        this.shortDiagnosis = shortDiagnosis;
    }

    public String getDate() {
        return date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getShortDiagnosis() {
        return shortDiagnosis;
    }

    public int getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }    
}
