package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// Simuloi sisäänkirjautumista // 
public class LoginDialog extends JDialog {
    private JTextField username;
    private JPasswordField password;
    private JButton login;
    private JButton cancel;
    
    public LoginDialog(JFrame parent) {
        super(parent, "Kirjaudu sisään", true);
        
        username = new JTextField(20);
        password = new JPasswordField(20);
        login = new JButton("Kirjaudu sisään");
        cancel = new JButton("Peruuta");
            
        // gridbag-layout //
        
        JPanel dialogPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();                       
        
        // First row //
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dialogPanel.add(new JLabel("Käyttäjätunnus: ('demo') "), gbc);
        
        gbc.gridx++;
        gbc.gridwidth = 2;
        dialogPanel.add(username, gbc);
        
        // Next row //
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        dialogPanel.add(new JLabel("Salasana: ('demo')"), gbc);
        
        gbc.gridx++;
        gbc.gridwidth = 2;
        dialogPanel.add(password, gbc);
        
        // Erillinen nappipaneeli //
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(login);
        buttonPanel.add(cancel);
        this.getRootPane().setDefaultButton(login);
        
        
        // action-listenerit //
                
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(getUsername().equals("demo") && getPassword().equals("demo")){
                   
                   JOptionPane.showMessageDialog(LoginDialog.this, 
                           "Tervetuloa MediAppiin!", 
                           "Onnistunut sisäänkirjautuminen", 
                           JOptionPane.INFORMATION_MESSAGE);
                           dispose();
               } else {
                   JOptionPane.showMessageDialog(LoginDialog.this, 
                           "Virheellinen käyttäjätunnus tai salasana", 
                           "Kirjautuminen epäonnistui", 
                           JOptionPane.ERROR_MESSAGE);
                           username.setText("");
                           password.setText("");
               }
            }            
        });
        
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                System.exit(0);
            }            
        });
        
        setLayout(new BorderLayout());        
        setResizable(false);
        setLocationRelativeTo(parent);        
        add(dialogPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
        pack();                
    }
    
    public String getUsername() {
        return username.getText();
    }
    
    public String getPassword() {
        return new String(password.getPassword());
    }
}