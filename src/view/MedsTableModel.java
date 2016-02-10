package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.MedicationItem;


// Taulukkomalli lääkityshistorialle historia-sivulla //
public class MedsTableModel extends AbstractTableModel {
    private List<MedicationItem> db;    
    private String[] columnNames = {"Päivämäärä", "Lääkkeen nimi"};
    
    public MedsTableModel(){
    }
    
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MedicationItem medicationItem = db.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return medicationItem.getDate();
            case 1:
                return medicationItem.getMedName();
        }                  
        return null;
    }
    
    public void setMedsData(List<MedicationItem> db) {
        this.db = db;
    }    
    
}
