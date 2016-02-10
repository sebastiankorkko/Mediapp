package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class Mediapp {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    new MainFrame();
                } catch (IOException ex) {
                    Logger.getLogger(Mediapp.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Mediapp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    });
}
}