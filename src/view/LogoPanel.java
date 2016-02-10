package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoPanel extends JPanel {
    
    //Yksinkertainen paneeli, joka näyttää logoa. Logo on julkaistu vapaaseen käyttöön: https://pixabay.com/fi/syd%C3%A4n-ekg-liit%C3%A4nt%C3%A4laite-syke-213747/ //
    public LogoPanel() throws IOException{
        ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.jpg"));
        JLabel logoLabel = new JLabel("", logo, JLabel.CENTER);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new BorderLayout());
        add(logoLabel, BorderLayout.CENTER);
        setBackground(Color.WHITE);           
    }
}
