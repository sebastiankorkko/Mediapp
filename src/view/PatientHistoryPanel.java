package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import model.HistoryItem;
import model.MedicationItem;
import model.Patient;

public class PatientHistoryPanel extends JPanel {
    
    private Patient activePatient;
    private JTable visitsTable;
    private VisitsTableModel visitsTableModel;
    private JTable medsTable;
    private MedsTableModel medsTableModel;
    private JScrollPane visitsScrollPane;
    private JScrollPane medsScrollPane;
    
    // Historiapaneeli //
    public PatientHistoryPanel(){        
    }
    
    public void activate(Patient patient){
            if (this.activePatient != null) {
                if((!this.activePatient.getSocialSecurityNumber().equals(patient.getSocialSecurityNumber()))){
                    this.removeAll();
                    this.revalidate();
                    this.repaint();
                }
            }            
        
        this.activePatient = patient;
        
        // Ylempi käyntitaulukko //
        visitsTableModel = new VisitsTableModel();
        setVisitsData(activePatient.getHistory());
        visitsTable = new JTable(visitsTableModel);
        
        // Tuplaklikkauskuuntelija, joka avaa laajemman hoitokertomuksen //
           visitsTable.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent e) {                               
                if(e.getClickCount() == 2) {
                    int row = visitsTable.rowAtPoint(e.getPoint());
                    JOptionPane.showMessageDialog(visitsTable, getActivePatient().getHistory().get(row).getDiagnosis(), "Hoitokertomus", JOptionPane.PLAIN_MESSAGE);                    
                }
            }
        });
        
        // Alempi lääkitystaulukko //   
        medsTableModel = new MedsTableModel();
        setMedsData(activePatient.getMedications());
        medsTable = new JTable(medsTableModel);
        
        visitsScrollPane = new JScrollPane(visitsTable);
        medsScrollPane = new JScrollPane(medsTable);
        Dimension dimensions = new Dimension(250,230);
        visitsScrollPane.setPreferredSize(dimensions);
        medsScrollPane.setPreferredSize(dimensions);
        
        Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
        Border visitsTitledBorder = BorderFactory.createTitledBorder("Käynnit");
        visitsScrollPane.setBorder(BorderFactory.createCompoundBorder(visitsTitledBorder, emptyBorder));
                
        Border medsTitledBorder = BorderFactory.createTitledBorder("Lääkkeet");
        medsScrollPane.setBorder(BorderFactory.createCompoundBorder(medsTitledBorder, emptyBorder));
        
        setLayout(new BorderLayout(30, 0));        
        add(visitsScrollPane, BorderLayout.NORTH);
        add(medsScrollPane, BorderLayout.CENTER);
        
    }
    
    public void setVisitsData(List<HistoryItem> db) {
        visitsTableModel.setVisitsData(db);
    }
    
    public void setMedsData(List<MedicationItem> db) {
        medsTableModel.setMedsData(db);
    }
    
    public Patient getActivePatient(){
        return this.activePatient;
    }        
}
