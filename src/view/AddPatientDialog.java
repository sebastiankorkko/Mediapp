package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class AddPatientDialog extends JDialog {
    
    private JTextField firstName;
    private JTextField lastName;
    private JTextField ssd;
    private JTextField address;
    private JTextField phoneNumber;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel formPanel;
    private JPanel buttonsPanel;
    private PatientListener patientListener;
        
    public AddPatientDialog(JFrame parent){
        super(parent, "Lisää potilas", false);
        
        firstName = new JTextField(10);
        lastName = new JTextField(10);
        ssd = new JTextField(10);
        address = new JTextField(10);
        phoneNumber = new JTextField(10);
        addButton = new JButton("Lisää potilas");
        cancelButton = new JButton("Peruuta");
        formPanel = new JPanel();
        buttonsPanel = new JPanel();
              
        // Main layout //
        setLayout(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 1;
        gbcMain.weighty = 1;
        gbcMain.fill = GridBagConstraints.NONE;
        gbcMain.anchor = GridBagConstraints.NORTH;
        add(formPanel, gbcMain);
                        
        // Form Panel //
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();   
        
        Insets rightPadding = new Insets(0,0,0,5);
        Insets noPadding = new Insets(0,0,0,0);
        
        int emptySpace = 15;
        Border emptyBorder = BorderFactory.createEmptyBorder(emptySpace, emptySpace, emptySpace, emptySpace);
        Border formBorder = BorderFactory.createTitledBorder("Uuden potilaan tiedot");
        formPanel.setBorder(BorderFactory.createCompoundBorder(emptyBorder, formBorder));
        
        // First row //
        
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;        
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = rightPadding;
        formPanel.add(new JLabel("Etunimi: "), gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = noPadding;
        formPanel.add(firstName, gbc);
               
        // Next row //
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = rightPadding;
        formPanel.add(new JLabel("Sukunimi: "), gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = noPadding;
        formPanel.add(lastName, gbc);
        
        // Next row //
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = rightPadding;
        formPanel.add(new JLabel("Henkilötunnus: "), gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = noPadding;
        formPanel.add(ssd, gbc);
        
        // Next row //
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = rightPadding;
        formPanel.add(new JLabel("Osoite "), gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = noPadding;
        formPanel.add(address, gbc);
        
        // Next row //
        
        gbc.gridx = 0;
        gbc.gridy++;        
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = rightPadding;
        formPanel.add(new JLabel("Puhelinnumero: "),gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = noPadding;
        formPanel.add(phoneNumber, gbc);
                      
        // Main layout - buttons panel //
        
        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        gbcMain.weightx = 1;
        gbcMain.weighty = 1;
        gbcMain.fill = GridBagConstraints.NONE;
        gbcMain.anchor = GridBagConstraints.SOUTH;
        add(buttonsPanel, gbcMain);
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(addButton);
        buttonsPanel.add(cancelButton);
        this.getRootPane().setDefaultButton(addButton);
        
        // Tehdään lisäys ja cancel-napeista samankokoiset //
        Dimension buttonSize = cancelButton.getPreferredSize();
        addButton.setSize(buttonSize);
        
        // Action-listenerit //
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPatientEvent ev = new AddPatientEvent(this, firstName.getText(), lastName.getText(), address.getText(), ssd.getText(), phoneNumber.getText());
                
                if(patientListener != null){
                    patientListener.addPatientEventOccured(ev);
                }
                setVisible(false);
            }                        
        });
                
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }                        
        });
        
        this.setSize(300, 250);
        this.setMinimumSize(new Dimension(300, 250));     
        setLocationRelativeTo(null);
    }
    
    public void setPatientListener(PatientListener patientListener){
        this.patientListener = patientListener;
    }
}
