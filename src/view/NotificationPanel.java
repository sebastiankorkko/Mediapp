package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.Border;
import model.Patient;

// Vasemmassa alalaidassa sijaitseva huomautuspaneeli. Alkuperäisen suunnitelman mukaan tämän piti olla dynaamisesti päivittyvä, //
// useiden vaikeuksien jälkeen erittäin haastava toteuttaa tässä ajassa. Saa alussa databasen ja päivittää sitä, jos uusia potilaita //
// ilmenee, mutta ei nosta ja pudota potilaita sykkeen ja verenpaineen perusteella, kuten oli tarkoitus //

public class NotificationPanel extends JPanel {
    private JList notificationList;
    private PatientListModel listModel;
    private PatientSelectedListener patientSelectedListener;
    
    public NotificationPanel(){
        
        listModel = new PatientListModel();
        notificationList = new JList(listModel);
        Color color = this.getBackground();
        notificationList.setBackground(color);
                
        // Tuplaklikkauslistner, joka avaa klikatun potilaan infopaneeliin //
        notificationList.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent e) {
                JList theList = (JList) e.getSource();
                if(e.getClickCount() == 2) {
                    int index = theList.locationToIndex(e.getPoint());
                    if(index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        if(patientSelectedListener != null){                            
                            PatientSelectedEvent ev = new PatientSelectedEvent(this, (Patient)o);
                            patientSelectedListener.patientSelectedEventOccured(ev);
                        }
                    }
                }
                
            }
        });
        
        // Tooltip, joka näyttää potilaan nimi + sotu //
        notificationList.addMouseMotionListener(new MouseMotionAdapter() { 
            @Override
            public void mouseMoved(MouseEvent e) {
                JList jList = (JList)e.getSource();
                ListModel listModel = jList.getModel();
                int index = jList.locationToIndex(e.getPoint());
                if(index > -1) {
                    jList.setToolTipText(listModel.getElementAt(index).toString());
                }
            }
        });    
                      
        setLayout(new BorderLayout());        
        add(notificationList, BorderLayout.CENTER);
        
        int emptySpaceY = 5;
        int emptySpaceX = 5;
        Border emptyBorder = BorderFactory.createEmptyBorder(emptySpaceY, emptySpaceX, emptySpaceY, emptySpaceX);
        Border titleBorder = BorderFactory.createTitledBorder("Huomautukset");
        setBorder(BorderFactory.createCompoundBorder(emptyBorder, titleBorder));        
    }
    
    public void setDb(List<Patient> db) {
        listModel.setDb(db);        
    }
    
    public void refreshList() {
      this.listModel.refresh();
    }
    
    public void setPatientSelectedListener(PatientSelectedListener listener){
        this.patientSelectedListener = listener;
    }
}