/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.gmarco.massdown.recursos;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Guillermo
 */
public class Configuracion {

    public Image iconDeMassDown = new ImageIcon(getClass().getResource("/src/es/gmarco/massdown/recursos/icon.png")).getImage();
    public static double version = 0.4;
    public static String directorioDeDescarga;
    public static boolean descargaEnCola;
    public static int descargasSimultaneas;
    
}
