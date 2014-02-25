package com.massdown.main;

import com.massdown.views.MainWindow;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Guillermo
 */
public class Main {
    public static void main(String args[])   {
 
       
        UIManager.put("ScrollBar.thumb", new javax.swing.plaf.ColorUIResource(50,50,50));
        UIManager.put("ScrollBar.thumbDarkShadow", new javax.swing.plaf.ColorUIResource(50,50,50));
        UIManager.put("ScrollBar.thumbHighlight", new javax.swing.plaf.ColorUIResource(50,50,50));
        UIManager.put("ScrollBar.thumbShadow", new javax.swing.plaf.ColorUIResource(50,50,50));
        UIManager.put("ScrollBar.background", new javax.swing.plaf.ColorUIResource(56,56,56));
        
       try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });        
        
    }
}
