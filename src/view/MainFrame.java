package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {
    
    private LogoPanel logoPanel;
    private LeftPanel leftPanel;
    private CardPanel cardPanel;
    private AddPatientDialog addPatientDialog;
    private Controller controller;
    private LoginDialog loginDialog;
    
    public MainFrame() throws IOException, InterruptedException{
       super("MediApp");
         
        // Sisäänkirjautumisikkuna //
        loginDialog = new LoginDialog(this);        
        loginDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        loginDialog.setVisible(true);
                                       
        setLayout(new BorderLayout());         
                        
        controller = new Controller();                
        cardPanel = new CardPanel();                       
        logoPanel = new LogoPanel();
        leftPanel = new LeftPanel();      
                        
        // Asettaa databasen vasemmalla oleville paneeleille //
        leftPanel.setDbForPanels(controller.getPatients());
        
        // Asetetaan listenerit vasemmalle oleville paneeleille //        
        leftPanel.setListenerForPanels(new PatientSelectedListener() {
            @Override
            public void patientSelectedEventOccured(PatientSelectedEvent e) { 
                cardPanel.activate(controller.setPatientActive(e));
                if(logoPanel.isVisible() == true){
                    logoPanel.setVisible(false);
                    remove(logoPanel);
                }
                if(cardPanel.isVisible() == false){
                    cardPanel.setVisible(true);
                    add(cardPanel, BorderLayout.CENTER);                    
                }                
            }
        });
        
        // Asetetaan cardpanelille listener, joka tarkkailee välilehden vaihtumista //
        cardPanel.setCardPanelSwitchListener(new CardPanelTabSwitchListener() {
            @Override
            public void cardPanelTabSwitchEventOccured(CardPanelTabSwitchEvent e) {
                if(e.getTabCommand().equals("CloseCardPanel")) {
                    cardPanel.closeCardPanel();
                    cardPanel.setVisible(false);
                    remove(cardPanel);
                    logoPanel.setVisible(true);
                    add(logoPanel);                      
                } else if((e.getTabCommand().equals("HistoryTab")) && !(e.getTabCommand().equals(e.getActivePanel()))){
                    cardPanel.activateHistory();
                } else if(e.getTabCommand().equals("GeneralTab")) {
                    cardPanel.activateGeneral();                    
                } else if(e.getTabCommand().equals("AutomationTab")){
                    cardPanel.activateAutomation();
                }
            }
        });
        
        // Potilaan lisäys-ikkuna //
        addPatientDialog = new AddPatientDialog(this);
        addPatientDialog.setPatientListener(new PatientListener() {
            @Override
            public void addPatientEventOccured(AddPatientEvent e) {
                controller.addPatient(e);
                leftPanel.refreshPanels();
            }
        });
        
        // Listener, joka kysyy haluaako joku todella lopettaa ohjelman //
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {                
                int result = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Haluatko varmasti lopettaa ohjelman?", 
                        "Lopeta ohjelma", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION) {
                    dispose();
                    System.gc();                    
                }
            }
        });               
        
        // Luo valikon //
        setJMenuBar(createMenuBar());                      
        
        add(leftPanel, BorderLayout.WEST);
        add(logoPanel, BorderLayout.CENTER);
        cardPanel.setVisible(false);                
        
        setSize(872,600);
        setMinimumSize(new Dimension(872, 600));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Ajastin, joka päivittää potilaita 10 sekunnin välein, simuloi sykkeen ja verenpaineen muutoksia //
        Timer timer = new Timer(10000, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
             controller.updatePatients();
           }            
        });
        timer.start();
    }
    
    // Luodaan valikkokomponentit //    
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
                
        JMenu settingsMenu = new JMenu("Asetukset");
        
        JMenu fileMenu = new JMenu("Tiedosto");
        JMenuItem addPatientItem = new JMenuItem("Lisää potilas...");
        JMenuItem exitItem = new JMenuItem("Lopeta ohjelma");
        fileMenu.add(addPatientItem);
        fileMenu.add(exitItem);
        
        addPatientItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               addPatientDialog.setVisible(true);
            }                   
        });        
                
        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);
        
        // Listener ohjelman lopetus-napille //
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                WindowListener[] listeners = getWindowListeners();                
               for(WindowListener listener : listeners) {
                        listener.windowClosing(new WindowEvent(MainFrame.this, 0));                        
               }
                
            }                       
        });
        
        // Mnemonicit ja kiihdyttimet valikolle //
        
        fileMenu.setMnemonic(KeyEvent.VK_T);
        exitItem.setMnemonic(KeyEvent.VK_L);
        addPatientItem.setMnemonic(KeyEvent.VK_P);
        
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        addPatientItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        
        return menuBar;
    }
}
