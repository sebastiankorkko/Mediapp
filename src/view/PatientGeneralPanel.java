package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.Patient;

public class PatientGeneralPanel extends JPanel {

    private Patient activePatient;
    private JTextField address;
    private JTextField phoneNumber;
    private JPanel heartBpmPanel;
    private JPanel bloodPressurePanel;
    private int heartBpm;
    private int lowBloodPressure;
    private int highBloodPressure;

    // Yleiset tiedot-paneeli //
    public PatientGeneralPanel() {
    }

    public void activate(Patient patient) {
        
            this.removeAll();
            this.revalidate();
            this.repaint();            
        
            this.activePatient = patient;
            if(this.activePatient.getHeartSensor().isInstalled()){
                heartBpm = this.activePatient.getHeartSensor().getBpm();
            }
            if(this.activePatient.getBloodPressureSensor().isInstalled()){
                lowBloodPressure = this.activePatient.getBloodPressureSensor().getLow();
                highBloodPressure  = this.activePatient.getBloodPressureSensor().getHigh();
            }
            
            address = new JTextField(20);
            address.setText(activePatient.getPatienAddress());
            address.setEditable(false);
            phoneNumber = new JTextField(20);
            phoneNumber.setText(activePatient.getPatientPhoneNumber());
            phoneNumber.setEditable(false);
            
            // Gridbag-layout //
            
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            Insets rightPadding = new Insets(0, 0, 0, 3);
            Insets leftPadding = new Insets(0, 3, 0, 0);
            Insets noPadding = new Insets(0, 0, 0, 0);

            // First row //
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 1;
            gbc.weighty = 4;
            add(new JLabel(""), gbc);

            gbc.gridx++;
            add(new JLabel(""), gbc);

            // Next Row //
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(0, 0, 0, 0);
            String patientName = activePatient.getFirstName() + " " + activePatient.getLastName();
            JLabel customNameLabel = new JLabel(patientName);
            Font customFont = new Font("Courier", Font.BOLD, 16);
            customNameLabel.setFont(customFont);
            customNameLabel.setMaximumSize(new Dimension(220,50));
            add(customNameLabel, gbc);

            gbc.gridx++;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 170, 0, 0);
            String patientSsd = activePatient.getSocialSecurityNumber();
            JLabel customSsdLabel = new JLabel(patientSsd);
            customSsdLabel.setMaximumSize(new Dimension(180,50));
            customSsdLabel.setFont(customFont);
            add(customSsdLabel, gbc);

            // Next row //
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = rightPadding;
            add(new JLabel("Kotiosoite: "), gbc);

            gbc.gridx++;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = leftPadding;
            add(address, gbc);

            // Next row //
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = rightPadding;
            add(new JLabel("Puhelinnumero: "), gbc);

            gbc.gridx++;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = leftPadding;
            add(phoneNumber, gbc);

            // Next row //
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.weighty = 2;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(0, 0, 0, 50);
            heartBpmPanel = new JPanel();
            heartBpmPanel.setLayout(new BorderLayout());
            String bpm = "" + heartBpm;
            JLabel heartRateLabel = new JLabel((bpm));
            heartRateLabel.setFont(customFont);
            heartBpmPanel.add(heartRateLabel, BorderLayout.SOUTH);
            add(heartBpmPanel, gbc);
            int emptySpaceX = 15;
            int emptySpaceY = 10;

            Border emptyBorder = BorderFactory.createEmptyBorder(emptySpaceY, emptySpaceX, emptySpaceY, emptySpaceX);
            Border bpmTitleBorder = BorderFactory.createTitledBorder("Syke");
            heartBpmPanel.setBorder(BorderFactory.createCompoundBorder(bpmTitleBorder, emptyBorder));

            gbc.gridx++;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 170, 0, 0);
            bloodPressurePanel = new JPanel();
            bloodPressurePanel.setLayout(new BorderLayout());
            String pressure = "" + highBloodPressure + "/" + lowBloodPressure;
            JLabel bloodPressureLabel = new JLabel(pressure);
            bloodPressureLabel.setFont(customFont);
            bloodPressurePanel.add(bloodPressureLabel, BorderLayout.CENTER);
            add(bloodPressurePanel, gbc);
            Border bloodTitleBorder = BorderFactory.createTitledBorder("Verenpaine");
            bloodPressurePanel.setBorder(BorderFactory.createCompoundBorder(bloodTitleBorder, emptyBorder));

            // Next row //                
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.weighty = 5;
            add(new JLabel(""), gbc);

            gbc.gridx++;
            add(new JLabel(""), gbc);  
    }
}
