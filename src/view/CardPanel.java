package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Patient;

// Tämä cardpanel sisältää ja vaihtaa valittavat välilehdet potilaskortissa (yleiset tiedot, historia, automaatio) //
// Päädyimme käyttämään tällaista viritelmää, koska JTabbedPaneen on äärimmäiseen vaikeaa saada sulkunappi visuaalisesti, kuten suunnittelimme (välilehtipalkkiin) //

public class CardPanel extends JPanel {
    private JButton generalPanelButton;
    private JButton historyPanelButton;
    private JButton automationPanelButton;
    private JButton xButton;
    private JPanel infoPanel;
    private JPanel buttonPanel;
    private CardLayout cl;
    private PatientGeneralPanel patientGeneralPanel;
    private PatientHistoryPanel patientHistoryPanel;
    private PatientAutomationPanel patientAutomationPanel;
    private String panelState = "";
    private CardPanelTabSwitchListener tabSwitchListener;
    
    private Patient activePatient;
    
    public CardPanel(){        
        patientGeneralPanel = new PatientGeneralPanel();
        patientHistoryPanel = new PatientHistoryPanel();
        patientAutomationPanel = new PatientAutomationPanel();        
        
        // infopanel sisältää itse paneelit //
        this.infoPanel = new JPanel();
        this.cl = new CardLayout();
        infoPanel.setLayout(cl);
        
        infoPanel.add(patientGeneralPanel, "General");
        infoPanel.add(patientHistoryPanel, "History");
        infoPanel.add(patientAutomationPanel, "Automation");
        
        //cardpanel käyttää borderlayouttia//
        setLayout(new BorderLayout());
       
        //luodaan napit//
        generalPanelButton = new JButton("Yleiset tiedot");
        historyPanelButton = new JButton("Potilashistoria");
        automationPanelButton = new JButton("Hoidonhallinta");
        xButton = new JButton("X");
        
        xButton.setFont(new Font("Courier", Font.BOLD, 12));
        xButton.setToolTipText("Sulje potilastiedot");
        
        Dimension buttonSize = historyPanelButton.getPreferredSize();
        generalPanelButton.setSize(buttonSize);
        automationPanelButton.setSize(buttonSize);
        
        //nappipaneeli on erillinen gridbag//
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // yleiset tiedot-nappi //        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(50,100,0,0);
        buttonPanel.add(generalPanelButton, gbc);
        
        // historia-nappi //
        gbc.gridx++;        
        gbc.insets = new Insets(50,0,0,0);
        buttonPanel.add(historyPanelButton, gbc);
        
        // automaatio-nappi //
        gbc.gridx++;
        buttonPanel.add(automationPanelButton, gbc);
        
        // x-nappi //
        gbc.gridx++;
        gbc.weightx = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(50,0,0,30);
        buttonPanel.add(xButton, gbc);
        
        // lisätään info- ja nappipaneeli borderlayouttiin //
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);
        
