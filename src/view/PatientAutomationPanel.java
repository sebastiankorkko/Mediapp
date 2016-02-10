package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import model.Patient;

public class PatientAutomationPanel extends JPanel {
    
    private Patient activePatient;
    private JButton addNewTimedRow;
    private JButton addNewAutomatedRow;
    
    public PatientAutomationPanel(){        
    }
    
    // Kutsuttaessa pyyhkii paneelin ja aktivoi itsensä //
    public void activate(Patient patient){
          
        this.removeAll();
        this.revalidate();
        this.repaint();

        this.activePatient = patient;        
        
        Font buttonFont = new Font("Courier", Font.BOLD, 14);
        addNewTimedRow = new JButton("+");
        addNewTimedRow.setFont(buttonFont);
        addNewTimedRow.setToolTipText("Lisää uusi ajastettu toiminto");
        addNewAutomatedRow = new JButton("+");
        addNewAutomatedRow.setFont(buttonFont);
        addNewAutomatedRow.setToolTipText("Lisää uusi automatisoitu toiminto");
        
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border timedTitleBorder = BorderFactory.createTitledBorder("Ajastettu lääkehoito");
        Border automaticTitleBorder = BorderFactory.createTitledBorder("Automaattinen hoito");
        
        final JPanel timedPanel = new JPanel();                   
        timedPanel.setLayout(new BoxLayout(timedPanel, BoxLayout.Y_AXIS)); 
        timedPanel.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 20));
        JScrollPane timedScroll = new JScrollPane(timedPanel);
        timedScroll.setPreferredSize(new Dimension (500,180)); 
        timedScroll.setBorder(BorderFactory.createCompoundBorder(emptyBorder, timedTitleBorder));
                                
        final JPanel automaticPanel = new JPanel();                        
        automaticPanel.setLayout(new BoxLayout(automaticPanel, BoxLayout.Y_AXIS));                         
        automaticPanel.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 20));
        JScrollPane automaticScroll = new JScrollPane(automaticPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);        
        automaticScroll.setPreferredSize(new Dimension (500,180));          
        automaticScroll.setBorder(BorderFactory.createCompoundBorder(emptyBorder, automaticTitleBorder));
        
        // Gridbag-layout //
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        
        // First row //
        gbcMain.weightx = 1;
        gbcMain.weighty = 1;
        gbcMain.fill = GridBagConstraints.NONE;
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        add(timedScroll, gbcMain);
        
        gbcMain.gridx++; 
        gbcMain.anchor = GridBagConstraints.EAST;
        gbcMain.insets = new Insets(0,0,50,20);
        add(addNewTimedRow, gbcMain);
        
        // Next row //
        gbcMain.gridy++;
        gbcMain.gridx = 0;
        gbcMain.weighty = 1;       
        gbcMain.anchor = GridBagConstraints.CENTER;
        gbcMain.insets = new Insets(0,0,0,0);
        add(automaticScroll, gbcMain);
        
        gbcMain.gridx++;
        gbcMain.weighty = 1;
        gbcMain.anchor = GridBagConstraints.EAST;
        gbcMain.insets = new Insets(0,0,50,20);        
        add(addNewAutomatedRow, gbcMain);          
        
        // Action-listenerit +-napeille, lisäävät automaatiorivejä ja ajastettuja rivejä //
        
        addNewTimedRow.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                timedPanel.add(new PatientAutomationTimedRow());
                revalidate();
                repaint();
            }            
        });                
                
        addNewAutomatedRow.addActionListener(new ActionListener () { 
            @Override
            public void actionPerformed(ActionEvent e) {
                automaticPanel.add(new PatientAutomationRow());
                revalidate();
                repaint();
            }        
        });        
    }   
}


