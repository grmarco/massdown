package es.gmarco.massdown.recursos;

import java.util.prefs.Preferences;

public class Configuracion {

    private final Preferences prefs;
    public static double version = 1.1;
    private static String directorioDeDescarga;
    private static boolean descargaEnCola;

    public Configuracion() {
        prefs = Preferences.userRoot().node(this.getClass().getName());
        directorioDeDescarga = prefs.get("DirectorioDeDescarga", ".");
        descargaEnCola = prefs.getBoolean("DescargaEnCola", true);
    }

    public static double getVersion() {
        return version;
    }

    public static String getDirectorioDeDescarga() {
        return directorioDeDescarga;
    }

    public void setDirectorioDeDescarga(String directorioDeDescarga) {        
        Configuracion.directorioDeDescarga = directorioDeDescarga;
        prefs.put("DirectorioDeDescarga", Configuracion.directorioDeDescarga);
    }

    public static boolean isDescargaEnCola() {
        return descargaEnCola;
    }

    public void setDescargaEnCola(boolean descargaEnCola) {
        Configuracion.descargaEnCola = descargaEnCola;
        prefs.putBoolean("DescargaEnCola", Configuracion.descargaEnCola);
    }
  
    
}