        // nappipaneelin action listenerit //
        
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabSwitchListener != null) {
                    CardPanelTabSwitchEvent ev = new CardPanelTabSwitchEvent(this, "CloseCardPanel");
                    tabSwitchListener.cardPanelTabSwitchEventOccured(ev);
                }
            }
        });
        
        historyPanelButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabSwitchListener != null) {
                    CardPanelTabSwitchEvent ev = new CardPanelTabSwitchEvent(this, "HistoryTab");
                    tabSwitchListener.cardPanelTabSwitchEventOccured(ev);
                }
            }
        });
        
        automationPanelButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabSwitchListener != null) {
                    CardPanelTabSwitchEvent ev = new CardPanelTabSwitchEvent(this, "AutomationTab");
                    tabSwitchListener.cardPanelTabSwitchEventOccured(ev);
                }
            }
        });
        
        generalPanelButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabSwitchListener != null) {
                    CardPanelTabSwitchEvent ev = new CardPanelTabSwitchEvent(this, "GeneralTab");
                    tabSwitchListener.cardPanelTabSwitchEventOccured(ev);
                }
            }
        });
        
        // lisätään mnemonicit //
        
        historyPanelButton.setMnemonic(KeyEvent.VK_H);
        automationPanelButton.setMnemonic(KeyEvent.VK_O);
        generalPanelButton.setMnemonic(KeyEvent.VK_Y);
        xButton.setMnemonic(KeyEvent.VK_X);
                
    }
    // Aktivoi potilastietopaneelin, oletuksena aina yleiset tiedot-paneeli //
    public void activate(Patient patient) {
        
        formatFonts();
        this.activePatient = patient;
        this.panelState = "GeneralTab";
        patientGeneralPanel.activate(patient);
        infoPanel.setVisible(true);
        generalPanelButton.setEnabled(false);
        Font font = generalPanelButton.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        generalPanelButton.setFont(font.deriveFont(attributes));
        cl.show(infoPanel, "General");
    }
    
    public void activateHistory() {
        
        Font font = historyPanelButton.getFont();
        Map attributes = font.getAttributes();
        
        if(this.panelState.equals("GeneralTab")){            
            generalPanelButton.setFont(font.deriveFont(attributes));
            generalPanelButton.setEnabled(true);
        } else if(this.panelState.equals("AutomationTab")) {            
            automationPanelButton.setFont(font.deriveFont(attributes));
            automationPanelButton.setEnabled(true);
        }        
                
        this.panelState = "HistoryTab";
                
        historyPanelButton.setEnabled(false);                
        
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        historyPanelButton.setFont(font.deriveFont(attributes));
        patientHistoryPanel.activate(activePatient);                
        cl.show(infoPanel, "History");                
    } 
    
    public void activateGeneral(){        
        Font font = generalPanelButton.getFont();
        Map attributes = font.getAttributes();
        
        if(this.panelState.equals("HistoryTab")){
            historyPanelButton.setFont(font.deriveFont(attributes));
            historyPanelButton.setEnabled(true);
        } else if(this.panelState.equals("AutomationTab")) {
            automationPanelButton.setFont(font.deriveFont(attributes));
            automationPanelButton.setEnabled(true);
        }
        this.panelState = "GeneralTab";
        
        generalPanelButton.setEnabled(false);
        
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        generalPanelButton.setFont(font.deriveFont(attributes));
        cl.show(infoPanel, "General");                 
    }
    
    public void activateAutomation(){
        Font font = automationPanelButton.getFont();
        Map attributes = font.getAttributes();
        
        if(this.panelState.equals("HistoryTab")){
            historyPanelButton.setFont(font.deriveFont(attributes));
            historyPanelButton.setEnabled(true);
        } else if(this.panelState.equals("GeneralTab")){
            generalPanelButton.setFont(font.deriveFont(attributes));
            generalPanelButton.setEnabled(true);           
        }
        patientAutomationPanel.activate(activePatient);
        this.panelState = "AutomationTab";
        
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        automationPanelButton.setFont(font.deriveFont(attributes));
        automationPanelButton.setEnabled(false);
        cl.show(infoPanel, "Automation");
    }
    
    // Sulkee ja nollaa cardpanelin, asettaa panelstaten tyhjäksi //
    public void closeCardPanel() {
        this.activePatient = null;
        this.panelState = "";
        formatFonts();
       
    }
    
    public void setCardPanelSwitchListener(CardPanelTabSwitchListener listener){
        this.tabSwitchListener = listener;
    }
    
    public Patient getActivePatient(){
        return this.activePatient;
    }
    
    public String getPanelState(){
        return this.panelState;
    }
    
    // Nollaa fontit, käytetään paneelin vaihdossa //
    public void formatFonts(){
        JButton button = new JButton();
        Font font = button.getFont();
        Map attributes = font.getAttributes();
        generalPanelButton.setFont(font.deriveFont(attributes));
        historyPanelButton.setFont(font.deriveFont(attributes));
        automationPanelButton.setFont(font.deriveFont(attributes));
        generalPanelButton.setEnabled(true);
        historyPanelButton.setEnabled(true);
        automationPanelButton.setEnabled(true); 
    }    
}
