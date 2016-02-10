package view;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.Patient;

// LeftPanel sisältää hakupaneelin ja huomautuspaneelin, ei sisällä mitään erityisiä toimintoja //
public class LeftPanel extends JPanel {
    
    private SearchPanel searchPanel;
    private NotificationPanel notificationPanel;
    
    public LeftPanel(){
      setLayout(new BorderLayout());
     
      searchPanel = new SearchPanel();
      notificationPanel = new NotificationPanel();
                                    
      add(searchPanel, BorderLayout.NORTH);
      add(notificationPanel, BorderLayout.SOUTH);
                        
      Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
      Border lineBorder = BorderFactory.createTitledBorder("");
      setBorder(BorderFactory.createCompoundBorder(emptyBorder, lineBorder));
    }
    
    public void setDbForPanels(List<Patient> db){        
        searchPanel.setDb(db);
        notificationPanel.setDb(db);
    }    
    
    public void refreshPanels() {       
        searchPanel.refreshList();
        notificationPanel.refreshList();
    }
    
    public void setListenerForPanels(PatientSelectedListener listener){
        notificationPanel.setPatientSelectedListener(listener);
        searchPanel.setPatientSelectedListener(listener);
    }
    
}
