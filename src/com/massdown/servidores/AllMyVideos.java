
package com.massdown.servidores;

import com.massdown.Servidor;
import java.net.MalformedURLException;
import java.net.URL;

public class AllMyVideos extends Servidor {

    public AllMyVideos(Servidor servidor) {
        super(servidor);
    }

    public URL ObtenerEnlaceDescarga() throws MalformedURLException {
        
        
        return new URL("");        
    }
    
}
