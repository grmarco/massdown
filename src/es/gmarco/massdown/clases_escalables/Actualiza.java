
package es.gmarco.massdown.clases_escalables;

import es.gmarco.massdown.recursos.Constantes;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JOptionPane;


public class Actualiza {
    
    private double versionActual;
    private double versionNueva;
    
    public Actualiza(boolean comprobacionSilenciosa) throws MalformedURLException, IOException {
        this.versionNueva = CompruebaVersion();
        this.versionActual = Constantes.version;
        
        if(this.versionActual != this.versionNueva) {
            Actualizar();
        } else {
            if(!comprobacionSilenciosa)
                JOptionPane.showInternalMessageDialog(new JOptionPane(), "MassDown está actualizado");
        }
    }
    
    public static double CompruebaVersion() throws MalformedURLException, IOException {
        String codigoFuente;        
        double version;
        
        codigoFuente = MetodosUtiles.ObtenerCodigoFuente("https://github.com/grmarco/massdown/blob/master/src/es/gmarco/massdown/recursos/Constantes.java")[1];
        version = Double.parseDouble(MetodosUtiles.ObtenerCadenaEntreTags(codigoFuente, 
                "n\">version</span> <span class=\"o\">=</span> <span class=\"mf\">", 
                "<span class=\"o\">;</span>", 60, 7));        
        
        return version;
    }
    
    
    public static void Actualizar() {
        
    }
    
}
