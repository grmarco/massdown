package com.massdown.main;

import com.massdown.views.MainWindow;
import es.gmarco.massdown.recursos.Actualiza;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Guillermo
 */
public class Main {
    public static void main(String args[])   {
        
        
        new Thread() {
            @Override
            public void run() {
                try {
                    new Actualiza(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        
        }.start();        
        
        // Gestiona las actualizaciones de Massdown
        if(args.length > 0) {
            if(args[1].equals("aplicarActualizacion")) {
                Actualiza.AplicarActualizacion(Double.parseDouble(args[0]));
            }
        }
       
        // Para que las barras de scroll aparezca wapas wapas
        UIManager.put("ScrollBar.thumb", new javax.swing.plaf.ColorUIResource(50,50,50));
        UIManager.put("ScrollBar.thumbDarkShadow", new javax.swing.plaf.ColorUIResource(50,50,50));
        UIManager.put("ScrollBar.thumbHighlight", new javax.swing.plaf.ColorUIResource(50,50,50));
        UIManager.put("ScrollBar.thumbShadow", new javax.swing.plaf.ColorUIResource(50,50,50));
        UIManager.put("ScrollBar.background", new javax.swing.plaf.ColorUIResource(56,56,56));
        
       // Look and feel del sistema 
       try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
               
       // La interfaz se ejecuta en su thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });        
        
    }
}
