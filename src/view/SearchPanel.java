package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import model.Patient;

public class SearchPanel extends JPanel {
    
    private PatientListModel patientListModel;
    private JTextField searchField;
    private JButton searchButton;
    private JList searchResults;
    private JLabel noResults;
    private JScrollPane scrollPanel;
    private PatientSelectedListener patientSelectedListener;
    
    // Vasemman paneelin hakupaneeli, näyttää alussa kaikki databasessa olevat potilaat, rajaa haettaessa tuloksia //
    public SearchPanel(){
       
        patientListModel = new PatientListModel();       
        searchField = new JTextField(10);              
        searchButton = new JButton("Hae");
        noResults = new JLabel("Haku ei tuottanut tuloksia");
        searchResults = new JList(patientListModel);
        scrollPanel = new JScrollPane(searchResults, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Dimension dimensions = new Dimension(150, 100);
        scrollPanel.setPreferredSize(dimensions);
        scrollPanel.setMaximumSize(dimensions);
        scrollPanel.setMinimumSize(dimensions);
        noResults.setPreferredSize(dimensions);
        noResults.setMinimumSize(dimensions);
        noResults.setMaximumSize(dimensions);       
              
        // Gridbag-layout //
        
        setLayout(new GridBagLayout());           
        GridBagConstraints gbc = new GridBagConstraints();   
        
        // First row //
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;        
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20,20,0,10);
        add(searchField, gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20,0,0,20);
        add(searchButton, gbc);
        
        // Next row //
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,20,0,0);
        add(scrollPanel, gbc);      
        scrollPanel.setVisible(true);

        // Next row //
        gbc.gridy++;
        gbc.insets = new Insets(10,20,0,0);
        gbc.anchor = GridBagConstraints.CENTER;        
        add(noResults, gbc);        
        noResults.setVisible(false);             
        
        // Listener hakukentälle ja napille //
        Action searchAction = new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {       
               patientListModel.search((String)searchField.getText());
               searchField.setText("");
               if(patientListModel.getSize() > 0){
                   noResults.setVisible(false);
                   scrollPanel.setVisible(true);
               } else {
                   noResults.setVisible(true);
                   scrollPanel.setVisible(false);
               }
           }           
        };
        
        searchButton.addActionListener(searchAction);
        searchField.addActionListener(searchAction);
       
        // Tuplaklikkauslistener, avaa halutun potilaan //
        searchResults.addMouseListener(new MouseAdapter() { 
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
        
        // Tooltip, hakee hiiren kohdalla olevan potilaan nimen + sotun //
        searchResults.addMouseMotionListener(new MouseMotionAdapter() { 
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
    }
   
    public void setDb(List<Patient> db){
        patientListModel.setDb(db);
    }
   
    public void setPatientSelectedListener(PatientSelectedListener listener){
        this.patientSelectedListener = listener;
   }
    
    public void refreshList(){
        patientListModel.refresh();
    }
           
}
           
