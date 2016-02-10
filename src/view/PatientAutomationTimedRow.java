package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class PatientAutomationTimedRow extends JPanel {
    private JComboBox sensorBox;
    private JSpinner timeIntervalSpinner;
    private JComboBox function;
    private JButton removeRowButton;
    private SpinnerModel timeIntervalModel;
    private String[] sensors = {"Sydän", "Verenpaine" };
    private String[] actions = {"Sydänlääke 1", "Sydänlääke 2", "Verenpainelääke 1", "Verenpainelääke 2" };
       
    // Yksittäinen ajastettu rivi //
    public PatientAutomationTimedRow() {
                
        timeIntervalModel = new SpinnerNumberModel(24, 1, 72, 1);             
        timeIntervalSpinner = new JSpinner(timeIntervalModel);
        timeIntervalSpinner.setPreferredSize(new Dimension(42, 25));
        
        sensorBox = new JComboBox(sensors);
        function = new JComboBox(actions);           
        removeRowButton = new JButton("-");
        removeRowButton.setFont(new Font("Courier", Font.BOLD, 11));
        
        timeIntervalSpinner.setToolTipText("Valitse aikaväli (tuntia)");
        sensorBox.setToolTipText("Valitse sensori");
        function.setToolTipText("Valitse toiminto");
        removeRowButton.setToolTipText("Poista rivi");
        
        // Gridbag-layout //
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
                
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,20,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(sensorBox, gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        add(timeIntervalSpinner, gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        add(function, gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        add(removeRowButton, gbc);      
        
        // Miinusmerkin kuuntelija, poistaa rivin //
        removeRowButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                revalidate();
                repaint();
            }            
        });                
    }    
}

