package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.HistoryItem;

// Käyntitaulukkomalli //
public class VisitsTableModel extends AbstractTableModel {
    
    private List<HistoryItem> db;
    private String[] columnNames = {"Päivämäärä", "Palveluyksikkö", "Syy"};
    
    public VisitsTableModel(){        
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HistoryItem historyItem = db.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return historyItem.getDate();
            case 1:
                return historyItem.getUnit();
            case 2:
                return historyItem.getShortDiagnosis();
        }
        return null;
    }
    
    public void setVisitsData(List<HistoryItem> db) {
        this.db = db;
    }    
    
}
