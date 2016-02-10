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

public class PatientAutomationRow extends JPanel {
    private JComboBox sensorBox;
    private JSpinner valueSpinner;
    private JComboBox function;
    private JButton removeRowButton;
    private SpinnerModel valueModel;
    private String[] sensors = {"Max syke", "Min syke", "Max alap", "Min alap", "Max yläp", "Min yläp"  };
    private String[] actions = {"Sähköisku", "Ambulanssi", "Lääke 1", "Rauhoittava" };

   // Yksittäinen automaatiorivi //    
    public PatientAutomationRow() {
                
        valueModel = new SpinnerNumberModel(50, 0, 250, 1);             
        valueSpinner = new JSpinner(valueModel);
        valueSpinner.setPreferredSize(new Dimension(42, 25));
        
        sensorBox = new JComboBox(sensors);
        sensorBox.setPreferredSize(new Dimension(93, 25));
        function = new JComboBox(actions);        
        function.setPreferredSize(new Dimension(134, 25));
        removeRowButton = new JButton("-");
        removeRowButton.setFont(new Font("Courier", Font.BOLD, 11));
        
        valueSpinner.setToolTipText("Valitse raja-arvo");
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
        add(valueSpinner, gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.CENTER;
        add(function, gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        add(removeRowButton, gbc);      
        
        // Rivin miinusmerkin listener, poistaa rivin //
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

