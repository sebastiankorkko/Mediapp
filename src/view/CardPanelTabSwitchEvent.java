package view;

import java.util.EventObject;

public class CardPanelTabSwitchEvent extends EventObject {
    private String tabCommand;
    private String activePanel;
   
    public CardPanelTabSwitchEvent(Object source, String command) {
        super(source);        
        this.tabCommand = command;
    } 
    
    public CardPanelTabSwitchEvent(Object source, String command, String panel) {
    super(source);        
    this.tabCommand = command;
    this.activePanel = panel;
    } 

    public String getTabCommand() {
        return tabCommand;
    }                 

    public String getActivePanel() {
        return activePanel;
    }        
}
